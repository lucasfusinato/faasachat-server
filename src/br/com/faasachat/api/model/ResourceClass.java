package br.com.faasachat.api.model;

/**
 * Resource class annotattion.
 * Used to marks a class as a resource.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public @interface ResourceClass {

    /**
     * Returns resource value (url).
     * @return
     */
    String value();

}
