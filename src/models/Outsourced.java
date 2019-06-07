package models;

public class Outsourced extends Part{
    private String companyName;

    public Outsourced(){};

    public Outsourced(String companyName){
        this.companyName = companyName;
    }

    /*
    Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){

    }
    */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return this.companyName;
    }
}
