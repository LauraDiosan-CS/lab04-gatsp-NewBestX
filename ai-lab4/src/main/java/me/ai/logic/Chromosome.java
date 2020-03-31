package me.ai.logic;

import java.util.*;

public class Chromosome {
    private List<Integer> route;
    private double fitness;

    public Chromosome(int n) {
        initRoute(n);
        this.fitness = 0D;
    }

    public Chromosome(List<Integer> route) {
        this.route = route;
        this.fitness = 0D;
    }

    private void initRoute(int n) {
        route = new LinkedList<>();

        for (int i = 0; i < n; i++)
            route.add(i);

        Collections.shuffle(route);
    }

    public void mutation() {
        int pos1 = Utils.getRand().nextInt(route.size());
        int pos2 = Utils.getRand().nextInt(route.size());

        if(pos1 == pos2)
            return;

        if (pos2 < pos1) {
            int aux = pos1;
            pos1 = pos2;
            pos2 = aux;
        }

        int el = route.remove(pos2);
        route.add(pos1 + 1, el);
    }

    public Chromosome crossover(Chromosome other) {
        List<Integer> newRoute = new LinkedList<>();

        int pos1 = Utils.getRand().nextInt(route.size());
        int pos2 = Utils.getRand().nextInt(route.size());

        if (pos2 < pos1) {
            int aux = pos1;
            pos1 = pos2;
            pos2 = aux;
        }

        Iterator<Integer> it = route.listIterator(pos1);
        for (int i = pos1; i < pos2; i++)
            newRoute.add(it.next());

        int k = 0;
        it = other.route.listIterator(pos2);
        while (newRoute.size() < route.size()) {
            if (!it.hasNext())
                it = other.route.listIterator();

            int el = it.next();
            if (!newRoute.contains(el)) {
                if (newRoute.size() < route.size() - pos1)
                    newRoute.add(el);
                else
                    newRoute.add(k++, el);
            }
        }

        return new Chromosome(newRoute);
    }

    public List<Integer> getRoute() {
        return route;
    }

    public void setRoute(List<Integer> route) {
        this.route = route;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chromosome that = (Chromosome) o;
        return Double.compare(that.fitness, fitness) == 0 &&
                Objects.equals(route, that.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(route, fitness);
    }

    @Override
    public String toString() {
        return "Chromosome{" +
                "route=" + route +
                ", fitness=" + fitness +
                '}';
    }
}
