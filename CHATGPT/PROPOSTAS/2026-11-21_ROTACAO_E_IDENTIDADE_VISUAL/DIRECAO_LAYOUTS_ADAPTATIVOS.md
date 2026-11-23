# Direção em análise — layouts adaptativos, Agenda e rotação

**Registro:** 22/11/2026  
**Tipo:** observação de UX e proposta de exploração visual/técnica  
**Estado:** em pesquisa / em análise  
**Origem:** feedback após teste da versão instalada, depois da primeira implementação de layouts adaptativos  
**Relação:** IDEA-006 e BUG-001

## 1. Objetivo

A interface do NEXUS deve aproveitar o espaço disponível de maneira intencional. Em celulares, tablets e telas maiores, a experiência não deve ser simplesmente esticada ou comprimida: painéis e componentes podem ser reorganizados conforme largura, altura e orientação, mantendo continuidade de contexto.

Esta direção reúne necessidades relacionadas, mas distintas: proporção e rolagem dos cartões semanais; identidade visual coerente entre Agenda e Kanban; layouts adaptativos para tablets e telas maiores; preservação de tela e estado durante rotação, relacionada ao BUG-001.

## 2. Feedback após teste real — Agenda semanal

O usuário avaliou a versão instalada e gostou das mudanças em geral, mas identificou que a composição em tablet na orientação horizontal ainda não está satisfatória. A experiência semanal também ficou compacta demais em relação à quantidade de informação exibida.

### 2.1 O que deve ser preservado

- No celular, a aparência atual dos cartões semanais foi considerada boa.
- No celular, os cartões do Kanban também foram considerados bons.
- A proporção horizontal dos cartões do Kanban faz sentido para a função de quadro e não deve ser copiada mecanicamente para a Agenda.
- A Agenda deve manter sua identidade funcional própria, mas compartilhar uma linguagem visual coerente com o Kanban.

### 2.2 Direção desejada para o painel semanal

- O painel/área principal da visão semanal deve ocupar praticamente toda a altura útil disponível, desde o início do conteúdo até próximo da barra inferior de navegação (Início, Financeiro etc.).
- O painel semanal deve permanecer fixo verticalmente dentro dessa área; a tela semanal como um todo não deve rolar verticalmente.
- Os cartões individuais dos dias devem usar o espaço vertical interno disponível.
- Quando um dia tiver mais tarefas ou compromissos do que cabem no cartão, a rolagem deve ocorrer dentro da área de conteúdo daquele dia, sem deslocar a página semanal inteira.
- Os cartões devem deixar de apresentar informações excessivamente compactadas. A área útil deve ser aproveitada para melhorar a leitura e a consulta.
- A rolagem independente precisa ser previsível e não pode prejudicar a seleção de dias, a leitura dos indicadores ou os alvos de toque.

Esta é uma direção de experiência desejada, não uma especificação de implementação fechada. A composição precisa ser prototipada e validada com dias vazios, poucos itens e grande volume de itens.

### 2.3 Linguagem visual compartilhada com Kanban

O usuário considera os cartões do Kanban visualmente mais bem resolvidos e quer elevar a qualidade visual dos cartões da Agenda semanal ao mesmo nível.

A referência é a linguagem visual: tratamento de superfície, hierarquia, espaçamento, organização das informações, legibilidade e consistência dos componentes. Não é uma solicitação para transformar a Agenda em Kanban nem para copiar dimensões e proporções indiscriminadamente.

Os cartões do Kanban são naturalmente mais largos por causa da organização em colunas e do conteúdo de cada tarefa. A Agenda tem outra finalidade e deve preservar uma proporção apropriada à visão semanal. A revisão deve procurar um sistema visual coerente, respeitando o contexto de cada módulo.

## 3. Agenda em celular

- Preservar a experiência atual dos cartões semanais no celular como ponto de partida.
- Melhorar a densidade de informação sem perder a aparência que o usuário aprovou.
- O painel semanal deve aproveitar a altura útil, aproximando-se da barra inferior sem sobrepor áreas do sistema ou controles de navegação.
- Evitar impor a mesma solução de tablet ao celular.
- A rolagem interna por dia precisa ser avaliada em telas compactas para garantir que gestos e seleção continuem confortáveis.

## 4. Agenda em tablet deitado — composição adaptativa

A composição anterior desejada continua relevante: apresentar dias/calendário em uma área e os itens do dia selecionado em outra, quando a janela comportar essa organização.

O teste real da primeira implementação mostrou que a distribuição atual em orientação horizontal ainda não está satisfatória. Portanto, não considerar o layout atual como aprovado.

Investigar uma composição que:
- distribua dias/calendário e conteúdo selecionado de forma equilibrada;
- não estique os componentes para preencher espaço sem propósito;
- mantenha hierarquia, proporções e legibilidade;
- preserve seleção e contexto ao mudar a largura, altura ou orientação;
- considere a altura reduzida em paisagem, mesmo quando houver largura suficiente para duas áreas.

A divisão entre painéis não precisa ser 50/50. Deve ser definida por conteúdo, legibilidade e testes.

## 5. Telas grandes e formato tipo monitor

Estudar uma experiência expandida para janelas largas: painéis simultâneos quando reduzirem navegação desnecessária; colunas e áreas laterais para detalhes; hierarquia e densidade adequadas; mesmos dados e regras de negócio entre layouts.

A referência a monitores é uma direção de adaptação visual, não uma decisão de transformar imediatamente o NEXUS em aplicativo desktop ou garantir suporte a qualquer janela livre.

