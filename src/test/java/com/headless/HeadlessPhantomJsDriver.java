package com.headless;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class HeadlessPhantomJsDriver

{

	WebDriver driver = null;
	String path = Paths.get("").toAbsolutePath().toString();

	@Test
	public void headlessPhantomJsBrowserTesting() {

		System.out.println("Working Directory = " + path);

		DesiredCapabilities caps = new DesiredCapabilities();
		((DesiredCapabilities) caps).setJavascriptEnabled(true);
		((DesiredCapabilities) caps).setCapability("takesScreenshot", true);
		((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				path + "/libs/phantomjs");

		driver = new PhantomJSDriver(caps);

		driver.manage().window().setSize(new Dimension(1280, 1024));
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.get("https://sites.google.com/view/helpcenter24x7/copy-of-home");

		System.out.println("Header text : " + driver.findElement(By.id("h.fvzqb94btn9")).getText());
		assertEquals("How can we help you?", driver.findElement(By.id("h.fvzqb94btn9")).getText());
		takeScreenShot("HomePage_");

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
