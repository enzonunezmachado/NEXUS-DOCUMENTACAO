# Claude — Visão, modelo mental e diagnóstico

> **Origem:** extração temática fiel da proposta completa do Claude, preservada sem reescrita substantiva.  
> **Estado:** PROPOSTA / EM ANÁLISE. Este material não representa decisão aprovada nem autorização de implementação.  
> **Fonte integral preservada:** `NEXUS — Proposta de Arquitetura da Informação e Experiência (2).md`. Consulte a fonte original para contexto contínuo e referências.

---

# NEXUS — Proposta de Arquitetura da Informação e Experiência

*Proposta independente do Claude · baseada no Dossiê v1.0 (24/11/2026) · sem implementação: é um raciocínio de organização, feito para ser comparado com outras propostas.*

## Como ler este documento

Para não misturar o que existe com o que eu sugiro, uso etiquetas:

- **\[atual\]** — o dossiê descreve como existente.
- **\[parcial\]** — o dossiê diz que está incompleto.
- **\[novo · apresentação\]** — só muda *como* se mostra algo que já existe.
- **\[novo · funcionalidade\]** — exige uma capacidade que o dossiê não descreve.
- **\[novo · dados/regra\]** — exige mexer em regras ou no modelo de dados.
- **\[hipótese\]** — só dá para confirmar vendo o app rodando. Eu tenho o dossiê, não o app.

Fora do dossiê, consultei apenas o guia oficial do Android sobre apps adaptáveis (barra de navegação que vira rail, classes de tamanho de janela, layouts lista‑detalhe e painel de apoio) e o anúncio da Navigation 3 estável. Os links estão no fim. Fóruns e Reddit de “vibe coding” não entram aqui: a decisão é de arquitetura, não de ferramenta; o que importa deles (passos pequenos e verificáveis) aparece no plano de transição.

## 1. Síntese da visão do NEXUS

O NEXUS não é um app de tarefas, nem uma agenda, nem um app de finanças. É um lugar onde a pessoa **recupera o contexto da própria vida** sem carregar tudo na cabeça. O que faz dele um produto — e não cinco apps colados — é que o mesmo fato da vida aparece por ângulos diferentes: um show é uma data (Agenda), uma ocasião com preparação (Evento), um gasto (Dinheiro) e talvez parte de um objetivo.

O problema que ele resolve não é *guardar* informação, é *juntá-la*: “o que tenho esta semana, quanto isso vai pesar, o que depende do quê”. Por isso a arquitetura precisa responder, com poucos toques, a quatro perguntas:

1. **E agora?** — o que importa hoje, o que exige atenção.
2. **Quando?** — o que está onde no tempo.
3. **O que fazer, e para quê?** — ação, entrega, intenção e ocasiões.
4. **Quanto?** — o que gastei, devo, comprometi, e o mundo financeiro ao redor.

Há dois ritmos de uso: o diário (olhar e seguir) e o profundo (investigar). A proposta serve ao primeiro por padrão e ao segundo por *descida de camadas*, nunca por esconder. Personalidade (sobriedade monocromática, frases discretas) e honestidade (estado vazio claro, origem do número) fazem parte da arquitetura, não são enfeite.

## 2. Classificação das áreas e mapa conceitual dos domínios

Em vez de agrupar por tela, agrupo pela *natureza* de cada coisa:

| Classe | O que é | Áreas do NEXUS |
| --- | --- | --- |
| Orientação | Ajuda a decidir o dia; não possui registros | Início, Assistente de Rotina, Widgets, notificações |
| Domínios | Onde os registros nascem e moram | Tarefas · Compromissos · Finanças (lançamentos, cartões e faturas, parcelamentos, recorrências, empréstimos, orçamentos, metas financeiras) |
| Contêineres de planejamento | Agrupam vários registros e dão sentido a eles | Objetivos · Projetos · Eventos |
| Lentes | Mostram sem possuir | Agenda (tempo) · Busca Global · Início |
| Mundo externo | Informação que não é da pessoa | Mercado |
| Personalidade | Dá voz ao produto | Biblioteca de Frases |
| Manutenção | Cuida dos dados e do app | Configurações · Backup e restauração · Exportação · Diagnóstico |
| Gestos transversais | Valem em qualquer lugar | Criar · Buscar · Vínculos e origem · Lembretes |

Cinco ideias sustentam o resto:

