package com.company.Tiles;

import com.company.Callbacks.BoardUpdateMessageCallback;
import com.company.GameBoard;
import com.company.Input.Movement;

public abstract class Tile implements Comparable<Tile> {
    protected char tileChar;
    protected Position position;
    private BoardUpdateMessageCallback boardUpdateMessageCallback;

    protected Tile(char tile){
        this.tileChar = tile;
    }

    protected void initialize(Position position){
        this.position = position;
    }

    public char getTile() {
        return tileChar;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public int compareTo(Tile tile) {
        return getPosition().compareTo(tile.getPosition());
    }

    public Tile Move(Movement movement, GameBoard board){
        Position newPosition = this.position.Translate(movement);
        return board.getTile(newPosition);
    }

    @Override
    public String toString() {
        return String.valueOf(tileChar);
    }

    public Tile (char tileChar , Position position){
        this.position = position;
        this.tileChar = tileChar;
    }

    public abstract void accept(Unit unit);
}
