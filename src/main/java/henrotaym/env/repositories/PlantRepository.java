package henrotaym.env.repositories;

import henrotaym.env.entities.Plant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PlantRepository extends JpaRepository<Plant, BigInteger> {}
