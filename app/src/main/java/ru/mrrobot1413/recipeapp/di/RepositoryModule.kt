package ru.mrrobot1413.recipeapp.di

import ru.mrrobot1413.recipeapp.network.Api
import ru.mrrobot1413.recipeapp.network.repositories.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRecipeRepository(api: Api): RecipeRepository {
        return RecipeRepository(apiSrc = api)
    }
}