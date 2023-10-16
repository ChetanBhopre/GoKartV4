package StepDefination;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Landing_Page {
	public WebDriver driver;
	public String productName;
	public String offerpgeProuctName;
	public WebDriverWait wait;

	

	@Given("user is on Greenkart landing page")
	public void user_is_on_greenkart_landing_page() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	}

	@When("User searched with shortname {string} and extract name of the product")
	public void user_searched_with_shortname_and_extract_name_of_the_product(String shortname) {

		driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortname);

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h4.product-name")));

		String product = driver.findElement(By.cssSelector("h4.product-name")).getText().trim();
		productName = product.split("-")[0].trim();
		System.out.println(productName);

	}

	@Then("user search for {string} shortname in offer page")
	public void user_search_for_shortname_in_offer_page(String shortname) throws InterruptedException {
		driver.findElement(By.linkText("Top Deals")).sendKeys(Keys.ENTER);

		Set<String> Windows = driver.getWindowHandles();

		for (String window : Windows) {

			driver.switchTo().window(window);
			System.out.println(driver.getTitle());

			if (driver.getCurrentUrl().equalsIgnoreCase("https://rahulshettyacademy.com/seleniumPractise/#/offers")) {
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortname);
				wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//td[1]")));
				offerpgeProuctName = driver.findElement(By.xpath("//tbody//td[1]")).getText();

			}
		}

	}

	@Then("Validate same product name machtes with landing page")
	public void validate_same_product_name_machtes_with_landing_page() {

		Assert.assertEquals(offerpgeProuctName, productName);

	}

}