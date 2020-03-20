package bl.helper;

import bl.helper.Objects.Api;


public class APIs {

    private static String protocol = "http://";
    private static String ip = "63.142.251.219";
    private static String path1 = "/backendless_io/user/";
    private static String userKey = Secrets.backendlessUserKey;
    private static String path2 = "/api/";
    private static String projectKey = Secrets.backendlessProjectKey;
    private static String path3 = "/";
    public static String baseURL = protocol + ip + path1 + userKey + path2 + projectKey + path3;

    public static Api getConfig = new Api("get_config.php");
    public static Api getRestaurants = new Api("get_restaurants.php");
    public static Api getWaiters = new Api("get_waiters_for_restaurants.php");
    public static Api getMenuItems = new Api("get_menu_items.php");
    public static Api getJobs = new Api("get_jobs.php");
    public static Api getJobRequirements = new Api("get_job_requirements.php");
    public static Api getHouses = new Api("get_houses.php");
    public static Api getSalonHairStyles = new Api("get_salon_hair_styles.php");
    public static Api getMap = new Api("get_map.php");

}
