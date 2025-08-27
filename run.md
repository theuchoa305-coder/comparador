# Instruções para Execução e Avaliação do Projeto Comparador

Este documento orienta sobre como executar e avaliar o projeto **Comparador** localmente, incluindo dependências, preparação do ambiente e verificação das funcionalidades principais.

---

## 1. Pré-requisitos

- **Java 17** ou superior instalado
- **Maven** instalado (ou utilize os wrappers `mvnw`/`mvnw.cmd` do projeto)
- **Docker** instalado (opcional, para execução via container)
- **Git** instalado

---

## 2. Clonando o Repositório

```bash
git clone https://github.com/theuchoa305-coder/comparador.git
cd comparador
```

---

## 3. Preparando o Banco de Dados

- O projeto utiliza um arquivo `produtos.json` como banco de dados, localizado em `src/main/resources/produtos.json`.
- Certifique-se de que o arquivo está presente e contém dados válidos. Um exemplo pode ser encontrado no README.

---

## 4. Executando Localmente

### Com Maven

```bash
./mvnw spring-boot:run
# Ou, se estiver usando Windows:
mvnw.cmd spring-boot:run
```

A aplicação ficará disponível por padrão em [http://localhost:8080](http://localhost:8080).

### Com Docker

```bash
docker build -t comparador .
docker run -p 8080:8080 comparador
```

---

## 5. Testando a API

### Documentação Interativa

- Acesse [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) para visualizar e testar todos os endpoints da API usando o Swagger UI.

### Endpoints Principais

- `GET /produtos` — Lista todos os produtos
- `GET /produtos/{nome}` — Consulta produto pelo Nome do produto
- `GET /status` — healthcheck da aplicação


---

## 6. Executando Testes Automatizados

```bash
./mvnw test
# Ou, se estiver usando Windows:
mvnw.cmd test
```

Os testes unitários e de integração serão executados, apresentando o resultado no terminal.

---

## 7. Avaliação

Para avaliar o projeto, siga os passos abaixo:

1. **Execução dos Endpoints:**  
   Utilize o Swagger UI ou ferramentas como Postman para testar todos os endpoints principais.

2. **Verificação do Tratamento de Erros:**  
   Tente enviar dados inválidos ou acessar produtos inexistentes para validar o tratamento de erros.

3. **Análise da Documentação:**  
   Confirme se a documentação dos endpoints está clara e condizente com o comportamento da API.

4. **Testes Automatizados:**  
   Execute os testes e confira se todos passaram. Analise os casos de teste implementados.
