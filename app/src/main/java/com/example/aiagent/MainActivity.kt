package com.example.aiagent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aiagent.ui.FinanceScreen
import com.example.aiagent.ui.FinanceViewModel
import com.example.aiagent.ui.TransactionDetailScreen
import com.example.aiagent.ui.theme.AiAgentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AiAgentTheme {
                val viewModel = remember { FinanceViewModel() }
                MainScreen(viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: FinanceViewModel) {
    // Collect the selected transaction state
    val selectedTransaction by viewModel.selectedTransaction.collectAsState()

    // Show the appropriate screen based on whether a transaction is selected
    if (selectedTransaction != null) {
        // Show transaction detail screen
        TransactionDetailScreen(
            transaction = selectedTransaction!!,
            onBackClick = { viewModel.clearSelectedTransaction() }
        )
    } else {
        // Show finance screen
        FinanceScreen(viewModel = viewModel)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AiAgentTheme {
        Greeting("Android")
    }
}