- **A Agenda é uma lente, não um domínio.** Só o compromisso nasce nela; tarefas, eventos, faturas e prazos de projeto são projeções de registros que moram em outro lugar. Isso explica por que ela integra tanto e por que não deve ter regras próprias sobre registros alheios.
- **Existe uma escada de intenção:** Objetivo (resultado que quero) → Projeto (entrega com começo e fim) → Tarefa (ação) → Passo (subtarefa). Hoje o degrau mais baixo é o mais visível e o mais alto fica escondido.
- **Evento é um terceiro tipo de contêiner**, ancorado no tempo. Objetivo, Projeto e Evento compartilham o mesmo esqueleto — *itens + datas + custos + progresso* — com ênfases diferentes. Reconhecer isso permite uma ficha com a mesma estrutura nos três.
- **Dinheiro tem três camadas:** o que *aconteceu ou está previsto* (lançamentos), o que está *comprometido adiante* (cartão, parcelas, recorrências, empréstimos) e o que se *planeja* (orçamentos, metas). Hoje a segunda camada é a mais difícil de enxergar.
- **O Assistente de Rotina é um ritual sobre o dia, não um módulo de dados.** Briefing e Revisão leem tarefas, agenda, eventos e finanças — exatamente o que o Início já lê.

Quem mora onde, e onde mais aparece:

| Registro | Mora em | Aparece também em |
| --- | --- | --- |
| Tarefa | Tarefas | Agenda (se tem data) · Projeto/Evento/Objetivo (se vinculada) · Início · Busca |
| Compromisso | Agenda | Início · Evento (se vinculado) · Busca |
| Evento | Planos › Eventos | Agenda (faixa de dias) · Dinheiro (custos) · Início |
| Projeto | Planos › Projetos | Agenda (prazo) · Objetivo · Dinheiro (custo) |
| Objetivo | Planos › Objetivos | Início (opcional) |
| Lançamento | Dinheiro › Lançamentos | Tarefa/Evento/Projeto (custo) · Agenda (conta direta não liquidada) |
| Compra no cartão | Dentro da fatura (Dinheiro › Cartões) | Nunca isolada na Agenda |
| Fatura | Dinheiro › Cartões | Agenda (vencimento) · Início (Atenção) |
| Ativo | Dinheiro › Mercado | Início (opcional) · alertas |
| Frase | Biblioteca de Frases | Início (cabeçalho) · fechamento do dia |

## 3. Diagnóstico da arquitetura atual

O dossiê pede equilíbrio: nem “tudo errado”, nem “a organização atual é a melhor”. O que **funciona e deve ser preservado**: o Início como abertura padrão; a Agenda como lente com a regra anti-duplicação (a fatura aparece, a compra individual não); atraso calculado separado do status; o Kanban como outra visão das mesmas tarefas; a recorrência como série; os blocos configuráveis; a transparência do Mercado sobre cache; o backup local.

O que vejo como problema ou oportunidade:

| # | Achado | Tipo | Base |
| --- | --- | --- | --- |
| 1 | A barra mistura naturezas: Início (orientação), Agenda (lente), Tarefas e Finanças (domínios), Eventos (contêiner). Não há critério único para “merece ser aba”. | Hierarquia / navegação | Dossiê |
| 2 | A escada de intenção está invertida: Tarefas tem aba, Projetos é uma aba *dentro* de Tarefas e Objetivos mora nas opções adicionais. | Hierarquia | Dossiê |
| 3 | Dentro de Tarefas convivem no mesmo nível visões (Lista, Kanban), filtros (Atrasadas, Concluídas) e outra entidade (Projetos). | Agrupamento | Dossiê |
| 4 | O menu de opções adicionais junta conteúdo (Mercado), gesto global (Busca), ritual (Assistente), domínio (Objetivos), personalidade (Frases), sistema (Widgets) e manutenção (Configurações, Backup, Diagnóstico). Quem procura “metas” não pensa em abrir esse menu. | Agrupamento / navegação | Dossiê |
| 5 | Início e Assistente de Rotina respondem à mesma pergunta (“como está meu dia?”) com dados muito parecidos. | Duplicação de responsabilidade | Dossiê; o grau de repetição é hipótese |
| 6 | O Início carrega análise (anel do mês, fluxo de seis meses, categorias) que repete telas de Tarefas e Finanças e disputa com o princípio “Home não é depósito”. | Densidade | Dossiê |
| 7 | “Meta” e “objetivo” aparecem três vezes: Objetivos, Metas financeiras e o bloco “Objetivos e metas”. | Nomenclatura | Dossiê |
| 8 | Compromissos dentro da ficha do evento são parciais; sem isso o ciclo preparação → participação não fecha. | Fluxo / integração | Dossiê (parcial) |
| 9 | A integração é interna, mas o dossiê não registra um padrão visual de “de onde vem / por que está aqui”. | Integração visual | Hipótese |
| 10 | Finanças concentra cerca de dez conceitos; “o que continuará pesando nos próximos meses” está espalhado entre cartões, parcelas, recorrências e empréstimos. | Densidade / agrupamento | Dossiê; a dispersão é hipótese |
| 11 | Criar exige escolher o tipo antes de escrever; são 14 editores, e compra parcelada e regra recorrente são editores à parte em vez de opções do lançamento. | Fluxo de criação | Dossiê; confirmar no app |
| 12 | Mercado não tem lugar conceitual: nem domínio pessoal, nem ferramenta. | Agrupamento | Dossiê |
| 13 | A navegação é por estado e o dossiê admite perda de estado na rotação. Sem pilha de “voltar” explícita e estado persistente por área, nenhuma arquitetura de vários painéis se sustenta. | Navegação (base técnica) | Dossiê |
| 14 | Backup é pilar da autonomia dos dados, mas divide o mesmo nível de Diagnóstico; Frases e Widgets têm valor e pouca descoberta. | Contexto | Dossiê |


