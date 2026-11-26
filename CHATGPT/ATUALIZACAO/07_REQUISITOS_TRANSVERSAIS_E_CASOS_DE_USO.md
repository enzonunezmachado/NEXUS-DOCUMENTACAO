# 07 — Requisitos transversais e casos de uso integrados

**Estado:** CAPTURADO / EM ANÁLISE  
**Finalidade:** registrar comportamentos que atravessam mais de uma entidade ou módulo e que não podem ser implementados como soluções isoladas.

## 1. Princípio de integração

O NEXUS precisa preservar a identidade de cada registro enquanto permite relações e aparições em vários contextos. Integração não significa fundir entidades nem duplicar informação. O comportamento deve ser consistente entre domínio, Agenda, Hoje, fichas, notificações, backup e Inbox.

## 2. Recorrência e checklist por ocorrência — GAP-003

### Necessidade do usuário

Uma tarefa pode se repetir semanalmente e possuir um checklist com itens estáveis. Cada ocorrência da tarefa representa um novo ciclo de execução. Concluir os itens de uma semana não pode deixar os itens da semana seguinte marcados.

### Exemplo de referência

Tarefa: “Checklist semanal”, recorrência toda segunda-feira.

Checklist-base:
- Conferir os compromissos da semana.
- Organizar as prioridades.
- Revisar pendências.

Comportamento esperado:
1. A ocorrência da semana 1 apresenta os três itens pendentes.
2. O usuário conclui os três itens.
3. A ocorrência da semana 1 permanece concluída no histórico, com seu checklist concluído.
4. A ocorrência da semana 2 apresenta os mesmos três itens, novamente pendentes.
5. A ocorrência da semana 2 tem identidade e estado próprios; concluir ou reabrir itens nela não altera a semana 1 nem futuras ocorrências.
6. O modelo/base da série mantém o conteúdo reutilizável do checklist, enquanto cada ocorrência mantém seu próprio estado de execução.

### Regras que precisam ser definidas

- O que constitui o modelo/base do checklist e o que pertence à ocorrência.
- Se ocorrências futuras recebem automaticamente alterações no checklist-base.
- Como tratar itens adicionados, removidos ou reordenados depois de algumas ocorrências concluídas.
- Se uma alteração no modelo afeta ocorrências já materializadas ou apenas futuras.
- Como tratar exceção de uma ocorrência, adiamento, cancelamento, conclusão parcial e reabertura.
- Como manter IDs estáveis, histórico e idempotência.
- Como a recorrência virtual da Agenda se relaciona com a materialização da tarefa e do checklist.
- Como backup, restauração, merge e Inbox preservam a distinção entre modelo e instância.
- Como a UI indica que o usuário está editando o modelo da série ou uma ocorrência específica.

### Risco a evitar

Não copiar o estado de conclusão do checklist anterior para a próxima ocorrência. Também não apagar o histórico anterior ao atualizar o modelo. Não criar uma coleção de tarefas avulsas para simular uma série.

### Critérios de aceite

- Duas ocorrências da mesma série podem ter checklists com os mesmos itens e estados diferentes.
- Concluir um item em uma ocorrência não altera o mesmo item em outra.
- A próxima ocorrência começa com os estados iniciais definidos para o novo ciclo.
- O histórico de ocorrências anteriores permanece íntegro.
- Alterar o modelo não corrompe instâncias anteriores.
- O comportamento sobrevive a reinício, backup/restauração, merge e entrada Inbox, conforme o escopo aprovado.
- Agenda, Tarefas, Hoje e fichas mostram a mesma ocorrência e o mesmo estado.

### Dependências

- Auditoria do motor de recorrência de Tarefas já existente.
- Inspeção do modelo de checklist/subtasks e da materialização de ocorrências.
- Decisão de produto sobre edição do modelo versus ocorrência.
- Compatibilidade com GAP-001 (recorrência de Compromissos/Eventos) e GAP-002 (contrato Inbox para recorrência).

**Importante:** o estado atual dessa capacidade ainda não foi verificado no código. Este registro captura o comportamento desejado e exige auditoria antes de afirmar que está ausente ou implementado.

## 3. Recorrência de compromissos e eventos — GAP-001

Consultar o registro técnico em `GOOGLE_IA/docs/15_REGISTRO_LACUNAS_E_FALHAS.md` e o requisito REQ-009. A regra deve diferenciar compromisso de evento e integrar Agenda, lembretes, exceções, backup e importação.

## 4. Ponte de entrada estruturada — GAP-002

Consultar o contrato técnico v1 e o registro técnico de lacunas. A Inbox precisa transportar a regra e as exceções de recorrência de forma fiel, com validação, prévia e confirmação. Não converter séries em registros únicos independentes.

## 5. Outros cenários transversais obrigatórios para auditoria

| Cenário | Pergunta que precisa ser respondida |
|---|---|
| Tarefa ligada a projeto e evento | O progresso e as relações aparecem sem duplicar a tarefa? |
| Tarefa com checklist e recorrência | Cada ocorrência possui estado independente e histórico preservado? |
| Compromisso dentro de evento | A ficha do evento mostra o compromisso canônico e a Agenda não duplica? |
| Evento de vários dias | Datas, duração, tarefas, custos, ingressos e lembretes se mantêm coerentes? |
| Lançamento ligado a tarefa/projeto/evento | Valor aparece nos contextos relacionados sem dupla contagem? |
| Item exibido na Agenda | Ícone identifica o tipo e controle de ação executa apenas a ação anunciada? |
| Registro em Hoje e módulo de origem | Alterar em um contexto atualiza todas as outras aparições? |
| Exclusão/arquivamento de entidade relacionada | Relações, histórico e dados financeiros permanecem íntegros? |
| Backup e restauração | Relações, recorrências, exceções e checklists por ocorrência são preservados? |
| Inbox e reimportação | Idempotência impede duplicação e conflito não apaga dados locais? |
| Rotação/recriação da Activity | Filtros, fichas, rota e rascunhos mantêm comportamento esperado? |
| Notificações | Alterar/cancelar série não deixa lembretes antigos ou duplicados? |

## 6. Regra de atualização

Quando um novo cenário revelar uma dependência entre entidades, registrar aqui, no requisito técnico correspondente e no inventário de lacunas. Não implementar uma solução local antes de entender o efeito sobre as outras áreas.
