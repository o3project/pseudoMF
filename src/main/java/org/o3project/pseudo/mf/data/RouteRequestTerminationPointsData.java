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

public class RouteRequestTerminationPointsData {

    private String inPoint;
    private String outPoint;

    public RouteRequestTerminationPointsData() {
    }

    public String getInPoint() {
        return inPoint;
    }

    public void setInPoint(String inPoint) {
        this.inPoint = inPoint;
    }

    public String getOutPoint() {
        return outPoint;
    }

    public void setOutPoint(String outPoint) {
        this.outPoint = outPoint;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((inPoint == null) ? 0 : inPoint.hashCode());
        result = prime * result + ((outPoint == null) ? 0 : outPoint.hashCode());
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
        RouteRequestTerminationPointsData other = (RouteRequestTerminationPointsData) obj;
        if (inPoint == null) {
            if (other.inPoint != null) {
                return false;
            }
        } else if (!inPoint.equals(other.inPoint)) {
            return false;
        }
        if (outPoint == null) {
            if (other.outPoint != null) {
                return false;
            }
        } else if (!outPoint.equals(other.outPoint)) {
            return false;
        }
        return true;
    }

}
