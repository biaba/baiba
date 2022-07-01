package com.skujevska.baiba.service;

import com.skujevska.baiba.model.Car;
import com.skujevska.baiba.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository repo;

    public List<Car> findAll() {
        return repo.findAll();
    }

    public Car saveCar(Car car) {
        return repo.save(car);
    }
}
