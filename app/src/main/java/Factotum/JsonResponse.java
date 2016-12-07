package Factotum;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JsonResponse<T>
{
    @SerializedName("data")
    private ArrayList<T> data;

    public ArrayList<T> getData()
    {
        return data;
    }

    public void setData(ArrayList<T> data)
    {
        this.data = data;
    }
}
