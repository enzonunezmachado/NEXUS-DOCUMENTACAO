# NEXUS — Documento Vivo de Arquitetura e Controle de Progresso

> **Princípio permanente:** Nenhuma funcionalidade é marcada como concluída apenas porque sua tela existe. Este documento registra arquitetura, schema completo, decisões técnicas, status por fase e matriz de testes (aprovados, reprovados, pendentes e limitações de ambiente).

---

## 1. Análise Arquitetural e Decisões Técnicas

- **Stack escolhida:** Android Nativo com **Kotlin + Jetpack Compose (Material 3)**, projeto de módulo único (`:app`) e Activity única (`MainActivity`), **Room (SQLite v4)** como fonte única da verdade, **`kotlinx.serialization`** para backup/restauração tolerante entre versões, **AlarmManager + WorkManager (`androidx.work:work-runtime-ktx`) + BroadcastReceivers** para notificações nativas offline com o app fechado e **Jetpack Glance (`1.1.1`)** para widgets nativos da tela inicial.
- **Justificativa frente a alternativas (ex.: React Native):** Notificações após reboot (`BOOT_COMPLETED`), reagendamento de alarmes em alterações de fuso, ações interativas diretamente na notificação alterando o SQLite sem abrir UI e múltiplos widgets interativos na tela inicial exigem código nativo Android. Usar Kotlin + Compose + Room elimina ponte JS/Nativa e garante APK único, rápido, offline-first e sem custo recorrente.
- **Privacidade e custo zero:** Sem permissão `INTERNET` no `AndroidManifest.xml`, sem backend, sem login, sem Firebase, sem IA paga. A internet só é acionada externamente via `Intent.ACTION_VIEW` ao abrir um link oficial, ingresso ou endereço no Google Maps/rotas.
- **Localização:** `pt-BR`, moeda `BRL (R$)`, datas `DD/MM/AAAA`, hora `24h`, fuso `America/Sao_Paulo` na exibição (armazenamento interno em epoch UTC ms).

---

## 2. Preservação de Dados nas Atualizações e Backup

1. **Identidade estável do APK:** `applicationId = "com.nexus.app"` preservado entre fases para atualização in-place sem desinstalar (`versionCode = 5`, `versionName = "5.0.0"`).
2. **Chaves primárias UUID em texto:** Geradas no aplicativo (`UUID.randomUUID().toString()`).
3. **Zero recriação destrutiva:** `fallbackToDestructiveMigration()` é proibido no `NexusDatabase`. A evolução utiliza `NexusMigrations.MIGRATION_1_2`, `NexusMigrations.MIGRATION_2_3` e `NexusMigrations.MIGRATION_3_4` explícitas e `exportSchema = true` (`app/schemas/`), alcançando a versão **v4 (25 tabelas)** na Fase 6 sem perder nenhum registro anterior.
4. **Backup externo JSON (SAF) v4 compatível com v1, v2 e v3:** Exportação/importação via Storage Access Framework sem pedir permissão de armazenamento; contém `formatVersion = 4`, `appVersion = "5.0.0"`, `exportedAt`, `databaseVersion = 4` e todas as 25 tabelas; parser tolerante (`ignoreUnknownKeys = true`, `coerceInputValues = true`, valores padrão) aceita backups v1, v2, v3 ou v4 e restaura em transação única (`withTransaction`) com saneamento de chaves estrangeiras, reagendamento automático dos lembretes e atualização imediata dos widgets.
5. **Exportação CSV financeira e importação/exportação de frases:** Exporta lançamentos financeiros via SAF (`text/csv`, separador `;`, cabeçalho e valores formatados) e permite importar/exportar a biblioteca local de frases em `.json` ou `.txt` via SAF.

---

## 3. Modelo Relacional Implementado (`NexusDatabase v4` — 25 Tabelas)

