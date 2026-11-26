# Plano mestre de evolução do NEXUS

**Estado:** PLANEJAMENTO CONSOLIDADO — execução por etapas  
**Atualizado:** 26/11/2026  
**Autoridade:** plano estratégico-operacional. Não substitui código, requisitos técnicos, ADRs ou critérios formais de aprovação.

## 1. Objetivo

Conduzir a evolução do NEXUS sem pontas soltas: completar e integrar o comportamento funcional, consolidar os contratos entre entidades, corrigir inconsistências, e só então reformular a experiência visual e a arquitetura da informação com base no funcionamento real.

O NEXUS deve permitir que informações de naturezas diferentes se conectem sem perder sua identidade. Uma tarefa pode estar ligada a um projeto, evento ou objetivo; aparecer na Agenda, no Hoje e em uma ficha; ter checklist, recorrência, prazo e custo quando aplicável. A experiência precisa representar essas relações de forma exata, sem duplicar registros, misturar conceitos ou obrigar o usuário a administrar telas/formulários desnecessários.

**Princípio de ordem:** primeiro integridade e completude do domínio; depois arquitetura da informação; depois linguagem visual e implementação incremental; por fim refinamento contínuo.

## 2. Estado geral das rodadas

| Rodada | Objetivo | Estado | Resultado / próximo gate |
|---|---|---|---|
| 1 | Atualização e proteção dos dados | **CONCLUÍDA** | Motor de merge, preservação de dados e pipeline inicial; persistência de keystore e atualização in-place ainda pertencem ao gate contínuo de release |
| 2 | Importação e entrada estruturada | **CONCLUÍDA** | Prévia, mesclagem, contrato de entrada e processamento estruturado implementados; validar fluxos reais conforme dependências |
| 3 | Ponte ChatGPT → GitHub → Android | **EM ANDAMENTO** | Reaplicar na `main` as correções necessárias das branches de teste, CI verde na `main` e validar configuração/importação real no aparelho |
| 4 | Completude funcional e integração entre domínios | **PRÓXIMA** | Fechar lacunas de recorrência, checklist por ocorrência, eventos e consistência transversal antes do redesign amplo |
| 5 | Arquitetura da informação e experiência | **POSTERIOR — EM ANÁLISE** | Investigar, decidir, prototipar e implementar a experiência gradualmente; Claude é referência, não especificação final |

## 3. Rodada 3 — concluir a ponte

### Estado conhecido
- A implementação da ponte Inbox foi desenvolvida em branch de correção.
- A execução GitHub Actions #50 passou para testes unitários e APK Debug naquela branch/commit.
- Release foi ignorado por falta de secrets de assinatura.
- As branches de correção estão divergentes da `main`; decisão registrada: não fazer merge integral.
- A execução #50 não prova integração final na `main`, funcionamento do token real nem importação validada no aparelho.

### Sequência
1. Trabalhar sobre a `main` atual.
2. Inspecionar o código atual e comparar com as correções já validadas nas branches.
3. Reaplicar apenas correções ausentes, sem merge integral.
4. Atualizar documentação técnica e artefatos internos do AI Studio.
5. Executar GitHub Actions na `main` e resolver todas as falhas.
6. Configurar o repositório privado NEXUS-INBOX e PAT de leitura restrito.
7. Validar no aparelho: conexão, leitura, prévia, confirmação, descarte, idempotência e log de importação.
8. Registrar evidências, limites e resultado.

**Gate:** CI da `main` verde e fluxo real no aparelho validado. Não confundir build verde com validação de integração.

## 4. Rodada 4 — completude funcional antes da reformulação

Esta rodada deve verificar não só se cada módulo funciona isoladamente, mas se combinações reais de entidades continuam corretas.

### Ordem de execução

#### 4.1 Recorrência de compromissos e eventos — GAP-001 / REQ-009
- Modelar séries, frequência, intervalos, limites e exceções.
- Diferenciar compromisso pontual/recorrente de evento com duração, preparação, custos e vínculos.
- Permitir editar/excluir uma ocorrência ou a série, conforme decisão de produto.
- Projeção na Agenda, lembretes, identidade estável, backup/restore/merge e testes.
- Não simular série com vários registros avulsos.

**Gate:** criar uma série real e demonstrar ocorrências, exceções, notificações e persistência após reabertura/restauração.

#### 4.2 Checklist de tarefa recorrente — GAP-003 (investigação e especificação)
- Verificar como o checklist/subtasks está associado hoje à tarefa e como a recorrência materializa novas ocorrências.
- Definir modelo de checklist como estrutura reutilizável da série/modelo e estado de conclusão independente por ocorrência.
- Ao concluir itens do checklist de uma semana, a ocorrência seguinte deve iniciar com os itens novamente pendentes, sem reabrir nem alterar o histórico da semana anterior.
- Preservar nomes, ordem e conteúdo do checklist-base, permitindo alterações futuras com regra explícita sobre ocorrências antigas e futuras.
- Definir comportamento para editar o checklist-base, adicionar/remover/reordenar itens, pular uma semana, concluir parcialmente, reabrir ocorrência e editar uma ocorrência isolada.
- Verificar se a tarefa recorrente é virtual ou materializada e como a identidade de cada ocorrência é preservada.
- Não copiar cegamente o estado marcado da ocorrência anterior para a próxima.

