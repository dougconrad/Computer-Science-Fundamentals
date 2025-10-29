package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class WaltzGeneratorTest {
    WaltzGenerator wg;

    @BeforeEach
    public void setup() {
        wg = new WaltzGenerator();
    }

    @Test
    public void rollDiceIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> wg.rollDice(0));
        assertThrows(IllegalArgumentException.class, () -> wg.rollDice(-1));
    }

    @Test
    public void rollDiceRolls() {
        for (int i = 1; i < 15; i++) {
            int total = wg.rollDice(i);
            int upperBounds = 6 * i;
            int lowerBounds = i;
            assertTrue((total >= lowerBounds) && (total <= upperBounds));
        }
    }

    @Test
    public void buildTableForMinuet() throws IOException {
        List<String> input = List.of("2", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        String minuetFilePath = "res/minuet.csv";
        String[][] expected = new String[13][];
        {
            expected[0] = new String[0];
            expected[1] = new String[0];
            for (int i = 2; i < 13; i++) {
                expected[i] = Files.readAllLines(Paths.get(minuetFilePath)).get(i).split(", ");
            }

        }
        assertArrayEquals(expected, wg.buildTable(input));
    }

    @Test
    public void buildTableForTrio() throws IOException {
        List<String> input = List.of("1", "1", "2", "3", "4", "5", "6");
        String trioFilePath = "res/trio.csv";
        String[][] expected = new String[7][];
        {
            expected[0] = new String[0];
            for (int i = 1; i < 7; i++) {
                expected[i] = Files.readAllLines(Paths.get(trioFilePath)).get(i).split(", ");
            }
        }
        assertArrayEquals(expected, wg.buildTable(input));
    }
}