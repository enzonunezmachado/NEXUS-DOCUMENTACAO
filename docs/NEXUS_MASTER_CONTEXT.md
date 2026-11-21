# NEXUS — Documento Mestre de Continuidade

> **Fonte central de contexto, decisões, estado e próximos passos do projeto.**
> Atualizar este documento continuamente. Não criar cópias paralelas com o mesmo propósito.
> Última consolidação: 20/11/2026.

## 1. Identificação e separação dos repositórios

- Repositório de documentação e memória do projeto: [enzonunezmachado/PROJECT_NEXUS](https://github.com/enzonunezmachado/PROJECT_NEXUS)
- Branch de documentação: `main`
- Repositório ativo do Google AI Studio: [enzonunezmachado/PROJETO-NEXUS-](https://github.com/enzonunezmachado/PROJETO-NEXUS-)
- **Regra crítica:** este documento pode ser mantido no `PROJECT_NEXUS`. Nunca editar, renomear, apagar ou gravar arquivos no `PROJETO-NEXUS-` ao executar tarefas de documentação.
- O repositório `PROJECT_NEXUS` possui histórico de builds/experimentos e branches antigos. Preservar tudo; não apagar código, workflows, branches ou histórico para torná-lo "só documentação". Seu papel como referência documental pode coexistir com o conteúdo histórico já existente.
- O código-fonte mais recente é o workspace do Google AI Studio e o repositório ativo acima, não presumir que o código de branches antigos do `PROJECT_NEXUS` representa a versão atual.

## 2. Como usar este documento em qualquer conversa

Ao iniciar uma nova conversa sobre o NEXUS:
1. Abrir este documento no repositório `PROJECT_NEXUS`.
2. Ler especialmente as seções 3, 4, 6, 7, 8 e 9.
3. Tratar este documento como memória de decisões e continuidade; tratar o workspace atual do Google AI Studio como fonte da verdade para o código.
4. Não pedir ao usuário para repetir informações já registradas.
5. Antes de propor um novo lote, conferir o que já foi implementado, o que está pendente e as dependências.
6. Ao concluir cada lote, atualizar este mesmo arquivo in-place, com commit claro. Não criar outro documento mestre.
7. Se houver divergência entre este documento e o código atual, registrar a divergência e corrigir o documento após verificar o código; nunca presumir que o documento prova que algo está implementado.

## 3. Visão e princípios do produto

NEXUS é um centro de comando pessoal e profissional, não apenas um gerenciador de tarefas. Seu propósito é reduzir a carga mental de lembrar, acompanhar e coordenar responsabilidades, compromissos, eventos, trabalho, família e finanças, oferecendo segurança e tranquilidade operacional.

A experiência deve ajudar o usuário a entender:
- O que está acontecendo agora.
- O que vem a seguir.
- O que está atrasado ou exige atenção.
- O que foi concluído.
- Quais compromissos e obrigações precisam ser acompanhados.

Princípios:
- **Offline-first:** funções essenciais devem operar sem internet.
- **Gratuito no uso essencial:** não depender de APIs pagas, assinaturas ou serviços obrigatórios.
- **Sem conta obrigatória, backend ou Firebase obrigatório.**
- **Dados sob controle do usuário:** persistência local e backup/exportação.
- **Fonte única de verdade:** registros não devem ser duplicados entre módulos.
- **Integração entre módulos:** Agenda, Tarefas, Eventos, Compromissos e Finanças podem apresentar o mesmo contexto sem criar registros duplicados.
- **Divulgação progressiva:** Home resumida; módulos especializados contêm detalhes.
- **Interface compacta, consistente e confiável:** evitar telas excessivamente longas, carrosséis desnecessários e excesso de informação.
- **Evolução incremental:** um lote por vez, preservando funcionalidades e dados existentes.
- **Arquitetura modular:** adicionar recursos sem reescrever desnecessariamente os arquivos centrais.
- Não inventar dados, status, cotações ou resultados para preencher lacunas.

## 4. Estado técnico conhecido do aplicativo

Último estado documentado no relatório do projeto:
- Aplicativo Android nativo.
- Application ID: `com.nexus.app`.
- Version code: `7`.
- Version name: `5.2.0`.
- Kotlin + Jetpack Compose + Material 3.
- Room, versão do banco `DATABASE_VERSION = 6`, com 27 entidades no último registro.
- WorkManager e AlarmManager.
- Quatro widgets Jetpack Glance.
- Notificações Android.
- Persistência local/offline-first.
- Dados de Mercado e outros módulos também utilizam stores locais especializados.
- Backup estruturado em JSON, formato atual registrado como v7.
- Requisições HTTP com `HttpsURLConnection`.
- Sem backend/Firebase/conta obrigatória no desenho atual.

**Atenção:** esses números são o último estado relatado, não uma garantia de que nada mudou depois. Conferir o código atual antes de alterar versões, schema ou arquitetura.

Restrições técnicas:
- Nunca usar `fallbackToDestructiveMigration()`.
- Não alterar schema Room ou criar migrações sem um lote explicitamente planejado.
- Preservar compatibilidade com backups antigos.
- Não substituir dados reais por mocks ou valores fixos.
- Não introduzir serviços, dependências, workers, provedores ou APIs sem necessidade e escopo aprovados.
- Não remover funcionalidade para resolver erro de compilação; corrigir a causa preservando o comportamento.
- Não afirmar que build/testes foram executados se o ambiente não os executou.

## 5. Fluxo de desenvolvimento e colaboração

- O usuário trabalha principalmente no Google AI Studio Build mode.
- O assistente atua como parceiro de produto e arquiteto de prompts: organiza decisões, define escopo, dependências e produz prompts incrementais prontos para colar no AI Studio.
- O usuário controla o fluxo de workspace, sincronização/exportação, build e instalação. Não solicitar que ele repita essas etapas quando estiver pedindo apenas o próximo lote ou planejamento.
- O código deve ser alterado no workspace/repositório ativo do AI Studio, nunca no repositório documental por engano.
- O repositório documental `PROJECT_NEXUS` guarda decisões, histórico, arquitetura, status e backlog; não é a fonte do código Android atual.
- Cada prompt de implementação deve pedir ao AI Studio para consultar o contexto e atualizar o documento mestre ao concluir o lote.
- Escopo de cada lote: objetivo claro, arquivos diretamente relevantes, restrições, critérios de conclusão e relatório honesto.
- Não repetir auditorias gerais sem motivo concreto.
- Não misturar redesign, infraestrutura e funcionalidades não relacionadas no mesmo lote.

## 6. Histórico de implementação — módulo Mercado (Lotes 01–09)

**Status:** consolidado e encerrado em nível de código-fonte e revisão estática. Não expandir o módulo sem necessidade concreta.

### Lote 01 — USD/BRL
- Cotação USD/BRL na área financeira.
- AwesomeAPI primária e BCB Olinda/PTAX como fallback.
- Identificação da modalidade, variação quando disponível, cache local, atualização manual/automática e sincronização periódica.
- Offline-first e preservação da última cotação válida.

### Lote 02 — Euro e criptomoedas
- EUR/BRL, BTC/BRL, ETH/BRL e SOL/BRL.
- Fontes e fallbacks dedicados por categoria.
- Estados e formatação específicos por ativo.

### Lote 03 — Módulo Mercado dedicado
- Tela geral, detalhes de ativos, filtros e navegação.
- Entradas pela barra superior, Mais e Financeiro.
- Reutilização do mesmo repositório de cotações e cache.

### Lote 04 — Índices e ações
- IBOV, SPX, IXIC, PETR4, VALE3, ITUB4 e MELI.
- Moedas e unidades preservadas: BRL, USD e pontos de índice.
- Avisos de atraso/modalidade quando aplicáveis.
- Conversão informativa de MELI para BRL somente quando há cotação USD/BRL válida.

### Lote 05 — Histórico e gráficos
- Séries históricas e intervalos suportados por ativo.
- Gráficos nativos Compose Canvas, interação por toque/arraste e métricas do período.
- Cache local de histórico em arquivos JSON.
- Sem interpolar ou inventar pontos ausentes.

### Lote 06 — Favoritos / Watchlist
- Favoritos persistidos localmente.
- Ordem de inserção preservada e sem requisições duplicadas.
- Seção Acompanhamento e acesso ao detalhe.

### Lote 07 — Alertas de preço
- Alertas acima/abaixo do preço-alvo.
- Persistência local em JSON, pausa/reativação/exclusão.
- Reaproveitamento do mecanismo existente de sincronização de cotações.
- Canal de notificação próprio e deep-link para o ativo.
- Rearme exige retorno ao lado oposto do alvo, evitando disparos duplicados.

### Lote 08 — Backup do Mercado
- Backup JSON v7 inclui favoritos e alertas, incluindo metadados relevantes.
- Cache de cotações, histórico e estados transitórios ficam fora do backup.
- Compatibilidade com backups v1–v6: ausência dos campos de Mercado preserva a configuração local; listas explicitamente vazias limpam os respectivos dados.
- Restauração não deve disparar notificações.

### Lote 09 — Consolidação e encerramento
- Corrigidos textos obsoletos de escopo do Mercado e rótulos da versão de backup.
- Melhorada a coordenação da restauração Room ↔ favoritos/alertas, com snapshots e rollback em falhas.
- Nenhum novo ativo, provedor, worker, dependência ou alteração de schema Room.
- Documento de continuidade do workspace AI Studio atualizado naquele ambiente.

### Catálogo consolidado de 12 ativos
- Moedas: USD-BRL, EUR-BRL.
- Cripto: BTC-BRL, ETH-BRL, SOL-BRL.
- Índices: IBOV, SPX, IXIC.
- Ações: PETR4, VALE3, ITUB4, MELI.

### Limitações e validação do Mercado
- O relatório do Lote 09 confirma revisão estática, não build ou QA de dispositivo.
- Ainda precisam ser executados em ambiente Android/CI: `:app:testDebugUnitTest`, `:app:assembleDebug`, lint e validação de UI/HTTP/notificações/SAF.
- O relatório registra 15 suítes JUnit, mas elas não foram executadas naquele workspace.
- APIs públicas gratuitas podem sofrer indisponibilidade, rate limits ou mudanças.
- Room, SharedPreferences e arquivos JSON não formam uma transação distribuída física; rollback reduz inconsistências, mas morte abrupta do processo no instante crítico ainda é uma limitação residual.
- Não reabrir o Mercado apenas para expansão ou refinamento cosmético.

## 7. Backlog consolidado — funcionalidades e revisões

Status abaixo significa planejamento/pendência identificado nas conversas, não confirmação de ausência no código atual. Antes de implementar, verificar os arquivos diretamente relevantes.

### Prioridade A — Eventos evoluídos
- Rever cadastro e edição de eventos.
- Evoluir o campo/modalidade `locationMode`.
- Adicionar suporte a banner/imagem JPEG e definir armazenamento/recuperação.
- Melhorar apresentação visual e detalhes do evento.
- Integrar eventos à Agenda e lembretes.
- Preservar mídias e dados no backup ZIP.
- Distinguir evento de tarefa, compromisso e lançamento financeiro.

### Prioridade A — Recorrência de compromissos e eventos
- Recorrência de compromissos.
- Recorrência de eventos.
- Frequências e intervalos, início e término.
- Exceções por ocorrência: alterar/cancelar uma ocorrência sem modificar indevidamente toda a série.
- Evitar duplicação de registros e notificações.
- Integrar ocorrências à Agenda.
- A recorrência de tarefas já foi implementada em lote anterior; reutilizar regras existentes, sem duplicar mecanismos.

### Prioridade A/B — Backup ZIP
- Evoluir além do JSON v7 para incluir arquivos e mídias.
- Incluir imagens/banners associados a eventos.
- Definir manifesto, referências, validação e estratégia de restauração.
- Preservar compatibilidade com backups JSON antigos.
- Tratar falhas e restauração parcial com segurança.
- Não confundir ZIP com substituição do backup JSON já implementado.

### Prioridade A — Agenda
Decisões e direções discutidas:
- Visualização mensal como abertura preferencial, com dia atual selecionado.
- Quatro visualizações: Dia, Semana, Mês e Lista.
- Integração visual de tarefas, eventos, compromissos e lançamentos financeiros, mantendo os tipos de registro distintos.
- Filtros compactos, organizados por tipo e depois categoria.
- Pesquisa local acessível por controle compacto.
- Semana com dias em cartões horizontais e navegação por gesto quando viável.
- Evitar rolagem excessiva e controles duplicados.
- Não criar tarefa automaticamente para cada lançamento financeiro.
- Revisar estados vazios, navegação, seleção do dia e sincronização das fontes.

### Prioridade A — Home / Dashboard / Meu Dia
Visão acordada:
- Home como centro de comando e resumo, não como substituto dos módulos.
- Saudação/frase discreta.
- Indicadores rápidos: tarefas pendentes, compromissos, próximo evento, visão financeira.
- Gráficos compactos, barras de progresso e números úteis.
- Conteúdo mais importante no topo; evitar uma Home longa.
- Tarefas não devem dominar toda a tela.
- Navegação principal definida nas conversas: Início | Agenda | Tarefas | Finanças | Mais.
- Personalização dos blocos foi discutida, mas regras finais ainda precisam ser definidas: reordenar, ativar/desativar e comportamento de layout.
- Não transformar a Home em terminal de investimentos.

### Prioridade B — Financeiro
- Revisar resumo e hierarquia das informações.
- Melhorar gráficos de receitas/despesas.
- Considerar gráfico de rosca para distribuição por categoria.
- Tornar obrigações e compromissos financeiros futuros mais fáceis de entender.
- Integrar lançamentos à Agenda sem duplicar tarefas.
- Mercado oferece contexto macro; Financeiro continua sendo para finanças pessoais.

### Prioridade B — Identidade visual
- Finalizar splash screen.
- Consolidar letra N como elemento de identidade.
- Revisar ícone do aplicativo.
- Padronizar tipografia, espaçamentos, hierarquia, cartões, modais, botões e estados vazios/carregamento/erro.
- Direção visual discutida: tema escuro predominante, preto e branco, visual limpo e compacto.
- Evoluir gradualmente sem redesign amplo que comprometa funções existentes.

### Prioridade B — Widgets e notificações
- Existem quatro widgets Glance no último estado registrado.
- Rever utilidade, conteúdo e integração dos widgets com módulos.
- Revisar notificações de tarefas, compromissos e eventos.
- Evitar lembretes duplicados.
- Avaliar preferências de notificações e navegação por deep-link.

### Prioridade B — Configurações e personalização
- Centralizar preferências de visualização da Agenda, filtros, notificações, Home, widgets e backup.
- Evitar configurações isoladas e contraditórias em cada tela.
- Tema e preferências de apresentação devem respeitar a arquitetura existente.

### Futuro — Integrações Google/Android
- Avaliar Google Calendar, Google Drive e recursos nativos do Android.
- Integrações devem ser opcionais e substituíveis.
- Não introduzir dependência obrigatória de login, nuvem ou Firebase.
- Não implementar sem escopo e análise de custo/privacidade.

### Futuro — Assistente de IA
Ideia discutida: um assistente que consulta dados reais e executa operações simples do NEXUS.
Exemplos:
- Quando começa/termina uma parcela?
- Quais compromissos tenho amanhã?
- Quanto tenho de despesas no mês?
- O que está atrasado?
- Quais tarefas ainda faltam?
Requisitos:
- Começar por consultas simples aos dados locais.
- Nunca inventar dados quando não encontrar informação.
- Operações de escrita exigem permissões e confirmação quando relevantes.
- Avaliar execução local e conexão online opcional.
- Não depender de API paga para funções essenciais.

## 8. Sequência recomendada de desenvolvimento

A sequência abaixo é uma recomendação de planejamento, não autorização para implementar todos os lotes de uma vez:

1. Eventos evoluídos.
2. Recorrência de compromissos e eventos.
3. Backup ZIP com mídias/arquivos.
4. Revisão da Agenda.
5. Revisão da Home/Dashboard.
6. Evolução visual do Financeiro.
7. Identidade visual e splash screen.
8. Widgets, notificações e configurações.
9. Integrações opcionais Google/Android.
10. Assistente inteligente local/online opcional.

Validação técnica deve acompanhar os lotes, não ficar esquecida até o fim.

## 9. Regras para próximos lotes e prompts

Todo prompt de implementação deve:
- Consultar este documento primeiro.
- Inspecionar apenas arquivos diretamente relevantes e confirmar o estado atual.
- Descrever objetivo, escopo, critérios de conclusão e restrições.
- Preservar funcionalidades, dados, migrations, backup, notificações, widgets e navegação existentes.
- Não fazer refatoração ampla sem necessidade.
- Não adicionar dependências ou serviços pagos sem aprovação.
- Não inventar dados ou apresentar placeholders para funcionalidades já implementadas.
- Reutilizar repositórios, estados e infraestrutura existentes quando apropriado.
- Adicionar/atualizar testes pertinentes.
- Distinguir claramente: implementado, inspeção estática, testes executados, build executado e limitações do ambiente.
- Atualizar este mesmo arquivo no final, incluindo arquivos alterados, decisões, limitações, testes e próximo passo.
- Não criar um segundo documento de continuidade.

Preferências de colaboração:
- Respostas em português brasileiro, diretas e informais.
- Evitar repetição, auditorias desnecessárias e desperdício de tokens.
- Quando o usuário pedir o próximo lote, entregar prompt pronto para copiar, com escopo controlado.
- Não pedir ao usuário para repetir seu fluxo de exportação/sincronização/build quando não for necessário para responder.

## 10. Estado atual e próximo passo

- Mercado: Lotes 01–09 encerrados em nível de código-fonte/revisão estática.
- Validação Android do Mercado: pendente (build, JUnit, lint e QA em dispositivo/CI).
- Próxima frente recomendada: **Eventos evoluídos**.
- Antes de iniciar: conferir o estado real do módulo de Eventos no workspace ativo do Google AI Studio, identificar o que já existe e implementar apenas lacunas confirmadas.
- Não iniciar Agenda, Home, ZIP ou outros módulos no mesmo lote de Eventos.

## 11. Histórico de atualizações deste documento

| Data | Atualização |
|---|---|
| 20/11/2026 | Criação do documento mestre: visão do produto, arquitetura conhecida, separação dos repositórios, histórico dos Lotes 01–09 do Mercado, backlog consolidado, sequência recomendada e regras de continuidade. |

---
**Regra final:** este arquivo é a memória documental central do NEXUS. Atualize-o continuamente em vez de iniciar uma nova memória em cada conversa.


## 12. Inventário documental do repositório legado (revisado em 20/11/2026)

O conteúdo Markdown preexistente foi lido e classificado. Estes arquivos são preservados como documentação; nem todos representam o estado atual do código.

| Arquivo | Papel e uso correto | Confiabilidade temporal |
|---|---|---|
| `NEXUS_MASTER_CONTEXT.md` | Documento mestre atual: decisões, estado reportado, backlog e continuidade. Atualizar este arquivo continuamente. | Atual; sempre verificar código antes de declarar implementação. |
| `NEXUS_PRODUCT_SPEC.md` | Especificação de produto dos 13 módulos, regras de negócio, fluxos, QuickAdd, Agenda, financeiro, eventos, Mercado e backup. | Referência de comportamento desejado; alguns requisitos antecedem os lotes recentes. |
| `lista.md` | Consolidação detalhada das decisões do usuário sobre UX mobile, Agenda, tarefas, Financeiro, Dashboard, filtros, densidade, navegação e validação. | Fonte forte de decisões de UX; prevalece sobre preferências antigas quando houver conflito. |
| `NEXUS_DESIGN_SYSTEM.md` | Identidade visual, paletas, tipografia, espaçamento, ícones, estados e componentes/gráficos Compose. | Diretriz visual; conferir implementação atual antes de marcar aderência. |
| `NEXUS_INTEGRATION_MAP.md` | Relações entre módulos, fonte única, fluxos de dados, integridade referencial e lista INT-01..INT-10. | Auditoria histórica; bugs e pendências podem ter sido corrigidos em lotes posteriores. |
| `NEXUS_IMPLEMENTATION_ROADMAP.md` | Plano técnico antigo de cinco lotes: estabilização, financeiro visual, recorrência, eventos+ZIP, Dashboard/identidade/Mercado. | Roadmap anterior; sequência foi superada pelas decisões e entregas posteriores. Usar apenas como histórico de dependências. |
| `NEXUS_CURRENT_STATE.md` | Auditoria factual de uma baseline 5.2.0 / Room 5 / Backup 5 / 25 entidades, com classificação implementada/parcial/pendente e bugs. | Snapshot histórico anterior aos Lotes recentes; não é o estado atual. |
| `NEXUS_UPDATE_01_CONTROL_CENTER.md` | Prompt operacional antigo de continuidade e implementação, referenciando branch `integracao-google` e commit histórico. | Arquivo histórico; não executar literalmente nem assumir branch/commit atuais. |
| `NEXUS_ARCHITECTURE_AND_PROGRESS.md` | Documento técnico de fases antigas, schema Room v4, Backup v4 e recursos iniciais. | Snapshot antigo; preservar como histórico, nunca usar para inferir versão atual. |
| `reviewchat.md` | Registro bruto de ações, inspeções e edições realizadas em uma sessão anterior de desenvolvimento. | Log histórico, não especificação normativa nem evidência de estado atual. |
| `README.md` (raiz) | Entrada do repositório e link para o documento mestre. | Atual. |
| `docs/README.md` | Índice da pasta documental. | Atualizar para apontar os documentos e explicar seus papéis. |

### Divergências históricas que não devem ser confundidas

- Documentos antigos descrevem Room v4/v5, 25 tabelas/entidades, Backup v4/v5 e ausência de Mercado, banners, ou recorrência de compromissos/eventos. O estado mais recente relatado pelo Google AI Studio informa Room `DATABASE_VERSION = 6`, 27 entidades, Backup JSON v7 e Mercado implementado nos Lotes 01–09. Para código atual, o workspace do Google AI Studio é a fonte da verdade; este documento registra os relatórios recebidos, não substitui inspeção do código.
- A especificação e o roadmap antigos descrevem Mercado como bloco limitado no Dashboard e colocam sua implementação no fim do roadmap. Os Lotes 01–09 posteriores ampliaram o Mercado para módulo dedicado e o encerraram. Não reabrir essa frente por causa de texto antigo.
- O roadmap antigo priorizava recorrência completa e Eventos+ZIP em uma sequência diferente. A sequência recomendada atual, após encerrar Mercado, é: Eventos evoluídos; recorrência de compromissos/eventos; Backup ZIP com mídias; revisão da Agenda; Dashboard/Home; Financeiro e identidade visual.
- A auditoria `NEXUS_CURRENT_STATE.md` enumera INT-01..INT-10. Não assumir que esses bugs continuam presentes: conferir o código atual antes de reabrir qualquer correção.
- `NEXUS_PRODUCT_SPEC.md` é especificação de produto, não relatório de conclusão. `NEXUS_CURRENT_STATE.md` é retrato antigo de código, não lista atual de desejos. `lista.md` registra decisões de UX, não prova implementação.

## 13. Higienização e identidade deste repositório

- Objetivo: manter a branch padrão como repositório de documentação Markdown do NEXUS.
- Manter todos os arquivos `.md` encontrados no histórico da branch principal e arquivar versões distintas do documento de arquitetura que existiam em branches antigas.
- Remover da árvore atual da branch padrão código Android, Gradle, workflows e demais arquivos que não sejam documentação Markdown. O histórico Git anterior permanece nos commits; esta limpeza não reescreve nem apaga os commits antigos.
- A limpeza da branch padrão não deve tocar no repositório ativo `PROJETO-NEXUS-`.
- O nome pretendido para distinguir este repositório do repositório ativo é `NEXUS-DOCUMENTACAO`. A integração GitHub disponível nesta sessão não oferece uma operação de renomeação de repositório; até que o proprietário altere o nome em GitHub Settings, o endereço/canonical slug continua `PROJECT_NEXUS`. Não fingir que o nome foi alterado.
- Depois de cada lote, atualizar este documento mestre em vez de criar outra memória central.

## 14. Atualização do inventário

| Data | Alteração |
|---|---|
| 20/11/2026 | Leitura e classificação dos documentos preexistentes; registro das divergências de baseline; preparação do repositório para permanecer documental, preservando arquivos Markdown e histórico Git. |


## 15. Resultado da limpeza documental do repositório (20/11/2026)

- A branch `main` foi convertida em árvore documental: permanecem 14 arquivos Markdown (`.md`) e nenhum arquivo de código, configuração, workflow ou recurso não-Markdown na árvore atual.
- As 14 branches que não eram `main` também foram movidas para o mesmo commit documental, usando atualização com verificação do SHA anterior. Assim, todas as pontas de branch agora apresentam a mesma árvore composta somente por documentos Markdown.
- Os documentos de arquitetura diferentes encontrados nas branches `integracao-google` e `diagnostico-feature-base` foram preservados em `docs/archive/`. A versão que estava na `main` permanece na raiz.
- Os 70 arquivos não-Markdown que estavam na árvore principal foram removidos da árvore ativa. Os commits anteriores não foram reescritos; entretanto, commits exclusivos de branches que perderam suas referências podem deixar de estar alcançáveis por branches. Esta limpeza atende ao pedido de manter o repositório visivelmente documental.
- A renomeação do repositório não foi executada: as ferramentas GitHub disponíveis nesta sessão permitem ler e alterar arquivos/refs, mas não expõem a operação administrativa de renomear o repositório. Nome atual confirmado: `PROJECT_NEXUS`. Nome desejado documentado: `NEXUS-DOCUMENTACAO`. Para concluir, o proprietário precisa alterar o campo Repository name em GitHub → Settings → General e confirmar Rename.
- Repositório ativo `PROJETO-NEXUS-` não foi acessado para escrita nem modificado.
