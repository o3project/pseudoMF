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

import org.o3project.pseudo.mf.router.IdExchange;
import org.o3project.pseudo.mf.router.L0SetUp;
import org.o3project.pseudo.mf.router.L1SetUp;
import org.o3project.pseudo.mf.router.McInitialize;
import org.o3project.pseudo.mf.router.RouteRequest;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PseudoMfRouter extends Application {
    private Logger logger = LoggerFactory.getLogger(PseudoMfRouter.class);
    private PseudoMfPropertyLoader loader = PseudoMfPropertyLoader.getInstance();

    @Override
    public synchronized Restlet createInboundRoot() {
        logger.debug("createInboundRoot() Start");
        Router router = new Router(getContext());
        router.attach(loader.getInitialize(), McInitialize.class);
        router.attach(loader.getL0SetUpPath(), L0SetUp.class);
        router.attach(loader.getL1SetUpPath(), L1SetUp.class);
        router.attach(loader.getL1FlowGenerate(), RouteRequest.class);
        router.attach(loader.getL1Idex(), IdExchange.class);
        logger.debug("createInboundRoot() End");
        return router;
    }

}
