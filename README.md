
The is a Test App for loading Photo data from dummy Api and display as list.
The Photo list is stored in room database and is searchable by title.
The app features pull to refresh, that reloads the data from remote api.
The app logic covers edge cases for network request failure and no search results,
and loads local data from cache on request failure.

# TechStack
- The app uses MVVM design pattern
- The app uses Dagger/Hilt for DI
- The app uses clean architecture.
- Room Repository for local storage
- Retrofit for api calls
- The compose for UI

# Tests
Unit tests
 - ActivityViewModel
 - LocalRepositoryImpl

Ui Tests
-MainActivity


# Modules
 App - App UI logic
 Core - Business logic
 Network - Networking logic


# Features
The app uses clean architecture, which helps separation of concerns and scalability
The app uses modules which keeps code separate and helps maintainability
The app uses Jetpack compose to improve UI performance and readability
The app uses Unit test and UI tests to improve reliability and testability
The app supports Kotlin Multiplatform Mobile that can be used to have common codebase for android and ios

