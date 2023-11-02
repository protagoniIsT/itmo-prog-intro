package markup;

import java.util.List;

public class Strikeout extends AbstractParagraph {
    public Strikeout(List<AbstractParagraph> items) {
        super(items, "~", "[s]", "[/s]");
    }
}