package reports;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;

	public void onStart(ITestContext context) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-report" + timestamp + ".html";

		spark = new ExtentSparkReporter("C:\\Users\\Thejas gowda\\Downloads\\practice2-master\\practice2-master\\report\\" + repName);

		spark.config().setDocumentTitle("Demo report");
		spark.config().setReportName("Demo framework report");
		spark.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Application", "selenium test framework");
		extent.setSystemInfo("Operating system", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Thejas");

	}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		//test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
		
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		//test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
	}
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
