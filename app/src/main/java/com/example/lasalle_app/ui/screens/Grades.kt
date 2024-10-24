package com.example.lasalle_app.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.lasalle_app.models.User
import com.example.lasalle_app.ui.components.Subject
import com.example.lasalle_app.ui.theme.LaSalle_AppTheme
import com.example.lasalle_app.utils.Screens
import com.example.lasalle_app.utils.users

fun calcuarPromedio(user : User): Double {
    var promedio : Double = 0.0
    user.major.subjects.forEach { materia ->
        val promedioMateria = (materia.grade1 + materia.grade2 + (3*materia.grade3))/5
        promedio += promedioMateria
    }
    promedio /= user.major.subjects.count()
    return promedio
}

@SuppressLint("DefaultLocale")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Grades (innerPadding: PaddingValues, navController : NavController){
    val user = users[0]
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        item {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.TopStart
            ) {
                Row (
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box {
                        AsyncImage(
                            model = user.img,
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .height(70.dp)
                                .width(70.dp)
                                .clip(RoundedCornerShape(100.dp))
                                .background(MaterialTheme.colorScheme.onPrimary)
                        )
                    }
                    Text(
                        text = "${user.name} ${user.lastName}",
                        modifier = Modifier.padding(start = 20.dp),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
        item {
            Text(
                text = user.major.name.uppercase(),
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        item {
            Text(
                text = "Semestre Actual: ${user.semester}",
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        item {
            Text(
                text = "Promedio Acumulado Actual: ${String.format("%.2f", calcuarPromedio(user))}",
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        item {
            Text(
                text = "Materias del curso",
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, bottom = 20.dp),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        }

        items(user.major.subjects){ subject ->
            Subject(subject = subject, padding = PaddingValues(bottom = 20.dp, start = 20.dp, end = 20.dp)){
                navController.navigate(Screens.Subject.route+"/${subject.id}")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GradesPreview() {
    LaSalle_AppTheme {
        val navController : NavController = rememberNavController()
        Grades(innerPadding = PaddingValues(0.dp), navController = navController)
    }
}