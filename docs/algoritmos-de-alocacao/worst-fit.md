# Worst Fit 

## 1. Visão geral

O Worst Fit é um algoritmo de alocação de memória que seleciona o maior bloco livre disponível capaz de atender à solicitação de um processo.

A estratégia busca alocar o processo no maior espaço disponível, deixando o restante desse bloco livre para futuras solicitações.

## 2. Funcionamento

1. Receber a solicitação de alocação de um processo.
2. Percorrer os blocos de memória disponíveis.
3. Identificar os blocos livres com tamanho suficiente.
4. Comparar os tamanhos dos blocos candidatos.
5. Selecionar o maior bloco adequado.
6. Alocar o processo e preservar eventual espaço restante como um bloco livre.
7. Caso nenhum bloco seja suficiente, informar que a alocação não foi possível.

## 3. Exemplo

Considere a seguinte memória:

| Bloco | Estado  | Tamanho |
| ----- | ------- | ------: |
| A     | Livre   |  100 MB |
| B     | Ocupado |  200 MB |
| C     | Livre   |  300 MB |
| D     | Livre   |  150 MB |

Um processo solicita 120 MB.

Os blocos C e D atendem à solicitação.

* Bloco C: deixa 180 MB livres.
* Bloco D: deixa 30 MB livres.

**Resultado:** o processo é alocado no bloco C, deixando 180 MB livres nessa região.

## 4. Características

* Seleciona o maior bloco livre adequado.
* Pode deixar um espaço restante relativamente grande no bloco selecionado.
* Precisa comparar os blocos candidatos para identificar o maior.
* Não garante menor fragmentação nem melhor aproveitamento da memória em todos os cenários.

## 5. Critérios de validação

* Deve selecionar o maior bloco livre capaz de atender à solicitação.
* Deve ignorar blocos ocupados e blocos insuficientes.
* Deve preservar o espaço restante após a alocação.
* Deve informar falha quando nenhum bloco disponível for suficiente.
* Deve tratar corretamente blocos candidatos de mesmo tamanho, conforme o critério de desempate definido na implementação.

## 6. Implementação no projeto

**Status:** Planejado para a V1.

A implementação deverá seguir a arquitetura definida em `ARCHITECTURE.md`.

Detalhes da implementação, decisões técnicas e testes serão adicionados após o desenvolvimento do algoritmo.

## 7. Referências

[A bibliografia utilizada para fundamentar o algoritmo será registrada nesta seção.](https://www.geeksforgeeks.org/operating-systems/worst-fit-allocation-in-operating-systems/)
