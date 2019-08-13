package br.com.barroso.productapi.service

import br.com.barroso.productapi.exception.ProductAlreadyException
import br.com.barroso.productapi.exception.ProductNotFoundException
import br.com.barroso.productapi.model.Inventory
import br.com.barroso.productapi.model.Product
import br.com.barroso.productapi.repository.ProductRepository
import io.leangen.graphql.annotations.GraphQLMutation
import io.leangen.graphql.annotations.GraphQLQuery
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.util.*

@Service
@GraphQLApi
class ProductService(val repository: ProductRepository) {

    private val logger = KotlinLogging.logger { }

    @GraphQLQuery(name = "productById")
    fun findProductById(productId: Int): Product {

        logger.info("Searching the product with the ID: $productId")

        val opEntityBase = this.repository.findById(productId)

        opEntityBase.ifPresent { p -> logger.info("Product ${p.productId} found!") }

        opEntityBase.orElseThrow{ProductNotFoundException()}

        return opEntityBase.get()
    }

    @GraphQLQuery(name = "productBySku")
    fun findProductBySku(sku: Int): Product {

        logger.info("Searching the product with the SKU: $sku")

        val opEntityBase = this.repository.findBySku(sku)

        opEntityBase.ifPresent { (_, sku1) -> logger.info("Product $sku1 found!") }

        opEntityBase.orElseThrow<ProductNotFoundException>{ProductNotFoundException()}

        return opEntityBase.get()
    }

    @GraphQLQuery(name = "products")
    fun findAll(): List<Product> {
        return this.repository.findAll()
    }

    @GraphQLMutation(name = "createProduct")
    fun createProduct(product: Product): Product {

        val opEntityBase = this.repository.findBySku(product.sku)

        opEntityBase.ifPresent { (_, sku) ->
            logger.error("Product $sku already registered!")
            throw ProductAlreadyException("Product already registered!")
        }

        logger.info("Creating the product with the SKU: ${product.sku}")

        this.repository.save(product)

        logger.info("Product ${product.sku} created!")

        return product
    }

    @GraphQLMutation(name = "updateProduct")
    fun updateProduct(product: Product): Boolean {

        val opEntityBase = this.repository.findBySku(product.sku)

        if (!opEntityBase.isPresent) {
            logger.error("Product ${product.sku} not found!")
            throw ProductNotFoundException("Product not found to update!")
        }

        logger.info("Updating the product with the SKU: ${product.sku}")

        this.prepareProductToUpdate(opEntityBase, product)

        this.repository.save(opEntityBase.get())

        logger.info("Product ${product.sku} updated!")

        return true
    }

    @GraphQLMutation(name = "removeProductById")
    fun removeProductById(productId: Int): Boolean {

        val opEntityBase = this.repository.findById(productId)

        opEntityBase.ifPresent { p ->

            logger.info("Removing the product with the SKU: ${p.productId}")
            this.repository.delete(p)
            logger.info("Product ${p.productId} removed!")
        }

        if (!opEntityBase.isPresent) {
            logger.error("Product $productId not found!")
            throw ProductNotFoundException("Product not found to delete!")
        }
        return true
    }

    @GraphQLMutation(name = "removeProductBySku")
    fun removeProductBySku(sku: Int): Boolean {

        val opEntityBase = this.repository.findBySku(sku)

        opEntityBase.ifPresent { p ->

            logger.info("Removing the product with the SKU: ${p.sku}")
            this.repository.delete(p)
            logger.info("Product ${p.sku} removed!")
        }

        if (!opEntityBase.isPresent) {
            logger.error("Product $sku not found!")
            throw ProductNotFoundException("Product not found to delete!")
        }
        return true
    }

    private fun prepareProductToUpdate(opEntityBase: Optional<Product>, product: Product) {

        opEntityBase.ifPresent { p -> p.name = product.name }

        // If collection is empty return.
        if (product.inventory == null || product.inventory?.warehouses == null || product.inventory?.warehouses?.isEmpty()!!) {
            return
        }

        opEntityBase.ifPresent { p ->
            p.name = product.name

            // If entity base collection is empty input new collection
            if (p.inventory == null || p.inventory?.warehouses == null || p.inventory?.warehouses?.isEmpty()!!) {

                if (p.inventory == null) {
                    p.inventory = Inventory()
                }
                p.inventory?.warehouses = product.inventory?.warehouses
            } else {

                // Update if WH exists or insert.
                product.inventory?.warehouses?.forEach { pWh ->

                    val wh = p.inventory?.warehouses?.stream()!!.filter { (_, locality, _, type) ->

                        (locality != null && locality.equals(pWh.locality!!, ignoreCase = true)
                                && type != null && type == pWh.type)
                    }.findFirst()

                    wh.ifPresent { w -> w.quantity = pWh.quantity }

                    if (!wh.isPresent) {
                        p.inventory?.warehouses?.add(pWh)
                    }
                }
            }
        }
    }
}