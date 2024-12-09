package Gestion.de.stage.Gestion.de.stage.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="adminSystem")
public class AdminSystemEntiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UtilisateurEntity utilisateur; // Clé étrangère vers Utilisateur
}

