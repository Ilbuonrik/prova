package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    static final Properties properties = new Properties();

    public Properties getConfig() {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("application.properties");

       if (is ==null){
           System.out.println("application prop not found");
       }

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }

    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}
