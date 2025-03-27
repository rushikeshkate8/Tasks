package com.rushikesh.tasks.di

import com.rushikesh.tasks.data.repository.RetrofitRepository
import com.rushikesh.tasks.data.repository.RoomRepository
import com.rushikesh.tasks.data.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class ActivityModule {
    @Provides
    @RetrofitQualifier
    @ViewModelScoped
    fun bindTaskRepository(retrofitRepository: RetrofitRepository): TaskRepository {
        return retrofitRepository
    }

    @Provides
    @RoomQualifier
    fun provideRoomRepository(): TaskRepository = RoomRepository()
}