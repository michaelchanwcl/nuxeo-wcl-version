/*
 * (C) Copyright 2006-2016 Nuxeo SA (http://nuxeo.com/) and others.
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
 *     Tiago Cardoso <tcardoso@nuxeo.com>
 */
package org.nuxeo.ecm.platform.threed.rendition;

import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.platform.rendition.extension.RenditionProvider;
import org.nuxeo.ecm.platform.rendition.service.RenditionDefinition;
import org.nuxeo.ecm.platform.threed.ThreeDDocument;
import org.nuxeo.ecm.platform.threed.TransmissionThreeD;

import java.util.Collections;
import java.util.List;

/**
 * Provides a rendition based on a 3d transmission format (referenced through the rendition definition lod).
 *
 * @since 8.4
 */
public class TransmissionThreeDRenditionProvider implements RenditionProvider {
    @Override
    public boolean isAvailable(DocumentModel documentModel, RenditionDefinition renditionDefinition) {
        ThreeDDocument threeDDocument = documentModel.getAdapter(ThreeDDocument.class);
        return threeDDocument != null && threeDDocument.getTransmissionThreeD(renditionDefinition.getName()) != null;
    }

    @Override
    public List<Blob> render(DocumentModel documentModel, RenditionDefinition renditionDefinition) {
        ThreeDDocument threeDDocument = documentModel.getAdapter(ThreeDDocument.class);
        if (threeDDocument == null) {
            return Collections.emptyList();
        }
        TransmissionThreeD transmissionThreeD = threeDDocument.getTransmissionThreeD(renditionDefinition.getName());
        return (transmissionThreeD.getBlob() != null) ? Collections.singletonList(transmissionThreeD.getBlob())
                : Collections.emptyList();
    }
}
