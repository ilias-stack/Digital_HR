package ord.sid.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data@AllArgsConstructor@NoArgsConstructor
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String nomProjet;
    private Date dateDebut;
    private Date dateFin;
    @OneToMany(mappedBy = "projetEmploye")
   private List<Employe> employeList;
    @OneToMany(mappedBy = "projetTache")
   private List<Tache> tacheList;
    @ManyToOne
   private Customer customer;
}
