package markup;

import java.util.List;

public abstract class AbstractParagraph extends AbstractMarkup implements Markdown {
    protected AbstractParagraph(List<AbstractParagraph> items, String initial, String tagLeft, String tagRight) {
        super(items, initial, tagLeft, tagRight);
    }
}