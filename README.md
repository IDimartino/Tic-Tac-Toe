# Tic Tac Toe - Android Code Challenge

A robust, production-ready Tic Tac Toe application built with modern Android development practices, following **Clean Architecture**, **SOLID principles**, and **Test-Driven Development (TDD)**.

## 🚀 Features

- **Reactive UI**: Built entirely with Jetpack Compose and Unidirectional Data Flow (MVI).
- **Core Engine**: A pure Kotlin domain engine (`TicTacToeEngine`) that manages game rules, turns, and win conditions independently of the UI.
- **State Management**: Lifecycle-aware state handling using `StateFlow` and `collectAsStateWithLifecycle`.
- **Animations**: Smooth, polished transitions for turns, cell updates, and game status changes using `AnimatedContent`.
- **Accessibility**: Comprehensive TalkBack support with semantic descriptions for every board position.
- **Dependency Injection**: Full integration with Hilt for modular and testable code.

## 🛠 Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material 3)
- **Dependency Injection**: Hilt
- **Architecture**: Clean Architecture + MVI (Model-View-Intent)
- **Asynchronous Flow**: Kotlin Coroutines & Flow
- **Unit Testing**: JUnit 4
- **Build System**: Gradle Kotlin DSL + Version Catalog (libs.versions.toml)

## 🏗 Architecture & Design Patterns

The project follows a highly decoupled structure:

1.  **Domain Layer**: Contains the "Business Logic".
    - **`TicTacToeEngine`**: The brain of the game, decoupled via an interface.
    - **Functional `Board`**: An immutable data structure representing the 3x3 grid.
    - **TDD Driven**: 100% of the game rules were developed using a Red-Green-Refactor cycle.
2.  **UI/Presentation Layer (MVI)**:
    - **Model (`TicTacToeUiState`)**: A single, immutable source of truth for the UI.
    - **Intent (`TicTacToeAction`)**: Sealed interface representing all user interactions.
    - **ViewModel**: Orchestrates the state by consuming actions and updating the state flow.
3.  **Clean Code Utilities**:
    - **`UiText`**: A wrapper to keep the ViewModel free of Android `Context` dependencies while supporting localized string resources.
    - **`Dimensions`**: A theme-driven system for consistent spacing using `CompositionLocal`.

## 🧪 Testing

This project prioritizes testability. The domain logic and view model transitions are fully covered by unit tests.

### Running Unit Tests
Execute the following command in the terminal:
```bash
./gradlew test
```
The test suite (20+ tests) covers:
- Win/Draw detection logic.
- Turn-switching orchestration.
- MVI state transitions in the ViewModel.

## 📥 How to Run

### Prerequisites
- **Android Studio Ladybug** (or newer)
- **JDK 17**
- **Android SDK 29+**

### Steps
1. **Clone & Open**: Clone the repo and open it in Android Studio.
2. **Sync**: Wait for Gradle sync to complete.
3. **Run**: Deploy to an emulator or physical device.

## 🧹 Commit History Strategy
The git history of this project is structured to demonstrate a professional development workflow:
- **`test:`** commits showing the Red phase (failing tests).
- **`feat:`** commits showing the Green phase (implementation).
- **`refactor:`** commits showing code improvements (Clean Code, naming, organization).
- **`chore:`** setup and documentation.
