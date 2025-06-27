package henrotaym.env.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigInteger;
import java.sql.Date;

@Entity
@Table(name = "action")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private String name;

    private Date due_at;

    @ManyToOne
    @JoinColumn(name = "plant_id", nullable = false)
    private Plant plant;

    // Getters
    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDue_at() {
        return due_at;
    }

    public Plant getPlant() {
        return plant;
    }

    // Setters
    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDue_at(Date due_at) {
        this.due_at = due_at;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
