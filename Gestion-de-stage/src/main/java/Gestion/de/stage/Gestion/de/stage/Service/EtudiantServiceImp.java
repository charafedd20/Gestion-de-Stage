package Gestion.de.stage.Gestion.de.stage.Service;

import Gestion.de.stage.Gestion.de.stage.Dto.EtudiantDto;
import Gestion.de.stage.Gestion.de.stage.Entity.EtudiantEntity;
import Gestion.de.stage.Gestion.de.stage.Entity.UtilisateurEntity;
import Gestion.de.stage.Gestion.de.stage.Repository.EtudiantRepo;
import Gestion.de.stage.Gestion.de.stage.Repository.UtilisateurRepo;
import Gestion.de.stage.Gestion.de.stage.mapper.EtudiantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EtudiantServiceImp implements EtudiantService{

    @Autowired
    private EtudiantRepo etudiantRepo;

    @Autowired
    private UtilisateurRepo utilisateurRepo;

    @Override
    public EtudiantDto createEtudiant(EtudiantDto etudiantDto) {
        EtudiantEntity etudiantEntity = EtudiantMapper.toEntity(etudiantDto);
        // Associer l'utilisateur si l'ID est fourni
        if (etudiantDto.getUtilisateurId() != 0) {
            UtilisateurEntity utilisateur = utilisateurRepo.findById(etudiantDto.getUtilisateurId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + etudiantDto.getUtilisateurId()));
            etudiantEntity.setUtilisateur(utilisateur);
        }
        EtudiantEntity savedEntity = etudiantRepo.save(etudiantEntity);
        return EtudiantMapper.toDto(savedEntity);
    }

    @Override
    public EtudiantDto updateEtudiant(Long id, EtudiantDto etudiantDto) {
        EtudiantEntity existingEntity = etudiantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'ID: " + id));

        // Mise à jour des champs
        existingEntity.setNom(etudiantDto.getNom());
        existingEntity.setPrenom(etudiantDto.getPrenom());
        existingEntity.setEmail(etudiantDto.getEmail());
        existingEntity.setTelephone(etudiantDto.getTelephone());
        existingEntity.setStatut(etudiantDto.getStatut());
        existingEntity.setFiliere(etudiantDto.getFiliere());
        existingEntity.setAnneeEtude(etudiantDto.getAnneeEtude());
        existingEntity.setEcole(etudiantDto.getEcole());

        // Mise à jour de l'utilisateur si nécessaire
        if (etudiantDto.getUtilisateurId() != 0) {
            UtilisateurEntity utilisateur = utilisateurRepo.findById(etudiantDto.getUtilisateurId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + etudiantDto.getUtilisateurId()));
            existingEntity.setUtilisateur(utilisateur);
        }

        EtudiantEntity updatedEntity = etudiantRepo.save(existingEntity);
        return EtudiantMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteEtudiant(Long id) {
        if (!etudiantRepo.existsById(id)) {
            throw new RuntimeException("Étudiant non trouvé avec l'ID: " + id);
        }
        etudiantRepo.deleteById(id);
    }

    @Override
    public EtudiantDto getEtudiantById(Long id) {
        EtudiantEntity etudiantEntity = etudiantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'ID: " + id));
        return EtudiantMapper.toDto(etudiantEntity);
    }

    @Override
    public EtudiantDto getEtudiantByEmail(String email) {
        EtudiantEntity etudiantEntity = etudiantRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'email: " + email));
        return EtudiantMapper.toDto(etudiantEntity);
    }

    @Override
    public List<EtudiantDto> getAllEtudiants() {
        return etudiantRepo.findAll()
                .stream()
                .map(EtudiantMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EtudiantDto> getEtudiantsByFiliere(String filiere) {
        return etudiantRepo.findByFiliere(filiere)
                .stream()
                .map(EtudiantMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EtudiantDto> getEtudiantsByEcole(String ecole) {
        return etudiantRepo.findByEcole(ecole)
                .stream()
                .map(EtudiantMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EtudiantDto> getEtudiantsByAnneeEtude(String anneeEtude) {
        return etudiantRepo.findByAnneeEtude(anneeEtude)
                .stream()
                .map(EtudiantMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByEmail(String email) {
        return etudiantRepo.existsByEmail(email);
    }
}
