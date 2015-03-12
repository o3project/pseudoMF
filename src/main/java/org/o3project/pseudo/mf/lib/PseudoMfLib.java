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
package org.o3project.pseudo.mf.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.o3project.pseudo.mf.PseudoMfPropertyLoader;
import org.o3project.pseudo.mf.PseudoMfRouter;
import org.o3project.pseudo.mf.data.RouteRequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PseudoMfLib {

    private final String lowerPath = PseudoMfPropertyLoader.getInstance().getLowerPath();
    private final String upperPath = PseudoMfPropertyLoader.getInstance().getUpperPath();
    private final String idexPath = PseudoMfPropertyLoader.getInstance().getIdExPath();
    private final String routePath = PseudoMfPropertyLoader.getInstance().getRoutePath();

    private Logger logger = LoggerFactory.getLogger(PseudoMfRouter.class);

    public PseudoMfLib() {
    }

    public String lowerDataFlieLoad() {
        return initializeDataFlieLoad(lowerPath);
    }

    public String upperDataFlieLoad() {
        return initializeDataFlieLoad(upperPath);
    }

    public Map<String, String> idExDataFlieLoad() {
        Map<String, String> restMap = new HashMap<String, String>();
        ArrayList<String> fileData = commonFlieLoad(idexPath);

        if (null == fileData || 0 == fileData.size()) {
            return null;
        }

        for (String strTmp : fileData) {
            String[] splitData = strTmp.split(":", 2);
            if (2 != splitData.length) {
                continue;
            }

            logger.debug("key:{}", splitData[0]);
            logger.debug("value:{}", splitData[1]);

            restMap.put(splitData[0], splitData[1]);

        }
        return restMap;
    }

    public Map<String, RouteRequestData> routeDataFlieLoad() {
        Map<String, RouteRequestData> restMap = new HashMap<String, RouteRequestData>();
        ArrayList<String> fileData = commonFlieLoad(routePath);

        if (null == fileData || 0 == fileData.size()) {
            return null;
        }

        for (String strTmp : fileData) {
            String[] splitData = strTmp.split("::");
            if (2 != splitData.length) {
                continue;
            }
            logger.debug("Route key:{}", splitData[0]);
            logger.debug("Route value:{}", splitData[1]);

            restMap.put(splitData[1], requestRoutePaser(splitData[0]));
        }

        return restMap;
    }

    public RouteRequestData requestRoutePaser(String strJson) {

        RouteRequestData rrd = null;
        try {
            JSONObject jsonObj = new JSONObject(strJson);
            ObjectMapper mapper = new ObjectMapper();
            rrd = mapper.readValue(jsonObj.toString(), RouteRequestData.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rrd;
    }

    private String initializeDataFlieLoad(final String path) {
        ArrayList<String> fileData = commonFlieLoad(path);

        if (null != fileData && 1 == fileData.size()) {
            return fileData.get(0);
        }
        return null;
    }

    private ArrayList<String> commonFlieLoad(final String path) {
        ArrayList<String> fileData = new ArrayList<String>();

        File file = new File(path);
        if (!checkFlie(file)) {
            System.out.println("Flie Not Found :" + path);
            return null;
        }

        try {
            String strTemp;
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((strTemp = br.readLine()) != null) {
                fileData.add(strTemp);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }

    private boolean checkFlie(final File file) {
        if (file.exists()) {
            if (file.isFile() && file.canRead()) {
                return true;
            }
        }
        return false;
    }

}
