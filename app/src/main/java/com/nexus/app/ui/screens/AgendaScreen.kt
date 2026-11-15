package com.nexus.app.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.nexus.app.core.DateTimeUtils
import com.nexus.app.data.db.entity.CategoryEntity
import com.nexus.app.domain.model.AgendaItem
import com.nexus.app.domain.model.FinancialStatus
import com.nexus.app.domain.model.StatementStatus
import com.nexus.app.domain.model.TaskStatus
import com.nexus.app.domain.model.TransactionType
import com.nexus.app.ui.AgendaFilterState
import com.nexus.app.ui.AgendaViewMode
import com.nexus.app.ui.components.NexusCard
import com.nexus.app.ui.components.NexusEmptyState
import com.nexus.app.ui.components.parseHexColorOrDefault
import com.nexus.app.ui.theme.LocalNexusPalette
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaScreen(
    items: List<AgendaItem>,
    categories: List<CategoryEntity>,
    filter: AgendaFilterState,
    onViewModeChange: (AgendaViewMode) -> Unit,
    onDateChange: (LocalDate) -> Unit,
    onCategoryFilterChange: (String?) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onOpenAppointment: (String) -> Unit,
    onOpenTask: (String) -> Unit,
    onOpenEvent: (String) -> Unit,
    onOpenTransaction: (String) -> Unit,
    onOpenCards: () -> Unit,
    onOpenProject: (String) -> Unit,
    onCreateAppointment: () -> Unit,
    modifier: Modifier = Modifier
) {
    val palette = LocalNexusPalette.current

    val displayedItems = when (filter.viewMode) {
        AgendaViewMode.DAY -> items.filter { it.date == filter.selectedDate }
        AgendaViewMode.WEEK -> {
            val startOfWeek = filter.selectedDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            val endOfWeek = startOfWeek.plusDays(6)
            items.filter { it.date in startOfWeek..endOfWeek }
        }
        AgendaViewMode.MONTH -> items.filter { it.date == filter.selectedDate }
        AgendaViewMode.LIST -> items
    }

    val conflictsInView = displayedItems
        .filterIsInstance<AgendaItem.AppointmentItem>()
        .filter { it.hasConflict }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Agenda",
                style = MaterialTheme.typography.headlineMedium,
                color = palette.textPrimary
            )
        }

        item {
            SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                AgendaViewMode.entries.forEachIndexed { index, mode ->
                    SegmentedButton(
                        selected = filter.viewMode == mode,
                        onClick = { onViewModeChange(mode) },
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = AgendaViewMode.entries.size
                        ),
                        label = { Text(mode.label, maxLines = 1) }
                    )
                }
            }
        }

        item {
            OutlinedTextField(
                value = filter.searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar compromissos, tarefas, eventos ou vencimentos") },
                leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
                trailingIcon = {
                    if (filter.searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(Icons.Outlined.Close, contentDescription = "Limpar busca")
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
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = filter.categoryFilterId == null,
                    onClick = { onCategoryFilterChange(null) },
                    label = { Text("Todas") },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = palette.surfaceElevated,
                        selectedLabelColor = palette.highlight
                    )
                )
                categories.forEach { cat ->
                    val dotColor = parseHexColorOrDefault(cat.colorHex, palette.textSecondary)
                    FilterChip(
                        selected = filter.categoryFilterId == cat.id,
                        onClick = {
                            onCategoryFilterChange(
                                if (filter.categoryFilterId == cat.id) null else cat.id
                            )
                        },
                        leadingIcon = {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(dotColor)
                            )
                        },
                        label = { Text(cat.name) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = palette.surfaceElevated,
                            selectedLabelColor = palette.highlight
                        )
                    )
                }
            }
        }

        if (filter.viewMode != AgendaViewMode.LIST) {
            item {
                DateNavigationBar(
                    viewMode = filter.viewMode,
                    selectedDate = filter.selectedDate,
                    onDateChange = onDateChange
                )
            }
        }

        if (filter.viewMode == AgendaViewMode.MONTH) {
            item {
                MonthCalendarGrid(
                    selectedDate = filter.selectedDate,
                    items = items,
                    onDateSelected = onDateChange
                )
            }
        }

        if (conflictsInView.isNotEmpty()) {
            item {
                NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.WarningAmber,
                            contentDescription = "Alerta de conflito",
                            tint = palette.warning
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Atenção: ${conflictsInView.size} compromisso(s) com conflito de horário neste período.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = palette.warning
                        )
                    }
                }
            }
        }

        if (displayedItems.isEmpty()) {
            item {
                NexusEmptyState(
                    icon = Icons.Outlined.CalendarMonth,
                    title = "Sem itens para exibir",
                    subtitle = "Compromissos, tarefas com prazo, eventos e vencimentos aparecem aqui automaticamente, sem duplicação.",
                    actionLabel = "Novo compromisso",
                    onAction = onCreateAppointment
                )
            }
        } else {
            items(displayedItems, key = { it.id }) { agendaItem ->
                when (agendaItem) {
                    is AgendaItem.AppointmentItem -> {
                        AppointmentAgendaRow(
                            item = agendaItem,
                            onClick = { onOpenAppointment(agendaItem.appointment.id) }
                        )
                    }
                    is AgendaItem.TaskDeadlineItem -> {
                        TaskDeadlineAgendaRow(
                            item = agendaItem,
                            onClick = { onOpenTask(agendaItem.task.id) }
                        )
                    }
                    is AgendaItem.EventItem -> {
                        EventAgendaRow(
                            item = agendaItem,
                            onClick = { onOpenEvent(agendaItem.event.id) }
                        )
                    }
                    is AgendaItem.FinancialDueItem -> {
                        FinancialDueAgendaRow(
                            item = agendaItem,
                            onClick = { onOpenTransaction(agendaItem.transaction.id) }
                        )
                    }
                    is AgendaItem.StatementDueItem -> {
                        StatementDueAgendaRow(
                            item = agendaItem,
                            onClick = onOpenCards
                        )
                    }
                    is AgendaItem.ProjectDeadlineItem -> {
                        ProjectDeadlineAgendaRow(
                            item = agendaItem,
                            onClick = { onOpenProject(agendaItem.project.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DateNavigationBar(
    viewMode: AgendaViewMode,
    selectedDate: LocalDate,
    onDateChange: (LocalDate) -> Unit
) {
    val palette = LocalNexusPalette.current
    val label = when (viewMode) {
        AgendaViewMode.DAY -> DateTimeUtils.formatFullDateWithWeekday(selectedDate)
        AgendaViewMode.WEEK -> {
            val start = selectedDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            val end = start.plusDays(6)
            "Semana ${DateTimeUtils.formatDayMonth(start)} – ${DateTimeUtils.formatDayMonth(end)}"
        }
        AgendaViewMode.MONTH -> DateTimeUtils.formatMonthYear(selectedDate)
        AgendaViewMode.LIST -> "Cronológica"
    }

    NexusCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    val prev = when (viewMode) {
                        AgendaViewMode.DAY -> selectedDate.minusDays(1)
                        AgendaViewMode.WEEK -> selectedDate.minusWeeks(1)
                        AgendaViewMode.MONTH -> selectedDate.minusMonths(1)
                        AgendaViewMode.LIST -> selectedDate
                    }
                    onDateChange(prev)
                }
            ) {
                Icon(Icons.AutoMirrored.Outlined.KeyboardArrowLeft, contentDescription = "Anterior")
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onDateChange(DateTimeUtils.todayLocalDate()) }
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleMedium,
                    color = palette.textPrimary
                )
                if (selectedDate != DateTimeUtils.todayLocalDate()) {
                    Text(
                        text = "Toque para voltar a hoje",
                        style = MaterialTheme.typography.labelSmall,
                        color = palette.info
                    )
                }
            }

            IconButton(
                onClick = {
                    val next = when (viewMode) {
                        AgendaViewMode.DAY -> selectedDate.plusDays(1)
                        AgendaViewMode.WEEK -> selectedDate.plusWeeks(1)
                        AgendaViewMode.MONTH -> selectedDate.plusMonths(1)
                        AgendaViewMode.LIST -> selectedDate
                    }
                    onDateChange(next)
                }
            ) {
                Icon(Icons.AutoMirrored.Outlined.KeyboardArrowRight, contentDescription = "Próximo")
            }
        }
    }
}

@Composable
private fun MonthCalendarGrid(
    selectedDate: LocalDate,
    items: List<AgendaItem>,
    onDateSelected: (LocalDate) -> Unit
) {
    val palette = LocalNexusPalette.current
    val yearMonth = YearMonth.from(selectedDate)
    val firstDay = yearMonth.atDay(1)
    val offset = (firstDay.dayOfWeek.value - 1)
    val daysInMonth = yearMonth.lengthOfMonth()
    val datesWithItems = items.map { it.date }.toSet()
    val today = DateTimeUtils.todayLocalDate()
    val weekHeaders = listOf("S", "T", "Q", "Q", "S", "S", "D")

    NexusCard(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            weekHeaders.forEach { dayName ->
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = dayName,
                        style = MaterialTheme.typography.labelSmall,
                        color = palette.textSecondary
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        val totalCells = offset + daysInMonth
        val rows = (totalCells + 6) / 7
        for (row in 0 until rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (col in 0 until 7) {
                    val cellIndex = row * 7 + col
                    val dayNumber = cellIndex - offset + 1
                    if (dayNumber in 1..daysInMonth) {
                        val cellDate = yearMonth.atDay(dayNumber)
                        val isSelected = cellDate == selectedDate
                        val isToday = cellDate == today
                        val hasEvents = cellDate in datesWithItems

                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { onDateSelected(cellDate) },
                            shape = RoundedCornerShape(8.dp),
                            color = if (isSelected) palette.highlight else palette.surface,
                            border = if (isToday && !isSelected) {
                                BorderStroke(1.dp, palette.info)
                            } else {
                                null
                            }
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = dayNumber.toString(),
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = if (isSelected || isToday) FontWeight.SemiBold else FontWeight.Normal
                                    ),
                                    color = if (isSelected) palette.background else palette.textPrimary
                                )
                                if (hasEvents) {
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(4.dp)
                                            .clip(CircleShape)
                                            .background(if (isSelected) palette.background else palette.info)
                                    )
                                }
                            }
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun AppointmentAgendaRow(
    item: AgendaItem.AppointmentItem,
    onClick: () -> Unit
) {
    val palette = LocalNexusPalette.current
    val appt = item.appointment
    val catColor = parseHexColorOrDefault(item.category?.colorHex, palette.info)

    NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true, onClick = onClick) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
            Icon(imageVector = Icons.Outlined.Event, contentDescription = "Compromisso", tint = catColor)
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = appt.title, style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                Spacer(modifier = Modifier.height(4.dp))
                val whenText = if (appt.isAllDay) {
                    "${DateTimeUtils.formatDate(appt.startAt)} · Dia inteiro"
                } else {
                    "${DateTimeUtils.formatDate(appt.startAt)} · ${DateTimeUtils.formatTime(appt.startAt)}–${DateTimeUtils.formatTime(appt.endAt)}"
                }
                val meta = buildList {
                    add("Compromisso")
                    add(whenText)
                    item.category?.let { add(it.name) }
                    if (appt.location.isNotBlank()) add(appt.location)
                }.joinToString(" · ")
                Text(text = meta, style = MaterialTheme.typography.labelMedium, color = palette.textSecondary)
                if (item.hasConflict) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Conflito de horário com: ${item.conflictTitles.joinToString(", ")}",
                        style = MaterialTheme.typography.labelMedium,
                        color = palette.warning
                    )
                }
            }
        }
    }
}

