# Claude — Adaptação, alternativas e fluxos

> **Origem:** extração temática fiel da proposta completa do Claude, preservada sem reescrita substantiva.  
> **Estado:** PROPOSTA / EM ANÁLISE. Este material não representa decisão aprovada nem autorização de implementação.  
> **Fonte integral preservada:** `NEXUS — Proposta de Arquitetura da Informação e Experiência (2).md`. Consulte a fonte original para contexto contínuo e referências.

---

## 7. Fluxos de uso ponta a ponta

Cada fluxo abaixo testa se a arquitetura realmente conecta as áreas.

1. **Abrir pela manhã e entender o dia.** Abre em Hoje. Lê a frase do dia, “Agora e a seguir” (primeiro compromisso às 9h), “Atenção” (uma fatura vence amanhã; um conflito na quinta) e “O dia” (blocos e três tarefas para hoje). Marca uma tarefa como feita ali mesmo; toca na fatura, vê a composição e marca como paga — ou deixa para depois. Custo: zero a dois toques, sem trocar de destino.
2. **Criar uma tarefa simples.** Em Tarefas (ou Hoje): + → título → (opcional) chip “Hoje” → Salvar. Se tiver data, aparece na Agenda; se não, fica em Tarefas, em “Sem prazo”.
3. **Tarefa de projeto com gasto.** Planos › Projetos › o projeto → “+ Tarefa” (já nasce vinculada) → título e prazo → Salvar. Na ficha da tarefa, bloco Dinheiro → “Registrar gasto” → valor e categoria. Resultado: o projeto passa a mostrar o custo, o lançamento mostra o chip “Tarefa · Projeto”, e o prazo da tarefa aparece na Agenda. Um registro feito uma vez, visto em quatro lugares.
4. **Consultar uma semana e perceber um conflito.** Agenda › Semana. A linha-resumo mostra “1 conflito na quinta”; tocar leva ao dia com os dois blocos sobrepostos destacados; tocar num deles abre a ficha, onde dá para reagendar. (Quem nem abriu a Agenda já teria visto o conflito em Atenção, no Hoje.)
5. **Planejar um evento.** Planos › Eventos › + → nome, datas, local → ficha. Ingresso: pago, em três vezes no cartão → gera as obrigações nas faturas. Custos: + transporte, + alimentação. Preparação: + tarefa “Comprar capa de chuva” (já vinculada) e + compromisso (hoje parcial). A Agenda mostra a faixa dos dias do evento e os prazos das tarefas de preparação.
6. **Entender o impacto financeiro de uma viagem.** Planos › Eventos › a viagem › Custos: total previsto, pago e a pagar, por tipo, e “quando pesa” mês a mês (parcelas do ingresso nas faturas de dezembro a fevereiro; hospedagem em janeiro). Tocar num mês abre Dinheiro › Comprometido naquele mês, com a composição. Alternativa já possível: Dinheiro › Lançamentos filtrando por evento. \[A visão “quando pesa” é novo · apresentação e depende de o vínculo evento → lançamento permitir somar por mês e por fatura.\]
7. **Consultar um cartão.** Dinheiro › Cartões › o cartão → limite disponível; fatura atual (vence dia 10 e aparece na Agenda); seletor Futuras → faturas dos próximos meses com as parcelas. Para o horizonte inteiro: Dinheiro › Comprometido, com a parte do cartão destacada; tocar em março mostra “parcela 4/10 …” e de onde vem. Responde às duas perguntas: o que vence e o que continuará pesando.
8. **Acompanhar um objetivo.** Planos › Objetivos › o objetivo → progresso com a origem do número (“calculado a partir de 2 projetos, 5 tarefas e 3 marcos”); Escada em árvore; marcos com datas; histórico. Tocar num projeto da escada abre a ficha dele; Voltar retorna ao objetivo. Pode alternar para progresso manual.
9. **Encontrar sem lembrar o módulo.** Lupa → “dentista” → resultados agrupados: Compromisso (12/12, 14h) · Tarefa “Ligar para o dentista” · Lançamento “Dentista R$ 200”. Toca no compromisso → ficha → Voltar → a busca continua lá, com a consulta.
10. **Consultar o Mercado e acompanhar um ativo.** Dinheiro › Mercado › Acompanhamento (favoritos, “atualizado há 12 min”). Toca no dólar → detalhe com gráfico de um mês; marca para acompanhar; cria alerta acima de um valor. Quando dispara: notificação → abre o ativo; o Hoje registra em Atenção. Sem conexão: último cache, com aviso claro.
11. **Revisão do dia e reorganização de pendências.** À noite, o Hoje mostra “Encerrar o dia”. Passo 1: o que foi feito. Passo 2: três pendências — adiar duas para amanhã às 9h (em lote), reagendar uma para sexta. Passo 3: prévia de amanhã. Concluir → frase de encerramento (opcional) → registro no Diário.

