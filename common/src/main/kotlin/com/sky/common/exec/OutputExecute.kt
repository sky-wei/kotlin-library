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

package com.sky.common.exec

import com.sky.common.util.tryRun
import java.io.OutputStream

class OutputExecute(
    private val out: OutputStream,
    private val commands: List<String>
) : Thread() {

    override fun run() {
        super.run()

        tryRun(
            logger = Execute.LOGGER
        ) {
            for (i in commands.indices) {
                val command = "${commands[i]}\n"
                if (command.startsWith("sleep(")) {
                    // 需要休眠一下
                    val time = command.substring(
                        command.indexOf('(') + 1, command.lastIndexOf(')')
                    )
                    sleep(time.toInt().toLong())
                    continue
                }
                out.write(command.toByteArray())
                out.flush()
            }
        }
    }
}