**Exemplo de aceite:** tarefa “Checklist semanal” toda segunda-feira com três itens. Na semana 1, os três itens são concluídos. Na semana 2, a nova ocorrência apresenta os mesmos três itens, todos pendentes. A semana 1 permanece concluída no histórico. As duas ocorrências são independentes.

#### 4.3 Ponte Inbox e recorrência — GAP-002
- Estender o contrato em versão compatível para criar/atualizar regras e exceções de recorrência.
- Incluir preview, validação estrita, idempotência, conflitos e transação.
- Não alterar silenciosamente o contrato v1 nem representar recorrência como ocorrências únicas.

#### 4.4 Fechar lacunas do domínio Eventos
- REQ-012: compromissos na ficha do Evento.
- REQ-014: campos avançados e apresentação de Evento.
- REQ-026: backup ZIP e recursos de mídia de Eventos.
- Verificar dependências com recorrência, custos, tarefas, notificações e backup.

#### 4.5 Consistência transversal e defeitos conhecidos
- INT-01: unificar consumidores da AgendaEngine, aposentando o motor legado conforme plano técnico.
- INT-03: evitar persistência de checklist/subtarefas antes de salvar/confirmar edição.
- BUG-001: preservar navegação, rota e rascunhos na recriação/rotação.
- Verificar regras de conclusão, bloqueios, subtarefas pendentes, reabertura, tarefas recorrentes e ações em todos os contextos.
- Conferir que uma entidade aparece em várias telas sem duplicação nem divergência de estado.

#### 4.6 Auditoria de completude
- Cruzar requisitos, código, modelo de dados, telas, notificações, backup, importação e testes.
- Para cada requisito, registrar: existe? completo? integrado? testado? validado em aparelho? dependências? evidência?
- Classificar lacunas por impacto; não transformar todo item opcional em bloqueio.
- Atualizar requisitos, estado atual, rastreabilidade, ADRs, roadmap, registro de lacunas e espelho.

**Gate da Rodada 4:** fluxos críticos ponta a ponta especificados e validados, lacunas de alta prioridade fechadas ou explicitamente aceitas como pendentes, e mapa de capacidades atualizado. Não iniciar redesign amplo sobre comportamento incerto.

## 5. Rodada 5 — arquitetura da informação e experiência

A Rodada 5 aproveita a proposta Claude e os documentos de análise já existentes, sem copiá-los integralmente. O usuário quer preservar a identidade atual do NEXUS e também manter abertas as ideias estruturais interessantes da proposta, inclusive a reorganização de destinos/abas, até que sejam comparadas e decididas.

### Fases e saídas obrigatórias

| Fase | Trabalho | Saída verificável |
|---|---|---|
| 5.1 Auditoria do app real | Percorrer todas as áreas, telas, estados, ícones, títulos, alinhamentos, bordas, formulários e fluxos; comparar código e uso no aparelho | Inventário de telas e problemas com evidência |
| 5.2 Modelo mental e entidades | Mapear entidades, relações, cardinalidades, ciclos de vida e dependências; incluir recorrência, checklist, tarefas, compromissos, eventos, projetos, objetivos e finanças | Mapa de entidades/relacionamentos e glossário |
| 5.3 Arquitetura da informação | Comparar navegação atual com alternativas inspiradas no Claude; testar destinos, abas, nomes, agrupamentos e descoberta | Alternativas comparadas e decisão humana registrada |
| 5.4 Linguagem visual | Definir tipografia, alinhamento de títulos, hierarquia, espaçamento, superfícies, bordas/raios proporcionais, ícones, cores, estados, relações e seleção | Design system revisado e inventário semântico |
| 5.5 Componentes e interações | Reduzir aparência de formulário; definir linhas, cards, fichas, campos, botões, chips, controles e ações; separar consultar, concluir, editar e navegar | Catálogo de componentes e contratos de interação |
| 5.6 Protótipos | Explorar fluxos reais no Stitch ou ferramenta adequada, incluindo Agenda, Hoje, tarefas, eventos, dinheiro e conexões | Protótipos comparados em celular e tablet |
| 5.7 Implementação incremental | Aprovar escopo técnico por lote, mapear arquivos e dependências, implementar sem reescrita ampla | Lotes pequenos, testáveis e reversíveis |
| 5.8 Refinamento | Acessibilidade, responsividade, microinterações, desempenho, alinhamento e consistência final | Relatório de validação real e pendências restantes |

