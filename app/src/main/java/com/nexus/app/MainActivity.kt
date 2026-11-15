package com.nexus.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nexus.app.data.db.entity.AppointmentEntity
import com.nexus.app.data.db.entity.CreditCardEntity
import com.nexus.app.data.db.entity.FinancialGoalEntity
import com.nexus.app.data.db.entity.LoanEntity
import com.nexus.app.domain.model.ReminderSourceType
import com.nexus.app.domain.model.TransactionType
import com.nexus.app.notifications.NexusNotificationScheduler
import com.nexus.app.ui.FinanceTabMode
import com.nexus.app.ui.NexusViewModel
import com.nexus.app.ui.screens.AgendaScreen
import com.nexus.app.ui.screens.AppointmentEditorSheet
import com.nexus.app.ui.screens.BackupScreen
import com.nexus.app.ui.screens.BudgetEditorDialog
import com.nexus.app.ui.screens.CreditCardEditorSheet
import com.nexus.app.ui.screens.DashboardCustomizationScreen
import com.nexus.app.ui.screens.DeclaredBalanceDialog
import com.nexus.app.ui.screens.DiagnosticsScreen
import com.nexus.app.ui.screens.EventEditorSheet
import com.nexus.app.ui.screens.EventsScreen
import com.nexus.app.ui.screens.FinanceScreen
import com.nexus.app.ui.screens.FinancialGoalEditorDialog
import com.nexus.app.ui.screens.GlobalSearchScreen
import com.nexus.app.ui.screens.HomeScreen
import com.nexus.app.ui.screens.InstallmentPurchaseEditorSheet
import com.nexus.app.ui.screens.LoanEditorSheet
import com.nexus.app.ui.screens.LoanPaymentDialog
import com.nexus.app.ui.screens.MoreScreen
import com.nexus.app.ui.screens.PlanningGoalsScreen
import com.nexus.app.ui.screens.ProjectEditorSheet
import com.nexus.app.ui.screens.QuickAddChooserSheet
import com.nexus.app.ui.screens.QuoteLibraryScreen
import com.nexus.app.ui.screens.RecurringRuleEditorSheet
import com.nexus.app.ui.screens.RoutineAssistantScreen
import com.nexus.app.ui.screens.SettingsScreen
import com.nexus.app.ui.screens.TaskEditorSheet
import com.nexus.app.ui.screens.TasksAndProjectsScreen
import com.nexus.app.ui.screens.TransactionEditorSheet
import com.nexus.app.ui.screens.WidgetsHubScreen
import com.nexus.app.ui.theme.LocalNexusPalette
import com.nexus.app.ui.theme.NexusTheme
import com.nexus.app.widgets.NexusWidgetNavigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

enum class MainTab(val label: String) {
    HOME("Início"),
    AGENDA("Agenda"),
    TASKS("Tarefas"),
    FINANCE("Finanças"),
    EVENTS("Eventos")
}

sealed interface SubRoute {
    data object None : SubRoute
    data object MoreMenu : SubRoute
    data object WidgetsHub : SubRoute
    data object GlobalSearch : SubRoute
    data object Settings : SubRoute
    data object Backup : SubRoute
    data object Diagnostics : SubRoute
    data class RoutineAssistant(val initialTabIndex: Int = 0) : SubRoute
    data class Planning(val initialGoalId: String? = null) : SubRoute
    data object DashboardCustomization : SubRoute
    data object QuoteLibrary : SubRoute
}

sealed interface ActiveEditor {
    data object None : ActiveEditor
    data object QuickAddChooser : ActiveEditor
    data class TaskEditor(val taskId: String?, val initialEventId: String? = null) : ActiveEditor
    data class AppointmentEditor(val appointmentId: String?) : ActiveEditor
    data class ProjectEditor(val projectId: String?) : ActiveEditor
    data class EventEditor(val eventId: String?) : ActiveEditor
    data class TransactionEditor(
        val transactionId: String?,
        val initialType: TransactionType = TransactionType.EXPENSE,
        val initialEventId: String? = null
    ) : ActiveEditor
    data class CreditCardEditor(val card: CreditCardEntity?) : ActiveEditor
    data object InstallmentEditor : ActiveEditor
    data object RecurringEditor : ActiveEditor
    data class LoanEditor(val loan: LoanEntity?) : ActiveEditor
    data class LoanPayment(val loan: LoanEntity) : ActiveEditor
    data object DeclaredBalance : ActiveEditor
    data object BudgetEditor : ActiveEditor
    data class FinancialGoalEditor(val goal: FinancialGoalEntity?) : ActiveEditor
}

class MainActivity : ComponentActivity() {
    private val incomingIntentFlow = MutableStateFlow<Intent?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        incomingIntentFlow.value = intent

        val appContainer = (application as NexusApplication).container
        setContent {
            val viewModel: NexusViewModel = viewModel(
                factory = NexusViewModel.Factory(appContainer.repository)
            )
            val incomingIntent by incomingIntentFlow.collectAsStateWithLifecycle()
            NexusAppRoot(
                viewModel = viewModel,
                repository = appContainer.repository,
                incomingIntent = incomingIntent,
                onIntentConsumed = { incomingIntentFlow.value = null }
            )
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        incomingIntentFlow.value = intent
    }
}

