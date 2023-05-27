package com.planit.myapp;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverBase {
	
	protected static WebDriver driver;
	protected static String planitUrl = "https://jupiter.cloud.planittesting.com/";
	
	//local browser: mvn clean test -Dwebdriver.chrome.driver="C:\\Opt\\Selenium\\chromedriver.exe"
	//protected static String ChromeDriver = "C:\\Opt\\Selenium\\chromedriver.exe";
	
	@BeforeAll
	static void setup(){
		System.out.println("@BeforeAll executed");
	    driver = new ChromeDriver();		
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get(planitUrl);
	}

	@BeforeEach
	void setupThis(){
		System.out.println("@BeforeEach executed");
	}

    @AfterEach
	void tearThis(){
	    driver.manage().deleteAllCookies();
    	System.out.println("@AfterEach executed");
	}

	@AfterAll
	static void tear(){
		System.out.println("@AfterAll executed");
	    driver.close();
	}
}
