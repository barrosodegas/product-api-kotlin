package br.com.barroso.productapi.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.io.Serializable
import java.lang.RuntimeException

/**
 * Class responsible for throwing exceptions stating that the product already exists.
 * @author Andre Barroso
 *
 */
@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
class ProductAlreadyException(message: String? = null): RuntimeException(message), Serializable {

    /**
     * UID.
     */
    companion object {
        private const val serialVersionUID = 1L
    }
}