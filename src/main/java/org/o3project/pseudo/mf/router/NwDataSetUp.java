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

import org.o3project.pseudo.mf.PseudoMfPropertyLoader;
import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NwDataSetUp extends ServerResource {
    private Logger logger = LoggerFactory.getLogger(NwDataSetUp.class);

    private PseudoMfPropertyLoader loader = PseudoMfPropertyLoader
            .getInstance();

    @Get
    public abstract Representation getMessage();

    protected Representation sendServer(final String data) {
        logger.debug("sendServer Start");
        ClientResource client = new ClientResource(loader.getRequestInitialize());
        Client ct = new Client(new Context(), Protocol.HTTP);
        client.setNext(ct);
        StringRepresentation srp = new StringRepresentation(data);

        Representation representation = null;
        try {
            representation = client.post(srp);
            String tmp = representation.toString();
            if (tmp == null) {
                logger.error("Representation is null.");
            }
            logger.debug("Representation:" + tmp);
        } catch (Exception e) {
            logger.error("catch exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (representation != null) {
                representation.release();
            }
            srp.release();
            client.release();

            representation = null;
            srp = null;
            client = null;
            try {
                ct.stop();
            } catch (Exception e) {
                //    e.printStackTrace();
            }
            ct = null;
        }
        logger.debug("sendServer End");
        return representation;
    }

}
