package com.example.aiagent.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailScreen(
    transaction: Transaction,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transaction Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF3A48E8),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Transaction Icon and Type
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF0F0F8))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = transaction.icon,
                    contentDescription = transaction.type,
                    tint = if (transaction.amount < 0) Color(0xFFE57373) else Color(0xFF81C784),
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Transaction Type
            Text(
                text = transaction.type,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Transaction Amount
            Text(
                text = (if (transaction.amount < 0) "-" else "+") + "$" + "%,.2f".format(kotlin.math.abs(transaction.amount)),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = if (transaction.amount < 0) Color(0xFFD32F2F) else Color(0xFF388E3C)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Transaction Details Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Transaction ID
                    DetailItem(
                        label = "Transaction ID",
                        value = transaction.id,
                        icon = Icons.Filled.Info
                    )

                    Divider(modifier = Modifier.padding(vertical = 12.dp))

                    // Transaction Date (simulated)
                    val dateFormat = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
                    val date = dateFormat.format(Date())
                    DetailItem(
                        label = "Date",
                        value = date,
                        icon = Icons.Filled.DateRange
                    )

                    Divider(modifier = Modifier.padding(vertical = 12.dp))

                    // Transaction Status (simulated)
                    DetailItem(
                        label = "Status",
                        value = "Completed",
                        icon = Icons.Filled.Info
                    )
                }
            }
        }
    }
}

@Composable
fun DetailItem(
    label: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color(0xFF3A48E8),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = label,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// Preview function
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun TransactionDetailScreenPreview() {
    val sampleTransaction = Transaction(
        id = "1",
        type = "Spending",
        amount = -500.0,
        icon = Icons.Filled.Info
    )

    MaterialTheme {
        TransactionDetailScreen(
            transaction = sampleTransaction,
            onBackClick = {}
        )
    }
}
