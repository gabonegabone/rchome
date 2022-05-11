package ru.redcollar.home.repository;

import org.springframework.data.repository.Repository;
import ru.redcollar.home.entity.Talk;

import java.util.List;

public interface TalkRepository extends Repository<Talk, Long> {

    List<Talk> findAll();

    Talk save(Talk talk);
}
