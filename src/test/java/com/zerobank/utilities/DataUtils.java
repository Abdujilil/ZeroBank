package com.zerobank.utilities;

import com.github.javafaker.Faker;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class DataUtils {

    private static Faker faker = new Faker();

    public static String generatesRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String generatesRandomFullName() {
        return faker.name().fullName();
    }

    public static int generateSingleNum(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

    public static String generateFirstName() {
        return faker.name().firstName();
    }

    public static String generateLastName() {
        return faker.name().lastName();
    }

    public static String generatePhoneNumber() {
        return faker.phoneNumber().cellPhone();
    }

    public static int generateSingleNum(int min, int max) {
        Random random = new Random();
        max++;
        return random.nextInt(max - min) + min;
    }

    public static String formatDate(int date, int month, int year, String expectedFormat) {
        return LocalDate.of(year, month, date).format(DateTimeFormatter.ofPattern(expectedFormat));
    }

    public static String generateRandomFutureDate(String expectedFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(expectedFormat);
        Date date = faker.date().future(1000, TimeUnit.DAYS);
        ;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(df);
    }
}
