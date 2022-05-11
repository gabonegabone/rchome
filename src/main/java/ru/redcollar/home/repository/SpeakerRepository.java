package ru.redcollar.home.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.redcollar.home.entity.Speaker;

import java.util.List;

public interface SpeakerRepository extends CrudRepository<Speaker, Long>,
        PagingAndSortingRepository<Speaker, Long>,
        CustomRepository {

    @EntityGraph(attributePaths = "talks")
    Iterable<Speaker> findAll();

    @EntityGraph(value = Speaker.TALKS_ENTITY_GRAPH)
    Page<Speaker> findAll(Pageable pageable);

    @Query("select speaker.id from Speaker speaker")
    List<Long> findIds(Pageable pageable);

    @EntityGraph(attributePaths = "talks")
    List<Speaker> findAllByIdIn(List<Long> longs, Sort sort);

    Iterable<Speaker> findSpeakersByNameStartingWith(String namePrefix);

    Iterable<Speaker> findПожалуйстаDistinctSpeakersByTalksNameLikeIgnoreCase(String talkNameLike);
}
