package Gestion.de.stage.Gestion.de.stage.Dto;

import lombok.Data;

@Data
public class EtudiantDto {

    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String statut;
    private String filiere;
    private String anneeEtude;
    private String ecole;
    private long utilisateurId; // Clé étrangère vers l'entité Utilisateur (au lieu de l'entité entière)

}

