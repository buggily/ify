import com.buggily.ify.feature.age.AgeState

val AgeState.Success.nameText: String
    get() = age.name

val AgeState.Success.ageText: String?
    get() = age.age?.toString()

val AgeState.Success.countText: String
    get() = formatNumber(age.count)

val AgeState.Error.errorText: String
    get() = error
