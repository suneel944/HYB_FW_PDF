package properties;

import org.openqa.selenium.By;

public class orAmazonCommon 
	{
	//Amazon Search Box
	public static By txtAmazonSearchBox = By.id("twotabsearchtextbox");
	
	public static By btnSearch = By.xpath("(.//*[@type='submit'])[1]");
	
	public static By lnkFirstProduct = By.xpath("(.//*[@data-component-type='s-product-image']//a)[1]");
	
	public static By resultText = By.xpath(".//*[contains(text(),'results for')]");
	
	public static By btnBuyNow = By.id("buy-now-button");
	
	public static By weLoginSection = By.xpath(".//*[@name='signIn']");
	}
