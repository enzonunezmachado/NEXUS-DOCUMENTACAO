# Decisões consolidadas de UX e produto

**Origem principal:** `docs/lista.md`, documento legado de consolidação de decisões do usuário.  
**Status:** decisões de experiência registradas; não equivalem à confirmação de implementação atual.

## 1. Princípio de experiência

“Muita informação, pouca confusão.” A interface deve ser limpa, elegante, compacta e funcional, pensada primeiro para celular. Deve reduzir rolagem desnecessária sem sacrificar legibilidade, contraste, alvos de toque ou acesso a informações importantes. Filtros servem para refinar uma interface que já deve ser organizada; não devem ser necessários para corrigir desorganização.

Evitar cartões altos sem necessidade, grandes áreas vazias, cabeçalhos repetidos, barras permanentes redundantes, fileiras excessivas de filtros e carrosséis aninhados. Navegação horizontal deve ter justificativa funcional, como percorrer os dias da semana.

## 2. Agenda como visão temporal unificada

A Agenda responde: “O que está acontecendo ou exige minha atenção nesta data ou neste período?”

Ela reúne registros originados em seus próprios módulos:
- Tarefas com data.
- Compromissos.
- Eventos.
- Obrigações e vencimentos financeiros relevantes.
- Faturas com vencimento.
- Prazos de projetos, quando aplicável.

A Agenda não deve criar uma segunda lista de tarefas nem duplicar registros. Ao selecionar um item, deve abrir seu registro ou módulo de origem.

### Visualizações
- Dia.
- Semana.
- Mês.
- Lista.

A decisão registrada em `lista.md` favorece Mês como visualização inicial, com o dia atual selecionado. A alternância entre modos deve preservar o contexto da navegação. A visão semanal foi descrita como horizontal, com os dias em cartões/colunas, sem empilhar os sete dias verticalmente.

### Filtros e pesquisa
- Um único controle compacto de filtros.
- Filtro por tipo e, depois, por categoria contextual ao tipo selecionado.
- Evitar duas fileiras permanentes de filtros.
- A lupa global deve ser o ponto único de pesquisa geral.
- Evitar barras de pesquisa largas e permanentes nas telas Agenda, Financeiro e Eventos.

## 3. Separação de tipos de registro

A aba Tarefas deve conter ações executáveis. Lançamentos financeiros, pagamentos, eventos e compromissos não devem aparecer como tarefas apenas porque exigem atenção.

Uma obrigação financeira pode aparecer na Agenda por causa do vencimento, mas sua fonte de verdade continua sendo o Financeiro. Não criar automaticamente uma tarefa duplicada. Um usuário pode criar uma tarefa separada e vinculá-la quando isso fizer sentido.

Status da tarefa e condição do prazo são dimensões diferentes:
- Status: pendente/não iniciada, em andamento, aguardando, bloqueada, concluída ou cancelada.
- Prazo: no prazo, atrasada ou sem prazo.

Uma tarefa pode estar em andamento e atrasada simultaneamente. Atraso deve ser derivado da data e do estado, não substituir o status.

## 4. Recorrência simples para o usuário

A recorrência deve ser acessível em “Mais detalhes”, com o rótulo “Repetir”. Foram registradas opções como diária, dias específicos da semana, semanal, quinzenal, mensal e intervalo personalizado.

Para editar ou excluir uma ocorrência, oferecer “Só esta ocorrência” ou “Toda a série”. Adiar uma ocorrência altera somente aquela data, sem modificar a cadência original. Evitar pré-criar centenas de ocorrências futuras; projetar ocorrências conforme a janela visual solicitada.

**Nota de estado:** recorrência de tarefas foi posteriormente reportada como implementada. Recorrência de compromissos e eventos permanecia planejada no último relatório técnico recebido.

## 5. Financeiro

A visão financeira deve ser visual e interativa, sem ambiguidade. As decisões registradas incluem:
- Seletor global de mês e ano, com abertura no mês atual.
- Gráfico de rosca de despesas por categoria, com até seis categorias e “Outros”.
- Alternância entre valores pagos e pagos + previstos.
- Seleção de categoria para filtrar os lançamentos.
- Gráfico de barras comparando os últimos seis meses.
- Previsão dos próximos 30 dias.
- Clareza entre realizado e previsto.
- Regras de cartão, fatura, pagamento e despesas sem duplicidade.

Os detalhes devem ser consultados na especificação técnica vigente antes de qualquer alteração.

## 6. Eventos e compromissos

Manter os conceitos distintos:
- **Evento:** ocasião com início/fim, local opcional, status e preço real de ingresso.
- **Compromisso:** horário reservado, com duração, local/link opcional e eventual vínculo a um evento.

O detalhe do evento pode apresentar compromissos, tarefas e lançamentos vinculados, sem duplicar seus registros. Preço de ingresso deve ser o valor real informado; não inventar taxas. Local pode ser presencial, online ou a definir. Banner é opcional.

## 7. Mercado

O Mercado foi pensado como contexto financeiro informativo, não como corretora. Não incluir ordens, carteira de investimentos, recomendação de compra/venda ou notícias como escopo implícito. A decisão inicial priorizava dólar, euro e Bitcoin; expansão dependia de fonte gratuita e confiável sem token pessoal. A implementação posterior foi ampliada tecnicamente para 12 ativos, conforme documentação GOOGLE_IA.

## 8. Dashboard / Início

O Dashboard deve resumir o dia, o mês e áreas importantes, sem reproduzir listas longas:
- Saudação, data e frase diária integradas em um único cabeçalho discreto.
- Indicador de tarefas com Concluídas, Pendentes e Em andamento; atrasadas em contador separado.
- Resumo financeiro compacto.
- Projetos e objetivos.
- Próximos compromissos e eventos.
- Alertas somente quando existirem.
- Bloco Mercado opcional.

Os gráficos e indicadores úteis devem aparecer próximos ao topo, depois do cabeçalho. A Home não deve se transformar em terminal de investimentos nem em lista interminável.

### Ordem de navegação registrada historicamente

O documento `lista.md` registrou a ordem: Tarefas | Agenda | Início | Financeiro | Eventos, com Início central. Esta é uma decisão histórica de UX; verificar o estado atual da navegação antes de tratá-la como vigente ou propor mudanças.

## 9. Design e interação

- Identidade minimalista, com referência de legibilidade e interação inspirada em Apple/iPhone/Apple Watch, sem copiar telas.
- Base neutra; cores com significado funcional.
- Contraste equivalente em tema claro e escuro.
- Ícones e formas ajudam a distinguir tipos de registro.
- Gráficos interativos e animações sutis, rápidas e úteis.
- Não sacrificar alvos de toque para compactar.
- Estados vazios e acessibilidade semântica devem ser considerados.

## 10. Processo e critérios de qualidade

As decisões também estabeleceram que cada lote deve terminar integrado e compilável, com testes executados e limitações honestamente registradas. Isso é uma diretriz de processo; não significa que os testes históricos tenham sido executados.

Fonte histórica detalhada: [`docs/lista.md`](../docs/lista.md).
