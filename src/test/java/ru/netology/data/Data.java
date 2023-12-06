package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class Data {
    private static Faker faker = new Faker(new Locale("en"));

    private Data() {
    }

    public static AuthInfo getAuthInfoWithTestData() { return new AuthInfo ("vasya", "qwerty123");}

    private static String generateRandomLogin() { return faker.name().username();}

    private static String generateRandomPassword() {return faker.internet().password();}

    public static AuthInfo generateRandomUser() {
        return new AuthInfo(generateRandomLogin(), generateRandomPassword());
    }

    public static VerificationCode generateRandomVerificationCode() {
        return new VerificationCode(faker.numerify("000000"));
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }


}
