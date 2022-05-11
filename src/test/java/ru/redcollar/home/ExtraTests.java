package ru.redcollar.home;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.redcollar.home.entity.Guest;
import ru.redcollar.home.entity.Speaker;
import ru.redcollar.home.entity.Talk;
import ru.redcollar.home.repository.GuestRepository;
import ru.redcollar.home.repository.SpeakerRepository;
import ru.redcollar.home.repository.TalkRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
public class ExtraTests {

    @Autowired
    private SpeakerRepository speakerRepository;

    @Autowired
    private TalkRepository talkRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Test
    @Sql("classpath:db/import.sql")
    public void toStringTest() {
        Speaker speaker = speakerRepository.findById(1L).get();
        log.info(speaker.toString());
    }

    /*
    change Talk and Guest fields to list and uncomment this test to run
    @Test
    @Transactional
    public void listForManyToManyTest() {
        Talk talk = new Talk();
        talk.setName("cool story");
        talk = talkRepository.save(talk);

        Talk talk1 = new Talk();
        talk1.setName("some name");
        talk1 = talkRepository.save(talk1);

        Talk talk2 = new Talk();
        talk2.setName("another name");
        talk2 = talkRepository.save(talk2);

        Guest guest = new Guest();
        guest.setSignedTalks(List.of(talk, talk1, talk2));
        guest = guestRepository.saveAndFlush(guest);
        log.info("GUEST TALKS: {}", guest.getSignedTalks());

        List<Talk> signedTalks = guest.getSignedTalks();
        List<Talk> newSignedTalks = new ArrayList<>(signedTalks);
        newSignedTalks.remove(talk1);
        guest.setSignedTalks(newSignedTalks);
        guestRepository.saveAndFlush(guest);
        log.info("GUEST TALKS: {}", guest.getSignedTalks());
    }
    */

    @Test
    @Transactional
    public void setForManyToManyTest() {
        Talk talk = new Talk();
        talk.setName("cool story");
        talk = talkRepository.save(talk);

        Talk talk1 = new Talk();
        talk1.setName("some name");
        talk1 = talkRepository.save(talk1);

        Talk talk2 = new Talk();
        talk2.setName("another name");
        talk2 = talkRepository.save(talk2);

        Guest guest = new Guest();
        guest.setSignedTalks(Set.of(talk, talk1, talk2));
        guest = guestRepository.saveAndFlush(guest);
        log.info("GUEST TALKS: {}", guest.getSignedTalks());

        Set<Talk> signedTalks = guest.getSignedTalks();
        Set<Talk> newSignedTalks = new HashSet<>(signedTalks);
        newSignedTalks.remove(talk1);
        guest.setSignedTalks(newSignedTalks);
        guestRepository.saveAndFlush(guest);
        log.info("GUEST TALKS: {}", guest.getSignedTalks());
    }

    // run this test then
    // comment default lombok check in Talk equals method
    // and uncomment correct check to see the difference
    @Test
    public void testEquals() {
        Talk talk = new Talk();
        talk.setName("bob");

        Talk anotherTalk = new Talk();
        anotherTalk.setName("gary");

        Set<Talk> talks = new HashSet<>();
        talks.add(talk);
        talks.add(anotherTalk);

        Assertions.assertEquals(2, talks.size());
    }
}
