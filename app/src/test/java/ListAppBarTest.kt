import android.os.Build
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.singlepoint.todo.data.ToDoDao
import com.singlepoint.todo.data.models.Priority
import com.singlepoint.todo.data.models.ToDoTask
import com.singlepoint.todo.data.repository.DataStoreRepository
import com.singlepoint.todo.data.repository.ToDoRepository
import com.singlepoint.todo.ui.screens.list.DefaultListAppBar
import com.singlepoint.todo.ui.viewmodels.SharedViewModel
import com.singlepoint.todo.util.Action
import com.singlepoint.todo.util.SearchAppBarState
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.mockStatic
import org.mockito.Mockito.`when`

class ListAppBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        // Set up a mock for android.os.Build
        mockStatic(Build::class.java)
        `when`(Build.FINGERPRINT).thenReturn("mockFingerprint")
        `when`(Build.MODEL).thenReturn("mockModel")
    }

    @Test
    fun testAppBar() {

        // Mock the ToDoDao
        val mockToDoDao = mock(ToDoDao::class.java)
        val mockTaskList = listOf(ToDoTask(id = 1, title = "Test Task", description = "Test Description", priority = Priority.HIGH))
        `when`(mockToDoDao.getAllTasks()).thenReturn(flowOf(mockTaskList))

        //mock the dataStore repository]
        val mockDataStoreRepository = mock(DataStoreRepository::class.java)
        `when`(mockDataStoreRepository.readSortState).thenReturn(flowOf(Priority.HIGH.name))

        // Create an instance of the MyRepository with the mock ToDoDao
        val toDoRepository = ToDoRepository(mockToDoDao)

        // Create an instance of the SharedViewModel with the repository
        val sharedViewModel = SharedViewModel(toDoRepository, mockDataStoreRepository)



        composeRule.setContent {
            DefaultListAppBar(
                onSearchClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                },
                onSortClicked = {
                    sharedViewModel.persistSortState(it)
                },
                onDeleteAllConfirmed = {
                    sharedViewModel.action.value = Action.DELETE_ALL
                }
            )
        }

        //composeRule.onNodeWithContentDescription("Search").assertIsDisplayed()
        composeRule.onNodeWithText(text = "Tasks").assertIsDisplayed()
    }
}