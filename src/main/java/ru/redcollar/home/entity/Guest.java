package ru.redcollar.home.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long age;
    private Boolean isStudent;
    private String jobName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "guest_talk",
            joinColumns = @JoinColumn(name = "talk_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id"))
    private Set<Talk> signedTalks;

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", name, age, isStudent, jobName);
    }
}
