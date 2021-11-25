/*
 * (C) Copyright 2006-2015 Nuxeo SA (http://nuxeo.com/) and others.
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
 *
 * Contributors:
 *     Bogdan Stefanescu
 *     Florent Guillaume
 */
package org.nuxeo.ecm.core.bulk;

import java.io.Serializable;

import org.nuxeo.ecm.core.api.impl.blob.StringBlob;

/**
 * Fake blob with a 0 content/length
 *
 * @since 2021.8
 */
public class ZeroLengthBlob extends StringBlob implements Serializable {

    public ZeroLengthBlob(String content) {
        super(content);
    }

    @Override
    public long getLength() {
        return 0;
    }

}