@Composable
private fun TaskDeadlineAgendaRow(
    item: AgendaItem.TaskDeadlineItem,
    onClick: () -> Unit
) {
    val palette = LocalNexusPalette.current
    val task = item.task
    val isDone = task.status == TaskStatus.COMPLETED
    val due = task.dueAt ?: task.createdAt
    val dueText = if (task.hasDueTime) DateTimeUtils.formatDateTime(due) else DateTimeUtils.formatDate(due)

    NexusCard(modifier = Modifier.fillMaxWidth(), elevated = false, onClick = onClick) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
            Icon(
                imageVector = Icons.Outlined.TaskAlt,
                contentDescription = "Tarefa com prazo",
                tint = when {
                    isDone -> palette.positive
                    item.isOverdue -> palette.danger
                    else -> palette.textSecondary
                }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        textDecoration = if (isDone) TextDecoration.LineThrough else TextDecoration.None
                    ),
                    color = if (isDone) palette.textSecondary else palette.textPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                val meta = buildList {
                    add("Tarefa")
                    add(dueText)
                    add(task.status.label)
                    item.project?.let { add(it.name) }
                    item.event?.let { add("Evento: ${it.name}") }
                }.joinToString(" · ")
                Text(
                    text = meta,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (item.isOverdue) palette.danger else palette.textSecondary
                )
            }
        }
    }
}

