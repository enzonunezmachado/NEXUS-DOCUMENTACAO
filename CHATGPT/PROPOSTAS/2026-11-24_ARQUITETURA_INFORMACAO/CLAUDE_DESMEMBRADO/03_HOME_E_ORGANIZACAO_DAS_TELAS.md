# Claude — Home e organização das telas

> **Origem:** extração temática fiel da proposta completa do Claude, preservada sem reescrita substantiva.  
> **Estado:** PROPOSTA / EM ANÁLISE. Este material não representa decisão aprovada nem autorização de implementação.  
> **Fonte integral preservada:** `NEXUS — Proposta de Arquitetura da Informação e Experiência (2).md`. Consulte a fonte original para contexto contínuo e referências.

---

## 6. Organização de cada tela

Cada área segue a mesma ficha: finalidade, informação prioritária, blocos e hierarquia entre resumo e detalhe, visões e filtros, ações, relações e comportamento nos três formatos (celular, tablet retrato, tablet paisagem). Ainda não desenho componentes — resolvo a lógica de apresentação.

### 6.1 Hoje

- **Finalidade:** responder “como está meu dia e o que exige atenção?” numa leitura de cerca de dez segundos. Nunca é o lugar onde se administra: lê tudo, não possui nada.
- **Informação prioritária:** (1) o que está acontecendo ou vem a seguir; (2) o que está em risco; (3) o que há para fazer hoje; (4) o pulso do resto da vida.
- **Personalização:** só o Radar (ordem, visibilidade, compacto ou detalhado por bloco), com “Editar Radar” a um toque do cabeçalho. O esqueleto dos blocos 1 a 4 não se reordena — evita uma Home vazia ou caótica —, mas Atenção e O dia podem ser recolhidos. Blocos configuráveis são \[atual\]; o esqueleto fixo é \[novo · apresentação\].
- **Hierarquia resumo → detalhe:** cada bloco é uma frase; tocar abre a ficha ou a tela de origem. Gráficos deixam de ser padrão do Hoje: continuam no Radar como opção e vivem completos em Dinheiro e Tarefas.
- **Ações:** concluir tarefa, adiar, marcar conta como paga, abrir ficha. O Criar (+) abre a captura rápida com Tarefa pré-selecionada.
- **Relações:** lê de todos os destinos; cada item carrega o chip de origem.

**Blocos, de cima para baixo (esqueleto fixo + Radar configurável):**

1. **Cabeçalho:** saudação, nome, data e a frase do dia numa linha discreta \[atual\].
2. **Agora e a seguir:** um ou dois itens — compromisso em curso ou o próximo, evento em andamento, tarefa com horário.
3. **Atenção** (até três itens + “ver mais”): conflitos de horário, tarefas atrasadas, contas e faturas que vencem em breve, orçamento perto do limite, evento com preparação pendente, alerta de Mercado disparado. Cada item mostra a origem e *uma* ação (Resolver · Adiar · Abrir). Sem nada a dizer, uma linha serena — “Nada pede atenção agora” —, honesta, sem preencher. \[novo · apresentação\]
4. **O dia:** linha do tempo do dia (compromissos, eventos, prazos com hora) e, abaixo, “Para hoje” (tarefas sem hora, com caixa de marcar) e vencimentos do dia. Link “Abrir na Agenda”.
5. **Radar:** blocos configuráveis, cada um uma linha-resumo que expande — Dinheiro (saldo declarado, resultado do mês, próximo vencimento) · Próximos eventos · Objetivos em andamento · Comprometido nos próximos meses · Mercado (acompanhamento) · Gráficos (fluxo de seis meses, categorias).
6. **Fechamento:** à noite aparece o cartão “Encerrar o dia” (6.2).

**Tamanhos.** *Celular:* coluna única; Radar abaixo, blocos compactos por padrão. *Tablet retrato:* duas colunas (Agora e O dia | Atenção e Radar); a ficha abre num painel lateral, não em tela cheia. *Tablet paisagem:* três colunas (O dia | Atenção e Para hoje | Radar), com os gráficos do Radar sempre à vista sem empurrar a leitura inicial.

### 6.2 Encerrar o dia e Diário

