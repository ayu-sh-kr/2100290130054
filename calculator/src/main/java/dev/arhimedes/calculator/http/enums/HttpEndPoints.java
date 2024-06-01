package dev.arhimedes.calculator.http.enums;

import java.text.MessageFormat;

public enum HttpEndPoints {
    AUTH("http://20.244.56.144/test/auth"),
    PRIME("http://20.244.56.144/test/primes"),
    EVEN("http://20.244.56.144/test/even"),
    FIBO("http://20.244.56.144/test/fibo"),
    RAND("http://20.244.56.144/test/rand")
    ;

    private final String path;

    HttpEndPoints(String path) {
        this.path = path;
    }

    public static String getUrl(HttpEndPoints path, String... params){
        return MessageFormat.format(path.path, params);
    }
}
