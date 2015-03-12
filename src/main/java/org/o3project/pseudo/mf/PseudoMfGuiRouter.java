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
package org.o3project.pseudo.mf;

import org.restlet.Application;
import org.restlet.Client;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PseudoMfGuiRouter extends Application {
    private Logger logger = LoggerFactory.getLogger(PseudoMfGuiRouter.class);

    @Override
    public synchronized Restlet createInboundRoot() {
        logger.debug("createInboundRoot() Start");
        Router router = new Router(getContext());
        // gui
        router.attach("", new Directory(getContext(), "clap://classloader/web/"));
        getContext().setClientDispatcher(new Client(getContext(), Protocol.CLAP));
        logger.debug("createInboundRoot() End");
        return router;
    }

}
