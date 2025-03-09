package fr.younesasn.jvsraide.repository;

import fr.younesasn.jvsraide.model.Sraide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SraideRepository extends JpaRepository<Sraide, Long> {

}
