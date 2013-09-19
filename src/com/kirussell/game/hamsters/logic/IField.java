package com.kirussell.game.hamsters.logic;

/**
 * Interface for game field
 */
public interface IField<T extends ICell> {
    ICell[][] getCells();
    int getColumnsCount();
    int getRowsCount();
}
