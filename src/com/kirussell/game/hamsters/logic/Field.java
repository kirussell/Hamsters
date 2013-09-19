package com.kirussell.game.hamsters.logic;

import com.kirussell.game.hamsters.utils.Debug;

import java.lang.reflect.Array;

public class Field<T extends ICell> implements IField<T>{

    private int mColumnsCount;
    private int mRowsCount;
    private T[][] mCells;
    private Class<T> mCellClass;

    public Field(int column, int row, Class<T> cellClass) {
        mColumnsCount = column;
        mRowsCount = row;
        mCellClass = cellClass;
        mCells = (T[][]) Array.newInstance(cellClass, new int[]{column, row});
        populate(mCellClass);
    }

    private void populate(Class<T> cellClass) {
        for (int i=0;i< mColumnsCount;i++) {
            for (int j=0;j< mRowsCount;j++) {
                try {
                    mCells[i][j] = (T)cellClass.newInstance();
                } catch (IllegalAccessException e) {
                    Debug.error(e);
                } catch (InstantiationException e) {
                    Debug.error(e);
                }
            }
        }
    }

    public void put(T cell) {
        mCells[cell.getColumn()][cell.getRow()] = cell;
    }

    public T[][] getCells() {
        return mCells;
    }

    @Override
    public int getColumnsCount() {
        return mColumnsCount;
    }
    @Override
    public int getRowsCount() {
        return mRowsCount;
    }
}