### Base & Produtividade (Fase 1 + vínculo opcional com Objetivos na Fase 6)
- `settings` (`id`, `userName`, `darkTheme`, `declaredBalanceCents`, `createdAt`, `updatedAt`)
- `categories` (`id`, `name`, `colorHex`, `scope`, `isDefault`, `createdAt`, `updatedAt`)
- `projects` (`id`, `name`, `description`, `deadlineAt`, `status`, `priority`, `goalId` FK `SET_NULL`, `createdAt`, `updatedAt`)
- `tasks` (`id`, `title`, `description`, `projectId` FK `SET_NULL`, `categoryId` FK `SET_NULL`, `eventId` FK `SET_NULL`, `goalId` FK `SET_NULL`, `priority`, `status`, `dueAt`, `hasDueTime`, `notes`, `completedAt`, `createdAt`, `updatedAt`)
- `subtasks` (`id`, `taskId` FK `CASCADE`, `title`, `isCompleted`, `sortOrder`, `createdAt`, `updatedAt`)
- `appointments` (`id`, `title`, `description`, `categoryId` FK `SET_NULL`, `startAt`, `endAt`, `isAllDay`, `location`, `link`, `createdAt`, `updatedAt`)
- `error_logs` (`id`, `source`, `message`, `stackTrace`, `occurredAt`, `createdAt`, `updatedAt`)

### Eventos & Financeiro (Fases 2 e 3 via `MIGRATION_1_2`)
- `events` (`id`, `name`, `description`, `categoryId`, `startAt`, `endAt`, `isAllDay`, `officialLink`, `ticketLink`, `locationName`, `address`, `city`, `state`, `notes`, `eventStatus`, `ticketStatus`, `ticketPriceCents`, `ticketQuantity`, `ticketFeesCents`, `ticketTotalCents`, `ticketPurchaseDate`, `ticketDueDate`, `paymentMethod`, `creditCardId`, `installmentsCount`, `createdAt`, `updatedAt`)
- `credit_cards` (`id`, `name`, `institution`, `totalLimitCents`, `availableLimitCents`, `closingDay`, `dueDay`, `colorHex`, `notes`, `createdAt`, `updatedAt`)
- `credit_card_statements` (`id`, `creditCardId` FK `CASCADE`, `yearMonth`, `closingAt`, `dueAt`, `status`, `paidAt`, `paidAmountCents`, `createdAt`, `updatedAt`)
- `installment_purchases` (`id`, `description`, `totalAmountCents`, `installmentsCount`, `installmentAmountCents`, `firstDueAt`, `creditCardId`, `categoryId`, `eventId`, `projectId`, `notes`, `createdAt`, `updatedAt`)
- `recurring_rules` (`id`, `title`, `type`, `amountCents`, `categoryId`, `paymentMethod`, `creditCardId`, `frequency`, `customIntervalDays`, `startAt`, `endAt`, `maxOccurrences`, `isActive`, `notes`, `createdAt`, `updatedAt`)
- `transactions` (`id`, `type`, `description`, `amountCents`, `categoryId`, `status`, `paymentMethod`, `occurredAt`, `dueAt`, `settledAt`, `projectId`, `eventId`, `eventCostType`, `isTicketObligation`, `creditCardId`, `statementId`, `installmentPurchaseId`, `installmentNumber`, `installmentsTotal`, `recurringRuleId`, `occurrenceIndex`, `notes`, `createdAt`, `updatedAt` — com índice único `(recurringRuleId, occurrenceIndex)`)
- `loans` (`id`, `creditor`, `originalAmountCents`, `contractedAmountCents`, `installmentsTotal`, `installmentAmountCents`, `paidInstallments`, `nextDueAt`, `remainingBalanceCents`, `finalDateAt`, `notes`, `createdAt`, `updatedAt`)
- `budgets` (`id`, `categoryId` FK `CASCADE`, `yearMonth`, `limitCents`, `createdAt`, `updatedAt` — índice único `(categoryId, yearMonth)`)
- `financial_goals` (`id`, `name`, `targetAmountCents`, `currentAmountCents`, `deadlineAt`, `categoryId`, `notes`, `createdAt`, `updatedAt`)
- `audit_history` (`id`, `entityType`, `entityId`, `action`, `summary`, `occurredAt`, `createdAt`, `updatedAt`)

