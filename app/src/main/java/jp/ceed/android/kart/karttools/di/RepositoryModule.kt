package jp.ceed.android.kart.karttools.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.ceed.android.kart.karttools.repository.InputValueRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun bindInputValueRepository(
        @ApplicationContext context: Context
    ): InputValueRepository {
        return InputValueRepository(context)
    }
}