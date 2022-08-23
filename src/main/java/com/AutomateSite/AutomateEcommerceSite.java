package com.AutomateSite;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;

public class AutomateEcommerceSite {
    WebDriver driverObj;
    public void invokeBrowserAndAmazon() {
        try {
            System.setProperty("webdriver.chrome.driver", "/home/prabhas/Desktop/MountBlueAssignment/Java/Drivers/chromedriver");
            driverObj = new ChromeDriver();
            driverObj.get("https://www.amazon.in");
            driverObj.manage().deleteAllCookies();
            driverObj.manage().window().maximize();
            Thread.sleep(1000);
            searchProductAndAddToCart();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void searchProductAndAddToCart() {
        try {
            driverObj.findElement(By.id("twotabsearchtextbox")).sendKeys("java books");
            Thread.sleep(3000);
            driverObj.findElement(By.id("nav-search-submit-button")).click();
            driverObj.findElement(By.xpath("//body/div[@id='a-page']/div[@id='search']/div[1]/div[1]/div[1]/span[3]/div[2]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/h2[1]/a[1]/span[1]")).click();
            String parentHandle = driverObj.getWindowHandle();
            Set<String> childhandles = driverObj.getWindowHandles();
            for (String handle : childhandles) {
                if (!handle.equals(parentHandle)) {
                    driverObj.switchTo().window(handle);
                    driverObj.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
                    driverObj.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();
                }
            }
            userRegistration();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void userRegistration() {
        try {
            WebElement goToUserRegistration = driverObj.findElement(By.linkText("Create your Amazon account"));
            goToUserRegistration.click();
            WebElement userName = driverObj.findElement(By.id("ap_customer_name"));
            userName.sendKeys("Saurav");
            WebElement mobNumber = driverObj.findElement(By.id("ap_phone_number"));
            mobNumber.sendKeys("8688949611");
            WebElement setPassword = driverObj.findElement(By.id("ap_password"));
            setPassword.sendKeys("Saurav@1998");
            driverObj.findElement(By.id("continue")).click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String []args){
        AutomateEcommerceSite automateObj = new AutomateEcommerceSite();
        automateObj.invokeBrowserAndAmazon();
    }

}
