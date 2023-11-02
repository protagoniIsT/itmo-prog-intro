package markup;

import java.util.List;

public abstract class AbstractMarkup implements Markdown {
    protected final List<? extends Markdown> items;
    private final String initial;
    private final String tagLeft;
    private final String tagRight;


    protected AbstractMarkup(List<? extends Markdown> items, String initial, String tagLeft, String tagRight) {
        this.items = items;
        this.initial = initial;
        this.tagLeft = tagLeft;
        this.tagRight = tagRight;
    }
    @Override
    public void toMarkdown(StringBuilder s) {
        s.append(initial);
        for (Markdown item: items) {
            item.toMarkdown(s);
        }
        s.append(initial);
    }

    @Override
    public void toBBCode(StringBuilder s) {
        s.append(tagLeft);
        for (Markdown item: items) {
            item.toBBCode(s);
        }
        s.append(tagRight);
    }
}