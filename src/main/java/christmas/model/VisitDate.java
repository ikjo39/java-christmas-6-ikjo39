package christmas.model;

import static christmas.constant.EventDateConfig.CHRISTMAS_EVENT_END_DAY;
import static christmas.constant.EventDate.EVENT_MONTH;
import static christmas.constant.EventDate.EVENT_YEAR;
import static christmas.constant.ExceptionMessage.INVALID_VISIT_DAY;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class VisitDate {
    private static final List<DayOfWeek> WEEKENDS = List.of(FRIDAY, SATURDAY);

    private final LocalDate visitDate;
    private final DayOfWeek visitDayOfWeek;

    public VisitDate(int day) {
        this.visitDate = convertDayToLocalDate(day);
        this.visitDayOfWeek = visitDate.getDayOfWeek();
    }

    private static LocalDate convertDayToLocalDate(int day) {
        try {
            return LocalDate.of(EVENT_YEAR.getValue(), EVENT_MONTH.getValue(), day);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(INVALID_VISIT_DAY.getMessage());
        }
    }

    public int getMonth() {
        return EVENT_MONTH.getValue();
    }

    public int getDayOfMonth() {
        return visitDate.getDayOfMonth();
    }

    public boolean isWeekend() {
        return WEEKENDS.contains(visitDayOfWeek);
    }

    public boolean isSpecialDate() {
        return visitDayOfWeek.equals(SUNDAY)
                || visitDate.isEqual(CHRISTMAS_EVENT_END_DAY.getDate());
    }

    public boolean isVisitDateInRange(LocalDate startDate, LocalDate endDate) {
        return (visitDate.isEqual(startDate) || visitDate.isAfter(startDate))
                && (visitDate.isBefore(endDate) || visitDate.isEqual(endDate));
    }
}
