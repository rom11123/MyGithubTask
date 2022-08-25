package com.example.mygithubtask.domain.use_cases

import androidx.room.withTransaction
import com.example.mygithubtask.common.Resource
import com.example.mygithubtask.data.local.room.UserDao
import com.example.mygithubtask.data.local.room.UserDataBase
import com.example.mygithubtask.data.local.room.repo.RepoDao
import com.example.mygithubtask.data.local.room.repo.RepoDatabase
import com.example.mygithubtask.data.model.Users
import com.example.mygithubtask.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetUsersUseCase(
    private val repository: UserRepository,
    private val db: UserDataBase,
    private val dao: UserDao,
) {
    operator fun invoke(): Flow<Resource<List<Users>>> = flow {
        try {

            emit(Resource.Loading<List<Users>>())
            val response = repository.getUsers()
            emit(Resource.Success<List<Users>>(response.body()!!))
            db.withTransaction {
                dao.deleteAll()
                response.body()?.let {
                    dao.insert(it)
                }
            }
        } catch (e: HttpException) {
            emit(Resource.Error<List<Users>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Users>>("Couldn't reach server. Check your internet connection."))
        }
    }
}