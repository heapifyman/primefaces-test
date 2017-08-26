package org.primefaces.test;

import java.io.Serializable;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "testView")
@ViewScoped
@Getter
@Setter
public class TestView implements Serializable {

    private final Random random = new Random();

    // fixed lower and upper boundaries for temperature range slider
    private int minTemperature = -20;
    private int maxTemperature = 80;
    // selected min and max temperatures
    private int temperatureMin = 15;
    private int temperatureMax = 60;

    // fixed lower boundary for normalisation slider
    private int minTemperatureNormalisation = 0;
    // dynamic upper boundary for normalisation slider
    private int maxTemperatureNormalisation;
    // selected normalisation value
    private int temperatureNormalisation;

    private void calculateMaxTemperatureNormalisation() {
        // update upper boundary for normalisation slider
        maxTemperatureNormalisation = (temperatureMax - temperatureMin) / 2;
    }

    private void updateTemperatureNormalisation() {
        calculateMaxTemperatureNormalisation();
        if (temperatureNormalisation > maxTemperatureNormalisation) {
            // set selected normalisation value to upper boundary if it was too big
            temperatureNormalisation = maxTemperatureNormalisation;
        }
    }

    @PostConstruct
    public void init() {
        calculateMaxTemperatureNormalisation();
        temperatureNormalisation = random.nextInt(maxTemperatureNormalisation);
    }

    public void temperatureMinChanged(ValueChangeEvent event) {
        // update selected min temperature
        temperatureMin = Integer.valueOf(event.getNewValue().toString());
        updateTemperatureNormalisation();
    }

    public void temperatureMaxChanged(ValueChangeEvent event) {
        // update selected max temperature
        temperatureMax = Integer.valueOf(event.getNewValue().toString());
        updateTemperatureNormalisation();
    }

    public void temperatureNormalisationChanged(ValueChangeEvent event) {
        // update selected normalisation value
        temperatureNormalisation = Integer.valueOf(event.getNewValue().toString());
    }

}
