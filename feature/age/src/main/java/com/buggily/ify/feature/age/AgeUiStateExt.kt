import com.buggily.ify.feature.age.AgeUiState

val AgeUiState.Response.nameText: String
    get() = age.name

val AgeUiState.Response.ageText: String?
    get() = age.age?.toString()

val AgeUiState.Response.countText: String
    get() = formatNumber(age.count)

val AgeUiState.Failure.Remote.Api.failureText: String
    get() = message
