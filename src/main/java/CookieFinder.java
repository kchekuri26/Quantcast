import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class responsible for finding the most active cookies.
 *
 * @author khushal
 */
public class CookieFinder {

    /**
     * Finds the most active cookies on a give date.
     *
     * @param cookieInfos list of CookieInfos from which to look for most active cookies
     * @param date        date (YYYY-MM-DD) to look for
     * @return list of most active cookie names
     */
    public static List<String> getMostActiveCookies(List<CookieInfo> cookieInfos, String date) {
        /* Key = cookie name and value = frequency */
        HashMap<String, Integer> map = new HashMap<>();
        int maxFreq = 0;
        for (CookieInfo cookieInfo : cookieInfos) {
            /* Check only until the given date. No need to loop over older dates as the given log file is sorted */
            if (date.compareTo(cookieInfo.getDate()) > 0) {
                break;
            }
            if (date.equals(cookieInfo.getDate())) {
                map.put(cookieInfo.getCookie(), map.getOrDefault(cookieInfo.getCookie(), 0) + 1);
                int freq = map.get(cookieInfo.getCookie());
                if (freq > maxFreq) {
                    maxFreq = freq;
                }
            }
        }
        List<String> results = new ArrayList<>();
        for (String cookie : map.keySet()) {
            if (map.get(cookie) == maxFreq) {
                results.add(cookie);
            }
        }
        return results;
    }
}