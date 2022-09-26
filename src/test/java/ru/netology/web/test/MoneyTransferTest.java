package ru.netology.web.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferMoneyPage;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyToSecondCard() {
        int amount = 1000;
        var dashboard = new DashboardPage();
        int balanceFirstCard = dashboard.getCardBalance("0");
        int balanceSecondCard = dashboard.getCardBalance("1");
        dashboard.chooseCard(1).transferMoney(DataHelper.getFirstCardNumber(), amount);
        int newBalanceFirstCard = dashboard.getCardBalance("0");
        int newBalanceSecondCard = dashboard.getCardBalance("1");
        Assertions.assertTrue(newBalanceFirstCard > 0 && newBalanceSecondCard > 0);
        Assertions.assertEquals(newBalanceFirstCard, (balanceFirstCard - amount));
        Assertions.assertEquals(newBalanceSecondCard, (balanceSecondCard + amount));
    }

    @Test
    void shouldTransferMoneyToFirstCard() {
        int amount = 5000;
        var dashboard = new DashboardPage();
        int balanceFirstCard = dashboard.getCardBalance("0");
        int balanceSecondCard = dashboard.getCardBalance("1");
        dashboard.chooseCard(0).transferMoney(DataHelper.getSecondCardNumber(), amount);
        int newBalanceFirstCard = dashboard.getCardBalance("0");
        int newBalanceSecondCard = dashboard.getCardBalance("1");
        Assertions.assertTrue(newBalanceFirstCard > 0 && newBalanceSecondCard > 0);
        Assertions.assertEquals(newBalanceFirstCard, (balanceFirstCard + amount));
        Assertions.assertEquals(newBalanceSecondCard, (balanceSecondCard - amount));
    }


    @Test
    void shouldErrorInsufficientFunds() {
        var dashboard = new DashboardPage();
        int amount = (dashboard.getCardBalance("1") + 5000);
        TransferMoneyPage transferMoneyPage = dashboard.chooseCard(1);
        transferMoneyPage.errorInsufficientFunds(DataHelper.getFirstCardNumber(), amount);
    }

    @Test
    void shouldNegativeAmount() {
        int amount = -5000;
        var dashboard = new DashboardPage();
        int balanceFirstCard = dashboard.getCardBalance("0");
        int balanceSecondCard = dashboard.getCardBalance("1");
        dashboard.chooseCard(1).transferMoney(DataHelper.getFirstCardNumber(), amount);
        int newBalanceFirstCard = dashboard.getCardBalance("0");
        int newBalanceSecondCard = dashboard.getCardBalance("1");
        Assertions.assertTrue(newBalanceFirstCard > 0 && newBalanceSecondCard > 0);
        Assertions.assertEquals(newBalanceFirstCard, (balanceFirstCard + amount));
        Assertions.assertEquals(newBalanceSecondCard, (balanceSecondCard - amount));
    }

    @Test
    void shouldErrorNullAmount() {
        int amount = 0;
        var dashboard = new DashboardPage();
        TransferMoneyPage transferMoneyPage = dashboard.chooseCard(0);
        transferMoneyPage.errorNegativeAmount(DataHelper.getFirstCardNumber(), amount);
    }

}

