package com.example.mygithubtask.domain

import com.example.mygithubtask.domain.use_cases.GetUserProfileUseCase
import com.example.mygithubtask.domain.use_cases.GetUserRepositoryUseCase
import com.example.mygithubtask.domain.use_cases.GetUsersUseCase
import org.koin.dsl.module

val domainModule = module {

    single { GetUserRepositoryUseCase(repository = get(),db = get(),dao = get()) }
    single { GetUsersUseCase(repository = get(),db = get(),dao = get()) }
    single { GetUserProfileUseCase(repository = get()) }
}


