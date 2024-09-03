package com.example.listtodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.listtodo.ui.theme.ListToDoTheme

data class BottomNav(
    val title: String,
    val selectedIcon: Int? = null,
    val unselectedIcon: Int? = null,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

data class ToDo(
    var name: String,
    var isCompleted: Boolean = false,
    var des: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListToDoTheme {
                val bottomNavItems = listOf(
                    BottomNav(
                        title = "Home",
                        selectedIcon = R.drawable.home__1_,
                        unselectedIcon = R.drawable.home__2_,
                        hasNews = false
                    ),
                    BottomNav(
                        title = "Add",
                        selectedIcon = R.drawable.plus,
                        unselectedIcon = R.drawable.plus__1_,
                        hasNews = false,
                        badgeCount = 45
                    ),
//                    BottomNav(
//                        title = "Settings",
//                        selectedIcon = Icons.Filled.Settings,
//                        unselectedIcon = Icons.Outlined.Settings,
//                        hasNews = true
//                    )
                )
                Scaffold(
                    bottomBar = {
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
                                    Image(
                                        painter = painterResource(id = R.drawable.home__1_),
                                        contentDescription = "",
                                        modifier = Modifier.size(90.dp)
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.plus),
                                        contentDescription = "",
                                        modifier = Modifier.size(80.dp)
                                    )
                                    Image(
                                        painter = painterResource(id = R.drawable.calendar),
                                        contentDescription = "",
                                        modifier = Modifier.size(80.dp)
                                    )
                                }
                            }
                        }
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .background(Color(0xFFF5F5F5))
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(20.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profile_user__1_),
                                contentDescription = "",
                                Modifier.size(40.dp)
                            )

                            Image(
                                painter = painterResource(id = R.drawable.notification__1_),
                                contentDescription = "",
                                Modifier.size(30.dp)
                            )
                        }
                        Column(

                        ) {

                        }
                    }
                }
            }
        }
    }
}
