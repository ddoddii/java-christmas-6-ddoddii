package christmas.model;

import christmas.model.constant.Menu;
import christmas.validator.MenuValidator;
import java.util.EnumMap;
import java.util.Map;


public class MenuCount {
    private EnumMap<Menu, Integer> menuCount;

    public MenuCount(Map<String, Integer> parsedMenu) {
        MenuValidator.validateMenuLogic(parsedMenu);
        this.menuCount = calculateMenuCount(parsedMenu);
    }

    public int calculateTotalAmount(){
        return menuCount.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public EnumMap<Menu,Integer> getValue(){
        return menuCount;
    }

    private EnumMap<Menu, Integer> calculateMenuCount(Map<String, Integer> parsedMenu) {
        EnumMap<Menu, Integer> calculatedMenuCount = new EnumMap<>(Menu.class);
        parsedMenu.forEach((menuName, count) -> {
            Menu menu = Menu.of(menuName);
            calculatedMenuCount.put(menu, count);
        });
        return calculatedMenuCount;
    }

}