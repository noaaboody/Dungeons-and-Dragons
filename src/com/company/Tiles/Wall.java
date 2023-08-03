package com.company.Tiles;

public class Wall extends Tile {
    public Wall(Position position) {
        super('#', position);
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }

}
