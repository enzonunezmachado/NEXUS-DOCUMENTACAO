package com.nexus.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nexus.app.core.DateTimeUtils
import com.nexus.app.data.db.entity.CategoryEntity
import com.nexus.app.data.db.entity.CreditCardEntity
import com.nexus.app.data.db.entity.FinancialGoalEntity
import com.nexus.app.data.db.entity.LoanEntity
import com.nexus.app.domain.model.EventCostType
import com.nexus.app.domain.model.EventWithDetails
import com.nexus.app.domain.model.FinancialStatus
import com.nexus.app.domain.model.PaymentMethod
import com.nexus.app.domain.model.ProjectWithProgress
import com.nexus.app.domain.model.RecurrenceFrequency
import com.nexus.app.domain.model.TransactionType
import com.nexus.app.domain.model.TransactionWithDetails
import com.nexus.app.ui.components.ConfirmDangerDialog
import com.nexus.app.ui.components.NexusCard
import com.nexus.app.ui.components.NexusDatePickerDialog
import com.nexus.app.ui.components.ReminderLeadTimeSelector
import com.nexus.app.ui.theme.LocalNexusPalette
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionEditorSheet(
    existing: TransactionWithDetails?,
    initialType: TransactionType,
    initialEventId: String? = null,
    categories: List<CategoryEntity>,
    projects: List<ProjectWithProgress>,
    events: List<EventWithDetails>,
    creditCards: List<CreditCardEntity>,
    onDismiss: () -> Unit,
    onSave: (
        id: String?,
        type: TransactionType,
        description: String,
        amountCents: Long,
        categoryId: String?,
        status: FinancialStatus,
        paymentMethod: PaymentMethod,
        occurredAt: Long,
        dueAt: Long?,
        settledAt: Long?,
        projectId: String?,
        eventId: String?,
        eventCostType: EventCostType?,
        creditCardId: String?,
        notes: String,
        reminderMinutesBefore: Int?
    ) -> Unit,
    onDelete: (String) -> Unit
) {
    val palette = LocalNexusPalette.current
    val tx = existing?.transaction

    var type by remember(tx?.id) { mutableStateOf(tx?.type ?: initialType) }
    var description by remember(tx?.id) { mutableStateOf(tx?.description ?: "") }
    var amountInput by remember(tx?.id) {
        mutableStateOf(tx?.let { DateTimeUtils.centsToInputString(it.amountCents) } ?: "")
    }
    var categoryId by remember(tx?.id) { mutableStateOf(tx?.categoryId) }
    var status by remember(tx?.id) { mutableStateOf(tx?.status ?: FinancialStatus.PENDING) }
    var paymentMethod by remember(tx?.id) { mutableStateOf(tx?.paymentMethod ?: PaymentMethod.PIX) }
    var dueDate by remember(tx?.id) {
        mutableStateOf(tx?.dueAt?.let { DateTimeUtils.epochToLocalDate(it) } ?: DateTimeUtils.todayLocalDate())
    }
    var projectId by remember(tx?.id) { mutableStateOf(tx?.projectId) }
    var eventId by remember(tx?.id) { mutableStateOf(tx?.eventId ?: initialEventId) }
    var eventCostType by remember(tx?.id) {
        mutableStateOf(tx?.eventCostType ?: if (initialEventId != null) EventCostType.OTHER else null)
    }
    var creditCardId by remember(tx?.id) { mutableStateOf(tx?.creditCardId) }
    var notes by remember(tx?.id) { mutableStateOf(tx?.notes ?: "") }

    var showDatePicker by remember { mutableStateOf(false) }
    var showCatMenu by remember { mutableStateOf(false) }
    var showStatusMenu by remember { mutableStateOf(false) }
    var showPayMenu by remember { mutableStateOf(false) }
    var showProjMenu by remember { mutableStateOf(false) }
    var showEventMenu by remember { mutableStateOf(false) }
    var showEventCostMenu by remember { mutableStateOf(false) }
    var showCardMenu by remember { mutableStateOf(false) }
    var showDeleteConfirm by remember { mutableStateOf(false) }
    var validationMessage by remember { mutableStateOf<String?>(null) }
    var reminderMinutesBefore by remember(tx?.id) { mutableStateOf<Int?>(null) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = palette.surface,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (tx == null) "Novo lançamento" else "Editar lançamento",
                    style = MaterialTheme.typography.titleLarge,
                    color = palette.textPrimary
                )
                if (tx != null) {
                    IconButton(onClick = { showDeleteConfirm = true }) {
                        Icon(Icons.Outlined.DeleteOutline, contentDescription = "Excluir", tint = palette.danger)
                    }
                }
            }

            SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                TransactionType.entries.forEachIndexed { idx, t ->
                    SegmentedButton(
                        selected = type == t,
                        onClick = { type = t },
                        shape = SegmentedButtonDefaults.itemShape(idx, TransactionType.entries.size),
                        label = { Text(t.label) }
                    )
                }
            }

            OutlinedTextField(
                value = description,
                onValueChange = { description = it; validationMessage = null },
                label = { Text("Descrição *") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = amountInput,
                onValueChange = { amountInput = it; validationMessage = null },
                label = { Text("Valor (R$) *") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    val catName = categories.firstOrNull { it.id == categoryId }?.name ?: "Sem categoria"
                    OutlinedButton(onClick = { showCatMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(catName, color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(expanded = showCatMenu, onDismissRequest = { showCatMenu = false }) {
                        DropdownMenuItem(text = { Text("Sem categoria") }, onClick = {
                            categoryId = null; showCatMenu = false
                        })
                        categories.forEach { c ->
                            DropdownMenuItem(text = { Text(c.name) }, onClick = {
                                categoryId = c.id; showCatMenu = false
                            })
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(onClick = { showStatusMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(status.label, color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(expanded = showStatusMenu, onDismissRequest = { showStatusMenu = false }) {
                        FinancialStatus.entries.forEach { st ->
                            DropdownMenuItem(text = { Text(st.label) }, onClick = {
                                status = st; showStatusMenu = false
                            })
                        }
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(onClick = { showPayMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(paymentMethod.label, color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(expanded = showPayMenu, onDismissRequest = { showPayMenu = false }) {
                        PaymentMethod.entries.forEach { pm ->
                            DropdownMenuItem(text = { Text(pm.label) }, onClick = {
                                paymentMethod = pm; showPayMenu = false
                            })
                        }
                    }
                }

                OutlinedButton(onClick = { showDatePicker = true }, modifier = Modifier.weight(1f)) {
                    Text("Data: ${DateTimeUtils.formatDate(dueDate)}", color = palette.textPrimary, maxLines = 1)
                }
            }

            if (type == TransactionType.EXPENSE && paymentMethod == PaymentMethod.CREDIT_CARD) {
                Box {
                    val cardName = creditCards.firstOrNull { it.id == creditCardId }?.name ?: "Selecionar cartão"
                    OutlinedButton(onClick = { showCardMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(cardName, color = palette.textPrimary)
                    }
                    DropdownMenu(expanded = showCardMenu, onDismissRequest = { showCardMenu = false }) {
                        DropdownMenuItem(text = { Text("Nenhum") }, onClick = {
                            creditCardId = null; showCardMenu = false
                        })
                        creditCards.forEach { card ->
                            DropdownMenuItem(text = { Text(card.name) }, onClick = {
                                creditCardId = card.id; showCardMenu = false
                            })
                        }
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    val projName = projects.firstOrNull { it.project.id == projectId }?.project?.name ?: "Sem projeto"
                    OutlinedButton(onClick = { showProjMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(projName, color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(expanded = showProjMenu, onDismissRequest = { showProjMenu = false }) {
                        DropdownMenuItem(text = { Text("Sem projeto") }, onClick = {
                            projectId = null; showProjMenu = false
                        })
                        projects.forEach { p ->
                            DropdownMenuItem(text = { Text(p.project.name) }, onClick = {
                                projectId = p.project.id; showProjMenu = false
                            })
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    val evName = events.firstOrNull { it.event.id == eventId }?.event?.name ?: "Sem evento"
                    OutlinedButton(onClick = { showEventMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(evName, color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(expanded = showEventMenu, onDismissRequest = { showEventMenu = false }) {
                        DropdownMenuItem(text = { Text("Sem evento") }, onClick = {
                            eventId = null; eventCostType = null; showEventMenu = false
                        })
                        events.forEach { ev ->
                            DropdownMenuItem(text = { Text(ev.event.name) }, onClick = {
                                eventId = ev.event.id
                                if (eventCostType == null) eventCostType = EventCostType.OTHER
                                showEventMenu = false
                            })
                        }
                    }
                }
            }

            if (eventId != null && type == TransactionType.EXPENSE) {
                Box {
                    OutlinedButton(onClick = { showEventCostMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text("Tipo de custo no evento: ${eventCostType?.label ?: "Outros"}", color = palette.textPrimary)
                    }
                    DropdownMenu(expanded = showEventCostMenu, onDismissRequest = { showEventCostMenu = false }) {
                        EventCostType.entries.forEach { ct ->
                            DropdownMenuItem(text = { Text(ct.label) }, onClick = {
                                eventCostType = ct; showEventCostMenu = false
                            })
                        }
                    }
                }
            }

            if (status != FinancialStatus.SETTLED && status != FinancialStatus.CANCELLED) {
                NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true) {
                    ReminderLeadTimeSelector(
                        selectedMinutes = reminderMinutesBefore,
                        onSelectMinutes = { reminderMinutesBefore = it }
                    )
                }
            }

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Observações") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2
            )

            validationMessage?.let {
                Text(text = it, color = palette.danger, style = MaterialTheme.typography.bodyMedium)
            }

            Button(
                onClick = {
                    val cents = DateTimeUtils.parseBrlToCents(amountInput)
                    if (description.isBlank()) {
                        validationMessage = "Informe a descrição."
                        return@Button
                    }
                    if (cents == null || cents <= 0L) {
                        validationMessage = "Informe um valor maior que zero."
                        return@Button
                    }
                    val dateEpoch = DateTimeUtils.localDateTimeToEpoch(dueDate, LocalTime.of(12, 0))
                    onSave(
                        tx?.id,
                        type,
                        description,
                        cents,
                        categoryId,
                        status,
                        paymentMethod,
                        tx?.occurredAt ?: dateEpoch,
                        dateEpoch,
                        if (status == FinancialStatus.SETTLED) (tx?.settledAt ?: DateTimeUtils.nowEpochMillis()) else null,
                        projectId,
                        eventId,
                        eventCostType,
                        creditCardId,
                        notes,
                        reminderMinutesBefore
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = palette.highlight, contentColor = palette.background)
            ) {
                Text("Salvar lançamento")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    if (showDatePicker) {
        NexusDatePickerDialog(
            initialDate = dueDate,
            onDateSelected = { dueDate = it },
            onDismiss = { showDatePicker = false }
        )
    }

    if (showDeleteConfirm && tx != null) {
        ConfirmDangerDialog(
            title = "Excluir lançamento?",
            message = "Remover \"${tx.description}\" (${DateTimeUtils.formatBrl(tx.amountCents)})?",
            onConfirm = {
                showDeleteConfirm = false
                onDelete(tx.id)
            },
            onDismiss = { showDeleteConfirm = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditCardEditorSheet(
    existing: CreditCardEntity?,
    onDismiss: () -> Unit,
    onSave: (
        id: String?,
        name: String,
        institution: String,
        totalLimitCents: Long,
        availableLimitCents: Long,
        closingDay: Int,
        dueDay: Int,
        notes: String
    ) -> Unit,
    onDelete: (String) -> Unit
) {
    val palette = LocalNexusPalette.current
    var name by remember(existing?.id) { mutableStateOf(existing?.name ?: "") }
    var institution by remember(existing?.id) { mutableStateOf(existing?.institution ?: "") }
    var totalLimit by remember(existing?.id) {
        mutableStateOf(existing?.let { DateTimeUtils.centsToInputString(it.totalLimitCents) } ?: "")
    }
    var availableLimit by remember(existing?.id) {
        mutableStateOf(existing?.let { DateTimeUtils.centsToInputString(it.availableLimitCents) } ?: "")
    }
    var closingDay by remember(existing?.id) { mutableStateOf((existing?.closingDay ?: 10).toString()) }
    var dueDay by remember(existing?.id) { mutableStateOf((existing?.dueDay ?: 17).toString()) }
    var notes by remember(existing?.id) { mutableStateOf(existing?.notes ?: "") }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = palette.surface,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(if (existing == null) "Novo cartão" else "Editar cartão", style = MaterialTheme.typography.titleLarge, color = palette.textPrimary)
                if (existing != null) {
                    IconButton(onClick = { onDelete(existing.id) }) {
                        Icon(Icons.Outlined.DeleteOutline, contentDescription = "Excluir", tint = palette.danger)
                    }
                }
            }
            OutlinedTextField(value = name, onValueChange = { name = it; errorMsg = null }, label = { Text("Nome do cartão *") }, singleLine = true, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = institution, onValueChange = { institution = it }, label = { Text("Instituição") }, singleLine = true, modifier = Modifier.fillMaxWidth())
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = totalLimit, onValueChange = { totalLimit = it },
                    label = { Text("Limite total (R$)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true, modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = availableLimit, onValueChange = { availableLimit = it },
                    label = { Text("Disponível (R$)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true, modifier = Modifier.weight(1f)
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = closingDay, onValueChange = { closingDay = it.filter { c -> c.isDigit() } },
                    label = { Text("Dia fechamento") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true, modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = dueDay, onValueChange = { dueDay = it.filter { c -> c.isDigit() } },
                    label = { Text("Dia vencimento") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true, modifier = Modifier.weight(1f)
                )
            }
            OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Observações") }, modifier = Modifier.fillMaxWidth())
            errorMsg?.let { Text(it, color = palette.danger) }
            Button(
                onClick = {
                    if (name.isBlank()) {
                        errorMsg = "Informe o nome do cartão."
                        return@Button
                    }
                    onSave(
                        existing?.id,
                        name,
                        institution,
                        DateTimeUtils.parseBrlToCents(totalLimit) ?: 0L,
                        DateTimeUtils.parseBrlToCents(availableLimit) ?: 0L,
                        closingDay.toIntOrNull()?.coerceIn(1, 31) ?: 10,
                        dueDay.toIntOrNull()?.coerceIn(1, 31) ?: 17,
                        notes
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = palette.highlight, contentColor = palette.background)
            ) {
                Text("Salvar cartão")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstallmentPurchaseEditorSheet(
    creditCards: List<CreditCardEntity>,
    categories: List<CategoryEntity>,
    onDismiss: () -> Unit,
    onSave: (
        description: String,
        totalAmountCents: Long,
        installmentsCount: Int,
        firstDueDate: LocalDate,
        creditCardId: String?,
        categoryId: String?,
        notes: String
    ) -> Unit
) {
    val palette = LocalNexusPalette.current
    var description by remember { mutableStateOf("") }
    var totalInput by remember { mutableStateOf("") }
    var countInput by remember { mutableStateOf("2") }
    var firstDueDate by remember { mutableStateOf(DateTimeUtils.todayLocalDate()) }
    var creditCardId by remember { mutableStateOf<String?>(null) }
    var categoryId by remember { mutableStateOf<String?>(null) }
    var notes by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var showCardMenu by remember { mutableStateOf(false) }
    var showCatMenu by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    ModalBottomSheet(onDismissRequest = onDismiss, containerColor = palette.surface, sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Nova compra parcelada", style = MaterialTheme.typography.titleLarge, color = palette.textPrimary)
            OutlinedTextField(value = description, onValueChange = { description = it; errorMsg = null }, label = { Text("Descrição *") }, singleLine = true, modifier = Modifier.fillMaxWidth())
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = totalInput, onValueChange = { totalInput = it; errorMsg = null },
                    label = { Text("Valor total (R$) *") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true, modifier = Modifier.weight(1.4f)
                )
                OutlinedTextField(
                    value = countInput, onValueChange = { countInput = it.filter { c -> c.isDigit() } },
                    label = { Text("Parcelas") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true, modifier = Modifier.weight(0.8f)
                )
            }
            OutlinedButton(onClick = { showDatePicker = true }, modifier = Modifier.fillMaxWidth()) {
                Text("1ª parcela: ${DateTimeUtils.formatDate(firstDueDate)}", color = palette.textPrimary)
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    val cardName = creditCards.firstOrNull { it.id == creditCardId }?.name ?: "Sem cartão"
                    OutlinedButton(onClick = { showCardMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(cardName, color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(expanded = showCardMenu, onDismissRequest = { showCardMenu = false }) {
                        DropdownMenuItem(text = { Text("Sem cartão") }, onClick = { creditCardId = null; showCardMenu = false })
                        creditCards.forEach { card ->
                            DropdownMenuItem(text = { Text(card.name) }, onClick = { creditCardId = card.id; showCardMenu = false })
                        }
                    }
                }
                Box(modifier = Modifier.weight(1f)) {
                    val catName = categories.firstOrNull { it.id == categoryId }?.name ?: "Sem categoria"
                    OutlinedButton(onClick = { showCatMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(catName, color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(expanded = showCatMenu, onDismissRequest = { showCatMenu = false }) {
                        DropdownMenuItem(text = { Text("Sem categoria") }, onClick = { categoryId = null; showCatMenu = false })
                        categories.forEach { c ->
                            DropdownMenuItem(text = { Text(c.name) }, onClick = { categoryId = c.id; showCatMenu = false })
                        }
                    }
                }
            }
            OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Observações") }, modifier = Modifier.fillMaxWidth())
            errorMsg?.let { Text(it, color = palette.danger) }
            Button(
                onClick = {
                    val cents = DateTimeUtils.parseBrlToCents(totalInput)
                    val count = countInput.toIntOrNull() ?: 2
                    if (description.isBlank() || cents == null || cents <= 0L) {
                        errorMsg = "Informe descrição e valor total válido."
                        return@Button
                    }
                    onSave(description, cents, count.coerceIn(2, 120), firstDueDate, creditCardId, categoryId, notes)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = palette.highlight, contentColor = palette.background)
            ) {
                Text("Gerar parcelas")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    if (showDatePicker) {
        NexusDatePickerDialog(initialDate = firstDueDate, onDateSelected = { firstDueDate = it }, onDismiss = { showDatePicker = false })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecurringRuleEditorSheet(
    categories: List<CategoryEntity>,
    creditCards: List<CreditCardEntity>,
    onDismiss: () -> Unit,
    onSave: (
        title: String,
        type: TransactionType,
        amountCents: Long,
        categoryId: String?,
        paymentMethod: PaymentMethod,
        creditCardId: String?,
        frequency: RecurrenceFrequency,
        customIntervalDays: Int,
        startDate: LocalDate,
        maxOccurrences: Int?,
        notes: String
    ) -> Unit
) {
    val palette = LocalNexusPalette.current
    var title by remember { mutableStateOf("") }
    var type by remember { mutableStateOf(TransactionType.EXPENSE) }
    var amountInput by remember { mutableStateOf("") }
    var frequency by remember { mutableStateOf(RecurrenceFrequency.MONTHLY) }
    var customDaysInput by remember { mutableStateOf("30") }
    var maxOccInput by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf(DateTimeUtils.todayLocalDate()) }
    var paymentMethod by remember { mutableStateOf(PaymentMethod.PIX) }
    var categoryId by remember { mutableStateOf<String?>(null) }
    var creditCardId by remember { mutableStateOf<String?>(null) }
    var notes by remember { mutableStateOf("") }

    var showFreqMenu by remember { mutableStateOf(false) }
    var showCatMenu by remember { mutableStateOf(false) }
    var showPayMenu by remember { mutableStateOf(false) }
    var showCardMenu by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    ModalBottomSheet(onDismissRequest = onDismiss, containerColor = palette.surface, sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Nova conta ou receita recorrente", style = MaterialTheme.typography.titleLarge, color = palette.textPrimary)
            SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                TransactionType.entries.forEachIndexed { idx, t ->
                    SegmentedButton(
                        selected = type == t,
                        onClick = { type = t },
                        shape = SegmentedButtonDefaults.itemShape(idx, TransactionType.entries.size),
                        label = { Text(t.label) }
                    )
                }
            }
            OutlinedTextField(value = title, onValueChange = { title = it; errorMsg = null }, label = { Text("Título *") }, singleLine = true, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(
                value = amountInput, onValueChange = { amountInput = it; errorMsg = null },
                label = { Text("Valor (R$) *") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true, modifier = Modifier.fillMaxWidth()
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(onClick = { showFreqMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(frequency.label, color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(expanded = showFreqMenu, onDismissRequest = { showFreqMenu = false }) {
                        RecurrenceFrequency.entries.forEach { freq ->
                            DropdownMenuItem(text = { Text(freq.label) }, onClick = { frequency = freq; showFreqMenu = false })
                        }
                    }
                }
                OutlinedButton(onClick = { showDatePicker = true }, modifier = Modifier.weight(1f)) {
                    Text("Início: ${DateTimeUtils.formatDate(startDate)}", color = palette.textPrimary, maxLines = 1)
                }
            }
            if (frequency == RecurrenceFrequency.CUSTOM_DAYS) {
                OutlinedTextField(
                    value = customDaysInput, onValueChange = { customDaysInput = it.filter { c -> c.isDigit() } },
                    label = { Text("Intervalo em dias") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true, modifier = Modifier.fillMaxWidth()
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    val catName = categories.firstOrNull { it.id == categoryId }?.name ?: "Sem categoria"
                    OutlinedButton(onClick = { showCatMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(catName, color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(expanded = showCatMenu, onDismissRequest = { showCatMenu = false }) {
                        DropdownMenuItem(text = { Text("Sem categoria") }, onClick = { categoryId = null; showCatMenu = false })
                        categories.forEach { c ->
                            DropdownMenuItem(text = { Text(c.name) }, onClick = { categoryId = c.id; showCatMenu = false })
                        }
                    }
                }
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(onClick = { showPayMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(paymentMethod.label, color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(expanded = showPayMenu, onDismissRequest = { showPayMenu = false }) {
                        PaymentMethod.entries.forEach { pm ->
                            DropdownMenuItem(text = { Text(pm.label) }, onClick = { paymentMethod = pm; showPayMenu = false })
                        }
                    }
                }
            }
            if (paymentMethod == PaymentMethod.CREDIT_CARD) {
                Box {
                    val cardName = creditCards.firstOrNull { it.id == creditCardId }?.name ?: "Selecionar cartão"
                    OutlinedButton(onClick = { showCardMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(cardName, color = palette.textPrimary)
                    }
                    DropdownMenu(expanded = showCardMenu, onDismissRequest = { showCardMenu = false }) {
                        creditCards.forEach { card ->
                            DropdownMenuItem(text = { Text(card.name) }, onClick = { creditCardId = card.id; showCardMenu = false })
                        }
                    }
                }
            }
            OutlinedTextField(
                value = maxOccInput,
                onValueChange = { maxOccInput = it.filter { c -> c.isDigit() } },
                label = { Text("Quantidade de ocorrências (opcional)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true, modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Observações") }, modifier = Modifier.fillMaxWidth())
            errorMsg?.let { Text(it, color = palette.danger) }
            Button(
                onClick = {
                    val cents = DateTimeUtils.parseBrlToCents(amountInput)
                    if (title.isBlank() || cents == null || cents <= 0L) {
                        errorMsg = "Informe título e valor válido."
                        return@Button
                    }
                    onSave(
                        title, type, cents, categoryId, paymentMethod, creditCardId,
                        frequency, customDaysInput.toIntOrNull() ?: 30, startDate,
                        maxOccInput.toIntOrNull(), notes
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = palette.highlight, contentColor = palette.background)
            ) {
                Text("Salvar recorrência")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    if (showDatePicker) {
        NexusDatePickerDialog(initialDate = startDate, onDateSelected = { startDate = it }, onDismiss = { showDatePicker = false })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanEditorSheet(
    existing: LoanEntity?,
    onDismiss: () -> Unit,
    onSave: (
        id: String?,
        creditor: String,
        originalAmountCents: Long,
        contractedAmountCents: Long,
        installmentsTotal: Int,
        installmentAmountCents: Long,
        paidInstallments: Int,
        nextDueAt: Long?,
        remainingBalanceCents: Long,
        finalDateAt: Long?,
        notes: String
    ) -> Unit
) {
    val palette = LocalNexusPalette.current
    var creditor by remember(existing?.id) { mutableStateOf(existing?.creditor ?: "") }
    var originalInput by remember(existing?.id) { mutableStateOf(existing?.let { DateTimeUtils.centsToInputString(it.originalAmountCents) } ?: "") }
    var contractedInput by remember(existing?.id) { mutableStateOf(existing?.let { DateTimeUtils.centsToInputString(it.contractedAmountCents) } ?: "") }
    var totalInstInput by remember(existing?.id) { mutableStateOf((existing?.installmentsTotal ?: 12).toString()) }
    var instAmountInput by remember(existing?.id) { mutableStateOf(existing?.let { DateTimeUtils.centsToInputString(it.installmentAmountCents) } ?: "") }
    var paidInstInput by remember(existing?.id) { mutableStateOf((existing?.paidInstallments ?: 0).toString()) }
    var remainingInput by remember(existing?.id) { mutableStateOf(existing?.let { DateTimeUtils.centsToInputString(it.remainingBalanceCents) } ?: "") }
    var nextDueDate by remember(existing?.id) { mutableStateOf(existing?.nextDueAt?.let { DateTimeUtils.epochToLocalDate(it) }) }
    var notes by remember(existing?.id) { mutableStateOf(existing?.notes ?: "") }
    var showDatePicker by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    ModalBottomSheet(onDismissRequest = onDismiss, containerColor = palette.surface, sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(if (existing == null) "Novo empréstimo / dívida" else "Editar empréstimo", style = MaterialTheme.typography.titleLarge, color = palette.textPrimary)
            OutlinedTextField(value = creditor, onValueChange = { creditor = it; errorMsg = null }, label = { Text("Credor *") }, singleLine = true, modifier = Modifier.fillMaxWidth())
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = originalInput, onValueChange = { originalInput = it }, label = { Text("Valor original") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), singleLine = true, modifier = Modifier.weight(1f))
                OutlinedTextField(value = contractedInput, onValueChange = { contractedInput = it }, label = { Text("Contratado") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), singleLine = true, modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = totalInstInput, onValueChange = { totalInstInput = it.filter { c -> c.isDigit() } }, label = { Text("Total parcelas") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), singleLine = true, modifier = Modifier.weight(1f))
                OutlinedTextField(value = paidInstInput, onValueChange = { paidInstInput = it.filter { c -> c.isDigit() } }, label = { Text("Pagas") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), singleLine = true, modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = instAmountInput, onValueChange = { instAmountInput = it }, label = { Text("Valor parcela") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), singleLine = true, modifier = Modifier.weight(1f))
                OutlinedTextField(value = remainingInput, onValueChange = { remainingInput = it }, label = { Text("Saldo devedor") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), singleLine = true, modifier = Modifier.weight(1f))
            }
            OutlinedButton(onClick = { showDatePicker = true }, modifier = Modifier.fillMaxWidth()) {
                Text(nextDueDate?.let { "Próximo vencimento: ${DateTimeUtils.formatDate(it)}" } ?: "Definir próximo vencimento", color = palette.textPrimary)
            }
            OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Observações") }, modifier = Modifier.fillMaxWidth())
            errorMsg?.let { Text(it, color = palette.danger) }
            Button(
                onClick = {
                    if (creditor.isBlank()) {
                        errorMsg = "Informe o credor."
                        return@Button
                    }
                    onSave(
                        existing?.id,
                        creditor,
                        DateTimeUtils.parseBrlToCents(originalInput) ?: 0L,
                        DateTimeUtils.parseBrlToCents(contractedInput) ?: 0L,
                        totalInstInput.toIntOrNull()?.coerceAtLeast(1) ?: 1,
                        DateTimeUtils.parseBrlToCents(instAmountInput) ?: 0L,
                        paidInstInput.toIntOrNull()?.coerceAtLeast(0) ?: 0,
                        nextDueDate?.let { DateTimeUtils.localDateTimeToEpoch(it, LocalTime.of(12, 0)) },
                        DateTimeUtils.parseBrlToCents(remainingInput) ?: 0L,
                        null,
                        notes
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = palette.highlight, contentColor = palette.background)
            ) {
                Text("Salvar empréstimo")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    if (showDatePicker) {
        NexusDatePickerDialog(initialDate = nextDueDate ?: DateTimeUtils.todayLocalDate(), onDateSelected = { nextDueDate = it }, onDismiss = { showDatePicker = false })
    }
}

@Composable
fun LoanPaymentDialog(
    loan: LoanEntity,
    onDismiss: () -> Unit,
    onConfirm: (newRemainingBalanceCents: Long) -> Unit
) {
    val palette = LocalNexusPalette.current
    val suggested = (loan.remainingBalanceCents - loan.installmentAmountCents).coerceAtLeast(0L)
    var remainingInput by remember { mutableStateOf(DateTimeUtils.centsToInputString(suggested)) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = palette.surfaceElevated,
        title = { Text("Registrar parcela paga", color = palette.textPrimary) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "${loan.creditor} (${loan.paidInstallments + 1}/${loan.installmentsTotal}). Informe o novo saldo devedor atualizado:",
                    color = palette.textSecondary
                )
                OutlinedTextField(
                    value = remainingInput,
                    onValueChange = { remainingInput = it },
                    label = { Text("Saldo devedor informado (R$)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(DateTimeUtils.parseBrlToCents(remainingInput) ?: suggested) }) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}

@Composable
fun DeclaredBalanceDialog(
    currentBalanceCents: Long,
    onDismiss: () -> Unit,
    onSave: (Long) -> Unit
) {
    val palette = LocalNexusPalette.current
    var input by remember { mutableStateOf(DateTimeUtils.centsToInputString(currentBalanceCents)) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = palette.surfaceElevated,
        title = { Text("Saldo atual informado", color = palette.textPrimary) },
        text = {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Saldo (R$)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )
        },
        confirmButton = {
            Button(onClick = { onSave(DateTimeUtils.parseBrlToCents(input) ?: 0L) }) {
                Text("Salvar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}

@Composable
fun BudgetEditorDialog(
    categories: List<CategoryEntity>,
    onDismiss: () -> Unit,
    onSave: (categoryId: String, limitCents: Long) -> Unit
) {
    val palette = LocalNexusPalette.current
    var selectedCatId by remember { mutableStateOf(categories.firstOrNull()?.id) }
    var limitInput by remember { mutableStateOf("") }
    var showCatMenu by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = palette.surfaceElevated,
        title = { Text("Definir orçamento mensal", color = palette.textPrimary) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Box {
                    val catName = categories.firstOrNull { it.id == selectedCatId }?.name ?: "Selecionar categoria"
                    OutlinedButton(onClick = { showCatMenu = true }, modifier = Modifier.fillMaxWidth()) {
                        Text(catName, color = palette.textPrimary)
                    }
                    DropdownMenu(expanded = showCatMenu, onDismissRequest = { showCatMenu = false }) {
                        categories.forEach { cat ->
                            DropdownMenuItem(text = { Text(cat.name) }, onClick = {
                                selectedCatId = cat.id; showCatMenu = false
                            })
                        }
                    }
                }
                OutlinedTextField(
                    value = limitInput,
                    onValueChange = { limitInput = it },
                    label = { Text("Limite mensal (R$)") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val catId = selectedCatId ?: return@Button
                val cents = DateTimeUtils.parseBrlToCents(limitInput) ?: return@Button
                if (cents > 0L) onSave(catId, cents)
            }) { Text("Salvar") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}

@Composable
fun FinancialGoalEditorDialog(
    existing: FinancialGoalEntity?,
    onDismiss: () -> Unit,
    onSave: (id: String?, name: String, targetCents: Long, currentCents: Long, notes: String) -> Unit
) {
    val palette = LocalNexusPalette.current
    var name by remember(existing?.id) { mutableStateOf(existing?.name ?: "") }
    var targetInput by remember(existing?.id) { mutableStateOf(existing?.let { DateTimeUtils.centsToInputString(it.targetAmountCents) } ?: "") }
    var currentInput by remember(existing?.id) { mutableStateOf(existing?.let { DateTimeUtils.centsToInputString(it.currentAmountCents) } ?: "") }
    var notes by remember(existing?.id) { mutableStateOf(existing?.notes ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = palette.surfaceElevated,
        title = { Text(if (existing == null) "Nova meta financeira" else "Editar meta", color = palette.textPrimary) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nome *") }, singleLine = true)
                OutlinedTextField(value = targetInput, onValueChange = { targetInput = it }, label = { Text("Valor desejado (R$) *") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), singleLine = true)
                OutlinedTextField(value = currentInput, onValueChange = { currentInput = it }, label = { Text("Valor atual (R$)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), singleLine = true)
                OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Observações") })
            }
        },
        confirmButton = {
            Button(onClick = {
                val target = DateTimeUtils.parseBrlToCents(targetInput) ?: return@Button
                val current = DateTimeUtils.parseBrlToCents(currentInput) ?: 0L
                if (name.isNotBlank() && target > 0L) {
                    onSave(existing?.id, name, target, current, notes)
                }
            }) { Text("Salvar") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar") } }
    )
}
