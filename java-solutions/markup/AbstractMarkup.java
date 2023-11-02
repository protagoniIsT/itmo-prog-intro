package markup;

import java.util.List;

public abstract class AbstractMarkup implements Markdown {
    protected final List<? extends Markdown> items;
    private final String markdownTag;
    private final String openBBCodeTag;
    private final String closeBBCodeTag;


    protected AbstractMarkup(List<? extends Markdown> items, String markdownTag, String openBBCodeTag, String closeBBCodeTag) {
        this.items = items;
        this.markdownTag = markdownTag;
        this.openBBCodeTag = openBBCodeTag;
        this.closeBBCodeTag = closeBBCodeTag;
    }
    @Override
    public void toMarkdown(StringBuilder s) {
        s.append(markdownTag);
        for (Markdown item: items) {
            item.toMarkdown(s);
        }
        s.append(markdownTag);
    }

    @Override
    public void toBBCode(StringBuilder s) {
        s.append(openBBCodeTag);
        for (Markdown item: items) {
            item.toBBCode(s);
        }
        s.append(closeBBCodeTag);
    }
}