### Notificações Nativas & Assistente de Rotina (Fase 4 via `MIGRATION_2_3`)
- `reminders` (`id`, `sourceType`, `sourceId`, `category`, `title`, `message`, `triggerAt`, `originalDueAt`, `minutesBefore`, `channelId`, `status`, `snoozedUntil`, `isExactScheduled`, `firedAt`, `createdAt`, `updatedAt` — índice único `(sourceType, sourceId, category)`)
- `routine_assistant_settings` (`id`, `notificationsEnabled`, `scheduleChannelEnabled`, `financeChannelEnabled`, `eventsChannelEnabled`, `routineChannelEnabled`, `morningBriefingEnabled`, `morningBriefingHour`, `morningBriefingMinute`, `nightReviewEnabled`, `nightReviewHour`, `nightReviewMinute`, `quietHoursEnabled`, `quietHoursStartHour`, `quietHoursStartMinute`, `quietHoursEndHour`, `quietHoursEndMinute`, `defaultTaskReminderMinutes`, `defaultAppointmentReminderMinutes`, `defaultEventReminderMinutes`, `defaultFinanceReminderMinutes`, `createdAt`, `updatedAt`)
- `briefing_history` (`id`, `briefingType`, `dateIso`, `summaryText`, `tasksCount`, `appointmentsCount`, `billsDueCount`, `overdueCount`, `reviewedAt`, `createdAt`, `updatedAt` — índice único `(briefingType, dateIso)`)

### Configuração Multi-instância de Widgets (Fase 5 — isolada por instância)
- Os 4 widgets consultam exclusivamente as tabelas do Room via `NexusRepository.loadAllWidgetSnapshots()` (nenhum dado de domínio duplicado).
- Preferências visuais por instância (`appWidgetId`, modo compacto, limite de itens e seções visíveis) ficam isoladas em `NexusWidgetConfigStore` (`SharedPreferences("nexus_widget_instance_prefs")`), com limpeza automática em `onDeleted(context, appWidgetIds)`.

### Planejamento, Personalização e Biblioteca Local de Frases (Fase 6 via `MIGRATION_3_4`)
- `personalization_settings` (`id`, `greetingEnabled`, `greetingTemplate`, `dateFormatPattern`, `showDailyQuoteOnDashboard`, `compactDashboardMode`, `showMetricSummaryOnDashboard`, `dashboardBlocksJson`, `lastQuoteDateIso`, `lastQuoteId`, `previousQuoteId`, `createdAt`, `updatedAt`)
- `quotes` (`id`, `text`, `author`, `isActive`, `lastShownDateIso`, `shownCount`, `createdAt`, `updatedAt`)
- `personal_goals` (`id`, `title`, `description`, `categoryId` FK `SET_NULL`, `targetDateAt`, `status`, `progressMode`, `manualProgressPercent`, `completedAt`, `notes`, `createdAt`, `updatedAt`)
- `goal_milestones` (`id`, `goalId` FK `CASCADE`, `title`, `description`, `dueAt`, `isCompleted`, `completedAt`, `sortOrder`, `createdAt`, `updatedAt`)
- `goal_progress_logs` (`id`, `goalId` FK `CASCADE`, `progressPercent`, `note`, `recordedAt`, `createdAt`, `updatedAt`)
- Migração aditiva em `projects` e `tasks`: coluna opcional `goalId` (`FOREIGN KEY(goalId) REFERENCES personal_goals(id) ON DELETE SET NULL` + índice `index_projects_goalId` / `index_tasks_goalId`) para vincular projetos e tarefas existentes sem criar registros duplicados.

---

## 4. Regras de Negócio Críticas Implementadas

