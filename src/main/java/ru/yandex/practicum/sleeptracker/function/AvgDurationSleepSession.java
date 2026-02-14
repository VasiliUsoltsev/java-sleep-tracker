package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FunctionException;

import java.util.List;
import java.util.function.Function;

public class AvgDurationSleepSession extends BasicFunction<Integer> {
    public AvgDurationSleepSession(String resultDescription) {
        super(resultDescription);
        super.setFunction(getFunction());
    }

    private Function<List<SleepingSession>, Integer> getFunction() {
        return list -> {
            return (int) Math.round(list.stream()
                    .mapToLong(SleepingSession::getDurationInMinutes)
                    .average()
                    .orElseThrow(() -> {
                        throw new FunctionException("Ошибка при выявлении средней " +
                                "продолжительности сессии");
                    }));
        };
    }
}
