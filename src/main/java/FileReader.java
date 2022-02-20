import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for reading and parsing files.
 *
 * @author khushal
 */
public class FileReader {

    /**
     * Reads a given csv file and returns a list of parsed data
     *
     * @param path the file path
     * @return list of CookieInfo parsed from the given csv file
     * @throws IOException from BufferedReader
     */
    public static List<CookieInfo> getCookieInfos(Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.ISO_8859_1)) {
            List<CookieInfo> cookieInfos = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(",", 2);
                if (temp.length != 2) {
                    System.err.println("Improper record found");
                } else {
                    cookieInfos.add(new CookieInfo(temp[0], temp[1].split("T", 2)[0]));
                }
            }
            return cookieInfos;
        }
    }
}