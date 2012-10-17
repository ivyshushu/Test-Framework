package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	protected WebDriver driver;
	
	//private final String pageTitle;
	//private final String pageUrl;
	
	private WebDriverWait wait;
	
	public Page(WebDriver d){
		this.driver = d;
		wait = new WebDriverWait(this.driver, 10);
	    //this.pageTitle = t;
		//this.pageUrl = u;
	}
	
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriverWait getWait() {
		return wait;
	}
	
    /****
	public String getPageTitle() {
		return this.pageTitle;
	}
	
	public String getPageUrl() {
		return this.pageUrl;
	}
	***/
	
	
	public static class CommonPageLocator{
		
	}
	
	public void getToUrl(String url) {
		this.driver.get(url);
	}
	
	public boolean isCurrentPage(String expectedTitle) {
		if(this.driver.getTitle().isEmpty()) {
			this.wait.until(new ExpectedCondition<String>() {
				@Override
				public String apply (WebDriver d) {
					return d.getTitle();
				}
			});
		}
		
		if(expectedTitle.equals(this.driver.getTitle()))
			return true;
		else
			return false;
	}
	
	public String getCurrentUrl() {
		return this.driver.getCurrentUrl();
	}
	
	public boolean isElementPresent(By by) {
		try {
			this.driver.findElement(by);
			return true;
		} catch(NoSuchElementException e) {
			return false;
		} catch(StaleElementReferenceException e) {
			return false;
		}
	}
	
	public boolean isElementVisible(By by) {
		try {
			this.driver.findElement(by).isDisplayed();
			return true;
		} catch(NoSuchElementException e) {
			return false;
		} catch(ElementNotVisibleException e) {
			return false;
		} catch(StaleElementReferenceException e) {
			return false;
		}
	}
	
	public ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {
				WebElement element = d.findElement(by);
				return element.isDisplayed() ? element : null;
			}
		};
	}
	
}