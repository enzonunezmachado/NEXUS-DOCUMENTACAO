# Pendências estratégicas e direção futura

**Tipo:** quadro de acompanhamento estratégico  
**Status:** atualizado em 22/11/2026; estados precisam ser confrontados com a implementação antes de abrir lotes.

## 1. Correções e verificações prioritárias surgidas no teste

| ID | Tema | Estado | Próximo passo |
|---|---|---|---|
| BUG-001 | Perda da tela e dos dados de edição ao girar o dispositivo | Observado; correção solicitada; causa desconhecida | Análise técnica abrangente no código e proposta de correção |
| COR-001 | Remover N verde redundante antes do nome NEXUS | Correção visual solicitada; não implementada | Localizar ocorrências e propor ajuste sem mudar o ícone N branco |
| OBS-001 | Preservar alertas vermelhos para atraso e amarelos para conflitos | Comportamento positivo observado | Manter semântica em futuras mudanças |
| OBS-002 | Preservar fluxo da Agenda de selecionar data e consultar itens abaixo | Comportamento positivo observado | Não quebrar durante evolução da visualização |

Detalhes e critérios: [Problemas e correções pós-teste](TESTES/2026-11-21_TESTE_VIDAS_SIMULADAS/05_OBSERVACOES_POS_TESTE/01_PROBLEMAS_E_CORRECOES.md) e [resultado da exploração Gabriel](TESTES/2026-11-21_TESTE_VIDAS_SIMULADAS/05_OBSERVACOES_POS_TESTE/04_RESULTADO_EXPLORACAO_GABRIEL.md).

## 2. Melhorias de produto capturadas para análise consolidada

| ID | Tema | Estado |
|---|---|---|
| IDEA-001 | Agenda com visão inicial semanal e opção configurável Dia/Semana/Mês | Capturada / em análise |
| IDEA-002 | Calendários de feriados Brasil e EUA | Capturada / em análise |
| IDEA-003 | Onboarding: tutorial e integração da vida em fluxos separados | Capturada / em análise |
| IDEA-004 | Central Financeira personalizável | Capturada / em análise |
| IDEA-005 | Bloco Mercado no Financeiro independente dos favoritos | Capturada / em análise |
| IDEA-006 | Layout adaptativo para celular, tablet e telas maiores; melhoria da densidade, rolagem independente dos cartões semanais e composição adaptativa da Agenda | Em pesquisa / em análise |
| IDEA-007 | Temas visuais harmonizados; Grafite Suave é a direção preferida até aqui, mas precisa ficar um pouco mais cinza/confortável | Capturada / em análise; paleta e tokens não aprovados |
| IDEA-008 | Splash screen e entrada visual | Capturada / em análise |
| IDEA-009 | Animações discretas e acessíveis | Capturada / em análise |
| IDEA-010 | Pesquisa opcional de imagens em eventos | Capturada / em análise |
| IDEA-011 | Open Finance/importação de extratos | Capturada / análise de viabilidade |

Detalhes gerais: [Ideias de evolução pós-teste](TESTES/2026-11-21_TESTE_VIDAS_SIMULADAS/05_OBSERVACOES_POS_TESTE/02_IDEIAS_DE_EVOLUCAO.md). O refinamento do tema claro está em [Direção em análise — refinamento do tema claro](PROPOSTAS/2026-11-21_ROTACAO_E_IDENTIDADE_VISUAL/DIRECAO_TEMA_CLARO.md). A expansão da IDEA-006 está em [Direção de layouts adaptativos](PROPOSTAS/2026-11-21_ROTACAO_E_IDENTIDADE_VISUAL/DIRECAO_LAYOUTS_ADAPTATIVOS.md).

## 3. Direções futuras sem prioridade definida

- Assistente de IA opcional.
- Automação de tarefas por IA.
- Integração conversacional por WhatsApp.

Ver [Visão de longo prazo](TESTES/2026-11-21_TESTE_VIDAS_SIMULADAS/05_OBSERVACOES_POS_TESTE/03_VISAO_DE_LONGO_PRAZO.md). Não tratar como escopo do próximo lote.

## 4. Débitos e verificações técnicas reportadas anteriormente

Estes itens vieram do relatório de reorganização técnica e não são decisões estratégicas novas:
- Build, testes unitários e APK não verificados no ambiente utilizado.
- Testes instrumentados ausentes para migrações Room reais, SAF, Doze, BootReceiver, notificações/deep-links e Glance.
- Risco residual na coordenação física Room × arquivos/SharedPreferences durante interrupção abrupta.
- Arquivos centrais extensos, incluindo NexusRepository.kt e EntityEditors.kt.
- Ausência de gradlew e de app/schemas/ no estado inspecionado.
- ZIP real não exportado/inspecionado.
- Commit Git não realizado no container.

Consultar GOOGLE_IA para detalhes e confirmar se houve atualização posterior. Não converter automaticamente esses débitos em tarefas aprovadas.

## 5. Próximos marcos

1. Continuar a pesquisa da IDEA-006 sobre layouts adaptativos, distribuição em painéis, linguagem visual dos cartões e rolagem interna.
2. Inspecionar a implementação atual da Agenda e do Kanban antes de definir uma proposta técnica.
3. Produzir uma proposta consolidada e protótipo para validação do usuário, sem autorização automática de implementação.
4. Revisar a causa e a abrangência do BUG-001 separadamente.
5. Após aprovação explícita de escopo, criar lotes de implementação separados e registrar testes reais.
6. Atualizar o estado de cada ID com evidências, commit/versão e resultado.

## 6. Débitos documentais e continuidade

- Manter o catálogo central sem duplicar documentos detalhados dos experimentos.
- Registrar propostas ao AI Studio somente quando forem efetivamente enviadas.
- Após cada lote, confrontar código, docs técnicos versionados e artefatos internos relevantes do AI Studio.
- Preservar histórico e distinguir solicitado, aprovado, implementado e validado.

## 7. Direção de longo prazo

O NEXUS deve continuar evoluindo. Módulos, tecnologias e interfaces podem mudar profundamente. A memória deve preservar por que decisões foram tomadas e como o produto evoluiu, sem obrigar versões futuras a reproduzir escolhas antigas.


## 8. Documentação técnica e exploração visual

- **DOC-001 — Aplicar Diátaxis seletivamente à documentação:** CAPTURADA / EM PESQUISA. Avaliar se tutoriais, guias práticos, referência e explicações estão suficientemente distintos, sem reorganizar todos os documentos existentes por princípio.
- **DOC-002 — Explorar Obsidian como visualizador de relações:** CAPTURADA / EM PESQUISA. Testar com dados fictícios e notas Markdown; não presumir sincronização ao vivo com Room nem exportar dados reais.
- Documento principal: [Proposta Diátaxis + Obsidian](PROPOSTAS/2026-11-26_DOCUMENTACAO_DIATAXIS_OBSIDIAN/README.md).
- Próximo passo: comparar Graph View, Bases, MkDocs, diagramas e possíveis visualizações nativas; definir problema de navegação antes de recomendar integração.
- Nenhuma alteração de código, banco, backup, interface ou ADR foi autorizada.
