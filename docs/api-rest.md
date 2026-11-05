# Contrato da API REST

Prefixo das rotas: `/api/simulacoes`. O campo `estrategia` usa os valores do enum `AllocationType`: `BEST_FIT`, `WORST_FIT`, `FIRST_FIT` e `NEXT_FIT`. Best Fit e Worst Fit serão implementados primeiro. First Fit e Next Fit são valores reconhecidos, mas retornam `501 Not Implemented` enquanto suas operações não estiverem disponíveis.

## Criar simulação

`POST /api/simulacoes`

Request:

```json
{
  "tamanhoMemoria": 1024,
  "estrategia": "BEST_FIT"
}
```

Retorna `201 Created`, com o estado inicial e o cabeçalho `Location` apontando para a simulação criada.

## Consultar estado

`GET /api/simulacoes/{simulationId}`

Retorna `200 OK` com a capacidade, estratégia selecionada, tamanhos ocupados e livres, blocos e processos.

## Alocar processo

`POST /api/simulacoes/{simulationId}/processos`

Request:

```json
{
  "nome": "Editor de texto",
  "tamanho": 128
}
```

O backend gera o identificador do processo. Em caso de sucesso, retorna `200 OK` com o estado atualizado. Sem espaço suficiente, retorna `409 Conflict`.

## Liberar processo

`DELETE /api/simulacoes/{simulationId}/processos/{processId}`

Retorna `200 OK` com o estado atualizado. A simulação ou o processo inexistente resulta em `404 Not Found`.

## Reiniciar simulação

`POST /api/simulacoes/{simulationId}/reiniciar`

Retorna `200 OK` com a memória vazia e a mesma capacidade e estratégia.

## Formato de estado

```json
{
  "id": "6b6bb68b-46d2-4fbf-93b5-8ac3643c59e3",
  "tamanhoTotal": 1024,
  "estrategia": "BEST_FIT",
  "memoriaOcupada": 128,
  "memoriaLivre": 896,
  "blocos": [
    {
      "enderecoInicial": 0,
      "tamanho": 128,
      "processo": {
        "id": "984aa68e-dd1f-44a3-b4c0-2016d8f1f5c2",
        "nome": "Editor de texto",
        "tamanho": 128
      }
    },
    {
      "enderecoInicial": 128,
      "tamanho": 896,
      "processo": null
    }
  ],
  "processos": [
    {
      "id": "984aa68e-dd1f-44a3-b4c0-2016d8f1f5c2",
      "nome": "Editor de texto",
      "tamanho": 128
    }
  ]
}
```

## Erros

Os erros usam o formato:

```json
{
  "timestamp": "2026-11-04T12:00:00Z",
  "status": 400,
  "erro": "Bad Request",
  "mensagem": "tamanho: deve ser maior que 0.",
  "caminho": "/api/simulacoes"
}
```

`400 Bad Request` indica JSON, parâmetros ou campos inválidos; `404 Not Found` indica simulação ou processo inexistente; `409 Conflict` indica falta de espaço para alocação; `501 Not Implemented` indica que as operações de domínio ou a estratégia solicitada ainda aguardam implementação.

## Estado de implementação

Controllers, DTOs, validação e tratamento de erros estão disponíveis. `SimulationApiOperationsAdapter` mantém a aplicação inicializável e retorna `501 Not Implemented` até a camada de aplicação ser conectada. Nenhum estado de simulação é criado por esse adaptador.
