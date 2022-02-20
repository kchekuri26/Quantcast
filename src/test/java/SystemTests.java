import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * Tests the entire program for correct output.
 *
 * @author khushal
 */
public class SystemTests {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /* Setting the std. output to output stream before test so that we can check for correct output */
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    /* Restore std. output to System,out after the test */
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Check for single output")
    void testSingleOutput() {
        String[] args = {"test.csv", "-d", "2018-12-09"};
        MostActiveCookie.main(args);
        Assertions.assertEquals("AtY0laUfhglK3lC7", outContent.toString().trim());
    }

    @Test
    @DisplayName("Check for multiple outputs")
    void testMultipleOutputs() {
        String[] args = {"test.csv", "-d", "2018-12-08"};
        MostActiveCookie.main(args);
        String[] expected = {"SAZuXPGUrfbcn5UA", "4sMM2LxV07bPJzwf", "fbcn5UAVanZf6UtG"};
        String[] actual = outContent.toString().trim().split("\\R");
        Arrays.sort(expected);
        Arrays.sort(actual);
        Assertions.assertTrue(expected.length == actual.length && Arrays.equals(expected, actual));
    }

    @Test
    @DisplayName("Check for no outputs")
    void testZeroOutputs() {
        String[] args = {"test.csv", "-d", "2018-12-06"};
        MostActiveCookie.main(args);
        Assertions.assertEquals("No cookies found", outContent.toString().trim());
    }

}