package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class SleepDataLoader {
    public static List<SleepingSession> loadSleepData(String path) throws IOException {
        final LinkedList<SleepingSession> listData = new LinkedList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(path, StandardCharsets.UTF_8))) {
            fileReader.lines()
                    .forEach(line -> {
                        String[] temp = line.split(";");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
                        LocalDateTime sleepStart = LocalDateTime.parse(temp[0], formatter);
                        LocalDateTime sleepEnd = LocalDateTime.parse(temp[1], formatter);
                        SleepQuality sleepQuality = SleepQuality.valueOf(temp[2]);

                        listData.add(new SleepingSession(sleepStart, sleepEnd, sleepQuality));
                    });
        }
        return listData;
    }
}
