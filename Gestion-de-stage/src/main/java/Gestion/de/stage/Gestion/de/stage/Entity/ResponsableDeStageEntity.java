package Gestion.de.stage.Gestion.de.stage.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "respostage")
public class ResponsableDeStageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephone;

    @OneToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UtilisateurEntity utilisateur; // Clé étrangère vers Utilisateur

    @OneToOne
    @JoinColumn(name="ecole_id",referencedColumnName = "id")
    private AdminEcoleEntity ecole;


}

