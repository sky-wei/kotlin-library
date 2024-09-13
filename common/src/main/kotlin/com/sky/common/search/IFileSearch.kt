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

interface IFileSearch {

    /**
     * 搜索指定目录下的所有文件或目录,
     * 把过滤后的所有文件或目录添加到ArrayList容器中
     * @param searchDir 搜索的目录
     * @return 探索的结果
     */
    fun search(searchDir: File): List<File>

    /**
     * 搜索指定目录下的所有文件或目录,
     * 把过滤后的所有文件或目录添加到ArrayList容器中
     * @param searchDir 搜索的目录
     * @return 探索的结果
     */
    fun search(searchDir: String): List<File>
}