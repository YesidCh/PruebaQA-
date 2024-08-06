package PruebaQA;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestRegistroUsuarios {
	private WebDriver driver;
	
    By registerLink = By.xpath("/html/body/app-root/app-sign-in/main/section[1]/app-sign-in-form/span/a");
    By confirmationTextPagRegister = By.xpath("/html/body/app-root/app-sign-up/main/section[2]/app-sign-up-form/h1");
    By nameRegister = By.id("full-name");
    By emailRegister = By.id("email");
    By passwordRegister = By.cssSelector("input[id='password']");
    By confirmPasswordRegister = By.cssSelector("input[id='confirm-password']");
    By buttonSingUp = By.xpath("/html/body/app-root/app-sign-up/main/section[2]/app-sign-up-form/form/button");
    By validatePasswordRegister = By.cssSelector("app-password[id='password']");
    By validateConfirmPasswordRegister = By.cssSelector("app-password[id='confirm-password']");
    By TextConfirmPasswordEquals = By.xpath("/html/body/app-root/app-sign-up/main/section[2]/app-sign-up-form/form/div[4]/label[2]/span");
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
    	
    	// Ingresamos al link de registro
    	
    	driver.findElement(registerLink).click();
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    	
    	if(driver.findElement(confirmationTextPagRegister).isDisplayed() ) { // confirmamos que la pagina se encuentre cargada
    	
//-------------- Validar que el campo de nombre acepte mínimo 2 palabras
    		
    		driver.findElement(nameRegister).sendKeys("Yesid");
            String fieldName = driver.findElement(nameRegister).getAttribute("class");
            boolean foundClass = false;
            for (String className : fieldName.split(" ")) {
                if (className.equals("ng-invalid")) {
                    foundClass = true;
                    break;
                }
            }
            if (foundClass) {
                System.out.println("El campo Full name es inválido para una palabra");
            }
            
            driver.findElement(nameRegister).clear();
            driver.findElement(nameRegister).sendKeys("Yesid Acevedo");
            fieldName = driver.findElement(nameRegister).getAttribute("class");
            foundClass = false;
            for (String className : fieldName.split(" ")) {
                if (className.equals("ng-valid")) {
                    foundClass = true;
                    break;
                }
            }
            if (foundClass) {
                System.out.println("El campo Full name acepta minimo 2 palabras");
            }
           
 //----------- Validar que el email tenga un formato estándar   
            
            driver.findElement(emailRegister).sendKeys("formatoinvalido");
            String fieldEmail = driver.findElement(emailRegister).getAttribute("class");  
            foundClass = false;
            for (String classEmail : fieldEmail.split(" ")) {
                if (classEmail.equals("ng-valid")) {
                    foundClass = true;
                    break;
                }
            }
            if (foundClass) {
                System.out.println("El campo Email recibe formatos invalidos, sin @");
            }      
            driver.findElement(emailRegister).clear();
            driver.findElement(emailRegister).sendKeys("#");
            fieldName = driver.findElement(nameRegister).getAttribute("class");
            foundClass = false;
            for (String classEmail : fieldName.split(" ")) {
                if (classEmail.equals("ng-valid")) {
                    foundClass = true;
                    break;
                }
            }
            if (foundClass) {
                System.out.println("El campo Email recibe formatos invalidos, con #");
            }
                      
  //--------La contraseña debe tener al menos 8 caracteres, 
   //-------incluyendo una mayúscula, una minúscula, un número y un carácter especial.
            
            //---Prueba de sin caracter especial
            driver.findElement(passwordRegister).clear();
            driver.findElement(passwordRegister).sendKeys("Password123");
            String fieldPassword = driver.findElement(validatePasswordRegister).getAttribute("class"); 
            foundClass = false;
            for (String classPassword : fieldPassword.split(" ")) {
                if (classPassword.equals("ng-valid")) {
                    foundClass = true;                    
                    break;
                }
            }	    	
	        if (foundClass) {
	            System.out.println("El campo Password no necesita carater especial");
	        }	
	      //---Prueba de sin numero 
	        driver.findElement(passwordRegister).clear();
            driver.findElement(passwordRegister).sendKeys("Password");//
            fieldPassword = driver.findElement(validatePasswordRegister).getAttribute("class");  
            foundClass = false;
            for (String classPassword : fieldPassword.split(" ")) {
                if (classPassword.equals("ng-invalid")) {
                    foundClass = true;
                    break;
                }
            }	    	
	        if (foundClass) {
	            System.out.println("El campo Password si necesita un numero");
	        } 
	        
	      //---Prueba de sin minuscula
	        driver.findElement(passwordRegister).clear();
            driver.findElement(passwordRegister).sendKeys("PASSWORD1");//
            fieldPassword = driver.findElement(validatePasswordRegister).getAttribute("class");  
            foundClass = false;
            for (String classPassword : fieldPassword.split(" ")) {
                if (classPassword.equals("ng-invalid")) {
                    foundClass = true;
                    break;
                }
            }	    	
	        if (foundClass) {
	            System.out.println("El campo Password si necesita una minuscula");
	        } 
	      //---Prueba de sin mayuscula
	        driver.findElement(passwordRegister).clear();
            driver.findElement(passwordRegister).sendKeys("password1");//
            fieldPassword = driver.findElement(validatePasswordRegister).getAttribute("class");  
            foundClass = false;
            for (String classPassword : fieldPassword.split(" ")) {
                if (classPassword.equals("ng-invalid")) {
                    foundClass = true;
                    break;
                }
            }	    	
	        if (foundClass) {
	            System.out.println("El campo Password si necesita una mayuscula");
	        } 
	        //---Prueba de sin cantidad de caracteres
	        driver.findElement(passwordRegister).clear();
            driver.findElement(passwordRegister).sendKeys("Pass1");//
            fieldPassword = driver.findElement(validatePasswordRegister).getAttribute("class");  
            foundClass = false;
            for (String classPassword : fieldPassword.split(" ")) {
                if (classPassword.equals("ng-valid")) {
                    foundClass = true;
                    break;
                }
            }	    	
	        if (foundClass) {
	            System.out.println("El campo Password no necesita minimo 8 caracteres");
	        } 
	        
//--------------Prueba la confirmacion de contraseña valida los datos ingresados---------
	        driver.findElement(passwordRegister).clear();
	        driver.findElement(confirmPasswordRegister).clear();
	        driver.findElement(passwordRegister).sendKeys("Pass1");
            driver.findElement(confirmPasswordRegister).sendKeys("Pass1");//
            List<WebElement> mesajeError = driver.findElements(TextConfirmPasswordEquals);  
          if (mesajeError.isEmpty()) {
            System.out.println("Las contraseñas coinciden, si se hace la validacion");
          } else {
        	System.out.println("Las contraseñas no coinciden");
          }
     
 //--------------Prueba habilitar Submit hasta que todos los campos esten completos---------
            driver.findElement(nameRegister).clear();
	        driver.findElement(emailRegister).clear();
            driver.findElement(passwordRegister).clear();
	        driver.findElement(confirmPasswordRegister).clear();
	        driver.findElement(nameRegister).sendKeys("Yesid Felipe Acevedo");
            driver.findElement(emailRegister).sendKeys("email1@ejemplo.com");      
            driver.findElement(passwordRegister).sendKeys("Password123");
            driver.findElement(confirmPasswordRegister).sendKeys("Password123");
           // driver.findElement(buttonSingUp).click(); 
            
            String classFieldName = driver.findElement(nameRegister).getAttribute("class");
            String classFieldEmail = driver.findElement(emailRegister).getAttribute("class");
            String classFieldPassword = driver.findElement(validatePasswordRegister).getAttribute("class");
            String classFieldConfirmPassword = driver.findElement(validateConfirmPasswordRegister).getAttribute("class");  
            boolean isValidButtonSingUp = driver.findElement(buttonSingUp).isEnabled();
            
            boolean isValidName = false;
            boolean isValidEmail = false;
            boolean isValidPassword = false;
            boolean isValidConfirmPassword = false;
            for (String className : classFieldName.split(" ")) {
                if (className.equals("ng-valid")) {
                	isValidName = true;
                    break;
                    }
            }
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
            for (String classConfirmPassword : classFieldConfirmPassword.split(" ")) {
                if (classConfirmPassword.equals("ng-valid")) {
                	isValidConfirmPassword = true;
                    break;
                }
            }           
            if ((isValidName==true && isValidEmail==true) && (isValidPassword== true && isValidConfirmPassword== true)) {
            	if (isValidButtonSingUp) {
            	System.out.println("Si esta validando que todos los campos esten completos");
            	}
            }
    //------ Verificar que se pueda registrar un usuario con datos válidos
            driver.findElement(buttonSingUp).click();
         
               		
    	} else {
    		System.out.print("No se puedo encontar la pagina de registro");
    	}
       
    }

    @After
    public void tearDown() {
        // Cerrar el navegador
          driver.quit();
        
    }
}
