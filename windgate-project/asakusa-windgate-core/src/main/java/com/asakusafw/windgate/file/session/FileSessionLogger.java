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
package com.asakusafw.windgate.file.session;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.asakusafw.windgate.core.WindGateLogger;

/**
 * Logger for WindGate file based sessions implementation.
 * @since 0.2.2
 */
public class FileSessionLogger extends WindGateLogger {

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(
            "com.asakusafw.windgate.file.session.log"); //$NON-NLS-1$

    /**
     * Creates a new instance.
     * @param target the client class
     * @throws IllegalArgumentException if some parameters were {@code null}
     */
    public FileSessionLogger(Class<?> target) {
        super(target, "SESSION_FILE");
    }

    @Override
    protected String getMessage(String code, Object... arguments) {
        String messagePattern = BUNDLE.getString(code);
        return MessageFormat.format(messagePattern, arguments);
    }
}
