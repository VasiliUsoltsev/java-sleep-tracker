package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FunctionException;

import java.util.List;
import java.util.function.Function;

public class AvgDurationSleepSession extends BasicFunction<String> {
    public AvgDurationSleepSession(String resultDescription) {
        super(getFunction(), resultDescription);
    }

    private static Function<List<SleepingSession>, String> getFunction() {
        return list -> {
            return String.format("%.2f",
                    list.stream()
                            .mapToLong(SleepingSession::getDurationInMinutes)
                            .average()
                            .orElseThrow(() -> {
                                throw new FunctionException("Ошибка при выявлении средней " +
                                        "продолжительности сессии");
                            })
            );
        };
    }
}
