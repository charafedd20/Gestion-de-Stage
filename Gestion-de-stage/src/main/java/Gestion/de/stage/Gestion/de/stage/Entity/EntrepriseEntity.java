package Gestion.de.stage.Gestion.de.stage.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Entreprise")
public class EntrepriseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String Description;

    @Column(nullable = false)
    private String type;



    @OneToMany(mappedBy = "entrepriseEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OffreDeStageEntity> offreDeStageEntityList;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UtilisateurEntity utilisateur; // Clé étrangère vers Utilisateur
}
