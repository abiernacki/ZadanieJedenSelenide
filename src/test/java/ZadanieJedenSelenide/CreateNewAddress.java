package ZadanieJedenSelenide;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class CreateNewAddress {

    @Given("wlaczamy strone sklepu PrestaShop, mamy zarejestrowanego uzytkownika")
    public void wlaczamyStroneSklepuPrestaShopMamyZarejestrowanegoUzytkownika() {

        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1980x1080";
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

    @And("klikniecie kafelka adresses i Create new address i wypełnienie <address>, <city>, <postalCode>, <phone>")
    public void klikniecieKafelkaAdressesICreateNewAddressIWypełnienieAddressCityPostalCodePhone() {
    }

    @Then("sprawdzenie czy dane w podanym adresie sa poprawne")
    public void sprawdzenieCzyDaneWPodanymAdresieSaPoprawne() {
    }
}
