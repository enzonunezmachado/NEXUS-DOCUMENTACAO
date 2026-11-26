# Claude — Componentes, fichas e interações

> **Origem:** extração temática fiel da proposta completa do Claude, preservada sem reescrita substantiva.  
> **Estado:** PROPOSTA / EM ANÁLISE. Este material não representa decisão aprovada nem autorização de implementação.  
> **Fonte integral preservada:** `NEXUS — Proposta de Arquitetura da Informação e Experiência (2).md`. Consulte a fonte original para contexto contínuo e referências.

---

### H.1 A escada de formatos e como escolher

Cada registro pode aparecer em cinco formatos. A escolha depende de quatro fatores: **natureza** do registro, **volume** da coleção, **importância** naquele contexto e **ação** esperada.

| Formato | Quando usar | Exemplos |
| --- | --- | --- |
| **Linha compacta** (sem cartão, uma ou duas linhas) | Coleção numerosa e acionável | Tarefas, extrato de lançamentos, resultados de busca, filhos dentro de uma expansão |
| **Cartão compacto** | Coleção média, com identidade própria | Compromissos do dia, faturas, projetos em lista, metas |
| **Cartão expressivo** | Poucos itens de alta identidade | Eventos próximos, projetos e objetivos ativos, cartões de área na Início |
| **Expansão no lugar** | Contêiner com até três filhos acionáveis do mesmo tipo | Evento ou projeto numa lista ou na Início |
| **Ficha** | Leitura completa, relações, comparação, ações e edição | Qualquer registro, ao tocar no corpo |

**Formato por entidade e contexto** \[proposta\]:

|  | Início | Lista do módulo | Agenda | Dentro de outra ficha | Busca |
| --- | --- | --- | --- | --- | --- |
| Tarefa | Linha | Linha | Linha com ícone de tipo | Linha | Linha |
| Compromisso | Cartão compacto (em “Agora”) | — | Bloco de tempo | Linha | Linha |
| Evento | Cartão expressivo | Cartão expressivo (compacto se forem muitos) | Faixa ou cartão compacto | Cartão compacto | Linha com data |
| Projeto | Cartão expressivo | Cartão compacto com progresso | Marca de prazo | Chip | Linha |
| Objetivo | Cartão expressivo | Cartão com marcos | — | Chip | Linha |
| Lançamento | Linha | Linha (extrato) | Linha | Linha | Linha |
| Fatura | Cartão compacto (em Atenção) | Cartão compacto | Marca de prazo | Linha | Linha |

### H.2 A tela se adapta à quantidade de dados

Sem inventar conteúdo para encher espaço:

| Volume | Composição | Exemplo |
| --- | --- | --- |
| **Nenhum** | Estado vazio com próximo passo, intencional | “Nenhuma tarefa para hoje. Próxima: 26/11, Estudar para P2.” — dado real, não decoração |
| **1 a 3** | Formato maior é permitido: mais contexto por item | A única tarefa do dia aparece com subtarefas e projeto à vista |
| **4 a 12** | Linhas ou cartões compactos, agrupados por tempo ou estado | Tarefas por Atrasadas, Hoje, Esta semana |
| **13 ou mais** | Linhas densas, grupos recolhíveis, filtros fixos, “mostrar mais” | Extrato do mês, lista de tarefas |

Os limiares são **hipóteses** a calibrar com o seu uso \[hipótese\]. O princípio é **mais contexto real**, nunca mais altura vazia.

### H.3 O que a visão semanal ensina

O que funciona nela \[doc\]: cada dia é um **cartão-contêiner** que já traz seus itens compactos, sem depender de cabeçalhos clicáveis que abrem uma lista; hoje e o dia selecionado têm contorno proporcional; a semana inteira aparece de uma vez em telas largas e vira carrossel em telas estreitas.

O que aproveitar em outras áreas: o padrão **cartão-contêiner com N linhas visíveis e “+N”**, bom para Evento (tarefas), Projeto (tarefas) e Objetivo (marcos). O que **não** copiar: o cartão por dia só funciona porque o **tempo é um eixo natural**; uma lista de duzentas tarefas não tem esse eixo.

### H.4 Estrutura comum das fichas, composição própria por entidade

As fichas compartilham um **esqueleto**: faixa de identidade (ícone de tipo, título, estado) → resumo (um a três fatos primários) → relações contextuais → detalhes recolhíveis → ações. Mudam a ordem, o peso e a composição.

**Regras das relações dentro da ficha** \[proposta\]: (1) só aparecem vínculos que **existem nos dados**; (2) vínculos **derivados** ganham o rótulo “via tarefas” ou “via lançamentos”; (3) agrupados em **Contexto** (parte de), **Dinheiro** (custos, fatura, parcela) e **Itens** (tarefas, compromissos); (4) até cinco chips visíveis e “+N”; (5) cada chip mostra ícone do tipo, nome e um estado curto, e **navega** para a ficha canônica; (6) o vínculo redundante com o contexto de origem não é repetido.

