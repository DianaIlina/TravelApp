package ru.netology.dataClasses;

import com.github.javafaker.Faker;
import com.ibm.icu.text.Transliterator;
import lombok.Value;
import lombok.experimental.UtilityClass;

import java.util.Calendar;
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

        public String getWrongNumber() {
            String number = "4444 4444 4444 4443";
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
            return String.format("%03d", faker.number().numberBetween(1, 999));
        }

        public Calendar generateDate(int months, int years) {
            Calendar date = Calendar.getInstance();
            date.add(Calendar.MONTH, months);
            date.add(Calendar.YEAR, years);

            return date;
        }

        public Calendar generateValidDate() {
            return generateDate(faker.number().numberBetween(0, 60), 0);
        }

        public Calendar generateMonthBelow() {
            return generateDate(-1, 0);
        }

        public Calendar generateMonthOver() {
            return generateDate(61, 0);
        }

        public Calendar generateYearBelow() {
            return generateDate(0, faker.number().numberBetween(-5, -1));
        }

        public Calendar generateYearOver() {
            return generateDate(0, faker.number().numberBetween(6, 10));
        }

        public String getMonthFromDate(Calendar date) {
            int month = date.get(Calendar.MONTH) + 1;
            return String.format("%02d", month);
        }

        public String getYearFromDate(Calendar date) {
            int year = date.get(Calendar.YEAR);
            year = year % 100;
            return String.format("%02d", year);
        }
    }
}
