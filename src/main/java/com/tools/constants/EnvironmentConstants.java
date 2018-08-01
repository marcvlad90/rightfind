package com.tools.constants;

import com.tools.persistance.PropertyFileReader;

public class EnvironmentConstants {
    public static final String BASE_URL = PropertyFileReader.getEnvConstantsItem("BASE_URL");
    public static final String TEST_USER_NAME = PropertyFileReader.getEnvConstantsItem("TEST_USER_NAME");
    public static final String TEST_USER_PASSWORD = PropertyFileReader.getEnvConstantsItem("TEST_USER_PASSWORD");
}
