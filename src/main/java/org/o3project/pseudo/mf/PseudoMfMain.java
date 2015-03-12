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
package org.o3project.pseudo.mf;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PseudoMfMain {
    private static Logger logger = LoggerFactory.getLogger(PseudoMfMain.class);

    private static PseudoMfPropertyLoader loader;

    public static void main(String[] args) throws Exception {

        logger.debug("main() Start");

        // プロパティファイル呼び出し
        if (args.length == 0) {
            loadProperties(null);
        } else {
            loadProperties(args[0]);
        }

        Component component = new Component();
        component.getServers().add(Protocol.HTTP, loader.getServicePort());
        component.getDefaultHost().attach(loader.getCommonPath(), new PseudoMfRouter());
        component.getDefaultHost().attach("/ui/", new PseudoMfGuiRouter());
        component.getContext().getParameters().add("maxThreads", "10");
        component.start();

        logger.debug("CurrentDir:{}", System.getProperty("user.dir"));

        logger.info("## dummyMC GUI URI: http://<HOST>:{}/ui/index.html", loader.getServicePort());
        logger.debug("main() End");
    }

    /**
     * プロパティファイル読込
     *
     * @param param
     * @return
     */
    private static PseudoMfPropertyLoader loadProperties(String param) {

        logger.debug("loadProperties() Start");

        // プロパティファイルの内容をロード
        loader = null;
        String filename = "";

        if (null != param) {
            filename = param;
        }
        try {
            loader = PseudoMfPropertyLoader.createInstance(filename);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("loadProperties() End");
        return loader;
    }
}
