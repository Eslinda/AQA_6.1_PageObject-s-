package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {

    private ElementsCollection cards = $$x("//li[@class='list__item']");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private ElementsCollection button = $$x("//button[@data-test-id='action-deposit']");

    public TransferMoneyPage chooseCard(int id) {
        button.get(id).click();
        return new TransferMoneyPage();
    }

    public int getCardBalance(String id) {
        var text = cards.get(Integer.parseInt(id)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}