@Composable
private fun EventAgendaRow(
    item: AgendaItem.EventItem,
    onClick: () -> Unit
) {
    val palette = LocalNexusPalette.current
    val ev = item.event
    val catColor = parseHexColorOrDefault(item.category?.colorHex, palette.highlight)

    NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true, onClick = onClick) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
            Icon(imageVector = Icons.Outlined.ConfirmationNumber, contentDescription = "Evento", tint = catColor)
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = ev.name, style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                Spacer(modifier = Modifier.height(4.dp))
                val whenText = if (ev.isAllDay) {
                    "${DateTimeUtils.formatDate(ev.startAt)} · Dia inteiro"
                } else {
                    "${DateTimeUtils.formatDate(ev.startAt)} · ${DateTimeUtils.formatTime(ev.startAt)}–${DateTimeUtils.formatTime(ev.endAt)}"
                }
                val meta = buildList {
                    add("Evento")
                    add(whenText)
                    add("Ingresso: ${ev.ticketStatus.label}")
                    if (ev.locationName.isNotBlank()) add(ev.locationName)
                    if (item.totalCostCents > 0L) add(DateTimeUtils.formatBrl(item.totalCostCents))
                }.joinToString(" · ")
                Text(text = meta, style = MaterialTheme.typography.labelMedium, color = palette.textSecondary)
            }
        }
    }
}

