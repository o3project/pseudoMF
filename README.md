README
==========================

 Software Installation
--------------------------
[Install jdk1.7, maven, and set proxy for maven.]

 Build PseudoMF
--------------------------

    $ bash ./buld.sh
    $ cd ./target
    $ cp -p ./pseudoMf-1.0.0-bin.tar.gz PATH/TO/INSTALL/DIR
    $ cd PATH/TO/INSTALL/DIR
    $ tar xvfz ./pseudoMf-1.0.0-bin.tar.gz
    $ cd pseudoMf-1.0.0-bin
    $ vi ./PseudoMf.properties

     check REQUEST_INITIALIZE.

     > REQUEST_INITIALIZE=http://127.0.0.1:44444/demo/node

     ex. if OCNRM Server Address:Port is 192.168.1.100:44444 then
     REQUEST_INITIALIZE=http://192.168.1.100:44444/demo/node

 Starting PseudoMF
--------------------------

    $ ./pseudoMf.sh -s


 Stopping PseudoMF
--------------------------

    $ ./pseudoMf.sh -q

