package markup;

import java.util.List;

public class Paragraph extends AbstractMarkup implements Markdown {
    public Paragraph(List<AbstractParagraph> items) {
        super(items, "", "", "");
    }
}