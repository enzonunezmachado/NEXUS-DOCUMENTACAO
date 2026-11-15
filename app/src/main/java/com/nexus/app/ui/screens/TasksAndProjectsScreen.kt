package com.nexus.app.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.FolderOpen
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nexus.app.core.DateTimeUtils
import com.nexus.app.domain.model.ProjectWithProgress
import com.nexus.app.domain.model.TaskPriority
import com.nexus.app.domain.model.TaskStatus
import com.nexus.app.domain.model.TaskWithDetails
import com.nexus.app.ui.TasksFilterState
import com.nexus.app.ui.TasksTabMode
import com.nexus.app.ui.components.NexusCard
import com.nexus.app.ui.components.NexusEmptyState
import com.nexus.app.ui.components.TaskRowCard
import com.nexus.app.ui.theme.LocalNexusPalette

@Composable
fun TasksAndProjectsScreen(
    tasks: List<TaskWithDetails>,
    projects: List<ProjectWithProgress>,
    filter: TasksFilterState,
    onTabChange: (TasksTabMode) -> Unit,
    onStatusFilterChange: (TaskStatus?) -> Unit,
    onPriorityFilterChange: (TaskPriority?) -> Unit,
    onProjectFilterChange: (String?) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onOpenTask: (String) -> Unit,
    onToggleTaskComplete: (String) -> Unit,
    onUpdateTaskStatus: (String, TaskStatus) -> Unit,
    onOpenProject: (String) -> Unit,
    onCreateTask: () -> Unit,
    onCreateProject: () -> Unit,
    modifier: Modifier = Modifier
) {
    val palette = LocalNexusPalette.current

    Column(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Tarefas e Projetos",
                    style = MaterialTheme.typography.headlineMedium,
                    color = palette.textPrimary
                )
                if (filter.tabMode == TasksTabMode.PROJECTS) {
                    OutlinedButton(onClick = onCreateProject, shape = RoundedCornerShape(10.dp)) {
                        Text("Novo projeto", color = palette.textPrimary)
                    }
                }
            }
        }

        ScrollableTabRow(
            selectedTabIndex = TasksTabMode.entries.indexOf(filter.tabMode),
            containerColor = palette.background,
            contentColor = palette.highlight,
            edgePadding = 16.dp
        ) {
            TasksTabMode.entries.forEach { tab ->
                Tab(
                    selected = filter.tabMode == tab,
                    onClick = { onTabChange(tab) },
                    text = { Text(tab.label, maxLines = 1) }
                )
            }
        }

        if (filter.tabMode == TasksTabMode.PROJECTS) {
            ProjectsListContent(
                projects = projects,
                onOpenProject = onOpenProject,
                onCreateProject = onCreateProject
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = filter.searchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Buscar tarefas por título, descrição ou notas") },
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

                TasksFilterRow(
                    filter = filter,
                    projects = projects,
                    onStatusFilterChange = onStatusFilterChange,
                    onPriorityFilterChange = onPriorityFilterChange,
                    onProjectFilterChange = onProjectFilterChange
                )
            }

            if (filter.tabMode == TasksTabMode.KANBAN) {
                KanbanBoardContent(
                    tasks = tasks,
                    onOpenTask = onOpenTask,
                    onUpdateTaskStatus = onUpdateTaskStatus
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 96.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (tasks.isEmpty()) {
                        item {
                            NexusEmptyState(
                                icon = Icons.Outlined.TaskAlt,
                                title = "Nenhuma tarefa encontrada",
                                subtitle = when (filter.tabMode) {
                                    TasksTabMode.OVERDUE -> "Você não possui tarefas atrasadas no momento."
                                    TasksTabMode.COMPLETED -> "As tarefas concluídas aparecerão aqui."
                                    else -> "Crie uma tarefa pelo botão + ou ajuste os filtros."
                                },
                                actionLabel = if (filter.tabMode == TasksTabMode.LIST) "Nova tarefa" else null,
                                onAction = if (filter.tabMode == TasksTabMode.LIST) onCreateTask else null
                            )
                        }
                    } else {
                        items(tasks, key = { it.task.id }) { item ->
                            TaskRowCard(
                                item = item,
                                onClick = { onOpenTask(item.task.id) },
                                onToggleComplete = { onToggleTaskComplete(item.task.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TasksFilterRow(
    filter: TasksFilterState,
    projects: List<ProjectWithProgress>,
    onStatusFilterChange: (TaskStatus?) -> Unit,
    onPriorityFilterChange: (TaskPriority?) -> Unit,
    onProjectFilterChange: (String?) -> Unit
) {
    val palette = LocalNexusPalette.current
    var showStatusMenu by remember { mutableStateOf(false) }
    var showPriorityMenu by remember { mutableStateOf(false) }
    var showProjectMenu by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box {
            FilterChip(
                selected = filter.statusFilter != null,
                onClick = { showStatusMenu = true },
                label = {
                    Text(filter.statusFilter?.let { "Status: ${it.label}" } ?: "Status: Todos")
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = palette.surfaceElevated,
                    selectedLabelColor = palette.highlight
                )
            )
            DropdownMenu(
                expanded = showStatusMenu,
                onDismissRequest = { showStatusMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Todos") },
                    onClick = {
                        onStatusFilterChange(null)
                        showStatusMenu = false
                    }
                )
                TaskStatus.entries.forEach { st ->
                    DropdownMenuItem(
                        text = { Text(st.label) },
                        onClick = {
                            onStatusFilterChange(st)
                            showStatusMenu = false
                        }
                    )
                }
            }
        }

        Box {
            FilterChip(
                selected = filter.priorityFilter != null,
                onClick = { showPriorityMenu = true },
                label = {
                    Text(filter.priorityFilter?.let { "Prioridade: ${it.label}" } ?: "Prioridade: Todas")
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = palette.surfaceElevated,
                    selectedLabelColor = palette.highlight
                )
            )
            DropdownMenu(
                expanded = showPriorityMenu,
                onDismissRequest = { showPriorityMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Todas") },
                    onClick = {
                        onPriorityFilterChange(null)
                        showPriorityMenu = false
                    }
                )
                TaskPriority.entries.forEach { pr ->
                    DropdownMenuItem(
                        text = { Text(pr.label) },
                        onClick = {
                            onPriorityFilterChange(pr)
                            showPriorityMenu = false
                        }
                    )
                }
            }
        }

        Box {
            val selectedProjectName = projects.firstOrNull { it.project.id == filter.projectFilterId }?.project?.name
            FilterChip(
                selected = filter.projectFilterId != null,
                onClick = { showProjectMenu = true },
                label = {
                    Text(selectedProjectName?.let { "Projeto: $it" } ?: "Projeto: Todos")
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = palette.surfaceElevated,
                    selectedLabelColor = palette.highlight
                )
            )
            DropdownMenu(
                expanded = showProjectMenu,
                onDismissRequest = { showProjectMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Todos") },
                    onClick = {
                        onProjectFilterChange(null)
                        showProjectMenu = false
                    }
                )
                projects.forEach { pw ->
                    DropdownMenuItem(
                        text = { Text(pw.project.name) },
                        onClick = {
                            onProjectFilterChange(pw.project.id)
                            showProjectMenu = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun KanbanBoardContent(
    tasks: List<TaskWithDetails>,
    onOpenTask: (String) -> Unit,
    onUpdateTaskStatus: (String, TaskStatus) -> Unit
) {
    val palette = LocalNexusPalette.current
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 96.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(TaskStatus.entries, key = { it.name }) { status ->
            val columnTasks = tasks.filter { it.task.status == status }
            NexusCard(
                modifier = Modifier
                    .width(280.dp)
                    .fillMaxSize(),
                elevated = false
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = status.label,
                        style = MaterialTheme.typography.titleMedium,
                        color = palette.textPrimary
                    )
                    Text(
                        text = columnTasks.size.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        color = palette.textSecondary
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (columnTasks.isEmpty()) {
                    Text(
                        text = "Nenhuma tarefa nesta coluna.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = palette.textSecondary
                    )
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(columnTasks, key = { "kanban_${it.task.id}" }) { taskItem ->
                            KanbanTaskCard(
                                item = taskItem,
                                onOpenTask = { onOpenTask(taskItem.task.id) },
                                onMoveStatus = { next -> onUpdateTaskStatus(taskItem.task.id, next) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun KanbanTaskCard(
    item: TaskWithDetails,
    onOpenTask: () -> Unit,
    onMoveStatus: (TaskStatus) -> Unit
) {
    val palette = LocalNexusPalette.current
    var showMoveMenu by remember { mutableStateOf(false) }

    NexusCard(
        modifier = Modifier.fillMaxWidth(),
        elevated = true,
        onClick = onOpenTask
    ) {
        Text(
            text = item.task.title,
            style = MaterialTheme.typography.titleMedium,
            color = palette.textPrimary
        )
        Spacer(modifier = Modifier.height(4.dp))
        val meta = buildList {
            add(item.task.priority.label)
            item.project?.let { add(it.name) }
            item.task.dueAt?.let { add(DateTimeUtils.formatDate(it)) }
        }.joinToString(" · ")
        Text(
            text = meta,
            style = MaterialTheme.typography.labelMedium,
            color = palette.textSecondary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box {
            OutlinedButton(
                onClick = { showMoveMenu = true },
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text("Mover status", style = MaterialTheme.typography.labelMedium, color = palette.textPrimary)
            }
            DropdownMenu(
                expanded = showMoveMenu,
                onDismissRequest = { showMoveMenu = false }
            ) {
                TaskStatus.entries.forEach { target ->
                    if (target != item.task.status) {
                        DropdownMenuItem(
                            text = { Text(target.label) },
                            onClick = {
                                showMoveMenu = false
                                onMoveStatus(target)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProjectsListContent(
    projects: List<ProjectWithProgress>,
    onOpenProject: (String) -> Unit,
    onCreateProject: () -> Unit
) {
    val palette = LocalNexusPalette.current
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 96.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (projects.isEmpty()) {
            item {
                NexusEmptyState(
                    icon = Icons.Outlined.FolderOpen,
                    title = "Nenhum projeto criado",
                    subtitle = "Agrupe tarefas em projetos e acompanhe o progresso calculado automaticamente.",
                    actionLabel = "Novo projeto",
                    onAction = onCreateProject
                )
            }
        } else {
            items(projects, key = { it.project.id }) { pw ->
                val project = pw.project
                NexusCard(
                    modifier = Modifier.fillMaxWidth(),
                    elevated = true,
                    onClick = { onOpenProject(project.id) }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = project.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = palette.textPrimary,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "${pw.progressPercent}%",
                            style = MaterialTheme.typography.labelMedium,
                            color = if (pw.progressPercent == 100 && pw.totalTasks > 0) palette.positive else palette.textPrimary
                        )
                    }
                    if (project.description.isNotBlank()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = project.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = palette.textSecondary,
                            maxLines = 2
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    LinearProgressIndicator(
                        progress = { pw.progressFraction },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = if (pw.progressPercent == 100 && pw.totalTasks > 0) palette.positive else palette.highlight,
                        trackColor = palette.border
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    val meta = buildList {
                        add(project.status.label)
                        add("Prioridade ${project.priority.label}")
                        add("${pw.completedTasks}/${pw.totalTasks} tarefas concluídas")
                        project.deadlineAt?.let { add("Prazo ${DateTimeUtils.formatDate(it)}") }
                    }.joinToString(" · ")
                    Text(
                        text = meta,
                        style = MaterialTheme.typography.labelMedium,
                        color = palette.textSecondary
                    )
                    if (pw.linkedTransactionsCount > 0) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Financeiro (${pw.linkedTransactionsCount}): ${DateTimeUtils.formatBrl(pw.estimatedCostCents)} · Pago: ${DateTimeUtils.formatBrl(pw.paidCostCents)}",
                            style = MaterialTheme.typography.labelSmall,
                            color = palette.info
                        )
                    }
                }
            }
        }
    }
}
