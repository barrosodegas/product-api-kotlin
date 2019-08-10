package br.com.barroso.productapi.controller

import br.com.barroso.productapi.model.Product
import br.com.barroso.productapi.service.ProductService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping( "/products")
class ProductController(val service: ProductService) {

    @GetMapping("/{productId}/id")
    fun findProductById(@PathVariable(name = "productId", required = true) productId: Int): Product {
        return this.service.findProductById(productId)
    }

    @GetMapping("/{sku}/sku")
    fun findProductBySku(@PathVariable(name = "sku", required = true) sku: Int): Product {
        return this.service.findProductBySku(sku)
    }

    @GetMapping()
    fun findAllProducts(): List<Product> {
        return this.service.findAll()
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createProduct(@Valid @RequestBody(required = true) product: Product): Product {
        return this.service.createProduct(product)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateProduct(@Valid @RequestBody(required = true) product: Product): Boolean {
        return this.service.updateProduct(product)
    }

    @DeleteMapping("/{productId}/id")
    fun removeProductById(@PathVariable(name = "productId", required = true) productId: Int): Boolean {
        return this.service.removeProductById(productId)
    }

    @DeleteMapping("/{sku}/sku")
    fun removeProductBySku(@PathVariable(name = "sku", required = true) sku: Int): Boolean {
        return this.service.removeProductBySku(sku)
    }
}