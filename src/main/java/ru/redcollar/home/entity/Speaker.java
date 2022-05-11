package ru.redcollar.home.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(name = Speaker.TALKS_ENTITY_GRAPH,
                attributeNodes = @NamedAttributeNode(Speaker_.TALKS))
})
public class Speaker {

    public static final String TALKS_ENTITY_GRAPH = "talks_entity_graph";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "speaker", fetch = FetchType.LAZY)
    private List<Talk> talks;
}
