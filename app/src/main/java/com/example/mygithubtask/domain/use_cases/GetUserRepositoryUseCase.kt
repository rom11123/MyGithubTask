package com.example.mygithubtask.domain.use_cases

import androidx.room.withTransaction
import com.example.mygithubtask.common.Resource
import com.example.mygithubtask.data.local.room.UserDao
import com.example.mygithubtask.data.local.room.repo.RepoDao
import com.example.mygithubtask.data.local.room.repo.RepoDatabase
import com.example.mygithubtask.data.model.Repository
import com.example.mygithubtask.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetUserRepositoryUseCase(
    private val repository: UserRepository,
    private val db: RepoDatabase, private val dao: RepoDao,

    ) {
    operator fun invoke(): Flow<Resource<List<Repository>>> = flow {
        try {

            emit(Resource.Loading<List<Repository>>())
            val response = repository.getUserRepository()

            emit(Resource.Success<List<Repository>>(response.body()!!))
            db.withTransaction {
                dao.deleteAll()
                response.body()?.let {
                    dao.insert(it)
                }
            }
        } catch (e: HttpException) {
            emit(Resource.Error<List<Repository>>(e.localizedMessage
                ?: "An unexpected error occured"))
        } catch (e: IOException) {

            emit(Resource.Error<List<Repository>>("Couldn't reach server. Check your internet connection."))


        }
    }
}