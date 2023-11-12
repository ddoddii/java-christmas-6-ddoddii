package christmas.model.strategy;

import christmas.model.EventBadge;

public interface BadgeStrategy {
    EventBadge calculateBadgeGrade(int promotionAmount);
}
