package dev.arhimedes.products.enums;

import java.text.MessageFormat;

public enum ProductEndpoints {
    GET("");

    public final String path;

    ProductEndpoints(String path) {
        this.path = path;
    }

    public static String getPath(ProductEndpoints path, String  ...params){
        return MessageFormat.format(path.path, params);
    }
}