### Requisitos visuais já capturados
- Preservar a wordmark “NEXUS” atual, que o usuário considera satisfatória.
- Reavaliar o símbolo isolado “N” usado como ícone/capa do aplicativo; não presumir que a letra simples deve continuar.
- Explorar símbolo próprio, preferencialmente branco sobre fundo preto, sem decidir ainda o conceito final.
- Modernizar a interface e reduzir a sensação de formulário e de componentes quadrados/antigos.
- Buscar uma linguagem elegante, atual e levemente orgânica, sem virar uma interface infantil, excessivamente arredondada ou genérica.
- Melhorar alinhamento e consistência dos títulos, hierarquia tipográfica, espaçamentos e composição entre telas.
- Bordas e raios devem ser proporcionais ao tamanho e à função do componente; não aplicar aumento indiscriminado.
- Revisar ícones de todos os módulos, com prioridade especial para a Agenda.
- Distinguir visualmente o ícone que identifica o tipo de registro do controle que executa a ação de concluir.
- Preservar o caráter próprio do NEXUS e a navegação atual até decisão explícita; a proposta Claude continua uma alternativa importante, não descartada nem aprovada por inteiro.

### Critérios de aceite da experiência
- O usuário reconhece o tipo de registro sem confundir ícone com ação.
- Títulos, alinhamentos, espaçamentos e hierarquia são consistentes.
- A interface não parece uma pilha de formulários; campos aparecem quando a intenção é editar/criar, não como padrão de consulta.
- O mesmo registro mantém identidade e estado em Agenda, Hoje, Tarefas, Eventos e fichas.
- É possível consultar sem entrar imediatamente em edição; ações rápidas são inequívocas.
- A experiência continua eficiente em listas densas e telas pequenas.
- Celular e tablet recebem composições adequadas ao espaço.
- Tema claro e escuro, acessibilidade e contraste permanecem corretos.
- A wordmark preservada e o novo símbolo formam uma identidade coerente, caso o símbolo seja aprovado.

## 6. Gate contínuo de confiabilidade

Acompanha todas as rodadas:
- Keystore persistente e assinatura consistente de releases.
- Atualização in-place sem perda de dados.
- Migrações Room, backup, restauração completa e merge incremental.
- Segurança do token Inbox e ausência de credenciais no APK/logs/backups.
- Testes unitários e instrumentados quando apropriados e disponíveis no ambiente correto.
- GitHub Actions como validação de build/testes automatizados.
- Validação manual em aparelho real para UI, notificações, permissões, navegação e fluxos Android.
- Desempenho, acessibilidade e compatibilidade.

## 7. Governança para não criar pontas soltas

1. Nenhuma implementação começa apenas porque uma IA sugeriu.
2. Toda mudança é classificada como apresentação, interação, regra de domínio, modelo de dados, persistência, integração ou infraestrutura.
3. Antes de alterar algo, mapear dependências e consumidores afetados.
4. Cada recurso precisa de critérios de aceite, casos normais, exceções, estados vazios, recorrência, histórico, backup e importação quando aplicável.
5. Uma informação tem uma fonte de verdade; as outras telas são projeções, não cópias.
6. Não declarar concluído sem evidência proporcional: código, teste, CI e/ou validação no aparelho.
7. Atualizar documentação técnica e estratégica no mesmo lote; sincronizar espelho e artefatos internos do AI Studio quando disponíveis.
8. Registrar decisões humanas, hipóteses, propostas e implementação em estados diferentes.
9. Ao descobrir uma dependência nova, abrir subetapa/GAP e atualizar o plano antes de improvisar no código.
10. Antes de iniciar qualquer nova sessão de implementação, ler este plano, o estado atual, o registro de lacunas e os documentos técnicos relevantes.

## 8. Fontes de acompanhamento

- [Visão, escopo e governança](./00_VISAO_ESCOPO_E_GOVERNANCA.md)
- [Arquitetura da informação](./01_ARQUITETURA_DA_INFORMACAO.md)
- [Linguagem visual, cores e símbolos](./02_LINGUAGEM_VISUAL_CORES_E_SIMBOLOS.md)
- [Componentes, cards e fichas](./03_COMPONENTES_CARDS_E_FICHAS.md)
- [Interações, relações e navegação](./04_INTERACOES_RELACOES_E_NAVEGACAO.md)
- [Plano de evolução e questões abertas](./05_PLANO_DE_EVOLUCAO_E_QUESTOES_ABERTAS.md)
- [Evolução conceitual e arquitetural](./06_EVOLUCAO_CONCEITUAL_E_ARQUITETURAL.md)
- [Requisitos transversais e casos de uso](./07_REQUISITOS_TRANSVERSAIS_E_CASOS_DE_USO.md)
- [Registro técnico de lacunas](../../GOOGLE_IA/docs/15_REGISTRO_LACUNAS_E_FALHAS.md)
- [Roadmap técnico](../../GOOGLE_IA/docs/10_ROADMAP.md)
- [Proposta Claude desmembrada](../PROPOSTAS/2026-11-24_ARQUITETURA_INFORMACAO/CLAUDE_DESMEMBRADO/README.md)

## 9. Próxima ação operacional

Concluir a Rodada 3 na `main`. Em seguida, iniciar a Rodada 4 pelo levantamento técnico de GAP-001 e GAP-003 em conjunto, porque a semântica de recorrência e o ciclo de vida do checklist precisam ser compatíveis antes de ampliar o contrato Inbox. Não iniciar ainda a reformulação visual ampla.
