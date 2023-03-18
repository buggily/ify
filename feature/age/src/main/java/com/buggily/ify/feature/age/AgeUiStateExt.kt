import com.buggily.ify.feature.age.AgeUiState

val AgeUiState.Success.nameText: String
    get() = age.name

val AgeUiState.Success.ageText: String?
    get() = age.age?.toString()

val AgeUiState.Success.countText: String
    get() = formatNumber(age.count)

val AgeUiState.Error.Api.errorText: String
    get() = error
