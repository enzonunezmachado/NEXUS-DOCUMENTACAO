package com.nexus.app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nexus.app.core.DateTimeUtils
import com.nexus.app.data.repository.NexusRepository
import com.nexus.app.domain.model.TaskPriority
import com.nexus.app.domain.model.TaskStatus
import com.nexus.app.domain.model.TaskWithDetails
import com.nexus.app.ui.theme.LocalNexusPalette
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset

@Composable
fun NexusCard(
    modifier: Modifier = Modifier,
    elevated: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val palette = LocalNexusPalette.current
    val shape = RoundedCornerShape(14.dp)
    val bgColor = if (elevated) palette.surfaceElevated else palette.surface
    val cardModifier = if (onClick != null) {
        modifier.clip(shape).clickable(onClick = onClick)
    } else {
        modifier
    }

    Surface(
        modifier = cardModifier,
        shape = shape,
        color = bgColor,
        border = BorderStroke(1.dp, palette.border),
        tonalElevation = 0.dp,
        shadowElevation = 0.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            content = content
        )
    }
}

@Composable
fun SectionHeader(
    title: String,
    trailing: String? = null,
    modifier: Modifier = Modifier
) {
    val palette = LocalNexusPalette.current
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = palette.textPrimary
        )
        if (trailing != null) {
            Text(
                text = trailing,
                style = MaterialTheme.typography.labelMedium,
                color = palette.textSecondary
            )
        }
    }
}

@Composable
fun NexusEmptyState(
    icon: ImageVector,
    title: String,
    subtitle: String,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val palette = LocalNexusPalette.current
    NexusCard(
        modifier = modifier.fillMaxWidth(),
        elevated = false
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = palette.textSecondary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = palette.textPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = palette.textSecondary
            )
            if (actionLabel != null && onAction != null) {
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = onAction,
                    border = BorderStroke(1.dp, palette.border),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = actionLabel, color = palette.textPrimary)
                }
            }
        }
    }
}

@Composable
fun TaskRowCard(
    item: TaskWithDetails,
    onClick: () -> Unit,
    onToggleComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val palette = LocalNexusPalette.current
    val task = item.task
    val isCompleted = task.status == TaskStatus.COMPLETED
    val isOverdue = NexusRepository.isTaskOverdue(task)

    NexusCard(
        modifier = modifier.fillMaxWidth(),
        elevated = true,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            IconButton(
                onClick = onToggleComplete,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = if (isCompleted) Icons.Outlined.CheckCircle else Icons.Outlined.RadioButtonUnchecked,
                    contentDescription = if (isCompleted) "Reabrir tarefa" else "Concluir tarefa",
                    tint = if (isCompleted) palette.positive else palette.textSecondary
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None
                    ),
                    color = if (isCompleted) palette.textSecondary else palette.textPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                val metaParts = buildList {
                    add(task.status.label)
                    add("Prioridade ${task.priority.label}")
                    item.project?.let { add(it.name) }
                    task.dueAt?.let { due ->
                        val dateText = if (task.hasDueTime) {
                            DateTimeUtils.formatDateTime(due)
                        } else {
                            DateTimeUtils.formatDate(due)
                        }
                        add(dateText)
                    }
                    if (item.subtasks.isNotEmpty()) {
                        add("${item.completedSubtasksCount}/${item.subtasks.size} subtarefas")
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    PriorityIndicatorDot(priority = task.priority)
                    Text(
                        text = metaParts.joinToString(" · "),
                        style = MaterialTheme.typography.labelMedium,
                        color = if (isOverdue) palette.danger else palette.textSecondary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun PriorityIndicatorDot(priority: TaskPriority) {
    val palette = LocalNexusPalette.current
    val color = when (priority) {
        TaskPriority.LOW -> palette.textSecondary
        TaskPriority.NORMAL -> palette.textPrimary
        TaskPriority.HIGH -> palette.warning
        TaskPriority.URGENT -> palette.danger
    }
    Box(
        modifier = Modifier
            .size(7.dp)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun parseHexColorOrDefault(hex: String?, fallback: Color): Color {
    if (hex.isNullOrBlank()) return fallback
    return try {
        Color(android.graphics.Color.parseColor(hex))
    } catch (_: Throwable) {
        fallback
    }
}

@Composable
fun ConfirmDangerDialog(
    title: String,
    message: String,
    confirmLabel: String = "Excluir",
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val palette = LocalNexusPalette.current
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = palette.surfaceElevated,
        title = {
            Text(text = title, color = palette.textPrimary, fontWeight = FontWeight.SemiBold)
        },
        text = {
            Text(text = message, color = palette.textSecondary)
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = palette.danger,
                    contentColor = Color.White
                )
            ) {
                Text(confirmLabel)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = palette.textPrimary)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NexusDatePickerDialog(
    initialDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    val initialMillis = initialDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
    val state = rememberDatePickerState(initialSelectedDateMillis = initialMillis)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    val millis = state.selectedDateMillis
                    if (millis != null) {
                        val picked = Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate()
                        onDateSelected(picked)
                    }
                    onDismiss()
                }
            ) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    ) {
        DatePicker(state = state)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NexusTimePickerDialog(
    initialTime: LocalTime,
    onTimeSelected: (LocalTime) -> Unit,
    onDismiss: () -> Unit
) {
    val palette = LocalNexusPalette.current
    val state = rememberTimePickerState(
        initialHour = initialTime.hour,
        initialMinute = initialTime.minute,
        is24Hour = true
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = palette.surfaceElevated,
        title = { Text("Selecionar horário (24h)", color = palette.textPrimary) },
        text = {
            TimePicker(state = state)
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onTimeSelected(LocalTime.of(state.hour, state.minute))
                    onDismiss()
                }
            ) {
                Text("Confirmar", color = palette.highlight)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar", color = palette.textSecondary)
            }
        }
    )
}

@Composable
fun ReminderLeadTimeSelector(
    selectedMinutes: Int?,
    onSelectMinutes: (Int?) -> Unit,
    modifier: Modifier = Modifier
) {
    val palette = LocalNexusPalette.current
    val options: List<Pair<Int?, String>> = listOf(
        null to "Padrão",
        0 to "Na hora",
        15 to "15 min",
        30 to "30 min",
        60 to "1 hora",
        -1 to "Sem lembrete"
    )

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Lembrete local",
            style = MaterialTheme.typography.titleMedium,
            color = palette.textPrimary
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            options.take(3).forEach { (value, label) ->
                val isSelected = selectedMinutes == value
                OutlinedButton(
                    onClick = { onSelectMinutes(value) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, if (isSelected) palette.highlight else palette.border)
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        color = if (isSelected) palette.highlight else palette.textSecondary,
                        maxLines = 1
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            options.drop(3).forEach { (value, label) ->
                val isSelected = selectedMinutes == value
                OutlinedButton(
                    onClick = { onSelectMinutes(value) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, if (isSelected) palette.highlight else palette.border)
                ) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium,
                        color = if (isSelected) palette.highlight else palette.textSecondary,
                        maxLines = 1
                    )
                }
            }
        }
    }
}

