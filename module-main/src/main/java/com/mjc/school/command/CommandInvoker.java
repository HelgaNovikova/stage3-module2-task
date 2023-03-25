package com.mjc.school.command;

import com.mjc.school.controller.command.CommandExecutor;
import com.mjc.school.service.dto.AuthorCreateDto;
import com.mjc.school.service.dto.AuthorResponseDto;
import com.mjc.school.service.dto.NewsCreateDto;
import com.mjc.school.service.dto.NewsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CommandInvoker {

    private final CommandExecutor executor;

    @Autowired
    public CommandInvoker(CommandExecutor executor) {
        this.executor = executor;
    }

    public List<NewsResponseDto> readAllNews() {
        return new CommandImpl("news.readAll", executor).execute();
    }

    public NewsResponseDto readNewsById(Long id) {
        return new CommandImpl("news.readById", Map.of("id", id), executor).execute();
    }

    public NewsResponseDto createNews(NewsCreateDto dto) {
        return new CommandImpl("news.create", dto, executor).execute();
    }

    public NewsResponseDto updateNews(NewsCreateDto dto) {
        return new CommandImpl("news.update", dto, executor).execute();
    }

    public boolean deleteNewsById(Long id) {
        return new CommandImpl("news.deleteById", Map.of("id", id), executor).execute();
    }

    public List<AuthorResponseDto> readAllAuthors() {
        return new CommandImpl("authors.readAll", executor).execute();
    }

    public AuthorResponseDto readAuthorsById(Long id) {
        return new CommandImpl("authors.readById", Map.of("id", id), executor).execute();
    }

    public AuthorResponseDto createAuthors(AuthorCreateDto dto) {
        return new CommandImpl("authors.create", dto, executor).execute();
    }

    public AuthorResponseDto updateAuthors(AuthorCreateDto dto) {
        return new CommandImpl("authors.update", dto, executor).execute();
    }

    public boolean deleteAuthorsById(Long id) {
        return new CommandImpl("authors.deleteById", Map.of("id", id), executor).execute();
    }
}
