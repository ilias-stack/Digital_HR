package ord.sid.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String nomTache;
    private Date dateDebut;
    private Date dateFin;
    @ManyToOne
    private Employe employeTache;
    @ManyToOne
    private Projet projetTache;
}
