package com.coronaintheworld.di

import com.coronaintheworld.common.ResponseHandler
import org.koin.dsl.module

val commonModule = module {
    factory { ResponseHandler() }
}
