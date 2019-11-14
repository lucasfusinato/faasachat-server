package br.com.faasachat.api.model;

/**
 * Annotation to request methods.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public @interface ResourceMethod {

    /**
     * Defines if request method requires token authentication.
     * @return
     */
    boolean authentication() default false;

}
