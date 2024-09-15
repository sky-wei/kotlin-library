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
package com.sky.common.search

import java.io.File
import java.io.FileFilter

class FileNameExtensionFilter(
    vararg extensions: String
) : FileFilter {

    private val extensions = arrayOfNulls<String>(extensions.size)
    private val lowerCaseExtensions = arrayOfNulls<String>(extensions.size)

    init {
        for (index in extensions.indices) {
            require(extensions[index].isNotEmpty()) {
                "Each extension must be non-null and not empty"
            }
            this.extensions[index] = extensions[index]
            lowerCaseExtensions[index] = extensions[index].lowercase()
        }
    }

    override fun accept(file: File): Boolean {

        if (file.isDirectory) {
            return false
        }

        val fileName = file.name
        val index = fileName.lastIndexOf('.')

        if (index > 0 && index < fileName.length - 1) {

            val desiredExtension = fileName.substring(
                index + 1
            ).lowercase()

            for (extension in lowerCaseExtensions) {
                if (desiredExtension == extension) {
                    return true
                }
            }
        }
        return false
    }
}