@Composable
private fun NexusAppRoot(
    viewModel: NexusViewModel,
    repository: com.nexus.app.data.repository.NexusRepository,
    incomingIntent: Intent?,
    onIntentConsumed: () -> Unit
) {
    val settings by viewModel.settings.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val dashboard by viewModel.dashboard.collectAsStateWithLifecycle()
    val allTasks by viewModel.allTasks.collectAsStateWithLifecycle()
    val filteredTasks by viewModel.filteredTasks.collectAsStateWithLifecycle()
    val tasksFilter by viewModel.tasksFilter.collectAsStateWithLifecycle()
    val allProjects by viewModel.allProjects.collectAsStateWithLifecycle()
    val allEvents by viewModel.allEvents.collectAsStateWithLifecycle()
    val filteredAgendaItems by viewModel.filteredAgendaItems.collectAsStateWithLifecycle()
    val agendaFilter by viewModel.agendaFilter.collectAsStateWithLifecycle()
    val financeSummary by viewModel.financeSummary.collectAsStateWithLifecycle()
    val filteredTransactions by viewModel.filteredTransactions.collectAsStateWithLifecycle()
    val allTransactions by viewModel.allTransactions.collectAsStateWithLifecycle()
    val financeFilter by viewModel.financeFilter.collectAsStateWithLifecycle()
    val cardsWithStatements by viewModel.cardsWithStatements.collectAsStateWithLifecycle()
    val installmentPurchases by viewModel.installmentPurchases.collectAsStateWithLifecycle()
    val recurringRules by viewModel.recurringRules.collectAsStateWithLifecycle()
    val budgets by viewModel.budgets.collectAsStateWithLifecycle()
    val financialGoals by viewModel.financialGoals.collectAsStateWithLifecycle()
    val loans by viewModel.loans.collectAsStateWithLifecycle()
    val auditHistory by viewModel.auditHistory.collectAsStateWithLifecycle()
    val globalSearchQuery by viewModel.globalSearchQuery.collectAsStateWithLifecycle()
    val globalSearchResults by viewModel.globalSearchResults.collectAsStateWithLifecycle()
    val tableCounts by viewModel.tableCounts.collectAsStateWithLifecycle()
    val errorLogs by viewModel.errorLogs.collectAsStateWithLifecycle()
    val backupState by viewModel.backupState.collectAsStateWithLifecycle()
    val routineSettings by viewModel.routineSettings.collectAsStateWithLifecycle()
    val reminders by viewModel.reminders.collectAsStateWithLifecycle()
    val briefingHistory by viewModel.briefingHistory.collectAsStateWithLifecycle()
    val dailyBriefing by viewModel.dailyBriefing.collectAsStateWithLifecycle()
    val nightReview by viewModel.nightReview.collectAsStateWithLifecycle()
    val personalizationSettings by viewModel.personalizationSettings.collectAsStateWithLifecycle()
    val quotes by viewModel.quotes.collectAsStateWithLifecycle()
    val allPersonalGoals by viewModel.allPersonalGoals.collectAsStateWithLifecycle()
    val filteredPersonalGoals by viewModel.filteredPersonalGoals.collectAsStateWithLifecycle()
    val goalsFilter by viewModel.goalsFilter.collectAsStateWithLifecycle()

    NexusTheme(darkTheme = settings.darkTheme) {
        val palette = LocalNexusPalette.current
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        var currentTab by remember { mutableStateOf(MainTab.HOME) }
        var subRoute by remember { mutableStateOf<SubRoute>(SubRoute.None) }
        var activeEditor by remember { mutableStateOf<ActiveEditor>(ActiveEditor.None) }
        var loadedAppointment by remember { mutableStateOf<AppointmentEntity?>(null) }

        LaunchedEffect(Unit) {
            viewModel.snackbarEvents.collect { msg ->
                snackbarHostState.showSnackbar(msg)
            }
        }

        val openAppointmentEditor: (String?) -> Unit = { apptId ->
            if (apptId == null) {
                loadedAppointment = null
                activeEditor = ActiveEditor.AppointmentEditor(null)
            } else {
                scope.launch {
                    loadedAppointment = repository.getAppointmentById(apptId)
                    activeEditor = ActiveEditor.AppointmentEditor(apptId)
                }
            }
        }

        val openFinanceCards: () -> Unit = {
            subRoute = SubRoute.None
            currentTab = MainTab.FINANCE
            viewModel.setFinanceTabMode(FinanceTabMode.CARDS)
        }

        LaunchedEffect(incomingIntent) {
            val extrasIntent = incomingIntent ?: return@LaunchedEffect
            val openRoutine = extrasIntent.getBooleanExtra(NexusNotificationScheduler.EXTRA_OPEN_ROUTINE, false)
            val sourceTypeRaw = extrasIntent.getStringExtra(NexusNotificationScheduler.EXTRA_SOURCE_TYPE)
            val sourceId = extrasIntent.getStringExtra(NexusNotificationScheduler.EXTRA_SOURCE_ID)
            val sourceType = sourceTypeRaw?.let { runCatching { ReminderSourceType.valueOf(it) }.getOrNull() }

            val widgetUri = extrasIntent.data
            val widgetTargetFromUri = widgetUri?.takeIf {
                it.scheme == NexusWidgetNavigation.SCHEME && it.host == NexusWidgetNavigation.HOST
            }?.pathSegments?.firstOrNull()
            val widgetRecordFromUri = widgetUri?.takeIf {
                it.scheme == NexusWidgetNavigation.SCHEME && it.host == NexusWidgetNavigation.HOST
            }?.pathSegments?.getOrNull(1)
            val widgetDateFromUri = widgetUri?.takeIf {
                it.scheme == NexusWidgetNavigation.SCHEME && it.host == NexusWidgetNavigation.HOST
            }?.getQueryParameter("date")

            val widgetTarget = extrasIntent.getStringExtra(NexusWidgetNavigation.EXTRA_WIDGET_TARGET) ?: widgetTargetFromUri
            val widgetRecordId = extrasIntent.getStringExtra(NexusWidgetNavigation.EXTRA_WIDGET_RECORD_ID) ?: widgetRecordFromUri
            val widgetDateIso = extrasIntent.getStringExtra(NexusWidgetNavigation.EXTRA_WIDGET_DATE_ISO) ?: widgetDateFromUri

            if (openRoutine || sourceType == ReminderSourceType.ROUTINE_MORNING || sourceType == ReminderSourceType.ROUTINE_NIGHT) {
                val tabIdx = if (sourceType == ReminderSourceType.ROUTINE_NIGHT) 1 else 0
                subRoute = SubRoute.RoutineAssistant(tabIdx)
                onIntentConsumed()
            } else if (sourceType != null && !sourceId.isNullOrBlank()) {
                subRoute = SubRoute.None
                when (sourceType) {
                    ReminderSourceType.TASK -> {
                        currentTab = MainTab.TASKS
                        activeEditor = ActiveEditor.TaskEditor(sourceId)
                    }
                    ReminderSourceType.APPOINTMENT -> {
                        currentTab = MainTab.AGENDA
                        openAppointmentEditor(sourceId)
                    }
                    ReminderSourceType.EVENT -> {
                        currentTab = MainTab.EVENTS
                        activeEditor = ActiveEditor.EventEditor(sourceId)
                    }
                    ReminderSourceType.TRANSACTION -> {
                        currentTab = MainTab.FINANCE
                        activeEditor = ActiveEditor.TransactionEditor(sourceId)
                    }
                    ReminderSourceType.STATEMENT -> openFinanceCards()
                    ReminderSourceType.ROUTINE_MORNING -> subRoute = SubRoute.RoutineAssistant(0)
                    ReminderSourceType.ROUTINE_NIGHT -> subRoute = SubRoute.RoutineAssistant(1)
                }
                onIntentConsumed()
            } else if (!widgetTarget.isNullOrBlank()) {
                subRoute = SubRoute.None
                when (widgetTarget) {
                    NexusWidgetNavigation.TARGET_HOME -> {
                        currentTab = MainTab.HOME
                        activeEditor = ActiveEditor.None
                    }
                    NexusWidgetNavigation.TARGET_AGENDA -> {
                        widgetDateIso?.let { iso ->
                            runCatching { LocalDate.parse(iso) }.getOrNull()?.let(viewModel::setAgendaSelectedDate)
                        }
                        currentTab = MainTab.AGENDA
                        activeEditor = ActiveEditor.None
                    }
                    NexusWidgetNavigation.TARGET_TASKS -> {
                        currentTab = MainTab.TASKS
                        activeEditor = ActiveEditor.None
                    }
                    NexusWidgetNavigation.TARGET_FINANCE -> {
                        currentTab = MainTab.FINANCE
                        viewModel.setFinanceTabMode(FinanceTabMode.OVERVIEW)
                        activeEditor = ActiveEditor.None
                    }
                    NexusWidgetNavigation.TARGET_FINANCE_CARDS -> openFinanceCards()
                    NexusWidgetNavigation.TARGET_EVENTS -> {
                        currentTab = MainTab.EVENTS
                        activeEditor = ActiveEditor.None
                    }
                    NexusWidgetNavigation.TARGET_APPOINTMENT -> {
                        currentTab = MainTab.AGENDA
                        if (!widgetRecordId.isNullOrBlank()) {
                            openAppointmentEditor(widgetRecordId)
                        }
                    }
                    NexusWidgetNavigation.TARGET_TASK -> {
                        currentTab = MainTab.TASKS
                        if (!widgetRecordId.isNullOrBlank()) {
                            activeEditor = ActiveEditor.TaskEditor(widgetRecordId)
                        }
                    }
                    NexusWidgetNavigation.TARGET_TRANSACTION -> {
                        currentTab = MainTab.FINANCE
                        if (!widgetRecordId.isNullOrBlank()) {
                            activeEditor = ActiveEditor.TransactionEditor(widgetRecordId)
                        }
                    }
                    NexusWidgetNavigation.TARGET_EVENT -> {
                        currentTab = MainTab.EVENTS
                        if (!widgetRecordId.isNullOrBlank()) {
                            activeEditor = ActiveEditor.EventEditor(widgetRecordId)
                        }
                    }
                }
                onIntentConsumed()
            }
        }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = palette.background,
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = {
                if (subRoute == SubRoute.None) {
                    Surface(
                        color = palette.surface,
                        contentColor = palette.textPrimary
                    ) {
                        Column(modifier = Modifier.fillMaxWidth().statusBarsPadding()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "NEXUS",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        letterSpacing = 1.6.sp
                                    ),
                                    color = palette.highlight
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    IconButton(onClick = { subRoute = SubRoute.GlobalSearch }) {
                                        Icon(
                                            imageVector = Icons.Outlined.Search,
                                            contentDescription = "Pesquisa global",
                                            tint = palette.textPrimary
                                        )
                                    }
                                    IconButton(onClick = { subRoute = SubRoute.MoreMenu }) {
                                        Icon(
                                            imageVector = Icons.Outlined.MoreHoriz,
                                            contentDescription = "Mais opções",
                                            tint = palette.textPrimary
                                        )
                                    }
                                }
                            }
                            HorizontalDivider(color = palette.border)
                        }
                    }
                }
            },
            floatingActionButton = {
                if (subRoute == SubRoute.None) {
                    FloatingActionButton(
                        onClick = { activeEditor = ActiveEditor.QuickAddChooser },
                        containerColor = palette.highlight,
                        contentColor = palette.background,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(Icons.Outlined.Add, contentDescription = "Cadastro rápido")
                    }
                }
            },
            bottomBar = {
                if (subRoute == SubRoute.None) {
                    NavigationBar(
                        containerColor = palette.surface,
                        contentColor = palette.textPrimary
                    ) {
                        MainTab.entries.forEach { tab ->
                            val icon = when (tab) {
                                MainTab.HOME -> Icons.Outlined.Home
                                MainTab.AGENDA -> Icons.Outlined.CalendarMonth
                                MainTab.TASKS -> Icons.Outlined.TaskAlt
                                MainTab.FINANCE -> Icons.Outlined.AccountBalanceWallet
                                MainTab.EVENTS -> Icons.Outlined.ConfirmationNumber
                            }
                            NavigationBarItem(
                                selected = currentTab == tab,
                                onClick = { currentTab = tab },
                                icon = { Icon(icon, contentDescription = tab.label) },
                                label = { Text(tab.label, maxLines = 1) },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = palette.highlight,
                                    selectedTextColor = palette.highlight,
                                    unselectedIconColor = palette.textSecondary,
                                    unselectedTextColor = palette.textSecondary,
                                    indicatorColor = palette.surfaceElevated
                                )
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            val contentModifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

            when (val activeRoute = subRoute) {
                SubRoute.MoreMenu -> MoreScreen(
                    onOpenPlanning = { subRoute = SubRoute.Planning(null) },
                    onOpenCustomizeDashboard = { subRoute = SubRoute.DashboardCustomization },
                    onOpenQuoteLibrary = { subRoute = SubRoute.QuoteLibrary },
                    onOpenRoutineAssistant = { subRoute = SubRoute.RoutineAssistant(0) },
                    onOpenWidgets = { subRoute = SubRoute.WidgetsHub },
                    onOpenSearch = { subRoute = SubRoute.GlobalSearch },
                    onOpenSettings = { subRoute = SubRoute.Settings },
                    onOpenBackup = { subRoute = SubRoute.Backup },
                    onOpenDiagnostics = { subRoute = SubRoute.Diagnostics },
                    onBack = { subRoute = SubRoute.None },
                    modifier = contentModifier
                )
                is SubRoute.Planning -> PlanningGoalsScreen(
                    goals = filteredPersonalGoals,
                    allGoals = allPersonalGoals,
                    categories = categories,
                    allTasks = allTasks,
                    allProjects = allProjects,
                    filter = goalsFilter,
                    initialSelectedGoalId = activeRoute.initialGoalId,
                    onBack = { subRoute = SubRoute.MoreMenu },
                    onStatusFilterChange = viewModel::setGoalsStatusFilter,
                    onCategoryFilterChange = viewModel::setGoalsCategoryFilter,
                    onSearchQueryChange = viewModel::setGoalsSearchQuery,
                    onSaveGoal = { id, title, desc, catId, targetAt, status, mode, manualPct, notes ->
                        viewModel.savePersonalGoal(id, title, desc, catId, targetAt, status, mode, manualPct, notes)
                    },
                    onUpdateGoalStatus = viewModel::updatePersonalGoalStatus,
                    onAddProgressLog = viewModel::addGoalProgressLog,
                    onDeleteGoal = { goalId -> viewModel.deletePersonalGoal(goalId) },
                    onSaveMilestone = { id, goalId, title, desc, dueAt ->
                        viewModel.saveMilestone(id, goalId, title, desc, dueAt)
                    },
                    onToggleMilestone = viewModel::toggleMilestoneCompleted,
                    onDeleteMilestone = viewModel::deleteMilestone,
                    onLinkTaskToGoal = viewModel::linkTaskToGoal,
                    onLinkProjectToGoal = viewModel::linkProjectToGoal,
                    onOpenTask = { activeEditor = ActiveEditor.TaskEditor(it) },
                    onToggleTaskComplete = viewModel::toggleTaskCompletion,
                    onOpenProject = { activeEditor = ActiveEditor.ProjectEditor(it) },
                    modifier = contentModifier
                )
                SubRoute.DashboardCustomization -> DashboardCustomizationScreen(
                    personalization = personalizationSettings,
                    blocks = dashboard.blocks,
                    onBack = { subRoute = SubRoute.None },
                    onToggleBlock = viewModel::toggleDashboardBlock,
                    onMoveBlock = viewModel::moveDashboardBlock,
                    onUpdateBlockConfig = viewModel::updateDashboardBlockConfig,
                    onToggleCompactMode = { compact ->
                        viewModel.saveGreetingAndPresentation(
                            personalizationSettings.greetingEnabled,
                            personalizationSettings.greetingTemplate,
                            personalizationSettings.dateFormatPattern,
                            personalizationSettings.showDailyQuoteOnDashboard,
                            compact,
                            personalizationSettings.showMetricSummaryOnDashboard
                        )
                    },
                    onToggleMetricSummary = { showSummary ->
                        viewModel.saveGreetingAndPresentation(
                            personalizationSettings.greetingEnabled,
                            personalizationSettings.greetingTemplate,
                            personalizationSettings.dateFormatPattern,
                            personalizationSettings.showDailyQuoteOnDashboard,
                            personalizationSettings.compactDashboardMode,
                            showSummary
                        )
                    },
                    onOpenQuoteLibrary = { subRoute = SubRoute.QuoteLibrary },
                    onResetToDefault = viewModel::resetDashboardToDefault,
                    modifier = contentModifier
                )
                SubRoute.QuoteLibrary -> QuoteLibraryScreen(
                    quotes = quotes,
                    dailyQuoteSelection = dashboard.dailyQuoteSelection,
                    showDailyQuoteOnDashboard = personalizationSettings.showDailyQuoteOnDashboard,
                    onBack = { subRoute = SubRoute.MoreMenu },
                    onToggleShowOnDashboard = { showQuote ->
                        viewModel.saveGreetingAndPresentation(
                            personalizationSettings.greetingEnabled,
                            personalizationSettings.greetingTemplate,
                            personalizationSettings.dateFormatPattern,
                            showQuote,
                            personalizationSettings.compactDashboardMode,
                            personalizationSettings.showMetricSummaryOnDashboard
                        )
                    },
                    onSaveQuote = { id, text, author, active ->
                        viewModel.saveQuote(id, text, author, active)
                    },
                    onToggleQuoteActive = viewModel::toggleQuoteActive,
                    onDeleteQuote = viewModel::deleteQuote,
                    onImportQuotes = viewModel::importQuotesFromText,
                    onExportQuotesJson = viewModel::exportQuotesJson,
                    onQuotesExported = viewModel::notifyQuotesExported,
                    onLogError = viewModel::logUiError,
                    modifier = contentModifier
                )
                SubRoute.WidgetsHub -> WidgetsHubScreen(
                    onBack = { subRoute = SubRoute.MoreMenu },
                    onNotifyMessage = viewModel::emitMessage,
                    modifier = contentModifier
                )
                is SubRoute.RoutineAssistant -> RoutineAssistantScreen(
                    dailyBriefing = dailyBriefing,
                    nightReview = nightReview,
                    routineSettings = routineSettings,
                    reminders = reminders,
                    briefingHistory = briefingHistory,
                    initialTabIndex = activeRoute.initialTabIndex,
                    onBack = { subRoute = SubRoute.None },
                    onMarkBriefingReviewed = viewModel::markBriefingReviewed,
                    onOpenTask = { activeEditor = ActiveEditor.TaskEditor(it) },
                    onToggleTaskComplete = viewModel::toggleTaskCompletion,
                    onPostponeTaskToTomorrow = viewModel::postponeTaskToTomorrow,
                    onOpenAppointment = openAppointmentEditor,
                    onOpenEvent = { activeEditor = ActiveEditor.EventEditor(it) },
                    onOpenTransaction = { activeEditor = ActiveEditor.TransactionEditor(it) },
                    onSettleTransaction = viewModel::settleTransaction,
                    onPostponeTransactionToTomorrow = viewModel::postponeTransactionToTomorrow,
                    onOpenFinanceCards = openFinanceCards,
                    onSaveRoutineSettings = viewModel::saveRoutineSettings,
                    onSnoozeReminder = { remId -> viewModel.snoozeReminder(remId, 15) },
                    onCancelReminder = viewModel::cancelReminder,
                    onSendTestNotification = viewModel::sendTestNotification,
                    onRescheduleAllReminders = viewModel::rescheduleAllReminders,
                    modifier = contentModifier
                )
                SubRoute.GlobalSearch -> GlobalSearchScreen(
                    query = globalSearchQuery,
                    results = globalSearchResults,
                    onQueryChange = viewModel::setGlobalSearchQuery,
                    onBack = { subRoute = SubRoute.None },
                    onOpenTask = { activeEditor = ActiveEditor.TaskEditor(it) },
                    onToggleTaskComplete = viewModel::toggleTaskCompletion,
                    onOpenProject = { activeEditor = ActiveEditor.ProjectEditor(it) },
                    onOpenGoal = { goalId -> subRoute = SubRoute.Planning(goalId) },
                    onOpenAppointment = openAppointmentEditor,
                    onOpenEvent = { activeEditor = ActiveEditor.EventEditor(it) },
                    onOpenTransaction = { activeEditor = ActiveEditor.TransactionEditor(it) },
                    onOpenFinanceCards = openFinanceCards,
                    modifier = contentModifier
                )
                SubRoute.Settings -> SettingsScreen(
                    settings = settings,
                    personalization = personalizationSettings,
                    routineSettings = routineSettings,
                    categories = categories,
                    onBack = { subRoute = SubRoute.MoreMenu },
                    onSaveSettings = viewModel::saveSettings,
                    onSaveGreetingAndPresentation = viewModel::saveGreetingAndPresentation,
                    onOpenCustomizeDashboard = { subRoute = SubRoute.DashboardCustomization },
                    onOpenQuoteLibrary = { subRoute = SubRoute.QuoteLibrary },
                    onResetDashboardToDefault = viewModel::resetDashboardToDefault,
                    onEditDeclaredBalance = { activeEditor = ActiveEditor.DeclaredBalance },
                    onOpenRoutineAssistant = { tabIdx -> subRoute = SubRoute.RoutineAssistant(tabIdx) },
                    onOpenWidgets = { subRoute = SubRoute.WidgetsHub },
                    onOpenBackup = { subRoute = SubRoute.Backup },
                    onOpenDiagnostics = { subRoute = SubRoute.Diagnostics },
                    onSaveCategory = { id, name, hex -> viewModel.saveCategory(id, name, hex) },
                    onDeleteCategory = viewModel::deleteCategory,
                    modifier = contentModifier
                )
                SubRoute.Backup -> BackupScreen(
                    backupState = backupState,
                    onBack = { subRoute = SubRoute.MoreMenu },
                    onGenerateBackupJson = viewModel::generateBackupJson,
                    onNotifyExported = viewModel::notifyBackupExported,
                    onInspectBackupJson = viewModel::inspectBackupJson,
                    onConfirmRestore = viewModel::confirmRestoreBackup,
                    onDismissDialogs = viewModel::dismissBackupDialogs,
                    onLogError = viewModel::logUiError,
                    modifier = contentModifier
                )
                SubRoute.Diagnostics -> DiagnosticsScreen(
                    tableCounts = tableCounts,
                    errorLogs = errorLogs,
                    onBack = { subRoute = SubRoute.MoreMenu },
                    onClearErrorLogs = viewModel::clearErrorLogs,
                    onCopied = { viewModel.emitMessage("Relatório copiado para a área de transferência") },
                    modifier = contentModifier
                )
                SubRoute.None -> {
                    when (currentTab) {
                        MainTab.HOME -> HomeScreen(
                            snapshot = dashboard,
                            dailyBriefing = dailyBriefing,
                            onOpenRoutineAssistant = { subRoute = SubRoute.RoutineAssistant(0) },
                            onOpenCustomizeDashboard = { subRoute = SubRoute.DashboardCustomization },
                            onOpenQuoteLibrary = { subRoute = SubRoute.QuoteLibrary },
                            onOpenPlanning = { goalId -> subRoute = SubRoute.Planning(goalId) },
                            onOpenTask = { activeEditor = ActiveEditor.TaskEditor(it) },
                            onToggleTaskComplete = viewModel::toggleTaskCompletion,
                            onOpenAppointment = openAppointmentEditor,
                            onOpenEvent = { activeEditor = ActiveEditor.EventEditor(it) },
                            onOpenFinance = { currentTab = MainTab.FINANCE },
                            onOpenFinanceCards = openFinanceCards,
                            onOpenTransaction = { activeEditor = ActiveEditor.TransactionEditor(it) },
                            onQuickAdd = { activeEditor = ActiveEditor.QuickAddChooser },
                            modifier = contentModifier
                        )
                        MainTab.AGENDA -> AgendaScreen(
                            items = filteredAgendaItems,
                            categories = categories,
                            filter = agendaFilter,
                            onViewModeChange = viewModel::setAgendaViewMode,
                            onDateChange = viewModel::setAgendaSelectedDate,
                            onCategoryFilterChange = viewModel::setAgendaCategoryFilter,
                            onSearchQueryChange = viewModel::setAgendaSearchQuery,
                            onOpenAppointment = openAppointmentEditor,
                            onOpenTask = { activeEditor = ActiveEditor.TaskEditor(it) },
                            onOpenEvent = { activeEditor = ActiveEditor.EventEditor(it) },
                            onOpenTransaction = { activeEditor = ActiveEditor.TransactionEditor(it) },
                            onOpenCards = openFinanceCards,
                            onOpenProject = { activeEditor = ActiveEditor.ProjectEditor(it) },
                            onCreateAppointment = { openAppointmentEditor(null) },
                            modifier = contentModifier
                        )
                        MainTab.TASKS -> TasksAndProjectsScreen(
                            tasks = filteredTasks,
                            projects = allProjects,
                            filter = tasksFilter,
                            onTabChange = viewModel::setTasksTabMode,
                            onStatusFilterChange = viewModel::setTasksStatusFilter,
                            onPriorityFilterChange = viewModel::setTasksPriorityFilter,
                            onProjectFilterChange = viewModel::setTasksProjectFilter,
                            onSearchQueryChange = viewModel::setTasksSearchQuery,
                            onOpenTask = { activeEditor = ActiveEditor.TaskEditor(it) },
                            onToggleTaskComplete = viewModel::toggleTaskCompletion,
                            onUpdateTaskStatus = viewModel::updateTaskStatus,
                            onOpenProject = { activeEditor = ActiveEditor.ProjectEditor(it) },
                            onCreateTask = { activeEditor = ActiveEditor.TaskEditor(null) },
                            onCreateProject = { activeEditor = ActiveEditor.ProjectEditor(null) },
                            modifier = contentModifier
                        )
                        MainTab.FINANCE -> FinanceScreen(
                            summary = financeSummary,
                            transactions = filteredTransactions,
                            filter = financeFilter,
                            cardsWithStatements = cardsWithStatements,
                            installmentPurchases = installmentPurchases,
                            recurringRules = recurringRules,
                            budgets = budgets,
                            financialGoals = financialGoals,
                            loans = loans,
                            auditHistory = auditHistory,
                            onTabChange = viewModel::setFinanceTabMode,
                            onTypeFilterChange = viewModel::setFinanceTypeFilter,
                            onStatusFilterChange = viewModel::setFinanceStatusFilter,
                            onSearchQueryChange = viewModel::setFinanceSearchQuery,
                            onEditDeclaredBalance = { activeEditor = ActiveEditor.DeclaredBalance },
                            onCreateTransaction = { t ->
                                activeEditor = ActiveEditor.TransactionEditor(null, initialType = t)
                            },
                            onOpenTransaction = { txId ->
                                activeEditor = ActiveEditor.TransactionEditor(txId)
                            },
                            onToggleSettleTransaction = viewModel::settleTransaction,
                            onCreateCard = { activeEditor = ActiveEditor.CreditCardEditor(null) },
                            onEditCard = { card -> activeEditor = ActiveEditor.CreditCardEditor(card) },
                            onCloseStatement = viewModel::closeCreditCardStatement,
                            onPayStatement = viewModel::payCreditCardStatement,
                            onCreateInstallment = { activeEditor = ActiveEditor.InstallmentEditor },
                            onSettleRemainingInstallments = viewModel::settleAllRemainingInstallments,
                            onDeleteInstallmentPurchase = viewModel::deleteInstallmentPurchase,
                            onCreateRecurring = { activeEditor = ActiveEditor.RecurringEditor },
                            onToggleRecurringActive = viewModel::toggleRecurringRuleActive,
                            onDeleteRecurringRule = viewModel::deleteRecurringRule,
                            onCreateBudget = { activeEditor = ActiveEditor.BudgetEditor },
                            onDeleteBudget = viewModel::deleteBudget,
                            onCreateGoal = { activeEditor = ActiveEditor.FinancialGoalEditor(null) },
                            onEditGoal = { goal -> activeEditor = ActiveEditor.FinancialGoalEditor(goal) },
                            onUpdateGoalAmount = viewModel::updateFinancialGoalAmount,
                            onDeleteGoal = viewModel::deleteFinancialGoal,
                            onCreateLoan = { activeEditor = ActiveEditor.LoanEditor(null) },
                            onEditLoan = { loan -> activeEditor = ActiveEditor.LoanEditor(loan) },
                            onPayLoanInstallment = { loan -> activeEditor = ActiveEditor.LoanPayment(loan) },
                            onDeleteLoan = viewModel::deleteLoan,
                            onGenerateCsv = viewModel::generateFinancialCsv,
                            onCsvExported = viewModel::notifyCsvExported,
                            onLogError = viewModel::logUiError,
                            modifier = contentModifier
                        )
                        MainTab.EVENTS -> EventsScreen(
                            events = allEvents,
                            onOpenEvent = { activeEditor = ActiveEditor.EventEditor(it) },
                            onCreateEvent = { activeEditor = ActiveEditor.EventEditor(null) },
                            modifier = contentModifier
                        )
                    }
                }
            }
        }

        when (val editor = activeEditor) {
            ActiveEditor.None -> Unit
            ActiveEditor.QuickAddChooser -> QuickAddChooserSheet(
                onDismiss = { activeEditor = ActiveEditor.None },
                onSelectNewTask = { activeEditor = ActiveEditor.TaskEditor(null) },
                onSelectNewAppointment = { openAppointmentEditor(null) },
                onSelectNewProject = { activeEditor = ActiveEditor.ProjectEditor(null) },
                onSelectNewEvent = { activeEditor = ActiveEditor.EventEditor(null) },
                onSelectNewTransaction = {
                    activeEditor = ActiveEditor.TransactionEditor(null, initialType = TransactionType.EXPENSE)
                }
            )
            is ActiveEditor.TaskEditor -> {
                val existing = editor.taskId?.let { id ->
                    allTasks.firstOrNull { it.task.id == id }
                }
                TaskEditorSheet(
                    existingTask = existing,
                    initialEventId = editor.initialEventId,
                    projects = allProjects,
                    categories = categories,
                    events = allEvents,
                    goals = allPersonalGoals,
                    onDismiss = { activeEditor = ActiveEditor.None },
                    onSave = { id, title, desc, projId, catId, evId, goalId, prio, st, dueAt, hasTime, notes, subs, reminderMins ->
                        viewModel.saveTask(
                            id = id,
                            title = title,
                            description = desc,
                            projectId = projId,
                            categoryId = catId,
                            eventId = evId,
                            goalId = goalId,
                            priority = prio,
                            status = st,
                            dueAt = dueAt,
                            hasDueTime = hasTime,
                            notes = notes,
                            subtaskDrafts = subs,
                            reminderMinutesBefore = reminderMins,
                            onSaved = { activeEditor = ActiveEditor.None }
                        )
                    },
                    onToggleComplete = viewModel::toggleTaskCompletion,
                    onToggleSubtask = viewModel::toggleSubtask,
                    onOpenEvent = { evId -> activeEditor = ActiveEditor.EventEditor(evId) },
                    onDelete = { taskId ->
                        viewModel.deleteTask(taskId) { activeEditor = ActiveEditor.None }
                    }
                )
            }
            is ActiveEditor.AppointmentEditor -> {
                AppointmentEditorSheet(
                    existingAppointment = loadedAppointment,
                    categories = categories,
                    initialDate = agendaFilter.selectedDate,
                    onDismiss = { activeEditor = ActiveEditor.None },
                    onSave = { id, title, desc, catId, startAt, endAt, isAllDay, location, link, reminderMins ->
                        viewModel.saveAppointment(
                            id = id,
                            title = title,
                            description = desc,
                            categoryId = catId,
                            startAt = startAt,
                            endAt = endAt,
                            isAllDay = isAllDay,
                            location = location,
                            link = link,
                            reminderMinutesBefore = reminderMins,
                            onSaved = { activeEditor = ActiveEditor.None }
                        )
                    },
                    onDelete = { apptId ->
                        viewModel.deleteAppointment(apptId) { activeEditor = ActiveEditor.None }
                    },
                    onError = viewModel::logUiError
                )
            }
            is ActiveEditor.ProjectEditor -> {
                val existing = editor.projectId?.let { id ->
                    allProjects.firstOrNull { it.project.id == id }
                }
                ProjectEditorSheet(
                    existingProject = existing,
                    goals = allPersonalGoals,
                    countLinkedTasks = viewModel::countTasksInProject,
                    onDismiss = { activeEditor = ActiveEditor.None },
                    onSave = { id, name, desc, deadline, status, prio, goalId ->
                        viewModel.saveProject(
                            id = id,
                            name = name,
                            description = desc,
                            deadlineAt = deadline,
                            status = status,
                            priority = prio,
                            goalId = goalId,
                            onSaved = { activeEditor = ActiveEditor.None }
                        )
                    },
                    onDelete = { projectId ->
                        viewModel.deleteProject(projectId) { activeEditor = ActiveEditor.None }
                    }
                )
            }
            is ActiveEditor.EventEditor -> {
                val existing = editor.eventId?.let { id ->
                    allEvents.firstOrNull { it.event.id == id }
                }
                EventEditorSheet(
                    existingEvent = existing,
                    categories = categories,
                    creditCards = cardsWithStatements.map { it.card },
                    onDismiss = { activeEditor = ActiveEditor.None },
                    onSave = { id, name, desc, catId, startAt, endAt, allDay, officialUrl, ticketUrl, locName, addr, city, st, notes, evStatus, tStatus, tPrice, tQty, tFees, tPurchaseDate, tDueDate, payMethod, cardId, instCount, reminderMins ->
                        viewModel.saveEvent(
                            id = id,
                            name = name,
                            description = desc,
                            categoryId = catId,
                            startAt = startAt,
                            endAt = endAt,
                            isAllDay = allDay,
                            officialLink = officialUrl,
                            ticketLink = ticketUrl,
                            locationName = locName,
                            address = addr,
                            city = city,
                            state = st,
                            notes = notes,
                            eventStatus = evStatus,
                            ticketStatus = tStatus,
                            ticketPriceCents = tPrice,
                            ticketQuantity = tQty,
                            ticketFeesCents = tFees,
                            ticketPurchaseDate = tPurchaseDate,
                            ticketDueDate = tDueDate,
                            paymentMethod = payMethod,
                            creditCardId = cardId,
                            installmentsCount = instCount,
                            reminderMinutesBefore = reminderMins,
                            onSaved = { activeEditor = ActiveEditor.None }
                        )
                    },
                    onDelete = { eventId ->
                        viewModel.deleteEvent(eventId) { activeEditor = ActiveEditor.None }
                    },
                    onAddLinkedTask = { evId ->
                        activeEditor = ActiveEditor.TaskEditor(taskId = null, initialEventId = evId)
                    },
                    onOpenTask = { taskId ->
                        activeEditor = ActiveEditor.TaskEditor(taskId = taskId)
                    },
                    onAddAdditionalExpense = { evId ->
                        activeEditor = ActiveEditor.TransactionEditor(
                            transactionId = null,
                            initialType = TransactionType.EXPENSE,
                            initialEventId = evId
                        )
                    },
                    onOpenTransaction = { txId ->
                        activeEditor = ActiveEditor.TransactionEditor(transactionId = txId)
                    },
                    onError = viewModel::logUiError
                )
            }
            is ActiveEditor.TransactionEditor -> {
                val existing = editor.transactionId?.let { id ->
                    allTransactions.firstOrNull { it.transaction.id == id }
                }
                TransactionEditorSheet(
                    existing = existing,
                    initialType = editor.initialType,
                    initialEventId = editor.initialEventId,
                    categories = categories,
                    projects = allProjects,
                    events = allEvents,
                    creditCards = cardsWithStatements.map { it.card },
                    onDismiss = { activeEditor = ActiveEditor.None },
                    onSave = { id, type, desc, amount, catId, st, payMethod, occurredAt, dueAt, settledAt, projId, evId, evCostType, cardId, notes, reminderMins ->
                        viewModel.saveTransaction(
                            id = id,
                            type = type,
                            description = desc,
                            amountCents = amount,
                            categoryId = catId,
                            status = st,
                            paymentMethod = payMethod,
                            occurredAt = occurredAt,
                            dueAt = dueAt,
                            settledAt = settledAt,
                            projectId = projId,
                            eventId = evId,
                            eventCostType = evCostType,
                            creditCardId = cardId,
                            notes = notes,
                            reminderMinutesBefore = reminderMins,
                            onSaved = { activeEditor = ActiveEditor.None }
                        )
                    },
                    onDelete = { txId ->
                        viewModel.deleteTransaction(txId) { activeEditor = ActiveEditor.None }
                    }
                )
            }
            is ActiveEditor.CreditCardEditor -> {
                CreditCardEditorSheet(
                    existing = editor.card,
                    onDismiss = { activeEditor = ActiveEditor.None },
                    onSave = { id, name, inst, totalLimit, availLimit, closing, due, notes ->
                        viewModel.saveCreditCard(
                            id = id,
                            name = name,
                            institution = inst,
                            totalLimitCents = totalLimit,
                            availableLimitCents = availLimit,
                            closingDay = closing,
                            dueDay = due,
                            notes = notes,
                            onSaved = { activeEditor = ActiveEditor.None }
                        )
                    },
                    onDelete = { cardId ->
                        viewModel.deleteCreditCard(cardId)
                        activeEditor = ActiveEditor.None
                    }
                )
            }
            ActiveEditor.InstallmentEditor -> {
                InstallmentPurchaseEditorSheet(
                    creditCards = cardsWithStatements.map { it.card },
                    categories = categories,
                    onDismiss = { activeEditor = ActiveEditor.None },
                    onSave = { desc, totalCents, count, firstDue, cardId, catId, notes ->
                        viewModel.createInstallmentPurchase(
                            description = desc,
                            totalAmountCents = totalCents,
                            installmentsCount = count,
                            firstDueDate = firstDue,
                            creditCardId = cardId,
                            categoryId = catId,
                            notes = notes,
                            onSaved = { activeEditor = ActiveEditor.None }
                        )
                    }
                )
            }
            ActiveEditor.RecurringEditor -> {
                RecurringRuleEditorSheet(
                    categories = categories,
                    creditCards = cardsWithStatements.map { it.card },
                    onDismiss = { activeEditor = ActiveEditor.None },
                    onSave = { title, type, amount, catId, payMethod, cardId, freq, customDays, startDate, maxOcc, notes ->
                        viewModel.saveRecurringRule(
                            title = title,
                            type = type,
                            amountCents = amount,
                            categoryId = catId,
                            paymentMethod = payMethod,
                            creditCardId = cardId,
                            frequency = freq,
                            customIntervalDays = customDays,
                            startDate = startDate,
                            maxOccurrences = maxOcc,
                            notes = notes,
                            onSaved = { activeEditor = ActiveEditor.None }
                        )
                    }
                )
            }
            is ActiveEditor.LoanEditor -> {
                LoanEditorSheet(
                    existing = editor.loan,
                    onDismiss = { activeEditor = ActiveEditor.None },
                    onSave = { id, creditor, orig, contr, totalInst, instAmount, paidInst, nextDue, remBal, finalDate, notes ->
                        viewModel.saveLoan(
                            id = id,
                            creditor = creditor,
                            originalAmountCents = orig,
                            contractedAmountCents = contr,
                            installmentsTotal = totalInst,
                            installmentAmountCents = instAmount,
                            paidInstallments = paidInst,
                            nextDueAt = nextDue,
                            remainingBalanceCents = remBal,
                            finalDateAt = finalDate,
                            notes = notes,
                            onSaved = { activeEditor = ActiveEditor.None }
                        )
                    }
                )
            }
            is ActiveEditor.LoanPayment -> {
                LoanPaymentDialog(
                    loan = editor.loan,
                    onDismiss = { activeEditor = ActiveEditor.None },
                    onConfirm = { newBalance ->
                        viewModel.registerLoanInstallmentPaid(editor.loan, newBalance)
                        activeEditor = ActiveEditor.None
                    }
                )
            }
            ActiveEditor.DeclaredBalance -> {
                DeclaredBalanceDialog(
                    currentBalanceCents = settings.declaredBalanceCents,
                    onDismiss = { activeEditor = ActiveEditor.None },
                    onSave = { newBalance ->
                        viewModel.updateDeclaredBalance(newBalance)
                        activeEditor = ActiveEditor.None
                    }
                )
            }
            ActiveEditor.BudgetEditor -> {
                BudgetEditorDialog(
                    categories = categories,
                    onDismiss = { activeEditor = ActiveEditor.None },
                    onSave = { catId, limitCents ->
                        viewModel.saveBudget(catId, limitCents) {
                            activeEditor = ActiveEditor.None
                        }
                    }
                )
            }
            is ActiveEditor.FinancialGoalEditor -> {
                FinancialGoalEditorDialog(
                    existing = editor.goal,
                    onDismiss = { activeEditor = ActiveEditor.None },
                    onSave = { id, name, target, current, notes ->
                        viewModel.saveFinancialGoal(
                            id = id,
                            name = name,
                            targetAmountCents = target,
                            currentAmountCents = current,
                            deadlineAt = editor.goal?.deadlineAt,
                            categoryId = editor.goal?.categoryId,
                            notes = notes,
                            onSaved = { activeEditor = ActiveEditor.None }
                        )
                    }
                )
            }
        }
    }
}
