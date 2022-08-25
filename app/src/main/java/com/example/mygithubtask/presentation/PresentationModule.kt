package com.example.mygithubtask.presentation

import com.example.mygithubtask.presentation.my_profile_screen.MyProfileViewModel
import com.example.mygithubtask.presentation.repository_screen.RepositoryViewModel
import com.example.mygithubtask.presentation.users_screen.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        RepositoryViewModel(repositoryUseCase = get(),dao = get())
    }

    viewModel {
        UsersViewModel(usersUseCase = get(),dao = get())
    }
    viewModel {
        MyProfileViewModel(get())
    }

}