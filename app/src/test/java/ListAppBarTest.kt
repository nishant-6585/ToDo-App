import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.singlepoint.todo.data.models.Priority
import com.singlepoint.todo.data.models.ToDoTask
import com.singlepoint.todo.presentaion.screens.list.DefaultListAppBar
import com.singlepoint.todo.presentaion.screens.list.SearchAction
import com.singlepoint.todo.presentaion.viewmodels.SharedViewModel
import com.singlepoint.todo.room.ToDoDao
import com.singlepoint.todo.room.repository.DataStoreRepository
import com.singlepoint.todo.room.repository.ToDoRepository
import com.singlepoint.todo.util.Action
import com.singlepoint.todo.util.SearchAppBarState
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ListAppBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testDefaultListAppBar() {

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
        composeRule.onNodeWithText(text = "Tasks").assertTextEquals("Tasks")
    }

    @Test
    fun testSearchAction() {
        composeRule.setContent {
           SearchAction {

           }
        }
        composeRule.onNodeWithContentDescription("Search Tasks").assertIsDisplayed()
    }
}