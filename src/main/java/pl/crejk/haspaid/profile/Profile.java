package pl.crejk.haspaid.profile;

import pl.crejk.haspaid.mojang.MojangProfile;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Table(name = "haspaid_profiles")
@Entity
public class Profile {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UUID uuid;
    @Id
    @Column(columnDefinition = "VARCHAR(16)")
    private String name;
    private boolean premium;

    public Profile(int id, UUID uuid, String name, boolean premium) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.premium = premium;
    }

    // premium account constructor
    public Profile(MojangProfile profile) {
        this.name = profile.getName();
        this.premium = true;
    }

    // cracked account constructor
    public Profile(String name) {
        this.name = name;
        this.premium = false;
    }

    public Profile() {
    }

    public int getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public boolean isPremium() {
        return premium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return id == profile.id &&
                premium == profile.premium &&
                uuid.equals(profile.uuid) &&
                name.equals(profile.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, name, premium);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", name='" + name + '\'' +
                ", premium=" + premium +
                '}';
    }
}