#### Tarefa

- **Primeiro plano:** título; estado, com marca de atraso (ponto + texto); prazo (relativo e absoluto); prioridade (por forma, não só cor); progresso de subtarefas; **ação “Concluir”** proeminente.
- **Contexto:** projeto, evento e objetivo como chips (só os que existem); gasto total com “ver lançamentos”; resumo da série, se for recorrente.
- **Segundo plano:** descrição, notas, categoria (ponto discreto).
- **Aprofundamento:** subtarefas completas e lançamentos vinculados.
- **Ações:** concluir ou reabrir; adiar; **lápis** para editar; excluir no menu.

#### Lançamento

- **Primeiro plano:** valor grande, com sinal; descrição; data (vencimento ou ocorrência) e estado (Previsto, Pendente, Realizado, Atrasado); categoria.
- **Origem e natureza:** “Despesa · custo de evento · alimentação”, “ingresso do evento X”, “parcela 3/10”, “regra mensal”.
- **Relações:** evento, projeto, tarefa; se estiver no cartão, a fatura que compõe, com vencimento.
- **Impacto financeiro (derivado):** efeito na fatura e no orçamento da categoria no mês \[pendente: confirmar que o cálculo pode ser feito com as regras atuais sem dupla contagem\].
- **Ações:** as ações financeiras que já existem, quando permitidas \[pendente: listar\]; lápis; excluir.

```
┌──────────────────────────────────────────┐
│ ←   Lançamento                      ✎  ⋮ │
├──────────────────────────────────────────┤
│ $  Estimativa alimentação Hackathon      │
│ −R$ 90,00                                │
│ vence 05/12/2026 · Previsto              │
│ Alimentação · Despesa · custo de evento  │
├──────────────────────────────────────────┤
│ CONTEXTO                                 │
│ ◆ Hackathon FinTech Poli 2026   04/12  › │
│ ⚑ projeto…  ○ tarefa…        (se existirem) │
├──────────────────────────────────────────┤
│ IMPACTO (derivado)                       │
│ Orçamento Alimentação    xx% → yy%       │
│ Fatura do mês            sem efeito      │
├──────────────────────────────────────────┤
│ Detalhes ▾  (forma de pagamento, notas)  │
└──────────────────────────────────────────┘
```

Os vínculos “projeto” e “tarefa” do desenho **só aparecem se existirem**; aqui estão como exemplo do espaço reservado. “xx%” e “yy%” são valores a calcular, não dados do vídeo.

#### Evento

- **Identidade:** capa (se existir) ou composição neutra intencional; nome; período; local; estado do evento.
- **Resumo:** **ingresso** com rótulo explícito (“Ingresso: pago”); custo total e pago em barra; preparação “tarefas concluídas de N”.
- **Preparação:** até três tarefas com círculo de conclusão e “ver todas”; compromissos do evento \[parcial no sistema\].
- **Custos:** por tipo (ingresso, transporte, alimentação, hospedagem, estacionamento, compras, outros), com estado.
- **Relações derivadas:** “projetos com tarefas neste evento”, rotulado como derivado.
- **Segundo plano:** links oficial e do ingresso, descrição, notas.
- **Ações:** adicionar tarefa, compromisso ou gasto **já vinculados ao evento**; lápis; excluir no menu.

```
  CARTÃO COMPACTO        EXPANDIDO NO LUGAR           FICHA (página)
┌───────────────────┐ ┌──────────────────────┐ ┌────────────────────────┐
│ [capa/inicial]    │ │ [capa/inicial]       │ │ ← Hackathon FinTech…  ✎ ⋮│
│ Hackathon FinTech │ │ Hackathon FinTech    │ │ [capa]                 │
│ 04/12 · dia todo  │ │ 04/12 · dia todo     │ │ 04/12 · dia todo       │
│ USP · Ingresso:   │ │ Ingresso: pago       │ │ Centro de Difusão…     │
│ pago              │ │ R$ 65 de R$ 155 ▓▓░  │ │ Ingresso: pago         │
│ ⌄                 │ │ PREPARAÇÃO           │ │ Custos R$65 / R$155 ▓▓░│
└───────────────────┘ │ ○ tarefa 1           │ │ PREPARAÇÃO  ver todas  │
                      │ ○ tarefa 2           │ │ ○ …  ○ …  ○ …          │
                      │ ○ tarefa 3  + 3      │ │ COMPROMISSOS           │
                      │ ⌃        abrir ficha ›│ │ CUSTOS por tipo        │
                      └──────────────────────┘ │ RELAÇÕES (via tarefas) │
                                               └────────────────────────┘
```

