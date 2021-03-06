public class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<int[]>();
        if (matrix.length == 0 || matrix[0].length == 0) return result;
        // the pacific boolean table
        boolean[][] pacific = new boolean[matrix.length][matrix[0].length];
        // the atlantic booean table
        boolean[][] atlantic = new boolean[matrix.length][matrix[0].length];
        //initially, all the top and left cells are flooded with pacific water
        //and all the right and bottom cells are flooded with atlantic water
        for (int i = 0; i < matrix.length; i++) {
            pacific[i][0] = true;
            atlantic[i][matrix[0].length - 1] = true;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            pacific[0][i] = true;
            atlantic[matrix.length - 1][i] = true;
        }
        //we go around the matrix and try to flood the matrix from 4 side.
        for (int i = 0; i < matrix.length; i++) {
            boolean[][] pacificVisited = new boolean[matrix.length][matrix[0].length];
            boolean[][] atlanticVisited = new boolean[matrix.length][matrix[0].length];
            water(pacific, pacificVisited, matrix, i, 0);
            water(atlantic, atlanticVisited, matrix, i, matrix[0].length - 1);
        }
        for (int i = 0; i < matrix[0].length; i++) {
            boolean[][] pacificVisited = new boolean[matrix.length][matrix[0].length];
            boolean[][] atlanticVisited = new boolean[matrix.length][matrix[0].length];
            water(pacific, pacificVisited, matrix, 0, i);
            water(atlantic, atlanticVisited, matrix, matrix.length - 1, i);
        }
        //check the shared points among 2 tables
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    int[] element = {i, j};
                    result.add(element);
                }
            }
        }
        return result;
    }

    //the flood function
    private void water(boolean[][] wet, boolean[][] visited, int[][] matrix, int i, int j) {
        wet[i][j] = true;
        visited[i][j] = true;
        int[] x = {0, 0, 1, -1};
        int[] y = {1, -1, 0, 0};
        for (int k = 0; k < 4; k++) {
            if (i + y[k] >= 0 && i + y[k] < matrix.length && j + x[k] >= 0 && j + x[k] < matrix[0].length
                    && !visited[i + y[k]][j + x[k]] && matrix[i + y[k]][j + x[k]] >= matrix[i][j]) {
                water(wet, visited, matrix, i + y[k], j + x[k]);
            }
        }
    }
}