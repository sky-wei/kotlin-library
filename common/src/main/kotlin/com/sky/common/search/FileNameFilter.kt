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

class FileNameFilter(
    builder: Builder
) : FileFilter {

    private val fileNames = builder.fileNames
    private val fullMatch: Boolean = builder.fullMatch
    private val lowerCaseFileNames = ArrayList<String>()

    init {
        for (index in fileNames.indices) {
            lowerCaseFileNames[index] = fileNames[index].lowercase()
        }
    }

    override fun accept(file: File): Boolean {

        if (file.isDirectory) return false

        val desiredFileName = file.name.lowercase()

        for (fileName in lowerCaseFileNames) {
            if (matchName(desiredFileName, fileName)) {
                return true
            }
        }
        return false
    }

    /**
     * 匹配名称
     */
    private fun matchName(
        desiredFileName: String,
        fileName: String?
    ): Boolean {

        fileName?: return false

        if (fullMatch) {
            // 全匹配
            return desiredFileName == fileName
        }
        // 包含匹配
        return desiredFileName.contains(fileName)
    }


    class Builder(init: Builder.() -> Unit) {

        var fileNames: List<String> = arrayListOf()
        var fullMatch: Boolean = true

        init {
            init()
        }

        fun fileName(vararg fileNames: String) {
            this.fileNames = fileNames.toList()
        }

        fun build() = FileNameFilter(this)
    }
}

fun fileNameFilter(
    init: FileNameFilter.Builder.() -> Unit
) = FileNameFilter.Builder(init).build()



