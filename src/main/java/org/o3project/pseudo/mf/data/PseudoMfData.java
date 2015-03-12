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
package org.o3project.pseudo.mf.data;

import java.util.HashMap;
import java.util.Map;

import org.o3project.pseudo.mf.lib.PseudoMfLib;

public class PseudoMfData {

    private String lowerData;
    private String upperData;
    private Map<String, String> idExData;
    private Map<String, RouteRequestData> routeData;

    private Map<String, Integer> routeCheck;

    private PseudoMfLib lib;
    private static PseudoMfData instance;

    private PseudoMfData() {
        lib = new PseudoMfLib();
        routeCheck = new HashMap<String, Integer>();
    }

    public static PseudoMfData getInstance() {
        if (null == instance) {
            instance = new PseudoMfData();
        }
        return instance;
    }

    public void initialize() {
        setLowerData(lib.lowerDataFlieLoad());
        setUpperData(lib.upperDataFlieLoad());
        setIdExData(lib.idExDataFlieLoad());
        setRouteData(lib.routeDataFlieLoad());

        routeCheck.clear();
    }

    public String getLowerData() {
        return lowerData;
    }

    private void setLowerData(String lowerData) {
        this.lowerData = lowerData;
    }

    public String getUpperData() {
        return upperData;
    }

    private void setUpperData(String upperData) {
        this.upperData = upperData;
    }

    public Map<String, String> getIdExData() {
        return idExData;
    }

    private void setIdExData(Map<String, String> idExData) {
        this.idExData = idExData;
    }

    public Map<String, RouteRequestData> getRouteData() {
        return routeData;
    }

    private void setRouteData(Map<String, RouteRequestData> routeData) {
        this.routeData = routeData;
    }

    public void putRoute(String data) {
        routeCheck.put(data, 1);
    }

    public boolean checkRoute(String data) {
        if (null == routeCheck.get(data)) {
            return false;
        }
        return true;
    }

}
