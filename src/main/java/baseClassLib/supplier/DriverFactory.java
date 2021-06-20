package baseClassLib.supplier;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DriverFactory
{
    /**
     * User Agent Counters
     */
    private static int chrome=0, ie=0, firefox=0, msedge=0, opera=0;

    private static final Supplier<WebDriver> chromeSupplier = () -> {
        WebDriverManager.chromedriver().setup();
        ++chrome;
        return new ChromeDriver();
    };

    private static final Supplier<WebDriver> firefoxSupplier = () -> {
        WebDriverManager.firefoxdriver().setup();
        ++firefox;
        return new FirefoxDriver();
    };

    private static final Supplier<WebDriver> edgeSupplier = () -> {
        WebDriverManager.edgedriver().setup();
        ++msedge;
        return new EdgeDriver();
    };

    private static final Supplier<WebDriver> ieSupplier = () -> {
        WebDriverManager.iedriver().setup();
        ++ie;
        return new InternetExplorerDriver();
    };

    public static final  Supplier<WebDriver> operaSupplier = () -> {
        WebDriverManager.operadriver().setup();
        ++opera;
        return new OperaDriver();
    };

    private static final Map<String,Supplier<WebDriver>> map = new HashMap<>();

    static
    {
        map.put("chrome", chromeSupplier);
        map.put("firefox", firefoxSupplier);
        map.put("msedge", edgeSupplier);
        map.put("ie", ieSupplier);
        map.put("opera", operaSupplier);
    }

    public static WebDriver getDriver(String browser)
    {
        return map.get(browser).get();
    }

    public static int getChromeCount()
    {
        return chrome;
    }
    public static int getIeCount()
    {
        return ie;
    }

    public static int getFirefoxCount()
    {
        return firefox;
    }
    public static int getOperaCount()
    {
        return opera;
    }

    public static int getMsEdgeCount()
    {
        return msedge;
    }
}
