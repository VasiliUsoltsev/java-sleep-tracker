package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FunctionException;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class DetermineChronotype extends BasicFunction<String> {
    private static final LocalTime time2300 = LocalTime.of(23, 0);
    private static final LocalTime time900 = LocalTime.of(9, 0);
    private static final LocalTime time2200 = LocalTime.of(22, 0);
    private static final LocalTime time700 = LocalTime.of(7, 0);

    public DetermineChronotype(String resultDescription) {
        super(resultDescription);
        super.setFunction(getFunction());
    }

    private Function<List<SleepingSession>, String> getFunction() {
        return list -> {
            if (list == null || list.isEmpty()) throw new FunctionException("Ошибка - нет данных для анализа");
            long owlCount = list.stream()
                    .filter(session -> {
                        LocalTime sleepStartTime = session.getSleepStart().toLocalTime();
                        LocalTime sleepEndTime = session.getSleepEnd().toLocalTime();

                        return sleepStartTime.isAfter(time2300) && sleepEndTime.isAfter(time900);
                    })
                    .count();

            long larkCount = list.stream()
                    .filter(session -> {
                        LocalTime sleepStartTime = session.getSleepStart().toLocalTime();
                        LocalTime sleepEndTime = session.getSleepEnd().toLocalTime();

                        return sleepStartTime.isBefore(time2200) && sleepEndTime.isBefore(time700);
                    })
                    .count();

            long pegionCount = list.size() - (larkCount + owlCount);

            if (owlCount > larkCount && owlCount > pegionCount)
                return "Сова";

            if (larkCount > owlCount && larkCount > pegionCount)
                return "Жаворонок";

            return "Голубь";
        };
    }
}
