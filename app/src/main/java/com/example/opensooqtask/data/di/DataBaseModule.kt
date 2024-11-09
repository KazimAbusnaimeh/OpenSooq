package com.example.opensooqtask.data.di

import CategoryModel
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    private const val REALM_VERSION = 1L
    private const val REALM_NAME = "OpenSooqRealm"
    private const val SHARED_PREF_NAME = "OpenSooqAppPrefs"

    @Singleton
    @Provides
    fun providesRealmConfig(): RealmConfiguration {
        return RealmConfiguration.Builder(setOf(CategoryModel::class))
            .name(REALM_NAME)
            .schemaVersion(REALM_VERSION)
            .build()
    }

    @Singleton
    @Provides
    fun providesRealm(config: RealmConfiguration): Realm {
        return Realm.open(config)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Application): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }
}
