package models;

public class InHouse extends Part{
    private int machineId;

    InHouse(){};

    InHouse(int machineId){
        this.machineId = machineId;
    }

    /*
    InHouse(int id, String name, double price, int stock, int min, int max, int machineId){

    }
    */

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getMachineId() {
        return machineId;
    }
}
