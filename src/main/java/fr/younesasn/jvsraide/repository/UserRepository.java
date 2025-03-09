package fr.younesasn.jvsraide.repository;

import fr.younesasn.jvsraide.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
