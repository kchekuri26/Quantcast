import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Driver {

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
            List<CookieInfo> cookieInfos = FileReader.getCookieInfos(Paths.get(args[0]));
            List<String> cookies = CookieFinder.getMostActiveCookies(cookieInfos, args[2]);
            for (String cookie : cookies) {
                System.out.println(cookie);
            }
        } catch (IOException e) {
            System.err.println("Not able to read " + args[0]);
        }
    }
}