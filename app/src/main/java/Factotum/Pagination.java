package Factotum;

import com.google.gson.annotations.SerializedName;

public class Pagination
{
    private int total;
    private int count;
    @SerializedName("per_page")
    private int perPage;
    @SerializedName("current_page")
    private int currentPage;
    @SerializedName("total_pages")
    private int totalPages;

    public int getTotal()
    {
        return total;
    }

    public int getCount()
    {
        return count;
    }

    public int getPerPage()
    {
        return perPage;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public int getTotalPages()
    {
        return totalPages;
    }
}
