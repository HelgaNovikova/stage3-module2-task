package com.mjc.school;

import com.mjc.school.menu.Menu;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.mjc.school");
        Menu menu = context.getBean(Menu.class);
        DataSource ds = context.getBean(DataSource.class);
        ds.generateNews("news", "authors", "content");
        menu.showMenu();
        Scanner sc = new Scanner(System.in);
        int userChoice = sc.nextInt();
        while (userChoice != 0){
            menu.getMenuItemById(userChoice).run(sc);
            userChoice = sc.nextInt();
        }
    }
}
