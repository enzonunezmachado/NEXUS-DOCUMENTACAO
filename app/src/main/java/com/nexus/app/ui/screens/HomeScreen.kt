package com.nexus.app.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.DashboardCustomize
import androidx.compose.material.icons.outlined.FormatQuote
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nexus.app.core.DateTimeUtils
import com.nexus.app.domain.model.AgendaItem
import com.nexus.app.domain.model.DailyBriefingSnapshot
import com.nexus.app.domain.model.DashboardBlockConfig
import com.nexus.app.domain.model.DashboardBlockType
import com.nexus.app.domain.model.DashboardSnapshot
import com.nexus.app.domain.model.FinancialStatus
import com.nexus.app.domain.model.QuoteLibraryStatus
import com.nexus.app.domain.model.StatementStatus
import com.nexus.app.domain.model.TransactionType
import com.nexus.app.ui.components.NexusCard
import com.nexus.app.ui.components.NexusEmptyState
import com.nexus.app.ui.components.SectionHeader
import com.nexus.app.ui.components.TaskRowCard
import com.nexus.app.ui.components.parseHexColorOrDefault
import com.nexus.app.ui.theme.LocalNexusPalette

@Composable
fun HomeScreen(
    snapshot: DashboardSnapshot,
    dailyBriefing: DailyBriefingSnapshot,
    onOpenRoutineAssistant: () -> Unit,
    onOpenCustomizeDashboard: () -> Unit,
    onOpenQuoteLibrary: () -> Unit,
    onOpenPlanning: (goalId: String?) -> Unit,
    onOpenTask: (String) -> Unit,
    onToggleTaskComplete: (String) -> Unit,
    onOpenAppointment: (String) -> Unit,
    onOpenEvent: (String) -> Unit,
    onOpenFinance: () -> Unit,
    onOpenFinanceCards: () -> Unit,
    onOpenTransaction: (String) -> Unit,
    onQuickAdd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val palette = LocalNexusPalette.current
    val today = DateTimeUtils.todayLocalDate()
    val personalization = snapshot.personalization
    val compact = personalization.compactDashboardMode
    val verticalGap = if (compact) 10.dp else 16.dp
    val enabledBlocks = snapshot.blocks.filter { it.enabled }.sortedBy { it.order }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(verticalGap)
    ) {
        if (enabledBlocks.isEmpty()) {
            item {
                NexusEmptyState(
                    icon = Icons.Outlined.DashboardCustomize,
                    title = "Todos os blocos do dashboard estão ocultos",
                    subtitle = "Seus registros continuam salvos no banco local. Ative ou reordene blocos quando quiser.",
                    actionLabel = "Personalizar dashboard",
                    onAction = onOpenCustomizeDashboard
                )
            }
        } else {
            enabledBlocks.forEach { block ->
                when (block.type) {
                    DashboardBlockType.GREETING -> {
                        if (personalization.greetingEnabled) {
                            item(key = "blk_greeting") {
                                GreetingBlock(
                                    userName = snapshot.userName,
                                    greetingTemplate = personalization.greetingTemplate,
                                    formattedDate = DateTimeUtils.formatDateByPattern(
                                        today,
                                        personalization.dateFormatPattern
                                    ),
                                    showDate = block.showPrimaryDetail,
                                    onCustomize = onOpenCustomizeDashboard
                                )
                            }
                        }
                    }

                    DashboardBlockType.DAILY_QUOTE -> {
                        if (personalization.showDailyQuoteOnDashboard) {
                            item(key = "blk_quote") {
                                DailyQuoteBlock(
                                    selection = snapshot.dailyQuoteSelection,
                                    showAuthor = block.showPrimaryDetail,
                                    showManageAction = block.showSecondaryDetail,
                                    onOpenQuoteLibrary = onOpenQuoteLibrary
                                )
                            }
                        }
                    }

                    DashboardBlockType.TASK_SUMMARY -> {
                        if (personalization.showMetricSummaryOnDashboard && block.showPrimaryDetail) {
                            item(key = "blk_task_metrics") {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    MetricTile(
                                        label = "Pendentes",
                                        value = snapshot.pendingCount.toString(),
                                        valueColor = palette.textPrimary,
                                        modifier = Modifier.weight(1f)
                                    )
                                    MetricTile(
                                        label = "Concluídas",
                                        value = snapshot.completedCount.toString(),
                                        valueColor = palette.positive,
                                        modifier = Modifier.weight(1f)
                                    )
                                    MetricTile(
                                        label = "Atrasadas",
                                        value = snapshot.overdueTasks.size.toString(),
                                        valueColor = if (snapshot.overdueTasks.isNotEmpty()) palette.danger else palette.textSecondary,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }

                        if (snapshot.totalItemsInDb == 0) {
                            item(key = "blk_empty_cmd") {
                                NexusEmptyState(
                                    icon = Icons.Outlined.Inbox,
                                    title = "Seu centro de comando está pronto",
                                    subtitle = "Adicione sua primeira tarefa, compromisso, evento ou lançamento financeiro.",
                                    actionLabel = "Criar primeiro registro",
                                    onAction = onQuickAdd
                                )
                            }
                        } else {
                            if (block.showOverdueOrAlerts && snapshot.overdueTasks.isNotEmpty()) {
                                val limitedOverdue = snapshot.overdueTasks.take(block.maxItems)
                                item(key = "blk_tasks_overdue_hdr") {
                                    SectionHeader(
                                        title = "Tarefas atrasadas",
                                        trailing = "${snapshot.overdueTasks.size}"
                                    )
                                }
                                items(limitedOverdue, key = { "overdue_${it.task.id}" }) { taskItem ->
                                    TaskRowCard(
                                        item = taskItem,
                                        onClick = { onOpenTask(taskItem.task.id) },
                                        onToggleComplete = { onToggleTaskComplete(taskItem.task.id) }
                                    )
                                }
                            }

                            if (block.showSecondaryDetail) {
                                val limitedPrio = snapshot.priorityTasksToday.take(block.maxItems)
                                item(key = "blk_tasks_prio_hdr") {
                                    SectionHeader(
                                        title = "Prioritárias e de hoje",
                                        trailing = if (snapshot.priorityTasksToday.isEmpty()) null else "${snapshot.priorityTasksToday.size}"
                                    )
                                }
                                if (limitedPrio.isEmpty()) {
                                    item(key = "blk_tasks_prio_empty") {
                                        NexusCard(modifier = Modifier.fillMaxWidth()) {
                                            Text(
                                                text = "Nenhuma tarefa prioritária ou com prazo para hoje.",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = palette.textSecondary
                                            )
                                        }
                                    }
                                } else {
                                    items(limitedPrio, key = { "prio_${it.task.id}" }) { taskItem ->
                                        TaskRowCard(
                                            item = taskItem,
                                            onClick = { onOpenTask(taskItem.task.id) },
                                            onToggleComplete = { onToggleTaskComplete(taskItem.task.id) }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    DashboardBlockType.TODAY_AGENDA -> {
                        if (block.showPrimaryDetail) {
                            item(key = "blk_routine") {
                                val billsDueCount = dailyBriefing.billsDueToday.size + dailyBriefing.overdueBills.size
                                NexusCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    elevated = true,
                                    onClick = onOpenRoutineAssistant
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                            Icon(
                                                imageVector = Icons.Outlined.NotificationsActive,
                                                contentDescription = null,
                                                tint = palette.highlight
                                            )
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column {
                                                Text(
                                                    text = "Assistente de Rotina",
                                                    style = MaterialTheme.typography.titleMedium,
                                                    color = palette.textPrimary
                                                )
                                                Text(
                                                    text = "${dailyBriefing.todayAppointments.size} compromissos · ${dailyBriefing.todayTasks.size} tarefas · $billsDueCount contas",
                                                    style = MaterialTheme.typography.labelMedium,
                                                    color = palette.textSecondary
                                                )
                                            }
                                        }
                                        TextButton(onClick = onOpenRoutineAssistant) {
                                            Text(
                                                text = if (dailyBriefing.isReviewedToday) "Revisado" else "Abrir",
                                                color = if (dailyBriefing.isReviewedToday) palette.positive else palette.info
                                            )
                                        }
                                    }
                                }
                            }

                            item(key = "blk_next_appt") {
                                SectionHeader(title = "Próximo compromisso")
                                Spacer(modifier = Modifier.height(6.dp))
                                val next = snapshot.nextAppointment
                                if (next == null) {
                                    NexusCard(modifier = Modifier.fillMaxWidth()) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Outlined.CalendarToday,
                                                contentDescription = null,
                                                tint = palette.textSecondary
                                            )
                                            Spacer(modifier = Modifier.width(12.dp))
                                            Text(
                                                text = "Nenhum compromisso futuro agendado.",
                                                style = MaterialTheme.typography.bodyMedium,
                                                color = palette.textSecondary
                                            )
                                        }
                                    }
                                } else {
                                    AppointmentCompactCard(
                                        item = next,
                                        showConflictAlert = block.showOverdueOrAlerts,
                                        onClick = { onOpenAppointment(next.appointment.id) }
                                    )
                                }
                            }
                        }

                        if (block.showSecondaryDetail && snapshot.todayAppointments.isNotEmpty()) {
                            val limitedAppts = snapshot.todayAppointments.take(block.maxItems)
                            item(key = "blk_today_appts_hdr") {
                                SectionHeader(
                                    title = "Compromissos de hoje",
                                    trailing = "${snapshot.todayAppointments.size}"
                                )
                            }
                            items(limitedAppts, key = { "today_appt_${it.id}" }) { appt ->
                                AppointmentCompactCard(
                                    item = appt,
                                    showConflictAlert = block.showOverdueOrAlerts,
                                    onClick = { onOpenAppointment(appt.appointment.id) }
                                )
                            }
                        }
                    }

                    DashboardBlockType.FINANCE_SUMMARY -> {
                        item(key = "blk_finance_summary") {
                            FinanceSummaryBlock(
                                snapshot = snapshot,
                                config = block,
                                onOpenFinance = onOpenFinance,
                                onOpenTransaction = onOpenTransaction
                            )
                        }
                    }

                    DashboardBlockType.UPCOMING_EVENTS -> {
                        if (snapshot.upcomingEvents.isNotEmpty()) {
                            val limitedEvents = snapshot.upcomingEvents.take(block.maxItems)
                            item(key = "blk_events_hdr") {
                                SectionHeader(
                                    title = "Próximos eventos",
                                    trailing = "${snapshot.upcomingEvents.size}"
                                )
                            }
                            items(limitedEvents, key = { "home_ev_${it.event.id}" }) { ew ->
                                EventSummaryCard(
                                    item = ew,
                                    onClick = { onOpenEvent(ew.event.id) }
                                )
                            }
                        }
                    }

                    DashboardBlockType.UPCOMING_PAYMENTS -> {
                        val hasDue = snapshot.upcomingPayments.isNotEmpty()
                        val hasStmts = block.showSecondaryDetail && snapshot.openStatements.isNotEmpty()
                        if (hasDue || hasStmts) {
                            item(key = "blk_upcoming_pay_hdr") {
                                SectionHeader(
                                    title = "Próximos pagamentos",
                                    trailing = "${snapshot.upcomingPayments.size + if (hasStmts) snapshot.openStatements.size else 0}"
                                )
                            }
                            items(snapshot.upcomingPayments.take(block.maxItems), key = { "up_tx_${it.transaction.id}" }) { tw ->
                                val tx = tw.transaction
                                val isOverdue = tx.status == FinancialStatus.OVERDUE
                                NexusCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    elevated = true,
                                    onClick = { onOpenTransaction(tx.id) }
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                            Icon(
                                                imageVector = Icons.Outlined.ReceiptLong,
                                                contentDescription = null,
                                                tint = if (isOverdue && block.showOverdueOrAlerts) palette.danger else palette.textSecondary
                                            )
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column {
                                                Text(tx.description, style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                                                Text(
                                                    text = buildString {
                                                        tx.dueAt?.let { append("Vence ${DateTimeUtils.formatDate(it)}") }
                                                        if (isOverdue) append(" · Em atraso")
                                                    },
                                                    style = MaterialTheme.typography.labelSmall,
                                                    color = if (isOverdue && block.showOverdueOrAlerts) palette.danger else palette.textSecondary
                                                )
                                            }
                                        }
                                        if (block.showPrimaryDetail) {
                                            val sign = if (tx.type == TransactionType.INCOME) "+" else "-"
                                            Text(
                                                text = "$sign${DateTimeUtils.formatBrl(tx.amountCents)}",
                                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                                                color = if (tx.type == TransactionType.INCOME) palette.positive else palette.textPrimary
                                            )
                                        }
                                    }
                                }
                            }
                            if (hasStmts) {
                                items(snapshot.openStatements.take(block.maxItems), key = { "up_stmt_${it.statement.id}" }) { sw ->
                                    val isOverdueStmt = sw.statement.status == StatementStatus.OVERDUE
                                    NexusCard(
                                        modifier = Modifier.fillMaxWidth(),
                                        elevated = true,
                                        onClick = onOpenFinanceCards
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                                Icon(
                                                    imageVector = Icons.Outlined.CreditCard,
                                                    contentDescription = null,
                                                    tint = if (isOverdueStmt && block.showOverdueOrAlerts) palette.danger else palette.highlight
                                                )
                                                Spacer(modifier = Modifier.width(10.dp))
                                                Column {
                                                    Text(
                                                        text = "Fatura ${sw.cardName} (${sw.statement.yearMonth})",
                                                        style = MaterialTheme.typography.titleMedium,
                                                        color = palette.textPrimary
                                                    )
                                                    Text(
                                                        text = "Vence ${DateTimeUtils.formatDate(sw.statement.dueAt)} · ${sw.statement.status.label}",
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = if (isOverdueStmt && block.showOverdueOrAlerts) palette.danger else palette.textSecondary
                                                    )
                                                }
                                            }
                                            if (block.showPrimaryDetail) {
                                                Text(
                                                    text = DateTimeUtils.formatBrl(sw.totalCents),
                                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                                                    color = if (isOverdueStmt && block.showOverdueOrAlerts) palette.danger else palette.textPrimary
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    DashboardBlockType.GOALS -> {
                        val hasPersonal = block.showPrimaryDetail && snapshot.personalGoals.isNotEmpty()
                        val hasFinancial = block.showSecondaryDetail && snapshot.financialGoals.isNotEmpty()
                        item(key = "blk_goals_hdr") {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                SectionHeader(
                                    title = "Objetivos e metas",
                                    trailing = "${snapshot.personalGoals.size + snapshot.financialGoals.size}"
                                )
                            }
                        }
                        if (!hasPersonal && !hasFinancial) {
                            item(key = "blk_goals_empty") {
                                NexusCard(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = { onOpenPlanning(null) }
                                ) {
                                    Text(
                                        text = "Nenhum objetivo ativo ainda. Toque para abrir Planejamento e Objetivos.",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = palette.textSecondary
                                    )
                                }
                            }
                        } else {
                            if (hasPersonal) {
                                items(snapshot.personalGoals.take(block.maxItems), key = { "home_pg_${it.goal.id}" }) { pg ->
                                    PersonalGoalCard(
                                        item = pg,
                                        onClick = { onOpenPlanning(pg.goal.id) }
                                    )
                                }
                            }
                            if (hasFinancial) {
                                items(snapshot.financialGoals.take(block.maxItems), key = { "home_fg_${it.id}" }) { fg ->
                                    val fraction = if (fg.targetAmountCents > 0L) {
                                        (fg.currentAmountCents.toFloat() / fg.targetAmountCents.toFloat()).coerceIn(0f, 1f)
                                    } else 0f
                                    NexusCard(
                                        modifier = Modifier.fillMaxWidth(),
                                        elevated = true,
                                        onClick = onOpenFinance
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(fg.name, style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                                            Text("${(fraction * 100).toInt()}%", style = MaterialTheme.typography.labelLarge, color = palette.positive)
                                        }
                                        Spacer(modifier = Modifier.height(6.dp))
                                        LinearProgressIndicator(
                                            progress = { fraction },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(6.dp)
                                                .clip(RoundedCornerShape(4.dp)),
                                            color = palette.positive,
                                            trackColor = palette.border
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "${DateTimeUtils.formatBrl(fg.currentAmountCents)} de ${DateTimeUtils.formatBrl(fg.targetAmountCents)}",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = palette.textSecondary
                                        )
                                    }
                                }
                            }
                        }
                    }

                    DashboardBlockType.CHARTS_INDICATORS -> {
                        item(key = "blk_charts") {
                            DashboardChartsBlock(
                                snapshot = snapshot,
                                showCashflow = block.showPrimaryDetail,
                                showCategoryBreakdown = block.showSecondaryDetail
                            )
                        }
                    }
                }
            }
        }

        item(key = "blk_footer_customize") {
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedButton(
                onClick = onOpenCustomizeDashboard,
                border = BorderStroke(1.dp, palette.border),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Outlined.DashboardCustomize, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Personalizar painel", color = palette.textPrimary)
            }
        }
    }
}

@Composable
private fun GreetingBlock(
    userName: String,
    greetingTemplate: String,
    formattedDate: String,
    showDate: Boolean,
    onCustomize: () -> Unit
) {
    val palette = LocalNexusPalette.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = DateTimeUtils.formatGreeting(userName, greetingTemplate),
                style = MaterialTheme.typography.headlineMedium,
                color = palette.textPrimary
            )
            if (showDate) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodyMedium,
                    color = palette.textSecondary
                )
            }
        }
        IconButton(onClick = onCustomize) {
            Icon(
                imageVector = Icons.Outlined.DashboardCustomize,
                contentDescription = "Personalizar dashboard",
                tint = palette.textSecondary
            )
        }
    }
}

