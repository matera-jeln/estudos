package eod.paralelismo.core.repository;

import eod.paralelismo.core.model.Pact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PactRepository extends JpaRepository<Pact, Long> {

}
