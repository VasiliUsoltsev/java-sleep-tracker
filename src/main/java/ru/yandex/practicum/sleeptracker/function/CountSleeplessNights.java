package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FunctionException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class CountSleeplessNights extends BasicFunction<Long> {
    private static final LocalTime MORNING_6AM = LocalTime.of(6, 0);
    private static final LocalTime NOON_12PM = LocalTime.of(12, 0);
    private static final LocalTime MIDNIGHT = LocalTime.of(0, 0);


    public CountSleeplessNights(String resultDescription) {
        super(resultDescription);
        super.setFunction(getFunction());
    }

    private Function<List<SleepingSession>, Long> getFunction() {
        return list -> {
            if (list == null || list.isEmpty()) throw new FunctionException("Ошибка - нет данных для анализа");
            return list.stream()
                    .filter(session -> {
                        LocalDateTime sleepStart = session.getSleepStart();
                        LocalDateTime sleepEnd = session.getSleepEnd();
                        LocalDate sleepStartDate = session.getSleepStart().toLocalDate();
                        LocalDate sleepEndDate = session.getSleepEnd().toLocalDate();

                        if (sleepStartDate.getDayOfMonth() != sleepEndDate.getDayOfMonth()) return false;

                        LocalDateTime dateTime600 = getMorningBoundary(sleepStartDate);
                        LocalDateTime dateTime1200 = getNoonBoundary(sleepStartDate);
                        LocalDateTime dateTime0000 = getMidnightBoundary(sleepStartDate.plusDays(1));

                        // Если засыпание и пробуждение происходит в один день, то вопрос только в том
                        // заснул ли пользователь после шести или до тк ночь считается с 0 по 6 утра
                        if (sleepStart.isAfter(dateTime600)) return true;

                        return false;
                    })
                    .count();
        };
    }

    private LocalDateTime getMorningBoundary(LocalDate date) {
        return LocalDateTime.of(date, MORNING_6AM);
    }

    private LocalDateTime getNoonBoundary(LocalDate date) {
        return LocalDateTime.of(date, NOON_12PM);
    }

    private LocalDateTime getMidnightBoundary(LocalDate date) {
        return LocalDateTime.of(date, MIDNIGHT);
    }


}
