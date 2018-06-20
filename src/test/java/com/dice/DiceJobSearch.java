package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().fullscreen();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String url = "https://dice.com";
		driver.get(url);
		
		String actualTitle = driver.getTitle();	
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";
		
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Step PASS. Dice homepage successully passed");
		}else {
			System.out.println("Step FAIL. Dice homepage did not load successully");
			//if page not open it will stop in this poing - throw new RuntimeException
			throw new RuntimeException("Step FAIL. Dice homepage did not load successully"); 
		}
		
		String keyword = "Software tester";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location = "78233";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click(); // .submit(); can do	
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		//ensure count is more than 0
		int countResult = Integer.parseInt(count.replaceAll(",", "")); //"," aradan qaldirdiq error vermesin diye
		
		if (countResult >0) {
			System.out.println("Keyword: " + keyword + " search returned " + countResult + " result in " + location);
		}else {
			System.out.println("Step FAIL: Keyword: " + keyword + " search returned " + countResult + " result in " + location);
		}
		driver.close();
	}

}
