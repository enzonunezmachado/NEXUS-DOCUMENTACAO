# 05 — Plano de evolução e questões abertas

**Estado:** EM ANÁLISE  
**Natureza:** plano preparatório; não é roadmap técnico aprovado nem autorização de implementação.

## 1. Sequência de trabalho proposta

### Fase 1 — Auditoria do aplicativo real
- Inspecionar telas, componentes, navegação, ícones, cores, contornos, estados e interações atuais.
- Mapear divergências entre Agenda, Tarefas, Home, Eventos, Financeiro e Mercado.
- Verificar responsividade e comportamento com poucos/muitos registros.
- Confrontar documentação com código e registrar fatos, hipóteses e inconsistências separadamente.

**Saída esperada:** inventário de experiência atual e mapa de problemas com evidências.

### Fase 2 — Validação da arquitetura da informação
- Avaliar a proposta Claude contra os módulos, dados e fluxos reais.
- Revisar destinos, nomenclaturas, hierarquia e relações.
- Testar fluxos ponta a ponta: organizar semana, preparar evento, acompanhar projeto, executar tarefas e consultar finanças.
- Preservar funções importantes que não se encaixem naturalmente na proposta sem forçar agrupamentos.

**Saída esperada:** arquitetura candidata comparada com alternativas, ainda sujeita à decisão humana.

### Fase 3 — Linguagem visual
- Definir princípios de identidade, tipografia, espaçamento, superfícies, bordas, cores e ícones.
- Resolver responsabilidades de cores de tipo, estado, categoria, relação e seleção.
- Fazer remapeamento de símbolos da Agenda e comparar com os demais módulos.
- Preservar a sobriedade e evitar agressividade visual.

**Saída esperada:** proposta de linguagem visual e matriz semântica para validação.

### Fase 4 — Sistema de componentes e interação
- Definir padrões de cards compactos, expansíveis, linhas, chips, fichas e controles.
- Definir contrato entre consulta, expansão, edição, conclusão e navegação.
- Especificar regras de densidade e estados vazios.
- Preparar componentes reutilizáveis sem apagar particularidades de cada entidade.

**Saída esperada:** catálogo de padrões e comportamentos.

### Fase 5 — Experiências das entidades
Explorar primeiro:
1. Ficha/apresentação de Evento.
2. Ficha de Projeto.
3. Ficha de Objetivo.
4. Ficha de Tarefa.
5. Ficha de registro financeiro.

A escolha inicial de Evento, Projeto e Financeiro foi sugerida por oferecerem contrastes úteis de apresentação. A inclusão de Objetivo e Tarefa é necessária para validar a rede de relações e a densidade operacional.

**Saída esperada:** fluxos e protótipos conceituais validados antes de implementação.

### Fase 6 — Aplicação por área
Aplicar a direção aprovada nas áreas principais, sem perder requisitos, filtros, ferramentas, histórico ou regras atuais.

### Fase 7 — Adaptação e refinamento
- Celular retrato/paisagem e tablets.
- Transições, microinterações e animações.
- Acessibilidade, contraste, movimento reduzido e desempenho.
- Revisão de estados vazios e listas grandes.

### Fase 8 — Validação
- Testar cenários com poucos e muitos registros.
- Testar registros com múltiplos vínculos.
- Testar conclusão, edição, navegação e retorno.
- Verificar consistência de status e cores em todos os contextos.
- Executar testes funcionais e de regressão apropriados.

## 2. Questões críticas ainda abertas

### Cores e relações
- Manter a semântica de status existente e adicionar canal contextual separado?
- Como uma tarefa com vários vínculos é representada?
- Como evitar que cor de projeto conflite com atraso, conflito ou conclusão?
- O marcador contextual é cor, faixa, ponto, chip ou combinação?

### Modelo mental e hierarquia
- Qual a definição de Objetivo, Projeto, Evento e Meta no modelo atual?
- Quais relações são diretas e quais são apenas contextuais?
- A proposta de agrupar Objetivos, Projetos e Eventos em Planos melhora descoberta?

### Apresentação
- Quais entidades merecem capa/imagem?
- Quando card deve expandir e quando deve navegar?
- Quantos itens relacionados devem aparecer antes de “Ver todas”?
- Como lidar com espaço vazio sem inventar conteúdo?

### Interação
- Em quais contextos a tarefa pode ser concluída diretamente?
- Como a ação se comporta para recorrências, tarefas bloqueadas ou estados especiais?
- Como separar ícone de tipo de checkbox de ação?
- Como preservar o estado ao navegar e voltar?

### Implementação e arquitetura
- Quais componentes atuais podem ser reaproveitados?
- Quais telas exigem reorganização ampla?
- Que mudanças são puramente visuais e quais alteram regras, dados ou navegação?
- Como dividir a implementação em lotes pequenos, testáveis e reversíveis?

## 3. Critérios de conclusão da iniciativa

A reformulação só poderá ser considerada concluída quando:
- A arquitetura de navegação tiver sido aprovada e implementada sem perda funcional.
- As entidades tiverem apresentação e fichas coerentes com seus papéis.
- Símbolos e controles não produzirem expectativas contraditórias.
- Status e relações forem distinguíveis e acessíveis.
- O layout funcionar com volumes baixos e altos de conteúdo.
- Os fluxos entre registros forem navegáveis e previsíveis.
- Celular e tablet apresentarem composições adequadas.
- Testes e validações de regressão confirmarem a integridade das regras existentes.

## 4. Regra de execução

Cada fase deverá ter escopo, dependências, arquivos/componentes envolvidos, critérios de aceite e testes. O Google AI Studio não deve receber um pedido único e amplo para reconstruir todo o aplicativo sem decomposição, inspeção e aprovação. Nenhuma implementação começa apenas porque uma ideia foi registrada aqui.
