package bl.helper.Objects;

import bl.helper.APIs;

public class Api {
    private String api;
    public Api(String api) {
        this.api = APIs.baseURL + api + APIs.apiExtension;
    }
    public String getApi() {
        return api;
    }
}