(As tarefas do desenho são genéricas: o vídeo não mostra quais tarefas pertencem ao evento.)

#### Projeto

- **Primeiro plano:** nome; estado; **prazo**; **progresso** (tarefas concluídas de N); objetivo associado (chip), se houver.
- **Propósito:** o texto de descrição, apresentado como propósito. Não existe campo separado \[código\].
- **Tarefas:** agrupadas com as atrasadas primeiro; círculo de conclusão em cada uma.
- **Dinheiro:** custo estimado e pago, número de lançamentos, com acesso ao extrato filtrado pelo projeto.
- **Não prometo** marcos nem participantes, que não existem no projeto \[código\].

#### Objetivo

- **Primeiro plano:** resultado pretendido; horizonte (data-alvo e dias restantes); **progresso com a origem do número** (“automático: 1 de 2 marcos, 3 tarefas, 1 projeto” ou “informado por você”).
- **Marcos:** lista com datas e marcação.
- **Escada curta:** projetos e tarefas ligados, em árvore de poucos níveis, com “ver todas”.
- **Histórico:** evolução do progresso.
- **Distinção:** Metas financeiras **não** aparecem aqui, pois não têm ligação com o objetivo no modelo \[código\].

#### Compromisso

- **Primeiro plano:** quando (data, horário, duração); onde (local, com abrir no mapa ou link, se existir); contexto (evento, categoria).
- **Ações:** lápis; excluir no menu. Não tem estado nem conclusão.

#### Fatura

- **Primeiro plano:** valor, vencimento, estado (Aberta, Fechada, Paga, Atrasada), cartão.
- **Composição:** lista das compras que a formam; a ação de pagar já existe nos Cartões \[doc\].

### H.5 Cartão de tarefa: decisões concretas sobre o que já existe

O `TaskRowCard` atual \[código\] junta status, projeto, prazo, recorrência, subtarefas e gasto em **uma linha de texto** separada por pontos, cortada em duas linhas. Em vez de aumentar a linha, proponho **hierarquia dentro da linha**: título (peso principal); estado de atraso e prazo (peso secundário, em destaque se atrasada); **chip** do vínculo mais útil (projeto na lista de Tarefas); e só na ficha o resto. Isso preserva a densidade e tira o texto cortado.

## I. Interações e navegação

### I.1 O contrato do toque \[proposta\]

Cada elemento tem **uma** responsabilidade, e o que parece controle é controle.

| Elemento | Toque | Resultado | Observação |
| --- | --- | --- | --- |
| **Círculo de conclusão** | Um toque | Conclui ou reabre a tarefa; confirmação visual curta e “Desfazer” | Área própria (≥ 48 dp); nunca abre a ficha |
| **Corpo da linha ou do cartão** | Um toque | Abre a **ficha de consulta** | Nunca abre o editor |
| **Lápis** (na ficha) | Um toque | Abre o editor correspondente | Ícone sempre no mesmo lugar |
| **⋮** (nas linhas e cartões) | Um toque | Menu: Editar · Excluir · Abrir no módulo | Para quem precisa editar sem passar pela ficha |
| **Chip de relação** | Um toque | Navega à ficha canônica do registro relacionado | Parece link, comporta-se como link |
| **Seta de expansão** (só em cartões-contêiner) | Um toque | Expande ou recolhe no lugar | Área distinta do corpo do cartão |
| **“Ver todas” e “+N”** | Um toque | Abre a coleção filtrada | Mesmo rótulo, mesma natureza |
| **Cabeçalho de bloco ou cartão de área** | Um toque | Abre o módulo | Distinto do item |
| **Etiquetas de estado e chips informativos** | Não respondem | — | Sem ripple e sem forma de botão |
| **Excluir** | Só no ⋮ ou na ficha | Confirmação | Já existe diálogo de perigo \[código\] |

Hoje, o corpo do cartão abre o editor \[código\]. Na proposta, **o editor continua o mesmo**, reaproveitado; muda quem o chama e a que distância do toque ele fica.

### I.2 Regras de conclusão: o que o código já diz e o que precisa ser decidido

Observações do código \[código\]:

- Na lista de Tarefas, o círculo **alterna** a conclusão **sem checar** se a tarefa está bloqueada.
- No widget, concluir é **recusado** para tarefas bloqueadas, concluídas ou canceladas.
- Ao concluir uma tarefa recorrente, o sistema **cria a próxima ocorrência**.
- Reabrir uma tarefa a devolve a “Em andamento”, **não ao status anterior** (por exemplo, “Aguardando”).
- Na Agenda, uma ocorrência virtual de recorrência precisa ser **materializada** antes de qualquer ação.

