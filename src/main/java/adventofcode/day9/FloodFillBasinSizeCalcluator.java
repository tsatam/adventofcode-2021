package adventofcode.day9;

import java.util.Arrays;
import java.util.stream.IntStream;

class FloodFillBasinSizeCalcluator {
    private final int[][] heightmap;

    public FloodFillBasinSizeCalcluator(int[][] heightmap) {
        this.heightmap = cloneHeightMap(heightmap);
    }

    public int sizeOfBasinWithLowPoint(Point point) {
        floodFillBasinFromPoint(point);
        return (int) Arrays.stream(heightmap)
            .flatMapToInt(IntStream::of)
            .filter(i -> i == -1)
            .count();
    }

    private void floodFillBasinFromPoint(Point point) {
        if (pointShouldBeInBasin(point)) {
            heightmap[point.y()][point.x()] = -1;
            floodFillBasinFromPoint(point.south());
            floodFillBasinFromPoint(point.north());
            floodFillBasinFromPoint(point.west());
            floodFillBasinFromPoint(point.east());
        }
    }

    private boolean pointShouldBeInBasin(Point point) {
        return pointIsInHeightmap(point) && heightmap[point.y()][point.x()] != -1 && heightmap[point.y()][point.x()] != 9;
    }

    private boolean pointIsInHeightmap(Point point) {
        return point.x() >= 0 && point.y() >= 0 && point.x() < heightmap[0].length && point.y() < heightmap.length;
    }

    private int heightAtPoint(Point point) {
        return heightmap[point.y()][point.x()];
    }

    private static int[][] cloneHeightMap(int[][] heightmap) {
        int[][] newHeightMap = new int[heightmap.length][];
        for (int i = 0; i < heightmap.length; i++) {
            newHeightMap[i] = heightmap[i].clone();
        }
        return newHeightMap;
    }
}
