package christmas.model;

import java.util.Arrays;
import java.util.Optional;

public enum Menu {
    SOUP("애피타이저","양송이수프",6000),
    TAPAS("애피타이저","타파스",8000),
    STEAK("메인","티본스테이크",55000),
    BBQ("메인","바비큐립",54000),
    SEAFOOD_PASTA("메인","해산물파스타",35000),
    XMAS_PASTA("메인","크리스마스파스타",25000),
    CHOCO_CAKE("디저트","초코케이크",15000),
    ICECREAM("디저트","아이스크림",5000),
    ZEROCOKE("음료","제로콜라",3000),
    REDWINE("음료","레드와인",60000),
    CHAMPAGNE("음료","샴페인",250000);


    private final String category;
    private final String name;
    private final int price;
    Menu(String category, String name, int price){
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static Menu of(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public boolean isMainCategory(){
        return "메인".equals(this.category);
    }

    public String getCategory(){
        return category;
    }

    public String getName(){
        return name;
    }

    public int getPrice(){
        return price;
    }
}
