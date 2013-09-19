package com.kirussell.game.hamsters.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.kirussell.game.hamsters.logic.Field;
import com.kirussell.game.hamsters.logic.ICell;
import com.kirussell.game.hamsters.logic.IField;

public class GameView extends ViewGroup implements IField<ICell>{

    private Field mField;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public ICell[][] getCells() {
        return mField.getCells();
    }

    @Override
    public int getColumnsCount() {
        return mField.getColumnsCount();
    }

    @Override
    public int getRowsCount() {
        return mField.getRowsCount();
    }

    @Override
    protected void onLayout(boolean b, int i, int i2, int i3, int i4) {

    }
}
