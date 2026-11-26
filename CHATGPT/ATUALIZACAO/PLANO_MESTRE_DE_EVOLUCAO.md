# Plano mestre de evolução do NEXUS

**Estado:** PLANEJAMENTO CONSOLIDADO — execução por etapas  
**Atualizado:** 26/11/2026 — após validação prática da Rodada 3  
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
| 3 | Ponte ChatGPT → GitHub → Android | **CONCLUÍDA (26/11/2026)** | Correções na `main`, GitHub Actions #70 verde e importação real confirmada pelo usuário no aplicativo. Cenários adicionais seguem no gate contínuo |
| 4 | Completude funcional e integração entre domínios | **EM ANDAMENTO — etapa atual** | Recorrência de compromissos/eventos, checklists por ocorrência e em compromissos/eventos, identidade contextual, demais lacunas e auditoria de completude. Não expandir a ponte nesta rodada |
| 5 | Arquitetura da informação e experiência | **POSTERIOR — EM ANÁLISE** | Investigar, decidir, prototipar e implementar a experiência gradualmente; Claude é referência, não especificação final |

## 3. Rodada 3 — ponte concluída

### Evidências
- Correções integradas à `main` no commit `4041611a4ef4e111c3633790d0c09babdecb2ae8`.
- GitHub Actions #70 (run `45792119560`) concluída com sucesso na `main`, com testes unitários e APK Debug.
- Release foi ignorado por ausência dos secrets de assinatura; isso não invalida a aprovação do build Debug e dos testes executados.
- O usuário confirmou que o aplicativo funcionou no aparelho e que conseguiu importar registros pelo fluxo ChatGPT → repositório privado `NEXUS-INBOX` → NEXUS Android.

### Resultado
O gate de encerramento da Rodada 3 foi atendido: código corrigido na `main`, CI verde e importação real confirmada no dispositivo.

### Limites e acompanhamento
A confirmação de importação não deve ser ampliada para afirmar que todos os cenários foram testados individualmente. Descarte sem gravação, reconsulta sem duplicidade, sincronização periódica em segundo plano, notificações/deep-links, reinício/rotação e verificações adicionais de segurança do token permanecem cenários de confiabilidade a acompanhar. Eles não bloqueiam o início da Rodada 4.

A extensão da Inbox para regras recorrentes permanece na Rodada 4, depois da definição dos modelos de recorrência (`GAP-001`/`GAP-002`). Não fazer merge integral das antigas branches de correção.

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

## 6. Rodada 6 — finalizar a integração bidirecional ChatGPT ↔ NEXUS

A ponte atual da Rodada 3 continua funcionando para importar entradas pelo contrato v1. A expansão não será feita agora. A integração definitiva só começa depois da Rodada 4 (domínio funcional completo) e da Rodada 5 (experiência estabilizada), para não construir contratos sobre modelos ainda sujeitos a mudanças.

### Entregas
1. **Guia operacional oficial:** completar `docs/16_GUIA_OPERACIONAL_CHATGPT_NEXUS.md` a partir do código real, com exemplos JSON executáveis e validados. O ChatGPT consulta esse guia antes de preparar entradas. O Google AI Studio gera/atualiza o guia com base nos DTOs, enums, validadores, relações e testes.
2. **Catálogo de capacidades:** documento/JSON versionado que descreve o que a versão do NEXUS suporta: entidades, campos, relações, operações, limitações e versões de contrato. Separado dos dados pessoais.
3. **Snapshot de estado:** exportação compacta, selecionável e minimizada do estado atual relevante do aplicativo, com timestamp, versão e escopo. O backup completo não será o contexto padrão.
4. **Retorno de alterações:** recibos e/ou feed incremental das operações e mudanças produzidas pelo NEXUS.
5. **Sincronização manual bidirecional:** uma ação pode buscar entradas pendentes, validar e mostrar prévia para confirmação, depois enviar snapshot/recibos/alterações ao repositório. Exibir claramente importações, exportações, conflitos e falhas.
6. **Automação opcional:** avaliar sincronização periódica em segundo plano. WorkManager é best-effort; não prometer execução exata às 00h/05h. O usuário poderá pausar/desativar.
7. **Segurança:** não ampliar automaticamente o PAT read-only atual para read/write dentro do APK. Comparar GitHub App com credenciais de curta duração, relay mínimo e token fine-grained separado, restrito ao único repositório e com expiração. Keystore protege em repouso, mas não elimina exposição durante uso pelo processo Android.
8. **Local-first e privacidade:** NEXUS permanece útil sem internet/ChatGPT. Definir minimização de dados, escopo, retenção, exclusões, idempotência, conflitos, concorrência e fonte de verdade.

### Separação de artefatos
- Catálogo de capacidades: o que o aplicativo sabe fazer.
- Snapshot de estado: o que existe atualmente no aplicativo.
- Inbox: operações que o ChatGPT propõe ao NEXUS.
- Recibos/feed de alterações: o que o NEXUS executou ou mudou.

Os nomes e formatos finais serão definidos na especificação técnica da Rodada 6; não são contratos implementados hoje.

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

## 7. Gate contínuo de confiabilidade

Acompanha todas as rodadas:
- Keystore persistente e assinatura consistente de releases.
- Atualização in-place sem perda de dados.
- Migrações Room, backup, restauração completa e merge incremental.
- Segurança do token Inbox e ausência de credenciais no APK/logs/backups.
- Testes unitários e instrumentados quando apropriados e disponíveis no ambiente correto.
- GitHub Actions como validação de build/testes automatizados.
- Validação manual em aparelho real para UI, notificações, permissões, navegação e fluxos Android.
- Desempenho, acessibilidade e compatibilidade.

## 8. Governança para não criar pontas soltas

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

## 9. Fontes de acompanhamento

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

## 10. Próxima ação operacional

A Rodada 3 está concluída. A Rodada 4 está em andamento: fechar recorrência de Compromissos/Eventos, independência de checklists por ocorrência, checklists próprios para Compromissos/Eventos, identidade contextual e demais lacunas do domínio. O Google AI Studio deverá completar o guia operacional de entrada a partir do código estabilizado. Não expandir a ponte nesta etapa; a integração bidirecional definitiva é a Rodada 6, depois da Rodada 5.
