package com.mavenit.selenium;



import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class SmokeTestSuite4 extends Hooks {
    @Test
    public void searchTest() {

        // Search

        driver.findElement(By.id("searchTerm")).sendKeys("adidas");
        driver.findElement(By.id("searchTerm")).sendKeys(Keys.ENTER);
        //String url = driver.getCurrentUrl();


        List<WebElement> productWebElements = driver.findElements(By.cssSelector("a[data-test='component-product-card-title']"));
        Random random = new Random();
        int randomNumber = random.nextInt(productWebElements.size() - 1);

        //Identifying the product
        WebElement selectedElement = productWebElements.get(randomNumber);
        String selectedProductName = selectedElement.getText();
        selectedElement.click();

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        //Add to cart
        driver.findElement(By.cssSelector("button[data-test='component-att-button']")).click();

        //Go to cart
        driver.findElement(By.cssSelector("a[data-test='component-att-button-basket']")).click();

        //Total price
        String price = driver.findElement(By.cssSelector("div.Summary__subTotalLabel__2GphY")).getText();


        //selecting dropdown for quantity
        WebElement element = driver.findElement(By.cssSelector("select[class='ProductCard__quantitySelect__2y1R3']"));


        //ProductCard__quantitySelect__2y1R3
        Select s1 = new Select(element);
        s1.selectByIndex(1);

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);


        //Check new total price
        String price2 = driver.findElement(By.cssSelector("div.Summary__subTotalLabel__2GphY")).getText();

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);


        assertThat(price2, is(greaterThan(price)));


    }


}

