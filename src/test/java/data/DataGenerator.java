package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        var cities = new String[]{"Майкоп", "Барнаул", "Благовещенск", "Архангельск", "Астрахань", "Уфа", "Белгород",
                "Брянск", "Владимир", "Волгоград", "Вологда", "Воронеж", "Махачкала", "Донецк", "Биробиджан", "Запорожье",
                "Иваново", "Магас", "Иркутск", "Калининград", "Петрозаводск", "Киров", "Кострома",
                "Краснодар", "Красноярск", "Курган", "Курск", "Саранск", "Москва", "Ставрополь", "Тамбов",
                "Казань", "Тверь", "Томск", "Тула", "Кызыл", "Тюмень"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String locate) {
        var faker = new Faker(new Locale(locate));
        return faker.name().lastName() + " " + faker.name().firstName();
    }
    public static String generatePhone(String locate) {
        var faker = new Faker(new Locale(locate));
        return faker.phoneNumber().phoneNumber();
    }
    public static class Registration {
        private Registration (){
        }
        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }
    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
