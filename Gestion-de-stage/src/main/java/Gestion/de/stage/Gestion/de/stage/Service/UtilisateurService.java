package Gestion.de.stage.Gestion.de.stage.Service;

import Gestion.de.stage.Gestion.de.stage.Dto.UserDto;

import java.util.List;

public interface UtilisateurService {

    // Créer un utilisateur
    UserDto createUser(UserDto userDto);

    // Récupérer un utilisateur par ID
    UserDto getUserById(Long id);

    // Récupérer un utilisateur par identifiant
    UserDto getUserByIdentifiant(String identifiant);

    // Mettre à jour un utilisateur
    UserDto updateUser(Long id, UserDto userDto);

    // Supprimer un utilisateur
    void deleteUser(Long id);

    // Liste de tous les utilisateurs
    List<UserDto> getAllUsers();

    // Récupérer tous les utilisateurs par rôle
    List<UserDto> getUsersByRole(String role);
}
