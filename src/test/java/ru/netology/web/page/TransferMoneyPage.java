package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class TransferMoneyPage {

    private SelenideElement amountTransfer =
            $x("//div[@data-test-id='amount']//input[@class='input__control']");
    private SelenideElement cardNumber =
            $x("//span[@data-test-id='from']//input[@class='input__control']");
    private SelenideElement buttonTransfer = $x("//button[@data-test-id='action-transfer']");
    private SelenideElement error = $x("//div[@data-test-id='error-notification']");

    public DashboardPage transferMoney(DataHelper.TransferMoneyCard transferCard, int amount) {
        amountTransfer.setValue(String.valueOf(amount));
        cardNumber.setValue(transferCard.getCardNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }

    public void errorInsufficientFunds(DataHelper.TransferMoneyCard transferCard, int amount) {
        amountTransfer.setValue(String.valueOf(amount));
        cardNumber.setValue(transferCard.getCardNumber());
        buttonTransfer.click();
        error.shouldHave(Condition.exactText("На карте " + transferCard.getCardNumber() + " недостаточно средств"))
                .shouldBe(Condition.visible);

    }

    public void errorNegativeAmount(DataHelper.TransferMoneyCard transferCard, int amount) {
        amountTransfer.setValue(String.valueOf(amount));
        cardNumber.setValue(transferCard.getCardNumber());
        buttonTransfer.click();
        error.shouldHave(Condition.exactText("Сумма перевода должна быть больше 0"))
                .shouldBe(Condition.visible);

    }

}