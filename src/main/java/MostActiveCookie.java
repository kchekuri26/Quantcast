import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class responsible for parsing the command line arguments and printing out the results.
 *
 * @author khushal
 */
public class MostActiveCookie {

    public static final String IMPROPER_INPUT_ERROR = "Improper input. Correct format: filename.csv -d YYYY-MM-DD";
    public static final String IMPROPER_FILE_FORMAT_ERROR = "Improper file format. Correct format: .csv";

    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println(IMPROPER_INPUT_ERROR);
            System.exit(-1);
        }

        if (!args[0].endsWith(".csv")) {
            System.err.println(IMPROPER_FILE_FORMAT_ERROR);
            System.exit(-1);
        }

        if (!args[1].equals("-d")) {
            System.err.println(IMPROPER_INPUT_ERROR);
            System.exit(-1);
        }

        try {
            List<String> cookies = CookieFinder.getMostActiveCookies(FileReader.getCookieInfos(Paths.get(args[0])), args[2]);
            if (cookies.isEmpty()) {
                System.out.println("No cookies found");
            } else {
                for (String cookie : cookies) {
                    System.out.println(cookie);
                }
            }
        } catch (IOException e) {
            System.err.println("Not able to read " + args[0]);
        }
    }
}