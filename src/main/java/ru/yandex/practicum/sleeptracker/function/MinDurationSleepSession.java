package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FunctionException;

import java.util.List;
import java.util.function.Function;

public class MinDurationSleepSession extends BasicFunction<Long> {
    public MinDurationSleepSession(String resultDescription) {
        super(getFunction(), resultDescription);
    }

    private static Function<List<SleepingSession>, Long> getFunction() {
        return list -> {
            return list.stream()
                    .map(SleepingSession::getDurationInMinutes)
                    .min(Long::compareTo)
                    .orElseThrow(() -> {
                        throw new FunctionException("Ошибка при выявлении минимальной " +
                                "продолжительности сессии");
                    });
        };
    }
}
