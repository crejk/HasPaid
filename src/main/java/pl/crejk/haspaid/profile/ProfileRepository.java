package pl.crejk.haspaid.profile;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {

    Optional<Profile> findByName(String name);
}
