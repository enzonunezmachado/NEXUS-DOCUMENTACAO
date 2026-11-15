package com.nexus.app.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material.icons.outlined.Repeat
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nexus.app.core.DateTimeUtils
import com.nexus.app.data.db.entity.AuditHistoryEntity
import com.nexus.app.data.db.entity.CreditCardEntity
import com.nexus.app.data.db.entity.FinancialGoalEntity
import com.nexus.app.data.db.entity.LoanEntity
import com.nexus.app.data.db.entity.RecurringRuleEntity
import com.nexus.app.domain.model.BudgetProgress
import com.nexus.app.domain.model.CardWithStatements
import com.nexus.app.domain.model.CategorySpendPoint
import com.nexus.app.domain.model.FinanceSummary
import com.nexus.app.domain.model.FinancialStatus
import com.nexus.app.domain.model.InstallmentPurchaseWithItems
import com.nexus.app.domain.model.MonthlyCashflowPoint
import com.nexus.app.domain.model.StatementStatus
import com.nexus.app.domain.model.TransactionType
import com.nexus.app.domain.model.TransactionWithDetails
import com.nexus.app.ui.FinanceFilterState
import com.nexus.app.ui.FinanceTabMode
import com.nexus.app.ui.components.NexusCard
import com.nexus.app.ui.components.NexusEmptyState
import com.nexus.app.ui.components.SectionHeader
import com.nexus.app.ui.components.parseHexColorOrDefault
import com.nexus.app.ui.theme.LocalNexusPalette
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun FinanceScreen(
    summary: FinanceSummary,
    transactions: List<TransactionWithDetails>,
    filter: FinanceFilterState,
    cardsWithStatements: List<CardWithStatements>,
    installmentPurchases: List<InstallmentPurchaseWithItems>,
    recurringRules: List<RecurringRuleEntity>,
    budgets: List<BudgetProgress>,
    financialGoals: List<FinancialGoalEntity>,
    loans: List<LoanEntity>,
    auditHistory: List<AuditHistoryEntity>,
    onTabChange: (FinanceTabMode) -> Unit,
    onTypeFilterChange: (TransactionType?) -> Unit,
    onStatusFilterChange: (FinancialStatus?) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onEditDeclaredBalance: () -> Unit,
    onCreateTransaction: (TransactionType) -> Unit,
    onOpenTransaction: (String) -> Unit,
    onToggleSettleTransaction: (String) -> Unit,
    onCreateCard: () -> Unit,
    onEditCard: (CreditCardEntity) -> Unit,
    onCloseStatement: (String) -> Unit,
    onPayStatement: (String) -> Unit,
    onCreateInstallment: () -> Unit,
    onSettleRemainingInstallments: (String) -> Unit,
    onDeleteInstallmentPurchase: (String) -> Unit,
    onCreateRecurring: () -> Unit,
    onToggleRecurringActive: (RecurringRuleEntity) -> Unit,
    onDeleteRecurringRule: (String) -> Unit,
    onCreateBudget: () -> Unit,
    onDeleteBudget: (String) -> Unit,
    onCreateGoal: () -> Unit,
    onEditGoal: (FinancialGoalEntity) -> Unit,
    onUpdateGoalAmount: (FinancialGoalEntity, Long) -> Unit,
    onDeleteGoal: (String) -> Unit,
    onCreateLoan: () -> Unit,
    onEditLoan: (LoanEntity) -> Unit,
    onPayLoanInstallment: (LoanEntity) -> Unit,
    onDeleteLoan: (String) -> Unit,
    onGenerateCsv: suspend () -> Result<String>,
    onCsvExported: () -> Unit,
    onLogError: (String, Throwable) -> Unit,
    modifier: Modifier = Modifier
) {
    val palette = LocalNexusPalette.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val csvLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("text/csv")
    ) { uri ->
        if (uri == null) return@rememberLauncherForActivityResult
        scope.launch {
            val csvResult = onGenerateCsv()
            csvResult.onSuccess { csv ->
                runCatching {
                    withContext(Dispatchers.IO) {
                        context.contentResolver.openOutputStream(uri)?.use { stream ->
                            stream.write(csv.toByteArray(Charsets.UTF_8))
                        } ?: error("Não foi possível abrir o destino selecionado.")
                    }
                }.onSuccess {
                    onCsvExported()
                }.onFailure { err ->
                    onLogError("Financeiro (Exportação CSV)", err)
                }
            }
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Financeiro",
                style = MaterialTheme.typography.headlineMedium,
                color = palette.textPrimary
            )
            OutlinedButton(
                onClick = { onCreateTransaction(TransactionType.EXPENSE) },
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(Icons.Outlined.Add, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Lançamento", color = palette.textPrimary)
            }
        }

        ScrollableTabRow(
            selectedTabIndex = FinanceTabMode.entries.indexOf(filter.tabMode),
            containerColor = palette.background,
            contentColor = palette.highlight,
            edgePadding = 16.dp
        ) {
            FinanceTabMode.entries.forEach { tab ->
                Tab(
                    selected = filter.tabMode == tab,
                    onClick = { onTabChange(tab) },
                    text = { Text(tab.label, maxLines = 1) }
                )
            }
        }

        when (filter.tabMode) {
            FinanceTabMode.OVERVIEW -> OverviewTabContent(
                summary = summary,
                onEditDeclaredBalance = onEditDeclaredBalance,
                onOpenTransaction = onOpenTransaction,
                onCreateTransaction = onCreateTransaction
            )
            FinanceTabMode.TRANSACTIONS -> TransactionsTabContent(
                transactions = transactions,
                filter = filter,
                onTypeFilterChange = onTypeFilterChange,
                onStatusFilterChange = onStatusFilterChange,
                onSearchQueryChange = onSearchQueryChange,
                onOpenTransaction = onOpenTransaction,
                onToggleSettleTransaction = onToggleSettleTransaction,
                onCreateTransaction = { onCreateTransaction(TransactionType.EXPENSE) },
                onExportCsv = {
                    val stamp = DateTimeUtils.formatDate(DateTimeUtils.nowEpochMillis()).replace("/", "-")
                    csvLauncher.launch("nexus-financeiro-$stamp.csv")
                }
            )
            FinanceTabMode.CARDS -> CardsTabContent(
                cards = cardsWithStatements,
                onCreateCard = onCreateCard,
                onEditCard = onEditCard,
                onCloseStatement = onCloseStatement,
                onPayStatement = onPayStatement,
                onOpenTransaction = onOpenTransaction
            )
            FinanceTabMode.RECURRING -> RecurringAndInstallmentsTabContent(
                installmentPurchases = installmentPurchases,
                recurringRules = recurringRules,
                onCreateInstallment = onCreateInstallment,
                onSettleRemainingInstallments = onSettleRemainingInstallments,
                onDeleteInstallmentPurchase = onDeleteInstallmentPurchase,
                onCreateRecurring = onCreateRecurring,
                onToggleRecurringActive = onToggleRecurringActive,
                onDeleteRecurringRule = onDeleteRecurringRule
            )
            FinanceTabMode.PLANNING -> PlanningAndLoansTabContent(
                budgets = budgets,
                goals = financialGoals,
                loans = loans,
                auditHistory = auditHistory,
                onCreateBudget = onCreateBudget,
                onDeleteBudget = onDeleteBudget,
                onCreateGoal = onCreateGoal,
                onEditGoal = onEditGoal,
                onUpdateGoalAmount = onUpdateGoalAmount,
                onDeleteGoal = onDeleteGoal,
                onCreateLoan = onCreateLoan,
                onEditLoan = onEditLoan,
                onPayLoanInstallment = onPayLoanInstallment,
                onDeleteLoan = onDeleteLoan
            )
        }
    }
}

