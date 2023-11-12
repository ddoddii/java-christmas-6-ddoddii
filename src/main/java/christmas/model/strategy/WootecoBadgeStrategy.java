package christmas.model.strategy;

import christmas.model.constant.EventBadge;

public class WootecoBadgeStrategy implements BadgeStrategy {
    @Override
    public EventBadge calculateBadgeGrade(int promotionAmount) {
        for (EventBadge badge : EventBadge.values()){
            if (promotionAmount >= badge.getBaseAmount()){
                return badge;
            }
        }
        return EventBadge.NOTHING;
    }
}
