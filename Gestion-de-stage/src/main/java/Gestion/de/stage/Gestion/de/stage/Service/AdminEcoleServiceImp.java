package Gestion.de.stage.Gestion.de.stage.Service;

import Gestion.de.stage.Gestion.de.stage.Dto.AdminEcoleDto;
import Gestion.de.stage.Gestion.de.stage.Entity.AdminEcoleEntity;
import Gestion.de.stage.Gestion.de.stage.Entity.UtilisateurEntity;
import Gestion.de.stage.Gestion.de.stage.Repository.AdminEcolRepo;
import Gestion.de.stage.Gestion.de.stage.Repository.UtilisateurRepo;
import Gestion.de.stage.Gestion.de.stage.mapper.AdminEcoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminEcoleServiceImp implements AdminEcoleService{

    private final AdminEcolRepo adminEcolRepo;
    private final UtilisateurRepo utilisateurRepo;

    @Autowired
    public AdminEcoleServiceImp(AdminEcolRepo adminEcolRepo, UtilisateurRepo utilisateurRepo) {
        this.adminEcolRepo = adminEcolRepo;
        this.utilisateurRepo = utilisateurRepo;
    }

    // Gestion des administrateurs d'école
    @Override
    public AdminEcoleDto createAdminEcole(AdminEcoleDto adminEcoleDto) {
        if (adminEcolRepo.existsByEmail(adminEcoleDto.getEmail())) {
            throw new RuntimeException("Un administrateur avec cet email existe déjà.");
        }

        AdminEcoleEntity adminEcoleEntity = AdminEcoleMapper.toEntity(adminEcoleDto);

        // Gérer l'utilisateur associé
        if (adminEcoleDto.getUtilisateurId() != null) {
            UtilisateurEntity utilisateur = utilisateurRepo.findById(adminEcoleDto.getUtilisateurId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé pour l'ID : " + adminEcoleDto.getUtilisateurId()));
            adminEcoleEntity.setUtilisateur(utilisateur);
        }

        AdminEcoleEntity savedEntity = adminEcolRepo.save(adminEcoleEntity);
        return AdminEcoleMapper.toDto(savedEntity);
    }

    @Override
    public AdminEcoleDto updateAdminEcole(Long id, AdminEcoleDto adminEcoleDto) {
        AdminEcoleEntity existingEntity = adminEcolRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrateur non trouvé pour l'ID : " + id));

        existingEntity.setNom(adminEcoleDto.getNom());
        existingEntity.setAdresse(adminEcoleDto.getAdresse());
        existingEntity.setEmail(adminEcoleDto.getEmail());
        existingEntity.setTelephone(adminEcoleDto.getTelephone());
        existingEntity.setRespo_Stage_Associé(adminEcoleDto.getRespoStageAssocie());

        // Gérer l'utilisateur associé
        if (adminEcoleDto.getUtilisateurId() != null) {
            UtilisateurEntity utilisateur = utilisateurRepo.findById(adminEcoleDto.getUtilisateurId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé pour l'ID : " + adminEcoleDto.getUtilisateurId()));
            existingEntity.setUtilisateur(utilisateur);
        }

        AdminEcoleEntity updatedEntity = adminEcolRepo.save(existingEntity);
        return AdminEcoleMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteAdminEcole(Long id) {
        if (!adminEcolRepo.existsById(id)) {
            throw new RuntimeException("Administrateur non trouvé pour l'ID : " + id);
        }
        adminEcolRepo.deleteById(id);
    }

    @Override
    public AdminEcoleDto getAdminEcoleById(Long id) {
        AdminEcoleEntity adminEcoleEntity = adminEcolRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrateur non trouvé pour l'ID : " + id));
        return AdminEcoleMapper.toDto(adminEcoleEntity);
    }

    @Override
    public List<AdminEcoleDto> getAllAdminEcoles() {
        return adminEcolRepo.findAll()
                .stream()
                .map(AdminEcoleMapper::toDto)
                .collect(Collectors.toList());
    }

}
