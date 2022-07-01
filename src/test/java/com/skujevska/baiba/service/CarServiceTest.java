package com.skujevska.baiba.service;

import com.skujevska.baiba.model.Car;
import com.skujevska.baiba.model.User;
import com.skujevska.baiba.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarServiceTest {

    @Mock
    CarRepository repository;

    @InjectMocks
    CarService service = new CarService();

    List<Car> cars;

    @BeforeAll
    void setup() {
        this.cars = createList();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getListSuccess() {
        when(repository.findAll()).thenReturn(cars);

        List<Car> result = this.service.findAll();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("audi");
    }

    @Test
    void getEmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Car> result = this.service.findAll();
        assertThat(result).isEmpty();
    }

    List<Car> createList() {
        List<Car> list = new ArrayList<>();
        list.add(new Car(1l, "audi", 100, new User()));
        list.add(new Car(2l, "volvo", 200, new User()));
        return list;
    }
}