package com.company.Tiles.Players;
import com.company.GameBoard;
import com.company.Tiles.Enemy;
import com.company.Tiles.Player;

import java.util.List;
import java.util.Random;

public class Warrior extends Player {
    protected int cooldown;
    protected int remainingCooldown;

    public Warrior(String name, int HP, int AP, int DP, int CD) {
        super(name, HP, AP, DP);
        this.specialAbility = "Avenger's Shield";
        this.cooldown = CD;
        this.remainingCooldown = 0;
    }
    public void OnAbilityCast(){
        if(remainingCooldown > 0 ){
            System.out.println("not enough resources");
        }
        else{
            remainingCooldown = cooldown;

            this.getHealth().set_HA(Math.min(this.getHealth().get_HA()+(10* defensePoints),this.getHealth().get_HP()));
            List<Enemy> enemyList = this.GetEnemyInRange(3);
            int enemyInRangeSize = enemyList.size();
            Random rand = new Random();
            int i = rand.nextInt(enemyInRangeSize);
           // (enemyList.get(i)).TakeDemage(0.1*healthPool);
        }
    }


    public void UponLevelingUp(){
        super.UponLevelingUp();
        remainingCooldown = 0;
        this.getHealth().set_HP(this.getHealth().get_HP() + (5*playerLevel));
        attackPoints = attackPoints + (2*playerLevel);
        defensePoints = defensePoints + playerLevel;
    }

    public void Tick(Player player, GameBoard board){
        Move(board);
        OnGameTick();
    }

    public void OnGameTick(){
        remainingCooldown = Math.max(remainingCooldown - 1, 0);
    }

    @Override
    public void visit(Player p) {
    }

}
