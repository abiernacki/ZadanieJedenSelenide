Feature: Zmiana informacji uzytkownika
  Scenario: uzytkownik moze dodac nowy adres

    Given wlaczamy strone sklepu PrestaShop, mamy zarejestrowanego uzytkownika
    When logowanie na stworzonego uzytkownika
    And klikniecie kafelka adresses i Create new address i wypełnienie "ul. Rakowiecka 15", "Warszawa", "02-432", "111222333"
    Then sprawdzenie czy dane w podanym adresie sa poprawne
