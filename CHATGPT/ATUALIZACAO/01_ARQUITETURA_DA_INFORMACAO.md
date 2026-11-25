# 01 — Arquitetura da informação e organização das áreas

**Estado:** EM ANÁLISE  
**Referência estrutural:** proposta Claude de 24/11/2026, sujeita a confronto com o aplicativo e as regras reais.

## 1. Hipótese estrutural de referência

A proposta Claude organiza a navegação principal em cinco destinos orientados por perguntas:

| Destino proposto | Pergunta | Responsabilidade sugerida |
|---|---|---|
| Hoje | E agora? | Contexto imediato, prioridades e visão do dia |
| Agenda | Quando? | Visão temporal unificada |
| Tarefas | O que fazer? | Itens acionáveis e execução |
| Planos | Para quê e que ocasiões? | Objetivos, projetos e eventos, se a organização for validada |
| Dinheiro | Quanto? | Finanças pessoais e análise financeira |

A nomenclatura e a quantidade de destinos ainda precisam ser testadas. “Planos” pode ser abstrato; Eventos não podem ficar difíceis de descobrir; Mercado não deve ser tratado automaticamente como parte do dinheiro pessoal, pois apresenta informações externas.

A proposta também considera Hoje como central sintética, e Agenda/Busca como lentes que apresentam registros de outras áreas sem duplicá-los.

## 2. Princípio de registro único e múltiplas aparições

Um registro deve ser criado uma vez e aparecer nos contextos relevantes sem gerar cópias independentes. Exemplos existentes documentados incluem tarefas na Agenda, tarefas ligadas a eventos e custos associados a projetos/eventos.

A reformulação deve tornar essas aparições coerentes: a mesma entidade deve manter identidade, estado e comportamento reconhecíveis em todos os lugares onde aparece.

## 3. Fichas de origem e navegação entre contextos

Cada tipo de registro deve ter uma ficha própria de consulta, com identidade e conteúdo adequados à entidade. A ficha deve ser acessível a partir de diferentes contextos sem criar cópias do registro.

A proposta Claude menciona chip de origem e faixa de conexões. São padrões a explorar, não componentes aprovados. Devem explicar relações em linguagem natural quando isso ajudar, por exemplo “parte de”, “custa”, “prepara”, “contribui para” ou “vence em”.

## 4. Profundidade progressiva

Hipótese de níveis:
1. Resumo contextual em uma tela.
2. Visão do módulo ou coleção.
3. Filtro ou análise mais específica.
4. Ficha completa do registro.
5. Navegação para registros relacionados.

Nem todo caminho precisa percorrer todos os níveis. O acesso deve ser curto quando a intenção do usuário for clara.

## 5. Planos e hierarquia de entidades

A proposta Claude agrupa Projetos, Objetivos e Eventos em uma área de planejamento, mas suas diferenças funcionais precisam ser preservadas.

O modelo técnico documentado atualmente indica, entre outras relações:
- Tarefas podem ter `projectId`, `eventId` e `goalId`.
- Projetos podem ter `goalId`.
- Compromissos podem ter `eventId`.
- Lançamentos podem ter vínculos com tarefa, projeto ou evento, conforme o modelo.

Isso não demonstra uma hierarquia única em árvore. Uma tarefa pode participar de mais de um contexto. Não presumir herança visual ou prioridade de um vínculo sobre outro.

A conversa levantou a necessidade de revisar o que exatamente significam Objetivo, Projeto, Evento e Meta no produto. A definição deve ser confrontada com `04_MODELO_DADOS.md`, especificação e código antes de qualquer mudança.

## 6. Experiência por área

Direções iniciais, não layouts finais:

- **Hoje:** síntese contextual, leitura imediata, sem excesso de blocos ou gráficos.
- **Agenda:** representação temporal clara, símbolos consistentes por tipo de ocorrência, preservando as qualidades da visão semanal atual.
- **Tarefas:** apresentação compacta, fácil de executar e capaz de acomodar muitas tarefas de contextos diferentes.
- **Planejamento/Planos:** apresentação mais visual e expressiva para eventos, projetos e objetivos, com diferenças de composição por entidade.
- **Dinheiro:** ambiente analítico, com gráficos e indicadores; degradês podem ser explorados com moderação e legibilidade.
- **Mercado:** ambiente de informação externa, com identidade tecnológica própria e visualizações expressivas, sem confundir ativos de mercado com finanças pessoais.

## 7. Questões para validação

- Cinco destinos são melhores que a navegação atual?
- “Planos” é compreendido naturalmente?
- Eventos precisam de entrada direta ou podem ser descobertos em Planejamento?
- Como Mercado se encaixa na navegação sem ser confundido com patrimônio pessoal?
- Quais informações pertencem à Home/Hoje e quais devem permanecer nos módulos?
- Como a proposta se comporta nos fluxos reais: organizar uma semana, preparar evento, executar projeto e consultar finanças?