@Composable
private fun DailyQuoteBlock(
    selection: com.nexus.app.domain.model.DailyQuoteSelection,
    showAuthor: Boolean,
    showManageAction: Boolean,
    onOpenQuoteLibrary: () -> Unit
) {
    val palette = LocalNexusPalette.current
    NexusCard(
        modifier = Modifier.fillMaxWidth(),
        elevated = true,
        onClick = onOpenQuoteLibrary
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Outlined.FormatQuote,
                contentDescription = null,
                tint = palette.highlight,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                when (selection.libraryStatus) {
                    QuoteLibraryStatus.EMPTY -> {
                        Text(
                            text = "Biblioteca de frases vazia",
                            style = MaterialTheme.typography.titleSmall,
                            color = palette.textPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Adicione ou importe frases locais offline sem conteúdo inventado.",
                            style = MaterialTheme.typography.bodySmall,
                            color = palette.textSecondary
                        )
                    }
                    QuoteLibraryStatus.SINGLE_QUOTE,
                    QuoteLibraryStatus.ROTATING -> {
                        val quote = selection.quote
                        if (quote != null) {
                            Text(
                                text = "\"${quote.text}\"",
                                style = MaterialTheme.typography.bodyLarge.copy(fontStyle = FontStyle.Italic),
                                color = palette.textPrimary
                            )
                            if (showAuthor) {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = quote.author.ifBlank { "Autoria não informada" },
                                    style = MaterialTheme.typography.labelMedium,
                                    color = palette.textSecondary
                                )
                            }
                        }
                    }
                }
            }
            if (showManageAction) {
                TextButton(onClick = onOpenQuoteLibrary) {
                    Text(
                        text = if (selection.libraryStatus == QuoteLibraryStatus.EMPTY) "Adicionar" else "Biblioteca",
                        color = palette.info
                    )
                }
            }
        }
    }
}