## 6. Fundamentos de pesquisa já identificados

A documentação oficial do Android recomenda decisões adaptativas baseadas no espaço real da janela do aplicativo, em vez de regras rígidas por tipo de dispositivo. Largura e altura são classificadas separadamente; a classe de altura é especialmente relevante em paisagem, quando duas áreas lado a lado podem não ser práticas.

Também foram identificados padrões oficiais para organizar conteúdo em painéis, como list-detail, e recomendações específicas para listas roláveis no Compose. A rolagem vertical aninhada exige limites de tamanho bem definidos; uma lista vertical interna sem dimensão delimitada dentro de outro contêiner vertical rolável pode causar comportamento inválido ou inesperado.

Referências consultadas:
- [Android — Use window size classes](https://developer.android.com/develop/adaptive-apps/guides/use-window-size-classes)
- [Android — Adaptive do's and don'ts](https://developer.android.com/develop/adaptive-apps/guides/adaptive-dos-and-donts)
- [Android — Build a list-detail layout](https://developer.android.com/develop/adaptive-apps/guides/list-detail)
- [Android — Lazy lists and grids in Compose](https://developer.android.com/develop/ui/compose/lists)
- [Android — Nested scrolling modifiers](https://developer.android.com/develop/ui/compose/touch-input/scroll/nested-scroll-modifiers)

Essas fontes fornecem fundamentos, não uma solução pronta para a Agenda do NEXUS. A pesquisa deve continuar com inspeção do código e protótipos, sem adicionar dependências ou alterar arquitetura por antecipação.

## 7. Investigação técnica a realizar

- Inspecionar a composição atual da Agenda semanal e identificar quais contêineres controlam altura e rolagem.
- Verificar se a página semanal inteira possui rolagem vertical e como os cartões internos são dimensionados.
- Avaliar uma área semanal com altura delimitada pela área útil entre cabeçalho e navegação inferior.
- Avaliar rolagem vertical independente por cartão/dia, com limites explícitos de altura e sem rolagem vertical concorrente da página.
- Verificar gestos, consumo de eventos de rolagem, cartões vazios, cartões com poucos itens e cartões com muitos itens.
- Comparar a linguagem visual dos cartões da Agenda com a dos cartões Kanban, identificando componentes e tokens compartilháveis sem uniformizar suas proporções.
- Avaliar classes de largura e altura da janela e mudanças dinâmicas de tamanho, não apenas orientação retrato/paisagem.
- Investigar layouts de painel único, painel duplo e composição expandida, conforme o espaço realmente disponível.
- Inspecionar dependências e APIs já presentes no projeto antes de propor novas bibliotecas.

## 8. Rotação e preservação de estado — relação com BUG-001

A adaptação visual não resolve, por si só, a perda de estado relatada ao girar o dispositivo.

O BUG-001 continua cobrindo a preservação da tela/rota, aba e contexto de navegação, conteúdo digitado, seleções, filtros, data selecionada, progresso de criação/edição e menus/diálogos relevantes, quando aplicável.

A implementação deve tratar a recriação da Activity e distinguir rotação de encerramento do processo. A análise técnica já reportada pelo AI Studio deve ser consultada antes de definir arquitetura ou escopo.

A interface deve adaptar sua composição à nova orientação sem descartar o contexto do usuário. São critérios complementares: continuidade funcional (não perder o que estava fazendo) e continuidade visual (reorganizar o conteúdo para a nova dimensão).

## 9. Critérios gerais de avaliação

- O layout se adapta às dimensões reais da janela, não apenas ao modelo do dispositivo.
- Largura e altura são consideradas; paisagem não é tratada automaticamente como sinônimo de espaço amplo em todas as direções.
- A Agenda mantém seleção e contexto ao alternar retrato/paisagem.
- Em tablet deitado, calendário e itens do dia podem permanecer visíveis simultaneamente quando houver espaço suficiente.
- O painel semanal ocupa a altura útil disponível, próximo da navegação inferior, sem sobreposição.
- A página semanal não se desloca verticalmente durante a rolagem das tarefas internas dos dias.
- Cada cartão de dia pode rolar seu próprio conteúdo quando necessário.
- O visual da Agenda alcança coerência de qualidade com o Kanban sem copiar sua estrutura ou proporção.
- Telas maiores ganham organização mais eficiente, não apenas espaços vazios.
- A interface mantém acessibilidade, alvos de toque e hierarquia.
- Preferências e contexto não são perdidos quando a composição muda.

## 10. Limites e estado

- Estado: em pesquisa / em análise; o primeiro retorno de pesquisa foi iniciado, mas a solução ainda precisa ser consolidada.
- O feedback foi obtido após teste real da versão instalada.
- A aparência atual dos cartões semanais no celular deve ser preservada como referência positiva.
- A composição horizontal atual do tablet não foi aprovada pelo usuário.
- A direção de rolagem interna dos cartões foi solicitada, mas ainda precisa de protótipo e validação técnica.
- Não há autorização de implementação nesta etapa.
- Não marcar BUG-001 como corrigido por registrar esta direção.
- Não definir breakpoints, dimensões ou proporções numéricas sem inspeção e prototipagem.

## 11. Relação com a documentação estratégica

Esta direção detalha a ideia existente **IDEA-006 — Layouts adaptativos para celular, tablet e telas maiores**. O BUG-001 permanece no documento de problemas/correções porque descreve a perda funcional de estado durante rotação. O catálogo central e o quadro de pendências apontam para este documento, sem criar uma ideia duplicada.
