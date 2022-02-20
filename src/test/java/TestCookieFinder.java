import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestCookieFinder {

    @Test
    @DisplayName("Check for date that does not exist")
    void testNonExistentDate() {
        Assertions.assertEquals(Collections.emptyList(), CookieFinder.getMostActiveCookies(TestFileReader.getCookieInfoList(), "2020-01-26"));
    }

    @Test
    @DisplayName("Check for single output")
    void testSingleOutput() {
        List<String> expected = new ArrayList<>();
        expected.add("AtY0laUfhglK3lC7");
        List<String> actual = CookieFinder.getMostActiveCookies(TestFileReader.getCookieInfoList(), "2018-12-09");
        Assertions.assertTrue(actual.size() == 1 && expected.equals(actual));
    }

    @Test
    @DisplayName("Check for multiple outputs")
    void testMultipleOutputs() {
        List<String> expected = new ArrayList<>();
        expected.add("SAZuXPGUrfbcn5UA");
        expected.add("4sMM2LxV07bPJzwf");
        expected.add("fbcn5UAVanZf6UtG");
        List<String> actual = CookieFinder.getMostActiveCookies(TestFileReader.getCookieInfoList(), "2018-12-08");
        Assertions.assertTrue(expected.size() == actual.size() && expected.containsAll(actual));
    }
}