package com.company.Tiles;
import com.company.*;
import com.company.Callbacks.enemyListCallBack;
import com.company.Input.InputProvider;
import com.company.Callbacks.PlayerDeathCallback;

import java.util.*;
public abstract class Player extends Unit {

    protected static final int REQ_EXP = 50;
    protected static final int ATTACK_BONUS = 4;
    protected static final int DEFENSE_BONUS = 1;
    protected static final int HEALTH_BONUS = 10;
    protected int experience;
    protected int playerLevel;
    protected String specialAbility;
    protected PlayerDeathCallback deathCallback;
    protected InputProvider inputProvider;
    protected List<Enemy> enemiesInRange;
    protected com.company.Callbacks.enemyListCallBack enemyListCallBack;

    public Player(String name, int HP, int AP, int DP) {
        super('@',name, HP, AP, DP);
        experience = 0;
        playerLevel = 1;
        specialAbility = "";
        this.inputProvider = new InputProvider();
    }

    public void setEnemyListCallBack(enemyListCallBack elcb){
        enemyListCallBack = elcb;
    }

    public void setEnemiesInRange(List<Enemy> enemyInRange){
        enemiesInRange = enemyInRange;
    }

    public void initialize (Position position){
        super.initialize(position);
    }
    public void onDeath(){
        this.tileChar='X';
        printer.print("you are the real L");
    }
    public abstract void OnAbilityCast();

    public void UponLevelingUp(){
        //experience = experience - (50*playerLevel);
        playerLevel++;
        int healthGained = gainHealth();
        this.getHealth().set_HA(getHealth().get_HA()+healthGained);
        int attackGained = gainAttack();
        int defenseGained = gainDefense();
        attackPoints += attackPoints;
        defensePoints += defenseGained;
        printer.print(String.format("%s reached level %d and gained: +%d Health, +%d Attack, +%d Defense.", getName(), playerLevel, healthGained, attackGained, defenseGained));
    }

    // when leveling up, returns the new amount of HEALTH
    protected int gainHealth() {
        return playerLevel * HEALTH_BONUS;
    }

    // when leveling up, returns the new amount of ATTACK
    protected int gainAttack() {
        return playerLevel * ATTACK_BONUS;
    }

    // when leveling up, returns the new amount of DEFENSE
    protected int gainDefense() {
        return playerLevel * DEFENSE_BONUS;
    }

    public abstract void Tick(Player player, GameBoard board);

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

    public void visit(Enemy enemy){
        super.battle(enemy);
        if (!enemy.alive()){
            swapPosition(enemy);
            onKill(enemy);
        }
    }

    protected void abilityDamage(Enemy e, int abilityDamage){
        int damageDone = Math.max(abilityDamage - e.defend(),0);
        e.getHealth().decreaseHealthAmount(damageDone);
        printer.print(String.format("%s hit %s for %d ability damage.", getName(),e.getName(),damageDone));
        if (!e.alive()){
            onKill(e);
        }
    }

    protected void onKill (Enemy e){
        int experienceGained = e.getExperienceValue();
        printer.print(String.format("%s died %s gained %d experience", e.getName(),getName(), experienceGained));
        addExperience(experienceGained);
        e.onDeath();
    }

    protected void addExperience (int experienceGained){
        this.experience += experienceGained;
        int nextLevelReq = levelUpRequirement();
        while (experience >= nextLevelReq){
            UponLevelingUp();
            experience -= nextLevelReq;
            nextLevelReq = levelUpRequirement();
        }
    }

    public List<Enemy> GetEnemyInRange(int Range){
        ArrayList<Enemy> enemyInRange = new ArrayList<Enemy>();
        for (Enemy e:enemyListCallBack.call()) {
            if (e.getPosition().RangeFrom(this.getPosition()) < Range){
                enemyInRange.add(e);
            }
        }
        this.setEnemiesInRange(enemyInRange);
        return enemyInRange;
    }

    public void Move(GameBoard board) {
        this.interact(super.Move(inputProvider.getInput(), board));
    }

    protected int levelUpRequirement(){
        return playerLevel*REQ_EXP;
    }

    public void SetInputProvider(InputProvider inputProvider){
        this.inputProvider = inputProvider;
    }

    // return a string that describe the player
    public String describe() {
        return String.format("%s\t\tLevel: %d\t\tExperience: %d/%d", super.describe(), playerLevel, experience, levelUpRequirement());
    }
}
