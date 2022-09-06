package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private SelenideElement loginField = $x("//input[@name='login']");
    private SelenideElement passwordField = $x("//input[@name='password']");
    private SelenideElement loginButton = $x("//span[@class='button__text']");
    private SelenideElement errorMessage =
            $x("//div[@data-test-id='error-notification']/div[@class='notification__content']");


    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void errorMessage() {
        errorMessage.shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }
}