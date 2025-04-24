# Documentação TERRACOTA

## Introdução
Esta documentação descreve os endpoints disponíveis na aplicação Terracota.

## Endpoints

### Customer

#### 1. **POST /api/customers**
- **Descrição:** Cria um novo cliente.
- **Request:**
  ```json
    {
      "email": "",
      "password": "",
      "user_role": "CUSTOMER",
      "name": "",
      "phone": "",
      "cpf": "",
      "is_active": true,
      "address": {
        "address_street": "",
        "address_number": "",
        "address_neighborhood": "",
        "address_city": "",
        "address_state": "",
        "address_zip_code": ""
    }
  }
  ```
#### 2. **GET /api/customers**
- **Descrição:** Retorna a lista de clientes.
- **Parâmetros de Consulta:**-
- `search` (opcional)
- `page` (opcional)
- `perPage` (opcional)
- `sort` (opcional)
- `dir` (opcional)

#### 3. **GET /api/customers/{id}**
- **Descrição:** Retorna um cliente.

#### 4. **DELETE /api/customers/{id}**
- **Descrição:** Deleta um cliente.

#### 5. **PUT /api/customers/{id}**
- **Descrição:** Atualiza um cliente.
```json
    {
      "name": "",
      "phone": "",
      "is_active": true
    }
```

### Craftsman

#### 1. **POST /api/craftsmen**
- **Descrição:** Cria um novo cliente.
- **Request:**
  ```json
    {
      "email": "",
      "password": "",
      "user_role": "CUSTOMER",
      "name": "",
      "phone": "",
      "cpf": "",
      "is_active": true,
      "address": {
        "address_street": "",
        "address_number": "",
        "address_neighborhood": "",
        "address_city": "",
        "address_state": "",
        "address_zip_code": ""
    }
  }
  ```
#### 2. **GET /api/craftsmen/**
- **Descrição:** Retorna a lista de artesãos.
- `search` (opcional)
- `page` (opcional)
- `perPage` (opcional)
- `sort` (opcional)
- `dir` (opcional)

#### 3. **GET /api/craftsmen/{id}**
- **Descrição:** Retorna um artesão.

#### 4. **DELETE /api/craftsmen/{id}**
- **Descrição:** Deleta um artesão.

#### 5. **PUT /api/craftsmen/{id}**
- **Descrição:** Atualiza um artesão.
```json
    {
      "name": "",
      "phone": "",
      "is_active": true
    }
```

### Product

#### 1. **POST /api/products**
- **Descrição:** Cria um novo produto.
  - **Request:**
  ```json
      {
        "name": "",
        "description": "",
        "price": 0.0,
        "type": "artesanato_em_papel",
        "craftsman_id": ""
       }
  ```
#### 2. **DELETE /api/products/{id}**
- **Descrição:** Deleta um produto.

#### 3. **GET /api/products/{product_id}/craftsmen/{craftsman_id}**
- **Descrição:** Retorna um produto de um artesão.

#### 4. **GET /api/craftsmen/{id}**
- **Descrição:** Retorna a lsita de produtos de um artesão.
- `search` (opcional)
- `page` (opcional)
- `perPage` (opcional)
- `sort` (opcional)
- `dir` (opcional)

#### 5. **PUT /api/products/{product_id}/craftsmen/craftsmen_id**
- **Descrição:** Atualiza um produto.
```json
{
  "name": "",
  "description": "",
  "price": 0.0
}
```