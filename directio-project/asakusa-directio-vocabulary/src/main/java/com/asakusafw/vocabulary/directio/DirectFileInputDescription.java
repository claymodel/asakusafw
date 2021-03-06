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
package com.asakusafw.vocabulary.directio;

import com.asakusafw.runtime.directio.DataFormat;
import com.asakusafw.runtime.directio.FilePattern;
import com.asakusafw.vocabulary.external.ImporterDescription;

/**
 * An abstract super class that describes a direct input source.
 * Each subclass must satisfy following requirements:
 * <ul>
 * <li> declared as {@code public} </li>
 * <li> not declared as {@code abstract} </li>
 * <li> not declared type parameters </li>
 * <li> not declared any explicit constructors </li>
 * </ul>
 * @since 0.2.5
 * @version 0.2.6
 */
public abstract class DirectFileInputDescription implements ImporterDescription {

    /**
     * Returns the base path of target input.
     * <em>On testing, the target path will be deleted by the framework.</em>
     * This path can include variables as <code>${&lt;variable-name&gt;}</code>.
     * @return target base path
     */
    public abstract String getBasePath();

    /**
     * Returns the resource path pattern of target input.
     * The framework will search for input files using this pattern from {@link #getBasePath() the base path}.
     * The pattern can include following characters:
     * <ul>
     * <li> normal characters
     *   <ul>
     *   <li> just represents a path </li>
     *   <li> excepts <code>"\", "*", "$", "?", "#", "|", "{", "}", "[", "]"</code> </li>
     *   </ul>
     * </li>
     * <li> <code>{alter1|alter2|alter3}</code> (selection)
     *   <ul>
     *   <li> represents selection </li>
     *   <li> each alternative must be consist of normal characters (NO wildcards, variables) </li>
     *   </ul>
     * </li>
     * <li> <code>${variable-name}</code> (variables)
     *   <ul>
     *   <li> replaced in runtime with barch arguments </li>
     *   </ul>
     * </li>
     * <li> {@code *} (wildcard character)
     *   <ul>
     *   <li> represents wildcard of file/directory name </li>
     *   </ul>
     * </li>
     * <li> {@code **} (wildcard segment)
     *   <ul>
     *   <li> represents all directories and files includes sub directories and files </li>
     *   <li> this must be an alternative of file/directory name;
     *        for example, {@code **.csv} is <em>NOT</em> accepted (<code>&#42;&#42;/&#42;.csv</code> instead)
     *   </li>
     *   </ul>
     * </li>
     * </ul>
     * Examples:
     * <table border="1">
     * <tr>
     *   <th> Expression </th>
     *   <th> Description </th>
     * </tr>
     * <tr>
     *   <td> <code>a/b/c.csv</code> </td>
     *   <td> just {@code a/b/c.csv} </td>
     * </tr>
     * <tr>
     *   <td> <code>{2011/12|2012/01|2012/02}/*.csv</code> </td>
     *   <td> any CSV files in "2011/12", "2012/01", or "2012/02" </td>
     * </tr>
     * <tr>
     *   <td> <code>&#42;</code> </td>
     *   <td> any files in target directory </td>
     * </tr>
     * <tr>
     *   <td> <code>&#42;&#42;</code> </td>
     *   <td> any files in target directory (recursive) </td>
     * </tr>
     * <tr>
     *   <td> <code>&#42;.csv</code> </td>
     *   <td> any CSV files in target directory</td>
     * </tr>
     * <tr>
     *   <td> <code>&#42;&#42;/&#42;.csv</code> </td>
     *   <td> any CSV files in target directory (recursive) </td>
     * </tr>
     * </table>
     * @return the resource path pattern
     * @see FilePattern
     */
    public abstract String getResourcePattern();

    /**
     * Returns an implementation of {@link DataFormat} class.
     * @return the class of {@link DataFormat}
     */
    public abstract Class<? extends DataFormat<?>> getFormat();

    @Override
    public DataSize getDataSize() {
        return DataSize.UNKNOWN;
    }
}
