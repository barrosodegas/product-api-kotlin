package br.com.barroso.productapi.model

import lombok.ToString
import java.io.Serializable
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 *
 * Class that represents the product entity.
 *
 * @author Andre Barroso
 *
 */
@ToString
@Entity
data class Product(

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var productId: Int? = null,

    @NotNull(message = "Field: Product --> sku is mandatory!")
    @Min(value = 1L, message = "Field: Product --> sku must be a positive number and greater than zero!")
    @Column(nullable = false, unique = true)
    var sku: Int? = null,

    @NotEmpty(message = "Field: Product --> name is mandatory!")
    @Column(nullable = false)
    var name: String? = null,

    @Valid
    @NotNull(message = "Field: Product --> inventory is mandatory!")
    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], optional = false)
    @JoinColumns(JoinColumn(name = "inventory_id", referencedColumnName = "inventory_id"))
    var inventory: Inventory? = null): Serializable {

    /**
     * UID.
     */
    companion object {
        private const val serialVersionUID = 1L
    }
}