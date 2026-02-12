package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.exception.FunctionException;

import java.util.List;
import java.util.function.Function;

public class CountSleepSession extends BasicFunction<Integer> {
    public CountSleepSession(String resultDescription) {
        super(getFunction(), resultDescription);
    }

    private static Function<List<SleepingSession>, Integer> getFunction() {
        return list -> {
            if (list == null || list.isEmpty()) throw new FunctionException("Ошибка - нет данных для анализа");
            return list.size();
        };
    }
}
