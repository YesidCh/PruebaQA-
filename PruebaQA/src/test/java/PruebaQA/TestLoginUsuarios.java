package PruebaQA;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestLoginUsuarios {
	private WebDriver driver;
	
    By emailRegister = By.id("email");
    By passwordRegister = By.cssSelector("input[id='password']");
    By buttonSignIn = By.xpath("/html/body/app-root/app-sign-in/main/section[1]/app-sign-in-form/form/button");
    By validatePasswordRegister = By.cssSelector("app-password[id='password']");
    By userName = By.xpath("/html/body/app-root/app-panel-root/app-navbar/div/div[2]/div/div/h2");
    By userImg = By.xpath("/html/body/app-root/app-panel-root/app-navbar/div/div[2]/div/div/label");
    By userLogout = By.xpath("/html/body/app-root/app-panel-root/app-navbar/div/div[2]/div/ul/li[3]/a");
  
    @Before
    public void setUp() {
        // Configuración del WebDriver
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
		driver.get("https://test-qa.inlaze.com/auth/sign-in");
    }

    @Test
    public void testRegistroUsuario() {
    	
    	// Ingresamos al link de ingreso  	
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		assertEquals ("Inlaze - QA Test Front", driver.getTitle());
			
    			
		//--------------Prueba habilitar Login hasta que todos los campos esten completos---------
        driver.findElement(emailRegister).clear();
        driver.findElement(passwordRegister).clear();
        driver.findElement(emailRegister).sendKeys("email1@ejemplo.com");      
        driver.findElement(passwordRegister).sendKeys("Password123");
        
        String classFieldEmail = driver.findElement(emailRegister).getAttribute("class");
        String classFieldPassword = driver.findElement(validatePasswordRegister).getAttribute("class"); 
        boolean isValidButtonSingUp = driver.findElement(buttonSignIn).isEnabled();
        
        boolean isValidEmail = false;
        boolean isValidPassword = false;
        for (String classEmail : classFieldEmail.split(" ")) {
            if (classEmail.equals("ng-valid")) {
            	isValidEmail = true;
                break;
            }
        }	 
        for (String classPassword : classFieldPassword.split(" ")) {
            if (classPassword.equals("ng-valid")) {
            	isValidPassword = true;
                break;
            }
        }     
        if (isValidEmail==true && isValidPassword== true) {
        	if (isValidButtonSingUp) {
        	System.out.println("Si esta validando que todos los campos esten completos");
        	}
        }
//------ Verificar que se pueda registrar un usuario con datos válidos -------------------------
       driver.findElement(buttonSignIn).click();
	
//------Prueba para comprobar que al ingresar se muestre el nombre del usuario. -------------------------
       
        List<WebElement> userRegistered = driver.findElements(userName);  
      if (userRegistered.isEmpty()) {
        System.out.println("No muestra el nombre del usuario");
      } else {
    	System.out.println("Si muestra el nombre del usuario");
      }
       
      
 //------Prueba para verificar que la plataforma permita cerrar la sesión correctamente.---------
      driver.findElement(userImg).click();
      driver.findElement(userLogout).click();
           
    	}
       
    

    @After
    public void tearDown() {
        // Cerrar el navegador
        driver.quit();
        
    }
}