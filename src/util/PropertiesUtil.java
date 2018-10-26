package util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by rio on 2018/10/26.
 */
public class PropertiesUtil {

    public static Properties getPropertiesByType(String type) {
        if (type == null || "".equals(type)) {
            return getDefaultProperties();
        } else {
            Properties prop = null;
            try {
                Method method = PropertiesUtil.class.getMethod("get" + type + "Properties", new Class[]{String.class});
                prop = (Properties) method.invoke(null,new Object[]{type});
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
            return prop;
        }
    }

    public static Properties getDefaultProperties() {
        Properties wspDef = new Properties();
        try {
            wspDef.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("WebServiceInfo.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        return wspDef;
    }
}