---

# PARTE II — Segunda rodada: apresentação, consulta, relações e Home

*Escrita em 25/11/2026, sobre a pasta ATUALIZAÇÃO, o vídeo do aplicativo e o repositório do projeto. Revisa a Parte I onde indicado; onde as duas divergirem, vale a Parte II. Nenhuma proposta daqui é decisão aprovada e nada daqui é plano de implementação.*

## O que eu tive acesso — e o que não

Você pediu para eu dizer claramente o que consegui e o que não consegui ver. Está aqui, porque limita várias conclusões.

| Fonte | Como usei | Limite |
| --- | --- | --- |
| Pasta `CHATGPT/ATUALIZACAO/` (README e documentos 00 a 06) | Li integralmente | Descreve intenção e hipóteses; não aprova nada (estado EM ANÁLISE) |
| Comparação crítica das propostas de 24/11 | Li | É síntese analítica, não decisão |
| Dossiê v1.0 (24/11) | Li integralmente | Descrição textual |
| Vídeo `1000588831.mp4` (11 s, 480×800, às 20:53 de 24/11/2026) | Assisti quadro a quadro, dois quadros por segundo | **Só mostra a rolagem da tela Início.** Não há nenhum toque em registro, nenhuma outra tela, o topo da Início fica fora do vídeo e só vi o tema escuro |
| Capturas de tela | **Não recebi.** Só o vídeo chegou | Agenda, Tarefas, Finanças, Eventos, Planejamento e Mercado: não os vi renderizados |
| Repositório `PROJETO-NEXUS-` (branch main, baixado em 25/11/2026) e seus docs técnicos | Li as entidades Room, as rotas, a regra de voltar, o Design System, o `TaskRowCard`, o cálculo do snapshot da Início, partes de Agenda, Tarefas e Eventos | São 88 arquivos Kotlin; não li tudo, não compilei, não executei, e não sei se a branch main é a versão instalada no seu aparelho |

Consequência prática: o que digo sobre o **comportamento ao tocar** vem do **código**, não de imagem; o que digo sobre **aparência** vem do **vídeo** (só da Início); o que digo sobre Agenda, Eventos, Tarefas e Finanças vem do **código e da documentação**. Marquei cada afirmação com a origem.

**Etiquetas de evidência:** **\[vídeo\]** observado no vídeo · **\[código\]** verificado no repositório · **\[doc\]** registrado na documentação · **\[inferência\]** deduzido por mim · **\[proposta\]** sugestão minha · **\[pendente\]** precisa de inspeção ou decisão humana. Para intenções, uso **\[intenção\]** (expressa por você) e **\[hipótese\]** (ainda em investigação).

Sobre fontes externas: consultei brevemente a documentação do Material Design sobre cartões, folhas inferiores e painéis de apoio, só como orientação geral (o cartão como ponto de entrada para um detalhe). Não a tratei como regra.

## A. Síntese do entendimento atual do NEXUS

O NEXUS é um sistema pessoal para **reduzir carga mental**: a pessoa deve poder confiar que o que aconteceu, o que está acontecendo e o que vai acontecer está registrado, relacionado e legível, sem reconstruir de cabeça as ligações entre compromissos, tarefas, projetos, objetivos, eventos e dinheiro \[intenção\]\[doc\]. O valor do produto está nas relações; a dificuldade está em mostrá-las sem tornar tudo pesado. Em suas palavras, o desafio é fazer a complexidade parecer compreensível, não fazê-la desaparecer.

Sete características precisam orientar a experiência (leitura minha dos documentos 00 a 04):

1. **Confiança antes de efeito:** números e estados honestos — origem do número, frescor, “informado por você”.
2. **Clareza proporcional:** cada informação com o peso que sua natureza e frequência justificam.
3. **Profundidade progressiva:** leitura inicial simples; histórico, filtros e análises sempre acessíveis.
4. **Integração perceptível:** relações visíveis quando ajudam a decidir ou agir, e só então.
5. **Previsibilidade das ações:** concluir, consultar, expandir, editar, navegar e excluir nunca se confundem.
6. **Personalidade sóbria e expressiva:** base preta, branca e grafite com cor, capa e composição usadas com propósito.
7. **Funcionar em celular e tablet** como composições diferentes, não como a mesma tela esticada.

