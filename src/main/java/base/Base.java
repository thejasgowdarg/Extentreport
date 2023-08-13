package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public static Properties prop;
	public static WebDriver driver;
	
	public Base(){
		
		 prop= new Properties();
		 FileInputStream file=null;
		 try {
			file=new FileInputStream("C:\\Users\\Thejas gowda\\eclipse-workspace\\Ninja\\src\\main\\java\\config\\data.proprties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			prop.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
			 
		 }
	public static void launchBrowser() {
		String browserName=prop.getProperty("browser");
		if(browserName.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--remote-allow-origins=*"); 
			//System.setProperty("webdriver.chromedriver","chromedriver.exe");
			driver=new ChromeDriver(ops);
		 
		 
	}else if(browserName.equals("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		//System.setProperty("webdriver.gechodriver", "gechodriver.exe");
		driver=new FirefoxDriver();
	}else if(browserName.equals("edge")){
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
		
	}else {
		System.out.println("invalid browserName "+browserName);
				//throw new Exception("invalid exception "+browserName);
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get(prop.getProperty("url"));
	}

}
