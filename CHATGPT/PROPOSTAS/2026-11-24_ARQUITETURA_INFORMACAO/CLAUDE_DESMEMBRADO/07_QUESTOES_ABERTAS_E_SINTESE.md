# Claude — Questões abertas e síntese final

> **Origem:** extração temática fiel da proposta completa do Claude, preservada sem reescrita substantiva.  
> **Estado:** PROPOSTA / EM ANÁLISE. Este material não representa decisão aprovada nem autorização de implementação.  
> **Fonte integral preservada:** `NEXUS — Proposta de Arquitetura da Informação e Experiência (2).md`. Consulte a fonte original para contexto contínuo e referências.

---

## M. Questões ainda não resolvidas

Cada linha diz **por que importa**, **como investigar** e **quem decide**. Nenhuma está respondida por esta análise.

| # | Questão | Por que importa | Como investigar | Quem decide |
| --- | --- | --- | --- | --- |
| 1 | Com que frequência você usa Eventos, Objetivos, Projetos e Mercado, e como os encontra hoje? | Decide se vale mudar as abas (E.4) | Anotar o uso por duas semanas ou fazer um teste de agrupamento com seus próprios registros | Você |
| 2 | “Planejamento”, “Planos” ou outro nome? | “Planejamento” hoje designa a tela de Objetivos | Teste de nomes com 5 a 8 funções reais | Você |
| 3 | A tarefa que aponta para um objetivo **e** está num projeto de outro objetivo causa inconsistência ou dupla contagem no progresso automático? | Afeta a confiança nos números | Ler as regras de progresso e montar um caso de teste | Técnico, depois você |
| 4 | Meta financeira deve se ligar a Objetivo ou a lançamentos? | Hoje são mundos separados; ligar exige dado novo | Decisão de produto | Você |
| 5 | Basta a ligação **derivada** entre evento e projeto (via tarefas), ou o evento precisa de vínculo direto? | Define o que a ficha do evento pode afirmar | Ver como você usa evento e projeto juntos | Você |
| 6 | Regras de conclusão: tarefa bloqueada, subtarefas pendentes, reabrir, lista × widget | A mesma ação hoje se comporta de formas diferentes | Testar no aparelho; decidir a regra única | Você, com verificação técnica |
| 7 | Tarefas recorrentes “virtuais” na Agenda podem ser concluídas diretamente? | Define o emblema da Agenda | Verificar no aplicativo | Técnico |
| 8 | Como o Voltar se comporta ao abrir um registro de dentro de outro? | `activeEditor` é variável única | Gravar vídeo: tarefa → evento → Voltar | Você grava; técnico interpreta |
| 9 | Estado por aba e na rotação: o que se perde hoje? | Pré-requisito de qualquer painel | Girar o aparelho em cada tela, com filtros e rolagem | Técnico |
| 10 | O “impacto no orçamento e na fatura” pode ser calculado sem dupla contagem? | Define a ficha de lançamento | Ler as regras financeiras | Técnico |
| 11 | “Comprometido” (horizonte de 12 meses) é viável sem dupla contagem entre fatura, parcela, recorrência e empréstimo? | A Parte I o sugeriu; permanece hipótese | Ler as regras e simular com dados de teste | Técnico |
| 12 | Quantos chips de relação ainda se leem bem? | O limite de cinco é hipótese | Teste de leitura com três vínculos por registro | Você |
| 13 | Uma paleta de identidade separada resolve o conflito com os estados? | Pode destravar capas e acentos | Protótipo com contraste e simulação de daltonismo | Design |
| 14 | Inventário completo de cores e ícones em todos os módulos | Só vi a Início | Percorrer Agenda, Eventos, Finanças, Planejamento e Mercado | Design e técnico |
| 15 | Capas e imagens de evento: vale criar dado novo? | Exige armazenamento, direitos e backup | Conversa de produto | Você |
| 16 | Limiares da Início (uma tela e meia, doze itens, tetos por bloco) | São hipóteses | Protótipo e teste dos dez segundos | Você |
| 17 | “Início” ou “Hoje” como nome? | “Início” hoje já é conhecido | Teste rápido | Você |
| 18 | Área de toque efetiva do círculo de conclusão (desenhado com 32 dp) | Precisa chegar a 48 dp | Conferir no aparelho | Técnico |
| 19 | A Busca deve cobrir mais tipos? | Hoje cobre tarefas, projetos, eventos, compromissos, lançamentos, cartões e objetivos; **não** cobre metas financeiras, faturas individuais, ativos de Mercado nem frases \[código: `GlobalSearchResults`\] | Decidir o que “achar sem lembrar o módulo” deve incluir | Você |
| 20 | A Agenda deve permitir concluir tarefas direto? | Hoje não encontrei como | Verificar e decidir | Você |

## N. Síntese final

### N.1 A direção geral mais promissora

1. **Construir o degrau que falta**: uma **ficha de consulta** para cada entidade, curta, padronizada em esqueleto e especializada em composição, com o editor existente atrás de um **lápis**.
2. **Reorganizar a Início por regras, não por mais blocos**: duas camadas (agora e atenção; áreas em resumo), com **um registro, uma aparição**, nomes que dizem a regra, urgência antes de fonte, tetos e “+N”, preservando a personalização.
3. **Tornar as relações visíveis e navegáveis** com chips de ícone e nome, só quando existem nos dados, derivados rotulados.
4. **Limpar a semântica visual antes de embelezar:** separar tipo, estado, vínculo e ação; resolver o acúmulo de significados do âmbar; não usar cor herdada como mecanismo principal.
5. **Manter as cinco abas atuais como ponto de partida** e tratar a reorganização de destinos como decisão posterior, dependente de dados de uso.

