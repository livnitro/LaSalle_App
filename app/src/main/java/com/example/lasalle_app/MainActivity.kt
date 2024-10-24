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
import com.example.lasalle_app.models.BottomNav
import com.example.lasalle_app.ui.screens.Calendar
import com.example.lasalle_app.ui.screens.ChangePassword
import com.example.lasalle_app.ui.screens.ChangeTheme
import com.example.lasalle_app.ui.screens.Grades
import com.example.lasalle_app.ui.screens.Home
import com.example.lasalle_app.ui.screens.NewsDetails
import com.example.lasalle_app.ui.screens.Payments
import com.example.lasalle_app.ui.screens.Settings
import com.example.lasalle_app.ui.screens.Subject
import com.example.lasalle_app.ui.theme.LaSalle_AppTheme
import com.example.lasalle_app.utils.Screens
import com.example.lasalle_app.utils.users
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
            LaSalle_AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AnimatedNavigationBar(
                            selectedIndex = selectedItem,
                            modifier = Modifier.height(100.dp),
                            barColor = MaterialTheme.colorScheme.primary,
                            cornerRadius = shapeCornerRadius(cornerRadius = 34.dp)
                        ) {
                            BottomNav.items.forEachIndexed { index, BottomNav ->
                                Column (
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            selectedItem = index
                                            navController.navigate(BottomNav.route)
                                        },
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ){
                                    Icon(
                                        imageVector = BottomNav.icon,
                                        contentDescription = "home",
                                        tint = if(selectedItem == index) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                                        modifier = Modifier.size(26.dp)
                                    )
                                    Text(
                                        BottomNav.title,
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
                            Home(innerPadding = innerPadding, navController = navController)
                        }
                        composable(route = Screens.Grades.route){
                            Grades(innerPadding = innerPadding, navController = navController)
                        }
                        composable(route = Screens.Calendar.route){
                            Calendar(innerPadding = innerPadding)
                        }
                        composable(route = Screens.Settings.route){
                            Settings(innerPadding = innerPadding, navController = navController)
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
                            NewsDetails(innerPadding = innerPadding, newsId = newsId)
                        }
                        composable(route = Screens.ChangePassword.route) {
                            ChangePassword(innerPadding = innerPadding)
                        }
                        composable(route = Screens.ChangeTheme.route) {
                            ChangeTheme(innerPadding = innerPadding)
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
                            Subject(innerPadding = innerPadding, subjectId = subjectId)
                        }
                        composable(route = Screens.Payments.route) {
                            Payments(innerPadding = innerPadding, user = users[0])
                        }
                    }
                }
            }
        }
    }
}