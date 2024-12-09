package Gestion.de.stage.Gestion.de.stage.Controller;

import Gestion.de.stage.Gestion.de.stage.Dto.AdminEcoleDto;
import Gestion.de.stage.Gestion.de.stage.Service.AdminEcoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin-ecole")
public class AdminEcoleController {

    private final AdminEcoleService adminEcoleService;

    public AdminEcoleController(AdminEcoleService adminEcoleService) {
        this.adminEcoleService = adminEcoleService;
    }

    // **Créer un administrateur d'école**
    @PostMapping("/create")
    public ResponseEntity<AdminEcoleDto> createAdminEcole(@RequestBody AdminEcoleDto adminEcoleDto) {
        AdminEcoleDto createdAdmin = adminEcoleService.createAdminEcole(adminEcoleDto);
        return ResponseEntity.ok(createdAdmin);
    }

    // **Récupérer un administrateur d'école par son ID**
    @GetMapping("/{id}")
    public ResponseEntity<AdminEcoleDto> getAdminEcoleById(@PathVariable Long id) {
        AdminEcoleDto adminEcole = adminEcoleService.getAdminEcoleById(id);
        return ResponseEntity.ok(adminEcole);
    }

    // **Mettre à jour un administrateur d'école**
    @PutMapping("/{id}")
    public ResponseEntity<AdminEcoleDto> updateAdminEcole(@PathVariable Long id, @RequestBody AdminEcoleDto adminEcoleDto) {
        AdminEcoleDto updatedAdmin = adminEcoleService.updateAdminEcole(id, adminEcoleDto);
        return ResponseEntity.ok(updatedAdmin);
    }

    // **Supprimer un administrateur d'école**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminEcole(@PathVariable Long id) {
        adminEcoleService.deleteAdminEcole(id);
        return ResponseEntity.noContent().build();
    }

    // **Récupérer tous les administrateurs d'école**
    @GetMapping
    public ResponseEntity<List<AdminEcoleDto>> getAllAdminEcoles() {
        List<AdminEcoleDto> adminEcoles = adminEcoleService.getAllAdminEcoles();
        return ResponseEntity.ok(adminEcoles);
    }
}
