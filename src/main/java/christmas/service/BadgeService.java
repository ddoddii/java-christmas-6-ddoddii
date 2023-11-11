package christmas.service;

import christmas.model.EventBadge;

public class BadgeService {
    public EventBadge calculateBadgeGrade(int promotionAmount){
        for (EventBadge badge : EventBadge.values()){
            if (promotionAmount >= badge.getBaseAmount()){
                return badge;
            }
        }
        return EventBadge.NOTHING;
    }
}