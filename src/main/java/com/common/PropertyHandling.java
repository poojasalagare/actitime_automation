package com.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyHandling {
    FileInputStream file;
    Properties properties;
    public PropertyHandling(String fileName) throws IOException {
         String filePath= System.getProperty("user.dir")+"/src/test/"+fileName;
         file=new FileInputStream(filePath);
         properties=new Properties();
         properties.load(file);

    }

    //get property from config.properties file based on key
    public String getProperty(String key)
    {
       String value=properties.getProperty(key);
       return value;
    }

}