**Hipótese central desta rodada \[inferência, a validar\]:** o aplicativo tem um modelo de dados relacional rico e uma interface organizada por módulos, mas só **dois degraus** de apresentação de um registro — a linha ou cartão de lista e o editor. **Falta o degrau do meio: a ficha de consulta.** Quase tudo o que você descreveu (tocar abre edição, relações escondidas dentro de formulários, uma Home que tenta mostrar um pouco de tudo) parece derivar dessa lacuna. A evidência está em B.3: não existe rota de detalhe para tarefa, compromisso, evento, projeto ou lançamento; todos abrem editores. A única ficha de consulta que existe é a do ativo de Mercado.

## B. Diagnóstico da experiência atual

### B.1 O que o vídeo mostra

Uma rolagem contínua da tela Início, de cima para baixo (o início da página não aparece). A sequência de blocos é: (1) um cartão de compromisso de amanhã, provavelmente o “próximo compromisso” \[inferência; código: `nextAppointment`\]; (2) Compromissos de hoje; (3) Tarefas do mês, com anel de 71%; (4) alerta de Atenção financeira; (5) Tarefas atrasadas; (6) Prioridades de hoje; (7) Objetivos e metas; (8) Resultado do mês; (9) Próximos pagamentos; (10) Próximos eventos; (11) Visão financeira, com gráfico de seis meses e rosca por categoria; (12) o botão Personalizar painel. Contei cerca de **27 cartões e blocos** só nesse trecho \[inferência minha, por contagem manual\], o que representa pelo menos cinco alturas de tela de rolagem.

### B.2 Achados

| # | Achado | Evidência | Causa provável | Natureza |
| --- | --- | --- | --- | --- |
| 1 | Extensão: cerca de 27 cartões e pelo menos cinco telas de rolagem, sem contar o que fica acima do início do vídeo | \[vídeo\] | Nove tipos de bloco empilhados, cada um com sua própria lista e limite \[código: `DashboardBlockType`\] | Densidade |
| 2 | A mesma tarefa aparece duas vezes: “Preparar deck de 5 slides…” em Tarefas atrasadas e em Prioridades de hoje | \[vídeo\] | “Prioridades de hoje” inclui toda tarefa que vence hoje **ou** tem prioridade alta/urgente, sem excluir as já listadas como atrasadas \[código: `priorityTasksToday` e `overdueTasks` são cálculos independentes\] | Repetição |
| 3 | O nome do bloco não corresponde ao conteúdo: “Prioridades de hoje” traz prioridade alta de qualquer data; “Próximos eventos” traz o evento que terminou às 19h (visto às 20h53); “Próximos pagamentos” traz uma fatura vencida em julho | \[vídeo\] | Regras de filtro por estado, não por tempo: eventos entram se `endAt` for maior ou igual ao início do dia de hoje \[código\] | Honestidade da informação |
| 4 | Pagamentos fora de ordem: contas e faturas são duas listas concatenadas; a fatura vencida de julho aparece depois de contas de fevereiro de 2027. O contador diz 9 e há 8 linhas | \[vídeo\] | Contas ordenadas com atrasadas primeiro; faturas ordenadas só por vencimento; o contador soma as duas listas, que são cortadas por `maxItems` \[código\] | Ordem e contagem |
| 5 | Números repetidos: R$ 3.132,10 aparece no cartão Resultado do mês, no rodapé do gráfico e no centro da rosca; “Próximo venc.” repete a primeira linha de Próximos pagamentos; o alerta de fatura em atraso repete a fatura listada dez cartões depois | \[vídeo\] | Blocos independentes, sem deduplicação | Repetição |
| 6 | Objetivos e metas misturados: três Objetivos (barra branca, etiqueta “Em andamento”, prazo, marcos) e duas Metas financeiras (barra verde, valores em R$, sem etiqueta) na mesma moldura | \[vídeo\] | Um único bloco para duas entidades diferentes \[código: `DashboardBlockType.GOALS`\] | Nomenclatura / hierarquia |
| 7 | Hierarquia invertida: os títulos de seção (pequenos, cinza) são mais fracos que os títulos dos cartões (brancos); os links azuis têm rótulos diferentes (“Ver todas”, “Planejar”, “Finanças”, “Detalhes”) e levam a destinos de naturezas diferentes | \[vídeo\] | Estilo de `SectionHeader` | Hierarquia visual |
| 8 | Metadados truncados e mecânicos: “…Hoje, às 12:00 · 1/3 sub…” cortado em duas linhas; nos objetivos, “0/0 marcos · 6 tarefa(s) · 1 projeto(s)” | \[vídeo\] | `maxLines = 2` e texto montado por concatenação \[código: `TaskRowCard`\] | Densidade / acabamento |
| 9 | O botão flutuante cobre valores e percentuais alinhados à direita durante a rolagem | \[vídeo\] | Posição fixa do botão sobre a coluna de valores | Espaço sem proporção |
| 10 | Status ambíguo no evento: etiqueta verde “Pago” ao lado de “Custo total R$ 155,00 · Pago R$ 65,00” | \[vídeo\] | A etiqueta é o estado do **ingresso** \[código: `TicketStatus`\]; o cartão não diz isso | Significado do estado |
| 11 | Rótulos técnicos: “Fatura Nubank Universitário (2026-07)” ao lado de “Aluguel … (12/2026)” | \[vídeo\] | Formato ISO exposto ao usuário | Acabamento |
| 12 | Cores com mais de um significado: âmbar é “atenção/conflito” na documentação, mas também prioridade alta, ingresso “Pendente”, “Próximo venc.” e todos os itens financeiros da Agenda; azul é “em andamento” e cor de link | \[vídeo\]\[doc\]\[código: `PriorityIndicatorDot`, `statusColor`\] | Semântica distribuída sem inventário | Semântica visual |
| 13 | Rosca com duas laranjas próximas e a maior fatia em branco, parecida com o trilho vazio | \[vídeo\] | Cores de categoria sem garantia de distinção \[hipótese\] | Legibilidade |
| 14 | Dois gráficos no fim da Início repetem a tela Finanças, contra o princípio documentado de que a Home não vire painel de gráficos | \[vídeo\]\[doc\] | Bloco `CHARTS_INDICATORS` ativo | Densidade |
| 15 | A personalização só aparece no fim da rolagem (o dossiê documenta também um atalho no cabeçalho, que o vídeo não mostra) | \[vídeo\]\[doc\] | — | Descoberta |
| 16 | Relações existem mas não aparecem: o cartão de tarefa mostra só o projeto, como texto no meio dos metadados; evento e objetivo da tarefa ficam invisíveis; a ligação entre “Estimativa alimentação Hackathon” e o evento só se deduz pelo nome | \[vídeo\]\[código: `TaskRowCard`\] | Cartão pensado como linha, não como ponto de entrada | Relações não comunicadas |
| 17 | Itens já passados em listas “de hoje”: a monitoria das 14h às 15h30 aparece sem distinção às 20h53 | \[vídeo\] | Lista do dia inteiro sem estado temporal | Contexto |