- **Finalidade:** fechar o ciclo diário sem cobrança. Mantém a lógica da Revisão Noturna e do histórico \[atual\]; muda apenas onde vive.
- **Fluxo em três passos:** (1) *O que foi feito* — as concluídas do dia; (2) *O que ficou* — para cada pendência: concluir agora · adiar para amanhã (horários sugeridos) · reagendar · cancelar · deixar sem data; (3) *Amanhã* — prévia de compromissos, prazos e vencimentos. Ao final, uma frase de encerramento opcional e o registro no Diário.
- **Diário:** histórico de revisões e briefings, acessado pelo menu do Hoje.
- **Regra de tom:** nada de pontuação, sequência de dias ou culpa — o dossiê pede que o assistente não seja um coach.
- **Celular:** um passo por vez, em tela cheia. **Tablet:** os três passos lado a lado; ver “o que ficou” e “amanhã” ao mesmo tempo ajuda a decidir onde cada pendência cabe.

### 6.3 Agenda

- **Finalidade:** ver e planejar o *tempo*, sem possuir os registros.
- **Informação prioritária:** o que ocupa tempo (blocos), o que vence (prazos), o que se sobrepõe (conflitos).
- **Estrutura:** topo com período e visões; logo abaixo as **Camadas** (Tarefas · Compromissos · Eventos · Financeiro · Projetos, de seleção múltipla, lembrando a escolha; “Tudo” é o padrão); corpo com a visão; painel do dia selecionado.
- **Visões:** Mês (padrão) · Semana (segunda a domingo) · Dia (hora a hora) · Lista (próximos dias em sequência, para ler). Busca textual e “mostrar pagos” permanecem \[atual\].
- **Três coisas no tempo, três formas:** **Blocos** (compromissos e tarefas com horário ocupam intervalo na grade); **Faixas** (eventos de vários dias, barras que atravessam os dias); **Prazos** (tarefas só com data, contas, faturas, prazo de projeto: marcas “vence” numa linha “Dia todo / Prazos” no topo do dia). Assim uma fatura não se confunde com um compromisso às 14h. \[novo · apresentação\]
- **Origem:** ícone do tipo + chip de origem em cada item; cor só para atraso e conflito.
- **Conflitos:** marca na grade e uma linha-resumo na semana (“1 conflito na quinta”), que também alimenta a Atenção do Hoje.
- **Fatura:** item único (“Fatura Nubank · R$ …”); tocar mostra a composição e leva a Dinheiro › Cartões. Compras isoladas nunca aparecem \[atual\].
- **Ações:** Criar (+) cria compromisso no dia selecionado; tocar num item abre a ficha na casa dele; microações no próprio item.
- **Celular:** Mês = grade compacta em cima e lista do dia embaixo; Semana = tira de sete dias + lista do dia (em paisagem de celular, sete colunas). **Tablet retrato:** calendário + dia selecionado; semana em sete colunas legíveis. **Tablet paisagem:** três painéis — calendário · dia selecionado · ficha do item (hoje: calendário + itens do dia \[atual\]).

### 6.4 Tarefas

- **Finalidade:** encontrar e conduzir ações — inclusive as sem data.
- **Informação prioritária:** o que está atrasado e o que é para hoje.
- **Estrutura:** faixa-resumo no topo (“2 atrasadas · 4 hoje · 9 em andamento”, tocável como filtros; o anel do mês fica aqui como detalhe recolhível, vindo do Início); alternador **Lista | Quadro**; filtros em chips (Atrasadas · Hoje · Próximas · Sem prazo · Concluídas) e “Mais filtros” (status, prioridade, categoria, projeto, evento, objetivo).
- **Lista:** grupos temporais \[atual\]; Atrasadas no topo, com acento semântico — uma *seção*, não uma aba; Concluídas recolhidas como histórico.
- **Quadro:** seis colunas, uma por status, sobre as mesmas tarefas \[atual\].
- **Ficha da tarefa:** título e estado · **Quando** (prazo, horário, lembrete) · **Passos** (subtarefas) · **Série** (se recorrente, com “só esta / toda a série”) · **Contexto** (projeto, evento, objetivo) · **Dinheiro** (gasto associado, “Registrar gasto”) · notas.
- **Ações:** + Tarefa (captura rápida); concluir direto da lista; “Registrar gasto” e “Mover para projeto” na ficha.
- **Relações:** Agenda (se tem data), Planos (vínculos), Dinheiro (gasto).
- **Celular:** lista; o quadro vira abas por status com contagem. **Tablet retrato:** lista + ficha lado a lado; quadro com três colunas visíveis. **Tablet paisagem:** quadro com as seis colunas (ou quatro com rolagem) e ficha em painel lateral; ou lista + ficha.

