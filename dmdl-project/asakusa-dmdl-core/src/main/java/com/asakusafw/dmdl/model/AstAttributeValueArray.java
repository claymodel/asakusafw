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
package com.asakusafw.dmdl.model;

import java.util.List;

import com.asakusafw.dmdl.Region;
import com.asakusafw.utils.collections.Lists;

/**
 * Represents an array of values.
 * @since 0.2.0
 */
public class AstAttributeValueArray extends AbstractAstNode implements AstAttributeValue {

    private final Region region;

    /**
     * Elements in this array.
     */
    public final List<AstAttributeValue> elements;

    /**
     * Creates and returns a new instance.
     * @param region the region of this node on the enclosing script, or {@code null} if unknown
     * @param elements the elements in this array
     * @throws IllegalArgumentException if some parameters were {@code null}
     */
    public AstAttributeValueArray(
            Region region,
            List<? extends AstAttributeValue> elements) {
        this.region = region;
        this.elements = Lists.freeze(elements);
    }

    @Override
    public Region getRegion() {
        return region;
    }

    @Override
    public <C, R> R accept(C context, AstNode.Visitor<C, R> visitor) {
        if (visitor == null) {
            throw new IllegalArgumentException("visitor must not be null"); //$NON-NLS-1$
        }
        R result = visitor.visitAttributeValueArray(context, this);
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + elements.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AstAttributeValueArray other = (AstAttributeValueArray) obj;
        if (!elements.equals(other.elements)) {
            return false;
        }
        return true;
    }
}
