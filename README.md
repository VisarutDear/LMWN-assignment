# LMWN-assignment
- An app that display list of cryptocurrency and display information of coin such as name, price, change and more.
## Summary
- Architecture: Clean Architecture + MVVM
- Uses [koin](https://github.com/InsertKoinIO/koin) for dependency injection
- Uses [Epoxy](https://github.com/airbnb/epoxy) for building complex screens in a RecyclerView

## Library
- `Epoxy` Recycler View
- `Retrofit` Http Client

## Known Issue
- Does not show website button on first click
- Display image in webview instead of imageview because can't display svg coin icon