### 6.5 Planos

Os três contêineres usam a **mesma ficha de plano**: cabeçalho (nome, status, datas) · resumo (progresso, prazo, custo) · itens · dinheiro · conexões · histórico. O que muda é a ênfase.

- **Visão:** três faixas — Objetivos ativos, Projetos em andamento, Próximos eventos — com dois ou três cartões cada e “Ver todos”. Mostra a escada de uma só vez.
- **Objetivos:** lista com progresso e a *origem do número* (“calculado a partir de 2 projetos e 5 tarefas” ou “informado por você”) — progresso manual e automático são \[atual\]; o rótulo é \[novo · apresentação\]. Ficha: marcos com datas, progresso, **Escada** (projetos e tarefas ligados, em árvore expansível), histórico de evolução.
- **Projetos:** lista com progresso, prazo e custo; filtros por status, prazo e objetivo. Ficha: resumo (progresso, prazo que também aparece na Agenda, custo das despesas não canceladas), tarefas agrupadas por status, objetivo vinculado.
- **Eventos:** lista por tempo — Em andamento · Próximos · Passados. Ficha: identidade (datas, local), **Preparação** (tarefas e compromissos \[parcial\]), **Ingresso** (gratuito, pago ou cancelado; à vista ou parcelado), **Custos** (por tipo; previsto × pago), linha do tempo antes / durante / depois.
- **Ações:** Criar (+) cria o que a sub-área mostra; dentro de uma ficha, “+ Tarefa”, “+ Compromisso” e “+ Custo” já nascem vinculados.
- **Diferenciar os três:** Objetivo é *resultado* (progresso e marcos); Projeto é *entrega* (tarefas, prazo, custo); Evento é *ocasião* (datas, preparação, ingresso). Na criação, uma pergunta de apoio: “É um resultado que você quer, uma entrega com fim ou uma ocasião com data?”
- **Celular:** Visão em faixas; listas; ficha em tela cheia, resumo sempre no topo e seções recolhíveis. **Tablet retrato:** lista + ficha. **Tablet paisagem:** lista · ficha · painel de apoio (Escada no objetivo, Custos no evento).

### 6.6 Dinheiro — Meu dinheiro

Cabeçalho comum: seletor de mês e ano \[atual\] e o alternador **Meu dinheiro | Mercado**.

- **Visão:** (a) pulso — saldo declarado com a legenda “informado por você, não recalculado” \[atual\], receitas, despesas e resultado, com alternador realizado | previsto; (b) **Vence logo** — previsão de 30 dias e maiores vencimentos; (c) orçamentos em alerta; (d) resumo de cartões (limite disponível, fatura aberta); (e) mini-gráficos — rosca por categoria e fluxo de seis meses. Tocar num gráfico abre **Análises**: tela cheia com os controles de hoje (pago × pago + previsto; selecionar uma categoria filtra os lançamentos).
- **Lançamentos:** extrato com filtros (período, tipo, categoria, status previsto/realizado/atrasado, conta ou cartão, vínculo com tarefa/evento/projeto), busca inline e soma do filtro no topo. Cada linha traz o chip de vínculo.
- **Cartões e faturas:** lista de cartões (limite, disponível, fechamento, vencimento); dentro, seletor **Atual · Fechada · Futuras** com as compras. “Pagar fatura” liquida as compras sem criar segunda despesa \[atual\]. Uma linha explica: *compras compõem a fatura; a Agenda mostra só o vencimento*.
- **Comprometido** \[novo · apresentação; possivelmente dados/regra\]: horizonte de doze meses empilhado por origem — faturas, parcelas, recorrências, empréstimos —, mês atual destacado. Tocar num mês lista o que o compõe e de onde vem (cada linha abre a ficha da origem). Abaixo, a gestão: parcelamentos, regras recorrentes, empréstimos e pagamentos, agrupados por tipo. Responde “o que vence e o que continuará pesando”.
- **Orçar:** orçamentos por categoria (barra de consumo, aviso ao se aproximar ou ultrapassar) e metas financeiras. Nomenclatura: “Metas financeiras” fica aqui, com a explicação “valores a juntar”, para não competir com “Objetivos” (resultados de vida).
- **Exportar CSV:** no menu ⋮ de Dinheiro e em Central › Dados \[atual\].
- **Celular:** abas rolantes; Visão em cartões verticais. **Tablet retrato:** Visão em duas colunas (pulso e vencimentos | gráficos); Lançamentos lista + ficha. **Tablet paisagem:** Visão em grade com os dois gráficos lado a lado (comparação); Lançamentos = filtros e soma · lista · ficha; Cartões = cartões · fatura · compras; Comprometido = horizonte · composição do mês.

