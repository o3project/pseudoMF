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

public class RouteRequestElementData {

    private RouteRequestTerminationPointsData terminationPoints;
    private String direction;
    private RouteRequestConstraintData constraint;

    public RouteRequestElementData() {
    }

    public RouteRequestTerminationPointsData getTerminationPoints() {
        return terminationPoints;
    }

    public void setTerminationPoints(RouteRequestTerminationPointsData terminationPoints) {
        this.terminationPoints = terminationPoints;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public RouteRequestConstraintData getConstraint() {
        return constraint;
    }

    public void setConstraint(RouteRequestConstraintData constraint) {
        this.constraint = constraint;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((constraint == null) ? 0 : constraint.hashCode());
        result = prime * result + ((direction == null) ? 0 : direction.hashCode());
        result = prime * result + ((terminationPoints == null) ? 0 : terminationPoints.hashCode());
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
        RouteRequestElementData other = (RouteRequestElementData) obj;
        if (constraint == null) {
            if (other.constraint != null) {
                return false;
            }
        } else if (!constraint.equals(other.constraint)) {
            return false;
        }
        if (direction == null) {
            if (other.direction != null) {
                return false;
            }
        } else if (!direction.equals(other.direction)) {
            return false;
        }
        if (terminationPoints == null) {
            if (other.terminationPoints != null) {
                return false;
            }
        } else if (!terminationPoints.equals(other.terminationPoints)) {
            return false;
        }
        return true;
    }

}