- **Agenda unificada sem cópias (`observeUnifiedAgenda`):** Consulta diretamente 6 fontes originais (`appointments`, `tasks` com prazo, `events`, `transactions` com vencimento, `credit_card_statements` em aberto/vencidas, e `projects` com prazo). Tocar no item abre o editor do registro original.
- **Cartões e faturas sem dupla contagem:** Compras no cartão entram em `transactions` e são associadas automaticamente à fatura pela data de fechamento; pagar a fatura marca a fatura como `PAID` e liquida as compras vinculadas como `SETTLED`, sem criar uma segunda despesa. Faturas vencidas em aberto são marcadas automaticamente como `OVERDUE` (`effectiveStatementStatus`), e o limite disponível do cartão (`computedRemainingLimitCents`) reflete a soma real em aberto.
- **Eventos, parcelamento de ingressos e projetos:** Evento gratuito não gera obrigação financeira; ingresso parcelado no cartão cria `InstallmentPurchaseEntity` + `N` transações vinculadas à fatura; liquidações parciais ou totais atualizam o status do ingresso (`PARTIALLY_PAID` / `PAID`). Projetos agregam automaticamente custos previstos e pagos a partir das transações vinculadas.
- **Exclusão segura preservando histórico:** Excluir `installment_purchases` ou `recurring_rules` remove apenas lançamentos não liquidados, desvinculando e preservando transações já pagas/recebidas no histórico.
- **Recorrências idempotentes:** `generateOccurrencesForRuleInternal` verifica `(recurringRuleId, occurrenceIndex)` com `OnConflictStrategy.IGNORE` no índice único do SQLite, garantindo zero duplicação ao reabrir o app.
- **Empréstimos sem cálculo irreal:** Ao registrar pagamento de parcela, o usuário informa o saldo devedor atualizado manualmente.
- **Notificações offline & Assistente de Rotina:**
  - `AlarmManager` (`setExactAndAllowWhileIdle` com fallback `setAndAllowWhileIdle`) + `WorkManager` periódico de 15 minutos (`NexusReminderSyncWorker`) + `NexusBootReceiver` (`BOOT_COMPLETED`, `TIMEZONE_CHANGED`, `TIME_SET`).
  - 4 canais nativos (`nexus_schedule`, `nexus_finance`, `nexus_events`, `nexus_routine`) e fluxo completo de permissão `POST_NOTIFICATIONS` (Android 13+) + `ACTION_REQUEST_SCHEDULE_EXACT_ALARM` (Android 12+).
  - Ações diretas na notificação (`NexusNotificationActionReceiver`): concluir tarefa, marcar lançamento como pago, adiar 15 min, dispensar ou marcar briefing como revisado direto no SQLite.
  - Modo silêncio respeitando janela diurna ou noturna (cruzando meia-noite) no fuso `America/Sao_Paulo`, além de cancelamento seletivo por categoria (`FINANCIAL` ao quitar vs `SCHEDULE`).
  - Assistente de Rotina com **Briefing Diário**, **Revisão Noturna** (adiamento em 1 toque para amanhã) e gestão de **Notificações** + fila persistida em SQLite.

