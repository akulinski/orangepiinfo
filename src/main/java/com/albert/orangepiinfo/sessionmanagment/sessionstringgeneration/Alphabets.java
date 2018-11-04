package com.albert.orangepiinfo.sessionmanagment.sessionstringgeneration;

import lombok.Getter;

public enum Alphabets {

    BASIC("abcdefghijklmnopqrstuvwxyz"),
    BASIC_WITH_BIG_LETTERS("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxy"),
    BASIC_WITH_SPECIAL_CHARS("abcdefghijklmnopqrstuvwxyz!@#$%^&*()_+{}|:\"<>/\\"),
    FULL("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxy!@#$%^&*()_+{}|:\"<>/\\");

    @Getter
    private String alphabet;

    Alphabets(String alphabet){
        this.alphabet = alphabet;
    }

}
