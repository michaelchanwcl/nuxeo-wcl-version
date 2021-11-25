/*
 * (C) Copyright 2015 Nuxeo SA (http://nuxeo.com/) and others.
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
 *     Thomas Roger
 */

package org.nuxeo.ecm.restapi.jaxrs.io.conversion;

/**
 * @since 7.4
 */
public class ConversionScheduled {

    public final String id;

    public final String pollingURL;

    public final String resultURL;

    /**
     * @since 8.4
     */
    public ConversionScheduled(String id, String pollingURL, String resultURL) {
        this.id = id;
        this.pollingURL = pollingURL;
        this.resultURL = resultURL;
    }

    public ConversionScheduled(String id, String pollingURL) {
        this(id, pollingURL, null);
    }
}
