# Forum API

![Java](https://img.shields.io/badge/Java-25-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4-green)
![JWT](https://img.shields.io/badge/Auth-JWT-blue)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue)
![Swagger](https://img.shields.io/badge/API-Documentation-green)
![Maven](https://img.shields.io/badge/Build-Maven-orange)

API REST para gerenciamento de tópicos de fórum desenvolvida com **Spring Boot**.

O projeto implementa autenticação segura com **JWT**, controle de acesso com **Spring Security**, persistência com **Spring Data JPA**, migração de banco com **Flyway** e documentação automática com **Swagger/OpenAPI**.

Este projeto foi desenvolvido para demonstrar boas práticas de construção de APIs REST modernas.

---

# Autor

**Giscelmo Costa**

GitHub  
https://github.com/Giscelmo

---

# Tecnologias Utilizadas

- Java 25
- Spring Boot
- Spring Security
- JWT (Auth0 Java JWT)
- Spring Data JPA
- PostgreSQL
- Flyway
- Lombok
- Swagger / OpenAPI
- Maven

---

# Arquitetura do Projeto

O projeto segue uma arquitetura baseada em camadas:


Controller → Service → Repository → Database


Estrutura de pacotes:


br.com.giscelmo.forum_api

controller
domain
repository
service
infra
security


Cada camada possui responsabilidade bem definida:

| Camada | Responsabilidade |
|------|------|
| Controller | Expor endpoints REST |
| Service | Regras de negócio |
| Repository | Comunicação com o banco |
| Domain | Entidades e DTOs |
| Infra | Configurações e segurança |

---

# Funcionalidades

### Usuários
- Cadastro de usuários

### Autenticação
- Login com email e senha
- Geração de token JWT

### Tópicos
- Criar tópico
- Listar tópicos
- Detalhar tópico
- Atualizar tópico
- Excluir tópico

### Cursos
- Cadastro de cursos

---

# Segurança

A API utiliza **Spring Security + JWT**.

Fluxo de autenticação:


Login → gera token JWT → token enviado no header Authorization


Header necessário nas requisições protegidas:


Authorization: Bearer TOKEN


---

# Documentação da API

A documentação é gerada automaticamente com **Swagger/OpenAPI**.

Após iniciar a aplicação, acesse:


http://localhost:8080/swagger-ui/index.html


Swagger permite:

- visualizar endpoints
- testar requisições
- autenticar com JWT
- visualizar schemas da API

---

# Como Executar o Projeto

### 1 - Clonar o repositório


git clone https://github.com/Giscelmo/forum-api.git


### 2 - Entrar na pasta do projeto


cd forum-api


### 3 - Configurar o banco PostgreSQL

Arquivo:


src/main/resources/application.properties


Exemplo:


spring.datasource.url=jdbc:postgresql://localhost:5432/forum-api
spring.datasource.username=postgres
spring.datasource.password=postgres


---

### 4 - Executar a aplicação


mvn spring-boot:run


ou executar a classe:


ForumApiApplication.java


---

# Autenticação

Endpoint de login:


POST /login


Body:


{
"email": "usuario@email.com
",
"senha": "123456"
}


Resposta:


{
"token": "jwt_token"
}


---

# Principais Endpoints

### Usuários

Criar usuário


POST /usuarios


---

### Tópicos

Criar tópico


POST /topicos


Listar tópicos


GET /topicos


Detalhar tópico


GET /topicos/{id}


Atualizar tópico


PUT /topicos/{id}


Excluir tópico


DELETE /topicos/{id}


---

### Cursos

Criar curso


POST /cursos


---

# Banco de Dados

O projeto utiliza **Flyway** para versionamento de banco.

Migrations localizadas em:


src/main/resources/db/migration


Exemplo:


V1__create_tabela_usuarios.sql
V2__create_table_perfis.sql
V3__create_table_usuarios_perfis.sql
V4__create_table_cursos.sql
V5__create_table_topicos.sql
V6__create_table_respostas.sql


---

# Estrutura do Banco

Principais tabelas:


usuarios
perfis
usuarios_perfis
cursos
topicos
respostas


---

# Boas Práticas Aplicadas

- Arquitetura em camadas
- DTOs para comunicação da API
- Paginação com Spring Data
- Segurança com JWT
- Documentação com Swagger
- Migrations com Flyway
- Injeção de dependências
- Tratamento de autenticação stateless

---

# Melhorias Futuras

- Refresh Token
- Testes automatizados
- Rate Limiting
- Logs estruturados
- Dockerização da aplicação

---

# Licença

Este projeto é destinado para fins educacionais.