package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.DataSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {

    private final DataSource dataSource;

    public NewsRepository(DataSource ds) {
        this.dataSource = ds;
    }

    @Override
    public List<NewsModel> readAll() {
        return dataSource.getNews().values().stream().toList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return Optional.ofNullable(dataSource.getNews().get(id));
    }

    @Override
    public NewsModel create(NewsModel entity) {
        return save(entity);
    }

    @Override
    public NewsModel update(NewsModel entity) {
        return save(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        return dataSource.getNews().remove(id) != null;
    }

    @Override
    public boolean existById(Long id) {
        return dataSource.getNews().get(id) != null;
    }

    public void deleteNewsByAuthorId(Long authorId) {
        List<NewsModel> news = readAll();
        for (NewsModel item : news) {
            if (Objects.equals(item.getAuthor().getId(), authorId)) {
                dataSource.getNews().remove(item.getId());
            }
        }
    }

    private NewsModel save(NewsModel news) {
        if (news.getId() == null) {
            news.setId(dataSource.getNextId());
            news.setCreateDate(LocalDateTime.now());
        }
        dataSource.getNews().put(news.getId(), news);
        news.setLastUpdateDate(LocalDateTime.now());
        return news;
    }
}
