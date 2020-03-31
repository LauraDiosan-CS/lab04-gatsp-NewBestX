package me.ai.domain;

public interface IMyGraph {
    Double getEdge(int node1, int node2);

    Integer getDegree(int node);

    Integer getN();

    Integer getNoEdges();
}
