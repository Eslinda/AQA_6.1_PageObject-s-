package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static TransferMoneyCard getFirstCardNumber() {
        return new TransferMoneyCard("5559 0000 0000 0001", "0");

    }

    public static TransferMoneyCard getSecondCardNumber() {
        return new TransferMoneyCard("5559 0000 0000 0002", "1");

    }

    @Value
    public static class TransferMoneyCard {
        private String cardNumber;
        private String id;
    }
}