@Composable
private fun FinanceSummaryBlock(
    snapshot: DashboardSnapshot,
    config: DashboardBlockConfig,
    onOpenFinance: () -> Unit,
    onOpenTransaction: (String) -> Unit
) {
    val palette = LocalNexusPalette.current
    val finance = snapshot.financeSummary
    NexusCard(
        modifier = Modifier.fillMaxWidth(),
        elevated = true,
        onClick = onOpenFinance
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Resumo financeiro (saldo informado)",
                    style = MaterialTheme.typography.labelMedium,
                    color = palette.textSecondary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = DateTimeUtils.formatBrl(finance.declaredBalanceCents),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                    color = if (finance.declaredBalanceCents >= 0L) palette.textPrimary else palette.danger
                )
            }
            TextButton(onClick = onOpenFinance) {
                Text("Abrir", color = palette.info)
            }
        }
        if (config.showPrimaryDetail) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Receitas: ${DateTimeUtils.formatBrl(finance.receivedIncomeCents)}",
                    style = MaterialTheme.typography.labelMedium,
                    color = palette.positive
                )
                Text(
                    text = "Despesas: ${DateTimeUtils.formatBrl(finance.paidExpenseCents)}",
                    style = MaterialTheme.typography.labelMedium,
                    color = palette.textSecondary
                )
            }
        }
        if (config.showOverdueOrAlerts && (finance.pendingExpenseCents > 0L || finance.overdueExpenseCents > 0L)) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = buildString {
                    append("Pendentes: ${DateTimeUtils.formatBrl(finance.pendingExpenseCents)}")
                    if (finance.overdueExpenseCents > 0L) {
                        append(" · Vencidas: ${DateTimeUtils.formatBrl(finance.overdueExpenseCents)}")
                    }
                },
                style = MaterialTheme.typography.labelSmall,
                color = if (finance.overdueExpenseCents > 0L) palette.danger else palette.textSecondary
            )
        }
        if (config.showSecondaryDetail) {
            finance.nextDueTransaction?.let { nextDue ->
                Spacer(modifier = Modifier.height(6.dp))
                TextButton(onClick = { onOpenTransaction(nextDue.transaction.id) }) {
                    Text(
                        text = "Próximo venc.: ${nextDue.transaction.description} (${DateTimeUtils.formatBrl(nextDue.transaction.amountCents)})",
                        style = MaterialTheme.typography.labelMedium,
                        color = palette.warning
                    )
                }
            }
        }
    }
}

