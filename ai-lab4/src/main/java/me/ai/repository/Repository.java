package me.ai.repository;

import me.ai.domain.IMyGraph;
import me.ai.domain.MatrixGraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    private String fileName;
    private MatrixGraph graph;

    public Repository(String fileName) throws IOException {
        this.fileName = fileName;
        readMatrix();
    }

    public IMyGraph getGraph() {
        return graph;
    }

    private void readMatrix() throws IOException {
        List<String> lines = readFile();

        if (lines == null)
            throw new IOException("Eroare la citirea fisierului");

        double[][] matrix;
        int n;

        Iterator<String> it = lines.iterator();
        n = Integer.parseInt(it.next());
        matrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            String[] parts = it.next().split(",");
            for (int j = 0; j < n; j++)
                matrix[i][j] = Double.parseDouble(parts[j]);
        }

        graph = new MatrixGraph(matrix, n);
    }

    private List<String> readFile() {
        Path p = Paths.get("./src/data/" + fileName);
        if (!Files.exists(p))
            return null;

        List<String> lines = null;

        try {
            lines = Files.lines(p).collect(Collectors.toList());
        } catch (IOException ignored) {
        }

        return lines;
    }
}
