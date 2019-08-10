package br.com.barroso.productapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductApiKotlinApplication

fun main(args: Array<String>) {
	runApplication<ProductApiKotlinApplication>(*args)
}
