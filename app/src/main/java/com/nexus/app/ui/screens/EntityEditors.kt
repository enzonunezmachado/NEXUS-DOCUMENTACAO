package com.nexus.app.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.OpenInNew
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.nexus.app.core.DateTimeUtils
import com.nexus.app.data.db.entity.AppointmentEntity
import com.nexus.app.data.db.entity.CategoryEntity
import com.nexus.app.data.db.entity.SubtaskEntity
import com.nexus.app.domain.model.EventWithDetails
import com.nexus.app.domain.model.PersonalGoalWithDetails
import com.nexus.app.domain.model.ProjectStatus
import com.nexus.app.domain.model.ProjectWithProgress
import com.nexus.app.domain.model.TaskPriority
import com.nexus.app.domain.model.TaskStatus
import com.nexus.app.domain.model.TaskWithDetails
import com.nexus.app.ui.components.ConfirmDangerDialog
import com.nexus.app.ui.components.NexusCard
import com.nexus.app.ui.components.NexusDatePickerDialog
import com.nexus.app.ui.components.NexusTimePickerDialog
import com.nexus.app.ui.components.ReminderLeadTimeSelector
import com.nexus.app.ui.theme.LocalNexusPalette
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickAddChooserSheet(
    onDismiss: () -> Unit,
    onSelectNewTask: () -> Unit,
    onSelectNewAppointment: () -> Unit,
    onSelectNewProject: () -> Unit,
    onSelectNewEvent: () -> Unit,
    onSelectNewTransaction: () -> Unit
) {
    val palette = LocalNexusPalette.current
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
            Text(
                text = "Cadastro rápido",
                style = MaterialTheme.typography.titleLarge,
                color = palette.textPrimary
            )
            Text(
                text = "Escolha o que deseja registrar no banco local:",
                style = MaterialTheme.typography.bodyMedium,
                color = palette.textSecondary
            )

            QuickAddOptionCard(
                icon = Icons.Outlined.TaskAlt,
                title = "Nova tarefa",
                subtitle = "Título obrigatório; prazo, projeto, evento e subtarefas opcionais.",
                onClick = onSelectNewTask
            )
            QuickAddOptionCard(
                icon = Icons.Outlined.CalendarMonth,
                title = "Novo compromisso",
                subtitle = "Horário, local, link externo e detecção automática de conflito.",
                onClick = onSelectNewAppointment
            )
            QuickAddOptionCard(
                icon = Icons.Outlined.AccountBalanceWallet,
                title = "Novo lançamento financeiro",
                subtitle = "Receita ou despesa vinculável a cartão, projeto ou evento.",
                onClick = onSelectNewTransaction
            )
            QuickAddOptionCard(
                icon = Icons.Outlined.ConfirmationNumber,
                title = "Novo evento",
                subtitle = "Data, local, ingresso integrado ao financeiro e tarefas vinculadas.",
                onClick = onSelectNewEvent
            )
            QuickAddOptionCard(
                icon = Icons.Outlined.FolderOpen,
                title = "Novo projeto",
                subtitle = "Agrupe tarefas com progresso calculado automaticamente.",
                onClick = onSelectNewProject
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun QuickAddOptionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    val palette = LocalNexusPalette.current
    NexusCard(
        modifier = Modifier.fillMaxWidth(),
        elevated = true,
        onClick = onClick
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null, tint = palette.highlight)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                Text(text = subtitle, style = MaterialTheme.typography.bodyMedium, color = palette.textSecondary)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskEditorSheet(
    existingTask: TaskWithDetails?,
    initialEventId: String? = null,
    projects: List<ProjectWithProgress>,
    categories: List<CategoryEntity>,
    events: List<EventWithDetails>,
    goals: List<PersonalGoalWithDetails> = emptyList(),
    onDismiss: () -> Unit,
    onSave: (
        id: String?,
        title: String,
        description: String,
        projectId: String?,
        categoryId: String?,
        eventId: String?,
        goalId: String?,
        priority: TaskPriority,
        status: TaskStatus,
        dueAt: Long?,
        hasDueTime: Boolean,
        notes: String,
        subtaskDrafts: List<Triple<String?, String, Boolean>>,
        reminderMinutesBefore: Int?
    ) -> Unit,
    onToggleComplete: (String) -> Unit,
    onToggleSubtask: (SubtaskEntity) -> Unit,
    onOpenEvent: (String) -> Unit,
    onDelete: (String) -> Unit
) {
    val palette = LocalNexusPalette.current
    val task = existingTask?.task

    var title by remember(task?.id) { mutableStateOf(task?.title ?: "") }
    var description by remember(task?.id) { mutableStateOf(task?.description ?: "") }
    var notes by remember(task?.id) { mutableStateOf(task?.notes ?: "") }
    var projectId by remember(task?.id) { mutableStateOf(task?.projectId) }
    var categoryId by remember(task?.id) { mutableStateOf(task?.categoryId) }
    var eventId by remember(task?.id) { mutableStateOf(task?.eventId ?: initialEventId) }
    var goalId by remember(task?.id) { mutableStateOf(task?.goalId) }
    var priority by remember(task?.id) { mutableStateOf(task?.priority ?: TaskPriority.NORMAL) }
    var status by remember(task?.id) { mutableStateOf(task?.status ?: TaskStatus.NOT_STARTED) }

    var dueDate by remember(task?.id) {
        mutableStateOf(task?.dueAt?.let { DateTimeUtils.epochToLocalDate(it) })
    }
    var hasDueTime by remember(task?.id) { mutableStateOf(task?.hasDueTime ?: false) }
    var dueTime by remember(task?.id) {
        mutableStateOf(
            if (task?.dueAt != null && task.hasDueTime) {
                DateTimeUtils.epochToLocalTime(task.dueAt)
            } else {
                LocalTime.of(9, 0)
            }
        )
    }

    val draftSubtasks = remember(task?.id) {
        mutableStateListOf<Triple<String?, String, Boolean>>().apply {
            existingTask?.subtasks?.forEach { add(Triple(it.id, it.title, it.isCompleted)) }
        }
    }
    var newSubtaskText by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }
    var showProjectDropdown by remember { mutableStateOf(false) }
    var showCategoryDropdown by remember { mutableStateOf(false) }
    var showEventDropdown by remember { mutableStateOf(false) }
    var showGoalDropdown by remember { mutableStateOf(false) }
    var showPriorityDropdown by remember { mutableStateOf(false) }
    var showStatusDropdown by remember { mutableStateOf(false) }
    var showDeleteConfirm by remember { mutableStateOf(false) }
    var titleError by remember { mutableStateOf(false) }
    var reminderMinutesBefore by remember(task?.id) { mutableStateOf<Int?>(null) }

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
                    text = if (task == null) "Nova tarefa" else "Detalhes da tarefa",
                    style = MaterialTheme.typography.titleLarge,
                    color = palette.textPrimary
                )
                if (task != null) {
                    IconButton(onClick = { showDeleteConfirm = true }) {
                        Icon(
                            imageVector = Icons.Outlined.DeleteOutline,
                            contentDescription = "Excluir tarefa",
                            tint = palette.danger
                        )
                    }
                }
            }

            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    if (it.isNotBlank()) titleError = false
                },
                label = { Text("Título *") },
                isError = titleError,
                supportingText = if (titleError) {
                    { Text("Informe o título da tarefa.") }
                } else null,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                maxLines = 4
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(
                        onClick = { showPriorityDropdown = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Prioridade: ${priority.label}", color = palette.textPrimary)
                    }
                    DropdownMenu(
                        expanded = showPriorityDropdown,
                        onDismissRequest = { showPriorityDropdown = false }
                    ) {
                        TaskPriority.entries.forEach { p ->
                            DropdownMenuItem(
                                text = { Text(p.label) },
                                onClick = {
                                    priority = p
                                    showPriorityDropdown = false
                                }
                            )
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(
                        onClick = { showStatusDropdown = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(status.label, color = palette.textPrimary)
                    }
                    DropdownMenu(
                        expanded = showStatusDropdown,
                        onDismissRequest = { showStatusDropdown = false }
                    ) {
                        TaskStatus.entries.forEach { s ->
                            DropdownMenuItem(
                                text = { Text(s.label) },
                                onClick = {
                                    status = s
                                    showStatusDropdown = false
                                }
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    val projName = projects.firstOrNull { it.project.id == projectId }?.project?.name ?: "Sem projeto"
                    OutlinedButton(
                        onClick = { showProjectDropdown = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(projName, color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(
                        expanded = showProjectDropdown,
                        onDismissRequest = { showProjectDropdown = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Sem projeto") },
                            onClick = {
                                projectId = null
                                showProjectDropdown = false
                            }
                        )
                        projects.forEach { pw ->
                            DropdownMenuItem(
                                text = { Text(pw.project.name) },
                                onClick = {
                                    projectId = pw.project.id
                                    showProjectDropdown = false
                                }
                            )
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    val catName = categories.firstOrNull { it.id == categoryId }?.name ?: "Sem categoria"
                    OutlinedButton(
                        onClick = { showCategoryDropdown = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(catName, color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(
                        expanded = showCategoryDropdown,
                        onDismissRequest = { showCategoryDropdown = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Sem categoria") },
                            onClick = {
                                categoryId = null
                                showCategoryDropdown = false
                            }
                        )
                        categories.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text(cat.name) },
                                onClick = {
                                    categoryId = cat.id
                                    showCategoryDropdown = false
                                }
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    val evName = events.firstOrNull { it.event.id == eventId }?.event?.name ?: "Sem evento"
                    OutlinedButton(
                        onClick = { showEventDropdown = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Evento: $evName", color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(
                        expanded = showEventDropdown,
                        onDismissRequest = { showEventDropdown = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Sem evento") },
                            onClick = {
                                eventId = null
                                showEventDropdown = false
                            }
                        )
                        events.forEach { ew ->
                            DropdownMenuItem(
                                text = { Text(ew.event.name) },
                                onClick = {
                                    eventId = ew.event.id
                                    showEventDropdown = false
                                }
                            )
                        }
                    }
                }
                eventId?.let { linkedEventId ->
                    TextButton(onClick = { onOpenEvent(linkedEventId) }) {
                        Text("Abrir evento", color = palette.info)
                    }
                }
            }

            if (goals.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    val goalTitle = goals.firstOrNull { it.goal.id == goalId }?.goal?.title ?: "Sem objetivo pessoal"
                    OutlinedButton(
                        onClick = { showGoalDropdown = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Objetivo: $goalTitle", color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(
                        expanded = showGoalDropdown,
                        onDismissRequest = { showGoalDropdown = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Sem objetivo pessoal") },
                            onClick = {
                                goalId = null
                                showGoalDropdown = false
                            }
                        )
                        goals.forEach { gw ->
                            DropdownMenuItem(
                                text = { Text(gw.goal.title) },
                                onClick = {
                                    goalId = gw.goal.id
                                    showGoalDropdown = false
                                }
                            )
                        }
                    }
                }
            }

            NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Prazo", style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                        Text(
                            text = dueDate?.let { d ->
                                if (hasDueTime) "${DateTimeUtils.formatDate(d)} às $dueTime" else DateTimeUtils.formatDate(d)
                            } ?: "Sem prazo definido",
                            style = MaterialTheme.typography.bodyMedium,
                            color = palette.textSecondary
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        if (dueDate != null) {
                            TextButton(onClick = {
                                dueDate = null
                                hasDueTime = false
                            }) {
                                Text("Limpar", color = palette.textSecondary)
                            }
                        }
                        OutlinedButton(onClick = { showDatePicker = true }) {
                            Text(if (dueDate == null) "Definir" else "Alterar", color = palette.textPrimary)
                        }
                    }
                }

                if (dueDate != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Incluir horário (24h)", style = MaterialTheme.typography.bodyMedium, color = palette.textPrimary)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (hasDueTime) {
                                TextButton(onClick = { showTimePicker = true }) {
                                    Text(dueTime.toString(), color = palette.info)
                                }
                            }
                            Switch(
                                checked = hasDueTime,
                                onCheckedChange = { checked ->
                                    hasDueTime = checked
                                    if (checked) showTimePicker = true
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    ReminderLeadTimeSelector(
                        selectedMinutes = reminderMinutesBefore,
                        onSelectMinutes = { reminderMinutesBefore = it }
                    )
                }
            }

            NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true) {
                Text("Subtarefas", style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                Spacer(modifier = Modifier.height(8.dp))
                draftSubtasks.forEachIndexed { index, (subId, subTitle, isDone) ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isDone,
                            onCheckedChange = { checked ->
                                draftSubtasks[index] = Triple(subId, subTitle, checked)
                                val persisted = existingTask?.subtasks?.firstOrNull { it.id == subId }
                                if (persisted != null) {
                                    onToggleSubtask(persisted)
                                }
                            }
                        )
                        Text(
                            text = subTitle,
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (isDone) palette.textSecondary else palette.textPrimary,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = { draftSubtasks.removeAt(index) }) {
                            Icon(Icons.Outlined.DeleteOutline, contentDescription = "Remover", tint = palette.textSecondary)
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = newSubtaskText,
                        onValueChange = { newSubtaskText = it },
                        placeholder = { Text("Nova subtarefa") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = {
                            val clean = newSubtaskText.trim()
                            if (clean.isNotEmpty()) {
                                draftSubtasks.add(Triple(null, clean, false))
                                newSubtaskText = ""
                            }
                        }
                    ) {
                        Icon(Icons.Outlined.Add, contentDescription = "Adicionar subtarefa", tint = palette.highlight)
                    }
                }
            }

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Observações") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                maxLines = 4
            )

            if (task != null) {
                HorizontalDivider(color = palette.border)
                Text(
                    text = buildString {
                        append("Criada em ${DateTimeUtils.formatDateTime(task.createdAt)}")
                        task.completedAt?.let {
                            append(" · Concluída em ${DateTimeUtils.formatDateTime(it)}")
                        }
                    },
                    style = MaterialTheme.typography.labelMedium,
                    color = palette.textSecondary
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (task != null) {
                    OutlinedButton(
                        onClick = {
                            onToggleComplete(task.id)
                            onDismiss()
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, palette.border)
                    ) {
                        Text(
                            text = if (task.status == TaskStatus.COMPLETED) "Reabrir" else "Concluir",
                            color = palette.positive
                        )
                    }
                }

                Button(
                    onClick = {
                        if (title.isBlank()) {
                            titleError = true
                            return@Button
                        }
                        val dueEpoch = dueDate?.let { date ->
                            val time = if (hasDueTime) dueTime else LocalTime.of(23, 59)
                            DateTimeUtils.localDateTimeToEpoch(date, time)
                        }
                        val finalSubtasks = draftSubtasks.toMutableList().apply {
                            val pendingSub = newSubtaskText.trim()
                            if (pendingSub.isNotEmpty()) add(Triple(null, pendingSub, false))
                        }
                        onSave(
                            task?.id,
                            title,
                            description,
                            projectId,
                            categoryId,
                            eventId,
                            goalId,
                            priority,
                            status,
                            dueEpoch,
                            hasDueTime,
                            notes,
                            finalSubtasks,
                            reminderMinutesBefore
                        )
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = palette.highlight,
                        contentColor = palette.background
                    )
                ) {
                    Text("Salvar")
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    if (showDatePicker) {
        NexusDatePickerDialog(
            initialDate = dueDate ?: DateTimeUtils.todayLocalDate(),
            onDateSelected = { dueDate = it },
            onDismiss = { showDatePicker = false }
        )
    }

    if (showTimePicker) {
        NexusTimePickerDialog(
            initialTime = dueTime,
            onTimeSelected = { dueTime = it },
            onDismiss = { showTimePicker = false }
        )
    }

    if (showDeleteConfirm && task != null) {
        ConfirmDangerDialog(
            title = "Excluir tarefa?",
            message = "A tarefa \"${task.title}\" e suas subtarefas serão removidas permanentemente.",
            onConfirm = {
                showDeleteConfirm = false
                onDelete(task.id)
            },
            onDismiss = { showDeleteConfirm = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentEditorSheet(
    existingAppointment: AppointmentEntity?,
    categories: List<CategoryEntity>,
    initialDate: LocalDate,
    onDismiss: () -> Unit,
    onSave: (
        id: String?,
        title: String,
        description: String,
        categoryId: String?,
        startAt: Long,
        endAt: Long,
        isAllDay: Boolean,
        location: String,
        link: String,
        reminderMinutesBefore: Int?
    ) -> Unit,
    onDelete: (String) -> Unit,
    onError: (String, Throwable) -> Unit
) {
    val palette = LocalNexusPalette.current
    val context = LocalContext.current

    var title by remember(existingAppointment?.id) { mutableStateOf(existingAppointment?.title ?: "") }
    var description by remember(existingAppointment?.id) { mutableStateOf(existingAppointment?.description ?: "") }
    var categoryId by remember(existingAppointment?.id) { mutableStateOf(existingAppointment?.categoryId) }
    var isAllDay by remember(existingAppointment?.id) { mutableStateOf(existingAppointment?.isAllDay ?: false) }
    var location by remember(existingAppointment?.id) { mutableStateOf(existingAppointment?.location ?: "") }
    var link by remember(existingAppointment?.id) { mutableStateOf(existingAppointment?.link ?: "") }

    var startDate by remember(existingAppointment?.id) {
        mutableStateOf(existingAppointment?.let { DateTimeUtils.epochToLocalDate(it.startAt) } ?: initialDate)
    }
    var startTime by remember(existingAppointment?.id) {
        mutableStateOf(existingAppointment?.let { DateTimeUtils.epochToLocalTime(it.startAt) } ?: LocalTime.of(9, 0))
    }
    var endDate by remember(existingAppointment?.id) {
        mutableStateOf(existingAppointment?.let { DateTimeUtils.epochToLocalDate(it.endAt) } ?: initialDate)
    }
    var endTime by remember(existingAppointment?.id) {
        mutableStateOf(existingAppointment?.let { DateTimeUtils.epochToLocalTime(it.endAt) } ?: LocalTime.of(10, 0))
    }

    var pickTarget by remember { mutableStateOf<String?>(null) }
    var showCategoryMenu by remember { mutableStateOf(false) }
    var showDeleteConfirm by remember { mutableStateOf(false) }
    var titleError by remember { mutableStateOf(false) }
    var reminderMinutesBefore by remember(existingAppointment?.id) { mutableStateOf<Int?>(null) }

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
                    text = if (existingAppointment == null) "Novo compromisso" else "Detalhes do compromisso",
                    style = MaterialTheme.typography.titleLarge,
                    color = palette.textPrimary
                )
                if (existingAppointment != null) {
                    IconButton(onClick = { showDeleteConfirm = true }) {
                        Icon(
                            imageVector = Icons.Outlined.DeleteOutline,
                            contentDescription = "Excluir compromisso",
                            tint = palette.danger
                        )
                    }
                }
            }

            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    if (it.isNotBlank()) titleError = false
                },
                label = { Text("Título *") },
                isError = titleError,
                supportingText = if (titleError) {
                    { Text("Informe o título do compromisso.") }
                } else null,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                maxLines = 4
            )

            Box(modifier = Modifier.fillMaxWidth()) {
                val catName = categories.firstOrNull { it.id == categoryId }?.name ?: "Sem categoria"
                OutlinedButton(
                    onClick = { showCategoryMenu = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Categoria: $catName", color = palette.textPrimary)
                }
                DropdownMenu(
                    expanded = showCategoryMenu,
                    onDismissRequest = { showCategoryMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Sem categoria") },
                        onClick = {
                            categoryId = null
                            showCategoryMenu = false
                        }
                    )
                    categories.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(cat.name) },
                            onClick = {
                                categoryId = cat.id
                                showCategoryMenu = false
                            }
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Dia inteiro", style = MaterialTheme.typography.bodyLarge, color = palette.textPrimary)
                Switch(checked = isAllDay, onCheckedChange = { isAllDay = it })
            }

            NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Início", style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(onClick = { pickTarget = "startDate" }) {
                            Text(DateTimeUtils.formatDate(startDate), color = palette.textPrimary)
                        }
                        if (!isAllDay) {
                            OutlinedButton(onClick = { pickTarget = "startTime" }) {
                                Text(startTime.toString(), color = palette.textPrimary)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Término", style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(onClick = { pickTarget = "endDate" }) {
                            Text(DateTimeUtils.formatDate(endDate), color = palette.textPrimary)
                        }
                        if (!isAllDay) {
                            OutlinedButton(onClick = { pickTarget = "endTime" }) {
                                Text(endTime.toString(), color = palette.textPrimary)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                ReminderLeadTimeSelector(
                    selectedMinutes = reminderMinutesBefore,
                    onSelectMinutes = { reminderMinutesBefore = it }
                )
            }

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Local") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = link,
                onValueChange = { link = it },
                label = { Text("Link opcional (URL ou Maps)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            if (link.isNotBlank() || location.isNotBlank()) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (link.isNotBlank()) {
                        OutlinedButton(
                            onClick = {
                                runCatching {
                                    val normalized = if (link.startsWith("http://") || link.startsWith("https://")) {
                                        link.trim()
                                    } else {
                                        "https://${link.trim()}"
                                    }
                                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(normalized)))
                                }.onFailure { onError("Link externo", it) }
                            }
                        ) {
                            Icon(Icons.Outlined.OpenInNew, contentDescription = null)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Abrir link")
                        }
                    }
                    if (location.isNotBlank()) {
                        OutlinedButton(
                            onClick = {
                                runCatching {
                                    val geoUri = Uri.parse("geo:0,0?q=${Uri.encode(location.trim())}")
                                    context.startActivity(Intent(Intent.ACTION_VIEW, geoUri))
                                }.onFailure { onError("Abrir mapa", it) }
                            }
                        ) {
                            Icon(Icons.Outlined.OpenInNew, contentDescription = null)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Abrir no Mapa")
                        }
                    }
                }
            }

            Button(
                onClick = {
                    if (title.isBlank()) {
                        titleError = true
                        return@Button
                    }
                    val startEpoch = if (isAllDay) {
                        DateTimeUtils.localDateToStartOfDayEpoch(startDate)
                    } else {
                        DateTimeUtils.localDateTimeToEpoch(startDate, startTime)
                    }
                    val endEpoch = if (isAllDay) {
                        DateTimeUtils.localDateToEndOfDayEpoch(if (endDate < startDate) startDate else endDate)
                    } else {
                        DateTimeUtils.localDateTimeToEpoch(
                            if (endDate < startDate) startDate else endDate,
                            endTime
                        )
                    }
                    onSave(
                        existingAppointment?.id,
                        title,
                        description,
                        categoryId,
                        startEpoch,
                        endEpoch,
                        isAllDay,
                        location,
                        link,
                        reminderMinutesBefore
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = palette.highlight,
                    contentColor = palette.background
                )
            ) {
                Text("Salvar compromisso")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    when (pickTarget) {
        "startDate" -> NexusDatePickerDialog(
            initialDate = startDate,
            onDateSelected = {
                startDate = it
                if (endDate < it) endDate = it
            },
            onDismiss = { pickTarget = null }
        )
        "endDate" -> NexusDatePickerDialog(
            initialDate = endDate,
            onDateSelected = { endDate = it },
            onDismiss = { pickTarget = null }
        )
        "startTime" -> NexusTimePickerDialog(
            initialTime = startTime,
            onTimeSelected = { startTime = it },
            onDismiss = { pickTarget = null }
        )
        "endTime" -> NexusTimePickerDialog(
            initialTime = endTime,
            onTimeSelected = { endTime = it },
            onDismiss = { pickTarget = null }
        )
    }

    if (showDeleteConfirm && existingAppointment != null) {
        ConfirmDangerDialog(
            title = "Excluir compromisso?",
            message = "O compromisso \"${existingAppointment.title}\" será excluído permanentemente.",
            onConfirm = {
                showDeleteConfirm = false
                onDelete(existingAppointment.id)
            },
            onDismiss = { showDeleteConfirm = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectEditorSheet(
    existingProject: ProjectWithProgress?,
    goals: List<PersonalGoalWithDetails> = emptyList(),
    countLinkedTasks: suspend (String) -> Int,
    onDismiss: () -> Unit,
    onSave: (
        id: String?,
        name: String,
        description: String,
        deadlineAt: Long?,
        status: ProjectStatus,
        priority: TaskPriority,
        goalId: String?
    ) -> Unit,
    onDelete: (String) -> Unit
) {
    val palette = LocalNexusPalette.current
    val project = existingProject?.project

    var name by remember(project?.id) { mutableStateOf(project?.name ?: "") }
    var description by remember(project?.id) { mutableStateOf(project?.description ?: "") }
    var deadlineDate by remember(project?.id) {
        mutableStateOf(project?.deadlineAt?.let { DateTimeUtils.epochToLocalDate(it) })
    }
    var status by remember(project?.id) { mutableStateOf(project?.status ?: ProjectStatus.ACTIVE) }
    var priority by remember(project?.id) { mutableStateOf(project?.priority ?: TaskPriority.NORMAL) }
    var goalId by remember(project?.id) { mutableStateOf(project?.goalId) }

    var showDatePicker by remember { mutableStateOf(false) }
    var showStatusMenu by remember { mutableStateOf(false) }
    var showPriorityMenu by remember { mutableStateOf(false) }
    var showGoalMenu by remember { mutableStateOf(false) }
    var showDeleteConfirm by remember { mutableStateOf(false) }
    var linkedTaskCount by remember { mutableStateOf(0) }
    var nameError by remember { mutableStateOf(false) }

    LaunchedEffect(showDeleteConfirm, project?.id) {
        if (showDeleteConfirm && project != null) {
            linkedTaskCount = countLinkedTasks(project.id)
        }
    }

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
                    text = if (project == null) "Novo projeto" else "Editar projeto",
                    style = MaterialTheme.typography.titleLarge,
                    color = palette.textPrimary
                )
                if (project != null) {
                    IconButton(onClick = { showDeleteConfirm = true }) {
                        Icon(
                            imageVector = Icons.Outlined.DeleteOutline,
                            contentDescription = "Excluir projeto",
                            tint = palette.danger
                        )
                    }
                }
            }

            if (existingProject != null) {
                Text(
                    text = "Progresso calculado: ${existingProject.completedTasks}/${existingProject.totalTasks} tarefas concluídas (${existingProject.progressPercent}%)",
                    style = MaterialTheme.typography.labelMedium,
                    color = palette.textSecondary
                )
                if (existingProject.linkedTransactionsCount > 0) {
                    Text(
                        text = "Financeiro vinculado (${existingProject.linkedTransactionsCount}): ${DateTimeUtils.formatBrl(existingProject.estimatedCostCents)} · Pago: ${DateTimeUtils.formatBrl(existingProject.paidCostCents)}",
                        style = MaterialTheme.typography.labelMedium,
                        color = palette.info
                    )
                }
            }

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    if (it.isNotBlank()) nameError = false
                },
                label = { Text("Nome do projeto *") },
                isError = nameError,
                supportingText = if (nameError) {
                    { Text("Informe o nome do projeto.") }
                } else null,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descrição") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                maxLines = 4
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(
                        onClick = { showStatusMenu = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(status.label, color = palette.textPrimary)
                    }
                    DropdownMenu(
                        expanded = showStatusMenu,
                        onDismissRequest = { showStatusMenu = false }
                    ) {
                        ProjectStatus.entries.forEach { st ->
                            DropdownMenuItem(
                                text = { Text(st.label) },
                                onClick = {
                                    status = st
                                    showStatusMenu = false
                                }
                            )
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(
                        onClick = { showPriorityMenu = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Prioridade: ${priority.label}", color = palette.textPrimary)
                    }
                    DropdownMenu(
                        expanded = showPriorityMenu,
                        onDismissRequest = { showPriorityMenu = false }
                    ) {
                        TaskPriority.entries.forEach { pr ->
                            DropdownMenuItem(
                                text = { Text(pr.label) },
                                onClick = {
                                    priority = pr
                                    showPriorityMenu = false
                                }
                            )
                        }
                    }
                }
            }

            NexusCard(modifier = Modifier.fillMaxWidth(), elevated = true) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Prazo do projeto", style = MaterialTheme.typography.titleMedium, color = palette.textPrimary)
                        Text(
                            text = deadlineDate?.let { DateTimeUtils.formatDate(it) } ?: "Sem prazo definido",
                            style = MaterialTheme.typography.bodyMedium,
                            color = palette.textSecondary
                        )
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        if (deadlineDate != null) {
                            TextButton(onClick = { deadlineDate = null }) {
                                Text("Limpar", color = palette.textSecondary)
                            }
                        }
                        OutlinedButton(onClick = { showDatePicker = true }) {
                            Text(if (deadlineDate == null) "Definir" else "Alterar", color = palette.textPrimary)
                        }
                    }
                }
            }

            if (goals.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    val goalTitle = goals.firstOrNull { it.goal.id == goalId }?.goal?.title ?: "Sem objetivo pessoal"
                    OutlinedButton(
                        onClick = { showGoalMenu = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Objetivo: $goalTitle", color = palette.textPrimary, maxLines = 1)
                    }
                    DropdownMenu(
                        expanded = showGoalMenu,
                        onDismissRequest = { showGoalMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Sem objetivo pessoal") },
                            onClick = {
                                goalId = null
                                showGoalMenu = false
                            }
                        )
                        goals.forEach { gw ->
                            DropdownMenuItem(
                                text = { Text(gw.goal.title) },
                                onClick = {
                                    goalId = gw.goal.id
                                    showGoalMenu = false
                                }
                            )
                        }
                    }
                }
            }

            Button(
                onClick = {
                    if (name.isBlank()) {
                        nameError = true
                        return@Button
                    }
                    val deadlineEpoch = deadlineDate?.let { DateTimeUtils.localDateToEndOfDayEpoch(it) }
                    onSave(project?.id, name, description, deadlineEpoch, status, priority, goalId)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = palette.highlight,
                    contentColor = palette.background
                )
            ) {
                Text("Salvar projeto")
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    if (showDatePicker) {
        NexusDatePickerDialog(
            initialDate = deadlineDate ?: DateTimeUtils.todayLocalDate(),
            onDateSelected = { deadlineDate = it },
            onDismiss = { showDatePicker = false }
        )
    }

    if (showDeleteConfirm && project != null) {
        val message = if (linkedTaskCount > 0) {
            "Excluir o projeto \"${project.name}\" desvinculará $linkedTaskCount tarefa(s), sem apagá-las."
        } else {
            "Excluir o projeto \"${project.name}\"?"
        }
        ConfirmDangerDialog(
            title = "Excluir projeto?",
            message = message,
            confirmLabel = "Excluir projeto",
            onConfirm = {
                showDeleteConfirm = false
                onDelete(project.id)
            },
            onDismiss = { showDeleteConfirm = false }
        )
    }
}
