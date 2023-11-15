package christmas.constant;

import static christmas.constant.EventDate.EVENT_MONTH;
import static christmas.constant.EventDate.EVENT_YEAR;

import java.time.LocalDate;

public enum EventDateConfig {
    CHRISTMAS_EVENT_START_DAY(1),
    CHRISTMAS_EVENT_END_DAY(25),

    GIVEAWAY_EVENT_START_DAY(1),
    GIVEAWAY_EVENT_END_DAY(31),

    WEEKDAY_EVENT_START_DAY(1),
    WEEKDAY_EVENT_END_DAY(31),

    WEEKEND_EVENT_START_DAY(1),
    WEEKEND_EVENT_END_DAY(31),

    SPECIAL_EVENT_START_DAY(1),
    SPECIAL_EVENT_END_DAY(31);

    private final int value;

    EventDateConfig(int value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), value);
    }
}