Isso mostra que **“a mesma ação” já se comporta de formas diferentes** (lista × widget), o que fere o contrato de que ações equivalentes devem ser equivalentes \[doc\]. Proposta de contrato, **para decisão sua** \[pendente\]:

1. **Conclusão direta** em Início, Tarefas, ficha da tarefa e prévia de tarefas dentro de evento e projeto — todos usam a mesma regra.
2. **Tarefa bloqueada:** o círculo mostra um cadeado; o toque explica “Bloqueada: altere o estado para concluir”. Decidir se a lista deve passar a obedecer à mesma regra do widget.
3. **Tarefa recorrente:** concluir avisa que a próxima ocorrência foi criada.
4. **Agenda:** a conclusão direta só é oferecida para tarefas já materializadas; nas virtuais, o toque abre a ficha. Enquanto isso, o emblema quadrado deixa de imitar uma caixa de seleção (G.5).
5. **Reabrir:** decidir se deve restaurar o status anterior (exigiria guardá-lo) ou continuar indo para “Em andamento”.

### I.3 Expandir, navegar ou abrir a ficha

| Situação | Escolha | Por quê |
| --- | --- | --- |
| Cartão de evento ou projeto numa lista ou na Início, com até três filhos acionáveis | **Expandir no lugar** | Dá para espiar e concluir sem sair do contexto |
| O registro tem mais de uma seção de informação, relações a explorar ou será editado | **Abrir a ficha** | O contexto de leitura precisa de espaço |
| A pessoa toca numa relação | **Navegar** à ficha do outro registro | Muda de entidade, não de nível |
| Linha de tarefa, lançamento ou compromisso | **Abrir a ficha** (nunca expandir) | Pouco conteúdo a mais; a ficha é curta e rápida |

Proibições: **expandir dentro de uma ficha**, **expandir dentro de uma expansão** e **ficha sobre ficha**. Em listas longas, **uma expansão aberta por vez**, para o conteúdo não crescer sem fim; na Início, o estado de expansão não persiste.

Um limite a respeitar: com seis tarefas, mostrar três na expansão é útil; com quatro, a expansão quase não economiza nada e é melhor abrir a ficha. Regra prática \[hipótese\]: **só oferecer expansão quando houver mais de três filhos**.

### I.4 Navegar entre registros relacionados

Objetivo: ir de um lançamento ao evento, do evento ao projeto, e **voltar** sem se perder, **sem camadas sobrepostas**.

1. **A ficha é uma tela (rota), não uma camada.** No celular ocupa a tela; no tablet, um painel (seção J).
2. **Navegar por relação empilha a nova ficha; Voltar a desempilha.** A barra superior mostra o nome da ficha anterior (“← Hackathon FinTech Poli”).
3. **Sem laços:** se a relação escolhida já está na pilha, o app volta até ela em vez de abrir uma cópia (evita A → B → A → B).
4. **A ficha anterior sai da tela** (transição lateral) e preserva rolagem, filtros e expansão quando se retorna.
5. **O editor é uma camada modal sobre a ficha**, não faz parte da pilha: ao salvar, **volta à ficha**, não à lista.
6. **O Voltar do sistema** segue a prioridade que já existe (editor › subtela › aba › Início › sair), com a pilha de fichas entre “subtela” e “aba” \[código: `resolveBackAction`\].
7. **Profundidade:** a trilha comprime depois do terceiro nível, e há atalho para o início do módulo.

**O que o código permite hoje** \[código\]: existe pilha de **subtelas** e uma regra pura de voltar; `activeEditor` é uma **variável única**, e há casos em que abrir outro registro de dentro de um editor **substitui** o primeiro. Não existe rota de ficha nem estado por aba. Como o Voltar se comporta nesses casos precisa ser conferido no aparelho \[pendente\]. **Isto é mudança de navegação**, não de apresentação.

### I.5 Consultar × editar na prática

- **Consultar:** tocar no corpo.
- **Concluir:** círculo, ou botão “Concluir” na ficha.
- **Ações de estado rápidas** (não são edição): concluir, adiar para amanhã, mudar status. Ficam como botões na ficha, sem abrir o editor.
- **Editar campos:** lápis na ficha, ou ⋮ na linha. O editor atual é reaproveitado.
- **Excluir:** menu da ficha ou do editor, com confirmação.
- **Descartar alterações:** a confirmação existente continua \[código\].

A separação não cria “mais toques” para quem só quer ver: o **primeiro toque deixa de abrir um formulário** e passa a abrir uma tela curta de leitura. Para quem quer editar direto, o ⋮ resolve em dois toques.

### I.6 Microinterações

Vêm depois: expansão suave que desloca o conteúdo abaixo, confirmação visual e tátil de conclusão, transições entre ficha e lista, progresso animado com moderação. Tudo respeita a preferência de movimento reduzido e nunca atrasa uma ação.

## J. Adaptação para diferentes telas

