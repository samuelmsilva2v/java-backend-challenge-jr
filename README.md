# Backend Challenge Jr. - Produtos
Este projeto é uma API RESTful desenvolvida em Java com Spring Boot, criada como parte de um desafio técnico para a posição de Desenvolvedor Backend Júnior.
<br> <br>
O desafio consiste em criar uma API RESTFul para gerenciamento de produtos.
Os produtos devem ter os atributos ID, Nome, Tipo e Preço Unitário. Os possíveis tipos poderão ser "Material" ou "Serviço".
<br>
A API deve ser capaz de executar as seguintes operações:
- Consulta por ID
- Consulta de todos os produtos
- Inserção
- Alteração
- Deleção
- Dashboard
  - Trazer a quantidade e o preço unitário médio segregado por Tipo

### Tecnologias Utilizadas
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- ModelMapper
- MockMvc
- Java Faker (para geração de dados fictícios em testes)
- JUnit

## Endpoints

### 1. Gerenciamento de produtos
| Método | Endpoint             | Descrição                   |
|--------|-----------------------|-----------------------------|
| POST   | `/api/produtos`      | Inserir um novo produto      |
| GET    | `/api/produtos`      | Consultar todas os produtos   |
| GET    | `/api/produtos/{id}` | Consultar um produto através da ID  |
| PUT    | `/api/produtos/{id}` | Alterar um produto     |
| DELETE | `/api/produtos/{id}` | Excluir um produto      |

```json
{
  "nome": "string",
  "tipo": "MATERIAL",
  "precoUnitario": 0.01
}
```

### 2. Dashboard
| Método | Endpoint             | Descrição                   |
|--------|-----------------------|-----------------------------|
| GET   | `/api/produtos/dashboard`      | Exibir quantidade e preço médio por tipo      |
```json
[
  {
    "tipo": "MATERIAL",
    "quantidade": 0,
    "precoMedio": 0.01
  },
  {
    "tipo": "SERVICO",
    "quantidade": 0,
    "precoMedio": 0.01
  }
]
```

## Testes
#### Executando os testes
```bash
mvn test
```

## Configuração e Execução

#### Requisitos
- Java 21 ou superior
- Spring Boot 3.3.5
- Maven 3.8+
- PostgreSQL 16

### Configuração do banco de dados:

#### Dados iniciais:

```sql
INSERT INTO tb_produto (id, nome, tipo, preco_unitario) VALUES
('0f54ce17-6de1-4289-8f3e-50a7cc497fa5', 'Cadeira de Escritório', 'MATERIAL', 200.00),
('9cc852d5-cb73-46d6-8751-0950bf902cd1', 'Mesa de Reunião', 'MATERIAL', 350.00),
('0d3d7c4a-b716-4c84-937a-c2f3797d4456', 'Reparo de Impressora', 'SERVICO', 150.00),
('c52b46cd-8295-47c8-8698-7cae9a12ef7e', 'Limpeza de Equipamentos', 'SERVICO', 80.00),
('52e5eb24-e5cb-4338-9624-09b114df347e', 'Manutenção de Computador', 'SERVICO', 250.00),
('5e297bd7-c9b8-498f-9a34-3145ec6637c1', 'Armário de Aço', 'MATERIAL', 450.00);
```

### 1.Clone o repositório:

```bash
https://github.com/samuelmsilva2v/java-backend-challenge-jr.git
cd java-backend-challenge-jr
```

### 2. Atualize o arquivo `application.properties` com as credenciais do seu banco de dados PostgreSQL:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/bd_produtos
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 3. Instale as dependências:

```bash
mvn clean install
```

### 4. Execute o projeto:
```bash
mvn spring-boot:run
```

### 5. Acesse a aplicação:
  - Documentação da API: http://localhost:8080/swagger-ui/index.html

## Estrutura do projeto
```plaintext
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── config/                                  # Classes de configuração
│   │   ├── controller/                              # Controladores REST
│   │   ├── dto/                                     # DTOs para entrada/saída de dados
│   │   ├── entities/                                # Entidades JPA
│   │   ├── enums/                                   # Enumerações (ex.: TipoProduto)
│   │   ├── exceptions/                              # Exceções customizadas
│   │   ├── handlers/                                # Tratamento global de exceções
│   │   ├── repository/                              # Repositórios (Spring Data JPA)
│   │   ├── services/                                # Camada de serviços
│   │   │   ├── impl/                                # Classe de implementação dos métodos
│   │   │   ├── interfaces/                          # Interface de serviços
│   │   ├── JavaBackendChallengeJrApplication.java   # Classe principal
│   └── resources/
│       ├── application.properties                       # Configurações do Spring Boot
├── test/
│   └── java/com/example/demo/                           # Testes unitários e de integração
│   │   ├── JavaBackendChallengeJrApplicationTests.java  # Classe de testes
```

## Autor
- Samuel Maciel da Silva
  - [LinkedIn](https://www.linkedin.com/in/samuelmsilva2v/)
  - [E-mail](mailto:samuelmsilva@outlook.com.br)
