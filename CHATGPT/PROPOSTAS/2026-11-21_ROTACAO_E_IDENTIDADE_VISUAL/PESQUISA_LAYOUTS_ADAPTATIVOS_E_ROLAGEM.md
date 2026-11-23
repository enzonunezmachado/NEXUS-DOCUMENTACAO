# Pesquisa inicial — layouts adaptativos e rolagem da Agenda

**Data:** 22/11/2026  
**Ideia relacionada:** IDEA-006  
**Estado:** EM PESQUISA  
**Objetivo:** fundamentar uma proposta para a Agenda semanal e layouts em tablet/telas maiores.  
**Limite:** pesquisa e documentação; nenhuma implementação autorizada.

## 1. Perguntas que motivaram a pesquisa

- Como aplicações Android adaptam layouts quando a janela muda de tamanho, proporção ou orientação?
- Como decidir entre uma área única e painéis lado a lado?
- Como evitar que um layout de tablet seja apenas uma versão esticada do celular?
- Como manter um painel semanal fixo e permitir rolagem independente do conteúdo de cada dia?
- Como compartilhar a linguagem visual entre Agenda e Kanban sem apagar as diferenças funcionais?

## 2. Fontes oficiais consultadas

### 2.1 Window size classes

[Android Developers — Use window size classes](https://developer.android.com/develop/adaptive-apps/guides/use-window-size-classes)

A documentação recomenda classificar o espaço disponível na janela do aplicativo por largura e altura, em vez de presumir dimensões a partir do tipo físico de dispositivo. As classes podem mudar durante a execução por rotação, multitarefa, redimensionamento ou mudança de postura de dispositivos dobráveis.

Referências de largura apresentadas pela documentação:
- Compact: abaixo de 600 dp.
- Medium: de 600 dp até abaixo de 840 dp.
- Expanded: de 840 dp até abaixo de 1200 dp.
- Large: de 1200 dp até abaixo de 1600 dp.
- Extra-large: 1600 dp ou mais.

A documentação também chama atenção para a altura: em paisagem, uma janela pode ter largura média e altura compacta, situação em que duas áreas lado a lado talvez não sejam práticas.

**Aplicabilidade ao NEXUS:** avaliar o espaço efetivo da janela, largura e altura, e não regras simplistas como `isTablet` ou orientação isolada. Os valores publicados são referências do Android, não breakpoints já escolhidos para o NEXUS.

### 2.2 Boas práticas adaptativas

[Android Developers — Adaptive do's and don'ts](https://developer.android.com/develop/adaptive-apps/guides/adaptive-dos-and-donts)

A documentação recomenda usar os mecanismos atuais de window size classes, aproveitar layouts canônicos e reorganizar conteúdo em painéis quando isso melhora o uso do espaço. Também alerta contra simplesmente esticar elementos para preencher telas grandes.

São citados componentes da biblioteca Material 3 Adaptive, como NavigationSuiteScaffold, ListDetailPaneScaffold e SupportingPaneScaffold.

**Aplicabilidade ao NEXUS:** usar a orientação como repertório de padrões, mas verificar dependências, versões e arquitetura já presentes antes de sugerir qualquer adoção.

### 2.3 Layout de lista e detalhe

[Android Developers — Build a list-detail layout](https://developer.android.com/develop/adaptive-apps/guides/list-detail)

O padrão list-detail mostra lista e detalhe lado a lado em janelas amplas; em janelas menores, mostra uma área por vez. A seleção do item mantém a correspondência entre lista e detalhe quando a janela muda de classe.

**Aplicabilidade ao NEXUS:** é uma referência conceitual para a Agenda em tablet: dias/calendário de um lado e conteúdo do dia selecionado do outro. Não implica que a Agenda deva usar literalmente esse componente ou que a navegação atual deva ser substituída.

### 2.4 Listas e rolagem no Compose

[Android Developers — Lazy lists and grids](https://developer.android.com/develop/ui/compose/lists)

A documentação alerta para listas roláveis na mesma direção aninhadas sem dimensão delimitada. Um exemplo problemático é um LazyColumn sem altura definida dentro de uma Column com rolagem vertical.

**Aplicabilidade ao NEXUS:** a experiência desejada — painel semanal sem rolagem vertical e áreas internas roláveis por dia — exige uma hierarquia de layout com dimensões bem definidas. Não se deve apenas colocar listas verticais ilimitadas dentro de outra área vertical rolável.

### 2.5 Rolagem aninhada

[Android Developers — Nested scrolling modifiers](https://developer.android.com/develop/ui/compose/touch-input/scroll/nested-scroll-modifiers)

O Compose oferece coordenação automática de rolagem entre componentes compatíveis. Quando a área interna chega ao limite, gestos podem ser propagados para o contêiner pai. Para coordenações mais avançadas, existe o mecanismo nestedScroll.

**Aplicabilidade ao NEXUS:** como a direção desejada é que o painel semanal não role verticalmente e cada dia controle seu próprio conteúdo, a implementação deve evitar um pai vertical rolável concorrente. O comportamento precisa ser testado com listas vazias, curtas e longas e com gestos iniciados em diferentes regiões.

## 3. Síntese provisória

As fontes apontam para quatro princípios úteis:

1. Adaptar pelo espaço real disponível na janela, considerando largura e altura.
2. Usar layouts de painel único ou múltiplos painéis conforme o espaço e a tarefa, não apenas conforme a orientação.
3. Não esticar componentes indiscriminadamente em telas grandes; reorganizar conteúdo e hierarquia.
4. Delimitar corretamente as áreas roláveis e evitar listas verticais aninhadas sem tamanho definido.

Esses princípios são referências para uma solução, não decisões finais de implementação.

## 4. Direção de experiência registrada pelo usuário

- Preservar a aparência atual dos cartões semanais no celular, que foi aprovada após o teste.
- Melhorar a densidade de informação dos cartões, que ficou compacta demais.
- Fazer o painel semanal ocupar a altura útil até próximo da barra inferior de navegação.
- Manter a página semanal fixa verticalmente.
- Permitir rolagem vertical independente dentro do conteúdo de cada cartão de dia quando houver excesso de itens.
- Aproximar a qualidade visual dos cartões da Agenda à linguagem dos cartões Kanban, sem copiar sua proporção horizontal ou estrutura.
- Reavaliar a composição do tablet em paisagem, que não agradou no teste real.
- Investigar comportamento em diferentes proporções, larguras e alturas.

## 5. Questões ainda abertas

- Qual é a estrutura real de contêineres e rolagem da Agenda atual?
- Os cartões de cada dia são componentes independentes ou compartilham um único contêiner de conteúdo?
- Qual a melhor distribuição em tablet deitado quando há pouca altura?
- Em que situações a Agenda deve usar painel duplo e em quais deve manter uma área única?
- Como evitar conflitos de gesto entre seleção do dia, rolagem interna e navegação?
- Quais componentes e tokens visuais do Kanban podem ser compartilhados sem comprometer a identidade da Agenda?
- Quais larguras/alturas reais devem ser usadas nos testes, com base no código e nos dispositivos disponíveis?

## 6. Próxima etapa da pesquisa

1. Inspecionar os arquivos atuais da Agenda e do Kanban no repositório ativo.
2. Mapear hierarquia de layout, constraints, rolagem e estado de seleção.
3. Reunir capturas da versão atual em celular e tablet, em retrato e paisagem.
4. Criar protótipos comparáveis para painel semanal fixo e rolagem independente por dia.
5. Comparar a linguagem visual dos cards da Agenda e Kanban.
6. Consolidar alternativas e critérios de aceite em uma proposta para o usuário.

## 7. Limitações

Esta é uma pesquisa inicial baseada em documentação oficial do Android. Não é um levantamento completo de aplicações de terceiros nem uma auditoria do código NEXUS. Não foram executados testes, benchmarks ou protótipos nesta etapa. Não foram aprovados breakpoints, dependências, APIs ou mudanças de arquitetura.