**O que os fluxos mostram.** Os fluxos 3, 5, 6 e 7 cruzam três domínios com um único cadastro; os fluxos 1, 4 e 11 mostram Hoje e Atenção como tecido conectivo; os fluxos 9 e 10 mostram que Busca e Mercado têm caminho próprio sem disputar a barra.

## 8. Adaptação para celulares e tablets

- **Princípio:** uma arquitetura só — mesmos cinco destinos, mesmas fichas, mesmo vocabulário. O que muda é *quantos painéis cabem*. A decisão segue a **classe de tamanho da janela** (compacta, média, expandida), não “ser tablet”; assim funciona também em janela dividida, dobráveis e rotação. Nas classes compacta e média cabe um painel; na expandida, dois ou mais — é a orientação oficial do Android.
- **Navegação:** barra inferior na janela compacta; rail nas demais. No rail: símbolo N e Criar estendido no topo, os cinco destinos, o campo de busca e a Central embaixo — mesma ordem e mesmos ícones em todos os formatos.
- **Lista–detalhe:** Tarefas, Planos, Lançamentos, Cartões, Mercado e Busca.
- **Principal + apoio:** Agenda (calendário + dia + ficha), Hoje (feed + ficha), Planos (ficha + Escada ou Custos).
- **Grade de blocos:** Hoje e Dinheiro › Visão, para ver vários resumos de uma vez e **comparar** (gráficos lado a lado).
- **Contexto e comparação:** a ficha abre ao lado, não por cima; categorias e fluxo de caixa ficam juntos; objetivo, escada e custos aparecem lado a lado; o Kanban mostra todas as colunas.
- **Formulários:** folha lateral ou diálogo central em telas largas — nunca uma tela de celular esticada.
- **Estado:** dia selecionado, filtros, ficha aberta e posição de rolagem sobrevivem a rotação, dobra e janela dividida. É pré-requisito: o dossiê registra a perda de estado na rotação como pendência, e por isso a fundação é a primeira fase do plano.

| Destino | Celular | Tablet retrato | Tablet paisagem |
| --- | --- | --- | --- |
| Hoje | Coluna única; Radar recolhido | Duas colunas; ficha em painel lateral | Três colunas; gráficos do Radar sempre visíveis |
| Agenda | Grade + lista do dia; semana em tira | Calendário + dia; semana de sete colunas | Calendário · dia · ficha |
| Tarefas | Lista; quadro em abas por status | Lista + ficha; quadro de três colunas | Quadro de seis colunas, ou lista + ficha |
| Planos | Visão em faixas; lista → ficha | Lista + ficha | Lista · ficha · apoio (Escada ou Custos) |
| Dinheiro | Abas rolantes; cartões verticais | Visão em duas colunas; lista + ficha | Grade com gráficos lado a lado; cartão · fatura · compras; horizonte · composição |
| Mercado | Lista → detalhe | Lista + detalhe | Lista + detalhe + comparação (futuro) |
| Busca | Tela cheia | Resultados + prévia | Campo permanente + resultados + ficha |

## 9. Trade-offs e riscos

