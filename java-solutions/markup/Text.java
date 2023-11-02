package markup;

import java.util.List;

public class Text extends AbstractParagraph {
    private final String text;

    public Text(String str) {
        super(List.of(), "", "", "");
        text = str;
    }

    @Override
    public void toMarkdown(StringBuilder s) {
        s.append(text);
    }
    @Override
    public void toBBCode(StringBuilder s) {
        s.append(text);
    }
}