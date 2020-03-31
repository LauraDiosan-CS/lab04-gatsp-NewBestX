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

// GEN 200000 -- Best length: 452.6526931271774 ; Route: [41, 43, 36, 16, 3, 17, 46, 11, 50, 45, 10, 37, 48, 8, 49, 20, 28, 19, 34, 35, 2, 27, 30, 25, 7, 21,
// 1, 15, 33, 29, 9, 38, 32, 44, 14, 4, 31, 0, 26, 5, 47, 22, 6, 42, 23, 13, 24, 12, 40, 39, 18]

//GEN 100000 -- Best length: 445.61253710724793 ; Route: [20, 28, 1, 21, 0, 26, 31, 10, 45, 50, 11, 46, 17, 3, 16, 36, 43, 41, 18, 39, 40, 12, 24, 13, 23,
// 42, 6, 22, 5, 47, 7, 25, 30, 27, 2, 35, 34, 19, 15, 49, 8, 48, 37, 4, 14, 44, 32, 38, 9, 29, 33]

//GEN 100000 -- Best length: 437.53711826478747 ; Route: [7, 25, 6, 22, 42, 23, 13, 24, 12, 40, 39, 18, 41, 43, 14, 44, 32, 38, 9, 48, 8, 15, 49, 29, 33, 20,
// 28, 1, 19, 34, 35, 2, 27, 30, 21, 0, 31, 10, 37, 4, 36, 16, 3, 17, 46, 11, 45, 50, 5, 26, 47]