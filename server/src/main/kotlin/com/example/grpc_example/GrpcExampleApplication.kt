package com.example.grpc_example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrpcExampleApplication

fun main(args: Array<String>) {
	runApplication<GrpcExampleApplication>(*args)
}
