package Gestion.de.stage.Gestion.de.stage.Service;

import Gestion.de.stage.Gestion.de.stage.Dto.CandidaturDto;
import Gestion.de.stage.Gestion.de.stage.Entity.CandidatureEntity;
import Gestion.de.stage.Gestion.de.stage.Entity.EtudiantEntity;
import Gestion.de.stage.Gestion.de.stage.Entity.OffreDeStageEntity;
import Gestion.de.stage.Gestion.de.stage.Repository.CandidatureRepo;
import Gestion.de.stage.Gestion.de.stage.Repository.EtudiantRepo;
import Gestion.de.stage.Gestion.de.stage.Repository.OffreDeStageRepo;
import Gestion.de.stage.Gestion.de.stage.mapper.CandidatureMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatureServiceImp implements CandidatureService{

    private final CandidatureRepo candidatureRepo;
    private final OffreDeStageRepo offreDeStageRepo;
    private final EtudiantRepo etudiantRepo;

    public CandidatureServiceImp(CandidatureRepo candidatureRepo, OffreDeStageRepo offreDeStageRepo, EtudiantRepo etudiantRepo) {
        this.candidatureRepo = candidatureRepo;
        this.offreDeStageRepo = offreDeStageRepo;
        this.etudiantRepo = etudiantRepo;
    }

    @Override
    public CandidaturDto createCandidature(CandidaturDto candidatureDto) {
        OffreDeStageEntity offreDeStage = offreDeStageRepo.findById(candidatureDto.getOffreDeStageId())
                .orElseThrow(() -> new RuntimeException("Offre de stage non trouvée avec l'ID : " + candidatureDto.getOffreDeStageId()));

        EtudiantEntity etudiant = etudiantRepo.findById(candidatureDto.getEtudiantId())
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'ID : " + candidatureDto.getEtudiantId()));

        if (candidatureRepo.existsByOffreDeStageIdAndEtudiantEntityId(offreDeStage.getId(), etudiant.getId())) {
            throw new RuntimeException("Une candidature existe déjà pour cette offre et cet étudiant.");
        }

        CandidatureEntity candidatureEntity = CandidatureMapper.toEntity(candidatureDto, offreDeStage, etudiant);
        candidatureEntity.setOffreDeStage(offreDeStage);
        candidatureEntity.setEtudiantEntity(etudiant);
        CandidatureEntity savedCandidature = candidatureRepo.save(candidatureEntity);
        return CandidatureMapper.toDto(savedCandidature);
    }

    @Override
    public CandidaturDto getCandidatureById(Long id) {
        CandidatureEntity candidature = candidatureRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature non trouvée avec l'ID : " + id));
        return CandidatureMapper.toDto(candidature);
    }

    @Override
    public CandidaturDto updateCandidature(Long id, CandidaturDto candidatureDto) {
        CandidatureEntity existingCandidature = candidatureRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature non trouvée avec l'ID : " + id));

        // Mise à jour des champs
        existingCandidature.setDateCandidature(candidatureDto.getDateCandidature());
        existingCandidature.setMotivation(candidatureDto.getMotivation());
        existingCandidature.setCvPath(candidatureDto.getCvPath());

        // Mise à jour de l'offre de stage
        if (candidatureDto.getOffreDeStageId() != null) {
            OffreDeStageEntity offreDeStage = offreDeStageRepo.findById(candidatureDto.getOffreDeStageId())
                    .orElseThrow(() -> new RuntimeException("Offre de stage non trouvée avec l'ID : " + candidatureDto.getOffreDeStageId()));
            existingCandidature.setOffreDeStage(offreDeStage);
        }

        // Mise à jour de l'étudiant
        if (candidatureDto.getEtudiantId() != null) {
            EtudiantEntity etudiant = etudiantRepo.findById(candidatureDto.getEtudiantId())
                    .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'ID : " + candidatureDto.getEtudiantId()));
            existingCandidature.setEtudiantEntity(etudiant);
        }

        CandidatureEntity updatedCandidature = candidatureRepo.save(existingCandidature);
        return CandidatureMapper.toDto(updatedCandidature);
    }

    @Override
    public void deleteCandidature(Long id) {
        if (!candidatureRepo.existsById(id)) {
            throw new RuntimeException("Candidature non trouvée avec l'ID : " + id);
        }
        candidatureRepo.deleteById(id);
    }

    @Override
    public List<CandidaturDto> getCandidaturesByOffre(Long offreDeStageId) {
        return candidatureRepo.findByOffreDeStageId(offreDeStageId)
                .stream()
                .map(CandidatureMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CandidaturDto> getCandidaturesByEtudiant(Long etudiantId) {
        return candidatureRepo.findByEtudiantEntityId(etudiantId)
                .stream()
                .map(CandidatureMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CandidaturDto> searchCandidaturesByMotivation(String keyword) {
        return candidatureRepo.findByMotivationContaining(keyword)
                .stream()
                .map(CandidatureMapper::toDto)
                .collect(Collectors.toList());
    }
}