### N.2 O que esta análise esclareceu

- **Consultar e editar estão fundidos** porque só existem editores; a única ficha de consulta do app é a do Mercado \[código\].
- **Os problemas da Início têm causas específicas**: listas independentes, filtros por estado e não por tempo, ordenação por fonte \[código\].
- **“Meta” significa duas coisas** e o bloco de objetivos mistura duas entidades sem relação no modelo \[código\].
- **O modelo de dados limita o que as fichas podem prometer**: evento sem vínculo direto com projeto ou objetivo, projeto sem marcos nem participantes, sem capa de evento \[código\].
- **O conflito de cor é real**, já existe no âmbar e nos links \[código\]\[doc\].
- **A Parte I errou ou exagerou** em pontos que listei em C.3.

### N.3 Hipóteses ainda por testar

Frequência de uso por área; compreensão de “Planejamento/Planos”; limiares de densidade da Início; leitura de até cinco chips; paleta de identidade separada; folha inferior como refinamento; “Comprometido” sem dupla contagem; impacto derivado no orçamento; comportamento real ao tocar e ao voltar; estado na rotação.

### N.4 O que abandonar e o que preservar da Parte I

| Abandonar ou rebaixar | Preservar |
| --- | --- |
| Cinco destinos novos como estrutura-base | A distinção domínio, lente, agrupador e ferramenta |
| “Planos” como nome | Registro único e várias aparições |
| Eventos fora da barra | Chip de origem e faixa de conexões (com canal visual revisto) |
| Mercado dentro de Dinheiro | Profundidade progressiva |
| Esqueleto fixo na Início e dissolução do Assistente | Modo de adaptação por fluxo em tablet |
| “A edição completa abre a ficha” (misturava consulta e edição) | A lista de fluxos e as questões em aberto como método |
| O diagnóstico “sem pilha de voltar” (estava impreciso) | — |

### N.5 O que não decidir antes de uma auditoria mais completa

A arquitetura final das abas e o nome do quarto destino; qualquer cor de vínculo; a troca de ícones da Agenda; as regras de conclusão unificadas; o formato final de cada ficha; a existência de capas de evento; qualquer mudança em dados, regras ou persistência.

### N.6 O que esta proposta resolve, o que não resolve e o que depende de você

**Resolve (no plano da proposta):** a ausência de consulta separada de edição; a Início longa, repetitiva e mal ordenada; a invisibilidade das relações; o conflito entre cor de estado e cor de vínculo; a falta de critério para escolher entre linha, cartão, expansão e ficha; o pré-requisito para tablet.

**Não resolve:** a arquitetura final de destinos; nomes; a decisão sobre Metas financeiras e Objetivos; o desenho visual definitivo; a viabilidade do impacto financeiro derivado e do “Comprometido”; a paleta; qualquer afirmação sobre telas que não vi.

**Depende de validação humana:** as vinte questões de M, em especial 1, 2, 4, 5, 6, 15, 16 e 17, que são decisões de produto e não de verificação técnica.

### N.7 O que eu sugiro como próxima conversa (sem plano técnico)

1. **Dois vídeos curtos** ou capturas: tocar em tarefa, evento e lançamento; abrir Agenda, Eventos e Finanças. Resolvem as questões 7, 8, 14 e 20.
2. **Um teste de agrupamento de verdade:** você agrupa em papéis 15 registros seus. Resolve 1 e 2.
3. **Decisões suas sobre regras de conclusão e nomenclatura de Metas e Objetivos** (questões 4 e 6).
4. Só depois: protótipos de **três fichas** (tarefa, lançamento, evento) e de **uma Início em duas camadas**, antes de qualquer implementação.

## Referências e fontes desta rodada

**Documentação e código do projeto (lidos nesta rodada):** pasta `NEXUS-DOCUMENTACAO/CHATGPT/ATUALIZACAO/` (README e 00 a 06); `CHATGPT/PROPOSTAS/2026-11-24_ARQUITETURA_INFORMACAO/00_COMPARACAO_CRITICA_E_SINTESE.md`; no repositório `PROJETO-NEXUS-`: `docs/06_DESIGN_SYSTEM.md`, `MainActivity.kt`, `domain/rules/BackNavigationRules.kt`, `domain/rules/TaskRules.kt`, `data/db/entity/NexusEntities.kt`, `domain/model/NexusEnums.kt`, `domain/model/DomainModels.kt`, `data/repository/NexusRepository.kt` (snapshot da Início e conclusão de tarefa), `ui/components/NexusComponents.kt` (`TaskRowCard`), `ui/screens/HomeScreen.kt`, `ui/screens/AgendaScreen.kt`.

**Orientação externa (uso geral, não normativo):** documentação do Material Design sobre cartões (https://m1.material.io/components/cards.html) e visão geral de componentes Material no Android (https://developer.android.com/design/ui/mobile/guides/components/material-overview).

