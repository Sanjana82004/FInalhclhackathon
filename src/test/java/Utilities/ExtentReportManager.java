package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
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

import TestCases.BaseClass;


public class ExtentReportManager implements ITestListener {
	 
	   public ExtentSparkReporter sparkReporter;
	   public ExtentReports extent;
	   public ExtentTest test;
	   String repName;
	   public void onStart(ITestContext testContext) {
		 
		   
		   String timeStamp = new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
		   repName = "Test-Report" +timeStamp+".html";
		   
		   sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName);
		   
		    sparkReporter.config().setDocumentTitle("opencart Automation Report");
		    sparkReporter.config().setReportName("opencart Functional Testing");
		     sparkReporter.config().setTheme(Theme.DARK);
		     
		     
		      extent = new ExtentReports();
		     extent.attachReporter(sparkReporter);
		     
		     
		     
		     extent.setSystemInfo("Application", "opencart");
		     extent.setSystemInfo("module", "Admin");
		     extent.setSystemInfo("Sub Module", "Customers");
		     extent.setSystemInfo("sanjana Tiwari", System.getProperty("user.name"));
		     extent.setSystemInfo("Environment", "QA");
		    
		 
	   } 
	   
	   public void onTestSuccess(ITestResult result) {
		   test = extent.createTest(result.getTestClass().getName());
		  
		   test.log(Status.PASS,result.getName()+ "got successfully executed");
		
		   
	   }
	   
	   
	   public void onTestFailure(ITestResult result) {
		   test = extent.createTest(result.getTestClass().getName()); // create new test entry in report
		 
		   test.log(Status.FAIL,result.getName()+ "got failed");
		   test.log(Status.INFO, result.getThrowable().getMessage());
		   
		   try {
			String imgPath = new BaseClass().captureScreen(result.getName())  ; 
			test.addScreenCaptureFromPath(imgPath);
		   }  catch(IOException e1) {
			   e1.printStackTrace();
		   }
		 
	   }
	   
	   public void onTestSkipped(ITestResult result) {
		   test = extent.createTest(result.getTestClass().getName());
		   // this line create a new test entry in the extent report
		  
		   test.log(Status.SKIP,result.getName()+ "got skipped");
		   test.log(Status.INFO, result.getThrowable().getMessage());
		   
		   
		 
	   }
	   
	   public void onFinish(ITestContext testContext) {
		 extent.flush();
		
		 String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		 File extentReport = new File(pathOfExtentReport);
		 try {
			 Desktop.getDesktop().browse(extentReport.toURI());
		 } catch(IOException e) {
			e.printStackTrace(); 
		 }
		 
		 
	   }
	   
	   
	   
	   
	}