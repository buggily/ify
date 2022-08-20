# ify

ify is a supplemental code sample written by @buggily.

### Overview

ify accepts a name via text input. Upon text input debounce, ify employs Kotlin Coroutines to asyncronously and concurrently fetch responses from [agify.io][agify], [genderize.io][genderize], and [nationalize.io][nationalize]. A callback adapter specific to suspension functions transforms the responses for safe, painless frontend consumption.

### Implementation

ify demonstrates the following:

- Dependency injection via [Hilt][hilt]
- REST API consumption via [Retrofit][retrofit]
- Asynchronous processing via [kotlinx.coroutines][coroutines]
- JSON serialization via [kotlinx.serialization][serialization]
- Declarative layouts via [Jetpack Compose][compose]
- Unidirectional data flow via [MVVM][mvvm]

### Setup

Because none of the endpoints require API keys for infrequent users, setup simply involves cloning the repository. Run in [Android Studio][android studio] or any Android IDE.

<img src="./res/ify_light.png" alt="ify light" width=50%><img src="./res/ify_dark.png" alt="ify dark" width=50%>

*ify in static light and dark modes*

[agify]: https://agify.io/
[genderize]: https://genderize.io/
[nationalize]: https://nationalize.io/

[hilt]: https://developer.android.com/training/dependency-injection/hilt-android/
[retrofit]: https://square.github.io/retrofit/
[coroutines]: https://github.com/Kotlin/kotlinx.coroutines/
[serialization]: https://github.com/Kotlin/kotlinx.serialization/
[compose]: https://developer.android.com/jetpack/compose/
[mvvm]: https://developer.android.com/topic/architecture/
[android studio]: https://developer.android.com/studio
