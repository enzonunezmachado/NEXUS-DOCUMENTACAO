# Problemas e correções solicitadas

**Origem:** exploração manual do NEXUS com o backup de Gabriel e observações diretas do usuário.  
**Estado geral:** itens registrados; nenhuma correção deste documento foi implementada por esta atualização documental.

## BUG-001 — Perda de tela e dados de edição ao girar o dispositivo

- **Classificação:** defeito funcional observado pelo usuário.
- **Prioridade sugerida:** alta.
- **Estado:** **EM ABERTO — persistiu após o lote visual/adaptativo**, com novo relato de uso em 22/11/2026. A correção não está validada.
- **Abrangência relatada:** comportamento recorrente em diferentes abas e fluxos, não apenas em Eventos. Em 22/11/2026, após instalar/testar a versão que continha o lote visual/adaptativo, o usuário voltou a relatar o problema em celular e tablet.
- **Comportamento relatado:** durante a criação ou edição de um registro, ao girar o telefone/tablet, o aplicativo retorna à página inicial e perde o contexto da operação. Em novo relato de 22/11/2026, o exemplo explícito foi editar uma tarefa já existente e voltar à tela inicial ao girar o aparelho.
- **Exemplos mencionados:** formulário de evento, edição de dados e menu de três pontos.
- **Resultado esperado:** a rotação deve preservar a tela atual, navegação, valores preenchidos, seleções, diálogos/menus relevantes e o progresso da edição.
- **Investigação necessária no código:**
  - recriação de Activity e tratamento de mudanças de configuração;
  - uso de `remember`, `rememberSaveable`, ViewModel e `SavedStateHandle`;
  - estado de navegação e restauração da rota atual;
  - formulários, editores, diálogos, menus e seleções;
  - estado que sobrevive à recriação do processo, quando viável.
- **Escopo da correção:** investigar o padrão arquitetural compartilhado e corrigir a causa abrangente. Não aceitar como solução apenas um workaround na tela Eventos.
- **Distinção importante:** rotação/recriação de Activity é diferente de encerramento forçado ou morte do processo. A análise deve declarar quais cenários serão cobertos.
- **Critério de validação futuro:** iniciar criação/edição em diferentes abas, preencher campos e seleções, girar o dispositivo em diferentes etapas e confirmar que tela, dados e progresso permanecem. Repetir em fluxos com menus/diálogos e, se suportado, após recriação do processo.
- **Relação com IDEA-006:** além da preservação de estado, a interface deve reorganizar-se conforme orientação e tamanho da janela. Em tablet deitado, a Agenda poderá manter calendário/dias de um lado e itens do dia selecionado do outro. Essa adaptação visual deve ser especificada e validada separadamente da persistência do estado.
- **Não confirmado:** causa exata, telas afetadas individualmente, solução técnica, cobertura de testes automatizados e versão em que será corrigido. O relato mais recente confirma persistência no uso após o lote visual/adaptativo, mas não substitui auditoria atual do código nem determina sozinho a abrangência completa.

## COR-001 — Remover o N verde redundante antes do nome NEXUS

- **Classificação:** correção visual solicitada pelo usuário.
- **Prioridade sugerida:** alta para o próximo lote visual, após investigação do BUG-001.
- **Estado:** SOLICITADO / análise de ocorrências pendente.
- **Problema percebido:** símbolo N verde antes da palavra NEXUS cria leitura visual semelhante a “N NEXUS” ou “NNXUS”. O verde não representa a identidade de marca desejada.
- **Direção solicitada:** retirar o N verde redundante e manter o nome NEXUS sem duplicação visual.
- **Referência de identidade:** ícone atual do aplicativo com N branco sobre fundo preto.
- **Restrições:** não criar logotipo novo, não alterar o ícone do aplicativo e não redesenhar a marca além do pedido sem autorização.
- **Investigação:** localizar todas as ocorrências do símbolo em headers, splash, telas, recursos gráficos e componentes reutilizados.
- **Critério de validação futuro:** verificar as telas relevantes e confirmar que o nome aparece sem símbolo redundante e que o ícone existente permanece inalterado.
- **Não confirmado:** quantidade/localização das ocorrências, origem do recurso e implementação.

## Preservações identificadas durante a exploração

Não são bugs; são comportamentos apreciados que não devem ser removidos acidentalmente:

- **OBS-001:** alertas vermelhos para atrasos e pendências vencidas e amarelos para sobreposição/conflito de horários, exibidos na página inicial.
- **OBS-002:** fluxo da Agenda em que o usuário seleciona uma data e consulta seus itens abaixo.

Qualquer refatoração futura deve preservar a distinção semântica entre atraso e conflito, salvo decisão explícita em contrário.

## Histórico de resolução

| ID | Estado atual | Quando atualizar |
|---|---|---|
| BUG-001 | EM ABERTO; novo relato de persistência após o lote visual/adaptativo em 22/11/2026; causa ainda não confirmada | Auditoria técnica transversal, proposta de correção, implementação autorizada e teste de rotação em dispositivo |
| COR-001 | Correção visual solicitada; não implementada | Após localizar ocorrências, alterar e revisar telas |

Ao concluir uma correção, registrar commit/versão, arquivos afetados, teste executado, resultado e limitações. Só usar **VALIDADO** quando houver evidência funcional adequada.
