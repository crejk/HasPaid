package pl.crejk.haspaid.profile;

import pl.crejk.haspaid.mojang.MojangProfile;
import pl.crejk.haspaid.util.UUIDUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Table(name = "haspaid_profiles")
@Entity
public class Profile {

    private UUID id;
    @Id
    @Column(columnDefinition = "VARCHAR(16)")
    private String name;
    private boolean premium;

    public Profile(UUID id, String name, boolean premium) {
        this.id = id;
        this.name = name;
        this.premium = premium;
    }

    // premium account constructor
    public Profile(MojangProfile profile) {
        this.id = UUIDUtil.fromIdWithoutBreak(profile.getId());
        this.name = profile.getName();
        this.premium = true;
    }

    // cracked account constructor
    public Profile(String name) {
        this.id = UUIDUtil.getOfflineId(name);
        this.name = name;
        this.premium = false;
    }

    public Profile() {
    }

    public UUID getId() {
        return id;
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
        return premium == profile.premium &&
                Objects.equals(id, profile.id) &&
                Objects.equals(name, profile.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, premium);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "uuid=" + id +
                ", name='" + name + '\'' +
                ", premium=" + premium +
                '}';
    }
}
