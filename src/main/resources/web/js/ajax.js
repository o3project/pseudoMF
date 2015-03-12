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
function createHttpRequest(){
    // Mozilla or Firefox
    if( window.XMLHttpRequest) {
        try{
            return new XMLHttpRequest();
        }catch(e){
            return false;
        }
    }
    // IE
    else if(window.ActiveXObject) {
        try{
            // IE6.0 over
            return new ActiveXObject("Msxml2.XMLHTTP");
        }catch(e){
            // IE6.0 under
            try{
                return new ActiveXObject("Microsoft.XMLHTTP");
            }catch(e2){
                return false;
            }
        }
    }
    return false;
}

function requestFile( data , method , fileName , async ){
    var httpoj = createHttpRequest();

    httpoj.onreadystatechange = function(){
        if (httpoj.readyState==4){
            on_loaded(httpoj);
        }
    }
    httpoj.open( method , fileName , async );
    httpoj.send( data );
}

//call back
function on_loaded(obj){
    var res  = obj.responseText;
    outputMessage( res )
}

function outputMessage( msg ){
    document.getElementById('outPut').innerHTML = msg;
}