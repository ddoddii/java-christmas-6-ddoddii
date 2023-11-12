package christmas.model.strategy;

import christmas.model.constant.EventBadge;

public interface BadgeStrategy {
    EventBadge calculateBadgeGrade(int promotionAmount);
}
