#  Test App

The is a Test App for loading event data from TicketMaster Api and display as list.
The event list is stored in room database and is searchable by name.
The app features pull to refresh, that reloads the data from api
The app logic covers edge cases of network request failure and no search results.

# Architecture

- The app uses Dagger/Hilt for DI
- The app uses clean architecture.
- Room Repository for events storage
- Retrofit to get data from API
- The UI is designed in compose
- The api key is injected using retrofit interceptor

# Tests

Unit tests
 - EventViewModel
 - LocalRespositoryImpl

Ui Tests
-MainActivity


# Modules

 App - App UI logic
 Core - Business core
 Network - Networking logic


# TODO
Save api credentials in android credential manager
