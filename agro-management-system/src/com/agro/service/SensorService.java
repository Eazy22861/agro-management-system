package com.agro.service;

import com.agro.model.SensorReading;
import com.agro.repository.SensorRepository;
import java.util.List;
import java.util.UUID;

public class SensorService {
    private static final double MIN_HUMIDITY = 30.0;
    private final SensorRepository repository;

    public SensorService(SensorRepository repository) {
        this.repository = repository;
    }

    public boolean registerNewSensorData(String fieldId, double temperature, double humidity) {
        if (fieldId == null || fieldId.isBlank() || humidity < 0.0 || humidity > 100.0) {
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
        if (readings.isEmpty()) {
            return "Нет данных по участку " + fieldId;
        }
        SensorReading latest = readings.get(readings.size() - 1);
        if (latest.humidity() < MIN_HUMIDITY) {
            return "Предупреждение для " + fieldId + ": влажность ниже нормы (" + latest.humidity() + "%)";
        }
        return "Состояние участка " + fieldId + " в норме (влажность " + latest.humidity() + "%)";
    }
}