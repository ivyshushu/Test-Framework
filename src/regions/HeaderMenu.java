package regions;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import pages.Base;
import pages.Living;
import pages.Page;

public class HeaderMenu extends Page {
	private By menuItemsLocator = By.cssSelector("ul > li");
	private By menuNameLocator = By.cssSelector("a");
	private By spLogoLocator = By.cssSelector(".header-logo");
	
	private WebElement rootElement;
	
	public HeaderMenu(WebDriver d, WebElement e) {
		super(d);
		this.rootElement = e;
	}
	
	public String getMenuName() {
		return this.rootElement.findElement(menuNameLocator).getText();
	}
	
	public Page click() {
		String menuName = this.getMenuName();
		this.rootElement.findElement(menuNameLocator).click();
		
		Actions a = new Actions(this.driver);
		a.moveToElement(rootElement.findElement(spLogoLocator)).perform();
		
		if(menuName.contains("Living")) {
			return new Living(this.driver);
		} else if(menuName.contains("Style")) {
			return new Style(this.driver);
		} else if(menuName.contains("Beauty")) {
			return new Beauty(this.driver);
		} else if(menuName.contains("Inspiration")) {
			return new Inspiration(this.driver);
		}
	}
	
	public void hover() {
		WebElement element = this.rootElement.findElement(menuNameLocator);
		Actions a = new Actions(this.driver);
		
		a.moveToElement(element).perform();
	}
	
	public boolean isDropdownMenuVisible() {
		WebElement dropdownMenu = this.rootElement.findElement(menuItemsLocator);
		return dropdownMenu.isDisplayed();
	}
	
	public List<WebElement> getMenuItems() {
		return this.rootElement.findElements(menuItemsLocator);
	}
	
	public class HeaderMenuItem extends Page {
		private By menuNameLocator = By.cssSelector("a");
		private WebElement rootElement;
		private HeaderMenu menuList;
		
		public HeaderMenuItem(WebDriver d, WebElement e, HeaderMenu m) {
			super(d);
			this.rootElement = e;
			this.menuList = m;
		}
		
		public String getMenuName() {
			this.menuList.hover();
			return this.rootElement.findElement(menuNameLocator).getText();
		}
		
		public Base click() {
			String menuName = this.menuList.getMenuName();
			this.menuList.hover();
			
			Actions a = new Actions(this.driver);
			a.moveToElement(rootElement.findElement(spLogoLocator)).click().perform();
			
			if(menuName.contains("Living")) {
				return Living(this.driver);
			} else if(menuName.contains("Style")) {
				return Style(this.driver);
			} else if(menuName.contains("Beauty")) {
				return Beauty(this.driver);
			} else if(menuName.contains("Inspiration")) {
				return Inspiration(this.driver);
			}
		} 
		
	}
}
