package api.env;

public class EnvSettings {
    public static void setBaseUrl() {
        System.setProperty("baseTypicodeApiUrl", "https://jsonplaceholder.typicode.com");
    }
}
