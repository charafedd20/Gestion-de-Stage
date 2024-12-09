package Gestion.de.stage.Gestion.de.stage.Controller;

import Gestion.de.stage.Gestion.de.stage.Dto.UserDto;
import Gestion.de.stage.Gestion.de.stage.Service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // **Créer un utilisateur**
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = utilisateurService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    // **Récupérer un utilisateur par ID**
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = utilisateurService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // **Récupérer un utilisateur par identifiant**
    @GetMapping("/identifiant/{identifiant}")
    public ResponseEntity<UserDto> getUserByIdentifiant(@PathVariable String identifiant) {
        UserDto user = utilisateurService.getUserByIdentifiant(identifiant);
        return ResponseEntity.ok(user);
    }

    // **Mettre à jour un utilisateur**
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUser = utilisateurService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    // **Supprimer un utilisateur**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        utilisateurService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // **Récupérer tous les utilisateurs**
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = utilisateurService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // **Récupérer les utilisateurs par rôle**
    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserDto>> getUsersByRole(@PathVariable String role) {
        List<UserDto> users = utilisateurService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }
}
