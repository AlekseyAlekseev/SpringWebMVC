package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class PostRepository {

    private static AtomicInteger counter = new AtomicInteger(1);

    private static final Map<Long, Post> database = new ConcurrentHashMap<>();

    public List<Post> all() {
        return new ArrayList<>(database.values());
    }

    public Optional<Post> getById(long id) {
        if (!database.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.of(database.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(counter.getAndIncrement());
            counter.incrementAndGet();
        }
        database.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        database.remove(id);
    }
}
