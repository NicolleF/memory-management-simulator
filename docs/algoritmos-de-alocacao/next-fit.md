# Next Fit

## 1. Visão geral

O Next Fit é uma variação do algoritmo First Fit que utiliza um ponteiro para registrar a posição em que a última alocação foi realizada.

Diferentemente do First Fit, que inicia a busca novamente no começo da memória a cada solicitação, o Next Fit continua a procura a partir da posição em que a busca anterior terminou.

Dessa forma, a busca pode percorrer a memória de maneira circular, retornando ao início quando chega ao final.

## 2. Funcionamento

1\. Receber a solicitação de alocação de um processo.
2\. Iniciar a busca a partir da posição armazenada pelo ponteiro de alocação.
3\. Percorrer os blocos de memória disponíveis a partir dessa posição.
4\. Identificar o primeiro bloco livre que possua tamanho suficiente para atender à solicitação.
5\. Alocar o processo no bloco encontrado.
6\. Atualizar o ponteiro para a posição em que a alocação foi realizada.
7\. Caso o final da memória seja alcançado, continuar a busca a partir do início, de forma circular.
8\. Caso nenhum bloco adequado seja encontrado após percorrer a memória disponível, informar que a alocação não foi possível.

## 3. Exemplo

Considere a seguinte situação de memória:

| Bloco | Estado   | Tamanho |
| ----- | -------- | ------: |
| A     | Livre    | 8 M     |
| B     | Ocupado  | 22 M    |
| C     | Livre    | 20 M    |
| D     | Livre    | 14 M    |
| E     | Ocupado  | 10 M    |
| F     | Livre    | 24 M    |

Considere que a última alocação ocorreu próxima ao bloco E e que um novo processo solicita 16 M.

A busca do Next Fit começa a partir da posição registrada pelo ponteiro:

* Bloco E: ocupado, não pode ser utilizado.
* Bloco F: possui 24 M e é suficiente para o processo de 16 M.
* O bloco F é selecionado, deixando 8 M livres.

**Resultado:** o processo de 16 M é alocado no bloco F, pois ele é o primeiro bloco livre suficientemente grande encontrado a partir da última posição de alocação.

Esse comportamento é diferente do First Fit, que reiniciaria a busca no início da memória, e do Best Fit, que procuraria o menor bloco capaz de acomodar os 16 M.

## 4. Características

* É uma variação do First Fit que mantém um ponteiro indicando onde a última busca terminou.
* Não reinicia a busca no início da memória a cada nova solicitação.
* Pode realizar a busca de forma circular, percorrendo novamente o início da memória quando necessário.
* Distribui as novas alocações por diferentes regiões da memória, evitando concentrar todas as buscas e alocações no início.
* Pode reduzir o tempo de busca em determinadas situações, pois a procura não precisa começar novamente desde o primeiro bloco.
* Assim como outros algoritmos de alocação contígua, não elimina a fragmentação externa e seu comportamento depende da distribuição dos blocos livres.

## 5. Critérios de validação

* Deve iniciar a busca a partir da posição indicada pelo ponteiro.
* Deve selecionar o primeiro bloco livre suficiente encontrado durante a busca.
* Deve ignorar blocos ocupados e blocos insuficientes.
* Deve atualizar o ponteiro após uma alocação bem-sucedida.
* Deve continuar a busca pelo início da memória quando atingir o final, caso ainda existam blocos que não foram analisados.
* Deve informar falha quando nenhum bloco disponível for suficiente após percorrer a memória.
* Deve preservar corretamente o espaço restante após a alocação.

## 6. Implementação no projeto

**Status:** Planejado para a V1.

A implementação deverá seguir a arquitetura definida em `ARCHITECTURE.md`.

O algoritmo deverá manter um ponteiro indicando a posição a partir da qual a próxima busca será realizada. A busca deverá ser circular, permitindo retornar ao início da memória quando o final for alcançado.

Detalhes da implementação, decisões técnicas e testes serão adicionados após o desenvolvimento do algoritmo.

## 7. Referências

[A bibliografia utilizada para fundamentar o algoritmo será registrada nesta seção.](https://www.geeksforgeeks.org/dsa/program-for-next-fit-algorithm-in-memory-management/)