@Composable
private fun FinancialDueAgendaRow(
    item: AgendaItem.FinancialDueItem,
    onClick: () -> Unit
) {
    val palette = LocalNexusPalette.current
    val tx = item.transaction
    val isIncome = tx.type == TransactionType.INCOME
    val isSettled = tx.status == FinancialStatus.SETTLED

    NexusCard(modifier = Modifier.fillMaxWidth(), elevated = false, onClick = onClick) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
            Icon(
                imageVector = Icons.Outlined.AccountBalanceWallet,
                contentDescription = "Financeiro",
                tint = when {
                    isSettled -> palette.positive
                    item.isOverdue -> palette.danger
                    else -> palette.warning
                }
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = tx.description,
                        style = MaterialTheme.typography.titleMedium,
                        color = palette.textPrimary,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = (if (isIncome) "+" else "-") + DateTimeUtils.formatBrl(tx.amountCents),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = if (isIncome) palette.positive else palette.textPrimary
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                val meta = buildList {
                    add(tx.type.label)
                    add(tx.status.label)
                    add(DateTimeUtils.formatDate(tx.dueAt ?: tx.occurredAt))
                    item.category?.let { add(it.name) }
                }.joinToString(" · ")
                Text(
                    text = meta,
                    style = MaterialTheme.typography.labelMedium,
                    color = if (item.isOverdue) palette.danger else palette.textSecondary
                )
            }
        }
    }
}

@Composable
private fun StatementDueAgendaRow(
    item: AgendaItem.StatementDueItem,
    onClick: () -> Unit
) {
    val palette = LocalNexusPalette.current
    val st = item.statement

    NexusCard(modifier = Modifier.fillMaxWidth(), elevated = false, onClick = onClick) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
            Icon(
                imageVector = Icons.Outlined.CreditCard,
                contentDescription = "Fatura de cartão",
                tint = if (st.status == StatementStatus.PAID) palette.positive else palette.warning
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Fatura ${item.card.name} (${st.yearMonth})",
                        style = MaterialTheme.typography.titleMedium,
                        color = palette.textPrimary,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = DateTimeUtils.formatBrl(item.totalCents),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = palette.warning
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Vencimento ${DateTimeUtils.formatDate(st.dueAt)} · ${st.status.label}",
                    style = MaterialTheme.typography.labelMedium,
                    color = palette.textSecondary
                )
            }
        }
    }
}

@Composable
private fun ProjectDeadlineAgendaRow(
    item: AgendaItem.ProjectDeadlineItem,
    onClick: () -> Unit
) {
    val palette = LocalNexusPalette.current
    val proj = item.project

    NexusCard(modifier = Modifier.fillMaxWidth(), elevated = false, onClick = onClick) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
            Icon(
                imageVector = Icons.Outlined.FolderOpen,
                contentDescription = "Prazo de projeto",
                tint = palette.textSecondary
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = proj.name, style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Prazo de projeto · ${proj.status.label} · ${DateTimeUtils.formatDate(item.date)}",
                    style = MaterialTheme.typography.labelMedium,
                    color = palette.textSecondary
                )
            }
        }
    }
}