### B.3 O que acontece ao tocar \[código\]

Esta é a parte mais importante da evidência, e vem do código porque o vídeo não mostra nenhum toque.

| Registro | Onde | Toque no corpo do cartão | Controle de conclusão | Observação |
| --- | --- | --- | --- | --- |
| Tarefa | Tarefas, Início | Abre o **editor** de tarefa | Círculo alterna a conclusão | O editor mostra despesas vinculadas e abre cada lançamento |
| Tarefa | Agenda | Abre o editor (ocorrência virtual de recorrência é materializada antes) | **Nenhum encontrado** | O emblema é um quadrado de 22 dp sem ação própria; o Design System o chama de “checkbox na Agenda” |
| Compromisso | Agenda, Início | Abre o editor de compromisso | — | — |
| Evento | Eventos, Início, Agenda | Abre o editor de evento | — | O editor reúne ingresso, financeiro e tarefas vinculadas |
| Lançamento | Finanças, Início, Agenda | Abre o editor de lançamento | — | — |
| Projeto | Tarefas, Agenda | Abre o editor de projeto | — | — |
| Fatura | Agenda | Abre a lista de Cartões | — | Destino de lista, não ficha |
| Objetivo | Início, Mais | Abre a tela de Planejamento | — | Conteúdo dessa tela não verificado \[pendente\] |
| Ativo de Mercado | Mercado | Abre o **detalhe de consulta** | — | Única ficha de consulta existente; já tem dois painéis a partir de 840 dp |

Duas conclusões: (1) **você tem razão** — consultar e editar estão fundidos, porque só existem editores; (2) o editor virou, sem querer, a ficha: é nele que se veem despesas vinculadas e tarefas do evento. Por isso o formulário parece pesado quando você só queria entender o registro.

### B.4 O que funciona e deve ser preservado

