
package com.company.Tiles.Players;
import com.company.GameBoard;
import com.company.Tiles.Enemy;
import com.company.Tiles.Player;

public class Rogue extends Player {
    protected int costAbility;
    protected int currentEnergy;
    public Rogue(String name, int HP, int AP, int DP ,int CA) {
        super(name, HP, AP, DP);
        this.specialAbility = "Fan Of Knives";
        this.costAbility = CA;
        this.currentEnergy = 100;
    }
    public void OnAbilityCast(){
        if(currentEnergy < costAbility){
            System.out.println("not enough resources");
        }
//        else{
//            currentEnergy = currentEnergy-costAbility;
//            List<Enemy> enemyInRange = this.GetEnemyInRange(2);
//            for (Enemy enemy: enemyInRange) {
//                this.attack(enemy);
//            }
//        }
    }
    public void UponLevelingUp(){
        super.UponLevelingUp();
        currentEnergy = 100;
        attackPoints = attackPoints +(3*playerLevel);
    }

    public void Tick(Player player, GameBoard board){
        Move(board);
        OnGameTick();
    }

    public void OnGameTick(){
        currentEnergy = Math.min(currentEnergy+10,100);
    }

    @Override
    public void visit(Player p) {

    }

}
