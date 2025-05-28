package com.example.aiagent.ui

import android.R.attr.singleLine
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding

// Assume you have your R.drawable.your_background_image
// For icons, you might need to add dependencies or use your own drawables
// e.g., implementation "androidx.compose.material:material-icons-extended:$compose_version"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceScreen(viewModel: FinanceViewModel = FinanceViewModel()) {
    // Collect state from ViewModel
    val transactions by viewModel.transactions.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopSection(viewModel)
        },
        bottomBar = {
            AppBottomNavigation()
        },
        // Exclude status bars from content padding as we handle it in TopSection
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            ActionButtons()
            TransactionHeader()

            LazyColumn {
                items(transactions) { transaction ->
                    TransactionItem(transaction, viewModel)
                }
                item { Spacer(modifier = Modifier.height(16.dp)) } // Space before bottom nav
            }
        }
    }
}

@Composable
fun TopSection(viewModel: FinanceViewModel) {
    // Collect state from ViewModel
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    var currencyDropdownExpanded by remember { mutableStateOf(false) }
    val selectedCurrency by viewModel.selectedCurrency.collectAsStateWithLifecycle()
    val availableBalance by viewModel.availableBalance.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp) // Adjust height as needed
            // Replace with your actual background
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF3A48E8), Color(0xFF2A37C8))
                )
            )
    ) {
        // You would place your background image here if using one
        // Image(painter = painterResource(id = R.drawable.your_background_image), contentDescription = null, contentScale = ContentScale.Crop)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* TODO: Trophy action */ }) {
                    Icon(
                        Icons.Filled.EmojiEvents,
                        contentDescription = "Trophy",
                        tint = Color.White
                    )
                }
                OutlinedTextField(
                    value = TextFieldValue(searchQuery),
                    onValueChange = { viewModel.onSearchQueryChanged(it.text) },
                    placeholder = { Text("Search \"Payments\"", color = Color.LightGray) },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = Color.LightGray
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    shape = RoundedCornerShape(24.dp),
                    singleLine = true
                )
                IconButton(onClick = { /* TODO: Notifications action */ }) {
                    Icon(
                        Icons.Filled.Notifications,
                        contentDescription = "Notifications",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable { currencyDropdownExpanded = true }
            ) {
                // You might use an actual flag image here
                Icon(
                    Icons.Filled.Flag,
                    contentDescription = "Currency Flag",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(selectedCurrency, color = Color.White, fontSize = 14.sp)
                Icon(
                    Icons.Filled.ArrowDropDown,
                    contentDescription = "Select Currency",
                    tint = Color.White
                )

                DropdownMenu(
                    expanded = currencyDropdownExpanded,
                    onDismissRequest = { currencyDropdownExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("US Dollar") },
                        onClick = { 
                            viewModel.onCurrencyChanged("US Dollar")
                            currencyDropdownExpanded = false 
                        })
                    DropdownMenuItem(
                        text = { Text("Euro") },
                        onClick = { 
                            viewModel.onCurrencyChanged("Euro")
                            currencyDropdownExpanded = false 
                        })
                }
            }

            Text(
                text = availableBalance,
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Available Balance",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* TODO: Add Money action */ },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Icon(
                    Icons.Filled.AddCard,
                    contentDescription = "Add Money",
                    tint = Color(0xFF3A48E8)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Add Money", color = Color(0xFF3A48E8), fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun ActionButtons() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.Red),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ActionButtonItem(icon = Icons.Filled.Send, text = "Send", onClick = { /*TODO*/ })
            ActionButtonItem(
                icon = Icons.Filled.Download,
                text = "Request",
                onClick = { /*TODO*/ }) // Placeholder icon
            ActionButtonItem(
                icon = Icons.Filled.AccountBalance,
                text = "Bank",
                onClick = { /*TODO*/ })
        }
    }
}

@Composable
fun ActionButtonItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            tint = Color(0xFF3A48E8), // Example color
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(Color(0xFFE0E0FF)) // Light blue background for icon
                .padding(6.dp)

        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text, fontSize = 13.sp, color = Color.DarkGray)
    }
}

@Composable
fun TransactionHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 8.dp) // Adjusted top padding
            .offset(y = (-20).dp), // Adjust if ActionButtons overlap too much
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Transaction", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        IconButton(onClick = { /* TODO: View all transactions */ }) {
            Icon(
                Icons.Filled.ArrowForwardIos,
                contentDescription = "View All",
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

// Removed getSampleTransactions function as we now get transactions from the ViewModel

@Composable
fun TransactionItem(transaction: Transaction, viewModel: FinanceViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.onTransactionSelected(transaction) }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF0F0F8)), // Light grey background for icon
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = transaction.icon,
                contentDescription = transaction.type,
                tint = if (transaction.amount < 0) Color(0xFFE57373) else Color(0xFF81C784) // Example icon tint
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(transaction.type, fontSize = 16.sp, color = Color.DarkGray)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = (if (transaction.amount < 0) "-" else "+") + "$" + "%,.0f".format(
                kotlin.math.abs(
                    transaction.amount
                )
            ),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (transaction.amount < 0) Color(0xFFD32F2F) else Color(0xFF388E3C)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            Icons.Filled.ArrowForwardIos,
            contentDescription = "Details",
            tint = Color.Gray,
            modifier = Modifier.size(14.dp)
        )
    }
}


@Composable
fun AppBottomNavigation() {
    // Using Material 3 NavigationBar
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        BottomNavItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavItem("History", Icons.Filled.History, Icons.Outlined.History),
        BottomNavItem(
            "Scan",
            Icons.Filled.QrCodeScanner,
            Icons.Outlined.QrCodeScanner,
            isCenter = true
        ), // Special Center Button
        BottomNavItem("Chat", Icons.Filled.ChatBubble, Icons.Outlined.ChatBubbleOutline),
        BottomNavItem("Profile", Icons.Filled.Person, Icons.Outlined.PersonOutline)
    )

    NavigationBar(
        containerColor = Color.White, // Or your desired background color
        tonalElevation = 0.dp
    ) {
        items.forEachIndexed { index, item ->
            if (item.isCenter) {
                // Custom layout for the center button
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                        .padding(bottom = 16.dp), // Push it up a bit
                    contentAlignment = Alignment.Center
                ) {
                    FloatingActionButton(
                        onClick = { selectedItem = index /* TODO: Handle click */ },
                        shape = CircleShape,
                        containerColor = Color(0xFF3A48E8), // Blue color
                        contentColor = Color.White,
                        modifier = Modifier.size(56.dp)
                    ) {
                        Icon(
                            item.selectedIcon,
                            contentDescription = item.label,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            } else {
                NavigationBarItem(
                    icon = {
                        Icon(
                            if (selectedItem == index) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.label
                        )
                    },
                    label = { Text(item.label, fontSize = 10.sp) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index /* TODO: Handle click */ },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF3A48E8),
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = Color(0xFF3A48E8),
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.Transparent // Or your choice for indicator
                    )
                )
            }
        }
    }
}

data class BottomNavItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val isCenter: Boolean = false
)

@Preview(showBackground = true)
@Composable
fun DefaultPreviewFinanceScreen() {
    MaterialTheme { // Ensure a MaterialTheme is applied for previews
        // Create a ViewModel instance for the preview
        val previewViewModel = FinanceViewModel()
        FinanceScreen(viewModel = previewViewModel)
    }
}
