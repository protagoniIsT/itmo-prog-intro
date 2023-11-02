package markup;

import java.util.List;

public class Emphasis extends AbstractParagraph {
    public Emphasis(List<AbstractParagraph> items) {
        super(items, "*", "[i]", "[/i]");
    }
}