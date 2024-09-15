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

class MultipleFilter(
    private val filters: List<FileFilter>
) : FileFilter {

    companion object {

        fun valueOf(vararg filters: FileFilter): FileFilter {
            return MultipleFilter(filters.toList())
        }
    }

    override fun accept(file: File): Boolean {
        return filters.find { it.accept(file) } != null
    }
}