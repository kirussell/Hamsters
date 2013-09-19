package com.kirussell.game.hamsters.logic;

import java.util.List;

/**
 * Interface for different algorithms to find paths
 */
public interface IPathFindAlgorithm<T extends ICell> {
    public List<T> getPath(T from,T to);
}
