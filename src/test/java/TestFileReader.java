import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestFileReader {

    @Test
    @DisplayName("Check for Incorrect file")
    void testIncorrectFile() {
        Assertions.assertThrows(IOException.class, () -> FileReader.getCookieInfos(Paths.get("incorrectFile.csv")));
    }

    @Test
    @DisplayName("Check for correct reading")
    void testCorrectRead() {
        CookieInfo info1 = new CookieInfo("AtY0laUfhglK3lC7", "2018-12-09");
        CookieInfo info2 = new CookieInfo("SAZuXPGUrfbcn5UA", "2018-12-09");
        CookieInfo info3 = new CookieInfo("5UAVanZf6UtGyKVS", "2018-12-09");
        CookieInfo info4 = new CookieInfo("AtY0laUfhglK3lC7", "2018-12-09");
        CookieInfo info5 = new CookieInfo("SAZuXPGUrfbcn5UA", "2018-12-08");
        CookieInfo info6 = new CookieInfo("4sMM2LxV07bPJzwf", "2018-12-08");
        CookieInfo info7 = new CookieInfo("fbcn5UAVanZf6UtG", "2018-12-08");
        CookieInfo info8 = new CookieInfo("4sMM2LxV07bPJzwf", "2018-12-07");
        List<CookieInfo> expected = new ArrayList<>(Arrays.asList(info1, info2, info3, info4, info5, info6, info7, info8));
        try {
            List<CookieInfo> actual = FileReader.getCookieInfos(Paths.get("src/test/resources/testFile.csv"));
            Assertions.assertTrue(expected.size() == actual.size() && equalElements(expected, actual));
        } catch (IOException e) {
            Assertions.fail("IOException", e);
        }
    }

    private static boolean equalElements(List<CookieInfo> expected, List<CookieInfo> actual) {
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).getCookie().equals(actual.get(i).getCookie()) || !expected.get(i).getDate().equals(actual.get(i).getDate())) {
                return false;
            }
        }
        return true;
    }

    @Test
    @DisplayName("Check for empty file")
    void testEmptyFile() {
        try {
            Assertions.assertEquals(0, FileReader.getCookieInfos(Paths.get("src/test/resources/empty.csv")).size());
        } catch (IOException e) {
            Assertions.fail("IOException", e);
        }
    }
}