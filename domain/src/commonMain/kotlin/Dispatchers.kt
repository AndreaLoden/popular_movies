package com.jetbrains.handson.mpp.mobile

import kotlinx.coroutines.CoroutineDispatcher

internal expect val Main: CoroutineDispatcher

internal expect val Background: CoroutineDispatcher