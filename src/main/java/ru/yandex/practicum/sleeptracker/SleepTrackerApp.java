package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.function.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerApp {
    private static final List<BasicFunction> listFunction = new ArrayList<>();
    private static List<SleepingSession> listSleepingSession;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("При запуске приложения вы не указали путь к файлу с логом сна!");
            System.out.println("Попробуйте перезапустить приложение с указанием пути -" +
                    " \"src/main/resources/sleep_log.txt\"");
            return;
        }
        final String PATH = args[0];
        System.out.println("Добро пожаловать в приложение по анализу сна!");
        try {
            listSleepingSession = SleepDataLoader.loadSleepData(PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        // Загрузка функций
        loadFunctions();

        // Последовательно запускаем функции
        int[] current = {1};
        System.out.println("Итоги проведенного анализа вашего сна:");
        listFunction.stream()
                .forEach(line -> {
                    System.out.println(current[0]++ + ") " + line.getResultDescription() + " - "
                            + line.calculate(listSleepingSession));
                });
    }

    private static void loadFunctions() {
        // Функция по подсчету кол-ва сессий сна за период
        BasicFunction functionCountSleepSession = new CountSleepSession("Кол-во сессий");
        listFunction.add(functionCountSleepSession);

        // Функция выявления минимальной продолжительности сессии
        BasicFunction functionMinDurationSleepSession = new MinDurationSleepSession("Минимальная" +
                " продолжительность сна (в минутах)");
        listFunction.add(functionMinDurationSleepSession);

        // Функция выявления максимальной продолжительности сессии
        BasicFunction functionMaxDurationSleepSession = new MaxDurationSleepSession("Максимальная" +
                " продолжительность сна (в минутах)");
        listFunction.add(functionMaxDurationSleepSession);

        // Функция выявления средней продолжительности сессии
        BasicFunction functionAvgDurationSleepSession = new AvgDurationSleepSession("Средняя" +
                " продолжительность сна (в минутах)");
        listFunction.add(functionAvgDurationSleepSession);

        // Функция по подсчету кол-ва сессий с плохим качеством сна
        BasicFunction functionCountBadSleepSession = new CountBadSleepSession("Кол-во сессий" +
                " с плохим качеством сна");
        listFunction.add(functionCountBadSleepSession);

        // Функция по выявлению хронотипа пользователя
        BasicFunction functionDetermineChronotype = new DetermineChronotype("Хронотип пользователя");
        listFunction.add(functionDetermineChronotype);

        // Функция по выявлению кол-ва бессонных ночей
        BasicFunction functionCountSleeplessNights = new CountSleeplessNights("Кол-во бессонных ночей");
        listFunction.add(functionCountSleeplessNights);
    }
}