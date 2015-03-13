# What's PseudoMF
"Opt-transport apps of O3 orchestrator&amp;controller suite"
---
PseudoMF(Pseudo Management Functions) is a software which works instead of physical network elements managementsystem(EMS), path computation elements(PCE).

PseudoMF provides optical core network resources information to OCNRM, ID mapping information between OpenFlow and internal ID (as we call Information Model ID), route information for new resource creation.


 Environment
--------------------------
OS：Ubuntu 12.04.3(x64)  
Memory: 1GB  
Middleware: Java 7 and later, Maven2  

 Build
--------------------------

    $ cd ~
    $ git clone https://github.com/o3project/pseudoMF.git
    $ cd pseudoMF
    $ bash ./build.sh
    $ tar xvfz ./target/pseudoMf-1.0.0-bin.tar.gz –C ~/

You can choose your install directory instead of ~/.

 Configuration
--------------------------
Edit PseudoMf.properties

     $ sudo vi ~/PseudoMf.properties

set OCNRM’s REST access points. For example,

     REQUEST_INITIALIZE=http://127.0.0.1:44444/demo/node

 Starting PseudoMF
--------------------------

    $ ~/pseudoMf-1.0.0/pseudoMf.sh -s



 Stopping PseudoMF
--------------------------

    $ ~/pseudoMf-1.0.0/pseudoMf.sh -q


