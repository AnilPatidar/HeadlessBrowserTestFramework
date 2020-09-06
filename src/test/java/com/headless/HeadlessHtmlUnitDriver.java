package com.headless;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Unit test for simple App.
 * 
 * HtmlUnitDriver is headless browser,
 * 
 * Features:
 * 
 *  Light Weight Fastest Implementation of WebDriver Platform
 * 
 * Independent There is support for proxy server available Support for the HTTPS
 * and HTTP protocols Support for HTML responses ( clicking links, submitting
 * forms, walking the DOM model of the HTML document etc.)
 * 
 * Support for cookies ,Proxy server support Support for basic and NTLM authentication Excellen
 * t
 * JavaScript support Support for submit methods GET and POST Ability to
 * customize the request headers being sent to the server Ability to determine
 * whether failing responses from the server should throw exceptions or should
 * be returned as pages of the appropriate type
 * 
 * Minimal visual interruption because of lack of graphical display Swifter
 * execution as compared to real browsers because the simulated browser is a
 * mere native code library hence faster to launch and doesn’t require much time
 * in communication as well as drawing and operating things on your screen.
 * 
 * Platform independent and supports concurrent test runs. Hence, most suitable
 * for Load testing. Is a pure Java based solution using the JS engine The
 * simulated browser is filled with key features depicting a rich client-side
 * behaviour with quicker execution, as compared to other driver instances Is
 * able to deal with HTTPS security, as well Possesses more dependability than a
 * real browser. Simulated browsers are highly independent and hence its
 * operation is quite smooth and dependable Provision to choose other browser
 * versions to testing using HtmlUnitDriver It is platform independent and
 * easier to run several tests concurrently. Ideal for Load Testing.
 * 
 * Challenges faced while using HtmlUnitDriver for headless browser testing
 * 
 * henever a test fails or is interrupted, it becomes tough to debug since it is
 * difficult to see it physically on the browser of your machine. Even if there
 * are chances of debugging, the time frame to do the same is quite high
 * Simulation of other browsers is possible, e.g. Firefox, IE etc. but there is
 * no surety to which extent these real world browsers would be replicated
 * JavaScript is disabled, by default and would have to be enabled when the
 * script is being written For other browsers, emulation of JS behavior is
 * difficult
 */
public class HeadlessHtmlUnitDriver

{

	@Test
	public void headlessBrowserTesting() throws InterruptedException {

		WebDriver driver = new HtmlUnitDriver(true);

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

		driver.quit();
	}

}
