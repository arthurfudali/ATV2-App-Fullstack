# at2_base

Projeto KMP/Ktor com armazenamento de produtos em memória no módulo `server`.

## Rodando o server

```powershell
Set-Location "C:\Users\Arthur\Documents\FATEC\LDDM\ATV2-App-Fullstack"
.\gradlew.bat :server:run
```

## API de produtos

- `GET /products`
- `GET /products/{id}`
- `POST /products`

Os dados iniciais ficam em `InMemoryProductRepository` e são perdidos ao reiniciar o server.
