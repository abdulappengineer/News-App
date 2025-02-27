package com.soha.infotech.newsapp.di

import android.app.Application
import androidx.room.Room
import com.soha.infotech.newsapp.domain.usecases.news.DeleteArticle
import com.soha.infotech.newsapp.domain.usecases.news.GetNews
import com.soha.infotech.newsapp.domain.usecases.news.NewsUseCases
import com.soha.infotech.newsapp.domain.usecases.news.SearchNews
import com.soha.infotech.newsapp.domain.usecases.news.SelectArticle
import com.soha.infotech.newsapp.domain.usecases.news.SelectArticles
import com.soha.infotech.newsapp.domain.usecases.news.UpsertArticle
import com.soha.infotech.newsapp.data.local.NewsDao
import com.soha.infotech.newsapp.data.local.NewsDatabase
import com.soha.infotech.newsapp.data.local.NewsTypeConvertor
import com.soha.infotech.newsapp.data.manager.LocalUserManagerImpl
import com.soha.infotech.newsapp.data.remote.NewsApi
import com.soha.infotech.newsapp.data.repository.NewsRepositoryImpl
import com.soha.infotech.newsapp.domain.manager.LocalUserManager
import com.soha.infotech.newsapp.domain.repository.NewsRepository
import com.soha.infotech.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.soha.infotech.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.soha.infotech.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.soha.infotech.newsapp.utils.Constants.BASE_URL
import com.soha.infotech.newsapp.utils.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Part 2.5 : Now We have to implement Dagger Hilt for Dependency Injection
 *
 * Create new Object file as AppModule
 * Here we need to specify the LocalUserManager and App Entry UseCases
 * Thus we implement dagger hilt
 */

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    /*
   inside the module we define the dependencies we want to provide
    */

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // to convert the json response to kotlin class
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration() // if you update something room will migrate it for you
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}