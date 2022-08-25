package com.example.mygithubtask.data

import android.app.Application
import androidx.room.Room
import com.example.mygithubtask.common.Constants
import com.example.mygithubtask.data.local.room.UserDao
import com.example.mygithubtask.data.local.room.UserDataBase
import com.example.mygithubtask.data.local.room.repo.RepoDao
import com.example.mygithubtask.data.local.room.repo.RepoDatabase
import com.example.mygithubtask.data.remote.ApiService
import com.example.mygithubtask.data.repository.UserImplFlow
import com.example.mygithubtask.data.repository.UserRepositoryImpl
import com.example.mygithubtask.domain.repository.UserRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule =  module {
    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    fun provideUserdataBase(application:Application):UserDataBase =
        Room.databaseBuilder(application,UserDataBase::class.java,"userDatabase")
            .fallbackToDestructiveMigration()
            .build()

    fun provideUserDao(db:UserDataBase):UserDao = db.usersDao()

    single{
        provideUserdataBase(get())

    }
    single{
        provideUserDao(get())
    }

    single{UserImplFlow(api = get(), db = get(),dao = get())}
    fun provideRepoDataBase(application:Application):RepoDatabase =
        Room.databaseBuilder(application,RepoDatabase::class.java,"repoDatabase")
            .fallbackToDestructiveMigration()
            .build()

    fun provideRepoDao(db:RepoDatabase):RepoDao = db.repoDao()

    single { provideRepoDataBase(get()) }

    single { provideRepoDao(get()) }


}
