package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.ElementNotVisibleException;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.WebDriverWait;

import regions.HeaderMenu;

public class Base extends Page {
	
	private By loginLink = By.id("login-button");
	private By loginEmail = By.id("email");
	private By loginPass = By.id("pass");
	private By loginButton = By.id("loginbutton");
	
	private By spLogoLocator = By.cssSelector(".header-logo");
	private By spLogoLinkLocator = By.cssSelector(".header-logo a");
	private String baseUrl = "https://www.sneakpeeq.com/";
	
	public Base(WebDriver d) {
		super(d);
	}
	
	public void login(String email, String pass){
		//wait for login button
		this.getWait().until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(loginLink);
			}
		});
		
		//Save the current windows handle
		String parentWindowHandle = this.driver.getWindowHandle();
		
		this.driver.findElement(loginLink).click();
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
		//return new Home(this.driver);
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
		private By accountSectionLocator = By.className("header-panel");
		private By accountDropdownLocator = By.cssSelector(".member-dropdown hide a");
		private By accountImgLocator = By.cssSelector(".header-member img");
		
		//Navigation menu section
		private By siteNavigationMenusLocator = By.cssSelector("nav > a");
		//private By menuLivingLocator = By.id("selector-living");
		private List<HeaderMenu> headerMenus;
		private int menusCount = 4;
		
		public HeaderRegion(WebDriver d) {
			super(d);
			this.headerMenus = new ArrayList<HeaderMenu>();
		}
		
		//After log in, Account section
		public boolean isAccountSectionPresent() {
			return this.isElementPresent(accountSectionLocator);
		}
		public boolean isAccountImgVisible() {
			return this.isElementVisible(accountImgLocator);
		}
		
		public boolean isAccountDropdownPresent() {
			return this.isElementPresent(accountDropdownLocator);
		}
		
		//public boolean isEyeIconVisible() {}
		//public boolean isCartIconVisble() {}
		//......
		
		//find a menu
		public HeaderMenu getSiteNavigationMenu(String value) throws Exception {
			for(HeaderMenu m : this.getSiteNavigationMenus()) {
				if(m.getMenuName().equals(value)) {
					return m;
					}
				 }
			throw new Exception("No such menu existed!");
		}
		
		//find all the menus
		public List<HeaderMenu> getSiteNavigationMenus() {
			this.getWait().until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply (WebDriver d) {
					return d.findElements(siteNavigationMenusLocator).size() == menusCount;
				}
			});
			
			for (WebElement e : this.driver.findElements(siteNavigationMenusLocator)) {
				HeaderMenu m = new HeaderMenu(this.driver, e);
				this.headerMenus.add(m);
			}
			
			return headerMenus;
		}
		
		public void clickMenu(String name) {
			try {
				HeaderMenu menu = this.getSiteNavigationMenu(name);
				menu.click();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		
		public HeaderMenu hoverMenu(String name) {
			try {
				HeaderMenu menu = this.getSiteNavigationMenu(name);
				menu.hover();
				return menu;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} 
		
	}
}
