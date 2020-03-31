package me.ai.logic;

import me.ai.domain.IMyGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GA {
    private List<Chromosome> population;
    private IMyGraph g;
    private Chromosome bestChromosome;

    public GA(IMyGraph graph, int popSize) {
        g = graph;
        initPop(popSize);
    }

    private void initPop(int popSize) {
        population = new ArrayList<>();

        for (int i = 0; i < popSize; i++)
            population.add(new Chromosome(g.getN()));
    }

    private void evaluation() {
        double shortestRoute = 100000000;
        for (Chromosome c : population) {
            double f = length(c.getRoute());
            c.setFitness(f);

            if (f < shortestRoute) {
                bestChromosome = c;
                shortestRoute = f;
            }
        }
    }

    private double length(List<Integer> route) {
        double sum = 0;

        Iterator<Integer> it = route.iterator();
        int last = it.next();

        while (it.hasNext()) {
            int next = it.next();
            sum += g.getEdge(last, next);
            last = next;
        }

        return sum + g.getEdge(last, route.get(0));
    }

    private int selection() {
        int pos1 = Utils.getRand().nextInt(population.size());
        int pos2 = Utils.getRand().nextInt(population.size());

        if (population.get(pos1).getFitness() < population.get(pos2).getFitness())
            return pos1;
        return pos2;
    }

    public void oneGeneration() {
        List<Chromosome> newPop = new ArrayList<>();

        for (int i = 0; i < population.size(); i++) {
            Chromosome c1 = population.get(selection());
            Chromosome c2 = population.get(selection());

            Chromosome off = c1.crossover(c2);
            off.mutation();
            newPop.add(off);
        }

        population = newPop;
        evaluation();
    }

    public void oneGenerationElitism() {
        List<Chromosome> newPop = new ArrayList<>();
        if (bestChromosome != null) {
            newPop.add(bestChromosome);
        } else
            newPop.add(population.get(selection()));

        for (int i = 0; i < population.size() - 1; i++) {
            Chromosome c1 = population.get(selection());
            Chromosome c2 = population.get(selection());

            Chromosome off = c1.crossover(c2);
            off.mutation();
            newPop.add(off);
        }

        population = newPop;
        evaluation();
    }

    public double getBestRouteLength() {
        return bestChromosome.getFitness();
    }

    public List<Integer> getBestRoute() {
        return bestChromosome.getRoute();
    }
}
