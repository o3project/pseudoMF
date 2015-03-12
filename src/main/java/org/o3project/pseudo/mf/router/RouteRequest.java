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

import java.util.Iterator;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.o3project.pseudo.mf.data.PseudoMfData;
import org.o3project.pseudo.mf.data.RouteRequestData;
import org.o3project.pseudo.mf.lib.PseudoMfLib;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouteRequest extends ServerResource {
    private Logger logger = LoggerFactory.getLogger(RouteRequest.class);
    private PseudoMfLib lib = new PseudoMfLib();

    @Post
    public Representation getRequest(String strJson) {
        logger.debug("getRequest Strat");
        logger.debug("getData:{}", strJson);
        RouteRequestData reqData = lib.requestRoutePaser(strJson);

        String ret = checkRoute(reqData);
        if (null == ret) {
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            return null;
        }
        getResponse().setStatus(Status.SUCCESS_OK);
        logger.info("getRequest End");
        return new JsonRepresentation(new JSONObject(ret));
    }

    private String checkRoute(RouteRequestData data) {
        String key = null;
        if (null == data) {
            return null;
        }
        Iterator<Entry<String, RouteRequestData>> itr = PseudoMfData.getInstance().getRouteData()
                .entrySet().iterator();
        while (itr.hasNext()) {
            Entry<String, RouteRequestData> entry = itr.next();
            key = entry.getKey();
            RouteRequestData value = entry.getValue();
            if (value.equals(data)) {
                if (PseudoMfData.getInstance().checkRoute(key)) {
                    logger.debug("data used");
                    key = null;
                    continue;
                }
                PseudoMfData.getInstance().putRoute(key);
                break;
            }
            key = null;
        }
        return key;
    }
}
