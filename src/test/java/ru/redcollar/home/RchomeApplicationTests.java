package ru.redcollar.home;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.redcollar.home.entity.Guest;
import ru.redcollar.home.entity.Speaker;
import ru.redcollar.home.entity.Talk;
import ru.redcollar.home.repository.GuestRepository;
import ru.redcollar.home.repository.GuestSpecification;
import ru.redcollar.home.repository.SpeakerRepository;
import ru.redcollar.home.repository.TalkRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
@Sql("classpath:db/import.sql")
class RchomeApplicationTests {

    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private TalkRepository talkRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Test
    @Transactional
    public void shouldReturnAllSpeakers() {
        Iterable<Speaker> speakers = speakerRepository.findAll();
        speakers.forEach(speaker -> {
            log.info(speaker.getName());
            log.info(speaker.getTalks().toString());
        });
    }

    @Test
    public void shouldReturnSpeakersSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Iterable<Speaker> speakers = speakerRepository.findAll(sort);
        speakers.forEach(speaker -> log.info(speaker.getName()));
    }

    @Test
    @Transactional
    public void shouldReturnSpeakersPaged() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(0, 2, sort);
        Page<Speaker> speakers = speakerRepository.findAll(pageable);
        speakers.forEach(speaker -> {
            log.info(speaker.getName());
            log.info(speaker.getTalks().toString());
        });
    }

    @Test
    @Transactional
    public void shouldResolveInMemoryPagination() {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        Pageable pageable = PageRequest.of(0, 2, sort);
        List<Long> ids = speakerRepository.findIds(pageable);
        List<Speaker> speakers = speakerRepository.findAllByIdIn(ids, sort);
        speakers.forEach(speaker -> {
            log.info(speaker.getName());
            log.info(speaker.getTalks().toString());
        });
    }

    @Test
    public void shouldReturnSpeakersByName() {
        Iterable<Speaker> speakers = speakerRepository.findSpeakersByNameStartingWith("G");
        speakers.forEach(speaker -> log.info(speaker.getName()));
    }

    @Test
    public void shouldReturnSpeakersByTalksName() {
        Iterable<Speaker> speakers = speakerRepository.findПожалуйстаDistinctSpeakersByTalksNameLikeIgnoreCase("%java%");
        speakers.forEach(speaker -> log.info(speaker.getName()));
    }

    @Test
    public void shouldUseCustomImplementation() {
        speakerRepository.doSomethingCool();
    }

    @Test
    public void talkRepoTest() {
        List<Talk> talks = talkRepository.findAll();
        talks.forEach(talk -> log.info(talk.getName()));
    }

    @Test
    @Sql("classpath:db/guests.sql")
    public void shouldDynamic() {
        GuestSpecification guestSpecification = new GuestSpecification();
        guestSpecification.setIsStudent(true);
        guestSpecification.setAgeFrom(19L);
        guestSpecification.setAgeTo(23L);
        Specification<Guest> specification = guestSpecification.buildSpec();

        List<Guest> guests = guestRepository.findAll(specification);
        guests.forEach(guest -> log.info(guest.toString()));
    }
}
