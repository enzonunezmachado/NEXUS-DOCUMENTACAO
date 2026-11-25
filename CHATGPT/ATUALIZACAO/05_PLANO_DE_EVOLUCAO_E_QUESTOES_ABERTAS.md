# 05 — Plano de evolução e questões abertas

**Estado:** EM ANÁLISE  
**Natureza:** sequência de investigação e preparação; não é roadmap técnico aprovado nem autorização de implementação.

## 1. Ordem de trabalho recomendada

A reformulação não deve começar por redesenhar telas isoladas. Primeiro é necessário entender o produto real, depois definir arquitetura e linguagem, e só então especificar e aplicar componentes.

### Fase 1 — Auditoria da experiência e da implementação atual

- Inspecionar o aplicativo Android real, não apenas protótipos ou referências.
- Inventariar áreas, telas, fluxos, componentes, ícones, cores, bordas e estados.
- Registrar o comportamento real de consulta, edição, conclusão, expansão e navegação.
- Verificar celular, tablet, retrato e paisagem.
- Avaliar telas com poucos e muitos registros.
- Confrontar interface, código, modelo de dados e documentação técnica.

**Saída:** diagnóstico com evidências, separando fatos, problemas observados, hipóteses e lacunas.

### Fase 2 — Modelo mental e mapa de entidades

- Mapear o que são Tarefa, Evento, Projeto, Objetivo, Meta e registros financeiros.
- Identificar relações, cardinalidades, dependências e regras reais.
- Distinguir domínio, destino, lente, ferramenta transversal e agrupador.
- Verificar quais relações são diretas e quais são apenas contextuais.
- Identificar funcionalidades e áreas que não podem desaparecer na reorganização.

**Saída:** mapa conceitual e glossário de entidades, validado contra o modelo e o código.

### Fase 3 — Arquitetura da informação

- Comparar a proposta Claude com alternativas realmente diferentes.
- Revisar destinos, nomes, agrupamentos e descoberta.
- Avaliar Hoje, Agenda, Tarefas, Planejamento, Dinheiro, Mercado e demais áreas.
- Testar fluxos ponta a ponta: organizar semana, preparar evento, acompanhar projeto, executar tarefas, revisar o dia e consultar finanças.
- Avaliar celular e tablet desde a arquitetura, não apenas no final.

**Saída:** alternativas comparadas e uma arquitetura candidata para decisão humana.

### Fase 4 — Linguagem visual e semântica

- Auditar o Design System existente antes de propor substituições.
- Definir princípios de tipografia, espaçamento, superfícies, bordas, raios e hierarquia.
- Separar as responsabilidades de tipo, estado, relação, categoria e seleção.
- Remapear ícones da Agenda e comparar com os demais módulos.
- Explorar personalidade por domínio sem fragmentar a identidade.
- Validar contraste, tema claro/escuro e acessibilidade.

**Saída:** proposta visual, inventário semântico e exemplos em contextos reais.

### Fase 5 — Componentes, fichas e interações

- Definir quando usar linha, card compacto, card expressivo, expansão ou ficha completa.
- Criar padrões especializados por entidade.
- Definir contrato entre concluir, consultar, expandir, editar, navegar e excluir.
- Especificar estados vazios, poucos registros e listas densas.
- Definir relações contextuais sem exposição excessiva.
- Preparar componentes reutilizáveis sem abstrações prematuras.

**Saída:** catálogo de componentes e comportamentos com exemplos e critérios de uso.

### Fase 6 — Protótipos e validação de fluxos

Explorar entidades contrastantes, por exemplo Evento, Projeto, Tarefa e registro financeiro. Validar também relações entre elas.

Testar:
- Visão compacta e ficha completa.
- Poucos e muitos registros.
- Um registro com múltiplos vínculos.
- Conclusão direta e edição.
- Navegação entre fichas e retorno.
- Diferentes tamanhos e orientações de tela.

**Saída:** experiência conceitual validada antes de qualquer implementação ampla.

### Fase 7 — Planejamento técnico e implementação incremental

Somente após aprovação:
- Separar mudanças de apresentação das mudanças de domínio/dados.
- Identificar componentes e telas afetados no código real.
- Definir dependências, lotes pequenos, reversíveis e testáveis.
- Especificar critérios de aceite e testes de regressão.
- Atualizar documentação técnica oficial no repositório ativo.
- Sincronizar o espelho e artefatos internos relevantes do AI Studio.

**Saída:** plano técnico autorizado, com escopo e critérios verificáveis.

### Fase 8 — Refinamento e validação contínua

- Aplicar responsividade e composições para tablets.
- Refinar transições, feedbacks e microinterações.
- Verificar acessibilidade, desempenho e movimento reduzido.
- Testar integridade de dados, cálculos, histórico, estados e navegação.
- Comparar a implementação real com os protótipos e critérios aprovados.

**Saída:** evidências de validação e registro do que foi efetivamente implementado.

## 2. Questões críticas ainda abertas

### Arquitetura e modelo mental
- Qual a diferença exata entre Objetivo, Projeto, Meta e Evento?
- Quais áreas são domínios, destinos ou lentes?
- Cinco destinos melhoram a descoberta ou criam agrupamentos artificiais?
- “Planos” ou “Planejamento” comunica corretamente a função?
- Como manter Eventos, Mercado e Biblioteca de Frases fáceis de encontrar?

### Cores e símbolos
- Qual canal deve representar relação sem competir com status?
- Como lidar com múltiplos vínculos?
- Quais cores, ícones e contornos atuais são regras semânticas?
- Quais símbolos da Agenda devem mudar?
- Como garantir reconhecimento sem depender de cor?

### Apresentação
- Quais entidades precisam de capa ou imagem?
- Quando expandir e quando abrir ficha?
- Como adaptar composição a poucos e muitos registros?
- Quais informações podem ser omitidas no resumo sem perder contexto?

### Interação e navegação
- Em quais contextos a conclusão direta é permitida?
- Como tratar recorrências e estados especiais?
- Qual contexto deve ser preservado ao retornar?
- Como evitar sobreposição de fichas e ações acidentais?

### Arquitetura técnica
- Quais mudanças são apenas de apresentação?
- Quais exigem alteração de navegação, estado, modelo ou regras?
- Quais componentes podem ser reaproveitados?
- Como manter modularidade sem criar abstrações para hipóteses ainda não validadas?

## 3. Critérios para considerar a reformulação bem-sucedida

- A arquitetura facilita encontrar e compreender as informações.
- As entidades mantêm identidade e fichas coerentes.
- A interface tem personalidade sem perder sobriedade.
- A densidade se adapta à quantidade e à natureza dos dados.
- Tipos, estados, relações e ações não se confundem.
- Conclusão, consulta, edição e navegação são previsíveis.
- A profundidade continua acessível.
- Celular e tablet têm composições adequadas.
- Nenhuma regra, dado, histórico ou funcionalidade é perdida sem decisão explícita.
- Testes e evidências confirmam a integridade da implementação.

## 4. Regra de execução

Nenhuma fase autoriza automaticamente a seguinte. Cada etapa precisa de escopo, evidências, decisões pendentes e critérios de aceite.

Não enviar ao Google AI Studio um pedido único para reconstruir o aplicativo inteiro. A implementação deve acontecer em etapas delimitadas, com inspeção do código, documentação técnica coerente, testes e validação no app real.

Registrar uma ideia nesta pasta não significa aprová-la. Aprovar uma direção visual não significa aprovar alteração no modelo de dados. E uma implementação só pode ser declarada após evidência no código e validação.
