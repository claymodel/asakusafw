/**
 * Copyright 2011-2013 Asakusa Framework Team.
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
package com.asakusafw.compiler.flow;

import com.asakusafw.compiler.common.Precondition;
import com.asakusafw.vocabulary.flow.JobFlow;
import com.asakusafw.vocabulary.flow.graph.FlowGraph;

/**
 * ジョブフロークラスの内容。
 */
public class JobFlowClass {

    private JobFlow config;

    private FlowGraph graph;

    /**
     * インスタンスを生成する。
     * @param config このジョブフローの設定
     * @param graph このジョブフローの構造を表すグラフ
     * @throws IllegalArgumentException 引数に{@code null}が指定された場合
     */
    public JobFlowClass(JobFlow config, FlowGraph graph) {
        Precondition.checkMustNotBeNull(config, "config"); //$NON-NLS-1$
        Precondition.checkMustNotBeNull(graph, "graph"); //$NON-NLS-1$
        this.config = config;
        this.graph = graph;
    }

    /**
     * このジョブフローの設定を返す。
     * @return このジョブフローの設定
     */
    public JobFlow getConfig() {
        return config;
    }

    /**
     * このジョブフローの構造を表すグラフを返す。
     * @return このジョブフローの構造を表すグラフ
     */
    public FlowGraph getGraph() {
        return this.graph;
    }
}
