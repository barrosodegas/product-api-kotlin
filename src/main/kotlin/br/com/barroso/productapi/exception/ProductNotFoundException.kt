package br.com.barroso.productapi.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.io.Serializable
import java.lang.RuntimeException

/**
 * Class responsible for throwing exceptions stating that the product not found.
 *
 * @author Andre Barroso
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
class ProductNotFoundException(message: String? = null): RuntimeException(message), Serializable {

    /**
     * UID.
     */
    companion object {
        private const val serialVersionUID = 1L
    }
}