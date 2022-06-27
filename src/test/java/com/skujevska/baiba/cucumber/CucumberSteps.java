package com.skujevska.baiba.cucumber;

import com.skujevska.baiba.model.Car;
import com.skujevska.baiba.repository.CarRepository;
import com.skujevska.baiba.service.CarService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CucumberSteps extends CucumberIntegrationContext {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @When("There are 3 cars in database")
    public void thereAreXCarsInDatabase() {
        int carsInDb = carService.findAll().size();
        List<Car> allCars = carRepository.findAll();
        while(carsInDb>3) {
            carRepository.delete(allCars.get(0));
            carsInDb = carRepository.findAll().size();
            allCars = carRepository.findAll();
        }
        Assert.assertEquals(3, carService.findAll().size());
    }

    @Then("I add one car")
    public void iAddOneCar() {
        Car car = createCar();
        carService.saveCar(car);
    }

    @And("There are 4 cars in database")
    public void thereAreXPlusOneCarsInDatabase() {
        Assert.assertEquals(4, carService.findAll().size());
    }

    private Car createCar() {
        Car newCar = new Car();
        newCar.setName("SSC Ultimate Aero");
        newCar.setSpeed(412);
        return newCar;
    }
}