@Composable
private fun DashboardChartsBlock(
    snapshot: DashboardSnapshot,
    showCashflow: Boolean,
    showCategoryBreakdown: Boolean
) {
    val palette = LocalNexusPalette.current
    val points = snapshot.financeSummary.monthlyCashflow
    val categories = snapshot.financeSummary.categorySpend
    val totalTasks = snapshot.pendingCount + snapshot.completedCount
    val taskCompletionFraction = if (totalTasks > 0) {
        (snapshot.completedCount.toFloat() / totalTasks.toFloat()).coerceIn(0f, 1f)
    } else 0f

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SectionHeader(title = "Gráficos e indicadores")

        NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Conclusão de tarefas", style = MaterialTheme.typography.titleSmall, color = palette.textPrimary)
                Text(
                    text = if (totalTasks == 0) "0%" else "${(taskCompletionFraction * 100).toInt()}% (${snapshot.completedCount}/$totalTasks)",
                    style = MaterialTheme.typography.labelMedium,
                    color = palette.positive
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            LinearProgressIndicator(
                progress = { taskCompletionFraction },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = palette.positive,
                trackColor = palette.border
            )

            if (showCashflow) {
                Spacer(modifier = Modifier.height(14.dp))
                Text("Receitas vs Despesas mensais", style = MaterialTheme.typography.titleSmall, color = palette.textPrimary)
                Spacer(modifier = Modifier.height(6.dp))
                if (points.isEmpty()) {
                    Text(
                        text = "Sem lançamentos realizados suficientes para o gráfico mensal.",
                        style = MaterialTheme.typography.bodySmall,
                        color = palette.textSecondary
                    )
                } else {
                    val maxValue = points.maxOfOrNull { maxOf(it.receivedIncomeCents, it.paidExpenseCents) }
                        ?.coerceAtLeast(1L) ?: 1L
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(palette.positive))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Receitas", style = MaterialTheme.typography.labelSmall, color = palette.textSecondary)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(palette.danger))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Despesas", style = MaterialTheme.typography.labelSmall, color = palette.textSecondary)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    ) {
                        val count = points.size.coerceAtLeast(1)
                        val groupWidth = size.width / count
                        val barWidth = (groupWidth * 0.28f).coerceAtLeast(8f)
                        val baseline = size.height - 6f

                        drawLine(
                            color = palette.border,
                            start = Offset(0f, baseline),
                            end = Offset(size.width, baseline),
                            strokeWidth = 2f
                        )
                        points.forEachIndexed { idx, pt ->
                            val centerX = groupWidth * idx + groupWidth / 2f
                            val incH = (pt.receivedIncomeCents.toFloat() / maxValue.toFloat()) * (baseline - 6f)
                            val expH = (pt.paidExpenseCents.toFloat() / maxValue.toFloat()) * (baseline - 6f)
                            if (incH > 0f) {
                                drawRect(
                                    color = palette.positive,
                                    topLeft = Offset(centerX - barWidth - 3f, baseline - incH),
                                    size = Size(barWidth, incH)
                                )
                            }
                            if (expH > 0f) {
                                drawRect(
                                    color = palette.danger,
                                    topLeft = Offset(centerX + 3f, baseline - expH),
                                    size = Size(barWidth, expH)
                                )
                            }
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        points.forEach { pt ->
                            Text(pt.yearMonthLabel, style = MaterialTheme.typography.labelSmall, color = palette.textSecondary)
                        }
                    }
                }
            }

            if (showCategoryBreakdown && categories.isNotEmpty()) {
                Spacer(modifier = Modifier.height(14.dp))
                Text("Maiores gastos por categoria (mês)", style = MaterialTheme.typography.titleSmall, color = palette.textPrimary)
                Spacer(modifier = Modifier.height(8.dp))
                val totalSpend = categories.sumOf { it.totalCents }.coerceAtLeast(1L)
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    categories.take(4).forEach { pt ->
                        val frac = (pt.totalCents.toFloat() / totalSpend.toFloat()).coerceIn(0f, 1f)
                        val barColor = parseHexColorOrDefault(pt.colorHex, palette.highlight)
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(pt.categoryName, style = MaterialTheme.typography.bodySmall, color = palette.textPrimary)
                                Text(DateTimeUtils.formatBrl(pt.totalCents), style = MaterialTheme.typography.labelSmall, color = palette.textSecondary)
                            }
                            Spacer(modifier = Modifier.height(3.dp))
                            Canvas(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(4.dp))
                            ) {
                                drawRect(color = palette.border, size = size)
                                drawRect(color = barColor, size = Size(size.width * frac, size.height))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MetricTile(
    label: String,
    value: String,
    valueColor: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier
) {
    val palette = LocalNexusPalette.current
    NexusCard(modifier = modifier, elevated = true) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
            color = valueColor
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = palette.textSecondary
        )
    }
}

