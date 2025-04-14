package com.mdm.project.utils;
import java.security.SecureRandom;

public class UsernameGenerator {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789@#$%&*!";

    public static String generateUsername(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder username = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            username.append(CHARACTERS.charAt(index));
        }

        return username.toString();
    }


}
