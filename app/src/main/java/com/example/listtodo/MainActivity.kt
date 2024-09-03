package com.example.listtodo

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.listtodo.ui.theme.ListToDoTheme
import java.util.Date
import java.util.Locale

data class Task(
    val title: String,
    val description: String,
    val date: Date
)

class TodoViewModel : ViewModel() {
    private val _tasks = MutableLiveData<List<Task>>(emptyList())
    val tasks = _tasks

    fun addTask(title: String, description: String) {
        val updatedTasks = _tasks.value.orEmpty().toMutableList().apply {
            add(Task(title, description, Date()))
        }
        _tasks.value = updatedTasks
    }
}

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListToDoTheme {
                val viewModel = TodoViewModel()
                Scaffold(
                    bottomBar = { BottomBar() }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .background(Color.White)
                    ) {
                        TopBar()
                        TodoPage(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomAppBar(
            modifier = Modifier
                .clip(RoundedCornerShape(370.dp))
                .height(80.dp)
                .width(300.dp),
            containerColor = Color(0xFFDCEDF4),
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
            }
        }
    }
}
@Composable
fun TaskPage(viewModel: TodoViewModel, modifier: Modifier = Modifier) {
    var isExpanded by remember { mutableStateOf(false) }
    var iconState by remember { mutableStateOf(Icons.Rounded.KeyboardArrowUp) }
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }

    Row(
        modifier = modifier
            .padding(20.dp)
            .animateContentSize()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary)
                .clickable {
                    isExpanded = !isExpanded
                    iconState = if (isExpanded) {
                        Icons.Rounded.KeyboardArrowUp
                    } else {
                        Icons.Rounded.KeyboardArrowDown
                    }
                }
        ) {
            Icon(
                modifier = Modifier.size(25.dp),
                imageVector = iconState,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSecondary
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = "Add Task",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontWeight = FontWeight.Bold
        )
    }

    Spacer(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onSecondaryContainer)
    )

    if (isExpanded) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))

        ) {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = taskName,
                onValueChange = { taskName = it },
                label = { Text(text = "Task Name") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = taskDescription,
                onValueChange = { taskDescription = it },
                label = { Text(text = "Task Description") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(onClick = {
                viewModel.addTask(taskName, taskDescription)
                taskName = ""
                taskDescription = ""
                isExpanded = false
            }) {
                Text(text = "Add Task")
            }
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Add icons or buttons for the top bar here
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoPage(viewModel: TodoViewModel) {
    val tasks by viewModel.tasks.observeAsState(emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        TaskPage(viewModel = viewModel)
    }

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(tasks) { task ->
            TaskItem(item = task)
        }
    }
}

@Composable
fun TaskItem(item: Task) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(16.dp)
    ) {
        Text(
            text = SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.ENGLISH).format(item.date),
            fontSize = 12.sp,
            color = Color.LightGray
        )
        Text(text = item.title, fontSize = 20.sp, color = Color.White)

        Text(text = item.description, fontSize = 16.sp, color = Color.White)
    }
}
