from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time
import csv


public class AppTest {

    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
         WebDriverManager.chromedriver().setup();
             ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-web-security");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        
        driver = new ChromeDriver(options);
    }
 @Test
    public void testcase_1() throws InterruptedException{


driver = webdriver.Chrome()
driver.maximize_window()
driver.get("https://demoqa.com/login")


wait=WebDriverWait(driver,10)

with open('data.csv','r') as file:
    reader=csv.DictReader(file)
    for row in reader:
        user_input=wait.until(EC.visibility_of_element_located((By.ID,"userName")))
        driver.execute_script("arguments[0].scrollIntoView(true);",user_input)
        ActionChains(driver).move_to_element(user_input).perform()

        user_input.clear()
        user_input.send_keys(row['username'])

        pass_input=wait.until(EC.visibility_of_element_located((By.ID,"password")))
        driver.execute_script("arguments[0].scrollIntoView(true);",pass_input)
        ActionChains(driver).move_to_element(pass_input).perform()

        pass_input.clear()
        pass_input.send_keys(row['password'])

        login_button=wait.until(EC.visibility_of_element_located((By.ID,"login")))
        driver.execute_script("arguments[0].scrollIntoView(true);",login_button)
        login_button.click()
        time.sleep(2)
        driver.get("https://demoqa.com/login")


driver.quit()
}


 @AfterTest
    public void afterTest() {
        driver.quit();
    }
}