# grpc-example

## Prerequisites

- Java (version 21)
- Node.js (version 22)
- Docker

## Running

### Backend

```bash
cd server
./gradlew :bootRun
```

### Frontend

```bash
cd client
npm install
npm run protogen
npm run dev
```

### Proxy

```bash
cd proxy
docker compose up
```
