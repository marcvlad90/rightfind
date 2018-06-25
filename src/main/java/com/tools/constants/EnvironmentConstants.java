package com.tools.constants;

import com.tools.persistance.PropertyFileReader;

public class EnvironmentConstants {
    public static final String BASE_URL = PropertyFileReader.getEnvConstantsItem("BASE_URL");
}