**O que melhora:** a barra passa a ter um critério único; a escada de intenção fica visível; as opções adicionais deixam de ser gaveta; a duplicação entre Hoje e Assistente desaparece; a densidade do Hoje cai sem tirar função; as conexões ficam legíveis; o tablet vira ganho real de contexto.

**O que pode piorar ou custar:**

1. **Eventos perde a aba própria.** Para quem vive de eventos, é um toque a mais. Mitigação: Planos abre na última sub-área usada, atalho no ícone do app, presença em Hoje, Agenda e Busca. Se o uso real mostrar Eventos como domínio diário, reavaliar.
2. **“Planos” é um nome abstrato** e pode demorar a ser descoberto. Testar com uso real (alternativas: Projetos, Rumos).
3. **Mercado dentro de Dinheiro** pode sugerir carteira de investimentos. Mitigação: alternador explícito, cabeçalho “informativo” e estética própria; quem consulta muito usa favoritos no Radar ou o widget futuro.
4. **Dissolver o Assistente no Hoje** pode inchar o Hoje. Mitigação: esqueleto fixo, Radar configurável, “Encerrar o dia” como fluxo à parte. Se pesar, Diário e Rotina voltam como entrada própria na Central.
5. **Dinheiro continua denso:** Visão + quatro seções + Mercado. As camadas ajudam, mas Comprometido depende de parcelas, recorrências e empréstimos serem agregáveis por mês \[hipótese\].
6. **Separar Projetos de Tarefas** esconde “as tarefas deste projeto” dentro de Tarefas. Mitigação: filtro por projeto e chip em cada tarefa.
7. **Ficha única e “visões leem, domínios escrevem”** exigem uma pilha de voltar confiável; sem isso, encadear fichas confunde.
8. **Esqueleto fixo do Hoje** tira liberdade de quem já ordenou todos os blocos. Mitigação: Radar totalmente configurável; Atenção e O dia recolhíveis.
9. **Camadas da Agenda** são mais abstratas que “Tudo / um tipo”. Manter “Tudo” como padrão e nomes simples.
10. **Conceitos que ficam mais difíceis de achar:** Backup (Central › Dados — mitigado pelo selo de último backup), Diagnóstico (Central › Suporte, de propósito), Metas financeiras (Dinheiro › Orçar).
11. **Custo oculto:** a base de navegação (pilha por destino, estado persistente, painéis) é a maior mudança e a menos visível.
12. **Risco de reformar tudo de uma vez.** Por isso o plano gradual, a seguir.

**O que exige funcionalidade nova:** Comprometido (se os dados não permitirem), selo e lembrete de backup, frases por momento, fixar widget, widget de Mercado, comparação no Mercado.

## 10. Plano gradual de evolução

A ordem segue risco crescente: primeiro o invisível e o organizacional (reversíveis), depois a navegação, depois a apresentação e, por último, o que mexe em dados. Cada fase é pequena o bastante para um pedido único à ferramenta de construção, termina com um teste no aparelho e funciona sem a seguinte. Como cada mudança de banco exige backup e restauração cuidadosos, as mudanças de dados devem ser agrupadas no menor número possível de versões. Num contexto de plano gratuito e limites de uso, a ordem também evita retrabalho: cada fase aproveita a anterior e nenhuma pede reescrever o app.

