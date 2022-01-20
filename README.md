# Coin-Gecko Crypto Currency Application
Coin-Gecko Crypto Currency Application is a simple MVVM pattern application with Single Activity. This application represents simple crypto currency coin listing from binance, a detail page for crypto coin and favorite action with firebase store.

<img src="/images/main-scene-crypto-coin-list.png" width="240"> | <img src="/images/main-scene-filtered-coins.png" width="240"> | <img src="/images/crypto-detail-scene.png" width="240">
<img src="/images/crypto-add-favorite.png" width="240"> | <img src="/images/crypto-favorite-list.png" width="240"> | <img src="/images/crypto-empty-favorite-list.png" width="240">

## Technologies

- Navigation Component: Consistent navigation between views
- ViewModel: Holds UI data across configuration changes
- LiveData: Lifecycle aware observable and data holder
- Retrofit: HTTP Client
- Gson: JSON serializer/deserializer
- Coroutines: Asynchronous programming
- Flow: Asynchronous service calls
- Dagger-Hilt: Dependency injection
- DataBinding: Binds UI components in layouts to data sources
- Glide: Image loading and caching
- Firebase: Storing user data

## Architecture

- Single Activity
- MVVM Pattern
- Clean Code
- Repository Pattern

<img src="/images/architecture-diagram.png" width="320" height="460"> | <img src="/images/project-folder-structure.png" width="320" height="460">

## License

```
Copyright kkocaburak

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
