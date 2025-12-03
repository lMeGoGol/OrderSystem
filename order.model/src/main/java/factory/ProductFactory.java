package factory;

import com.github.javafaker.Faker;
import products.Clothing;
import products.Electronics;

public class ProductFactory {
    private static final Faker faker = new Faker();

    public static Electronics createElectronics() {
        return Electronics.builder()
                .id(faker.idNumber().valid())
                .name(faker.commerce().productName())
                .price(Double.parseDouble(faker.commerce().price()))
                .appliance(faker.options().option("TV", "Laptop", "Phone"))
                .model(faker.commerce().promotionCode())
                .build();
    }

    public static Clothing createClothing() {
        return Clothing.builder()
                .id(faker.idNumber().valid())
                .name(faker.commerce().productName())
                .price(Double.parseDouble(faker.commerce().price()))
                .size(faker.options().option("S", "M", "L", "XL"))
                .material(faker.commerce().material())
                .build();
    }
}
