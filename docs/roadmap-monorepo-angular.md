# Plano de desenvolvimento do simulador

Este plano organiza a evolução do repositório monolítico com backend Java/Spring e frontend Angular. O frontend atual é um protótipo exclusivamente visual, com dados ilustrativos e opções para comparar a identidade visual. A integração HTTP entra depois que o contrato da API e os casos de uso do backend estiverem definidos.

## Estado atual

- O backend já tem modelos de processo, memória e bloco, estratégias First Fit, Best Fit, Next Fit e Worst Fit e uma factory para selecionar a estratégia.
- A memória permite alocar em um bloco e manter a sobra. `Memory.liberar` ainda lança `UnsupportedOperationException`.
- Ainda não há serviço de simulação que coordene início, alocação, liberação e leitura do estado.
- Ainda não há API REST, DTOs para entrada e saída, validação HTTP, tratamento uniforme de erros, documentação da API ou política de CORS.
- O frontend Angular está iniciado em `frontend/` com um protótipo visual estático. Não há simulação local nem integração com a API.
- O frontend ainda não tem um contrato estável de API para consumir. A fábrica e os algoritmos existem no backend, mas precisam de testes unitários para validar os casos de uso antes da integração.

## Etapas

### 1. Fechar as regras do simulador

Registrar as decisões que afetam domínio, API e interface: tamanho e unidade da memória, tamanho total configurável, se processos podem ter identificadores repetidos, comportamento ao tentar alocar sem espaço suficiente, estratégia escolhida no início ou alterável durante a simulação e o que acontece ao reiniciar.

Para uma primeira versão, recomenda-se escolher uma estratégia ao criar a simulação e mantê-la até reiniciar. Isso preserva o estado interno de estratégias como Next Fit. Falha por falta de espaço deve ser um resultado esperado da simulação; entrada inválida deve ser erro de validação.

**Pronto quando:** as regras de alocação, liberação, falha e reinício estiverem documentadas e alinhadas entre a equipe.

### 2. Completar e validar o núcleo do backend

Implementar a liberação de processos em `Memory`, unir blocos livres vizinhos e definir o resultado quando o identificador não existe. Validar também identificadores duplicados, preservação do tamanho total da memória e consistência dos blocos após cada operação. Criar um serviço de simulação que mantenha a memória e a estratégia selecionada e ofereça operações de iniciar, alocar, liberar, consultar estado e reiniciar.

Adicionar testes unitários para cada estratégia, para alocação exata e fragmentada, falta de espaço, identificadores inválidos ou repetidos, desalocação, junção de blocos e sequências de operações. O núcleo deve continuar independente do Spring e do frontend.

**Pronto quando:** as regras da etapa 1 estiverem cobertas por testes do núcleo e o serviço de simulação conseguir completar uma sequência inteira sem depender de uma interface.

### 3. Definir e implementar a API REST

Criar controllers, DTOs de request/response, validação, conversão entre tipos da API e do domínio, e respostas de erro consistentes. Documentar o contrato com OpenAPI. Configurar CORS somente para as origens de desenvolvimento e produção realmente usadas.

Um contrato inicial possível:

| Operação | Rota | Objetivo |
| --- | --- | --- |
| Criar simulação | `POST /api/simulations` | Informa o tamanho da memória e o tipo de estratégia; retorna o identificador e o estado inicial. |
| Consultar simulação | `GET /api/simulations/{simulationId}` | Retorna memória, blocos, processos e estratégia atuais. |
| Alocar processo | `POST /api/simulations/{simulationId}/processes` | Recebe identificador, nome e tamanho do processo; retorna resultado e estado atualizado. |
| Liberar processo | `DELETE /api/simulations/{simulationId}/processes/{processId}` | Libera o processo e retorna o estado atualizado. |
| Reiniciar simulação | `POST /api/simulations/{simulationId}/reset` | Restaura a memória vazia com as configurações escolhidas. |

As rotas são uma proposta a validar na etapa 1. Antes da integração, decidir onde o backend guarda cada simulação e como evita que requisições de usuários diferentes compartilhem estado. Para execução local inicial, simulações em memória identificadas por `simulationId` podem bastar; persistência em banco não é necessária para o protótipo.

**Pronto quando:** todas as operações tiverem exemplos de request/response, erros documentados e puderem ser verificadas de ponta a ponta sem Angular.

### 4. Definir a identidade visual e preparar o frontend Angular

**Estado:** protótipo visual estático implementado em `frontend/`, com três direções visuais e temas claro/escuro. Os dados são ilustrativos e as ações de simulação ainda não funcionam.

Manter a aplicação Angular em `frontend/`, com comandos e dependências próprios. Validar a direção visual com a equipe e, quando as regras e o contrato da API estiverem prontos, estruturar os componentes e serviços para consumir o backend. Não implementar algoritmos de alocação no frontend.

O protótipo visual apresenta:

- Cabeçalho com nome do simulador, seletor de tema claro/escuro e configuração inicial da memória.
- Comparação visual entre First Fit, Best Fit, Next Fit e Worst Fit, com uma explicação curta de cada estratégia.
- Exemplo visual de formulário de processo e capacidade, sem ações funcionais.
- Mapa visual de blocos proporcionais à capacidade, diferenciando áreas livres e ocupadas, com endereço inicial, tamanho e processo.
- Lista e histórico com dados ilustrativos.
- Resumo de memória total, ocupada e livre com valores de exemplo.
- Layout responsivo, rótulos e foco acessíveis e contraste adequado nos dois temas.

Os únicos controles ativos nesta etapa trocam tema e direção visual. Os controles de simulação permanecem desativados até a integração com o backend.

**Pronto quando:** a equipe escolher a direção visual e o protótipo servir de referência para a implementação das telas integradas.

### 5. Integrar frontend e backend

Implementar a versão HTTP do serviço Angular conforme OpenAPI. Configurar a URL base por ambiente. Exibir estados de carregamento, falha de rede, validação e falta de memória sem perder o estado visível; atualizar o mapa e a lista a partir das respostas do backend.

**Pronto quando:** o mesmo fluxo do protótipo funcionar contra o backend e os erros esperados estiverem representados na interface.

### 6. Automatizar execução e entrega do monorepo

Documentar como iniciar backend e frontend em terminais separados. Adicionar ao `.gitignore` artefatos do Angular (`node_modules/`, `dist/`, `.angular/`, `coverage/`) e arquivos de configuração local com segredos. Na integração contínua, instalar e validar cada projeto com seus comandos próprios, evitando que dependências do frontend sejam misturadas com Maven.

**Pronto quando:** uma pessoa nova conseguir clonar o repositório, seguir o README e executar os dois projetos localmente; as verificações do backend e frontend estiverem automatizadas.

## O que falta para integração completa

1. Implementar `Memory.liberar` e a junção de blocos livres adjacentes.
2. Fechar as regras de identidade do processo, tamanho/unidade da memória, falhas de alocação e ciclo de vida da simulação.
3. Criar e testar o serviço que coordena memória, estratégia e operações da simulação.
4. Criar a API REST, DTOs, validações, erros, OpenAPI e configuração de CORS.
5. Definir isolamento e armazenamento das simulações entre clientes.
6. Escolher e validar a identidade visual do protótipo Angular em tema claro e escuro.
7. Implementar o serviço HTTP Angular e integrar as telas com os DTOs aprovados.
8. Atualizar README e CI para os comandos independentes dos dois projetos.

O trabalho pode avançar nas etapas 1 e 4 em paralelo: a equipe fecha as regras do simulador enquanto escolhe a identidade visual do protótipo. A integração começa após as etapas 2 e 3.
