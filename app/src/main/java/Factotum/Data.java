package Factotum;

import com.google.gson.annotations.SerializedName;

public class Data<T>
{
    private String type;
    private String id;
    @SerializedName("attributes")
    private T attributes;

    public Data(String type, String id)
    {
        this.type = type;
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public T getAttributes()
    {
        return attributes;
    }

    public void setAttributes(T attributes)
    {
        this.attributes = attributes;
    }
}
