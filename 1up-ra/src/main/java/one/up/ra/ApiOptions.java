package one.up.ra;

public class ApiOptions {

    private final int minApiVersion;

    public ApiOptions(int minApiVersion) {
        this.minApiVersion = minApiVersion;
    }

    public int getMinApiVersion() {
        return minApiVersion;
    }
}
