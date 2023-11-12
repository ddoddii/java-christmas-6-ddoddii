package christmas.model;

public enum EventBadge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NOTHING("없음",0);


    private String name;
    private int baseAmount;

    EventBadge(String name, int baseAmount) {
        this.name = name;
        this.baseAmount = baseAmount;
    }


    public String getName(){
        return name;
    }

    public int getBaseAmount() {
        return baseAmount;
    }

}