### 6.7 Dinheiro — Mercado

- **Finalidade:** acompanhar cotações e variações; informativo, nunca o patrimônio da pessoa nem uma corretora \[atual\].
- **Honestidade:** cabeçalho próprio (“Mercado · dados informativos”); cada cotação com carimbo de frescor (“atualizado há 12 min · cache”); estado offline explícito, usando o último cache válido \[atual\].
- **Estrutura:** **Acompanhamento** (favoritos) primeiro; **Todos os ativos** agrupados (moedas, criptomoedas, índices, ações); **Alertas** de preço. Detalhe do ativo: cotação, variação, unidade, gráfico (1 dia, 1 semana, 1 mês, 3 meses, 6 meses, 1 ano, conforme a disponibilidade), favoritar, criar alerta.
- **Relações:** alerta disparado vira notificação, abre o ativo e aparece na Atenção do Hoje; o acompanhamento é bloco opcional do Radar (desligado por padrão).
- **Celular:** lista → detalhe. **Tablet retrato:** lista + detalhe. **Tablet paisagem:** lista + detalhe, com espaço para comparar dois ativos \[novo · funcionalidade\].

### 6.8 Busca

- **Finalidade:** achar sem lembrar o módulo.
- **Entrada:** lupa na barra superior de qualquer destino; campo permanente no rail do tablet. Filtros *dentro* de listas (como o texto da Agenda) continuam filtrando só aquela lista; a lupa é sempre global.
- **Estado inicial:** buscas recentes e chips de tipo (Tarefas · Compromissos · Eventos · Lançamentos · Projetos · Objetivos · Ativos).
- **Resultados:** agrupados por tipo, com cabeçalho e contagem; cada linha mostra ícone do tipo, título, contexto (projeto, evento), data ou valor e status. Tocar abre a ficha na casa do registro; Voltar retorna à busca com a consulta preservada.
- **Cobertura:** o dossiê fala em “entidades suportadas” sem listá-las \[hipótese: confirmar quais tipos a busca cobre hoje\]; a proposta supõe todos os registros do mapa acima.
- **Celular:** tela cheia. **Tablet:** resultados à esquerda, prévia ou ficha à direita.

### 6.9 Criar e editores

- **Criar (+) contextual:** Hoje e Tarefas → tarefa · Agenda → compromisso (no dia selecionado) · Planos → objetivo, projeto ou evento, conforme a sub-área · Dinheiro › Meu dinheiro → lançamento · Mercado → alerta de preço. Toque longo: menu completo.
- **Captura rápida:** uma folha baixa com o **título primeiro**; uma linha de tipo (já escolhido pelo contexto, trocável sem perder o texto); chips opcionais — *Quando*, *Valor*, *Projeto/Evento*; “Salvar” ou “Mais detalhes”, que abre o editor completo mantendo o que foi digitado.
- **Tarefa × compromisso, em uma linha:** *Tarefa: algo que você faz, com prazo. Compromisso: hora marcada, com início e fim.*
- **Editor completo em quatro blocos:** Essencial · Quando e onde · Vínculos e dinheiro · Avançado (recorrência, lembrete, categoria…). Nenhum campo é removido; campos vazios ficam recolhidos. \[novo · apresentação\]
- **Lançamentos por perguntas, não por tipos:** “No cartão?” · “Parcelar?” · “Repete?”. As respostas levam ao que hoje são editores separados (compra parcelada, regra recorrente). \[novo · apresentação; confirmar nas regras de dados se parcelamento e recorrência podem nascer do mesmo fluxo\]
- **Criação em contexto:** dentro de projeto, evento, tarefa ou cartão, o vínculo vem preenchido (“+ Tarefa” no evento já nasce ligada a ele; “Registrar gasto” na tarefa já traz a tarefa).
- **Tablet:** o editor abre como folha lateral ou diálogo central, sem cobrir a lista de onde veio.
- **Segurança:** confirmações de descarte e de operações destrutivas permanecem \[atual\].

