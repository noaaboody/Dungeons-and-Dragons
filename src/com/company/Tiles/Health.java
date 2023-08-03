package com.company.Tiles;

public class Health {
    private int healthPool;
    private int healthAmount;

    public Health (int healthPool , int healthAmount){
        this.healthAmount = healthAmount;
        this.healthPool = healthPool;
    }

    public int get_HP(){
        return healthPool;
    }
    public int get_HA(){
        return healthAmount;
    }
    public void set_HP(int HP){
         healthPool=HP;
    }
    public void set_HA(int HA){
        healthAmount=HA;
    }

    public String toString(){
        return String.format("Health Amount: %s Health pool %s",this.healthAmount , this.healthPool);
    }

    public void decreaseHealthAmount(int dec){
        this.healthAmount -= dec;
    }
}
