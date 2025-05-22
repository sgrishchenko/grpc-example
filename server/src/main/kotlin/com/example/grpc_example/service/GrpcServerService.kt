package com.example.grpc_example.service

import com.example.grpc_example.proto.HelloReply
import com.example.grpc_example.proto.HelloRequest
import com.example.grpc_example.proto.SimpleGrpc.SimpleImplBase
import io.grpc.stub.StreamObserver
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service

@Service
internal class GrpcServerService : SimpleImplBase() {
    override fun sayHello(req: HelloRequest, responseObserver: StreamObserver<HelloReply?>) {
        log.info("Hello " + req.getName())
        require(!req.getName().startsWith("error")) { "Bad name: " + req.getName() }
        if (req.getName().startsWith("internal")) {
            throw RuntimeException()
        }
        val reply = HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build()
        responseObserver.onNext(reply)
        responseObserver.onCompleted()
    }

    override fun streamHello(req: HelloRequest, responseObserver: StreamObserver<HelloReply?>) {
        log.info("Hello " + req.getName())
        var count = 0
        while (count < 10) {
            val reply = HelloReply.newBuilder().setMessage("Hello(" + count + ") ==> " + req.getName()).build()
            responseObserver.onNext(reply)
            count++
            try {
                Thread.sleep(1000L)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
                responseObserver.onError(e)
                return
            }
        }
        responseObserver.onCompleted()
    }

    companion object {
        private val log: Log = LogFactory.getLog(GrpcServerService::class.java)
    }
}