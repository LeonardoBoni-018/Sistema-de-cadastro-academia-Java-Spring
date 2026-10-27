# Sistema de Cadastro de Academia

API REST em **Java + Spring Boot** para gerenciar alunos de uma academia, com cadastro de avaliações físicas, exercícios e treinos.

## Tecnologias

- Java 17
- Spring Boot 4 (Web MVC, Data JPA, Security)
- MySQL
- JWT (jjwt) para autenticação
- springdoc-openapi (documentação Swagger)
- Lombok e Maven

## Funcionalidades

| Recurso | Rota base | Operações |
|---|---|---|
| Alunos | `/v1/alunos` | cadastrar, atualizar, remover e consultar a avaliação física do aluno |
| Avaliações físicas | `/v1/avaliacoes` | cadastrar, listar e listar paginado (`/page/{page}/size/{size}`) |
| Exercícios | `/v1/exercicios` | cadastrar, listar e filtrar por grupo muscular (`/grupos/{grupoMuscular}`) |
| Treinos | `/v1/treinos` | cadastrar treinos de um aluno com seus exercícios |

O tratamento de erros é centralizado (`GlobalExceptionHandler`), com respostas padronizadas para *bad request* e *not found*.

## Como executar

Pré-requisitos: JDK 17+, Maven e um MySQL em execução.

1. Configure as variáveis de ambiente:

   | Variável | Descrição |
   |---|---|
   | `DATABASE_USERNAME` | usuário do MySQL (padrão `root`) |
   | `DATABASE_PASSWORD` | senha do MySQL |
   | `JWT_KEY` | chave secreta usada para assinar os tokens JWT |
   | `JWT_EXPIRATION` | validade do token em milissegundos (padrão `900000`) |

2. O banco `gym_security` é criado automaticamente. Por padrão a aplicação espera o MySQL em `localhost:3305`; ajuste `src/main/resources/application.yaml` se o seu estiver em outra porta.

3. Suba a aplicação:

   ```bash
   mvn spring-boot:run
   ```

4. A documentação interativa fica disponível no Swagger UI (`/swagger-ui.html`).

## Estrutura

```
src/main/java/br/com/boni/spring_boot_essentials
├── controller   # endpoints REST
├── service      # regras de negócio
├── database     # entidades JPA e repositórios
├── dto          # objetos de transferência
├── exception    # exceções e resposta de erro
└── handler      # tratamento global de exceções
```

## Autor

Leonardo Rodrigues Boni — [GitHub](https://github.com/LeonardoBoni-018) · [LinkedIn](https://www.linkedin.com/in/leonardo-rodrigues-boni-3751432b7/)
