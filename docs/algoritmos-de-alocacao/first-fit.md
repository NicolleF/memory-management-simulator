# First Fit (Primeiro Ajuste)

## 1. Visão geral

O First Fit é um algoritmo de alocação de memória que seleciona o primeiro bloco livre capaz de atender à solicitação de um processo.

A busca começa no início da região de memória considerada e avança sequencialmente até encontrar um bloco com espaço suficiente.

## 2. Funcionamento

1. Receber a solicitação de alocação de um processo.
2. Percorrer os blocos de memória em ordem crescente de endereço.
3. Verificar se cada bloco está livre e possui tamanho suficiente.
4. Alocar o processo no primeiro bloco que atenda aos requisitos.
5. Caso o bloco seja maior que o necessário, dividir a região em uma parte ocupada e outra livre.
6. Se nenhum bloco for suficiente, informar que a alocação não foi possível.

## 3. Exemplo

Considere a seguinte memória:

| Bloco | Estado  | Tamanho |
| ----- | ------- | ------: |
| A     | Livre   |  100 MB |
| B     | Ocupado |  200 MB |
| C     | Livre   |  300 MB |
| D     | Livre   |  150 MB |

Um processo solicita 120 MB.

O algoritmo verifica os blocos na ordem apresentada:

* Bloco A: possui 100 MB, portanto é insuficiente.
* Bloco B: está ocupado e não pode ser utilizado.
* Bloco C: possui 300 MB e está livre, portanto atende à solicitação.

**Resultado:** o processo é alocado no bloco C, utilizando 120 MB e deixando 180 MB livres nessa região.

## 4. Características

* Realiza a busca a partir do início da memória.
* Interrompe a busca assim que encontra um bloco adequado.
* Pode deixar espaços livres de diferentes tamanhos ao longo da memória.
* A escolha do primeiro bloco adequado não garante o menor desperdício de espaço.

## 5. Critérios de validação

* Deve selecionar o primeiro bloco livre com tamanho suficiente.
* Deve ignorar blocos ocupados.
* Deve rejeitar blocos menores que a solicitação.
* Deve preservar o espaço restante quando o bloco selecionado for maior que o necessário.
* Deve informar falha quando nenhum bloco disponível for suficiente.

## 6. Implementação no projeto

**Status:** Planejado para a V1.

A implementação deverá seguir a arquitetura definida em `ARCHITECTURE.md`.

Detalhes da implementação, decisões técnicas e testes serão adicionados após o desenvolvimento do algoritmo.

## 7. Referências

[A bibliografia utilizada para fundamentar o algoritmo será registrada nesta seção.](https://www.geeksforgeeks.org/operating-systems/first-fit-allocation-in-operating-systems/)
