package me.ai.service;

import me.ai.logic.GA;
import me.ai.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public List<String> generate(int popSize, int nGenerations) {
        GA ga = new GA(repo.getGraph(), popSize);

        List<String> output = new ArrayList<>();

        for (int i = 1; i <= nGenerations; i++) {
            ga.oneGenerationElitism();

            String s = "GEN " + i + " -- Best length: " +
                    ga.getBestRouteLength() + " ; Route: " +
                    ga.getBestRoute();
            output.add(s);
        }

        return output;
    }
}
