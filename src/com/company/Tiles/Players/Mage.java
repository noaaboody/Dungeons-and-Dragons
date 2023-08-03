package com.company.Tiles.Players;

import com.company.GameBoard;
import com.company.Tiles.Enemy;
import com.company.Tiles.Player;

public class Mage extends Player {

    protected int manaPool;
    protected int currentMana;
    protected int manaCost;
    protected int spellPower;
    protected int hitsCount;
    protected int abilityRange;
    public Mage(String name, int HP, int AP, int DP,int MP , int MC , int SP , int HC ,int AR) {
        super(name, HP, AP, DP);
        this.manaPool = MP;
        this.specialAbility = "Blizzard";
        this.currentMana = MP/4;
        this.manaCost = MC;
        this.spellPower = SP;
        this.abilityRange = AR;
        this.hitsCount = HC;
    }

    public void OnAbilityCast(){

        if(currentMana < manaCost){
            System.out.println("not enough resources");
        }
        else{
//            currentMana = currentMana-manaCost;
//            int hits = 0;
//            //List<Enemy> enemyList = this.GetEnemyInRange(abilityRange);
//            int enemyInRangeSize = enemyList.size();
//            while(hits<hitsCount && enemyInRangeSize > 0){
//                Random rand = new Random();
//                int i = rand.nextInt(enemyInRangeSize);
//                this.attack(enemyList.get(i));
//                hits++;
//            }
        }
    }
    public void UponLevelingUp(){
        super.UponLevelingUp();
        manaPool=manaPool+(25*playerLevel);
        currentMana = Math.min(currentMana+(manaPool/4),manaPool);
        spellPower=spellPower+(10*playerLevel);
    }

    public void Tick(Player player, GameBoard board){
        Move(board);
        OnGameTick();
    }

    public void OnGameTick(){
        currentMana = Math.min(manaPool,currentMana+1*playerLevel);
    }

    @Override
    public void visit(Player p) {

    }

}
