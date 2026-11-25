# 03 — Componentes, cards e fichas especializadas

**Estado:** EM ANÁLISE  
**Objetivo:** registrar os padrões de apresentação desejados para registros e coleções.

## 1. Cards não devem ser o formato universal

A interface não deve transformar cada informação em um card grande. A apresentação depende da natureza do conteúdo, da quantidade de registros e da ação esperada.

- Tarefas em listas numerosas podem ser compactas.
- Eventos podem se beneficiar de capa e apresentação visual.
- Projetos e objetivos podem exigir progresso, marcos e relações.
- Lançamentos financeiros precisam priorizar valor, natureza, data e estado.
- Informações pequenas ou densas podem permanecer em linhas, chips ou agrupamentos simples.

O tamanho deve ser proporcional à importância e ao contexto.

## 2. Layout que funciona com poucos ou muitos registros

Um requisito central é evitar telas que só pareçam completas quando há muitos dados. Duas tarefas não devem resultar em componentes minúsculos perdidos em um grande vazio, nem em espaços vazios que pareçam defeito.

O layout deve:
- Adaptar densidade e composição à quantidade de conteúdo.
- Não inventar conteúdo para preencher espaço.
- Usar estados vazios úteis e intencionais.
- Permitir leitura rápida com poucos registros e eficiência com muitos.
- Preservar a qualidade da visão semanal da Agenda, que apresenta os elementos da semana visualmente em vez de depender apenas de dias selecionáveis com listas abaixo.

## 3. Cards compactos e expansíveis

Hipótese de interação para alguns registros, especialmente eventos:
1. Estado inicial compacto, com título, identidade visual e informações essenciais.
2. Ao expandir, o card revela conteúdo contextual e desloca naturalmente os elementos seguintes.
3. Pode apresentar uma amostra limitada de itens relacionados (por exemplo, três de seis tarefas).
4. Um comando “Ver todas” leva à visualização completa.
5. A expansão não cria uma pilha de fichas sobrepostas.

A expansão deve ser seletiva. Não é uma regra para todos os cards nem para estruturas aninhadas. Não abrir automaticamente todos os elementos internos de um projeto ou objetivo ao expandir um contêiner.

## 4. Exemplo conceitual: ficha/apresentação de evento

Um evento como “Viagem para São Vicente” pode ter:
- Capa ou imagem contextual, quando disponível.
- Título, período e descrição curta.
- Informações essenciais do evento.
- Amostra de tarefas vinculadas, com checkbox funcional quando a ação for concluir.
- “Ver todas” para acessar a coleção completa.
- Objetivo ou projeto relacionado, se a relação existir e for relevante.
- Atalho discreto de edição, como lápis em botão circular.
- Acesso à ficha completa ou à tela própria do evento.

A composição e ordem das relações devem ser definidas pelo valor para o usuário e pelo modelo real, não por uma lista fixa de campos.

## 5. Fichas especializadas por entidade

A proposta é que cada tipo de registro tenha uma ficha com identidade e conteúdo adequados, sem usar uma ficha genérica com campos simplesmente trocados.

Direções iniciais:
- **Evento:** apresentação visual, período, local/formato, participação, tarefas, compromissos, custos e relações relevantes.
- **Projeto:** propósito, progresso derivado, prazo, tarefas, custos e objetivo relacionado.
- **Objetivo:** resultado pretendido, progresso, marcos, prazo e projetos/tarefas relacionados, respeitando o modelo real.
- **Tarefa:** título, contexto, prazo, prioridade, status, checklist, recorrência e ações pertinentes; apresentação compacta em coleções.
- **Lançamento financeiro:** valor, natureza, data/vencimento, categoria, liquidação e vínculos aplicáveis, sem esconder regras financeiras.

Essas listas são campos candidatos, não declaração de que todos existem ou devem aparecer sempre. Conferir código, modelo e especificação.

## 6. Tarefas compactas e acionáveis

Tarefas podem aparecer em listas de Hoje, Agenda, projetos, objetivos e eventos. A apresentação compacta deve permitir:
- Identificar título e contexto.
- Reconhecer prazo e prioridade quando relevantes.
- Concluir diretamente quando a ação estiver disponível.
- Abrir a ficha específica ao tocar no conteúdo principal.
- Evitar cards altos e repetitivos quando há muitas tarefas.

O usuário sugeriu que a cor contextual de um projeto/objetivo pudesse ajudar a reconhecer a origem de uma tarefa. Isso depende da decisão de cores e da hierarquia de vínculos; manter como evolução futura em análise.

## 7. Imagens, capas, degradês e expressão

Imagens e capas devem acrescentar contexto, não ser decoração obrigatória. Eventos são candidatos naturais a capas; projetos/objetivos podem receber tratamento visual próprio se isso fizer sentido. Degradês podem ser explorados em Mercado e gráficos financeiros, com moderação e contraste adequado.

A ausência de imagem deve resultar em uma composição intencional, não em um espaço quebrado.

## 8. Edição

Um lápis discreto e claramente acionável pode abrir o formulário de edição. Consulta e edição devem ser ações distintas. Não tornar todos os campos diretamente editáveis nem transformar cada toque em edição.