- **Widgets Nativos Jetpack Glance (Fase 5):**
  - **4 widgets reais registrados no `AndroidManifest.xml`** com seus respectivos `GlanceAppWidgetReceiver` e metadados XML em `res/xml/widget_*_info.xml`:
    1. `MyDayWidgetReceiver` (`MyDayGlanceWidget`): data atual (`pt-BR`), compromissos do dia e horários (com sinalização de conflito), tarefas com prazo hoje, contagem de pendentes e concluídas, estado de agenda livre e abertura direta do item ou da Agenda na data de hoje.
    2. `MyTasksWidgetReceiver` (`MyTasksGlanceWidget`): tarefas priorizadas por urgência (`URGENT > HIGH > NORMAL > LOW`) e prazo, nome do projeto, prazo, prioridade/status, contador de pendentes e **conclusão segura em 2 etapas diretamente no widget** (`[Confirmar]` / `[Cancelar]` com TTL de 15s em `NexusWidgetConfigStore`). Tarefas `BLOCKED`, `COMPLETED` ou `CANCELLED` têm a conclusão direta bloqueada.
    3. `FinanceWidgetReceiver` (`FinanceGlanceWidget`): exibe o **Saldo Declarado** estritamente separado dos totais calculados, diferencia receitas/despesas **Previstas** e **Realizadas**, mostra vencidos e **faturas abertas** (calculadas sem dupla contagem), e lista os próximos vencimentos com atalho direto ao lançamento ou cartões.
    4. `NexusSummaryWidgetReceiver` (`NexusSummaryGlanceWidget`): visão consolidada de tarefas pendentes, compromissos de hoje, próximos eventos, contas a vencer/faturas abertas e metas financeiras (seção exibida apenas quando houver metas cadastradas).
  - **Tamanhos responsivos (`SizeMode.Responsive`) & Multi-instância:** Suporte nativo a `SMALL` (130×100dp), `MEDIUM` (240×130dp) e `LARGE` (280×240dp) com redimensionamento horizontal/vertical e `NexusWidgetConfigActivity` (`APPWIDGET_CONFIGURE`) permitindo configurar cada instância (`appWidgetId`) individualmente (modo compacto, limite de itens e visibilidade de seções).
  - **Sincronização automática offline:** `NexusWidgetUpdater` (protegido por `Mutex` para evitar rajadas ao SQLite) é acionado automaticamente por `NexusRepository.onDataChanged` em toda mutação de tarefas, projetos, compromissos, eventos, finanças, faturas e restauração de backup, além de `NexusNotificationActionReceiver`, `NexusBootReceiver` (`BOOT_COMPLETED`, `TIME_SET`, `DATE_CHANGED`, `TIMEZONE_CHANGED`, `MY_PACKAGE_REPLACED`), `NexusReminderSyncWorker` (a cada 15 min) e botão manual de refresh no cabeçalho do widget e em **Mais → Widgets da Tela Inicial** (`WidgetsHubScreen` com `requestPinAppWidget` no Android 8+).
  - **Deep-links exclusivos:** `NexusWidgetNavigation` gera `Intent` com URIs distintos (`nexus://open/...`) para impedir que o `AppWidgetManager` reaproveite o mesmo `PendingIntent` entre linhas diferentes, abrindo exatamente o registro ou módulo selecionado no `MainActivity`.
  - **Limitações nativas do Android documentadas:** O sistema limita atualizações periódicas passivas via `updatePeriodMillis` (mínimo 30 min em AppWidgets) e pode atrasar `WorkManager`/broadcasts quando o usuário ativa restrição agressiva de bateria (Doze/OEM); o botão de sincronização manual no próprio widget e em *Widgets da Tela Inicial* contorna essa restrição sem consumir rede nem bateria excessiva. Também não há observação contínua em segundo plano fora do processo do app quando fechado, sendo a consistência garantida por mutações locais, ações de notificação/widget, `DATE_CHANGED` e `WorkManager`.

### Como Adicionar e Testar os Widgets no Android
1. **Pelo aplicativo:** Abra **Mais → Widgets da Tela Inicial**, toque em **Fixar na tela inicial** (Android 8+ em launchers compatíveis) ou use **Sincronizar widgets agora** para forçar atualização manual.
2. **Pela tela inicial do Android:** Pressione e segure uma área vazia → **Widgets** → procure **NEXUS** → escolha *Meu Dia*, *Minhas Tarefas*, *Financeiro* ou *Resumo NEXUS*.
3. **Configuração e redimensionamento:** Toque na engrenagem no cabeçalho do widget (ou segure e escolha *Configurar*) para alternar entre modo compacto e seções por instância; arraste as bordas do widget para validar os layouts `SMALL`, `MEDIUM` e `LARGE`.
4. **Validação de conclusão e atualização:** No widget *Minhas Tarefas*, toque em concluir uma tarefa normal (`[Confirmar]`/`[Cancelar]`), confirme que tarefas `BLOCKED` não exibem conclusão direta, altere um lançamento financeiro ou restaure um backup e observe todos os widgets sincronizados automaticamente.

- **Dashboard Modular & Personalização (Fase 6):**
  - **9 blocos independentes ordenáveis e configuráveis:** `GREETING` (saudação), `DAILY_QUOTE` (frase diária), `TASK_SUMMARY` (resumo de tarefas), `TODAY_AGENDA` (agenda do dia), `FINANCE_SUMMARY` (resumo financeiro), `UPCOMING_EVENTS` (próximos eventos), `UPCOMING_PAYMENTS` (próximos vencimentos), `GOALS` (metas pessoais e financeiras) e `CHARTS_INDICATORS` (indicadores e mini-gráficos Canvas).
  - **Configuração por bloco:** cada bloco aceita ativar/desativar, reordenar cima/baixo, definir `maxItems` e alternar exibição de detalhes (`showDetails`, modo compacto global e cartões de métricas rápidas) e botão **Restaurar organização padrão**.
  - **Ocultação estritamente não destrutiva:** desativar qualquer bloco oculta somente sua exibição no Dashboard, preservando integralmente os registros reais no Room, a barra inferior de navegação e os acessos diretos em **Mais**, **Configurações** e **Backup**.
  - **Identidade e saudação configuráveis:** nome editável, template `"AUTO"` (Bom dia / Boa tarde / Boa noite conforme horário de `America/Sao_Paulo`) ou modelo customizado com `{nome}`, e formato de data selecionável (`WEEKDAY_DD_MM_YYYY`, `DD_MM_YYYY`, `DD_MMM_YYYY`).

