package com.planit.myapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;



/**
 * Unit test for simple App.
 */
public class AppTest extends WebDriverBase
{
    @Test
    public void test01()
    {
        System.out.println("@Test01 executed");
	    //Launch website
	    driver.navigate().to(planitUrl);
     
         //Maximize the browser
         driver.manage().window().maximize();

         //Click submit button
         driver.findElement(By.xpath("//li[@id='nav-contact']/a")).click();        
         driver.findElement(By.xpath("//div[@class='form-actions']/a")).click();
         
	     //Get the Text based on its xpath
	     String resultName = driver.findElement(By.xpath("//span[@id='forename-err']")).getText();
	     String resultEmail = driver.findElement(By.xpath("//span[@id='email-err']")).getText();
	     String resultMessage = driver.findElement(By.xpath("//span[@id='message-err']")).getText();
	     
	     //Verify error messages
	     Assertions.assertEquals("Forename is required", resultName);
	     Assertions.assertEquals("Email is required", resultEmail);
	     Assertions.assertEquals("Message is required", resultMessage);
	     
	     //Populate mandatory fields
	     driver.findElement(By.xpath("//input[@id='forename']")).sendKeys("John");
	     driver.findElement(By.xpath("//input[@id='email']")).sendKeys("John.example@planit.net.au");
	     driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("They are great!");
	     
	     //Click submit button
	     driver.findElement(By.xpath("//div[@class='form-actions']/a")).click();
    }    
    
    @Test
    @RepeatedTest(5)
    public void test02()
    {
        System.out.println("@Test02 executed");
	    //Launch website
	    driver.navigate().to(planitUrl);    
        //Maximize the browser
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//li[@id='nav-contact']/a")).click();
        //Input the info
        driver.findElement(By.xpath("//input[@id='forename']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("John.example@planit.net.au");
        driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("They are great!");
         
         //Click submit button
         driver.findElement(By.xpath("//div[@class='form-actions']/a")).click();
         
         //Get the Text based on its xpath ?? something is not right here!!!!
         String resultInfo = driver.findElement(By.xpath("//div[@class='alert alert-success']/strong[@class='ng-binding']")).getText();
         //Validate successful submission message
         Assertions.assertEquals("Thanks John", resultInfo);    
    }    
    
    @Test
    public void test03()
    {
        System.out.println("@Test03 executed");
	    //Launch website
	    driver.navigate().to(planitUrl);
     
         //Maximize the browser
        driver.manage().window().maximize();
         
        //Go to shop page
        driver.findElement(By.xpath("//a[@href='#/shop']")).click();
         
        //Buy 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear
        for (int i = 0; i < 2; i++) {
        	 driver.findElement(By.xpath("//li[@id='product-2']//a")).click();
         }
         for (int i = 0; i < 5; i++) {
        	 driver.findElement(By.xpath("//li[@id='product-4']//a")).click();
         }
         for (int i = 0; i < 3; i++) {
        	 driver.findElement(By.xpath("//li[@id='product-7']//a")).click();
         }
         
         //Go to cart page
         driver.findElement(By.xpath("//a[@href='#/cart']")).click();
         
		 // Get the subtotal for each product based on its xpath and split the text to get the number
		 String subtotalFrog = driver.findElement(By.xpath("//img[@src='images/src-embed/frog.jpg']/../following-sibling::td[3]")).getText();
		 subtotalFrog = subtotalFrog.substring(1);
		 		 
		 String subtotalBunny = driver.findElement(By.xpath("//img[@src='images/src-embed/bunny.jpg']/../following-sibling::td[3]")).getText();
		 subtotalBunny = subtotalBunny.substring(1);
		 
		 String subtotalValentine = driver.findElement(By.xpath("//img[@src='images/src-embed/valentine.jpg']/../following-sibling::td[3]")).getText();
		 subtotalValentine = subtotalValentine.substring(1);		 
		 
		 //Verify the subtotal for each product is correct
		 Assertions.assertEquals("21.98", subtotalFrog);
		 Assertions.assertEquals("49.95", subtotalBunny);
		 Assertions.assertEquals("44.97", subtotalValentine);
		 
		 // Get each product price based on its xpath 
		 String priceFrog = driver.findElement(By.xpath("//img[@src='images/src-embed/frog.jpg']/../following-sibling::td[1]")).getText();
		 String priceBunny = driver.findElement(By.xpath("//img[@src='images/src-embed/bunny.jpg']/../following-sibling::td[1]")).getText();
		 String priceValentine = driver.findElement(By.xpath("//img[@src='images/src-embed/valentine.jpg']/../following-sibling::td[1]")).getText();
		 //Verify the price for each product
		 Assertions.assertEquals("$10.99", priceFrog);
		 Assertions.assertEquals("$9.99", priceBunny);
		 Assertions.assertEquals("$14.99", priceValentine);
		 
		 // Change the data type
		 float subtotalF = Float.parseFloat(subtotalFrog);
		 float subtotalB = Float.parseFloat(subtotalBunny);
		 float subtotalV = Float.parseFloat(subtotalValentine);
		 //Verify that total = sum(sub totals)
		 float expected_total = subtotalF + subtotalB + subtotalV;
		 float total = 116.90f;
		 Assertions.assertEquals(expected_total, total, 0.01);     
    }    

}