### 6.10 Central do NEXUS (símbolo N)

Cinco entradas, cada uma com uma natureza só:

- **Biblioteca de Frases:** gerenciar frases e autores, importar TXT/JSON, ativar e desativar \[atual\]. Participação discreta e intencional: cabeçalho do Hoje \[atual\]; frase de encerramento em “Encerrar o dia” \[novo · apresentação\]; nunca em telas de dinheiro, alertas ou estados vazios. Um controle por momento (“mostrar no Hoje · mostrar no encerramento”) e, mais adiante, marcar frases por momento (manhã, noite) \[novo · funcionalidade e dados\].
- **Widgets:** os quatro widgets com a pergunta que cada um responde e como adicioná-los. Cada widget é uma *janela para um destino*, nunca uma tela miniaturizada (6.11).
- **Configurações**, por assunto: *Perfil e aparência* (nome, tema) · *Início* (Radar e densidade) · *Notificações e silêncio* · *Dinheiro* (categorias e preferências financeiras).
- **Dados:** exportar e restaurar backup (JSON) e exportar finanças (CSV) \[atual\]. Restaurar ganha atrito proporcional ao risco: mostra o que será substituído, recomenda exportar antes e pede confirmação explícita. Um selo “último backup: …” no topo \[novo · funcionalidade\] e, depois, um lembrete discreto na Atenção do Hoje quando fizer muito tempo. O backup completo com imagens de eventos continua pendente \[parcial\].
- **Suporte:** o Diagnóstico em duas camadas — um estado simples (“Tudo certo” ou “Há algo a reportar”) e “Detalhes técnicos” expansível, com contagens e registros —, botão “Copiar relatório” \[novo · apresentação\] e a versão do app.

### 6.11 Widgets e notificações como extensões

- **Widgets:** *Meu Dia* ↔ Agora e a seguir + O dia · *Minhas Tarefas* ↔ tarefas de hoje e atrasadas · *Financeiro* ↔ pulso do Dinheiro · *Resumo NEXUS* ↔ Atenção e contagens. Tocar abre a ficha ou o destino no contexto exato. Ideias futuras: “Fixar widget” a partir do destino correspondente e um widget de Mercado \[novo · funcionalidade\].
- **Notificações:** sempre abrem a *ficha do registro* (ou o ativo, ou “Encerrar o dia”), nunca só a aba. Regra de coerência: o que notifica é o que aparece em Atenção — e vice-versa — para que o aplicativo diga a mesma coisa nos dois lugares. Horários de silêncio e restauração dos agendamentos permanecem \[atual\].


---

## F. Home e experiência de visão geral

### F.1 Como a Início funciona hoje \[código\]\[vídeo\]

A Início é uma lista vertical de até nove tipos de bloco (saudação, frase, resumo de tarefas, agenda do dia, resumo financeiro, próximos eventos, próximos pagamentos, objetivos e metas, gráficos). Cada bloco tem ordem, ativação e quantidade máxima configuráveis, mas **é calculado de forma independente**: o repositório monta listas separadas (`overdueTasks`, `priorityTasksToday`, `upcomingEvents`, `upcomingPayments`, `openStatements`) e cada bloco desenha a sua. Não há uma etapa que decida **o que merece aparecer, uma vez só e na ordem certa**. Todos os achados B.2 de repetição, nome inadequado e ordem errada saem daí. Em telas largas a Início passa para duas colunas (≥ 720 dp) \[doc\].

### F.2 Qual deve ser a função da Início

| Interpretação | Pergunta que responde | Vantagem | Limite |
| --- | --- | --- | --- |
| Central de atenção | “O que precisa de mim?” | Foco total | Num dia tranquilo fica vazia; perde a visão geral |
| Visão geral da situação | “Como está tudo?” | Tranquiliza | É o que existe hoje: vira relatório e rolagem |
| Central de acesso contextual (a analogia do Windows) | “Para onde eu vou?” | Atalhos para tudo, com informação | Cartões de mesmo peso não priorizam; um atalho informa o lugar, mas o valor do NEXUS é o **estado entre lugares** |
| **Híbrida em duas camadas (recomendada)** | “O que preciso saber e fazer agora — e como está o resto?” | Decisão imediata em cima, panorama embaixo | Exige regras de relevância e deduplicação (F.3) |

