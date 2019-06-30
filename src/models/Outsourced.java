package models;

public class Outsourced extends Part{

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private String companyName;

    public Outsourced(){};

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        setCompanyName(companyName);
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return this.companyName;
    }
}