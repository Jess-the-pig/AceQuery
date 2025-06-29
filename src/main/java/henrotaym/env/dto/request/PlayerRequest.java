package henrotaym.env.dto.request;

import java.time.LocalDate;

public class PlayerRequest {
      private String name;

    private String nationality;

    private LocalDate birthdate;

    private Integer ranking;

    // Getters

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
