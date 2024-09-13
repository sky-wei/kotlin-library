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

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File
import java.io.FileFilter
import java.util.*

class FileSearch(
    private val filter: FileFilter
) : IFileSearch {

    companion object {

        val LOGGER: Logger = LogManager.getLogger(FileSearch::class.java)
    }

    override fun search(searchDir: String): List<File> {
        return search(File(searchDir))
    }

    /**
     * 搜索指定目录下的所有文件或目录,
     * 把过滤后的所有文件或目录添加到ArrayList容器中
     * @param searchDir 搜索的目录
     * @return 探索的结果
     */
    override fun search(searchDir: File): List<File> {

        if (!searchDir.isDirectory) {
            return emptyList()
        }

        // 获取源目录下的所有文件或目录
        val searchQueue = listDir(searchDir)
        val searchFiles: MutableList<File> = ArrayList()

        while (!searchQueue.isEmpty()) {

            val file = searchQueue.poll()

            if (file != null) {

                if (file.isDirectory) {
                    val queue1 = listDir(file)
                    if (queue1.isNotEmpty()) {
                        searchQueue.addAll(queue1)
                    }
                }

                if (accept(file)) {
                    searchFiles.add(file)
                }
            }
        }
        return searchFiles
    }

    /**
     * 列表指定目录下的所有文件或目录并存在LinkedList容器中
     * @param dir 列表的目录
     * @return 目录下的所有文件或目录
     */
    private fun listDir(dir: File): Queue<File> {

        val queue: Queue<File> = LinkedList()

        if (!dir.isDirectory) {
            return queue
        }

        dir.listFiles()?.forEach {
            queue.add(it)
        }
        return queue
    }

    /**
     * 用来过滤文件是否可用,
     * 当不设置过滤规则时放行所有文件
     * @param file 判断需要过滤文件
     * @return true:文件可用,false:文件需要过滤
     */
    private fun accept(file: File): Boolean {
        return filter.accept(file)
    }
}


/**
 * 所有文件
 */
object AllowAllFile : FileFilter {
    override fun accept(file: File): Boolean {
        return file.isFile
    }
}


fun fileSearch(
    filter: FileFilter = AllowAllFile,
    callback: IFileSearch.() -> List<File>
): List<File> {
    return FileSearch(
        filter = filter
    ).run {
        callback()
    }
}

fun fileSearch(
    searchDir: String,
    filter: FileFilter = AllowAllFile
): List<File> {
    return fileSearch(
        filter = filter
    ) {
        search(searchDir)
    }
}

fun fileSearch(
    searchDir: File,
    filter: FileFilter = AllowAllFile
): List<File> {
    return fileSearch(
        filter = filter
    ) {
        search(searchDir)
    }
}