@Composable
private fun OverviewTabContent(
    summary: FinanceSummary,
    onEditDeclaredBalance: () -> Unit,
    onOpenTransaction: (String) -> Unit,
    onCreateTransaction: (TransactionType) -> Unit
) {
    val palette = LocalNexusPalette.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true, onClick = onEditDeclaredBalance) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Saldo atual informado",
                            style = MaterialTheme.typography.labelMedium,
                            color = palette.textSecondary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = DateTimeUtils.formatBrl(summary.declaredBalanceCents),
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = if (summary.declaredBalanceCents >= 0L) palette.textPrimary else palette.danger
                        )
                    }
                    IconButton(onClick = onEditDeclaredBalance) {
                        Icon(Icons.Outlined.Edit, contentDescription = "Editar saldo", tint = palette.highlight)
                    }
                }
            }
        }

        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                FinanceMetricCard(
                    title = "Receitas recebidas",
                    amountCents = summary.receivedIncomeCents,
                    accent = palette.positive,
                    subtitle = "Previstas: ${DateTimeUtils.formatBrl(summary.plannedIncomeCents)}",
                    modifier = Modifier.weight(1f)
                )
                FinanceMetricCard(
                    title = "Despesas pagas",
                    amountCents = summary.paidExpenseCents,
                    accent = palette.textPrimary,
                    subtitle = "Pendentes: ${DateTimeUtils.formatBrl(summary.pendingExpenseCents)}",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        if (summary.overdueExpenseCents > 0L) {
            item {
                NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Contas vencidas em aberto",
                            style = MaterialTheme.typography.titleMedium,
                            color = palette.danger
                        )
                        Text(
                            text = DateTimeUtils.formatBrl(summary.overdueExpenseCents),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = palette.danger
                        )
                    }
                }
            }
        }

        item {
            SectionHeader(title = "Próximo vencimento")
            Spacer(modifier = Modifier.height(6.dp))
            val next = summary.nextDueTransaction
            if (next == null) {
                NexusCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Nenhuma conta pendente com vencimento futuro.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = palette.textSecondary
                    )
                }
            } else {
                TransactionRowCard(
                    item = next,
                    onClick = { onOpenTransaction(next.transaction.id) },
                    onToggleSettle = {}
                )
            }
        }

        item {
            SectionHeader(title = "Fluxo mensal (Realizado)")
            Spacer(modifier = Modifier.height(6.dp))
            MonthlyCashflowChartCard(points = summary.monthlyCashflow)
        }

        item {
            SectionHeader(title = "Despesas por categoria (Mês atual)")
            Spacer(modifier = Modifier.height(6.dp))
            CategorySpendCard(points = summary.categorySpend)
        }

        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(
                    onClick = { onCreateTransaction(TransactionType.INCOME) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("+ Receita", color = palette.positive)
                }
                OutlinedButton(
                    onClick = { onCreateTransaction(TransactionType.EXPENSE) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("+ Despesa", color = palette.textPrimary)
                }
            }
        }
    }
}

