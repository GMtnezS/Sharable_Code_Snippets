package com.example.m7cta.di

import com.example.m7cta.domain.repository.ConverterRepository
import com.example.m7cta.domain.repository.ConverterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    
    @Binds
    @Singleton
    abstract fun bindConverterRepository(
        impl: ConverterRepositoryImpl
    ): ConverterRepository
}