package com.mjc.school.menu;

import com.mjc.school.command.CommandInvoker;
import com.mjc.school.service.dto.AuthorCreateDto;
import com.mjc.school.service.dto.NewsCreateDto;
import com.mjc.school.service.exception.CustomException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

@Component
public class Menu implements InitializingBean {
    private final Map<Integer, MenuItem> menu = new LinkedHashMap<>();

    private final CommandInvoker commandInvoker;

    public Menu(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
    }

    public MenuItem getMenuItemById(int id) {
        return menu.get(id);
    }

    void addMenuItem(int index, String title, Consumer<Scanner> run) {
        menu.put(index, new MenuItem(index, title) {
            @Override
            public void run(Scanner sc) {
                run.accept(sc);
            }
        });
    }

    @PostConstruct
    private void createMenu() {
        addMenuItem(1, "Get all news.", scanner -> {
            System.out.println(commandInvoker.readAllNews());
            showMenu();
        });

        addMenuItem(2, "Get news by id.", scanner -> {
            System.out.println("Operation: Get news by id.\n" + "Enter news id:");
            Long newsId = scanner.nextLong();
            try {
                System.out.println(commandInvoker.readNewsById(newsId));
            } catch (CustomException e) {
                System.out.println("ERROR_CODE: " + e.getCode() + " ERROR_MESSAGE: " + e.getMessage());
            }
            showMenu();
        });

        addMenuItem(3, "Create news.", scanner -> {
            System.out.println("Operation: Create news.");
            System.out.println("Enter news title:");
            scanner.nextLine();
            String title = scanner.nextLine();
            System.out.println("Enter news content:");
            String content = scanner.nextLine();
            System.out.println("Enter author id:");
            Long authorId = scanner.nextLong();
            try {
                System.out.println(commandInvoker.createNews(new NewsCreateDto(title, content, authorId)));
            } catch (CustomException e) {
                System.out.println("ERROR_CODE: " + e.getCode() + " ERROR_MESSAGE: " + e.getMessage());
            }
            showMenu();
        });

        addMenuItem(4, "Update news.", scanner -> {
            System.out.println("Operation: Update news.");
            System.out.println("Enter news id:");
            Long newsId = scanner.nextLong();
            System.out.println("Enter news title:");
            scanner.nextLine();
            String title = scanner.nextLine();
            System.out.println("Enter news content:");
            String content = scanner.nextLine();
            System.out.println("Enter author id:");
            Long authorId = scanner.nextLong();
            try {
                NewsCreateDto dto = new NewsCreateDto(newsId, title, content, authorId);
                System.out.println(commandInvoker.updateNews(dto));
            } catch (CustomException e) {
                System.out.println("ERROR_CODE: " + e.getCode() + " ERROR_MESSAGE: " + e.getMessage());
            }
            showMenu();
        });

        addMenuItem(5, "Remove news by id.", scanner -> {
            System.out.println("Operation: Remove news by id.\n" + "Enter news id:");
            Long newsId = scanner.nextLong();
            try {
                System.out.println(commandInvoker.deleteNewsById(newsId));
            } catch (CustomException e) {
                System.out.println("ERROR_CODE: " + e.getCode() + " ERROR_MESSAGE: " + e.getMessage());
            }
            showMenu();
        });

        addMenuItem(6, "Get all authors.", scanner -> {
            System.out.println(commandInvoker.readAllAuthors());
            showMenu();
        });

        addMenuItem(7, "Get author by id.", scanner -> {
            System.out.println("Operation: Get author by id.\n" + "Enter author id:");
            Long authorId = scanner.nextLong();
            try {
                System.out.println(commandInvoker.readAuthorsById(authorId));
            } catch (CustomException e) {
                System.out.println("ERROR_CODE: " + e.getCode() + " ERROR_MESSAGE: " + e.getMessage());
            }
            showMenu();
        });

        addMenuItem(8, "Create author.", scanner -> {
            System.out.println("Operation: Create author.");
            System.out.println("Enter author's name:");
            scanner.nextLine();
            String name = scanner.nextLine();
            try {
                System.out.println(commandInvoker.createAuthors(new AuthorCreateDto(name)));
            } catch (CustomException e) {
                System.out.println("ERROR_CODE: " + e.getCode() + " ERROR_MESSAGE: " + e.getMessage());
            }
            showMenu();
        });

        addMenuItem(9, "Update author.", scanner -> {
            System.out.println("Operation: Update author.");
            System.out.println("Enter author's id:");
            Long authorId = scanner.nextLong();
            System.out.println("Enter new author's name:");
            scanner.nextLine();
            String name = scanner.nextLine();
            try {
                AuthorCreateDto dto = new AuthorCreateDto(authorId, name);
                System.out.println(commandInvoker.updateAuthors(dto));
            } catch (CustomException e) {
                System.out.println("ERROR_CODE: " + e.getCode() + " ERROR_MESSAGE: " + e.getMessage());
            }
            showMenu();
        });

        addMenuItem(10, "Remove author by id.", scanner -> {
            System.out.println("Operation: Remove author by id.\n" + "Enter author id:");
            Long authorId = scanner.nextLong();
            System.out.println(commandInvoker.deleteAuthorsById(authorId));
            showMenu();
        });

        addMenuItem(0, "Exit.", scanner -> {
        });
    }

    public void showMenu() {
        System.out.println("Enter the number of operation:");
        for (MenuItem item : menu.values()) {
            System.out.println(item.toString());
        }
    }

    @Override
    public void afterPropertiesSet() {
        createMenu();
    }
}
