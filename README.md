#  Test App

The is a Test App for loading event data from TicketMaster Api and display as list.
The event list is stored in room database and is searchable by name.
The app features pull to refresh, that reloads the data from api.
The app logic covers edge cases for network request failure and no search results.

# Features

- The app uses Dagger/Hilt for DI
- The app uses clean architecture.
- Room Repository for local storage
- Retrofit for api calls
- The UI in compose
- The api key is injected using retrofit interceptor

# Tests

Unit tests
 - EventViewModel
 - LocalRepositoryImpl

Ui Tests
-MainActivity


# Modules

 App - App UI logic
 Core - Business logic
 Network - Networking logic


# TODO
Save api credentials in android credential manager
