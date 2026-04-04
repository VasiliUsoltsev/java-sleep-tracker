package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FunctionException;

import java.util.List;
import java.util.function.Function;

public class MaxDurationSleepSession extends BasicFunction<Long> {
    public MaxDurationSleepSession(String resultDescription) {
        super(resultDescription);
        super.setFunction(getFunction());
    }

    private Function<List<SleepingSession>, Long> getFunction() {
        return list -> {
            return list.stream()
                    .map(SleepingSession::getDurationInMinutes)
                    .max(Long::compareTo)
                    .orElseThrow(() -> {
                        throw new FunctionException("Ошибка при выявлении максимальной " +
                                "продолжительности сессии");
                    });
        };
    }
}
