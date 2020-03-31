package me.ai.domain;

public class MatrixGraph implements IMyGraph {
    double[][] a;
    int n;

    public MatrixGraph(double[][] matrix, int size) {
        a = matrix;
        n = size;
    }

    @Override
    public Double getEdge(int node1, int node2) {
        return a[node1][node2];
    }

    @Override
    public Integer getDegree(int node) {
        int s = 0;
        for (double i : a[node])
            if (i != 0)
                s++;
        return s;
    }

    @Override
    public Integer getN() {
        return n;
    }

    @Override
    public Integer getNoEdges() {
        return null;
    }
}
