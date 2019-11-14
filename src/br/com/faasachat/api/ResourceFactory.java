package br.com.faasachat.api;

import java.lang.reflect.InvocationTargetException;

import br.com.faasachat.api.model.ResourceClass;

/**
 * Factory to create services.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public class ResourceFactory {
    
    /**
     * Unique class instance.
     */
    private static ResourceFactory instance;
    
    /**
     * Returns unique class instance.
     * @return
     */
    public static synchronized ResourceFactory getInstance() {
        if(instance == null) {
            instance = new ResourceFactory();
        }
        return instance;
    }
    
    /**
     * Creates a service from request data.
     * @param request
     * @param session
     * @return
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public Object create(String resourceName) throws Exception {
        ResourceType resource = ResourceType.fromValue(resourceName);
        Class<? extends Object> resourceClass = resource.getResourceClass();
        //if(!resourceClass.getClass().isAnnotationPresent(ResourceClass.class)) {
        //throw new Exception("Resource class not found.");
        //}
        return resourceClass.getConstructor().newInstance();
    }

}
