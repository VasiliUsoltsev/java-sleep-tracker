package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FunctionException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class CountSleeplessNights extends BasicFunction<Long> {
    public CountSleeplessNights(String resultDescription) {
        super(getFunction(), resultDescription);
    }

    private static Function<List<SleepingSession>, Long> getFunction() {
        return list -> {
            if (list == null || list.isEmpty()) throw new FunctionException("Ошибка - нет данных для анализа");
            return list.stream()
                    .filter(session -> {
                        LocalDateTime sleepStart = session.getSleepStart();
                        LocalDateTime sleepEnd = session.getSleepEnd();
                        LocalDate sleepStartDate = session.getSleepStart().toLocalDate();
                        LocalDate sleepEndDate = session.getSleepEnd().toLocalDate();

                        if (sleepStartDate.getDayOfMonth() != sleepEndDate.getDayOfMonth()) return false;

                        LocalDateTime dateTime600 = LocalDateTime.of(
                                sleepStartDate,
                                LocalTime.of(6, 0));
                        LocalDateTime dateTime1200 = LocalDateTime.of(
                                sleepStartDate,
                                LocalTime.of(12, 0));
                        LocalDateTime dateTime0000 = LocalDateTime.of(
                                sleepStartDate.plusDays(1),
                                LocalTime.of(0, 0));


                        if (sleepStart.isAfter(dateTime600) && sleepStart.isBefore(dateTime1200)) {
                            return true;
                        }

                        if (sleepStart.isAfter(dateTime1200) && sleepEnd.isBefore(dateTime0000)) {
                            return true;
                        }

                        return false;
                    })
                    .count();
        };
    }
}
