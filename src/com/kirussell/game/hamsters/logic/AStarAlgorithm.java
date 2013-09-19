package com.kirussell.game.hamsters.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Finds paths with A* algorithm on field
 */
public class AStarAlgorithm<T extends AStarAlgorithm.AStarCell> implements IPathFindAlgorithm<T> {

    private static final int SHIFT_COST = 1;
    private int mRowsCount;
    private int mColumnsCount;
    private T[][] mCells;

    public AStarAlgorithm(IField<T> field) {
        mCells = (T[][]) field.getCells();
        mRowsCount = field.getRowsCount();
        mColumnsCount = field.getColumnsCount();
    }

    @Override
    public List<T> getPath(T from, T to) {
        resetHeuristics();
        List<T> closed = new ArrayList<T>();
        PriorityQueue<T> opened = new PriorityQueue<T>();
        opened.offer(from);
        while (!opened.isEmpty() && !opened.contains(to)) {
            T currentCell = opened.poll();
            closed.add(currentCell);
            for (T neighbour : getNeighbours(currentCell)) {
                if (neighbour != to && neighbour != null && !closed.contains(neighbour)) {
                    if (!opened.contains(neighbour)) {
                        neighbour.setPreviousCell(currentCell);
                        neighbour.setG(countG(neighbour));
                        neighbour.setH(countH(neighbour, to));
                        opened.offer(neighbour);
                    } else {
                        final int neighboursG = currentCell.getG() + SHIFT_COST;
                        if (neighboursG < neighbour.getG()) {
                            opened.remove(neighbour);
                            neighbour.setG(neighboursG);
                            neighbour.setPreviousCell(currentCell);
                            opened.offer(neighbour);
                        }
                    }
                }
            }
        }
        if (opened.isEmpty()) return null;
        return getResultPath(from, to);
    }

    private List<T> getResultPath(T start, T end) {
        List<T> result = new ArrayList<T>();
        AStarCell currentCell = end;
        while (currentCell != start) {
            result.add((T) currentCell);
            currentCell = currentCell.getPreviousCell();
        }
        result.add(start);
        Collections.reverse(result);
        return result;
    }

    private List<T> getNeighbours(T currentCell) {
        List<T> neighbours = new ArrayList<T>(4);
        neighbours.add(getNeighbour(currentCell.getColumn(), currentCell.getRow(), 0, -1));
        neighbours.add(getNeighbour(currentCell.getColumn(), currentCell.getRow(), 0, 1));
        neighbours.add(getNeighbour(currentCell.getColumn(), currentCell.getRow(), -1, 0));
        neighbours.add(getNeighbour(currentCell.getColumn(), currentCell.getRow(), 1, 0));
        return neighbours;
    }

    private T getNeighbour(int column, int row, int shiftColumn, int shiftRow) {
        int neighbourColumn = column + shiftColumn;
        int neighbourRow = row + shiftRow;

        if (neighbourColumn >= 0 && neighbourColumn < mColumnsCount) {
            if (neighbourRow >= 0 && neighbourRow < mRowsCount)
                return mCells[neighbourColumn][neighbourRow];
        }
        return null;
    }

    private int countG(T to) {
        return to.getPreviousCell().getG() + SHIFT_COST;
    }

    private int countH(AStarCell from,AStarCell to){
        return SHIFT_COST * (Math.abs(from.getRow() - to.getRow()) + Math.abs(from.getColumn() - to.getColumn()));
    }

    private void resetHeuristics() {
        for (int i = 0; i < mColumnsCount; i++) {
            for (int j = 0; j < mRowsCount; j++) {
                mCells[i][j].resetHeuristics();
            }
        }
    }

    public static abstract class AStarCell implements ICell {

        private int mColumn;
        private int mRow;
        private int mG;
        private int mH;
        private AStarCell mPreviousCell;

        public AStarCell(int column, int row) {
            mColumn = column;
            mRow = row;
        }

        @Override
        public int getColumn() {
            return mColumn;
        }

        @Override
        public int getRow() {
            return mRow;
        }

        @Override
        public abstract boolean isEmpty();

        protected void resetHeuristics() {
            mG = 0;
            mH = 0;
            mPreviousCell = null;
        }

        protected int getF() {
            return mG + mH;
        }

        protected void setG(int g) {
            mG = g;
        }

        protected int getG() {
            return mG;
        }

        protected void setH(int h) {
            mH = h;
        }

        protected AStarCell getPreviousCell() {
            return mPreviousCell;
        }

        protected void setPreviousCell(AStarCell previous) {
            mPreviousCell = previous;
        }
    }
}
