package henrotaym.env.repositories;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import henrotaym.env.entities.Disease;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, BigInteger> {
    List<Disease> findByPlantId(BigInteger plantId);
}
