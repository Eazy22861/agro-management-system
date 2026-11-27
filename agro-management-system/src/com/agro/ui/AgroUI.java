package com.agro.ui;

import com.agro.repository.InMemorySensorRepository;
import com.agro.repository.SensorRepository;
import com.agro.service.SensorService;

public class AgroUI {
    private final SensorService service;

    public AgroUI(SensorService service) {
        this.service = service;
    }

    public void runDemo() {
        boolean saved = service.registerNewSensorData("field-1", 22.5, 25.0);
        System.out.println("Сохранено: " + saved);
        System.out.println(service.analyzeFieldCondition("field-1"));
    }

    public static void main(String[] args) {
        SensorRepository repo = new InMemorySensorRepository();
        SensorService service = new SensorService(repo);
        AgroUI ui = new AgroUI(service);
        ui.runDemo();
    }
}