| Fase | O que muda | Tipo |
| --- | --- | --- |
| 0. Verificar | Abrir o app e confirmar as \[hipótese\]; anotar o que mais se usa; decidir as questões da seção 11 | Sem código |
| 1. Fundação | Voltar sobe um degrau; cada destino guarda seu estado; o estado sobrevive à rotação. Direção técnica possível: Navigation 3 (estável desde novembro de 2025, com a pilha de telas sob controle do próprio app) e a barra/rail adaptativa do Material 3 — sugestão, não imposição | Navegação (base técnica) |
| 2. Organização | Central do NEXUS (Frases, Widgets, Configurações por assunto, Dados, Suporte); Mercado vira alternador em Dinheiro; Projetos sai das abas de Tarefas; Atrasadas e Concluídas viram seção e filtro; ajustes de nomenclatura (4.5) | Puramente organizacional |
| 3. Navegação principal | Planos (Visão · Objetivos · Projetos · Eventos) no lugar de Eventos na barra; Busca na barra superior; Criar contextual com toque longo | Navegação |
| 4. Hoje | Esqueleto (Cabeçalho, Agora e a seguir, Atenção, O dia) + Radar; Encerrar o dia e Diário substituem a entrada do Assistente; gráficos viram opção do Radar | Apresentação (dados que já existem) |
| 5. Conexões | Chips de origem; faixa de Conexões; ficha única; Agenda com blocos, faixas, prazos e camadas | Apresentação |
| 6. Criação | Captura rápida com título primeiro; editores em quatro blocos; criação em contexto | Apresentação |
| 7. Tablets | Rail; lista–detalhe e principal + apoio por destino; formulários em folha lateral | Navegação + apresentação |
| 8. Dinheiro em profundidade | Visão e Análises, Cartões com seletor Atual/Futuras, Orçar; **Comprometido**; perguntas “No cartão? Parcelar? Repete?” | Apresentação; **dados/regra** onde a agregação ou o fluxo único não existir |
| 9. Evoluções | Selo e lembrete de backup; frases por momento; fixar widget; widget de Mercado; comparação no Mercado; completar compromissos em eventos \[parcial\]; recorrência de compromissos e eventos; backup ZIP com imagens | Funcionalidades novas |

Resumo por natureza: **organizacionais** — fase 2; **de navegação** — fases 1, 3 e 7; **de apresentação** — fases 4, 5, 6 e parte da 8; **que exigem funcionalidade nova** — fase 9 e partes da 8; **que exigem alteração de regras ou dados** — somente onde a fase 8 esbarrar nos limites do modelo atual, e por isso ficam por último.

## 11. Questões em aberto que precisam de decisão humana

1. **Uso real.** Quais destinos você abre mais por semana, e quanto Eventos pesa? Se Eventos for diário, a posição dele (e a de Planos) muda.
2. **Nome do quarto destino.** “Planos”, “Projetos” ou outro? Afeta a descoberta de Objetivos e Eventos.
3. **Mercado dentro de Dinheiro.** Aceitável, ou prefere na Central? Com que frequência você consulta?
4. **Esqueleto fixo do Hoje.** Aceita personalizar só o Radar? Os gráficos devem ficar fora do Hoje por padrão?
5. **Assistente de Rotina.** Dissolver em Hoje (Briefing = Hoje; Revisão = “Encerrar o dia”) ou manter uma entrada própria?
6. **Busca.** Quais tipos ela cobre hoje? Define se “achar sem lembrar o módulo” já é verdade.
7. **Comprometido.** Os dados de parcelas, recorrências, empréstimos e faturas permitem projetar os próximos meses? Se não, a seção vira trabalho de dados e regra.
8. **Custo de evento por mês.** O vínculo evento → lançamento permite somar por mês e por fatura?
9. **Objetivo × meta financeira.** Devem se conectar (um objetivo conter uma meta de dinheiro como marco) ou ficar separados e apenas bem nomeados?
10. **Compromissos dentro do evento.** Completar o fluxo \[parcial\] antes ou depois da reorganização?
11. **Parcelamento e recorrência no fluxo do lançamento.** As regras permitem nascer do mesmo editor, ou há diferenças que exigem telas separadas?
12. **Frases por momento.** Vale marcar frases (manhã, noite) ou o sorteio atual basta?
13. **Critério de Atenção.** Aceita “o que notifica é o que aparece em Atenção”? Quais itens entram?
14. **Lacunas do dossiê.** Ele não descreve gestos existentes (deslizar para concluir?), o comportamento do botão de criação atual, o conteúdo exato do Diagnóstico e da Busca, nem o desempenho em telas grandes. Tratei tudo isso como \[hipótese\]; cada ponto pode ajustar detalhes de 6.3, 6.4, 6.8 e 6.10, mas não muda a arquitetura central.

