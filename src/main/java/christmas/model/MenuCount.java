package christmas.model;

import christmas.validator.MenuValidator;
import java.util.EnumMap;
import java.util.Map;


public class MenuCount {
    private EnumMap<Menu, Integer> menuCount;

    public MenuCount(Map<String, Integer> parsedMenu) {
        MenuValidator.validateMenuLogic(parsedMenu);
        this.menuCount = calculateMenuCount(parsedMenu);
    }

    private EnumMap<Menu, Integer> calculateMenuCount(Map<String, Integer> parsedMenu) {
        EnumMap<Menu, Integer> menuCount = new EnumMap<>(Menu.class);
        parsedMenu.forEach((menuName, count) -> {
            Menu menu = Menu.of(menuName);
            menuCount.put(menu, count);
        });
        return menuCount;
    }

}