- **Biblioteca Local de Frases (Fase 6):**
  - **Zero conteúdo pré-inventado:** a tabela `quotes` inicia vazia até que o usuário cadastre suas próprias frases ou importe arquivos locais `.json` ou `.txt` via SAF (`parseImportedQuotes`), com exportação completa em `.json`.
  - **Autoria honesta:** campo de autor é opcional; quando vazio, é preservado como vazio no banco e exibido com fallback explícito `"Autoria não informada"`, jamais preenchido com suposições.
  - **Seleção determinística sem repetição consecutiva:** `selectDailyQuote` mantém a mesma frase ao longo de todo o dia (`lastQuoteDateIso`), evita repetir `lastQuoteId` no dia seguinte quando há `>= 2` frases ativas e trata com segurança os cenários de `0` frases ativas (aviso claro no painel ou bloco ocultável) e `1` única frase ativa.

- **Planejamento e Objetivos Pessoais (Fase 6):**
  - **CRUD completo em `personal_goals`:** título, descrição, categoria, prazo, status (`ACTIVE`, `PAUSED`, `COMPLETED`, `CANCELLED`), modo de progresso (`AUTO` ou `MANUAL`), marcos ordenáveis (`goal_milestones`) e registro de histórico de evoluções (`goal_progress_logs`).
  - **Progresso automático real (`computeGoalProgressPercent`):** em modo `AUTO`, calcula a evolução combinando marcos concluídos e tarefas/projetos vinculados via `goalId`; quando não há vínculos nem marcos, respeita `0%` (ou `100%` ao concluir).
  - **Integração sem duplicação (`goalId` FK `SET_NULL`):** vincular ou desvincular tarefas (`TaskEntity.goalId`) e projetos (`ProjectEntity.goalId`) existentes nos seletores do objetivo ou diretamente nos editores de Tarefa e Projeto altera apenas a referência estrangeira; excluir um objetivo preserva as tarefas e projetos associados.
  - **Filtros e busca global:** alternância real entre `Ativos`, `Atrasados` (`isGoalOverdue`), `Pausados`, `Concluídos` e `Todos`, filtro por categoria, busca textual local e inclusão dos objetivos na **Pesquisa Global** do aplicativo.

- **Configurações Reorganizadas (Fase 6):**
  - Agrupamento funcional sem botões fictícios nas seções *Aparência*, *Dashboard*, *Saudação e identidade*, *Frases diárias*, *Idioma e região*, *Data e horário*, *Moeda e financeiro*, *Notificações e Briefing*, *Widgets e Backup*, *Privacidade e armazenamento local*, *Categorias* e *Diagnóstico e versão*.

---

## 5. Controle de Progresso por Fases

