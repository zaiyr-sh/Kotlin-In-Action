package ch_6;

import java.util.List;
import java.util.Locale;

public class CollectionUtils {
    public static List<String> uppercaseAll(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            items.set(i, items.get(i).toUpperCase(Locale.ROOT));
        }
        return items;
    }
}
