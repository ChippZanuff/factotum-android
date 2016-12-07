package Factotum;

public class Pagination
{
    private int total;
    private int count;
    private int per_page;
    private int current_page;
    private int total_pages;

    public Pagination(int total, int count, int per_page, int current_page, int total_pages)
    {
        this.total = total;
        this.count = count;
        this.per_page = per_page;
        this.current_page = current_page;
        this.total_pages = total_pages;
    }

    public int getTotal()
    {
        return total;
    }

    public int getCount()
    {
        return count;
    }

    public int getPer_page()
    {
        return per_page;
    }

    public int getCurrent_page()
    {
        return current_page;
    }

    public int getTotal_pages()
    {
        return total_pages;
    }
}
