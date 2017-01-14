package Factotum.Type;

import Factotum.Attributes;

public class Type extends Attributes
{
    private String name;
    private String categoryId;
    private String id;

    public Type()
    {

    }

    public String getName()
    {
        return name;
    }

    public Type setName(String name)
    {
        this.name = name;

        return this;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    public String getCategoryIdId()
    {
        return categoryId;
    }

    public void setCategoryIdId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
}
