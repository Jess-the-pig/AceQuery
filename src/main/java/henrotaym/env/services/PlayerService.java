package henrotaym.env.services;

import henrotaym.env.entities.Player;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    @PersistenceContext private EntityManager entityManager;

    public List<Player> searchPlayers(
            String name, String nationality, String sortBy, String sortOrder) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> cq = cb.createQuery(Player.class);
        Root<Player> player = cq.from(Player.class);

        List<Predicate> predicates = new ArrayList<>();

        // Filtre name (contains, insensible à la casse)
        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(cb.lower(player.get("name")), "%" + name.toLowerCase() + "%"));
        }

        // Filtre nationality (égalité stricte)
        if (nationality != null && !nationality.isEmpty()) {
            predicates.add(cb.equal(player.get("nationality"), nationality));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        // Gestion du tri dynamique
        boolean asc = !"desc".equalsIgnoreCase(sortOrder); // défaut asc

        if ("age".equalsIgnoreCase(sortBy)) {
            if (asc) {
                // age asc = naissance la plus récente en premier = birthdate desc
                cq.orderBy(cb.desc(player.get("birthdate")));
            } else {
                // age desc = naissance la plus ancienne en premier = birthdate asc
                cq.orderBy(cb.asc(player.get("birthdate")));
            }
        } else if ("birthdate".equalsIgnoreCase(sortBy)) {
            // optionnel si tu veux garder ce tri direct sur birthdate
            if (asc) {
                cq.orderBy(cb.asc(player.get("birthdate")));
            } else {
                cq.orderBy(cb.desc(player.get("birthdate")));
            }
        } else if ("ranking".equalsIgnoreCase(sortBy)) {
            if (asc) {
                cq.orderBy(cb.asc(player.get("ranking")));
            } else {
                cq.orderBy(cb.desc(player.get("ranking")));
            }
        } else {
            // tri par défaut
            cq.orderBy(cb.asc(player.get("ranking")));
        }

        return entityManager.createQuery(cq).getResultList();
    }
}
