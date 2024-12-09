package Gestion.de.stage.Gestion.de.stage.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="adminEcole")
public class AdminEcoleEntity {

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
    private String Respo_Stage_Associé;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UtilisateurEntity utilisateur; // Clé étrangère vers Utilisateur

}
