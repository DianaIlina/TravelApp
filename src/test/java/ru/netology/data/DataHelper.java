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

        public String generateHolder() {
            String firstname = faker.name().firstName();
            String lastname = faker.name().lastName();
            String holderRus = firstname + " " + lastname;

            final String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";

            Transliterator toLatinTrans = Transliterator.getInstance(CYRILLIC_TO_LATIN);
            String holder = toLatinTrans.transliterate(holderRus);
            holder = holder.replaceAll("[^a-zA-Z ]", "").replace("ё", "[yo]");

            return holder;
        }

        public String generateCode() {
            String code = String.format("%03d", faker.number().numberBetween(1, 999));
            return code;
        }

        public int generateYear() {
            int currentYear = LocalDate.now().getYear() % 100;
            int maxYear = currentYear + 5;
            int year = faker.number().numberBetween(currentYear,maxYear);
            return year;
        }

        public int generateMonth() {
            int year = DataHelper.GenerateData.generateYear();
            int currentMonth = LocalDate.now().getMonthValue();
            int month = 0;

            int currentYear = LocalDate.now().getYear() % 100;
            int maxYear = currentYear + 5;


            if (year == currentYear) {
                month = faker.number().numberBetween(currentMonth, 12);
            } else if (year == maxYear) {
                month = faker.number().numberBetween(1, currentMonth);
            } else {
                month = faker.number().numberBetween(1, 12);
            }
            return month;
        }


    }
}