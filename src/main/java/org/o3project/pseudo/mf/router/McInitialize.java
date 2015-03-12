/*
* Copyright 2015 FUJITSU LIMITED.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.o3project.pseudo.mf.router;

import org.o3project.pseudo.mf.data.PseudoMfData;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class McInitialize extends ServerResource {

    public McInitialize() {
    }

    @Get
    public Representation getMessage() {
        PseudoMfData.getInstance().initialize();
        return new StringRepresentation((Status.SUCCESS_OK).toString(), MediaType.TEXT_PLAIN);
    }
}
