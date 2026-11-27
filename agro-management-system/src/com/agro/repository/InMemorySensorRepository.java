package com.agro.repository;

import com.agro.model.SensorReading;
import java.util.ArrayList;
import java.util.List;

public class InMemorySensorRepository implements SensorRepository {
    private final List<SensorReading> storage = new ArrayList<>();

    @Override
    public boolean saveSensorReading(SensorReading reading) {
        if (reading == null) {
            return false;
        }
        return storage.add(reading);
    }

    @Override
    public List<SensorReading> getReadingsByField(String fieldId) {
        List<SensorReading> result = new ArrayList<>();
        for (SensorReading reading : storage) {
            if (reading.fieldId().equals(fieldId)) {
                result.add(reading);
            }
        }
        return result;
    }
}