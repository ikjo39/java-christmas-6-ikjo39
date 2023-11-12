package christmas.model;

import static christmas.constant.ExceptionMessage.INVALID_VISIT_DAY;

import java.time.DateTimeException;
import java.time.LocalDate;

public class VisitDate {
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;

    private final LocalDate visitDate;

    public VisitDate(int day) {
        this.visitDate = convertDayToLocalDate(day);
    }

    private LocalDate convertDayToLocalDate(int day) {
        try {
            return LocalDate.of(EVENT_YEAR, EVENT_MONTH, day);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(INVALID_VISIT_DAY.getMessage());
        }
    }

    public int getMonth() {
        return EVENT_MONTH;
    }

    public int getDayOfMonth() {
        return visitDate.getDayOfMonth();
    }
}
