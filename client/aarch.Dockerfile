FROM ubuntu:22.04

RUN apt-get update
RUN apt-get install curl unzip -y

# Install protoc
RUN curl -LO https://github.com/protocolbuffers/protobuf/releases/download/v31.0/protoc-31.0-linux-aarch_64.zip
RUN unzip protoc-31.0-linux-aarch_64.zip

RUN mv ./bin/protoc /usr/local/bin/.
RUN chmod +x /usr/local/bin/protoc

# Install protobuf-javascript
RUN curl -LO https://github.com/protocolbuffers/protobuf-javascript/releases/download/v3.21.4/protobuf-javascript-3.21.4-linux-aarch_64.zip
RUN unzip protobuf-javascript-3.21.4-linux-aarch_64.zip

RUN mv ./bin/protoc-gen-js /usr/local/bin/.
RUN chmod +x /usr/local/bin/protoc-gen-js

# Install protoc-gen-grpc-web
RUN curl -LO https://github.com/grpc/grpc-web/releases/download/1.5.0/protoc-gen-grpc-web-1.5.0-linux-aarch64

RUN mv protoc-gen-grpc-web-1.5.0-linux-aarch64 /usr/local/bin/protoc-gen-grpc-web
RUN chmod +x /usr/local/bin/protoc-gen-grpc-web

# Prepare input/output directories
RUN mkdir proto
RUN mkdir gen

# Run the generation command
CMD ["protoc", "-I=./proto", "hello.proto", "--js_out=import_style=commonjs:./gen", "--grpc-web_out=import_style=typescript,mode=grpcwebtext:./gen"]