# 03 — Componentes, cards e fichas especializadas

**Estado:** EM ANÁLISE  
**Objetivo:** definir princípios de apresentação para coleções e entidades, preservando densidade, personalidade e clareza de ação.

## 1. Não existe um formato universal

A interface não deve transformar toda informação em um card grande. O formato depende de quatro fatores:
- Natureza da entidade.
- Quantidade e densidade dos registros.
- Importância da informação naquele contexto.
- Ação que o usuário precisa realizar.

Exemplos:
- Tarefas numerosas podem aparecer em linhas ou cards compactos.
- Eventos podem merecer capas e composição visual mais expressiva.
- Projetos podem destacar propósito, progresso e marcos.
- Lançamentos financeiros devem priorizar valor, natureza, data e situação.
- Informações auxiliares podem usar chips, rótulos ou agrupamentos discretos.

Consistência significa compartilhar regras de composição e comportamento, não forçar o mesmo componente para tudo.

## 2. Layout adaptável à quantidade de dados

A tela deve funcionar bem tanto com poucos quanto com muitos registros.

Com poucos itens:
- Não deixar duas tarefas minúsculas isoladas no topo de uma área enorme.
- Usar melhor a composição disponível sem inflar artificialmente os componentes.
- Priorizar contexto e hierarquia, não preencher espaço com conteúdo inventado.

Com muitos itens:
- Preservar densidade, leitura rápida e ações acessíveis.
- Evitar cards altos e repetitivos.
- Agrupar ou filtrar quando isso realmente ajuda.

Estados vazios devem parecer intencionais e orientar o próximo passo, sem simular dados existentes.

A visão semanal atual foi apontada como referência positiva: cards compactos e visualmente organizados para os dias e seus itens, sem depender exclusivamente de cabeçalhos clicáveis com uma lista aberta abaixo. É uma referência a preservar e estudar, não um modelo a copiar para todas as áreas.

## 3. Cards compactos, expandidos e navegação

Alguns registros podem oferecer expansão contextual:
1. Apresentação inicial compacta com identidade e informações essenciais.
2. Ação explícita ou área claramente indicada para expandir.
3. Conteúdo adicional aparece no próprio fluxo e desloca naturalmente o que está abaixo.
4. Uma amostra limitada de itens relacionados pode ser exibida.
5. “Ver todas” leva à coleção completa ou ficha apropriada.

Exemplo: um evento com seis tarefas pode revelar até três tarefas, cada uma com ação de conclusão quando permitida, e oferecer acesso às demais.

A expansão é seletiva, não obrigatória. Nem todo card deve expandir; nem toda entidade deve revelar subentidades automaticamente. Em alguns casos, abrir a ficha completa é mais claro.

Evitar camadas infinitas de cards, fichas sobrepostas ou expansão aninhada que torne a leitura confusa.

## 4. Fichas especializadas por entidade

Cada entidade deve ter uma apresentação própria, coerente com seu papel. Não usar uma ficha genérica que apenas troca os campos.

Direções conceituais:
- **Evento:** capa quando útil, título, período, local, descrição, tarefas, compromissos, custos e relações relevantes.
- **Projeto:** propósito, progresso, prazo, tarefas, marcos e objetivo relacionado.
- **Objetivo:** resultado pretendido, horizonte, progresso, marcos e projetos/tarefas relacionados.
- **Tarefa:** título, prazo, prioridade, estado, recorrência, contexto e ação de conclusão quando disponível.
- **Registro financeiro:** valor, natureza, data ou vencimento, categoria, liquidação e vínculos aplicáveis.

Esses campos são candidatos para investigação, não garantia de que todos existam no modelo ou devam aparecer sempre. Confirmar no código, modelo e documentação técnica.

## 5. Exemplo de apresentação contextual de evento

Um evento como “Viagem para São Vicente” pode apresentar:
- Capa ou imagem contextual, se disponível e útil.
- Título e período.
- Resumo curto e informações essenciais.
- Tarefas vinculadas, com poucas linhas visíveis inicialmente.
- Ação de concluir diretamente em cada tarefa, quando permitida.
- Acesso “Ver todas”.
- Projeto ou objetivo relacionado, somente se a relação existir e ajudar a compreender o contexto.
- Ação explícita para abrir a ficha completa.
- Edição por controle próprio e discreto.

A ordem e a quantidade de informações devem ser decididas pelo contexto, não por uma lista fixa de campos.

## 6. Tarefas compactas e acionáveis

Tarefas aparecem em diferentes contextos: Hoje, Agenda, Tarefas, projetos e eventos. A apresentação compacta deve:
- Manter título legível e contexto suficiente.
- Exibir prazo, prioridade ou estado quando forem relevantes.
- Permitir conclusão direta quando o contexto e as regras permitirem.
- Abrir a ficha ao tocar no conteúdo principal.
- Não transformar cada tarefa em um card alto.
- Evitar repetir relações e metadados já claros no contexto.

Ações rápidas precisam ter área de toque e feedback próprios, sem fazer o usuário abrir formulário e procurar a ação cotidiana de concluir.

## 7. Capas, imagens, degradês e ausência de imagem

Capas e imagens devem acrescentar identidade ou contexto, não ser decoração obrigatória. Eventos são candidatos naturais; projetos e objetivos podem receber tratamento próprio se houver valor.

Degradês podem ser explorados em gráficos financeiros e Mercado, respeitando contraste e propósito. A ausência de imagem deve produzir uma composição intencional, não um espaço vazio quebrado ou um placeholder chamativo sem função.

## 8. Bordas, raios e proporção

Raios, espessuras, contornos e superfícies precisam respeitar a escala do componente. Um card amplo pode suportar maior presença visual; uma tarefa compacta, chip ou ícone pequeno pode precisar de borda discreta.

Não aplicar uma regra de “aumentar todas as bordas” indiscriminadamente. Auditar proporcionalidade, densidade, tema claro/escuro e hierarquia.

## 9. Consulta e edição

A apresentação principal deve favorecer consulta. Edição é uma ação explícita, por exemplo um lápis discreto e claramente acionável que abre o formulário adequado.

Não tornar toda a superfície editável nem fazer com que qualquer toque abra edição. A ficha deve distinguir conteúdo, relações, ações rápidas e comandos de manutenção.
