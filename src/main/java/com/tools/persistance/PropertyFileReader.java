package com.tools.persistance;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
    public static String getEnvConstantsItem(String key) {

        String ENV_PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator + System.getProperty("runEnv") + File.separator;

        Properties prop = new Properties();
        InputStream input = null;

        String value = "";

        try {
            input = new FileInputStream(ENV_PATH + "environmentConstants.properties");
            prop.load(input);

            value = prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}
