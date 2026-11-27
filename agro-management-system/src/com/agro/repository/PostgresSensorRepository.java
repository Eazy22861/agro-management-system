package com.agro.repository;

import com.agro.model.SensorReading;
import java.util.ArrayList;
import java.util.List;

public class PostgresSensorRepository implements SensorRepository {
    @Override
    public boolean saveSensorReading(SensorReading reading) {
        // Реализация записи в PostgreSQL
        return reading != null;
    }

    @Override
    public List<SensorReading> getReadingsByField(String fieldId) {
        // Реализация чтения из PostgreSQL по fieldId
        return new ArrayList<>();
    }
}