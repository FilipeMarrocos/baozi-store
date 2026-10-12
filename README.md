# Baozi Store – API REST

Trabalho de Desenvolvimento Web Back-End (UNINTER).
Java 17 · Spring Boot 3 · Spring Data JPA · H2 (ou MySQL) · Postman

## Como rodar
1. Instale o JDK 17+ e abra a pasta no IntelliJ / Eclipse / VS Code (ele baixa o Maven sozinho).
2. Rode a classe `BaoziStoreApplication` (ou `mvn spring-boot:run`).
3. A API sobe em `http://localhost:8080`.
4. Console do banco: `http://localhost:8080/h2-console`
   (JDBC URL: `jdbc:h2:file:./data/baozidb`, user `sa`, senha em branco).

## Endpoints
| Recurso  | POST | GET (todos) | GET /{id} | PUT /{id} | DELETE /{id} |
|----------|------|-------------|-----------|-----------|--------------|
| /clientes | ✔ | ✔ | ✔ | ✔ (opcional) | ✔ |
| /produtos | ✔ | ✔ | ✔ | ✔ (opcional) | ✔ |
| /pedidos  | ✔ | ✔ | ✔ | – | ✔ |

### Exemplos de corpo (JSON)
```json
// POST /clientes
{ "nome": "Filipe1234567", "clienteDesde": "2026-10-12" }

// POST /produtos
{ "nome": "Baozi de Carne", "preco": 8.50, "estoque": true }

// POST /pedidos
{ "clienteId": 1, "produtoId": 1, "quantidade": 5 }
```

## Postman
Importe `Baozi_Store.postman_collection.json`. As requisições já estão na ordem certa
para os prints. O nome do cliente vem da variável `nome_cliente` da coleção
(troque `Filipe1234567` pelo seu nome + RU reais).

## Estrutura
```
br.com.baozi
├── model        (Cliente, Produto, Pedido, PedidoRequest)
├── repository   (ClienteRepository, ProdutoRepository, PedidoRepository)
└── controller   (ClienteController, ProdutoController, PedidoController)
```
