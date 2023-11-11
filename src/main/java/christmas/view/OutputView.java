package christmas.view;

import static christmas.view.ViewMessage.ORDERED_MENU;

import christmas.util.Parser;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private final String QUANTITY_SUFFIX = "개";

    public void displayEventMessage(int date) {
        String formattedMessage = String.format(ViewMessage.EVENT.getMessage(), date);
        System.out.println(formattedMessage);
    }

    public void displayOrderedMenu(Map<String, Integer> parsedMenu) {
        String orders = parsedMenu.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + QUANTITY_SUFFIX)
                .collect(Collectors.joining("\n"));
        System.out.println(ORDERED_MENU.getMessage());
        System.out.println(orders);
    }
    

}
