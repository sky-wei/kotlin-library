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

import com.sky.common.util.PlatformUtil
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.File
import java.io.OutputStream

/**
 * 当前类主要用于执行相关的命令<br></br>
 *
 * [.exec]用来执行一组命令
 *
 * @author sky
 */
class Execute (
    private val envp: Array<String> = arrayOf(),
    private val workDir: File = File(PlatformUtil.getUserDir()),
    private val out: OutputStream = System.out,
    private val err: OutputStream = System.err
) : IExecute {

    companion object {

        val LOGGER: Logger = LogManager.getLogger(Execute::class.java)
    }

    /**
     * 把一组String[]命令转换成List,并且跳过第一个命令
     * @param commands 需要转换的命令
     * @return 转换后的命令
     */
    private fun resolveCommands(commands: Array<out String>): List<String> {
        val outputExecute: MutableList<String> = ArrayList()
        for (i in 1 until commands.size) {
            outputExecute.add(commands[i])
        }
        return outputExecute
    }

    /**
     * cmd|sh中执行指定的一组命令(并把执行的结果输出到流中)<br></br>
     * 例如:Windows{"cmd", "java -version", "exit"}<br></br>
     * 例如:Linux{"sh", "java -version", "exit"}<br></br>
     * 使用方法:{"sh|cmd", commands, "exit"}<br></br>
     * @param commands 执行的命令
     * @throws BrutException
     */
    @Throws(BrutException::class)
    override fun insertionExec(vararg commands: String) {

        val newCommands = arrayListOf<String>()

        // 获取相应系统下的命令
        newCommands.add(
            if (PlatformUtil.isWindows) "cmd" else "sh"
        )

        commands.forEach { newCommands.add(it) }

        // 最后添加的退出命令
        newCommands.add("exit")

        // 执行整个命令
        exec(*newCommands.toTypedArray())
    }

    /**
     * 执行指定的一组命令(并把执行的结果输出到流中)<br></br>
     * 执行命令时,可以休眠再执行下一条命令(单位:毫秒)<br></br>
     * 例如:{"java -version", "sleep(1000)", "java"}<br></br>
     * 注意:休眠命令不能放在第一条命令上<br></br>
     * @param commands 执行的命令
     * @throws BrutException
     */
    @Throws(BrutException::class)
    override fun exec(vararg commands: String) {

        var process: Process? = null

        try {
            val command = commands[0]

            process = Runtime.getRuntime().exec(command, envp, workDir)

            val `in` = process.inputStream
            val ine = process.errorStream
            val out = process.outputStream

            StreamForwarder(`in`, this.out).start()
            StreamForwarder(ine, this.err).start()

            OutputExecute(out, resolveCommands(commands)).start()

            if (process.waitFor() != 0) {
                throw BrutException("could not exec command: " + commands.contentToString())
            }
        } catch (e: Exception) {
            throw BrutException("could not exec command: " + commands.contentToString(), e)
        } finally {
            process?.destroy()
        }
    }
}


fun runtime(
    envp: Array<String> = arrayOf(),
    workDir: File = File(PlatformUtil.getUserDir()),
    out: OutputStream = System.out,
    err: OutputStream = System.err,
    callback: IExecute.() -> Unit
) {
    Execute(
        envp = envp,
        workDir = workDir,
        out = out,
        err = err
    ).run { callback() }
}
