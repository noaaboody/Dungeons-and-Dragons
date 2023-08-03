package com.company.Tiles.Players;

import com.company.GameBoard;
import com.company.Tiles.Enemy;
import com.company.Tiles.Player;

import java.util.List;
import java.util.Random;

public class Hunter extends Player {
    private int range;
    private int arrows;
    private int tickCount;
    private List<Enemy> enemyList ;
    public Hunter(String name, int HP, int AP, int DP,int range) {
        super(name, HP, AP, DP);
        this.range = range;
        this.arrows = this.playerLevel*10;
        this.enemyList = this.GetEnemyInRange(range);
        this.tickCount =0;
    }

    @Override
    public void OnAbilityCast() {
        if(arrows == 0 || this.enemyList.isEmpty()){
            System.out.println("not enough resources");
        }
        else{
            arrows --;
            //todo deal demage?
        }
    }

    public void UponLevelingUp(){
      super.UponLevelingUp();
      arrows += 10*this.playerLevel;
      this.attackPoints +=  2*this.playerLevel;
      this.defensePoints += this.playerLevel;
    }

    @Override
    public void Tick(Player player, GameBoard board) {
        if (this.tickCount == 10 ) {
            arrows += this.playerLevel;
            tickCount = 0;
        }
        else tickCount++;
    }

    @Override
    public void visit(Player p) {


    }

}
