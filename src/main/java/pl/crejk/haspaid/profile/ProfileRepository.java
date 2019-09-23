package pl.crejk.haspaid.profile;

import io.vavr.control.Option;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Integer> {

    Option<Profile> findByName(String name);
}
