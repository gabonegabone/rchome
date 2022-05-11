package ru.redcollar.home.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;

    @ManyToMany(mappedBy = "signedTalks")
    private Set<Guest> guests;

    @Override
    public String toString() {
        return name;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Talk)) return false;
        final Talk other = (Talk) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();

        // default lombok null check
        if (this$id == null) {
            if (other$id != null) return false;
        } else {
            if (!this$id.equals(other$id)) return false;
        }
        return true;

        // correct null check
//        if (this$id == null || other$id == null) return false;
//        else return this$id.equals(other$id);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Talk;
    }

    public int hashCode() {
        return 42;
    }
}
