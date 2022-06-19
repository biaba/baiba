package com.skujevska.baiba.controller;

import com.skujevska.baiba.model.Car;
import com.skujevska.baiba.model.User;
import com.skujevska.baiba.service.CarService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CarControllerTest {

    @Autowired
    CarService carService;

    @Autowired
    private MockMvc mockMvc;

    private List<Car> cars = createList();

    @Test
    @Order(1)
    public void notAuthorizedUser() throws Exception {

        this.mockMvc.perform(get("/cars")
                        .cookie(new Cookie("Bearer", " wrong cookie")))
                .andExpect(status().isUnauthorized());

    }

    @Test
    @Order(2)
    public void gettingCars() throws Exception {
        mockMvc.perform(get("/cars")
                        .cookie(new Cookie("Bearer", " eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJ0YSIsImlhdCI6MTY1NTQ3NzgyNywiZXhwIjoxNjU1NTY0MjI3fQ.FvKktb2_Ubh-4Ods_F_qzSy9mBW2JVocAzXcM9s46qTouXTBj-QALZsR02OzC2DYYP6VJYJF2c7_3aUkFzegvw")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cars"))
                .andExpect(view().name("cars"));
    }

    @Test
    @Order(3)
    public void wrongUrl() throws Exception {
        mockMvc.perform(post("/car"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();

    }

    private List<Car> createList() {
        List<Car> list = new ArrayList<>();
        list.add(new Car(1l, "audi", 100, new User()));
        list.add(new Car(2l, "volvo", 200, new User()));
        return list;
    }
}
