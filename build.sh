#! /bin/bash
mvn install:install-file -Dfile=./lib/org.restlet.jar -DgroupId=org.restlet -DartifactId=restlet -Dversion=2.1.3 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=./lib/org.restlet.ext.json.jar -DgroupId=org.restlet.ext.json -DartifactId=restlet.ext.json -Dversion=2.1.3 -Dpackaging=jar -DgeneratePom=true
chmod +x ./pseudoMf.sh
sleep 1
mvn package

