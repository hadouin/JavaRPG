import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class InputAskerTest {

    @Test
    public void promptStringChoice() {
        String[] entries = new String[]{"Warrior", "Mage", "Healer"};

        assertPromptStringChoice(entries, "1", "Mage");
        assertPromptStringChoice(entries, "0", "Warrior");
        assertPromptStringChoice(entries, "2", "Healer");
    }

    private void assertPromptStringChoice(String[] inputEntries, String inputString, String expectedString) {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());

        System.setIn(in);
        InputAsker tmpInputAsker = new InputAsker(System.in, System.out);
        assertEquals(expectedString, inputEntries[tmpInputAsker.getStringsChoiceIndex("message:", inputEntries)]);
        // optionally, reset System.in to its original
        System.setIn(sysInBackup);
    }

    private void assertPromptStringChoice(String[] inputEntries, String[] inputStrings, String expectedString) {
        InputAsker inputAsker = new InputAsker(System.in, System.out);
        InputStream sysInBackup = System.in; // backup System.in to restore it later

        String concatString = String.join("\n", inputStrings);
        InputStream stream = new ByteArrayInputStream(concatString.getBytes(StandardCharsets.UTF_8));

        InputAsker tmpInputAsker = new InputAsker(System.in, System.out);
        assertEquals(expectedString, inputEntries[tmpInputAsker.getStringsChoiceIndex("message:", inputEntries)]);

        // optionally, reset System.in to its original
        System.setIn(sysInBackup);
    }
}