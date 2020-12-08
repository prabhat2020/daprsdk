package com.tcs.service.utility

import kotlin.reflect.KClass

fun <T: Any> cast(any: Any, clazz: KClass<out T>): T = clazz.javaObjectType.cast(any)