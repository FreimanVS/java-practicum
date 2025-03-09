package com.freimanvs.javapracticum.utils;

public class GeneratorUtil {
    private static long id = 1;
    public long generateId() {
        return id++;
    }
}
