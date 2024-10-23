package com.example.lasalle_app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lasalleapp.models.BottomNavigationItem
import com.example.lasalleapp.ui.screens.CalendarScreen
import com.example.lasalleapp.ui.screens.ChangePasswordScreen
import com.example.lasalleapp.ui.screens.ChangeThemeScreen
import com.example.lasalleapp.ui.screens.GradesScreen
import com.example.lasalleapp.ui.screens.HomeScreen
import com.example.lasalleapp.ui.screens.NewsDetailsScreen
import com.example.lasalleapp.ui.screens.PaymentsScreen
import com.example.lasalleapp.ui.screens.SettingsScreen
import com.example.lasalleapp.ui.screens.SubjectScreen
import com.example.lasalleapp.ui.theme.LaSalle_AppTheme
import com.example.lasalleapp.utils.Screens
import com.example.lasalleapp.utils.users
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var selectedItem by rememberSaveable {
                mutableIntStateOf(0)
            }
            LaSalleApp_Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AnimatedNavigationBar(
                            selectedIndex = selectedItem,
                            modifier = Modifier.height(100.dp),
                            barColor = MaterialTheme.colorScheme.primary,
                            cornerRadius = shapeCornerRadius(cornerRadius = 34.dp)
                        ) {
                            BottomNavigationItem.items.forEachIndexed { index, bottomNavigationItem ->
                                Column (
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            selectedItem = index
                                            navController.navigate(bottomNavigationItem.route)
                                        },
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ){
                                    Icon(
                                        imageVector = bottomNavigationItem.icon,
                                        contentDescription = "home",
                                        tint = if(selectedItem == index) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                                        modifier = Modifier.size(26.dp)
                                    )
                                    Text(
                                        bottomNavigationItem.title,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = if(selectedItem == index) MaterialTheme.colorScheme.onPrimary
                                        else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = Screens.Home.route){
                        composable(route = Screens.Home.route){
                            HomeScreen(innerPadding = innerPadding, navController = navController)
                        }
                        composable(route = Screens.Grades.route){
                            GradesScreen(innerPadding = innerPadding, navController = navController)
                        }
                        composable(route = Screens.Calendar.route){
                            CalendarScreen(innerPadding = innerPadding)
                        }
                        composable(route = Screens.Settings.route){
                            SettingsScreen(innerPadding = innerPadding, navController = navController)
                        }
                        composable(
                            route = Screens.NewsDetail.route+"/{newsId}",
                            arguments = listOf(
                                navArgument("newsId") {
                                    type= NavType.IntType
                                }
                            )
                        ){
                            val newsId = it.arguments?.getInt("newsId") ?: 0
                            NewsDetailsScreen(innerPadding = innerPadding, newsId = newsId)
                        }
                        composable(route = Screens.ChangePassword.route) {
                            ChangePasswordScreen(innerPadding = innerPadding)
                        }
                        composable(route = Screens.ChangeTheme.route) {
                            ChangeThemeScreen(innerPadding = innerPadding)
                        }
                        composable(
                            route = Screens.Subject.route+"/{subjectId}",
                            arguments = listOf(
                                navArgument("subjectId"){
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            val subjectId = it.arguments?.getInt("subjectId") ?: 0
                            SubjectScreen(innerPadding = innerPadding, subjectId = subjectId)
                        }
                        composable(route = Screens.Payments.route) {
                            PaymentsScreen(innerPadding = innerPadding, user = users[0])
                        }
                    }
                }
            }
        }
    }
}