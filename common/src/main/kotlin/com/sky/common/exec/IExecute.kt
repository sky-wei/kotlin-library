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

interface IExecute {

    /**
     * cmd|sh中执行指定的一组命令(并把执行的结果输出到流中)<br></br>
     * 例如:Windows{"cmd", "java -version", "exit"}<br></br>
     * 例如:Linux{"sh", "java -version", "exit"}<br></br>
     * 使用方法:{"sh|cmd", commands, "exit"}<br></br>
     * @param commands 执行的命令
     * @throws BrutException
     */
    @Throws(BrutException::class)
    fun insertionExec(vararg commands: String)

    /**
     * 执行指定的一组命令(并把执行的结果输出到流中)<br></br>
     * 执行命令时,可以休眠再执行下一条命令(单位:毫秒)<br></br>
     * 例如:{"java -version", "sleep(1000)", "java"}<br></br>
     * 注意:休眠命令不能放在第一条命令上<br></br>
     * @param commands 执行的命令
     * @throws BrutException
     */
    @Throws(BrutException::class)
    fun exec(vararg commands: String)
}