@Composable
private fun FinanceMetricCard(
    title: String,
    amountCents: Long,
    accent: Color,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    val palette = LocalNexusPalette.current
    NexusCard(modifier = modifier, elevated = true) {
        Text(text = title, style = MaterialTheme.typography.labelMedium, color = palette.textSecondary)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = DateTimeUtils.formatBrl(amountCents),
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            color = accent,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.labelSmall,
            color = palette.textSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun MonthlyCashflowChartCard(points: List<MonthlyCashflowPoint>) {
    val palette = LocalNexusPalette.current
    NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true) {
        if (points.isEmpty()) {
            Text(
                text = "Sem lançamentos realizados para compor o gráfico mensal.",
                style = MaterialTheme.typography.bodyMedium,
                color = palette.textSecondary
            )
        } else {
            val maxValue = points.maxOfOrNull { maxOf(it.receivedIncomeCents, it.paidExpenseCents) }
                ?.coerceAtLeast(1L) ?: 1L
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(palette.positive))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Receitas", style = MaterialTheme.typography.labelSmall, color = palette.textSecondary)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(palette.danger))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Despesas", style = MaterialTheme.typography.labelSmall, color = palette.textSecondary)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
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
                    val incH = (pt.receivedIncomeCents.toFloat() / maxValue.toFloat()) * (baseline - 8f)
                    val expH = (pt.paidExpenseCents.toFloat() / maxValue.toFloat()) * (baseline - 8f)

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
            Spacer(modifier = Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                points.forEach { pt ->
                    Text(
                        text = pt.yearMonthLabel,
                        style = MaterialTheme.typography.labelSmall,
                        color = palette.textSecondary
                    )
                }
            }
        }
    }
}

