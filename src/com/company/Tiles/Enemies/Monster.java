package com.company.Tiles.Enemies;

import com.company.GameBoard;
import com.company.Input.Movement;
import com.company.Tiles.Enemy;
import com.company.Tiles.Player;

import java.util.Random;

public class Monster extends Enemy {
    protected int visionRange;
    public Monster(char c,String name, int HP, int AP, int DP, int experienceValue,int VR) {
        super(c,name, HP, AP, DP, experienceValue);
        this.visionRange = VR;
    }

    public void Tick(Player player, GameBoard board){
        OnGameTick(player, board);
    }

    public void OnGameTick(Player player, GameBoard board){
        if(this.position.RangeFrom(player.getPosition())<visionRange){
            int dx = this.position.getX() - player.getPosition().getX();
            int dy = this.position.getY() - player.getPosition().getY();

            if(dx<dy){
                if(dx>0)
                    this.interact(this.Move(Movement.LEFT, board));
                else this.interact(this.Move(Movement.RIGHT, board));
            }
            else {
                if(dy > 0)
                    this.interact(this.Move(Movement.UP, board));
                else this.interact(this.Move(Movement.DOWN, board));
            }
        }
        else {
            this.interact(this.Move(Movement.values()[new Random().nextInt(Movement.values().length)], board));
        }
        }
    }

