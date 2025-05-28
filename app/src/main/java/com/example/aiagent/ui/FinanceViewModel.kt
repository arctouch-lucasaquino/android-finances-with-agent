package com.example.aiagent.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Savings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Moved Transaction data class here
data class Transaction(
    val id: String,
    val type: String, // "Spending", "Income", "Bills", "Savings"
    val amount: Double,
    val icon: ImageVector // Use appropriate icons
)

class FinanceViewModel : ViewModel() {
    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions.asStateFlow()

    // Other states like searchQuery, selectedCurrency, balance can also be managed here
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCurrency = MutableStateFlow("US Dollar")
    val selectedCurrency: StateFlow<String> = _selectedCurrency.asStateFlow()

    private val _availableBalance = MutableStateFlow("$20,000") // Example initial value
    val availableBalance: StateFlow<String> = _availableBalance.asStateFlow()

    // Selected transaction for detail view
    private val _selectedTransaction = MutableStateFlow<Transaction?>(null)
    val selectedTransaction: StateFlow<Transaction?> = _selectedTransaction.asStateFlow()


    init {
        loadTransactions()
        // In a real app, balance would likely be calculated or fetched
    }

    private fun loadTransactions() {
        // In a real app, you would fetch this from a repository or data source
        _transactions.value = getSampleTransactionsData()
    }

    // Moved sample data generation here
    private fun getSampleTransactionsData(): List<Transaction> {
        return listOf(
            Transaction("1", "Spending", -500.0, Icons.Filled.CreditCard),
            Transaction("2", "Income", 3000.0, Icons.Filled.AccountBalanceWallet),
            Transaction("3", "Bills", -800.0, Icons.Filled.ReceiptLong),
            Transaction("4", "Savings", 1000.0, Icons.Filled.Savings),
            // 10 more transactions added
            Transaction("5", "Spending", -120.0, Icons.Filled.CreditCard),
            Transaction("6", "Spending", -75.50, Icons.Filled.CreditCard),
            Transaction("7", "Income", 2500.0, Icons.Filled.AccountBalanceWallet),
            Transaction("8", "Bills", -150.0, Icons.Filled.ReceiptLong),
            Transaction("9", "Savings", 500.0, Icons.Filled.Savings),
            Transaction("10", "Bills", -200.0, Icons.Filled.ReceiptLong),
            Transaction("11", "Spending", -350.0, Icons.Filled.CreditCard),
            Transaction("12", "Income", 1200.0, Icons.Filled.AccountBalanceWallet),
            Transaction("13", "Bills", -95.0, Icons.Filled.ReceiptLong),
            Transaction("14", "Savings", 750.0, Icons.Filled.Savings)
        )
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        // Implement search logic if needed, e.g., filter transactions
    }

    fun onCurrencyChanged(currency: String) {
        _selectedCurrency.value = currency
        // Implement currency change logic if needed
    }

    fun onTransactionSelected(transaction: Transaction) {
        _selectedTransaction.value = transaction
    }

    fun clearSelectedTransaction() {
        _selectedTransaction.value = null
    }

    // Add other event handlers as needed, e.g., for adding money, handling clicks
}
