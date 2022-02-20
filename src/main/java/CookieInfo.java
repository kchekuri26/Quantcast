/**
 * Data structure to store cookie name and date
 *
 * @author khushal
 */
public class CookieInfo {

    private final String cookie;
    private final String date;

    public CookieInfo(String cookie, String date) {
        this.cookie = cookie;
        this.date = date;
    }

    public String getCookie() {
        return cookie;
    }

    public String getDate() {
        return date;
    }
}