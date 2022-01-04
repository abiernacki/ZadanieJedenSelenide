package ZadanieJedenSelenide;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateNewAddress {

    private String generatedAlias;

    @Given("wlaczamy strone sklepu PrestaShop, mamy zarejestrowanego uzytkownika")
    public void wlaczamyStroneSklepuPrestaShopMamyZarejestrowanegoUzytkownika() {

//        Configuration.holdBrowserOpen = true;
//        Configuration.browserSize = "1980x1080";
        Configuration.timeout = 5000;
        open("https://prod-kurs.coderslab.pl/");
    }

    @When("logowanie na stworzonego uzytkownika")
    public void logowanieNaStworzonegoUzytkownika() {

        $(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a")).click();

        $(By.name("email")).clear();
        $(By.name("email")).sendKeys("zenon@domena.pl");

        $(By.name("password")).clear();
        $(By.name("password")).sendKeys("myszka777");

        $(By.id("submit-login")).click();

    }

    @And("klikniecie kafelka adresses i Create new address i wypełnienie {string}, {string}, {string}, {string}")
    public void klikniecieKafelkaAdressesICreateNewAddressIWypełnienie(String address, String city, String postalCode, String phone) {


        if ($(By.id("addresses-link")).isDisplayed())
        {
            $(By.id("addresses-link")).click();
            $(By.cssSelector("#content > div.addresses-footer > a > span")).click();
        } else {
            $(By.id("address-link")).click();
        }

        Random random = new Random();
        generatedAlias = String.valueOf(random.nextInt(1000000));

        $(By.name("alias")).clear();
        $(By.name("alias")).sendKeys(generatedAlias);

        $(By.name("address1")).clear();
        $(By.name("address1")).sendKeys(address);

        $(By.name("city")).clear();
        $(By.name("city")).sendKeys(city);

        $(By.name("postcode")).clear();
        $(By.name("postcode")).sendKeys(postalCode);

        $(By.xpath("//select/option[@value ='17']")).click();

        $(By.name("phone")).clear();
        $(By.name("phone")).sendKeys(phone);

        $(By.xpath("/html/body/main/section/div/div/section/section/div/div/form/footer/button")).click();

    }

    @Then("sprawdzenie czy dane w podanym adresie sa poprawne")
    public void sprawdzenieCzyDaneWPodanymAdresieSaPoprawne() {

        List<WebElement> addressElements = new ArrayList<>($$(By.tagName("article")));

        String addressText = addressElements.get(addressElements.size()-1).getText();
        Assert.assertTrue(addressText.contains("ul. Rakowiecka 15"));
        Assert.assertTrue(addressText.contains("Warszawa"));
        Assert.assertTrue(addressText.contains("02-432"));
        Assert.assertTrue(addressText.contains("111222333"));

        // Usuwanie ostatnio dodanego adresu
        List<WebElement> deleteAddresses = new ArrayList<>($$(By.cssSelector("a[data-link-action='delete-address']")));
        deleteAddresses.get(deleteAddresses.size()-1).click();

        // Sprawdzenie czy adres zostal usuniety
        List<WebElement> hawMany = new ArrayList<>($$(By.tagName("article")));
        addressText = hawMany.get(hawMany.size()-1).getText();
        Assert.assertFalse(addressText.contains(generatedAlias));

    }
    }

