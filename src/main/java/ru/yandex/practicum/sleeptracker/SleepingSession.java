package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepingSession implements Comparable<SleepingSession> {
    private final LocalDateTime sleepStart;
    private final LocalDateTime sleepEnd;
    private final SleepQuality sleepQuality;

    public SleepingSession(LocalDateTime sleepStart, LocalDateTime sleepEnd, SleepQuality sleepQuality) {
        this.sleepStart = sleepStart;
        this.sleepEnd = sleepEnd;
        this.sleepQuality = sleepQuality;
    }

    @Override
    public String toString() {
        return "Сессия сна{" +
                "заснули=" + sleepStart +
                ", проснулись=" + sleepEnd +
                ", качество=" + sleepQuality +
                '}';
    }

    @Override
    public int compareTo(SleepingSession o) {
        return Long.compare(Duration.between(this.sleepStart, this.sleepEnd).getSeconds() / 60,
                Duration.between(o.sleepStart, o.sleepEnd).getSeconds() / 60);
    }

    public Long getDurationInMinutes() {
        return Duration.between(sleepStart, sleepEnd).getSeconds() / 60;
    }

    public boolean isBadSession() {
        return sleepQuality.equals(SleepQuality.BAD);
    }

    public boolean isGoodSession() {
        return sleepQuality.equals(SleepQuality.GOOD);
    }

    public boolean isNormalSession() {
        return sleepQuality.equals(SleepQuality.NORMAL);
    }

    public LocalDateTime getSleepStart() {
        return sleepStart;
    }

    public LocalDateTime getSleepEnd() {
        return sleepEnd;
    }

    public SleepQuality getSleepQuality() {
        return sleepQuality;
    }
}


