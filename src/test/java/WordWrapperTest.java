import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for wrapping words.
 */
class WordWrapperTest {
    @Test
    @DisplayName("Invalid line length")
    void invalidLength() {
        assertThrows(AssertionError.class, () -> WordWrapper.wrap("Anything", 0));
    }

    @Test
    @DisplayName("Empty string doesn't need splitting")
    void emptyText() {
        String lines = WordWrapper.wrap("", 50);

        assertEquals("", lines);
    }

    @Test
    @DisplayName("Single line doesn't need splitting")
    void singleLine() {
        String lines = WordWrapper.wrap("Just a single line", 50);

        assertEquals("Just a single line", lines);
    }

    @Test
    @DisplayName("Split across multiple lines")
    void multipleLines() {
        String lines = WordWrapper.wrap("A longer sentence that needs to be split across lines", 20);

        assertEquals("A longer sentence\nthat needs to be\nsplit across lines", lines);
    }

    @Test
    @DisplayName("Split long word across lines")
    void longWord() {
        String lines = WordWrapper.wrap("A verylongwordthatneedstobesplit across lines", 10);

        assertEquals("A\nverylongwo\nrdthatneed\nstobesplit\nacross\nlines", lines);
    }

    @Test
    @DisplayName("Lots of whitespace at split")
    void lotsOfWhitespace() {
        String lines = WordWrapper.wrap("Lots of               whitespace", 10);

        assertEquals("Lots of\nwhitespace", lines);
    }

    @Test
    @DisplayName("Multiple types of whitespace")
    void whitespace() {
        String lines = WordWrapper.wrap("A sentence with  compound  whitespace \n\r and some\n\t in lines", 20);

        assertEquals("A sentence with\ncompound  whitespace\nand some\n\t in lines", lines);
    }

    @Test
    @DisplayName("Text is even multiple of line length")
    void evenLines() {
        String lines = WordWrapper.wrap("Some even word list", 4);

        assertEquals("Some\neven\nword\nlist", lines);
    }


    @Test
    @DisplayName("Whitespace on end")
    void trailingSpace() {
        String lines = WordWrapper.wrap("Word ", 4);

        assertEquals("Word", lines);
    }

    @Test
    @DisplayName("Whitespace on end after split")
    void longTrailingSpace() {
        String lines = WordWrapper.wrap("Short line          ", 12);

        assertEquals("Short line", lines);
    }
}