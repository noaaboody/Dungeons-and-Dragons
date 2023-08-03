package com.company.Tiles.Enemies;

import com.company.GameBoard;
import com.company.Tiles.Enemy;
import com.company.Tiles.Player;

public class Trap extends Enemy {
    protected int visibilityTime;
    protected int invisibilityTime;
    protected int ticksCount;
    protected boolean visible;
    public Trap(char c,String name, int HP, int AP, int DP, int experienceValue ,int VT ,int inVT) {
        super(c,name, HP, AP, DP, experienceValue);
        this.invisibilityTime = inVT;
        this.visibilityTime = VT;
        this.ticksCount = 0;
        this.visible = true;
    }

    public void Tick(Player player, GameBoard board){
        OnGameTick(player);
    }

    public void OnGameTick(Player player){
        visible = ticksCount < visibilityTime;
        if (ticksCount == (visibilityTime + invisibilityTime )){
            ticksCount = 0;
        }
        else ticksCount++;
        if (this.position.RangeFrom(player.getPosition()) < 2)
            this.battle(player);
    }

    public String toString(){
        if(!visible)
            this.tileChar = ' ';
        return String.valueOf(tileChar);
    }
    
}
