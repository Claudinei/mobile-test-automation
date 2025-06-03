package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    public static Properties load() throws IOException {
        String env = getMainConfig().getProperty("env");
        String path = "config/config-" + env + ".properties";
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        }
        return props;
    }

    private static Properties getMainConfig() throws IOException {
        Properties main = new Properties();
        try (FileInputStream fis = new FileInputStream("config/config.properties")) {
            main.load(fis);
        }
        return main;
    }
}
