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

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * プロパティファイル読込用専用クラス
 *
 * @author TCS
 *
 */
public class PseudoMfPropertyLoader {
    private Logger logger = LoggerFactory
            .getLogger(PseudoMfPropertyLoader.class);

    private static PseudoMfPropertyLoader loader = new PseudoMfPropertyLoader();
    private static Properties conf = null;

    /** プロパティファイルのデフォルトパス */
    private static final String DEFAULT_PATH = "org/o3project/pseudo/mf/PseudoMf.properties";
    private static HashMap<String, String> prop;

    /** プロパティファイルのキー項目 */
    private enum propertyKey {
        SERVICE_PORT, INITIALIZE, COMMON_PATH, L1_FLOW_GENERATE, L1_IDEX, REQUEST_INITIALIZE,
        LOWER_PATH, UPPER_PATH, IDEX_PATH, ROUTE_PATH, L0_SETUP, L1_SETUP
    }

    /**
     * コンストラクタ
     *
     */
    private PseudoMfPropertyLoader() {

    }

    public static PseudoMfPropertyLoader createInstance() {
        return createInstance("");
    }

    public static PseudoMfPropertyLoader createInstance(String path) {
        loader.loadProperty(path);
        return loader;

    }

    public static PseudoMfPropertyLoader getInstance() {
        return loader;
    }

    /**
     * プロパティファイルの設定
     *
     * @param path
     * @return
     */
    private PseudoMfPropertyLoader loadProperty(final String path) {
        conf = new Properties();
        try {
            if (null == path || path.equals("")) {
                ClassLoader cl = PseudoMfPropertyLoader.class.getClassLoader();
                // 引数で指定されたプロパティファイルを読み込む
                conf.load(cl.getResourceAsStream(DEFAULT_PATH));

            } else {
                // 外部プロパティファイル指定時
                InputStream in = new BufferedInputStream(new FileInputStream(
                        path));
                conf.load(in);
            }
            // プロパティファイルの内容を格納
            prop = new HashMap<String, String>();
            for (int count = 0; count < propertyKey.values().length; count++) {
                logger.debug(propertyKey.values()[count].toString() + ":"
                        + getValue(propertyKey.values()[count].toString()));
                prop.put(propertyKey.values()[count].toString(),
                        getValue(propertyKey.values()[count].toString()));
            }

        } catch (IOException e) {
            logger.error("File load Error : " + path);
            e.printStackTrace();
        }

        return loader;
    }

    /**
     * プロパティファイルの値を取得
     *
     * @param key
     * @return
     */
    private String getValue(String key) {
        return conf.getProperty(key);
    }

    public int getServicePort() {
        return Integer.valueOf(prop.get(propertyKey.SERVICE_PORT.toString()))
                .intValue();
    }

    public String getInitialize() {
        return prop.get(propertyKey.INITIALIZE.toString());
    }

    public String getCommonPath() {
        return prop.get(propertyKey.COMMON_PATH.toString());
    }

    public String getL1FlowGenerate() {
        return prop.get(propertyKey.L1_FLOW_GENERATE.toString());
    }

    public String getL1Idex() {
        return prop.get(propertyKey.L1_IDEX.toString());
    }

    public String getRequestInitialize() {
        return prop.get(propertyKey.REQUEST_INITIALIZE.toString());
    }

    public String getLowerPath() {
        return System.getProperty("user.dir") + prop.get(propertyKey.LOWER_PATH.toString());
    }

    public String getUpperPath() {
        return System.getProperty("user.dir") + prop.get(propertyKey.UPPER_PATH.toString());
    }

    public String getIdExPath() {
        return System.getProperty("user.dir") + prop.get(propertyKey.IDEX_PATH.toString());
    }

    public String getRoutePath() {
        return System.getProperty("user.dir") + prop.get(propertyKey.ROUTE_PATH.toString());
    }

    public String getL0SetUpPath() {
        return prop.get(propertyKey.L0_SETUP.toString());
    }

    public String getL1SetUpPath() {
        return prop.get(propertyKey.L1_SETUP.toString());
    }

}
