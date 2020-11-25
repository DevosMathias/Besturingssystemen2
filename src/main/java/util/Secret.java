package util;

import java.util.Properties;

public class Secret extends Credentials {

    public Secret() {

    }

    static public void setPass(Properties dbProperties) {
        dbProperties.setProperty("user", "local_r0644058");
        dbProperties.setProperty("password", "DsYLXkfEKRùSG");
    }
}
