package pages;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import regions.HeaderMenu;

public class Base extends Page {
	
	private By loginButton = By.id("login-button");
	private By loginEmail = By.id("email");
	private By loginPass = By.id("pass");
	
	private By spLogoLocator = By.cssSelector(".header-logo");
	private By spLogoLinkLocator = By.cssSelector(".header-logo a");
	private String baseUrl = "https://www.sneakpeeq.com/";
	
	public Base(WebDriver d) {
		super(d);
	}
	
	public Home login(String email, String pass){
		//wait for login button
		this.getWait().until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(loginButton);
			}
		});
		//Save the current windows handle
		String parentWindowHandle = this.driver.getWindowHandle();
		
		this.driver.findElement(loginButton).click();
		this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriver loginPageDriver = null;
		Set<String> windowHandles = this.driver.getWindowHandles();
		for(String handle : windowHandles) {
			loginPageDriver = this.driver.switchTo().window(handle);
			if(loginPageDriver.getTitle().equals("Log In | Facebook"))
				break;
		}
		
		//Login
		loginPageDriver.findElement(loginEmail).clear();
		loginPageDriver.findElement(loginEmail).sendKeys(email);
		loginPageDriver.findElement(loginPass).clear();
		loginPageDriver.findElement(loginPass).sendKeys(pass);
		loginPageDriver.findElement(loginButton).click();
		
		this.driver.switchTo().window(parentWindowHandle);
		
		return new Home(this.driver);
	}
	
	public String getPageTitle() {
		this.getWait().until(new ExpectedCondition<String>() {
			@Override
			public String apply(WebDriver d) {
				return d.getTitle();
			}
		});
		return this.driver.getTitle();
	}
	
	public boolean isSpLogoVisible() {
		return this.isElementVisible(this.spLogoLocator);
	}
	
	public Home clickSpLogo() {
		this.driver.findElement(spLogoLinkLocator).click();
		return new Home(this.driver);
	}
	
	public HeaderRegion header() {
		return new HeaderRegion(this.driver);
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public class HeaderRegion extends Page {
		
		//Logged in
		private By accountControllerLocator = By.className("header-panel");
		private By accountDropdownLocator = By.cssSelector(".member-dropdown hide a");
		private By accountPicLocator = By.cssSelector(".header-member img");
		
		//Navigation menu
		private By siteNavigationMenusLocator = By.cssSelector("nav > a");
		
		
		public HeaderRegion(WebDriver d) {
			super(d);
		}
		
		public boolean isAccountPicVisible() {
			return this.isElementVisible(accountPicLocator);
		}
		
		public boolean isAccountDropdownPresent() {
			return this.isElementPresent(accountDropdownLocator);
		}
		
		/***
		 * This returns a menu selector
		 ***/
		public HeaderMenu siteNavigationMenu(String value) throws Exception {
			for(HeaderMenu m : this.siteNavigationMenus()) {
				if(m.getMenuName().equals(value)) {
					return m;
					}
				 }
			throw new Exception("No such menu existed!");
		}
		
		/***
		 * This returns a list of menu selectors
		 ***/
		public List<HeaderMenu> siteNavigationMenus() {
			this.getWait().until(new ExpectedCondition<Integer>() {
				@Override
				public Integer apply (WebDriver d) {
					return d.findElements(siteNavigationMenusLocator).size();
				}
			});
			
			List<HeaderMenu> menus = new ArrayList<HeaderMenu>();
			
			for (WebElement e : this.driver.findElements(siteNavigationMenusLocator)) {
				HeaderMenu m = new HeaderMenu(this.driver, e);
				menus.add(m);
			}
			
			return menus;
		}
	}
}