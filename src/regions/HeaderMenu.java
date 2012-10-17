package regions;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import pages.Page;

public class HeaderMenu extends Page {
	
	private By menuItemsLocator = By.cssSelector("ul>li>a");
	private String menuNameLocator = "data-store";
	private String menuItemStyle = "sytle";
	
	//rootElement is the menu need to check out
	private WebElement rootElement;
	private List<HeaderMenuItem> menuItems;
	
	public HeaderMenu(WebDriver d, WebElement e) {
		super(d);
		this.rootElement = e;
		this.menuItems = null;
	}
	
	public String getMenuName() {
		return this.rootElement.getAttribute(menuNameLocator);
	}
	
	public void hover() {
		Actions a = new Actions(this.driver);
		a.moveToElement(this.rootElement).perform();
	}
	
	public void click() {
		Actions a = new Actions(this.driver);
		a.click(this.rootElement).perform();
	}
	
	public boolean isDropdownMenuVisible() {
		return this.getDriver().findElement(By.cssSelector("ul")).getAttribute("style").equals("display: block; ");
	}
	
	public HeaderMenuItem getMenuItem(String name) throws Exception {
		for(HeaderMenuItem item : this.getMenuItems()) {
			if(item.getItemName().equals(name))
				return item;
		}
		throw new Exception("No such menu item existed!");
	}
	
	public List<HeaderMenuItem> getMenuItems() {
		for(WebElement item : this.rootElement.findElements(menuItemsLocator)) {
			this.menuItems.add(new HeaderMenuItem(this.getDriver(), item, this));
		}
		return this.menuItems;
	}
	
	public class HeaderMenuItem extends Page {
		private By itemNameLocator = By.cssSelector(".store-name");
		
		private WebElement rootElement;
		private HeaderMenu menu;
		//img, tagline, remaining time
		
		public HeaderMenuItem(WebDriver d, WebElement r, HeaderMenu m) {
			super(d);
			this.rootElement = r;
			this.menu = m;
		}
		
		public String getItemName() {
			this.menu.hover();
			return this.rootElement.findElement(itemNameLocator).getText();
		}
		
	    public void click() {
	    	this.menu.hover();
	    	Actions builder = new Actions(this.getDriver());
	    	builder.moveToElement(rootElement).click().build().perform();
	    }
	    
		
	}
}