Da analogia do Windows eu **mantenho** só uma ideia: **cada bloco é um atalho que leva ao lugar certo**. Não mantenho a ideia de uma grade de cartões de mesmo peso como estrutura principal.

**Camada 1 — Agora e atenção** (decisão): o que está acontecendo, o que está em risco, o que fazer hoje. **Camada 2 — Áreas** (panorama): uma vista sintética de cada área viva, em cartões que informam e levam ao módulo. Quase todo o volume de hoje deveria ficar na camada 2, em modo resumo.

### F.3 Regras de composição

1. **Um registro, uma aparição.** Cada registro é “reivindicado” pelo primeiro bloco que o merece, na ordem Atenção › Agora › Para hoje › Áreas. A tarefa atrasada e de prioridade alta aparece **uma vez**, com as duas marcas.
2. **O nome do bloco é a regra.** O título diz o critério: “Em atraso”, “Vence hoje”, “Próximos 7 dias”. Nada de “Próximos” com itens passados.
3. **Urgência antes de fonte.** Contas e faturas entram na mesma ordenação: vencidos primeiro, depois por vencimento. Um item vencido nunca fica atrás de um de fevereiro de 2027.
4. **Teto e “+N”.** Atenção, até 3 itens; Agora, até 2; Para hoje, até 5; cada cartão de área, até 2 itens. O excedente vira “+N” que leva à lista completa.
5. **Resumo primeiro, lista sob demanda.** O padrão de uma área sem urgência é **uma frase com números** (“3 em andamento · mais próximo: 18/12”). A lista aparece ao expandir ou na ficha.
6. **Estado do tempo.** O que já aconteceu hoje aparece esmaecido ou sai da camada 1. O evento que terminou às 19h não é “próximo” às 20h53.
7. **Vazio honesto.** “Nada pede atenção agora” quando for verdade; nunca preencher com itens para parecer cheio.
8. **Números não se repetem.** O resultado do mês aparece uma vez; o gráfico é opcional e, se ligado, **não repete** a frase do cartão.
9. **Cada bloco é um atalho com dois toques distintos:** o cabeçalho leva ao módulo; o item leva à **ficha de consulta**.
10. **Personalização preservada.** Ordem, ativação e quantidade por bloco continuam; acrescenta-se o **modo** do bloco (Resumo ou Lista) e três presets (Compacto, Padrão, Detalhado) \[proposta\].

### F.4 Quanto é suficiente

Não há número certo; proponho **hipóteses a testar**: modo padrão com no máximo **uma tela e meia** e cerca de **doze itens individuais**, medido com o **teste dos dez segundos** — em dez segundos a pessoa responde: o que está atrasado? o que vem agora? há dinheiro vencendo? Quem quiser mais abre o modo Detalhado ou o módulo \[hipótese\].

**Critérios de relevância** (regras explicáveis, não caixa-preta) \[proposta\]: urgência (vencido › vence hoje › vence em até 48 h); compromisso com hora; valor financeiro vencendo; prioridade alta ou urgente; preparação pendente de evento próximo; alerta de mercado disparado. Estados “aguardando” e “bloqueada” pesam menos como **ação**, mas podem aparecer como **situação**.

### F.5 Estrutura proposta (celular, retrato)

Os valores abaixo são **ilustrações baseadas no que o vídeo mostra**; os vínculos entre os registros do exemplo não aparecem no vídeo.

