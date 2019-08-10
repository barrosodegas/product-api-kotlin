package br.com.barroso.productapi.repository

import br.com.barroso.productapi.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository: JpaRepository<Product, Int> {

    /**
     * Method responsible to find product by code.
     * @param productId Product ID.
     * @return Product.
     */
    @Query("SELECT p FROM Product p WHERE p.productId=:productId")
    override fun findById(@Param("productId") productId: Int): Optional<Product>

    /**
     * Method responsible to find product by code.
     * @param sku Product Code.
     * @return Product.
     */
    @Query("SELECT p FROM Product p WHERE p.sku=:sku")
    fun findBySku(@Param("sku") sku: Int?): Optional<Product>
}