package Gestion.de.stage.Gestion.de.stage.Dto;

import lombok.Data;

@Data
public class UserDto {

    private long id;
    private String identifiant;
    private String password; // Champ pour gérer les mots de passe lors de la création/mise à jour
    private String role;  // Le rôle est un String pour représenter les différentes valeurs (ADMIN, ETUDIANT, etc.)

}

