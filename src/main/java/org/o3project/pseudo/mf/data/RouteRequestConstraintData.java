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

public class RouteRequestConstraintData {
    private String bandwidth;
    private String latency;

    public RouteRequestConstraintData() {
    }

    public String getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth = bandwidth;
    }

    public String getLatency() {
        return latency;
    }

    public void setLatency(String latency) {
        this.latency = latency;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bandwidth == null) ? 0 : bandwidth.hashCode());
        result = prime * result + ((latency == null) ? 0 : latency.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RouteRequestConstraintData other = (RouteRequestConstraintData) obj;
        if (bandwidth == null) {
            if (other.bandwidth != null) {
                return false;
            }
        } else if (!bandwidth.equals(other.bandwidth)) {
            return false;
        }
        if (latency == null) {
            if (other.latency != null) {
                return false;
            }
        } else if (!latency.equals(other.latency)) {
            return false;
        }
        return true;
    }

}
