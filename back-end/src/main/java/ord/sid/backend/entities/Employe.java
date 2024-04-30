package ord.sid.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
private String firstName;
private String lastName;
private String email;
private String Tel;
private Date dateEntre;
@ManyToOne
private Projet projetEmploye;
@OneToMany(mappedBy = "employeTache")
private List<Tache> tacheList;




}
