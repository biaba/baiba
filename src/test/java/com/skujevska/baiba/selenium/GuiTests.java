package com.skujevska.baiba.selenium;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GuiTests {

    @LocalServerPort
    private int port;

    private ChromeDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        String base = "http://localhost:" + port;
        driver.get(base);
    }

    @After
    public void logout() {
        driver.quit();
    }

    @Test
    public void test01_MainPage() {
        WebElement homeField = driver.findElement(By.id("logedIn"));
        Assert.assertNotNull(homeField);
    }

    @Test
    public void test02_LoginForm() throws Exception {

        WebElement loginLink = driver.findElement(By.id("login"));
        loginLink.click();

        WebElement loginForm = driver.findElement(By.id("loginForm"));
        Assert.assertNotNull(loginForm);
    }

    @Test
    public void test03_LoginSuccess() throws Exception {

        WebElement loginLink = driver.findElement(By.id("login"));
        loginLink.click();

        WebElement userNameField = driver.findElement(By.id("userName"));
        String userName = "marta";
        userNameField.sendKeys(userName);

        WebElement pasWordField = driver.findElement(By.id("passWord"));
        String password = "marta";
        pasWordField.sendKeys(password);


        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();

        List<WebElement> topCarLink = driver.findElements(By.id("topCars"));
        Assert.assertNotNull(topCarLink);
    }
}