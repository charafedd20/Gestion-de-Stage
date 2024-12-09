package Gestion.de.stage.Gestion.de.stage.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "etudiant")
public class EtudiantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String statut;

    @Column(nullable = false)
    private String filiere;

    @Column(nullable = false)
    private String anneeEtude;

    @Column(nullable = false)
    private String ecole;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UtilisateurEntity utilisateur;  // Clé étrangère vers Utilisateur



}
