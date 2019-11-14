package br.com.faasachat.api.model;

import java.security.SecureRandom;

/**
 * Token generation class.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class Token {
    
    /**
     * Self unique instance.
     */
    private static Token instance;

    /**
     * Returns self unique instance.
     * @return
     */
    public static synchronized Token getInstance() {
        if(instance == null) {
            instance = new Token();
        }
        return instance;
    }
    
    /**
     * Generates a token.
     * @return
     */
    public String generate() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes.toString();
    }
    
}
