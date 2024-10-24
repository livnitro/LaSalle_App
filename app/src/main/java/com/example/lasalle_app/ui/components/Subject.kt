package com.example.lasalle_app.ui.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.lasalle_app.models.Subject
import com.example.lasalle_app.ui.theme.LaSalle_AppTheme
import com.example.lasalle_app.utils.users

@SuppressLint("DefaultLocale")
@Composable
fun Subject(subject : Subject, padding : PaddingValues, onClick : (Subject) -> Unit) {
    val promedioAcumulado : Double = (subject.grade1 + subject.grade2 + (3*subject.grade3))/5

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(padding)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.tertiary)
            .clickable {
                onClick(subject)
            },
        contentAlignment = Alignment.CenterStart
    ){
        Column (
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
        ){
            Text(
                text = subject.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(bottom = 10.dp),
            )
            Text(
                text = "Promedio: ${String.format("%.2f", promedioAcumulado)}",
                fontSize = 12.sp
            )

            Box (
                modifier = Modifier
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(20.dp)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "MÁS INFORMACIÓN",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 14.sp
                )
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
fun SubjectPreview() {
    LaSalle_AppTheme {
        Subject(users[0].major.subjects[0], padding = PaddingValues(top = 20.dp, start = 20.dp, end = 20.dp)){}
    }
}