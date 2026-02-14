package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class BasicFunction<T> {
    private final String resultDescription;
    private Function<List<SleepingSession>, T> function;

    public BasicFunction(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    protected void setFunction(Function<List<SleepingSession>, T> function) {
        this.function = function;
    }

    public T calculate(List<SleepingSession> sleepingSessionList) {
        return function.apply(sleepingSessionList);
    }
}
