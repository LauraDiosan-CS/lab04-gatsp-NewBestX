package me.ai;

import me.ai.repository.Repository;
import me.ai.service.Service;
import me.ai.ui.Console;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Repository repo;
        try {
            repo = new Repository("hard.txt");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        Service s = new Service(repo);
        Console c = new Console(s);

        c.run();
    }
}
