package Gestion.de.stage.Gestion.de.stage.Controller;

import Gestion.de.stage.Gestion.de.stage.Dto.EtudiantDto;
import Gestion.de.stage.Gestion.de.stage.Service.EtudiantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    // **Créer un étudiant**
    @PostMapping
    public ResponseEntity<EtudiantDto> createEtudiant(@RequestBody EtudiantDto etudiantDto) {
        EtudiantDto createdEtudiant = etudiantService.createEtudiant(etudiantDto);
        return ResponseEntity.ok(createdEtudiant);
    }

    // **Récupérer un étudiant par ID**
    @GetMapping("/{id}")
    public ResponseEntity<EtudiantDto> getEtudiantById(@PathVariable Long id) {
        EtudiantDto etudiant = etudiantService.getEtudiantById(id);
        return ResponseEntity.ok(etudiant);
    }

    // **Mettre à jour un étudiant**
    @PutMapping("/{id}")
    public ResponseEntity<EtudiantDto> updateEtudiant(@PathVariable Long id, @RequestBody EtudiantDto etudiantDto) {
        EtudiantDto updatedEtudiant = etudiantService.updateEtudiant(id, etudiantDto);
        return ResponseEntity.ok(updatedEtudiant);
    }

    // **Supprimer un étudiant**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }

    // **Récupérer tous les étudiants**
    @GetMapping
    public ResponseEntity<List<EtudiantDto>> getAllEtudiants() {
        List<EtudiantDto> etudiants = etudiantService.getAllEtudiants();
        return ResponseEntity.ok(etudiants);
    }

    // **Rechercher un étudiant par email**
    @GetMapping("/search-by-email")
    public ResponseEntity<EtudiantDto> getEtudiantByEmail(@RequestParam String email) {
        EtudiantDto etudiant = etudiantService.getEtudiantByEmail(email);
        return ResponseEntity.ok(etudiant);
    }

    // **Rechercher les étudiants par filière**
    @GetMapping("/search-by-filiere")
    public ResponseEntity<List<EtudiantDto>> getEtudiantsByFiliere(@RequestParam String filiere) {
        List<EtudiantDto> etudiants = etudiantService.getEtudiantsByFiliere(filiere);
        return ResponseEntity.ok(etudiants);
    }

    // **Rechercher les étudiants par école**
    @GetMapping("/search-by-ecole")
    public ResponseEntity<List<EtudiantDto>> getEtudiantsByEcole(@RequestParam String ecole) {
        List<EtudiantDto> etudiants = etudiantService.getEtudiantsByEcole(ecole);
        return ResponseEntity.ok(etudiants);
    }
}
