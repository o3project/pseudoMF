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

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdExchangeResponseData {

    private String dpid;
    private String odutype;
    private String port;
    private String tpn;
    private String ts;

    public IdExchangeResponseData() {
    }

    public String getDpid() {
        return dpid;
    }

    public void setDpid(String dpid) {
        if (null != dpid && !dpid.equals("")) {
            this.dpid = dpid;
        }
    }

    public String getOdutype() {
        return odutype;
    }

    public void setOdutype(String odutype) {
        if (null != odutype && !odutype.equals("")) {
            this.odutype = odutype;
        }
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        if (null != port && !port.equals("")) {
            this.port = port;
        }
    }

    public String getTpn() {
        return tpn;
    }

    public void setTpn(String tpn) {
        if (null != tpn && !tpn.equals("")) {
            this.tpn = tpn;
        }
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        if (null != ts && !ts.equals("")) {
            this.ts = ts;
        }
    }

}
