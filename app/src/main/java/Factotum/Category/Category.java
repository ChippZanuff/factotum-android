package Factotum.Category;

import Factotum.Attributes;

public class Category extends Attributes
{
    private String name;

    public String getName()
    {
        return name;
    }

    public Category setName(String name)
    {
        this.name = name;

        return this;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
