package br.com.barroso.productapi.model

import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * Class that represents the WareHouse entity.
 *
 * @author Andre Barroso
 *
 */
@Entity
data class Warehouse(

    @Id
    @Column(name = "warehouse_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var wareHouseId: Int? = null,

    @NotEmpty(message = "Field: Warehouse --> locality is mandatory!")
    @Column(nullable = false)
    var locality: String? = null,

    @NotNull(message = "Field: Warehouse --> quantity is mandatory!")
    @Column(nullable = false)
    var quantity: Int? = null,

    @NotNull(message = "Field: Warehouse --> type is mandatory!")
    @Column(nullable = false)
    var type: WarehouseTypeEnum? = null): Serializable {

    /**
     * UID.
     */
    companion object {
        private const val serialVersionUID = 1L
    }
}