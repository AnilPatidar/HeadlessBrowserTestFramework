package com.headless;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class HeadlessChromeDriver

{
	WebDriver driver = null;
	String path = Paths.get("").toAbsolutePath().toString();


	@Test
	public void headlessChromeBrowserTesting() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "/Users/anilpatidar/Downloads/chromedriver");

		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--headless");
		 driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com");

		driver.manage().addCookie(new Cookie("foo", "bar"));

		//Coookie
		Cookie cookie1 = driver.manage().getCookieNamed("foo");
		System.out.println("Cookie : " + cookie1);
		System.out.println("");
		System.out.println("Email text : " + driver.findElement(By.id("email")).getAttribute("placeholder"));
		assertEquals("Email address or phone number", driver.findElement(By.id("email")).getAttribute("placeholder"));
		
		//Java Script Executor
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@name ='login']")));
		System.out.println("current url : " + driver.getCurrentUrl());
		
		assertEquals("https://www.facebook.com/login/",driver.getCurrentUrl());
		takeScreenShot("fb_login_page");

		driver.quit();
	}
	
	public void takeScreenShot(String checkName) {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		File DestFile = new File(path + "/Screens/" + checkName + "" + System.currentTimeMillis() + ".png");
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