- O **círculo de conclusão** da tarefa tem descrição acessível e alterna o estado \[código\]; o problema é a inconsistência com a Agenda, não o círculo.
- **Atraso separado de status**, com ponto e texto (“Atrasada”), e não só cor \[vídeo\]\[doc\].
- O banner de **Atenção financeira** é um bom padrão: uma frase, contorno semântico, ação clara \[vídeo\].
- **Anel de tarefas** que mantém “atrasadas” e “sem prazo” fora da proporção \[doc\].
- **Valores com numerais tabulares**, barras de orçamento com faixas e a rosca interativa que filtra lançamentos \[doc\].
- **Blocos configuráveis** com ordem, ativação e quantidade máxima por bloco \[código\]: é capacidade existente e não deve ser removida.
- A **visão semanal** da Agenda, citada por você como referência positiva \[doc\], e o **detalhe de ativo** do Mercado, que já resolve consulta separada de edição.
- O sistema de **contornos coloridos proporcionais ao tamanho do componente** (`ColoredBorderRole`) \[doc\].

### B.5 Limites do diagnóstico

Não vi nenhuma tela além da Início, nenhum toque, nenhum tablet, nenhum tema claro, nenhuma animação. As afirmações sobre toque e navegação são de código e **precisam ser conferidas no aparelho** \[pendente\]. Se você puder gravar mais dois vídeos curtos — um tocando em uma tarefa, um evento e um lançamento, outro abrindo Agenda, Eventos e Finanças — várias dúvidas abaixo se resolvem.

## C. Revisão crítica da arquitetura anterior

### C.1 Respostas honestas

| Pergunta | Resposta |
| --- | --- |
| A Parte I resolve a distribuição das informações ou principalmente a navegação? | **Principalmente a navegação.** Esboçou Hoje e fichas, mas sem definir como cada tipo de registro é apresentado. |
| Resolve a sobrecarga da Início? | **Só em parte.** “Esqueleto + Radar” ataca o tamanho, mas não trata as causas que o vídeo e o código mostram: repetição entre blocos, nomes que não batem com o conteúdo, ordenação por fonte e não por urgência. |
| Define o que acontece ao tocar? | **Não.** Pior: a regra 5 de 4.4 dizia que “a edição completa abre a ficha”, ou seja, **misturava consulta e edição** — o problema que você descreve. |
| Explica como mostrar relações? | **Em parte.** Chip de origem e faixa de conexões são boas ideias, mas não verifiquei o modelo: por exemplo, não existe vínculo direto entre evento e projeto (D.2), e o canal visual do chip não foi definido. |
| Oferece consulta separada de edição? | **Não.** |
| O modelo de navegação continua o melhor? | **Não demonstrado.** Os achados B.2 e B.3 não dependem das abas; indicam que reorganizar destinos não é o gargalo. |

### C.2 O que continua válido

- A distinção entre **domínio, lente, agrupador e ferramenta transversal**. O código confirma: a Agenda é uma lente (o `AgendaEngine` monta ocorrências a partir de tarefas, compromissos, eventos, lançamentos, faturas e projetos) \[código\].
- **Registro único, várias aparições**, e a ideia de “visões leem, domínios escrevem” — corrigida pela separação consulta × edição (seção I).
- **Chip de origem** e **faixa de conexões** como linguagem de relação, agora com canal visual a definir (G.4).
- **Profundidade progressiva** e a necessidade de rastrear o que é atual, novo e hipótese.
- Reconhecer **Evento, Projeto e Objetivo** como entidades de natureza parecida (agrupam registros), mas sem exigir um destino comum.

### C.3 O que preciso mudar

1. **Planos como destino:** o nome “Planejamento” já rotula hoje a tela de Objetivos \[código: `PlanningGoalsScreen`\], então a renomeação colidiria com um significado existente. Retiro a recomendação como estrutura-base.
2. **Eventos fora da barra:** baseei-me na elegância conceitual, não em uso. O evento tem dinheiro próprio (ingresso que gera lançamento), preparação e datas; é um domínio pesado. Retiro a recomendação até haver dado de uso.
3. **Mercado dentro de Dinheiro:** contradiz a sua visão e o código — o Mercado já tem **ícone próprio na barra superior** (gráfico de linha) \[código\]. Retiro.
4. **Hoje com esqueleto fixo:** removeria uma capacidade existente (blocos configuráveis). Troco por regras de relevância e deduplicação que **preservam** a personalização (F).
5. **Dissolver o Assistente de Rotina:** ele tem configurações, histórico próprio e lembretes (Briefing e Revisão são tipos de lembrete) \[código\]. Mantenho como ritual com entrada própria, integrado à Início, sem dissolvê-lo.
6. **Diagnóstico #13 da Parte I** (“sem pilha de voltar explícita”): **estava impreciso.** Existe uma pilha de subtelas e uma regra pura de voltar com prioridade fixa (editor → subtela → aba → Início → sair) \[código: `subRouteStack`, `resolveBackAction`\]. O que não existe é pilha para **fichas** nem estado por aba.
7. **“Criar por perguntas” e “Comprometido”:** dependem de regras de parcelamento e recorrência que não verifiquei; passam de recomendação a hipótese.
8. **Tablet com lista–detalhe:** pressupunha fichas de consulta, que não existem. A adaptação para tablet passa a depender delas.

