package pl.crejk.haspaid;

import java.util.Objects;

public class HasPaidResult {

    private final String id;
    private final String name;

    public HasPaidResult(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasPaidResult that = (HasPaidResult) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "HasPaidResult{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
