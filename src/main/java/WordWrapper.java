import java.util.ArrayList;
import java.util.List;

/**
 * Utility for wrapping words to fit the line length.
 */
public class WordWrapper {
    private final List<String> lines;
    private String text;
    private final int lineLength;
    private int lineBreak;
    private int breakSize;

    public WordWrapper(String text, int lineLength) {
        this.text = text;
        this.lineLength = lineLength;
        lines = new ArrayList<>();
    }

    /**
     * Split the given text into lines with a maximum (given) line length.
     * Words are not split unless they are longer than a line.
     * All whitespace at the split point for each line is replaced with a single \n.
     * @param text          text to split into lines by wrapping
     * @param lineLength    maximum line length to use
     * @return the reformatted text
     */
    static public String wrap(String text, int lineLength) {
        assert (lineLength > 0);

        return new WordWrapper(text, lineLength).run();
    }

    private String run() {
        // Add lines to a list as they are split
        while (findLineBreak()) {
            lines.add(text.substring(0, lineBreak));
            text = text.substring(lineBreak + breakSize);
        }
        // Add the remaining chunk
        if (text.length() > 0) {
            lines.add(text);
        }

        return String.join("\n", lines);
    }

    /**
     * Find the next line break point - position-to-break and size-of-break.
     * Sets lineBreak and breakSize fields.
     *
     * @return whether a break was found
     */
    private boolean findLineBreak() {
        if (text.length() <= lineLength) {
            return false;
        }

        // Find last whitespace
        for (lineBreak = lineLength; lineBreak >= 0; lineBreak--) {
            if (Character.isWhitespace(text.charAt(lineBreak))) {
                break;
            }
        }

        if (lineBreak == -1) {
            // No whitespace found - break word at line length
            lineBreak = lineLength;
            breakSize = 0;
            return true;
        }

        // Determine how much whitespace there is
        //  .. search backward
        while (lineBreak > 0 &&
                Character.isWhitespace(text.charAt(lineBreak - 1))) {
            lineBreak--;
        }
        //  .. search forward
        breakSize = 1;
        while (lineBreak + breakSize < text.length() &&
                Character.isWhitespace(text.charAt(lineBreak + breakSize))) {
            breakSize++;
        }
        return true;
    }
}
