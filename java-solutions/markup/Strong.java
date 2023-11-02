package markup;

import java.util.List;

public class Strong extends AbstractParagraph {
    public Strong(List<AbstractParagraph> items) {
        super(items, "__", "[b]", "[/b]");
    }
}