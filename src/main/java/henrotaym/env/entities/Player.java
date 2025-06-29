package henrotaym.env.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nationality;

    private LocalDate birthdate;

    private Integer ranking;

    // Getters

    public Player() {}

    public Player(Long id, String name, String nationality, LocalDate birthdate, Integer ranking) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.birthdate = birthdate;
        this.ranking = ranking;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Integer getRanking() {
        return ranking;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}
