package com.example.jupiterPg.service;

import com.cloudinary.Cloudinary;
import com.example.jupiterPg.model.Bed;
import com.example.jupiterPg.model.Resident;
import com.example.jupiterPg.repository.BedRepository;
import com.example.jupiterPg.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final BedRepository bedRepository;
    private final Cloudinary cloudinary;

    public Resident add(Resident resident, MultipartFile idProof) {

        Bed bed = bedRepository.findById(resident.getBedId())
                .orElseThrow(() -> new RuntimeException("Bed not found"));

        if (!"AVAILABLE".equals(bed.getStatus())) {
            throw new RuntimeException("Bed already occupied");
        }

        bed.setStatus("OCCUPIED");
        bedRepository.save(bed);

        if (idProof != null && !idProof.isEmpty()) {
            resident.setIdProofUrl(uploadIdProof(idProof));
        }

        resident.setStatus("ACTIVE");
        resident.setCheckInDate(
                resident.getCheckInDate() != null
                        ? resident.getCheckInDate()
                        : LocalDate.now()
        );

        return residentRepository.save(resident);
    }

    public Resident update(String id, Resident updated, MultipartFile idProof) {

        Resident existing = residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found"));

        if ("CHECKED_OUT".equals(existing.getStatus())) {
            throw new RuntimeException("Cannot edit checked-out resident");
        }

        if (idProof != null && !idProof.isEmpty()) {
            existing.setIdProofUrl(uploadIdProof(idProof));
        }

        existing.setName(updated.getName());
        existing.setMobile(updated.getMobile());
        existing.setEmail(updated.getEmail());
        existing.setRent(updated.getRent());
        existing.setDeposit(updated.getDeposit());
        existing.setExpectedCheckOutDate(updated.getExpectedCheckOutDate());

        return residentRepository.save(existing);
    }

    private String uploadIdProof(MultipartFile file) {
        try {
            Map upload = cloudinary.uploader().upload(
                    file.getBytes(),
                    Map.of("folder", "resident-id-proofs")
            );
            return upload.get("secure_url").toString();
        } catch (Exception e) {
            throw new RuntimeException("ID Proof upload failed");
        }
    }




    public void delete(String id) {

        Resident resident = residentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found"));

        if ("ACTIVE".equals(resident.getStatus())) {
            throw new RuntimeException("Checkout resident before deleting");
        }

        bedRepository.findById(resident.getBedId()).ifPresent(bed -> {
            bed.setStatus("AVAILABLE");
            bedRepository.save(bed);
        });

        residentRepository.deleteById(id);
    }


    public Resident checkout(String residentId, LocalDate checkoutDate) {

        Resident resident = residentRepository.findById(residentId)
                .orElseThrow(() -> new RuntimeException("Resident not found"));

        if ("CHECKED_OUT".equals(resident.getStatus())) {
            throw new RuntimeException("Resident already checked out");
        }

        bedRepository.findById(resident.getBedId()).ifPresent(bed -> {
            bed.setStatus("AVAILABLE");
            bedRepository.save(bed);
        });

        resident.setStatus("CHECKED_OUT");
        resident.setActualCheckOutDate(
                checkoutDate != null ? checkoutDate : LocalDate.now()
        );

        return residentRepository.save(resident);
    }

    public List<Resident> getAll() {
        return residentRepository.findAll();
    }
}
