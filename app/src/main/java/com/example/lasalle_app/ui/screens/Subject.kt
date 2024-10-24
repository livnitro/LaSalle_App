package com.example.lasalle_app.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lasalle_app.models.Subject
import com.example.lasalle_app.ui.theme.LaSalle_AppTheme
import com.example.lasalle_app.utils.users
import androidx.compose.ui.Alignment


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Subject(innerPadding: PaddingValues, subjectId: Int) {
    val user = users[0]
    val subject = user.major.subjects.first { it.id == subjectId }

    DisplayInfo(innerPadding = innerPadding) {
        Text(
            text = subject.name.uppercase(),
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Calificaciones",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 20.dp, top = 40.dp),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 18.sp,
        )

        GradeTable(subject = subject)
    }
}

@Composable
fun GradeTable(subject: Subject) {
    Column(modifier = Modifier.padding(20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Parcial",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Calificación",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Estado",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        GradeRow(parcial = "Primer Parcial", grade = subject.grade1)
        GradeRow(parcial = "Segundo Parcial", grade = subject.grade2)
        GradeRow(parcial = "Tercer Parcial", grade = subject.grade3)
    }
}

@Composable
fun GradeRow(parcial: String, grade: Double) {
    val icon = if (grade >= 6) Icons.Default.CheckCircle else Icons.Default.Warning
    val iconColor = if (grade >= 6) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = parcial,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = grade.toString(),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically) // Alinea verticalmente
                .fillMaxWidth()
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SubjectPreview() {
    LaSalle_AppTheme {
        Subject(innerPadding = PaddingValues(0.dp), 1)
    }
}