## Referências consultadas

- Android Developers — Build adaptive navigation: https://developer.android.com/develop/adaptive-apps/guides/build-adaptive-navigation
- Android Developers — Adaptive layouts in Compose: https://developer.android.com/develop/ui/compose/layouts/adaptive
- Android Developers — Layout and navigation patterns: https://developer.android.com/design/ui/mobile/guides/layout-and-content/layout-and-nav-patterns
- Android Developers Blog — Jetpack Navigation 3 is stable: https://developer.android.com/blog/posts/jetpack-navigation-3-is-stable

---


---

### J.1 Princípio

A **ficha é o mesmo conteúdo em recipientes diferentes**: tela cheia no celular, painel lateral em telas largas, folha em contextos de espiar. O app já decide pela **largura disponível** (≥ 720 dp: grades de duas colunas; ≥ 840 dp com altura ≥ 480: dois painéis na Agenda e no detalhe de Mercado) \[doc\]. Mantenho essa regra como base, a calibrar.

### J.2 Por módulo

| Módulo | Celular | Tablet retrato | Tablet paisagem |
| --- | --- | --- | --- |
| **Início** | Coluna única: camada 1, depois Áreas em grade de duas colunas | Duas colunas (Agora e Atenção · Áreas); a ficha desliza num painel à direita | Três colunas (dia · atenção e hoje · áreas), ficha em painel; a camada 1 fica sempre visível |
| **Agenda** | Mês compacto + lista do dia; semana em carrossel (como hoje) | Os sete cartões da semana lado a lado | Os dois painéis atuais; a ficha abre sobre o painel direito, ou como terceiro painel só em larguras muito grandes |
| **Tarefas** | Lista; quadro com colunas roláveis | Lista e ficha lado a lado | Lista e ficha; no quadro, ficha como folha lateral |
| **Eventos** | Cartões expressivos em coluna | Grade de duas colunas e ficha ao lado | Grade compacta, ficha completa à direita |
| **Finanças** | Visão em cartões; extrato em linhas | Visão em duas colunas; extrato e ficha | Visão em grade (os dois gráficos lado a lado, já existente); extrato · ficha; cartão · fatura · compras |
| **Planejamento (Objetivos)** | Lista; ficha em tela cheia | Lista e ficha | Lista · ficha com marcos · escada |
| **Mercado** | Lista e detalhe | Dois painéis (já existente) | Dois painéis, com espaço para comparar ativos |
| **Busca** | Tela cheia | Resultados e prévia | Campo fixo, resultados e ficha |

### J.3 Regras

1. **Mais painéis, não cartões maiores:** em tablet se ganha contexto simultâneo (lista e ficha, calendário e dia), não elementos esticados.
2. **A ficha aberta como painel não apaga a lista** nem a posição nela.
3. **Estado preservado** ao girar, dobrar ou dividir a tela: ficha aberta, seleção, filtro e rolagem. O dossiê registra a perda de estado na rotação como pendência \[doc\], e isto é **pré-requisito** de qualquer ficha em painel.
4. **Editores em folha lateral ou diálogo central** nas telas largas, nunca em tela cheia esticada.
5. **A decisão é pela janela, não pelo aparelho**; vale para janela dividida e dobráveis.
6. **Nem todo módulo usa a mesma estrutura:** o Mercado já tem a sua; a Início usa grade; a Agenda tem eixo temporal.

### J.4 Dependências

A ficha precisa existir primeiro (sem ficha não há lista–detalhe); o estado por aba e a preservação na rotação vêm antes de painéis; a pilha de fichas é nova capacidade de navegação \[pendente\].

## K. Alternativas e recomendações

Concentro o esforço nas decisões que realmente mudam a experiência: como consultar, como organizar a Início, como mostrar relações e como compor a linguagem visual. A arquitetura de destinos já foi comparada em E.2 a E.4.

### K.1 Como consultar um registro

