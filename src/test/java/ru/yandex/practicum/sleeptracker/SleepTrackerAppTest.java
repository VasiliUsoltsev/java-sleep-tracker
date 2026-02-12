package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.exception.FunctionException;
import ru.yandex.practicum.sleeptracker.function.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerAppTest {
    private static final String PATH = "src/test/resources/sleep_log.txt";
    private static List<BasicFunction> listFunction = new ArrayList<>();
    private static List<SleepingSession> listSleepingSession;

    private static void loadLogsSleepSession() {
        try {
            listSleepingSession = SleepDataLoader.loadSleepData(PATH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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

    // Функция по подсчету кол-ва сессий сна за период
    @DisplayName("Функция по подсчету кол-ва сессий сна за период - проверка подсчета")
    @Test
    void functionCountSleepSession() {
        loadLogsSleepSession();
        loadFunctions();
        Assertions.assertEquals(18, listSleepingSession.size());
    }

    @DisplayName("Функция по подсчету кол-ва сессий сна за период - проверка подсчета при пустом списке")
    @Test
    void functionCountSleepSessionEmpty() {
        loadLogsSleepSession();
        loadFunctions();
        listSleepingSession.clear();
        Assertions.assertThrows(FunctionException.class, () -> listFunction.get(0).calculate(listSleepingSession));
    }

    // Функция выявления минимальной продолжительности сессии
    @DisplayName("Функция выявления минимальной продолжительности сессии - проверка подсчета")
    @Test
    void functionMinDurationSleepSession() {
        loadLogsSleepSession();
        loadFunctions();
        Assertions.assertEquals(25L, listFunction.get(1).calculate(listSleepingSession));
    }

    @DisplayName("Функция выявления минимальной продолжительности сессии - проверка подсчета при пустом списке")
    @Test
    void functionMinDurationSleepSessionEmpty() {
        loadLogsSleepSession();
        loadFunctions();
        listSleepingSession.clear();
        Assertions.assertThrows(FunctionException.class, () -> listFunction.get(1).calculate(listSleepingSession));
    }

    // Функция выявления максимальной продолжительности сессии
    @DisplayName("Функция выявления максимальной продолжительности сессии - проверка подсчета")
    @Test
    void functionMaxDurationSleepSession() {
        loadLogsSleepSession();
        loadFunctions();
        Assertions.assertEquals(960L, listFunction.get(2).calculate(listSleepingSession));
    }

    @DisplayName("Функция выявления максимальной продолжительности сессии - проверка подсчета при пустом списке")
    @Test
    void functionMaxDurationSleepSessionEmpty() {
        loadLogsSleepSession();
        loadFunctions();
        listSleepingSession.clear();
        Assertions.assertThrows(FunctionException.class, () -> listFunction.get(2).calculate(listSleepingSession));
    }

    // Функция выявления средней продолжительности сессии
    @DisplayName("Функция выявления средней продолжительности сессии - проверка подсчета")
    @Test
    void functionAvgDurationSleepSession() {
        loadLogsSleepSession();
        loadFunctions();
        Assertions.assertEquals("411.94", listFunction.get(3).calculate(listSleepingSession));
    }

    @DisplayName("Функция выявления средней продолжительности сессии - проверка подсчета при пустом списке")
    @Test
    void functionAvgDurationSleepSessionEmpty() {
        loadLogsSleepSession();
        loadFunctions();
        listSleepingSession.clear();
        Assertions.assertThrows(FunctionException.class, () -> listFunction.get(3).calculate(listSleepingSession));
    }

    // Функция по подсчету кол-ва сессий с плохим качеством сна
    @DisplayName("Функция по подсчету кол-ва сессий с плохим качеством сна - проверка подсчета")
    @Test
    void functionCountBadSleepSession() {
        loadLogsSleepSession();
        loadFunctions();
        Assertions.assertEquals(6, listFunction.get(4).calculate(listSleepingSession));
    }

    @DisplayName("Функция по подсчету кол-ва сессий с плохим качеством сна - проверка подсчета при пустом списке")
    @Test
    void functionCountBadSleepSessionEmpty() {
        loadLogsSleepSession();
        loadFunctions();
        listSleepingSession.clear();
        Assertions.assertThrows(FunctionException.class, () -> listFunction.get(4).calculate(listSleepingSession));
    }

    // Функция по выявлению хронотипа пользователя
    @DisplayName("Функция по выявлению хронотипа пользователя - проверка подсчета")
    @Test
    void functionDetermineChronotype() {
        loadLogsSleepSession();
        loadFunctions();
        Assertions.assertEquals("Голубь", listFunction.get(5).calculate(listSleepingSession));
    }

    @DisplayName("Функция по выявлению хронотипа пользователя - проверка подсчета при пустом списке")
    @Test
    void functionDetermineChronotypeEmpty() {
        loadLogsSleepSession();
        loadFunctions();
        listSleepingSession.clear();
        Assertions.assertThrows(FunctionException.class, () -> listFunction.get(5).calculate(listSleepingSession));
    }

    // Функция по выявлению кол-ва бессонных ночей
    @DisplayName("Функция по выявлению кол-ва бессонных ночей - проверка подсчета из набора данных")
    @Test
    void functionCountSleeplessNights() {
        loadLogsSleepSession();
        loadFunctions();
        Assertions.assertEquals(4L, listFunction.get(6).calculate(listSleepingSession));
    }

    @DisplayName("Функция по выявлению кол-ва бессонных ночей - проверка подсчета при пустом списке")
    @Test
    void functionCountSleeplessNightsEmpty() {
        loadLogsSleepSession();
        loadFunctions();
        listSleepingSession.clear();
        Assertions.assertThrows(FunctionException.class, () -> listFunction.get(6).calculate(listSleepingSession));
    }

    @DisplayName("Функция по выявлению кол-ва бессонных ночей - проверка подсчета если поспать вечером")
    @Test
    void functionCountSleeplessNights1() {
        LocalDateTime sleepStart = LocalDateTime.of(2025, 11, 1, 18, 10);
        LocalDateTime sleepEnd = LocalDateTime.of(2025, 11, 1, 23, 50);
        listSleepingSession = new ArrayList<>();
        listSleepingSession.add(new SleepingSession(sleepStart, sleepEnd, SleepQuality.BAD));
        loadFunctions();
        Assertions.assertEquals(1L, listFunction.get(6).calculate(listSleepingSession));
    }

    @DisplayName("Функция по выявлению кол-ва бессонных ночей - проверка подсчета если поспать утром")
    @Test
    void functionCountSleeplessNights2() {
        LocalDateTime sleepStart = LocalDateTime.of(2025, 11, 1, 6, 10);
        LocalDateTime sleepEnd = LocalDateTime.of(2025, 11, 1, 11, 50);
        listSleepingSession = new ArrayList<>();
        listSleepingSession.add(new SleepingSession(sleepStart, sleepEnd, SleepQuality.BAD));
        loadFunctions();
        Assertions.assertEquals(1L, listFunction.get(6).calculate(listSleepingSession));
    }

    @DisplayName("Функция по выявлению кол-ва бессонных ночей - проверка подсчета если заснуть ночью и проснуться утром")
    @Test
    void functionCountSleeplessNights3() {
        LocalDateTime sleepStart = LocalDateTime.of(2025, 11, 1, 2, 10);
        LocalDateTime sleepEnd = LocalDateTime.of(2025, 11, 1, 11, 50);
        listSleepingSession = new ArrayList<>();
        listSleepingSession.add(new SleepingSession(sleepStart, sleepEnd, SleepQuality.BAD));
        loadFunctions();
        Assertions.assertEquals(0L, listFunction.get(6).calculate(listSleepingSession));
    }

    @DisplayName("Функция по выявлению кол-ва бессонных ночей - проверка подсчета если заснуть вечером а проснуться ночью")
    @Test
    void functionCountSleeplessNights4() {
        LocalDateTime sleepStart = LocalDateTime.of(2025, 11, 1, 22, 10);
        LocalDateTime sleepEnd = LocalDateTime.of(2025, 11, 2, 04, 50);
        listSleepingSession = new ArrayList<>();
        listSleepingSession.add(new SleepingSession(sleepStart, sleepEnd, SleepQuality.BAD));
        loadFunctions();
        Assertions.assertEquals(0L, listFunction.get(6).calculate(listSleepingSession));
    }

    @DisplayName("Функция по выявлению кол-ва бессонных ночей - проверка подсчета если заснуть ночью и проснуться ночью")
    @Test
    void functionCountSleeplessNights5() {
        LocalDateTime sleepStart = LocalDateTime.of(2025, 11, 1, 0, 10);
        LocalDateTime sleepEnd = LocalDateTime.of(2025, 11, 1, 04, 50);
        listSleepingSession = new ArrayList<>();
        listSleepingSession.add(new SleepingSession(sleepStart, sleepEnd, SleepQuality.BAD));
        loadFunctions();
        Assertions.assertEquals(0L, listFunction.get(6).calculate(listSleepingSession));
    }

}