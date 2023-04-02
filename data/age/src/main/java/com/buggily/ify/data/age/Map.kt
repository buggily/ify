package com.buggily.ify.data.age

import com.buggily.ify.local.age.LocalAge
import com.buggily.ify.remote.age.RemoteAge

fun LocalAge.to(): Age = Age(
    name = name,
    age = age,
    count = count,
)

fun RemoteAge.toLocal(): LocalAge = LocalAge(
    name = name,
    age = age,
    count = count,
)