|  | **Ficha como tela** | **Folha inferior de consulta** | **Só expansão no lugar** | **Recomendação: ficha como tela, curta e padronizada** |
| --- | --- | --- | --- | --- |
| Como funciona | Toque no corpo abre uma tela de leitura com pilha de retorno | Toque abre uma folha que cobre parte da tela; arrasta para tela cheia | Toque no cartão revela os detalhes no próprio lugar | Igual à primeira; em tablet vira painel |
| Problema que resolve | Consulta, relações e navegação em um só lugar | Consulta rápida sem sair da lista | Espiar sem trocar de tela | Os três primeiros do item I |
| Benefícios | Espaço para relações; um padrão único; boa para tablet | Mantém o contexto da lista; rápida | Mínimo de toques | Simples de ensinar e de testar |
| Limitações | Troca de tela a cada consulta; exige pilha de retorno | Dois recipientes para o mesmo conteúdo; a navegação por relação dentro da folha fica ambígua | Não comporta relações ricas nem edição; vira pilha de cartões | Para tarefa e lançamento, a tela de leitura precisa ser curta (cabe sem rolagem) |
| Complexidade | Média (rota nova e pilha) | Média a alta (gestos, estados) | Baixa a média | Média |
| Pressupostos | Estado preservado por aba e na rotação | Mesmos, mais gestos de folha | Poucos filhos por registro | Mesmos da primeira |
| Quando é adequada | Quando se quer previsibilidade | Quando o contexto da lista importa muito | Só para contêineres com poucos filhos | Início do trabalho |

**Por que não escolhi a folha inferior como padrão:** ela serve bem para espiar, mas, ao tocar numa relação dentro dela, o app ou empilha folhas (o que você não quer) ou troca de tela (o que apaga o benefício da folha). Fica como **refinamento a testar** em tarefa e lançamento, depois que a ficha-tela existir \[proposta\]. A expansão no lugar fica restrita aos contêineres (I.3).

### K.2 Como organizar a Início

|  | **Atenção + Radar** (Parte I, ajustada) | **Mosaico de áreas** (a analogia do Windows) | **Linha do dia** | **Recomendação: híbrida em duas camadas** |
| --- | --- | --- | --- | --- |
| Como funciona | Faixa fixa de atenção e depois blocos configuráveis | Cartões de tamanhos iguais, um por área, cada um com um destaque | A Início é uma linha do tempo do dia, com o resto ao lado | Camada 1 (agora, atenção, hoje) + camada 2 (áreas em cartões de resumo) |
| Problema que resolve | Excesso de blocos | Acesso a tudo com informação real | Leitura temporal natural | Decisão imediata e panorama |
| Benefícios | Foco | Panorama rápido, atalho em cada cartão | Contexto do dia | Prioriza e informa |
| Limitações | Esqueleto fixo tira a personalização atual | Cartões de mesmo peso não priorizam; a urgência se perde; repete o problema das listas se cada cartão virar lista | Tarefas sem data, objetivos e dinheiro não são tempo | Precisa de regras de relevância e deduplicação |
| Complexidade | Média | Média | Média a alta | Média (regras de seleção, sem dado novo) |
| Pressupostos | A Home é só atenção | O usuário quer navegar a partir da Início | O dia é o eixo | Regras de relevância aceitáveis e explicáveis |
| Quando é adequada | Para quem só quer o urgente | Para quem usa a Início como menu | Para quem vive de agenda | Uso misto: rápido e exploratório |

### K.3 Como mostrar relações

|  | **Chip com ícone e nome** (recomendado) | **Cor herdada** | **Mapa relacional (constelação)** |
| --- | --- | --- | --- |
| Como funciona | Cada vínculo é um chip tocável, só quando existe | A cor de um contêiner aparece nos filhos | Visualização em grafo sob demanda |
| Benefícios | Reconhecível sem cor; navegável; funciona com vários vínculos | Reconhecimento imediato de poucos contêineres | Mostra a rede de uma só vez; atrativo |
| Limitações | Ocupa espaço | Colide com estados; ruído com vários vínculos; acessibilidade | Caro, difícil de ler, pouco útil no cotidiano |
| Veredito | Principal | Só em superfícies grandes e com paleta separada | **Exploração futura**, não navegação principal (a comparação crítica sugeriu o mesmo caminho) |