@Composable
private fun CategorySpendCard(points: List<CategorySpendPoint>) {
    val palette = LocalNexusPalette.current
    NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true) {
        if (points.isEmpty()) {
            Text(
                text = "Nenhuma despesa registrada neste mês.",
                style = MaterialTheme.typography.bodyMedium,
                color = palette.textSecondary
            )
        } else {
            val total = points.sumOf { it.totalCents }.coerceAtLeast(1L)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                points.forEach { pt ->
                    val fraction = (pt.totalCents.toFloat() / total.toFloat()).coerceIn(0f, 1f)
                    val barColor = parseHexColorOrDefault(pt.colorHex, palette.highlight)
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(pt.categoryName, style = MaterialTheme.typography.bodyMedium, color = palette.textPrimary)
                            Text(
                                text = DateTimeUtils.formatBrl(pt.totalCents),
                                style = MaterialTheme.typography.labelMedium,
                                color = palette.textSecondary
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(7.dp)
                                .clip(RoundedCornerShape(4.dp))
                        ) {
                            drawRect(color = palette.border, size = size)
                            drawRect(
                                color = barColor,
                                size = Size(size.width * fraction, size.height)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TransactionsTabContent(
    transactions: List<TransactionWithDetails>,
    filter: FinanceFilterState,
    onTypeFilterChange: (TransactionType?) -> Unit,
    onStatusFilterChange: (FinancialStatus?) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onOpenTransaction: (String) -> Unit,
    onToggleSettleTransaction: (String) -> Unit,
    onCreateTransaction: () -> Unit,
    onExportCsv: () -> Unit
) {
    val palette = LocalNexusPalette.current
    var showStatusMenu by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            OutlinedTextField(
                value = filter.searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar lançamentos, categorias ou eventos") },
                leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
                trailingIcon = {
                    if (filter.searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(Icons.Outlined.Close, contentDescription = "Limpar")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilterChip(
                    selected = filter.typeFilter == null,
                    onClick = { onTypeFilterChange(null) },
                    label = { Text("Todos") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = palette.surfaceElevated,
                        selectedLabelColor = palette.highlight
                    )
                )
                TransactionType.entries.forEach { t ->
                    FilterChip(
                        selected = filter.typeFilter == t,
                        onClick = { onTypeFilterChange(if (filter.typeFilter == t) null else t) },
                        label = { Text(t.label) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = palette.surfaceElevated,
                            selectedLabelColor = palette.highlight
                        )
                    )
                }
                Box {
                    FilterChip(
                        selected = filter.statusFilter != null,
                        onClick = { showStatusMenu = true },
                        label = { Text(filter.statusFilter?.let { "Status: ${it.label}" } ?: "Status: Todos") },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = palette.surfaceElevated,
                            selectedLabelColor = palette.highlight
                        )
                    )
                    DropdownMenu(expanded = showStatusMenu, onDismissRequest = { showStatusMenu = false }) {
                        DropdownMenuItem(text = { Text("Todos") }, onClick = {
                            onStatusFilterChange(null); showStatusMenu = false
                        })
                        FinancialStatus.entries.forEach { st ->
                            DropdownMenuItem(text = { Text(st.label) }, onClick = {
                                onStatusFilterChange(st); showStatusMenu = false
                            })
                        }
                    }
                }
                OutlinedButton(onClick = onExportCsv, shape = RoundedCornerShape(10.dp)) {
                    Icon(Icons.Outlined.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("CSV", color = palette.textPrimary)
                }
            }
        }

        if (transactions.isEmpty()) {
            item {
                NexusEmptyState(
                    icon = Icons.Outlined.AccountBalanceWallet,
                    title = "Nenhum lançamento encontrado",
                    subtitle = "Registre receitas, despesas ou contas para acompanhar previstos e realizados.",
                    actionLabel = "Novo lançamento",
                    onAction = onCreateTransaction
                )
            }
        } else {
            items(transactions, key = { it.transaction.id }) { item ->
                TransactionRowCard(
                    item = item,
                    onClick = { onOpenTransaction(item.transaction.id) },
                    onToggleSettle = { onToggleSettleTransaction(item.transaction.id) }
                )
            }
        }
    }
}

@Composable
fun TransactionRowCard(
    item: TransactionWithDetails,
    onClick: () -> Unit,
    onToggleSettle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val palette = LocalNexusPalette.current
    val tx = item.transaction
    val isSettled = tx.status == FinancialStatus.SETTLED
    val isIncome = tx.type == TransactionType.INCOME

    NexusCard(
        modifier = modifier.fillMaxWidth(),
        elevated = true,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onToggleSettle,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = if (isSettled) Icons.Outlined.CheckCircle else Icons.Outlined.RadioButtonUnchecked,
                    contentDescription = if (isSettled) "Reabrir como pendente" else "Marcar realizado",
                    tint = when (tx.status) {
                        FinancialStatus.SETTLED -> palette.positive
                        FinancialStatus.OVERDUE -> palette.danger
                        else -> palette.textSecondary
                    }
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tx.description,
                    style = MaterialTheme.typography.titleMedium,
                    color = palette.textPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                val meta = buildList {
                    add(tx.status.label)
                    add(tx.paymentMethod.label)
                    val dateRef = tx.dueAt ?: tx.occurredAt
                    add(DateTimeUtils.formatDate(dateRef))
                    item.category?.let { add(it.name) }
                    item.event?.let { add("Evento: ${it.name}") }
                    item.creditCard?.let { add(it.name) }
                }.joinToString(" · ")
                Text(
                    text = meta,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (tx.status == FinancialStatus.OVERDUE) palette.danger else palette.textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = (if (isIncome) "+" else "-") + DateTimeUtils.formatBrl(tx.amountCents),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = if (isIncome) palette.positive else palette.textPrimary
            )
        }
    }
}

@Composable
private fun CardsTabContent(
    cards: List<CardWithStatements>,
    onCreateCard: () -> Unit,
    onEditCard: (CreditCardEntity) -> Unit,
    onCloseStatement: (String) -> Unit,
    onPayStatement: (String) -> Unit,
    onOpenTransaction: (String) -> Unit
) {
    val palette = LocalNexusPalette.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Cartões e Faturas", style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                OutlinedButton(onClick = onCreateCard, shape = RoundedCornerShape(10.dp)) {
                    Text("Novo cartão", color = palette.textPrimary)
                }
            }
        }

        if (cards.isEmpty()) {
            item {
                NexusEmptyState(
                    icon = Icons.Outlined.CreditCard,
                    title = "Nenhum cartão cadastrado",
                    subtitle = "Cadastre cartões para agrupar compras por fechamento e quitar faturas sem duplicar despesas.",
                    actionLabel = "Adicionar cartão",
                    onAction = onCreateCard
                )
            }
        } else {
            items(cards, key = { it.card.id }) { cw ->
                val card = cw.card
                NexusCard(
                    modifier = Modifier.fillMaxWidth(),
                    elevated = true,
                    onClick = { onEditCard(card) }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(card.name, style = MaterialTheme.typography.titleLarge, color = palette.textPrimary)
                            Text(
                                text = buildList {
                                    if (card.institution.isNotBlank()) add(card.institution)
                                    add("Fecha dia ${card.closingDay}")
                                    add("Vence dia ${card.dueDay}")
                                }.joinToString(" · "),
                                style = MaterialTheme.typography.labelMedium,
                                color = palette.textSecondary
                            )
                        }
                        Text(
                            text = DateTimeUtils.formatBrl(cw.currentOpenTotalCents),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            color = palette.warning
                        )
                    }

                    if (card.totalLimitCents > 0L) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Limite total: ${DateTimeUtils.formatBrl(card.totalLimitCents)} · Disponível est.: ${DateTimeUtils.formatBrl(cw.computedRemainingLimitCents)}",
                            style = MaterialTheme.typography.labelMedium,
                            color = palette.textSecondary
                        )
                    }

                    if (cw.statements.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(color = palette.border)
                        Spacer(modifier = Modifier.height(8.dp))
                        cw.statements.take(4).forEach { sw ->
                            val st = sw.statement
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Fatura ${st.yearMonth} · ${st.status.label}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = if (st.status == StatementStatus.PAID) palette.positive else palette.textPrimary
                                    )
                                    Text(
                                        text = "Vence ${DateTimeUtils.formatDate(st.dueAt)} · ${sw.transactions.size} item(ns) · ${DateTimeUtils.formatBrl(sw.totalCents)}",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = palette.textSecondary
                                    )
                                }
                                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    if (st.status == StatementStatus.OPEN) {
                                        TextButton(onClick = { onCloseStatement(st.id) }) {
                                            Text("Fechar", color = palette.textSecondary)
                                        }
                                    }
                                    if (st.status != StatementStatus.PAID) {
                                        OutlinedButton(
                                            onClick = { onPayStatement(st.id) },
                                            shape = RoundedCornerShape(8.dp)
                                        ) {
                                            Text("Pagar", color = palette.positive)
                                        }
                                    }
                                }
                            }
                            if (sw.transactions.isNotEmpty() && st.status != StatementStatus.PAID) {
                                sw.transactions.take(3).forEach { txItem ->
                                    TextButton(
                                        onClick = { onOpenTransaction(txItem.transaction.id) },
                                        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp)
                                    ) {
                                        Text(
                                            text = "  • ${txItem.transaction.description}: ${DateTimeUtils.formatBrl(txItem.transaction.amountCents)}",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = palette.textSecondary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RecurringAndInstallmentsTabContent(
    installmentPurchases: List<InstallmentPurchaseWithItems>,
    recurringRules: List<RecurringRuleEntity>,
    onCreateInstallment: () -> Unit,
    onSettleRemainingInstallments: (String) -> Unit,
    onDeleteInstallmentPurchase: (String) -> Unit,
    onCreateRecurring: () -> Unit,
    onToggleRecurringActive: (RecurringRuleEntity) -> Unit,
    onDeleteRecurringRule: (String) -> Unit
) {
    val palette = LocalNexusPalette.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Compras Parceladas", style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                OutlinedButton(onClick = onCreateInstallment, shape = RoundedCornerShape(10.dp)) {
                    Text("Novo parcelamento", color = palette.textPrimary)
                }
            }
        }

        if (installmentPurchases.isEmpty()) {
            item {
                NexusEmptyState(
                    icon = Icons.Outlined.CreditCard,
                    title = "Nenhum parcelamento cadastrado",
                    subtitle = "Gere parcelas mensais automaticamente no cartão ou em conta.",
                    actionLabel = "Criar parcelamento",
                    onAction = onCreateInstallment
                )
            }
        } else {
            items(installmentPurchases, key = { it.purchase.id }) { item ->
                val p = item.purchase
                val progress = if (p.installmentsCount > 0) {
                    item.paidCount.toFloat() / p.installmentsCount.toFloat()
                } else 0f

                NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(p.description, style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                            Text(
                                text = "${item.paidCount}/${p.installmentsCount} pagas · Total ${DateTimeUtils.formatBrl(p.totalAmountCents)}",
                                style = MaterialTheme.typography.labelMedium,
                                color = palette.textSecondary
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (item.remainingCents > 0L) {
                                TextButton(onClick = { onSettleRemainingInstallments(p.id) }) {
                                    Text("Quitar", color = palette.positive)
                                }
                            } else {
                                Text("Quitado", style = MaterialTheme.typography.labelMedium, color = palette.positive)
                            }
                            IconButton(onClick = { onDeleteInstallmentPurchase(p.id) }) {
                                Icon(
                                    imageVector = Icons.Outlined.DeleteOutline,
                                    contentDescription = "Excluir parcelamento",
                                    tint = palette.danger
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { progress.coerceIn(0f, 1f) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = if (item.remainingCents == 0L) palette.positive else palette.highlight,
                        trackColor = palette.border
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Pago: ${DateTimeUtils.formatBrl(item.paidCents)} · Restante: ${DateTimeUtils.formatBrl(item.remainingCents)}",
                        style = MaterialTheme.typography.labelMedium,
                        color = palette.textSecondary
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Contas e Receitas Recorrentes", style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                OutlinedButton(onClick = onCreateRecurring, shape = RoundedCornerShape(10.dp)) {
                    Text("Nova recorrência", color = palette.textPrimary)
                }
            }
        }

        if (recurringRules.isEmpty()) {
            item {
                NexusEmptyState(
                    icon = Icons.Outlined.Repeat,
                    title = "Nenhuma recorrência ativa",
                    subtitle = "Configure assinaturas, aluguel ou salários com geração idempotente sem duplicação.",
                    actionLabel = "Criar recorrência",
                    onAction = onCreateRecurring
                )
            }
        } else {
            items(recurringRules, key = { it.id }) { rule ->
                NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(rule.title, style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                            Text(
                                text = "${rule.type.label} · ${DateTimeUtils.formatBrl(rule.amountCents)} · ${rule.frequency.label}",
                                style = MaterialTheme.typography.labelMedium,
                                color = if (rule.type == TransactionType.INCOME) palette.positive else palette.textSecondary
                            )
                            Text(
                                text = "Início: ${DateTimeUtils.formatDate(rule.startAt)} · ${rule.paymentMethod.label}",
                                style = MaterialTheme.typography.labelSmall,
                                color = palette.textSecondary
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Switch(
                                checked = rule.isActive,
                                onCheckedChange = { onToggleRecurringActive(rule) }
                            )
                            IconButton(onClick = { onDeleteRecurringRule(rule.id) }) {
                                Icon(
                                    imageVector = Icons.Outlined.DeleteOutline,
                                    contentDescription = "Excluir recorrência",
                                    tint = palette.danger
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PlanningAndLoansTabContent(
    budgets: List<BudgetProgress>,
    goals: List<FinancialGoalEntity>,
    loans: List<LoanEntity>,
    auditHistory: List<AuditHistoryEntity>,
    onCreateBudget: () -> Unit,
    onDeleteBudget: (String) -> Unit,
    onCreateGoal: () -> Unit,
    onEditGoal: (FinancialGoalEntity) -> Unit,
    onUpdateGoalAmount: (FinancialGoalEntity, Long) -> Unit,
    onDeleteGoal: (String) -> Unit,
    onCreateLoan: () -> Unit,
    onEditLoan: (LoanEntity) -> Unit,
    onPayLoanInstallment: (LoanEntity) -> Unit,
    onDeleteLoan: (String) -> Unit
) {
    val palette = LocalNexusPalette.current
    var goalAmountDialogTarget by remember { mutableStateOf<FinancialGoalEntity?>(null) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Orçamentos do mês", style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                OutlinedButton(onClick = onCreateBudget, shape = RoundedCornerShape(10.dp)) {
                    Text("Definir orçamento", color = palette.textPrimary)
                }
            }
        }

        if (budgets.isEmpty()) {
            item {
                NexusCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Nenhum limite mensal definido por categoria neste mês.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = palette.textSecondary
                    )
                }
            }
        } else {
            items(budgets, key = { it.budget.id }) { bp ->
                val exceeded = bp.exceededCents > 0L
                NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(bp.category.name, style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                            Text(
                                text = "Gasto: ${DateTimeUtils.formatBrl(bp.spentCents)} / Limite: ${DateTimeUtils.formatBrl(bp.budget.limitCents)} (${bp.usedPercent}%)",
                                style = MaterialTheme.typography.labelMedium,
                                color = if (exceeded) palette.danger else palette.textSecondary
                            )
                        }
                        IconButton(onClick = { onDeleteBudget(bp.budget.id) }) {
                            Icon(Icons.Outlined.DeleteOutline, contentDescription = "Remover orçamento", tint = palette.textSecondary)
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { bp.usedFraction },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = if (exceeded) palette.danger else palette.highlight,
                        trackColor = palette.border
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Metas Financeiras", style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                OutlinedButton(onClick = onCreateGoal, shape = RoundedCornerShape(10.dp)) {
                    Text("Nova meta", color = palette.textPrimary)
                }
            }
        }

        if (goals.isEmpty()) {
            item {
                NexusEmptyState(
                    icon = Icons.Outlined.Savings,
                    title = "Nenhuma meta financeira",
                    subtitle = "Defina reservas de emergência, viagens ou aquisições e acompanhe o progresso.",
                    actionLabel = "Criar meta",
                    onAction = onCreateGoal
                )
            }
        } else {
            items(goals, key = { it.id }) { goal ->
                val fraction = if (goal.targetAmountCents > 0L) {
                    (goal.currentAmountCents.toFloat() / goal.targetAmountCents.toFloat()).coerceIn(0f, 1f)
                } else 0f
                val pct = (fraction * 100f).toInt()
                NexusCard(
                    modifier = Modifier.fillMaxWidth(),
                    elevated = true,
                    onClick = { onEditGoal(goal) }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(goal.name, style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                            Text(
                                text = "${DateTimeUtils.formatBrl(goal.currentAmountCents)} de ${DateTimeUtils.formatBrl(goal.targetAmountCents)} ($pct%)",
                                style = MaterialTheme.typography.labelMedium,
                                color = if (pct >= 100) palette.positive else palette.textSecondary
                            )
                        }
                        Row {
                            TextButton(onClick = { goalAmountDialogTarget = goal }) {
                                Text("Atualizar", color = palette.info)
                            }
                            IconButton(onClick = { onDeleteGoal(goal.id) }) {
                                Icon(Icons.Outlined.DeleteOutline, contentDescription = "Excluir", tint = palette.textSecondary)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { fraction },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = if (pct >= 100) palette.positive else palette.highlight,
                        trackColor = palette.border
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Empréstimos e Dívidas", style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                OutlinedButton(onClick = onCreateLoan, shape = RoundedCornerShape(10.dp)) {
                    Text("Novo empréstimo", color = palette.textPrimary)
                }
            }
        }

        if (loans.isEmpty()) {
            item {
                NexusCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Nenhum empréstimo ou financiamento cadastrado.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = palette.textSecondary
                    )
                }
            }
        } else {
            items(loans, key = { it.id }) { loan ->
                val done = loan.paidInstallments >= loan.installmentsTotal
                NexusCard(
                    modifier = Modifier.fillMaxWidth(),
                    elevated = true,
                    onClick = { onEditLoan(loan) }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(loan.creditor, style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                            Text(
                                text = "${loan.paidInstallments}/${loan.installmentsTotal} pagas · Saldo devedor: ${DateTimeUtils.formatBrl(loan.remainingBalanceCents)}",
                                style = MaterialTheme.typography.labelMedium,
                                color = if (done) palette.positive else palette.warning
                            )
                            loan.nextDueAt?.let { due ->
                                Text(
                                    text = "Próximo vencimento: ${DateTimeUtils.formatDate(due)}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = palette.textSecondary
                                )
                            }
                        }
                        Row {
                            if (!done) {
                                TextButton(onClick = { onPayLoanInstallment(loan) }) {
                                    Text("Pagar parcela", color = palette.positive)
                                }
                            }
                            IconButton(onClick = { onDeleteLoan(loan.id) }) {
                                Icon(Icons.Outlined.DeleteOutline, contentDescription = "Excluir", tint = palette.textSecondary)
                            }
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(6.dp))
            SectionHeader(title = "Histórico recente de alterações")
        }

        if (auditHistory.isEmpty()) {
            item {
                NexusCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "As movimentações financeiras e de faturas serão registradas aqui.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = palette.textSecondary
                    )
                }
            }
        } else {
            items(auditHistory.take(15), key = { it.id }) { audit ->
                NexusCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "${audit.action} · ${DateTimeUtils.formatDateTime(audit.occurredAt)}",
                        style = MaterialTheme.typography.labelMedium,
                        color = palette.textSecondary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = audit.summary,
                        style = MaterialTheme.typography.bodyMedium,
                        color = palette.textPrimary
                    )
                }
            }
        }
    }

    goalAmountDialogTarget?.let { goal ->
        var amountInput by remember(goal.id) {
            mutableStateOf(DateTimeUtils.centsToInputString(goal.currentAmountCents))
        }
        AlertDialog(
            onDismissRequest = { goalAmountDialogTarget = null },
            containerColor = palette.surfaceElevated,
            title = { Text("Atualizar progresso", color = palette.textPrimary) },
            text = {
                OutlinedTextField(
                    value = amountInput,
                    onValueChange = { amountInput = it },
                    label = { Text("Valor acumulado (R$)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true
                )
            },
            confirmButton = {
                Button(onClick = {
                    val cents = DateTimeUtils.parseBrlToCents(amountInput) ?: 0L
                    onUpdateGoalAmount(goal, cents)
                    goalAmountDialogTarget = null
                }) {
                    Text("Salvar")
                }
            },
            dismissButton = {
                TextButton(onClick = { goalAmountDialogTarget = null }) { Text("Cancelar") }
            }
        )
    }
}