### C.4 O que ainda não pode ser concluído

A frequência real de uso de cada área; se “Planejamento”, “Planos” ou outro nome é compreendido; como Agenda, Eventos, Tarefas e Finanças aparecem de fato; e se as hipóteses de código se confirmam no aparelho.

## D. Modelo conceitual das entidades

Tudo nesta seção vem do modelo de dados e dos enums do código \[código\], salvo onde marcado. Não presumo que as entidades formam uma árvore.

### D.1 O que cada entidade é

| Entidade | O que representa | Campos principais | Ciclo de vida | Como a pessoa deve entendê-la |
| --- | --- | --- | --- | --- |
| **Tarefa** | Uma ação a executar | Título, descrição, prazo (com ou sem hora), prioridade (4 níveis), status (6), notas, subtarefas, recorrência | Não iniciada → em andamento / aguardando / bloqueada → concluída ou cancelada. **Atraso é derivado do prazo, não é status** | “Algo que eu faço” |
| **Compromisso** | Uma hora marcada | Título, descrição, início e fim, dia inteiro, local, link, categoria, evento | Sem status próprio | “Algo marcado” |
| **Evento** | Uma ocasião com data, local e custo | Nome, descrição, categoria, início e fim, local e endereço, links, notas, status (Planejado, Confirmado, Realizado, Cancelado) e **ingresso embutido** (status em 7 níveis, preço, quantidade, taxas, total, compra, vencimento, forma de pagamento, cartão, parcelas) | Planejado → confirmado → realizado ou cancelado | “Um acontecimento para o qual me preparo e que custa” |
| **Projeto** | Uma iniciativa com começo e fim | Nome, descrição, prazo, status (Planejado, Em andamento, Pausado, Concluído, Arquivado), prioridade, objetivo | Planejado → em andamento → concluído ou arquivado | “Uma entrega que vou conduzir” |
| **Objetivo** | Um resultado pretendido | Título, descrição, categoria, data-alvo, status (Em andamento, Pausado, Concluído, Cancelado), modo de progresso (**Automático** a partir de marcos, tarefas e projetos, ou **Manual** em %), marcos com data, histórico de progresso | Em andamento → concluído, pausado ou cancelado | “Algo que quero alcançar” |
| **Meta financeira** | Um valor a juntar | Nome, valor-alvo, valor atual, prazo, categoria, notas | Sem status próprio | “Uma quantia que quero reunir” |
| **Lançamento** | Um movimento de dinheiro | Tipo (receita ou despesa), descrição, valor, categoria, status (Previsto, Pendente, Realizado, Atrasado, Cancelado), forma de pagamento, data de ocorrência, vencimento, liquidação, tipo de custo de evento (7), marca de “obrigação de ingresso” | Previsto ou pendente → realizado; atrasado é calculado; cancelado | “Dinheiro que entrou, saiu ou vai sair” |
| **Cartão, fatura, compra parcelada, regra recorrente, empréstimo, orçamento** | Estruturas financeiras que geram ou agrupam lançamentos | Específicos de cada uma | Fatura: aberta, fechada, pendente, paga, atrasada | “Como o dinheiro se organiza no tempo” |
| **Categoria** | Classificação transversal com **cor escolhida pelo usuário** | Nome, cor, escopo (Geral, Agenda, Tarefa, Financeiro, Evento) | — | “A área da vida a que isto pertence” |

### D.2 Relações confirmadas \[código\]

Todas são opcionais e, quando o destino é excluído, o vínculo é apenas limpo (nada é apagado em cascata), exceto onde indicado.

| De | Para | Quantos |
| --- | --- | --- |
| Tarefa | Projeto · Evento · Objetivo · Categoria · Série de recorrência | **Até um de cada tipo** (portanto até três contextos ao mesmo tempo: projeto, evento e objetivo) |
| Tarefa | Subtarefas | Muitas (apagadas junto com a tarefa) |
| Projeto | Objetivo | Zero ou um |
| Compromisso | Evento · Categoria | Zero ou um de cada |
| Evento | Categoria · Cartão (do ingresso) | Zero ou um de cada |
| Lançamento | Projeto · Evento · Tarefa · Categoria · Cartão · Fatura · Compra parcelada · Regra recorrente | Até um de cada tipo |
| Compra parcelada | Evento · Projeto · Cartão · Categoria | Até um de cada tipo |
| Objetivo | Marcos · Histórico de progresso · Categoria | Muitos marcos e registros (apagados junto com o objetivo); uma categoria |
| Orçamento | Categoria | Uma, por mês |
| Meta financeira | Categoria | Zero ou uma; **nenhuma relação com Objetivo, Projeto ou Lançamento** |
| Empréstimo | — | Nenhuma |

