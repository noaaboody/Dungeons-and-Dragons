package com.company.Tiles;

import com.company.*;
import com.company.Callbacks.MessageCallback;

import java.util.Random;

public abstract class Unit extends Tile {
    protected String name;
    private Health health;
    protected int attackPoints;
    protected int defensePoints;
    protected MessageCallback messageCallback;
    protected Printer printer;

    public Unit (char c,String name,int HP,int AP , int DP){
        super (c);
        this.attackPoints = AP;
        this.defensePoints = DP;
        this.health = new Health(HP,HP);
        this.name = name;
        this.printer = new Printer();
    }
    public Health getHealth(){
        return health;
    }
    public int getAttack(){
        return attackPoints;
    }
    public int getDefense(){
        return defensePoints;
    }
    public String getName (){
        return this.name;
    }

    public void initialize (Position position, MessageCallback messageCallback){
        super.initialize(position);
        this.messageCallback = messageCallback;
    }

    //the turn of the Unit
    public abstract void Tick(Player player, GameBoard board);

    public String description (){
        return ("the health amount is:"+this.health.get_HA() + "the HP is:"+this.health.get_HP());
    }

    public void interact(Tile tile){
        tile.accept(this);
    }

    //visitors
    public void visit(Empty e){
        swapPosition(e);
    }
    public void visit (Wall w){}
    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    public void attack(Unit other){
        this.attack(other, getAttack());
    }

    public void attack(Unit other, int attackPoints){
        Random rand = new Random();
        int int_random = rand.nextInt(attackPoints+1);
        printer.print(String.format("%s roll %d attack points.", getName(), int_random));
        battle(other);
        //return int_random;
    }

    public int defend() {
        Random rand = new Random();
        int int_random = rand.nextInt(this.attackPoints+1);
        return int_random;
    }

    // Combat against another unit.
    protected void battle(Unit u){
        //messageCallback.send(String.format("%s engaged in combat with %s. \n %s\n%s",getName(), u.getName(), describe(), u.describe()));//todo how to use this ?
        printer.print(String.format("%s engaged in combat with %s. \n %s\n%s",getName(), u.getName(), describe(), u.describe()));
        int DamageDone = Math.max(attackPoints-u.defend(),0);
        u.health.decreaseHealthAmount(DamageDone);
        //messageCallback.send(String.format("%s dealt %d damage to %s.", getName(),DamageDone,u.getName()));//todo how to use this?
        printer.print(String.format("%s dealt %d damage to %s.", getName(),DamageDone,u.getName()));
        printer.print(this.describe());
        printer.print(u.describe());
    }

    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth().get_HA(), getAttack(), getDefense());
    }
    public abstract void onDeath ();

    public void setMessageCallback(MessageCallback messageCallback){
        this.messageCallback = messageCallback;
    }

    public boolean alive(){
        return getHealth().get_HA() > 0;
    }

    protected void swapPosition(Tile tile){
        Position p = tile.getPosition();
        tile.setPosition(this.getPosition());
        this.setPosition(p);
    }

}
