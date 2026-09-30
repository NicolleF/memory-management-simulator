# Best Fit

## 1. Visão geral

O Best Fit é um algoritmo de alocação de memória que seleciona o menor bloco livre disponível capaz de atender à solicitação de um processo.

A estratégia busca utilizar o espaço mais próximo possível do tamanho solicitado, evitando reservar uma quantidade de memória muito maior do que a necessária.

## 2. Funcionamento

1\. Receber a solicitação de alocação de um processo.
2\. Percorrer os blocos de memória disponíveis.
3\. Identificar os blocos livres com tamanho suficiente para atender à solicitação.
4\. Comparar os tamanhos dos blocos candidatos.
5\. Selecionar o menor bloco que seja capaz de acomodar o processo.
6\. Alocar o processo no bloco selecionado e preservar eventual espaço restante.
7\. Caso nenhum bloco seja suficiente, informar que a alocação não foi possível.

## 3. Exemplo

Considere a seguinte memória e os processos apresentados na figura:

| Processo | Memória solicitada |
| -------- | -----------------: |
| J1       | 20 K               |
| J2       | 200 K              |
| J3       | 500 K              |
| J4       | 50 K               |

Os blocos de memória disponíveis são:

| Bloco | Tamanho |
| ----- | ------: |
| A     | 30 K    |
| B     | 50 K    |
| C     | 200 K   |
| D     | 700 K   |

Aplicando o algoritmo Best Fit:

* J1 solicita 20 K. O menor bloco capaz de atender à solicitação é o bloco A, de 30 K, deixando 10 K de espaço restante.
* J2 solicita 200 K. O bloco C possui exatamente 200 K e, portanto, é o menor bloco adequado.
* J3 solicita 500 K. Os blocos menores não são suficientes, então o bloco D, de 700 K, é selecionado, deixando 200 K de espaço restante.
* J4 solicita 50 K. O bloco B possui exatamente 50 K e é selecionado.

**Resultado:** os processos são alocados nos menores blocos disponíveis que conseguem atender às suas respectivas solicitações. Ao final, são utilizados 770 K, com 210 K de espaço restante devido às diferenças entre o tamanho dos blocos e os processos alocados.

## 4. Características

* Seleciona o menor bloco livre que seja suficiente para a solicitação.
* Busca aproveitar a memória de forma mais precisa, evitando utilizar blocos muito maiores do que o necessário.
* Pode exigir a análise de vários ou de todos os blocos livres para encontrar o melhor encaixe.
* Pode deixar pequenos espaços de memória após as alocações, aumentando a fragmentação externa em determinadas situações.
* Pode apresentar maior custo computacional do que estratégias que encontram um bloco adequado mais rapidamente.

## 5. Critérios de validação

* Deve selecionar o menor bloco livre capaz de atender à solicitação.
* Deve ignorar blocos ocupados e blocos insuficientes.
* Deve preservar o espaço restante após a alocação.
* Deve informar falha quando nenhum bloco disponível for suficiente.
* Deve tratar corretamente blocos candidatos de mesmo tamanho, conforme o critério de desempate definido na implementação.

## 6. Implementação no projeto

**Status:** Planejado para a V1.

A implementação deverá seguir a arquitetura definida em `ARCHITECTURE.md`.
Detalhes da implementação, decisões técnicas e testes serão adicionados após o desenvolvimento do algoritmo.

## 7. Referências

[A bibliografia utilizada para fundamentar o algoritmo será registrada nesta seção.](https://www.geeksforgeeks.org/operating-systems/best-fit-allocation-in-operating-system/)