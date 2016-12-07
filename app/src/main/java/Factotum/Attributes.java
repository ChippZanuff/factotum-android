package Factotum;

import java.util.HashMap;
import java.util.Map;

abstract public class Attributes
{
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties()
    {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object object)
    {
        this.additionalProperties.put(name, object);
    }
}