@Composable
fun AppointmentCompactCard(
    item: AgendaItem.AppointmentItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showConflictAlert: Boolean = true
) {
    val palette = LocalNexusPalette.current
    val appt = item.appointment
    val timeSummary = if (appt.isAllDay) {
        "${DateTimeUtils.formatDate(appt.startAt)} · Dia inteiro"
    } else {
        "${DateTimeUtils.formatDate(appt.startAt)} · ${DateTimeUtils.formatTime(appt.startAt)}–${DateTimeUtils.formatTime(appt.endAt)}"
    }

    NexusCard(
        modifier = modifier.fillMaxWidth(),
        elevated = true,
        onClick = onClick
    ) {
        Text(
            text = appt.title,
            style = MaterialTheme.typography.titleMedium,
            color = palette.textPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        val meta = buildList {
            add(timeSummary)
            item.category?.let { add(it.name) }
            if (appt.location.isNotBlank()) add(appt.location)
        }.joinToString(" · ")
        Text(
            text = meta,
            style = MaterialTheme.typography.labelMedium,
            color = palette.textSecondary
        )
        if (showConflictAlert && item.hasConflict) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.WarningAmber,
                    contentDescription = "Conflito de horário",
                    tint = palette.warning
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Conflito com: ${item.conflictTitles.joinToString(", ")}",
                    style = MaterialTheme.typography.labelMedium,
                    color = palette.warning
                )
            }
        }
    }
}
