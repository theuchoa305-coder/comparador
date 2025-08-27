# Comparador

Sistema para comparação de produtos, desenvolvido em Java com arquitetura moderna, boas práticas de tratamento de erros, documentação, testes e Docker. Este projeto foi pensado para ser facilmente extensível, seguro e de fácil manutenção.

## Visão Geral

O **Comparador** é uma API REST que permite cadastrar, consultar e comparar produtos através de endpoints intuitivos. O projeto prioriza qualidade de código, padronização, testes automatizados e documentação clara.

## Principais Funcionalidades

- Cadastro e consulta de produtos
- Comparação entre produtos
- Tratamento robusto de erros
- Documentação automática via Swagger/OpenAPI
- Testes automatizados (Junit/Mockito)
- Deploy facilitado com Docker

## Endpoints Principais

- `POST /produtos`: Cadastra um novo produto
- `GET /produtos`: Lista todos os produtos
- `GET /produtos/{id}`: Consulta um produto pelo ID
- `POST /comparar`: Compara dois produtos

> Para detalhes completos dos endpoints, acesse `/swagger-ui.html` após iniciar o projeto.

## Estrutura do Banco de Dados

- **Arquivo:** `produtos.json`
- **Formato:** JSON
- **Exemplo de Produto:**
  ```json
  {
    "nome": "Notebook Dell",
    "imagemUrl": "https://exemplo.com/notebook.jpg",
    "descricao": "Notebook de alto desempenho",
    "preco": 4500.00,
    "classificacao": 4.7,
    "especificacoes": {
      "Processador": "Intel i7",
      "RAM": "16GB",
      "Armazenamento": "512GB SSD"
    }
  }
  ```
- **Localização:** O arquivo deve estar dentro da pasta `src/main/resources` do projeto.

## Estratégia Técnica

### Pilha de Tecnologia Backend

- **Java 17+**: Linguagem robusta, orientada a objetos e amplamente utilizada no mercado corporativo.
- **Spring Boot**: Framework para construção de APIs REST, favorecendo produtividade, modularidade e facilidade de integração.
- **Maven**: Gerenciador de dependências e ciclo de vida do projeto.
- **Swagger/OpenAPI**: Documentação automática dos endpoints, facilitando testes e integração.
- **Junit/Mockito**: Frameworks para testes automatizados, garantindo confiabilidade e qualidade do código.
- **Docker**: Containerização para facilitar a execução, replicação e deploy do sistema em qualquer ambiente.
- **Arquivo JSON**: Persistência simples e rápida dos dados, ideal para prototipação e ambientes de demonstração.

### Integração de GenAI e Ferramentas Modernas

- **GenAI (Inteligência Artificial Generativa)**: Utilizada para acelerar o desenvolvimento, sugerir melhorias de código, gerar documentação e automatizar tarefas repetitivas. Ferramentas como GitHub Copilot e assistentes de código foram usadas para:
    - Geração de exemplos de endpoints e casos de uso.
    - Sugestão de padrões de tratamento de erros e estruturação de serviços.
    - Criação inicial de testes e documentação.
    - Geração de dados para preenchimento do produtos.json
- **IDEs Modernas**: Visual Studio Code, integradas com plugins de GenAI ( Github Copilot ), potencializam a produtividade e qualidade.
- **Automação de testes e build**: Scripts e plugins de integração contínua garantem que mudanças sejam validadas rapidamente.
- **Swagger UI**: Interface visual para consumir e testar a API diretamente via navegador.

A combinação dessas tecnologias e ferramentas garante um desenvolvimento ágil, padronizado e com alta qualidade, permitindo foco nas regras de negócio e entrega de valor.

## Estratégia de Arquitetura

- **Spring Boot**: Framework principal para APIs REST
- **Camadas separadas**: Controller, Service, Repository, Exception
- **Tratamento global de erros**: Com `@ControllerAdvice` e resposta padronizada
- **Configuração via profiles**: Facilita deploy em diferentes ambientes
- **Documentação automática**: Swagger integrado
- **Testes unitários**: Cobertura dos principais fluxos
- **Docker**: Containerização para fácil replicação

## Instruções de Configuração e Execução

1. **Pré-requisitos**
    - Java 17+
    - Docker (opcional)

2. **Configuração**
    - Edite o arquivo `src/main/resources/application-dev.properties` conforme seu ambiente (Banco de dados, portas, etc).

3. **Execução**
    - Pelo Maven:  
      `./mvnw spring-boot:run`
    - Com Docker:  
      `docker build -t comparador .`  
      `docker run -p 8080:8080 comparador`

4. **Acesso à documentação**
    - Acesse: `http://localhost:8080/swagger-ui.html`

## Testes

- Executar testes unitários:
  ```
  ./mvnw test
  ```

## Boas Práticas Demonstradas

- **Tratamento de erros**: Centralizado (`GlobalExceptionHandler`)
- **Padronização de respostas de erro**: Objeto `ApiError`
- **Documentação**: Swagger/OpenAPI
- **Testes automatizados**
- **Uso de Docker** para facilitar ambiente e deploy

## Decisões Arquiteturais Importantes

- Uso de Spring Boot pela produtividade e comunidade
- Separação clara de responsabilidades
- Configuração externalizada e por profiles
- Docker para garantir portabilidade

## Diferenciais

- Código limpo e extensível
- Facilidade de deploy e testes
- Documentação automática e detalhada
- Pronto para produção e fácil integração com CI/CD

## Ferramentas Utilizadas

- Spring Boot
- Java 17+
- Maven
- Docker
- Swagger/OpenAPI
- Junit/Mockito
- GenAI/IDEs para geração e revisão de código

---

Qualquer dúvida ou sugestão, abra um [issue](https://github.com/theuchoa305-coder/comparador/issues).
