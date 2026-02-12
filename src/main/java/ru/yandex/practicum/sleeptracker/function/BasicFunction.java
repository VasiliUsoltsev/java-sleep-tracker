package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class BasicFunction<T> {
    private final String resultDescription;
    private final Function<List<SleepingSession>, T> function;

    public BasicFunction(Function<List<SleepingSession>, T> function,
                         String resultDescription) {
        this.function = function;
        this.resultDescription = resultDescription;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public T calculate(List<SleepingSession> sleepingSessionList) {
        return function.apply(sleepingSessionList);
    }
}
