package com.kirussell.game.hamsters.logic;

/**
 * Interface for game field cell
 */
public interface ICell {
    public int getColumn();
    public int getRow();
    public boolean isEmpty();
}
