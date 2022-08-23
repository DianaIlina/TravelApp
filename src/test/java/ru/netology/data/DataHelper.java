package ru.netology.data;

import com.github.javafaker.Faker;
import com.ibm.icu.text.Transliterator;
import lombok.Value;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.Locale;

public class DataHelper {

    @Value
    public static class CardDetails {
        public String number;
        public int month;
        public int year;
        public String holder;
        public String code;
    }

    @UtilityClass
    public static class GenerateData {
        Faker faker = new Faker(new Locale("ru"));

        public String getApprovedNumber() {
            String number = "4444 4444 4444 4441";
            return number;
        }

        public String getDeclinedNumber() {
            String number = "4444 4444 4444 4442";
            return number;
        }

        public String generateHolderRus() {
            String firstname = faker.name().firstName();
            String lastname = faker.name().lastName();
            String holderRus = firstname + " " + lastname;

            return holderRus;
        }

        public String generateHolder() {
            final String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";

            Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
            String holder = toLatinTrans.transliterate(generateHolderRus());
            holder = holder.replaceAll("[^a-zA-Z ]", "").replace("ё", "[yo]");

            return holder;
        }

        public String generateCode() {
            String code = String.format("%03d", faker.number().numberBetween(1, 999));
            return code;
        }

        public int generateCurrentYear() {
            int currentYear = LocalDate.now().getYear() % 100;
            return currentYear;
        }

        public int generateYear() {
            int maxYear = generateCurrentYear() + 5;
            int year = faker.number().numberBetween(generateCurrentYear(),maxYear);
            return year;
        }

        public int generateYearBelow() {
            int yearBelow = generateCurrentYear() - 1;
            return yearBelow;
        }

        public int generateYearMax() {
            int yearMax = generateCurrentYear() + 5;
            return yearMax;
        }

        public int generateYearOver() {
            int yearOver = generateCurrentYear() + 6;
            return yearOver;
        }

        public int generateCurrentMonth() {
            int currentMonth = LocalDate.now().getMonthValue();
            return currentMonth;
        }

        public int generateMonth() {
            int year = DataHelper.GenerateData.generateYear();
            int month = 0;

            if (year == generateCurrentYear()) {
                month = faker.number().numberBetween(generateCurrentMonth(), 12);
            } else if (year == generateYearMax()) {
                month = faker.number().numberBetween(1, generateCurrentMonth());
            } else {
                month = faker.number().numberBetween(1, 12);
            }
            return month;
        }

        public int generateMonthBelowCurrentYear() {
            int monthBelow = generateCurrentMonth() - 1;
            return monthBelow;
        }

        public int generateMonthOverYearMax() {
            int monthOver = generateCurrentMonth() + 1;
            return monthOver;
        }
    }
}