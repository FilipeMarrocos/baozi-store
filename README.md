# Baozi Store – API REST

Trabalho de Desenvolvimento Web Back-End (UNINTER) – Filipe Marrocos – RU: 5270191

API REST simples para controle de clientes, produtos e pedidos de uma loja de pão chinês.
Tecnologias: Java 17, Spring Boot 3, Spring Data JPA, banco H2 e Postman.

## Endpoints

| Recurso   | POST | GET (todos) | GET /{id} | PUT /{id}    | DELETE /{id} |
|-----------|------|-------------|-----------|--------------|--------------|
| /clientes | ✔    | ✔           | ✔         | ✔ (opcional) | ✔            |
| /produtos | ✔    | ✔           | ✔         | ✔ (opcional) | ✔            |
| /pedidos  | ✔    | ✔           | ✔         | –            | ✔            |

## Exemplos de corpo (JSON)

```json
// POST /clientes
{ "nome": "FilipeMarrocos5270191", "clienteDesde": "2026-10-12" }

// POST /produtos
{ "nome": "Baozi de Carne", "preco": 8.50, "estoque": true }

// POST /pedidos
{ "clienteId": 1, "produtoId": 1, "quantidade": 5 }
```

## Estrutura

```
br.com.baozi
├── model        (Cliente, Produto, Pedido, PedidoRequest)
├── repository   (ClienteRepository, ProdutoRepository, PedidoRepository)
└── controller   (ClienteController, ProdutoController, PedidoController)
```
