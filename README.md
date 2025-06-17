# 🏺 TERRACOTA

**TERRACOTA** é uma plataforma de e-commerce especializada na venda e compra de produtos artesanais. O sistema oferece uma solução completa para artesãos gerenciarem seus produtos e vendas de forma eficiente e segura.

*Documentação da API*
- [https://spring-terracota-new.onrender.com/api/swagger-ui/index.html](https://spring-terracota-new.onrender.com/api/swagger-ui/index.html)
---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **SpringDoc OpenAPI**
- **Flyway** (versionamento de banco de dados)
- **Lombok**
- **PostgreSQL**
- **Azure Spring**
- **Google Guava**
- **Mercado Pago SDK**
- **Auth0 JWT** (autenticação e autorização)

---

## 🧱 Arquitetura

O projeto adota a **Arquitetura Hexagonal (Ports and Adapters)**, com a seguinte estrutura principal:

```bash
terracota
├── application      # Casos de uso
├── domain           # Entidades, interfaces e lógica de negócio
└── infrastructure   # Controllers, repositórios, configurações e integrações externas
```

---

## 🎯 Objetivo do Projeto

Criar um sistema robusto e escalável que permita:

- Gerenciar produtos artesanais
- Realizar compras e vendas
- Integrar com meios de pagamento (Mercado Pago)
- Autenticação segura com JWT (Auth0)
- Disponibilizar documentação automatizada da API

---

## 🧠 Integrações com IA

O projeto conta com funcionalidades baseadas em inteligência artificial:

- **Chatbot inteligente** para auxiliar usuários durante a navegação e no processo de compra.
- **Sistema de recomendação de produtos** personalizado com base no comportamento de navegação e histórico de compras dos usuários.

---

## 🛠️ Como executar localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/luucasdinoo/spring-terracota-new.git
   cd spring-terracota-new
   ```

2. Inicie um banco de dados PostgreSQL localmente ou configure um banco remoto:
   ```bash
   docker compose up -d
   ```

3. Verifique as migrations do Flyway e execute-as logo em seguida:
   ```bash
   mvn flyway:info
   mvn flyway:migrate
   ```

4. Adicione suas variáveis de ambiente, exemplo presente no arquivo `.env.example`.


5. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```
