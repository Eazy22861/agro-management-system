package com.agro.repository;

import com.agro.model.SensorReading;
import java.util.List;

public interface SensorRepository {
    boolean saveSensorReading(SensorReading reading);
    List<SensorReading> getReadingsByField(String fieldId);
}