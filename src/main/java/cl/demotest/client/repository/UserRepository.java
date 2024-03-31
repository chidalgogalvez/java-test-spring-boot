package cl.demotest.client.repository;

import cl.demotest.client.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository  extends CrudRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);

    Optional<Users> findUserById(UUID uuid);

    void deleteByid(UUID uuid);
}
