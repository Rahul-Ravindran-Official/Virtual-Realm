package bl.helper.Objects;

public class OConfig {
    private int cid;
    private String  configName;
    private String  configValue;

    public OConfig(int cid, String configName, String configValue) {
        this.cid = cid;
        this.configName = configName;
        this.configValue = configValue;
    }
    public OConfig() {
    }

    public int getCid() {
        return cid;
    }
    public void setCid(int cid) {
        this.cid = cid;
    }
    public String getConfigName() {
        return configName;
    }
    public void setConfigName(String configName) {
        this.configName = configName;
    }
    public String getConfigValueAsString() {
        return configValue;
    }
    public Integer getConfigValueAsInteger() {
        return Integer.parseInt(configValue);
    }
    public double getConfigValueAsDouble() {
        return Double.parseDouble(String.valueOf(configValue));
    }
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

}