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

import kotlin.test.Test

class FileSearchTest {

    @Test
    fun testSearch() {

        fileSearch {
            filter = multipleFilter(
                fileNameFilter {
                    fullMatch = false
                    fileName("Search")
                },
                fileNameFilter {
                    fileName("ExecuteTest.kt")
                }
            )
        }.search(
            "/media/data/Work/SkyProject/kotlin-library/common/src/test/kotlin/com/sky"
        ).also {
            println(it)
            assert(it.isNotEmpty())
        }
    }
}