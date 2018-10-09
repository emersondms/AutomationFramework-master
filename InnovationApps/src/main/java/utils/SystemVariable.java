package utils;

public class SystemVariable {
	 private static final String PROPS_SYSTEM_UNDER_TEST = "system";
	    private static final String PROPS_SYSTEM_DEFAULT_UNDER_TEST = "QA";
	    private static final String PROPS_SYSTEM_URL = "target.url";
	    private static final String PROPS_SYSTEM_DEFAULT_URL = "https://bosch-demo.hypeinnovation.cloud/login?/servlet/hype";
	    public static final String PROPS_BROWSER = "browsername";
	    // The Type of the Browser on which the script should run.
	    // Accepted values - IE,CHROME,FIREFOX
	    public static final String PROPS_BROWSER_DEFAULT_VALUE = "FIREFOX"; 
	    public static final String PROPS_SUITE_NAME = "suitename";
	    public static final String PROPS_SUITE_NAME_DEFAULT = "SmokeTest";

	    public static final String SYSTEM_UNDER_TEST = System.getProperty(PROPS_SYSTEM_UNDER_TEST, PROPS_SYSTEM_DEFAULT_UNDER_TEST);
	    public static final String URL_UNDER_TEST = System.getProperty(PROPS_SYSTEM_URL, PROPS_SYSTEM_DEFAULT_URL);
	    public static final String BROWSER = System.getProperty(PROPS_BROWSER, PROPS_BROWSER_DEFAULT_VALUE);
	    public static final String SUITE_NAME = System.getProperty(PROPS_SUITE_NAME, PROPS_SUITE_NAME_DEFAULT);
	    public static final String USER_DIR = System.getProperty("user.dir");
	    public static final String PROPS_PROXY = "proxy";
	    public static final String PROXY_URL_DEFAULT_VALUE = "http://rbins.bosch.com/fe.pac";
}
