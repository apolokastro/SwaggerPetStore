package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport implements ITestListener {
	
	public ExtentSparkReporter reporter;
	public ExtentReports report;
	public ExtentTest test;
	
	String reportName;
	
	public void onStart(ITestContext context) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH_mm_ss");
		
		String timeStamp = now.format(formatter);
		reportName="Test-Report-" + timeStamp + ".html";
		
		reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + reportName);
		
		reporter.config().setDocumentTitle("API Test Report");
		reporter.config().setReportName("Automation API Report");
		reporter.config().setTheme(Theme.STANDARD);
		
		report = new ExtentReports();
		
		report.attachReporter(reporter);
		report.setSystemInfo("App", "PetStore  User api");
		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("Tester", System.getProperty("user.name"));
		report.setSystemInfo("Environment", "QA");
	}
	
	public void onTestSuccess(ITestResult result) {
		test = report.createTest(result.getTestClass().getRealClass().getSimpleName() + " : " + result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		test = report.createTest(result.getTestClass().getRealClass().getSimpleName() + " : " + result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onTestSkiped(ITestResult result) {
		test = report.createTest(result.getTestClass().getRealClass().getSimpleName() + " : " + result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skiped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		report.flush();
	}
	
	
}
