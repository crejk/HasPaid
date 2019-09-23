package pl.crejk.haspaid.profile;

import pl.crejk.haspaid.mojang.MojangProfile;
import pl.crejk.haspaid.util.UUIDUtil;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Table(name = "haspaid_profiles")
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, columnDefinition = "BINARY(16)")
    private UUID uniqueId;

    @Column(unique = true, columnDefinition = "VARCHAR(16)")
    private String name;

    @Column(nullable = false)
    private boolean premium;

    public Profile(UUID uniqueId, String name, boolean premium) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.premium = premium;
    }

    // premium account constructor
    public Profile(MojangProfile profile) {
        this.uniqueId = UUIDUtil.fromIdWithoutBreak(profile.getId());
        this.name = profile.getName();
        this.premium = true;
    }

    // cracked account constructor
    public Profile(String name) {
        this.uniqueId = UUIDUtil.getOfflineId(name);
        this.name = name;
        this.premium = false;
    }

    public Profile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return id == profile.id &&
                premium == profile.premium &&
                uniqueId.equals(profile.uniqueId) &&
                name.equals(profile.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uniqueId, name, premium);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", uniqueId=" + uniqueId +
                ", name='" + name + '\'' +
                ", premium=" + premium +
                '}';
    }
}
