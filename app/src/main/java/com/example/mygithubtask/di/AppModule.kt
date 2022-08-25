package com.example.mygithubtask.di

import com.example.mygithubtask.data.dataModule
import com.example.mygithubtask.domain.domainModule
import com.example.mygithubtask.presentation.presentationModule

val appModule = dataModule + domainModule + presentationModule