```
┌──────────────────────────────────────────┐
│ NEXUS                         ↗   ⌕   ⋯ │
├──────────────────────────────────────────┤
│ Boa noite, Enzo              Personalizar│
│ terça, 24/11 · frase do dia, discreta    │
├──────────────────────────────────────────┤
│ AGORA                                    │
│ ◷ amanhã 11:00  Check-in clínico USP     │
│                 HUB · Família e Lazer    │
├──────────────────────────────────────────┤
│ ATENÇÃO · 3                      ver tudo│
│ ▲ Fatura Nubank (jul/2026) vencida       │
│   R$ 27,90                    [abrir]    │
│ ○ Preparar deck de 5 slides… atrasada    │
│   hoje 12:00 · App Pitch                 │
│ ◆ Hackathon FinTech Poli · 04/12         │
│   ingresso pago · R$ 65 de R$ 155        │
├──────────────────────────────────────────┤
│ PARA HOJE · 1                  ver tarefas│
│ ○ Estudar para P2 de Cálculo Numérico    │
│   26/11 08:00 · 1/2 subtarefas           │
├──────────────────────────────────────────┤
│ ÁREAS                                    │
│ ┌────────────────┐ ┌────────────────┐   │
│ │ DINHEIRO       │ │ EVENTOS        │   │
│ │ −R$ 42,10      │ │ Hackathon      │   │
│ │ no mês         │ │ 04/12, dia todo│   │
│ │ próx.: R$ 90   │ │ R$ 65 de R$155 │   │
│ │ em 05/12       │ │ + 2 eventos    │   │
│ └────────────────┘ └────────────────┘   │
│ ┌────────────────┐ ┌────────────────┐   │
│ │ OBJETIVOS      │ │ PROJETOS       │   │
│ │ 3 em andamento │ │ App Pitch      │   │
│ │ Fechar 2º sem. │ │ 1 tarefa       │   │
│ │ ▓▓▓░░░ 33%     │ │ atrasada       │   │
│ │ prazo 18/12    │ │                │   │
│ └────────────────┘ └────────────────┘   │
│ Mercado · Rotina · Gráficos (opcionais)  │
│                          mais áreas ⌄   │
└──────────────────────────────────────────┘
```

O que mudou em relação à Início atual: a camada 1 cabe em uma tela; a fatura vencida e a tarefa atrasada **aparecem uma vez**, no topo; o dinheiro é **um** cartão (e não resumo + pagamentos + dois gráficos); objetivos e metas deixam de dividir moldura; o que sobra vive nos módulos, a um toque.

### F.6 Os blocos, um a um

| Bloco | Pergunta | O que mostra | O que fica só no módulo | Toque |
| --- | --- | --- | --- | --- |
| **Agora** | O que está acontecendo ou vem a seguir? | Compromisso em curso ou próximo; evento do dia; tarefa com hora | Agenda completa | Item → ficha |
| **Atenção** | O que precisa de mim antes de virar problema? | Até 3 itens ordenados por urgência: fatura ou conta vencida, tarefa atrasada, conflito de horário, orçamento estourado, evento próximo com pendência, alerta de mercado disparado. Cada item traz **uma** ação | Lista completa de atrasos e alertas | Item → ficha; ação → concluir ou abrir |
| **Para hoje** | O que eu faço hoje? | Tarefas que vencem hoje e ainda não foram reivindicadas; prioridade alta sem data pode entrar com marca própria, não com o nome “de hoje” | Todas as tarefas | Círculo conclui; corpo → ficha |
| **Dinheiro** | Como estou no mês e o que vence? | Resultado do mês; saldo informado (rotulado como informado); próximo vencimento | Gráficos, lançamentos, cartões, orçamentos | Cabeçalho → Finanças; item → ficha |
| **Eventos** | O que está chegando e como está a preparação? | Próximo(s) evento(s) com data e situação de ingresso e custo | Lista de eventos, tarefas e compromissos do evento | Cabeçalho → Eventos; evento → ficha |
| **Objetivos** | Estou avançando no que importa? | Objetivos em andamento com progresso e prazo; **sem** Metas financeiras | Marcos, histórico | Objetivo → ficha |
| **Projetos** | Que iniciativas estão ativas e como estão? | Projetos ativos com progresso e uma frase de situação | Tarefas do projeto | Projeto → ficha |
| **Metas financeiras** | Quanto falta para cada valor a juntar? | Opcional, junto do cartão de Dinheiro | Cadastro e edição | Meta → ficha |
| **Mercado, Rotina, Gráficos** | Opcionais, desligados por padrão | Cotações favoritas; cartão contextual “Encerrar o dia” em horário configurável; gráfico único e compacto | Tudo o mais | Cabeçalho → módulo |

### F.7 Rituais, tablet e o que depende de quê

- **Rituais:** o Assistente de Rotina aparece como **cartão contextual** na camada 1 no horário configurado (manhã: Briefing; noite: Encerrar o dia). O histórico e as configurações continuam no módulo \[proposta\].
- **Tablet:** três colunas — Agora e Atenção · Para hoje com o dia da Agenda · grade de Áreas —; o toque num item abre a ficha em um painel à direita sem tirar a Início da tela (seção J).
- **Dependência técnica:** o que proponho é **seleção, ordenação e deduplicação no cálculo do snapshot** e nova apresentação; em princípio não exige dado novo \[inferência\]. Mexer nessas regras, porém, é mexer em lógica de negócio da Início e pede testes \[pendente\].

