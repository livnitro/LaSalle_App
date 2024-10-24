package com.example.lasalle_app.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lasalle_app.models.User
import com.example.lasalle_app.ui.theme.LaSalle_AppTheme
import com.example.lasalle_app.ui.theme.PaymentComplete
import com.example.lasalle_app.ui.theme.PaymentPending
import com.example.lasalle_app.utils.users
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

@Composable
fun Payments(innerPadding: PaddingValues, user: User) {
    DisplayInfo(innerPadding = innerPadding) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "PAGOS",
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )

            user.payments.forEach { payment ->
                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (payment.isPaid) PaymentComplete
                            else PaymentPending
                        )
                        .padding(16.dp),
                ) {
                    Text(
                        text = "${payment.month}: ${payment.amount}",
                        fontSize = 16.sp,
                    )
                }
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
fun PaymentsPreview() {
    LaSalle_AppTheme {
        Payments(innerPadding = PaddingValues(0.dp), users[0])
    }
}
