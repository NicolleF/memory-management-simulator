# Memory Management Simulator

Simulador de alocação de memória desenvolvido para a disciplina
de Sistemas Operacionais da FURB, 2026/2.

## Versões

### V1.0 - Alocação e Desalocação de Memória
- First Fit
- Best Fit
- Worst Fit
- Next Fit

## Tecnologias

- Java 21
- Spring Boot 4.1.1
- Maven
- Angular 21 (frontend)

## Estrutura do monorepo

- `src/`: backend Spring Boot e núcleo do simulador.
- `frontend/`: protótipo visual Angular estático, com dados ilustrativos e opções de tema e identidade visual.
- `docs/`: [contrato da API REST](docs/api-rest.md), especificações e [plano de desenvolvimento](docs/roadmap-monorepo-angular.md).

## Executar

Com Java 21 e Maven instalados, inicie o backend na raiz do repositório:

```bash
mvn spring-boot:run
```

Para executar os testes:

```bash
mvn test
```

Para iniciar o frontend, abra outro terminal na pasta `frontend/`:

```bash
npm install
npm start
```

O frontend é uma prévia visual estática: os dados são ilustrativos, os controles de simulação estão desativados e ainda não há conexão com a API.