### D.3 Objetivo, Projeto, Meta e Evento — a diferença, sem sinônimos

|  | Mede | Termina quando | Tem data? | Tem dinheiro? | Contém |
| --- | --- | --- | --- | --- | --- |
| **Objetivo** | O resultado alcançado | O resultado acontece (progresso automático ou manual) | Data-alvo opcional | Não diretamente | Marcos; recebe tarefas e projetos |
| **Projeto** | A entrega concluída | As tarefas se concluem ou a pessoa o encerra | Prazo opcional | Sim: custo estimado e pago via lançamentos | Recebe tarefas e lançamentos |
| **Meta financeira** | O valor reunido | O valor atual alcança o alvo | Prazo opcional | É dinheiro | Nada |
| **Evento** | A ocasião preparada e vivida | A data passa (ou é cancelado) | Sempre | Ingresso e custos | Recebe tarefas, compromissos e lançamentos |

A conclusão para a nomenclatura é incômoda mas clara: **“meta” hoje tem dois significados** — a entidade Meta financeira e o rótulo de bloco “Metas”/“Objetivos e metas” da Início. Recomendo reservar **Meta** só para o valor a juntar e **Objetivo** só para o resultado pessoal, e parar de misturar os dois na mesma lista \[proposta\].

### D.4 Não é uma árvore: é uma rede com duas pontes

**Tarefa** e **Lançamento** são as pontes: cada uma pode apontar para três contextos diferentes ao mesmo tempo. Evento, Projeto e Objetivo são os pontos de reunião. “Múltiplos vínculos”, no modelo atual, significa **no máximo um por tipo**, nunca dois projetos para a mesma tarefa. Isso importa para o design: o pior caso de ruído é de **três etiquetas** de contexto por registro, não de uma lista aberta.

### D.5 Lacunas e dúvidas do modelo \[pendente\]

1. **Evento não tem vínculo direto com Projeto nem com Objetivo.** Só existe ligação **derivada**, via tarefas ou lançamentos do evento que pertencem a um projeto. A ficha de evento que você descreveu (“projeto ou objetivo relacionado, quando existir”) só pode mostrar isso como “projetos com tarefas neste evento”, rotulado como derivado — ou exigiria um dado novo, que não proponho como decisão.
2. **Projeto não tem categoria, marcos nem participantes.** Os marcos existem só no Objetivo.
3. **Meta financeira está isolada:** sem ligação com Objetivo nem com lançamentos. Não sei se o valor atual é sempre manual \[pendente: ler regras\].
4. **Duplo caminho tarefa → objetivo:** a tarefa pode apontar para um objetivo direto **e** estar em um projeto que aponta para outro. Não verifiquei se as regras impedem a inconsistência nem se o progresso automático conta duas vezes.
5. **Compromisso não se liga a tarefa, projeto ou lançamento.** Uma consulta médica com gasto fica sem conexão estrutural.
6. **Não existem** capa ou imagem de evento, participantes, nem descrição de “propósito” separada da descrição do projeto.
7. **Ingresso é dado do evento e também gera lançamento** (marca de obrigação de ingresso). O mesmo dinheiro tem duas representações; a ficha de lançamento precisa dizer “este é o ingresso do evento X”, senão parece uma despesa avulsa.

### D.6 O que cada ficha pode mostrar com o que já existe

| Ficha | Já existe no modelo de consulta | Exigiria dado novo |
| --- | --- | --- |
| Evento | Tarefas vinculadas, lançamentos vinculados com total estimado e pago, ingresso, cartão, categoria | Capa, participantes, projeto ou objetivo direto. Compromissos do evento existem no banco, mas a carga na ficha é parcial \[doc\] |
| Projeto | Tarefas e progresso, custo estimado e pago, número de lançamentos, objetivo | Marcos, participantes, categoria, propósito separado |
| Objetivo | Marcos, tarefas, projetos, histórico de progresso, categoria | — |
| Tarefa | Subtarefas, projeto, evento, categoria, recorrência, despesas vinculadas | O objetivo da tarefa existe no banco, mas **não é carregado** no modelo de consulta; carregá-lo é mudança de consulta, não de dado |
| Lançamento | Categoria, projeto, evento, tarefa, cartão | Fatura, compra parcelada e regra recorrente têm id mas não são carregadas na consulta |
| Compromisso | Categoria, evento, local, link | — |

## E. Arquitetura da informação