### K.4 Linguagem visual

|  | **Fundação sóbria em camadas** (recomendada) | **Tema forte por domínio** | **“Luma-lite” uniforme** |
| --- | --- | --- | --- |
| Ideia | Consolidar semântica e hierarquia, depois componentes, depois expressão | Cada módulo com paleta e composição próprias | Aplicar o mesmo estilo expressivo a tudo |
| Benefício | Reduz o risco de decorar ambiguidade | Personalidade forte | Consistência fácil |
| Limitação | Resultado visível mais lento | Fragmenta a identidade | Perde a densidade das tarefas e a sobriedade |
| Veredito | **Recomendada** | Rejeitar | Rejeitar |

### K.5 Tablet

|  | **Painéis conforme o fluxo** (recomendado) | **Mesma estrutura em tudo** | **Cartões maiores** |
| --- | --- | --- | --- |
| Ideia | Lista–detalhe onde há registros; calendário + dia; grade de resumos na Início | Barra lateral e dois painéis em todos os módulos | Ampliar a interface de celular |
| Veredito | **Recomendado** | Parcial: não serve para a Início nem para o Mercado | **Rejeitar** |

### K.6 Recomendações consolidadas

| Decisão | Recomendação | Confiança | O que me faria mudar |
| --- | --- | --- | --- |
| Destinos | Partir das cinco abas atuais; Mais reorganizado em grupos; Mercado fora de Finanças, com seu ícone | Média | Dados de uso; teste de agrupamento |
| Consulta | **Ficha como tela**, curta e padronizada; folha como refinamento | Média a alta | Teste de uso rápido em tarefas e lançamentos |
| Toque | Corpo abre ficha; círculo conclui; lápis edita; relação navega | **Alta** | — |
| Início | Híbrida em duas camadas, com regras de relevância e deduplicação; personalização preservada | Média a alta | Medição do teste dos dez segundos |
| Relações | Chips com ícone e nome; derivados rotulados | Média a alta | Teste de leitura com três vínculos |
| Cor de vínculo | Não usar como mecanismo principal | Média | Teste com paleta de identidade separada |
| Semântica | Auditar antes de qualquer cor nova; separar estado de prioridade | **Alta** | — |
| Capas de evento | Expressão da camada 3, depois da fundação; exigem dado novo | Média | Frequência de eventos com imagem |
| Tablet | Painéis por fluxo, depois das fichas e do estado preservado | Média a alta | Teste em aparelho |

## L. Fluxos completos

Uso registros que aparecem **no vídeo** como exemplos; **os vínculos entre eles não aparecem no vídeo**, então descrevo cada fluxo “se o vínculo existir”.

### Cenário A — Evento

**Começa:** na Início, camada 2, cartão **Eventos**: “Hackathon FinTech Poli 2026 · 04/12 · dia todo · Ingresso: pago · R$ 65 de R$ 155”.

1. **O que aparece:** nome, data, estado do ingresso com rótulo, custo pago e total em barra. Se o evento tem mais de três tarefas, o cartão traz a seta de expansão.
2. **Toca na seta:** o cartão se expande no lugar, desloca o que está abaixo e mostra três tarefas com círculo de conclusão e “+N”.
3. **Conclui uma tarefa:** toca no círculo; confirmação visual e “Desfazer”; o contador do cartão se atualiza.
4. **Toca no corpo do cartão (ou em “abrir ficha”):** abre a **ficha do evento**: identidade, período e local, custos por tipo, preparação, compromissos e “projetos com tarefas neste evento” (derivado).
5. **Toca no chip do projeto:** navega à **ficha do projeto** (empilhada). A barra mostra “← Hackathon FinTech Poli”.
6. **Volta:** retorna à ficha do evento, na mesma rolagem; **volta de novo:** retorna à Início, com o cartão recolhido (a expansão não persiste na Início).

