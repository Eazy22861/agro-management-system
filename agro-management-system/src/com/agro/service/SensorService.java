package com.agro.service;

import com.agro.model.SensorReading;
import com.agro.repository.SensorRepository;
import java.util.List;
import java.util.UUID;

public class SensorService {
    private static final double MIN_HUMIDITY = 30.0;
    private static final double MAX_HUMIDITY = 75.0;
    private final SensorRepository repository;

    public SensorService(SensorRepository repository) {
        this.repository = repository;
    }

    public boolean registerNewSensorData(String fieldId, double temperature, double humidity) {
        if (fieldId == null || fieldId.isBlank() || !isValidHumidity(humidity)) {
            return false;
        }
        SensorReading reading = new SensorReading(
                UUID.randomUUID().toString(),
                fieldId,
                temperature,
                humidity
        );
        return repository.saveSensorReading(reading);
    }

    public String analyzeFieldCondition(String fieldId) {
        List<SensorReading> readings = repository.getReadingsByField(fieldId);
        if (readings == null || readings.isEmpty()) {
            return "Нет данных по участку " + fieldId;
        }
        SensorReading latest = readings.get(readings.size() - 1);
        return analyzeHumidity(latest.humidity());
    }

    // BUG-001: граница влажности 30.0 вместо 29.0 (логическая ошибка)
    public String analyzeHumidity(double humidity) {
        if (humidity < MIN_HUMIDITY) {
            return "Суару қажет (Қауіпті деңгей)";
        } else if (humidity <= MAX_HUMIDITY) {
            return "Қалыпты жағдай";
        } else {
            return "Ылғалдылық шамадан тыс";
        }
    }

    // BUG-002: защита от пустого списка / null, чтобы не возвращать NaN
    public double calculateAverageTemp(List<Double> readingsList) {
        if (readingsList == null || readingsList.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (double temp : readingsList) {
            total += temp;
        }
        return total / readingsList.size();
    }

    // BUG-003: диапазон [0.0; 100.0] через && вместо ||
    public boolean isValidHumidity(double humidity) {
        return humidity >= 0.0 && humidity <= 100.0;
    }
}