package com.skujevska.baiba.service;

import com.skujevska.baiba.model.Car;
import com.skujevska.baiba.model.User;
import com.skujevska.baiba.repository.CarRepository;
import com.skujevska.baiba.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.postgresql.util.PSQLException;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarServiceTest {

    @Mock
    CarRepository repository;

    @InjectMocks
    CarService service = new CarService();

    private List<Car> cars;

    @BeforeAll
    public void setup() {
        this.cars = createList();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getListSuccess() {
        when(repository.findAll()).thenReturn(cars);

        List<Car> result = this.service.findAll();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("audi");
    }

    @Test
    public void getEmptyList() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<Car> result = this.service.findAll();
        assertThat(result.size()).isEqualTo(0);
    }

    private List<Car> createList() {
        List<Car> list = new ArrayList<>();
        list.add(new Car(1l, "audi", 100, new User()));
        list.add(new Car(2l, "volvo", 200, new User()));
        return list;
    }
}