# AiAgent - Finance App

## Overview
Finance application built with Jetpack Compose that helps users track their transactions, manage their finances, and view detailed transaction information. What makes this project unique is that its UI was created by Gemini from a screenshot, while Junie implemented the ViewModel architecture, adapted the UI to use the ViewModel, and created the Transaction Detail screen.

## Features
- Clean, modern UI with a beautiful design
- Transaction tracking and categorization
- Transaction details view
- Search functionality
- Currency selection
- Bottom navigation with quick actions

## Technologies Used
- Kotlin
- Jetpack Compose for UI
- Material 3 Design
- MVVM Architecture
- StateFlow for state management
- Lifecycle-aware state collection

## Created with AI Agents
This project showcases the power of AI-assisted development:

- **Gemini**: Created the UI from a screenshot
- **Junie**: Created the ViewModel, changed the UI to use the ViewModel, and created the Transaction Detail screen

The development process involved:
1. Providing screenshots of the desired UI design to Gemini
2. Gemini generated the initial Jetpack Compose UI code
3. Junie implemented the ViewModel architecture
4. Junie modified the UI to use the ViewModel for state management
5. Junie created the Transaction Detail screen

This approach significantly accelerated the development process while maintaining high-quality code and design fidelity.

## Project Structure
- `MainActivity.kt`: Entry point of the application with navigation logic
- `FinanceScreen.kt`: Main screen showing the user's balance and transactions
- `FinanceViewModel.kt`: ViewModel managing the state and business logic
- `TransactionDetailScreen.kt`: Screen showing detailed information about a selected transaction

## Setup and Installation
1. Clone the repository
2. Open the project in Android Studio
3. Build and run the application on an emulator or physical device

## Requirements
- Android Studio Arctic Fox or newer
- Minimum SDK: 24
- Target SDK: 36
- Kotlin 1.8.0 or newer

## Credits and Acknowledgments
- UI design inspired by modern finance applications
- Special thanks to Gemini for creating the UI from a screenshot
- Special thanks to Junie for implementing the ViewModel, adapting the UI, and creating the Transaction Detail screen
