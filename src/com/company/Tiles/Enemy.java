package com.company.Tiles;

import com.company.Callbacks.EnemyDeathCallback;
import com.company.GameBoard;

public abstract class Enemy extends Unit {
    protected int experienceValue;
    private EnemyDeathCallback enemyDeathCallback;

    protected Enemy(char c,String name, int HP, int AP, int DP, int experienceValue) {
        super(c,name, HP, AP, DP);
        this.experienceValue = experienceValue;
    }

    public void setDeathCallback(EnemyDeathCallback edc){
        this.enemyDeathCallback = edc;
    }

    public void OnEnemyTurn(){}

    //enemy's turn
    public abstract void Tick(Player player, GameBoard board);

    @Override
    public void visit(Player p) {
        p.battle(this);
    }

    @Override
    public void visit(Enemy e) {
    }

    @Override
    public void onDeath() {
        //enemyDeathCallback.call(this);
        this.tileChar = '.';
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
    public void setPosition(Position position){
        this.position = position;
    }

    public int getExperienceValue() {
        return this.experienceValue;
    }
}
