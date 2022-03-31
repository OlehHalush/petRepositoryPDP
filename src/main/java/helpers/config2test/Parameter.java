package helpers.config2test;

/**
 * parameters from run.properties
 */
public enum Parameter {

    ACCOUNT_PROSPECT_NAME("data.account.prospect.name", Config.PARALLEL),
    ACCOUNT_CUSTOMER_NAME("data.account.customer.name", Config.PARALLEL),
    ACCOUNT_WHOLESALER_NAME("data.account.wholesaler.name", Config.PARALLEL),
    ACCOUNT_HEADOFFICE_NAME("data.account.headoffice.name", Config.PARALLEL),
    ACCOUNT_PROSPECT_MINIMAL_NAME("data.account.prospect.minimal.name", Config.PARALLEL),
    ACCOUNT_CUSTOMER_MINIMAL_NAME("data.account.customer.minimal.name", Config.PARALLEL),
    ACCOUNT_TERRITORY("data.account.territory"),
    ACCOUNT_TEAM_MEMBER("data.account.team_member"),
    CONTACT_EMAIL("data.email.contact"),
    CONGA_SIGN_PASSWORD("data.email.conga_sign.password"),

    RUN_MARKET("run.market"),
    RUN_LANGUAGE("run.language"),
    SALES_REP_USERNAME("sales_rep.user.username", Config.PARALLEL),
    SALES_REP_PASSWORD("sales_rep.user.password", Config.PARALLEL),
    SALES_MANAGER_USERNAME("sales_manager.user.username", Config.PARALLEL),
    SALES_MANAGER_PASSWORD("sales_manager.user.password", Config.PARALLEL),
    SALES_REP_SECONDARY_USERNAME("sales_rep_secondary.user.username", Config.PARALLEL),
    SALES_REP_SECONDARY_PASSWORD("sales_rep_secondary.user.password", Config.PARALLEL),
    ADMIN_USERNAME("admin.user.username"),
    ADMIN_PASSWORD("admin.user.password"),
    DEFAULT_USER("default.user"),
    SALES_REP_APPIUM_URL("sales_rep.appium.url", Config.DEFAULT_APPIUM_URL),
    SALES_REP_DEVICE_NAME("sales_rep.appium.device_name", Config.DEFAULT_DEVICE_NAME),
    SALES_REP_PLATFORM_VERSION("sales_rep.appium.platform_version", Config.DEFAULT_PLATFORM_VERSION),
    SALES_MANAGER_APPIUM_URL("sales_manager.appium.url", Config.DEFAULT_APPIUM_URL),
    SALES_MANAGER_DEVICE_NAME("sales_manager.appium.device_name", Config.DEFAULT_DEVICE_NAME),
    SALES_MANAGER_PLATFORM_VERSION("sales_manager.appium.platform_version", Config.DEFAULT_PLATFORM_VERSION),
    SALES_REP_SECONDARY_APPIUM_URL("sales_rep_secondary.appium.url", Config.DEFAULT_APPIUM_URL),
    SALES_REP_SECONDARY_DEVICE_NAME("sales_rep_secondary.appium.device_name", Config.DEFAULT_DEVICE_NAME),
    SALES_REP_SECONDARY_PLATFORM_VERSION("sales_rep_secondary.appium.platform_version", Config.DEFAULT_PLATFORM_VERSION),
    ORG_URL("org.url"),
    APP("appium.app"),
    REPORTER_VIDEO("reporter.video"),
    CLIENT_ID("client_id"),
    CLIENT_SECRET("client_secret");

    private final String key;
    private String defaultKey;
    private boolean isParallel, hasDefaultKey;

    Parameter(String key, Config... configs) {
        this.key = key;
        for (Config config : configs) {
            switch (config) {
                case PARALLEL:
                    isParallel = true;
                    break;
                case DEFAULT_APPIUM_URL:
                    hasDefaultKey = true;
                    defaultKey = "default.appium.url";
                    break;
                case DEFAULT_DEVICE_NAME:
                    hasDefaultKey = true;
                    defaultKey = "default.appium.device_name";
                    break;
                case DEFAULT_PLATFORM_VERSION:
                    hasDefaultKey = true;
                    defaultKey = "default.appium.platform_version";
                    break;
                default:
                    throw new IllegalStateException("Not implemented for " + config);
            }
        }
    }

    public String get() {
        return isParallel
                ? (hasDefaultKey ? ConfigReader.getParallelRunParameter(key, defaultKey) : ConfigReader.getParallelRunParameter(key))
                : (hasDefaultKey ? ConfigReader.getRunParameter(key, defaultKey) : ConfigReader.getRunParameter(key));
    }

    private enum Config {
        PARALLEL,
        DEFAULT_APPIUM_URL,
        DEFAULT_DEVICE_NAME,
        DEFAULT_PLATFORM_VERSION
    }
}
