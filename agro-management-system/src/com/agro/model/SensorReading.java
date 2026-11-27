package com.agro.model;

public record SensorReading(String id, String fieldId, double temperature, double humidity) {
}