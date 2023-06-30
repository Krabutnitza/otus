package helper;

import org.apache.commons.lang3.StringUtils;

public class TestPreparation {
    public String getBaseUrl() {
        return StringUtils.stripEnd(System.getProperty("webdriver.base.url"), "/");
    }

}
