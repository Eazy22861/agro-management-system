package com.agro.model;

import java.time.Instant;

public class SensorData {
    private final String sensorId;
    private final double temperatureC;
    private final double soilMoisturePct;
    private final Instant recordedAt;

    public SensorData(String sensorId, double temperatureC, double soilMoisturePct, Instant recordedAt) {
        this.sensorId = sensorId;
        this.temperatureC = temperatureC;
        this.soilMoisturePct = soilMoisturePct;
        this.recordedAt = recordedAt;
    }

    public String getSensorId() {
        return sensorId;
    }

    public double getTemperatureC() {
        return temperatureC;
    }

    public double getSoilMoisturePct() {
        return soilMoisturePct;
    }

    public Instant getRecordedAt() {
        return recordedAt;
    }
}