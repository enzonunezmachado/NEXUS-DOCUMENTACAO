# 01 — Arquitetura da informação e organização das áreas

**Estado:** EM ANÁLISE  
**Objetivo:** investigar como o NEXUS deve organizar domínios, destinos, registros e contextos de uso, sem antecipar uma arquitetura final.

## 1. O problema que a arquitetura precisa resolver

O NEXUS reúne informações que se afetam mutuamente: tarefas, compromissos, eventos, projetos, objetivos, dinheiro, mercado e outras áreas pessoais. O usuário não deve precisar lembrar em qual módulo cada dado foi criado para conseguir encontrá-lo ou agir sobre ele.

A arquitetura precisa equilibrar:
- Acesso rápido ao que exige atenção agora.
- Visões temporais e operacionais.
- Áreas especializadas para criar, organizar e aprofundar informações.
- Relações entre registros sem duplicação.
- Descoberta simples para uso cotidiano e profundidade para uso avançado.

A navegação não deve ser definida apenas pela lista de funcionalidades existentes nem pela conveniência técnica da implementação atual. Deve refletir tarefas mentais reais do usuário.

## 2. Distinguir domínio, destino e lente

Antes de decidir abas ou agrupamentos, classificar cada elemento:

- **Domínio:** assunto ou conjunto de entidades com regras próprias, como tarefas, finanças ou mercado.
- **Destino:** área principal para a qual o usuário navega.
- **Lente:** visualização de registros que continuam pertencendo a outros domínios, como Agenda ou Hoje.
- **Ferramenta transversal:** função disponível em diferentes contextos, como busca ou criação.
- **Contêiner/agrupador:** área que reúne entidades relacionadas sem apagar suas diferenças.

Uma mesma tarefa pode aparecer em Hoje, Agenda, Tarefas e na ficha de um evento ou projeto, mas continua sendo um único registro. Agenda e Hoje não devem criar cópias independentes.

## 3. Proposta Claude: referência, não estrutura predeterminada

A proposta de 24/11/2026 sugeriu cinco destinos orientados por perguntas:

| Destino sugerido | Pergunta | Responsabilidade hipotética |
|---|---|---|
| Hoje | E agora? | Contexto imediato e prioridades |
| Agenda | Quando? | Organização temporal |
| Tarefas | O que fazer? | Execução e acompanhamento |
| Planos | Para quê e que ocasiões? | Objetivos, projetos e eventos |
| Dinheiro | Quanto? | Finanças pessoais |

Essa proposta é uma hipótese útil para comparação, não o ponto de chegada obrigatório. Em especial:
- Não assumir que cinco destinos são melhores que a navegação atual.
- Não adotar “Planos” sem testar se o nome é compreendido.
- Não tornar Eventos difíceis de descobrir.
- Não agrupar entidades apenas porque parecem relacionadas.
- Não encaixar Mercado em Dinheiro: Mercado é um ambiente de informações externas, distinto das finanças pessoais.
- Não presumir que Hoje deve substituir todos os rituais, históricos ou funções da Home atual.

A próxima análise deve comparar modelos genuinamente diferentes, incluindo a possibilidade de preservar parte da organização atual.

## 4. Registro único e múltiplas aparições

Um registro deve ser criado uma vez e apresentado em todos os contextos relevantes, mantendo identidade, estado e regras.

Exemplos:
- Uma tarefa pode aparecer em Tarefas, Hoje, Agenda e dentro do evento ou projeto ao qual está vinculada.
- Um evento pode apresentar tarefas, compromissos e custos relacionados.
- Um projeto pode mostrar tarefas e progresso, sem duplicar esses registros.
- Uma informação financeira pode ser consultada em sua área e em um contexto relacionado, sem criar um segundo lançamento.

A interface deve deixar claro quando está exibindo o mesmo registro em outra perspectiva.

## 5. Modelo mental e relações entre entidades

A hierarquia real ainda precisa ser compreendida. Não presumir que todas as relações formam uma árvore única.

A documentação técnica atual indica relações como:
- Tarefas com possíveis vínculos a projeto, evento e objetivo.
- Projetos relacionados a objetivos.
- Compromissos relacionados a eventos.
- Lançamentos financeiros relacionados a tarefa, projeto ou evento, conforme o modelo.

Isso não significa que uma tarefa sempre pertença a um único projeto, nem que todo evento esteja subordinado a um objetivo. Relações e cardinalidades devem ser verificadas no modelo e no código.

**Meta permanece um conceito a esclarecer.** Não tratá-la como sinônimo de Objetivo, Projeto ou marco sem confirmar sua definição no produto. A análise deve mapear o significado de cada entidade, sua finalidade, ciclo de vida, relações e diferença percebida pelo usuário.

## 6. Direções por contexto

Estas são responsabilidades desejadas, não layouts finais:

- **Hoje/Home:** síntese do contexto atual, prioridades e próximos passos; evitar transformar a tela em dashboard de gráficos ou lista infinita.
- **Agenda:** visão temporal coerente, preservando o que funciona na visão semanal e padronizando a leitura dos tipos de ocorrência.
- **Tarefas:** área operacional rápida, com filtros e profundidade, sem perder eficiência em listas numerosas.
- **Planejamento (nome provisório):** possível ambiente para organizar eventos, projetos e objetivos; precisa preservar a identidade de cada entidade e não pressupõe o nome final “Planos”.
- **Dinheiro:** domínio financeiro pessoal, com visão cotidiana e análises aprofundadas.
- **Mercado:** informações externas, acompanhamento e visualizações próprias; não confundir com patrimônio, orçamento ou lançamentos pessoais.
- **Biblioteca de Frases e outras áreas próprias:** preservar sua identidade e avaliar seu lugar na arquitetura, sem apagá-las por não caberem nos cinco destinos propostos.

## 7. Profundidade progressiva

A experiência pode oferecer níveis de aprofundamento:
1. Resumo no contexto em que o usuário está.
2. Visão da coleção ou do domínio.
3. Filtros, histórico ou análise específica.
4. Ficha completa da entidade.
5. Navegação para registros relacionados.

Esses níveis não precisam ser uma sequência obrigatória. O usuário deve chegar diretamente ao nível adequado à intenção, sem percursos artificiais.

## 8. Questões que precisam de investigação

- Quais são os domínios e entidades reais do aplicativo hoje?
- Qual a diferença funcional e mental entre Objetivo, Projeto, Meta e Evento?
- Quais relações são diretas, opcionais, múltiplas ou derivadas?
- Quais áreas são destinos e quais são lentes?
- A proposta de cinco destinos melhora descoberta e frequência de uso?
- “Planos” ou “Planejamento” é compreendido sem explicação?
- Como Eventos continuam fáceis de localizar?
- Como Hoje preserva revisão diária, histórico e outros rituais úteis?
- Onde Mercado, Biblioteca de Frases e ferramentas transversais devem aparecer?
- Como a arquitetura se comporta em celular e tablet?
