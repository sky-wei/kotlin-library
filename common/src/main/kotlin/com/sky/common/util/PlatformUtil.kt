/*
 * Copyright (c) 2024 The sky Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.common.util

object PlatformUtil {

    enum class Environment {
        Windows, Linux, Mac
    }

    val enumeration: Environment

    init {

        val osName = getOSName()

        enumeration = when {
            osName.contains("Windows") -> {
                Environment.Windows
            }
            osName.contains("Linux") -> {
                Environment.Linux
            }
            osName.contains("Mac") -> {
                Environment.Mac
            }
            else -> {
                Environment.Windows
            }
        }
    }

    val isWindows: Boolean
        get() = enumeration == Environment.Windows

    val isLinux: Boolean
        get() = enumeration == Environment.Linux

    val isMac: Boolean
        get() = enumeration == Environment.Mac

    fun getUserDir(): String = System.getProperty("user.dir")

    fun getOSName(): String = System.getProperty("os.name")
}