# Prompts Utilizados com Ferramentas de IA

Este arquivo registra os principais prompts utilizados para acelerar o desenvolvimento, documentação, avaliação e revisão do projeto via ferramentas GenAI como ChatGPT, Copilot Chat e GitHub Copilot. Os exemplos abaixo representam versões otimizadas, prontos para serem reutilizados em projetos similares e valorizados por especialistas.

---

## 1. Estratégia Técnica & Arquitetura

- Gere um README estratégico para meu projeto Java Spring Boot, focando em arquitetura, principais endpoints, instruções detalhadas de configuração e execução, diferenciais técnicos e exemplos de payloads. Considere que o banco de dados é um arquivo JSON em `src/main/resources`.
- Atualize o README para incluir uma seção sobre decisões arquiteturais e justificativas para a stack escolhida no backend. Mostre como cada tecnologia contribui para escalabilidade, segurança e produtividade.
- Atualize o README para incluir uma seção de Estratégia Técnica, detalhando a stack utilizada no backend (Java, Spring Boot, Maven, Docker, etc) e explique como ferramentas de IA (Copilot, ChatGPT) aceleraram o desenvolvimento, automatizaram tarefas e melhoraram documentação e testes.
- Adicione ao README uma explicação sobre o uso do arquivo `produtos.json` como banco de dados. Mostre um exemplo de estrutura de produto em JSON e detalhe o caminho correto do arquivo dentro do projeto.

---

## 2. Instruções de Execução e Avaliação

- Gere um arquivo `run.md` com instruções passo a passo para executar e avaliar o projeto localmente. Inclua pré-requisitos, comandos para rodar via Maven e Docker, como acessar o Swagger, executar testes automatizados, exemplos de chamadas via curl ou Postman e pontos de avaliação recomendados.
- Inclua no `run.md` uma seção sobre as ferramentas de IA utilizadas no desenvolvimento (Copilot, ChatGPT, etc) e gere um arquivo `prompts.md` com os principais prompts usados, explicando para que cada um foi útil e como poderiam ser adaptados para acelerar projetos similares.

---

## 3. Boas Práticas, Tratamento de Erros e Qualidade

- Instrua como implementar tratamento global de erros com `@ControllerAdvice` em Spring Boot, garantindo respostas padronizadas e seguras para o consumidor da API.
- Sugira casos de teste unitário e de integração para todos os serviços críticos, incluindo cenários de erro e persistência em arquivo JSON. Oriente como garantir independência dos dados persistidos nos testes.
- Gere scripts ou instruções automatizadas para rodar testes, build e validação do projeto. Inclua recomendações de ferramentas de CI/CD.

---

## 4. Refino de Endpoints e Arquitetura

- Crie uma lista de endpoints RESTful para uma API de produtos em Spring Boot, detalhando métodos, payloads e respostas esperadas. Instrua como implementar o tratamento global de erros com `@ControllerAdvice` e sugira a melhor estrutura de camadas para um backend que utiliza arquivos JSON como persistência.

---

## 5. Testes Automatizados

- Gere exemplos de casos de teste unitário e de integração para o serviço de comparação de produtos, considerando diferentes cenários (produtos inexistentes, dados inválidos, etc). Oriente como mockar ou isolar o acesso ao arquivo JSON em testes no Spring Boot, garantindo independência dos dados persistidos.

---

## 6. Documentação, Usabilidade e Produtividade

- Crie instruções detalhadas para execução e avaliação do projeto, incluindo pré-requisitos, comandos, exemplos de chamadas, pontos de validação e como acessar a documentação interativa (Swagger).
- Gere um arquivo `prompts.md` com exemplos de prompts usados para gerar código, documentação e automações, explicando como cada um contribuiu para o sucesso do projeto.

---

## 7. Visão de Evolução & Manutenção

- Sugira melhorias incrementais e roadmap para evolução do projeto, considerando escalabilidade, integração com bancos relacionais, testes avançados e refinamento da arquitetura.

