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

import java.io.IOException;

import org.json.JSONObject;
import org.o3project.pseudo.mf.data.PseudoMfData;
import org.o3project.pseudo.mf.data.IdExchangeRequestData;
import org.o3project.pseudo.mf.data.IdExchangeResponseData;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IdExchange extends ServerResource {
    private Logger logger = LoggerFactory.getLogger(IdExchange.class);

    /*
     * strJsonは{"fjFlowId":"xxxxx","terminationPoint":"xxxxxxx"}
     * で入ってくる。
     * */
    @Post
    public Representation getRequest(String strJson) {
        logger.debug("IdExchange Request Start");
        logger.debug("getData:{}", strJson);
        IdExchangeRequestData idExReq = requestPaser(strJson);
        if (null == idExReq) {
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            return null;
        }
        logger.debug("terminationPoint:{}", idExReq.getTerminationPoint());
        String ret = responseJson(PseudoMfData.getInstance().getIdExData()
                .get("\"" + idExReq.getTerminationPoint() + "\""));

        if (null == ret) {
            getResponse().setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
            return null;
        }

        getResponse().setStatus(Status.SUCCESS_OK);
        logger.debug("IdExchange Request End");
        return new JsonRepresentation(new JSONObject(ret));
    }

    private IdExchangeRequestData requestPaser(String strJson) {
        IdExchangeRequestData idEx = null;
        if (null == strJson) {
            return null;
        }
        try {
            JSONObject jsonObj = new JSONObject(strJson);
            ObjectMapper mapper = new ObjectMapper();
            idEx = mapper.readValue(jsonObj.toString(), IdExchangeRequestData.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idEx;
    }

    private String responseJson(String strJson) {
        String ret = null;
        if (null == strJson) {
            return null;
        }
        try {
            JSONObject jsonObj = new JSONObject(strJson);
            ObjectMapper mapper = new ObjectMapper();
            ret = mapper.writeValueAsString(mapper.readValue(jsonObj.toString(),
                    IdExchangeResponseData.class));
            logger.debug("resData:{}", ret);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;

    }
}
