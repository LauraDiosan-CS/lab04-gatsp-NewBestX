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

    class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    private void readMatrix() throws IOException {
        List<String> lines = readFile();

        if (lines == null)
            throw new IOException("Eroare la citirea fisierului");

        if (lines.get(0).contains("NAME")) {
            Iterator<String> it = lines.iterator();

            String s = it.next();
            while (!s.startsWith("DIMENSION"))
                s = it.next();

            int n = Integer.parseInt(s.split(" ")[2]);
            double[][] matrix = new double[n][n];
            Point[] points = new Point[n];

            s = it.next();
            while (!s.equals("NODE_COORD_SECTION"))
                s = it.next();

            s = it.next();
            while (!s.equals("EOF")) {
                String[] p = s.split(" ");
                int i = Integer.parseInt(p[0]) - 1;
                points[i] = new Point(Integer.parseInt(p[1]), Integer.parseInt(p[2]));
                s = it.next();
            }

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    double d = distance(points[i], points[j]);
                    matrix[i][j] = d;
                    matrix[j][i] = d;
                }
            }

//            for(int i = 0; i<n; i++) {
//                StringBuilder sb = new StringBuilder();
//                for(int j = 0; j<n; j++)
//                    sb.append(matrix[i][j]).append(" ");
//                System.out.println(sb.toString());
//            }

            graph = new MatrixGraph(matrix, n);
            return;
        }

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
