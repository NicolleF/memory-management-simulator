# Arquitetura

## 1. Visão geral

Este projeto consiste em um simulador de gerenciamento de memória desenvolvido para a disciplina de Sistemas Operacionais.

O simulador será desenvolvido de forma incremental, sendo a primeira versão:

* **V1:** Alocação e Desalocação de Memória

A arquitetura foi definida para separar a lógica da simulação da interface gráfica, permitindo que os algoritmos de gerenciamento de memória sejam implementados e testados independentemente da UI.

---

## 2. Princípios da arquitetura

O projeto seguirá os seguintes princípios:

* Os algoritmos de gerenciamento de memória serão implementados pela equipe.
* A interface não deve conter regras de gerenciamento de memória.
* A lógica da simulação deve ser independente da interface.
* Cada algoritmo deve possuir sua própria implementação.
* A inclusão de novos algoritmos deve exigir o mínimo possível de alterações nas implementações existentes.
* A arquitetura será evoluída conforme novas versões forem implementadas.

---

# V1 - Alocação de Memória

## 3. Escopo

A primeira versão será responsável pelo gerenciamento básico de uma memória simulada.

O simulador deverá permitir:

* Criar processos.
* Solicitar alocação de memória.
* Liberar memória utilizada por processos.
* Visualizar o estado atual da memória.
* Selecionar uma estratégia de alocação.

### Estratégias de alocação

* First Fit
* Best Fit
* Next Fit (planejado)
* Worst Fit

---

## 4. Arquitetura da V1

```text
┌─────────────────────┐
│         UI          │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  Simulador de       │
│     Memória         │
└──────────┬──────────┘
           │
     ┌─────┴─────┐
     ▼           ▼
┌─────────┐ ┌──────────────┐
│ Memória │ │  Estratégias │
└─────────┘ ├──────────────┤
            │ First Fit    │
            │ Best Fit     │
            │ Next Fit     │
            │ Worst Fit    │
            └──────────────┘
```

---

## 5. Componentes da V1

### 5.1 Memória

Responsável por representar a memória simulada.

Responsabilidades:

* Definir o tamanho total da memória.
* Armazenar os blocos de memória.
* Identificar blocos ocupados e livres.
* Realizar alocações.
* Liberar memória.

---

### 5.2 Bloco de memória

Representa uma região da memória simulada.

Possíveis informações:

* Posição inicial.
* Tamanho.
* Estado de ocupação.
* Processo associado.

---

### 5.3 Processo

Representa um processo que solicita memória.

Possíveis informações:

* Identificador do processo.
* Nome do processo.
* Tamanho de memória solicitado.

---

### 5.4 Estratégia de alocação

Define o contrato que deverá ser seguido pelas estratégias de alocação.

```text
EstrategiaDeAlocacao
       │
       ├── FirstFit
       ├── BestFit
       ├── NextFit
       └── WorstFit
```

Cada estratégia será responsável por determinar em qual região da memória um processo deverá ser alocado.

A escolha da implementação é centralizada em `AllocationFactory`.
O simulador informa um `AllocationType` (`FIRST_FIT`, `BEST_FIT`, `NEXT_FIT` ou `WORST_FIT`)
e recebe uma `AllocationStrategy`, que pode ser passada a `Memory.alocar`.
Assim, o código que coordena a simulação não precisa instanciar as estratégias concretas.

---

### 5.5 Simulador de memória

Responsável por coordenar a execução da simulação.

Responsabilidades:

* Receber solicitações de processos.
* Selecionar a estratégia de alocação.
* Solicitar a alocação da memória.
* Liberar processos.
* Disponibilizar o estado atual da simulação para a UI.

---

## 6. Fluxo de dados da V1

```text
Usuário
   │
   ▼
 UI
   │
   │ Solicitação de alocação
   ▼
Simulador de Memória
   │
   ▼
Estratégia de Alocação
   │
   ▼
Memória
   │
   ▼
Resultado da Alocação
   │
   ▼
 UI
```

---

## 7. Estrutura conceitual da V1

```text
src/
├── core/
│   ├── model/
│   │   ├── Memory
│   │   ├── MemoryBlock
│   │   └── Process
│   ├── enums/
│   │   └── AllocationType
│   ├── factories/
│   │   └── AllocationFactory
│   ├── strategies/
│   │   ├── AllocationStrategy
│   │   ├── AllocationFirstFit
│   │   ├── AllocationBestFit
│   │   ├── AllocationNextFit
│   │   └── AllocationWorstFit
│   └── simulation/
│       └── MemorySimulator
└── ui/
   └── ...
```
