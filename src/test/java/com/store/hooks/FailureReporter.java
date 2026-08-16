package com.store.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FailureReporter extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
        WebDriver driver = (WebDriver) tr.getTestContext().getAttribute("driver");
        if (driver != null) {
            try {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                Path targetDir = Path.of("screenshots");
                Files.createDirectories(targetDir);
                Files.copy(screenshot.toPath(), targetDir.resolve("failure_" + time + ".png"));
            } catch (IOException | RuntimeException e) {
                System.err.println("Unable to capture screenshot: " + e.getMessage());
            }
        }
    }
}
