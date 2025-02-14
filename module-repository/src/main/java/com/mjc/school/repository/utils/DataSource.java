package com.mjc.school.repository.utils;

import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class DataSource implements InitializingBean {
    public static final int DEFAULT_NEWS_COUNT_TO_GENERATE = 20;
    private final Map<Long, NewsModel> news = new HashMap<>();
    private final Map<Long, AuthorModel> authors = new HashMap<>();
    private long lastNewsIndex = 0;

    private List<AuthorModel> readAuthorsFromFile(String path) {
        List<String> lines = readFromFile(path);
        long id = 1;
        List<AuthorModel> authorsFromFile = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (String line : lines) {
            authorsFromFile.add(new AuthorModel(id, line, now, now));
            id++;

        }
        return authorsFromFile;
    }

    private List<String> readTitlesFromFiles(String titlePath) {
        return readFromFile(titlePath);
    }

    private List<String> readContentsFromFile(String contentPath) {
        return readFromFile(contentPath);
    }

    private List<String> readFromFile(String contentPath) {
        try {
            URL resource = Objects.requireNonNull(NewsRepository.class.getClassLoader().getResource(contentPath));
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.openStream()))) {
                return new ArrayList<>(reader.lines().toList());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file " + contentPath, e);
        }
    }

    public Map<Long, NewsModel> getNews() {
        return news;
    }

    public Map<Long, AuthorModel> getAuthors() {
        return authors;
    }

    public long getNextId() {
        return ++lastNewsIndex;
    }

    public void generateNews(String titlesPath, String authorsPath, String contentPath) {
        List<String> titlesFromFile = readTitlesFromFiles(titlesPath);
        List<AuthorModel> authorsFromFile = readAuthorsFromFile(authorsPath);
        List<String> contentFromFile = readContentsFromFile(contentPath);
        for (int i = 0; i < DEFAULT_NEWS_COUNT_TO_GENERATE; i++) {
            Random randomNum = new Random();
            int indexC = randomNum.nextInt(0, contentFromFile.size());
            String tmpContent = contentFromFile.get(indexC);
            int indexT = randomNum.nextInt(0, titlesFromFile.size());
            String tmpTitle = titlesFromFile.get(indexT);
            int indexA = randomNum.nextInt(0, authorsFromFile.size());
            AuthorModel tmpAuthor = authorsFromFile.get(indexA);
            long id = this.lastNewsIndex++;
            LocalDateTime now = LocalDateTime.now();
            news.put(id, new NewsModel(id, tmpTitle, tmpContent, now, now, tmpAuthor));
            authors.put(tmpAuthor.getId(), tmpAuthor);
            contentFromFile.remove(indexC);
            titlesFromFile.remove(indexT);
            authorsFromFile.remove(indexA);
        }
    }

    @Override
    public void afterPropertiesSet() {
        generateNews("news", "authors", "content");
    }
}