**Ações disponíveis:** concluir tarefas, adicionar tarefa ou gasto já vinculados ao evento, lápis para editar, excluir no menu.

### Cenário B — Projeto

**Começa:** na Início, cartão **Projetos**: “App Pitch — Hackathon FinTech Poli · 1 tarefa atrasada”.

1. **Toca:** abre a **ficha do projeto**: o texto de descrição como propósito; estado; prazo; progresso (tarefas concluídas de N); objetivo associado, se houver; custo estimado e pago.
2. **Tarefas:** lista com a atrasada primeiro (“Preparar deck de 5 slides para Mentoria Pitch · hoje 12:00 · 1/3 subtarefas”), cada uma com círculo próprio.
3. **Toca na tarefa:** abre a **ficha da tarefa** (empilhada), com o chip do projeto omitido por ser redundante.
4. **Volta:** retorna ao projeto, com o filtro e a rolagem de antes.

**Limite:** “marcos” e “participantes” **não existem** no projeto \[código\]; a ficha não os mostra.

### Cenário C — Tarefa

**Começa:** na Início, em **Para hoje**: “Estudar para P2 de Cálculo Numérico · 26/11 08:00 · em andamento · 1/2 subtarefas”.

- **Concluir direto:** toca no círculo. Se a regra de conclusão com subtarefas pendentes pedir confirmação, o app a pede \[pendente: o código atual não verifica subtarefas\].
- **Consultar:** toca no corpo e abre a ficha curta: estado, prazo, prioridade por forma, subtarefas (uma marcada), contexto (projeto, evento ou objetivo, se houver), gasto.
- **Atualizar o status:** na ficha, os botões “Concluir”, “Adiar” e “Mudar status” resolvem sem abrir o editor.
- **Editar campos:** lápis na ficha; ao salvar, volta à ficha.
- **Navegar:** toca no chip do projeto e vê a ficha dele e as outras tarefas; Voltar retorna à tarefa.

### Cenário D — Dinheiro

**Começa:** na Início, no cartão **Dinheiro** (“próx.: R$ 90 em 05/12”), ou pela Atenção.

1. **Toca no próximo vencimento:** abre a **ficha do lançamento** “Estimativa alimentação Hackathon · −R$ 90,00 · vence 05/12/2026 · Previsto”.
2. **O que revela:** categoria e natureza (despesa, custo de evento); o **evento** relacionado, se houver, como chip; fatura ou parcela, se estiver no cartão; **impacto derivado** no orçamento da categoria \[pendente: confirmar o cálculo\]; detalhes recolhíveis (forma de pagamento, notas).
3. **Navega:** toca no chip do evento e vê o custo total e pago; Voltar retorna ao lançamento.
4. **Decide editar:** toca no lápis; o editor abre como camada; ao salvar, **volta à ficha**, já atualizada. Se só quer ver, Voltar leva à Início.

### Cenário E — Revisão da situação

**Começa:** o app abre às 20h53 na Início.

- **Em dez segundos** a pessoa lê, sem rolar: **Agora** (“amanhã 11:00 Check-in clínico USP”); **Atenção** (fatura de julho vencida, R$ 27,90; tarefa atrasada; o evento com o ingresso e o custo); **Para hoje** (uma tarefa).
- **Se quiser mais:** rola até **Áreas** e vê Dinheiro, Eventos, Objetivos e Projetos em resumo; cada cartão é um atalho.
- **Se quiser agir:** toca na fatura vencida, abre a ficha da fatura e vai à tela de Cartões para pagar \[ação existente\].
- **Se for o fim do dia:** o cartão “Encerrar o dia” aparece na camada 1 no horário configurado e leva à Revisão Noturna.

**O que isto prova:** o que antes eram cerca de 27 cartões vira uma leitura de uma tela e meia, sem apagar nenhuma informação: o que sai da Início continua a um toque no módulo.