| Fase | Escopo | Status Atual |
| :--- | :--- | :--- |
| **Fase 1 — Fundação** | Estrutura Android, Room, Gradle wrapper properties, Tema Dark/Light, Início, Agenda unificada, Tarefas/Projetos, Pesquisa global, Configurações, Backup/Restauração JSON SAF, Diagnóstico + ErrorLog, Testes unitários | **Código implementado (pendente compilação em Android Studio)** |
| **Fase 2 — Financeiro e Eventos** | Room v2 (`MIGRATION_1_2`), Receitas/Despesas, Cartões e Faturas, Parcelamentos, Recorrências, Empréstimos, Orçamentos, Metas financeiras, Auditoria, Gráficos Canvas, Exportação CSV SAF, Eventos com Maps/Rota externo e ingresso financeiro | **Código implementado (pendente compilação em Android Studio)** |
| **Fase 3 — Integrações e Consistência** | Ingressos parcelados no cartão, sincronização bidirecional de ingressos/parcelamentos, faturas `OVERDUE` e limite restante calculado, custos financeiros agregados em Projetos, exclusão de parcelamentos/recorrências preservando histórico pago, idempotência reforçada | **Código implementado (pendente compilação em Android Studio)** |
| **Fase 4 — Notificações e Assistente** | Room v3 (`MIGRATION_2_3`), Backup JSON v3 (compatível v1/v2), `AlarmManager` + `WorkManager` + `BootReceiver`, ações interativas na notificação, modo silêncio, seletor de antecedência nos editores, Assistente de Rotina (Briefing Diário, Revisão Noturna, Notificações) e deep-links | **Código implementado (pendente compilação em Android Studio)** |
| **Fase 5 — Widgets Android** | Jetpack Glance `1.1.1`, 4 widgets reais (`Meu Dia`, `Minhas Tarefas`, `Financeiro`, `Resumo NEXUS`) com layouts responsivos `SMALL`/`MEDIUM`/`LARGE`, múltiplas instâncias (`NexusWidgetConfigActivity`), conclusão segura em 2 etapas, sincronização centralizada (`NexusWidgetUpdater`), tela `WidgetsHubScreen` e `NexusPhase5WidgetUnitTests` | **Código implementado (pendente compilação em Android Studio)** |
| **Fase 6 — Planejamento e Personalização** | Room v4 (`MIGRATION_3_4`), Backup JSON v4 (compatível v1/v2/v3), Dashboard modular (9 blocos ativáveis, reordenáveis e configuráveis), Saudação e formato de data, Biblioteca Local de Frases (CRUD, sem autoria inventada, import/export SAF `.json`/`.txt`, rotação diária determinística), Planejamento e Objetivos (`personal_goals`, marcos, logs de progresso, vínculo não-duplicado com Tarefas/Projetos via `goalId`), Configurações reorganizadas e `NexusPhase6UnitTests` (14 casos) | **Código implementado (pendente compilação em Android Studio)** |

---

## 6. Relatório de Testes e Limitações (Estado Atual)

- **Limitação crítica deste ambiente:** Este container executa apenas **Node.js / Vite / React** (sem JDK, Gradle, Android SDK, `adb` ou emulador Android).
- **Verificações executadas de verdade neste ambiente:**
  - Compilação TypeScript/Vite (`npm run build`) e lint (`npm run lint`) do inspetor web e exportador `.zip`.
  - Auditoria estática dos arquivos Kotlin/XML/Manifest (25 `@Entity` alinhadas com `NexusDatabase v4`, `MIGRATION_1_2` + `MIGRATION_2_3` + `MIGRATION_3_4`, 4 receivers Glance + `NexusWidgetConfigActivity` + `DATE_CHANGED` registrados corretamente, assinaturas entre `NexusRepository`, `NexusViewModel`, `MainActivity`, telas de Planejamento/Personalização/Frases e widgets, ausência de `INTERNET`, `fallbackToDestructiveMigration()` e ausência de frases inventadas).
- **Testes pendentes (exigem Android Studio / emulador / dispositivo físico):**
  - Execução de `./gradlew testDebugUnitTest` (`NexusPhase1UnitTests`, `NexusPhase2UnitTests`, `NexusPhase3And4UnitTests`, `NexusPhase5WidgetUnitTests` e `NexusPhase6UnitTests`).
  - Compilação Gradle (`./gradlew assembleDebug`) e geração de APK.
  - Teste em dispositivo/emulador: migrações Room `v1 -> v2 -> v3 -> v4`, restauração de backups v1/v2/v3/v4 via SAF, persistência da ordem e configuração de blocos do Dashboard ao reiniciar, seleção diária de frases sem repetição consecutiva e importação/exportação `.json`/`.txt` via SAF, CRUD de objetivos/marcos/histórico de progresso e vinculação sem duplicação de Tarefas/Projetos, disparo real de `AlarmManager` e `WorkManager` com app fechado, ações diretas na bandeja de notificações, reagendamento pós-boot (`BOOT_COMPLETED`), fixação/redimensionamento/multi-instância dos 4 widgets Glance e deep-links `nexus://open/...`.
