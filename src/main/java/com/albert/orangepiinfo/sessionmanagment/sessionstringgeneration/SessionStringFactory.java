package com.albert.orangepiinfo.sessionmanagment.sessionstringgeneration;


import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class SessionStringFactory {

    private String alphabet;

    private SecureRandom secureRandom;

    private Integer resultLen = 15;

    private Integer alphabetSize;

    public SessionStringFactory() {
        this.alphabet = Alphabets.BASIC_WITH_BIG_LETTERS.getAlphabet();
        this.secureRandom = new SecureRandom();
        this.alphabetSize = this.alphabet.length();
    }

    public SessionStringFactory(String alphabet) {
        this.alphabet = alphabet;
        this.secureRandom = new SecureRandom();
        this.alphabetSize = this.alphabet.length();
    }

    public SessionStringFactory(Alphabets alphabets) {
        this.alphabet = alphabets.getAlphabet();
        this.secureRandom = new SecureRandom();
        this.alphabetSize = this.alphabet.length();
    }

    public SessionStringFactory(Integer resultLen) {
        this.alphabet = Alphabets.FULL.getAlphabet();
        this.secureRandom = new SecureRandom();
        this.resultLen = resultLen;
        this.alphabetSize = this.alphabet.length();
    }

    public SessionStringFactory(String alphabet, Integer resultLen) {
        this.alphabet = alphabet;
        this.secureRandom = new SecureRandom();
        this.resultLen = resultLen;
        this.alphabetSize = this.alphabet.length();
    }

    public SessionStringFactory(Alphabets alphabets, Integer resultLen) {
        this.alphabet = alphabets.getAlphabet();
        this.resultLen = resultLen;
        this.alphabetSize = this.alphabet.length();
        this.secureRandom = new SecureRandom();
    }

    public String generateSession() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.resultLen; i++) {
            char randomChar = this.alphabet.charAt(secureRandom.nextInt(this.alphabetSize));
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

}
