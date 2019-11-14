package br.com.faasachat.api;

import br.com.faasachat.api.resource.ContactResource;
import br.com.faasachat.api.resource.SessionResource;
import br.com.faasachat.api.resource.UserResource;

/**
 * Enumeration with all resource types.
 * @author Lucas Fusinato Wilhelm Chiodini Zanis
 * @since 11/11/2019
 * @version 1.0
 */
public enum ResourceType {

    /**
     * Contacts resource.
     */
    CONTACT("/contacts", ContactResource.class),
    
    /**
     * Sessions resource.
     */
    SESSION("/sessions", SessionResource.class),
    
    /**
     * Users resource.
     */
    USER("/users", UserResource.class);
    
    /**
     * Resource value (url).
     */
    private String value;
    
    /**
     * Resource class.
     */
    private Class<? extends Object> resourceClass;
    
    /**
     * Instantiates resource type.
     * @param value
     * @param resourceClass
     */
    private ResourceType(String value, Class<? extends Object> resourceClass) {
        this.value = value;
        this.resourceClass = resourceClass;
    }
    
    /**
     * Returns resource value.
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns resource class.
     * @return
     */
    public Class<? extends Object> getResourceClass() {
        return resourceClass;
    }

    /**
     * Returns resource type from value.
     * @param resource
     * @return
     */
    public static ResourceType fromValue(String resource) {
        for(ResourceType type : values()) {
            if(type.getValue().equals(resource)) {
                return type;
            }
        }
        return null;
    }

}
