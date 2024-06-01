package dev.arhimedes.products.enums;

public enum ProductCategory {
    PHONE("Phone"), COMPUTER("Computer"), TV("TV"), EARPHONE("Earphone"),
    TABLET("Tablet"), CHARGER("Charger"), MOUSE("Mouse"), KEYPAD("Keypad"),
    BLUETOOTH("Bluetooth"), PENDRIVE("Pendrive"), REMOTE("Remote"),
    SPEAKER("Speaker"), HEADSET("Headset"), LAPTOP("Laptop"), PC("PC");

    private final String name;

    ProductCategory(String name) {
        this.name = name;
    }

    private static String getValue(ProductCategory category){
        return category.name;
    }
}
