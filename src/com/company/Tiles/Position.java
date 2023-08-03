package com.company.Tiles;

import com.company.Input.Movement;

public class Position implements Comparable {
    private int x;
    private int y;

    public Position (int x,int y){
        this.x = x;
        this.y = y;
    }


    public static Position at(int x, int y) {
        Position p = new Position(x,y);
        return p;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int compareTo(Position position) {
        if(this.y < position.y){
            return -1;
        }
        else if (this.y > position.y){
            return 1;
        }
        else{
            if (this.x < position.x){
                return -1;
            }
            else if(this.y > position.y){
                return 1;
            }
            else return 0;
        }
    }

    public double RangeFrom (Position other){
        return Math.sqrt((this.getX()-other.getX())^2 + (this.getY()-other.getY())^2);
    }

    public Position Translate(Movement movement) {
        int deltaX = 0;
        int deltaY = 0;
        switch (movement) {
            case LEFT:
                deltaX = -1;
                break;
            case UP:
                deltaY = -1;
                break;
            case RIGHT:
                deltaX = 1;
                break;
            case DOWN:
                deltaY = 1;
                break;
        }
        Position position = new Position(this.x + deltaX, this.y + deltaY);
        return position;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
