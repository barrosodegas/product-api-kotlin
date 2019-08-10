package br.com.barroso.productapi.model

import lombok.ToString
import java.io.Serializable
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

@ToString
@Entity
data class Inventory(

    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var inventoryId: Int? = null,

    @Valid
    @NotEmpty(message = "Field: Inventory --> wareHouses is mandatory!")
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "inventory_id")
    var warehouses: MutableList<Warehouse>? = null): Serializable {

    /**
     * UID.
     */
    companion object {
        private const val serialVersionUID = 1L
    }
}