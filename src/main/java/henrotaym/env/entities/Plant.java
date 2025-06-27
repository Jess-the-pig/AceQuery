package henrotaym.env.entities;

import henrotaym.env.enums.StatusName;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "plant")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private String species;
    private String name;
    private Date buyingDate;
    private StatusName status;

    @ManyToMany
    @JoinTable(
            name = "plant_disease",
            joinColumns = @JoinColumn(name = "plant_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id"))
    private List<Disease> diseases;

    // Getters
    public BigInteger getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public String getName() {
        return name;
    }

    public Date getBuyingDate() {
        return buyingDate;
    }

    public StatusName getStatus() {
        return status;
    }

    // Setters
    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBuyingDate(Date buyingDate) {
        this.buyingDate = buyingDate;
    }

    public void setStatus(StatusName status) {
        this.status = status;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }
}
