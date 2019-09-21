# Popular Movies App

## Project Overview
This showcase app queries the MovieDB for popular videos and shows them on a grid, clicking on a poster takes the user to the detail page, where various information about the movie are shown. Reviews and additional videos are also shown, if present.

## What is included?
- 100% Kotlin
- API quering using Retrofit + Coroutines inside ViewModel with LiveData
- Code is organized using Clean Architecture principles
- DI with Koin
- Shared element animation from list to detail fragment
- Picasso for image loading
- Unit tests
- One UI test :)
- Basic Circle CI configuration for testing

## What's missing?
- The MovieDB provides more data that could be shown, like top rated movies etc
- Call caching with Room could be added
- More UI tests
