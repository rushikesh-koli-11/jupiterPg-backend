package com.example.jupiterPg.service;

import com.cloudinary.Cloudinary;
import com.example.jupiterPg.model.Pg;
import com.example.jupiterPg.repository.PgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PgService {

    private final PgRepository pgRepository;
    private final Cloudinary cloudinary;


    public Pg create(Pg pg, MultipartFile[] images) {
        uploadImages(pg, images);
        return pgRepository.save(pg);
    }


    public Pg update(String id, Pg incomingPg, MultipartFile[] images) {

        Pg existing = pgRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PG not found"));


        existing.setName(incomingPg.getName());
        existing.setLocation(incomingPg.getLocation());
        existing.setTotalFloors(incomingPg.getTotalFloors());

        List<String> finalImages = new ArrayList<>();

        if (incomingPg.getImages() != null) {
            finalImages.addAll(incomingPg.getImages());
        }

        if (images != null && images.length > 0) {
            for (MultipartFile file : images) {
                try {
                    Map upload = cloudinary.uploader().upload(
                            file.getBytes(),
                            Map.of("folder", "pgs")
                    );
                    finalImages.add(upload.get("secure_url").toString());
                } catch (Exception e) {
                    throw new RuntimeException("Image upload failed");
                }
            }
        }

        existing.setImages(finalImages);

        return pgRepository.save(existing);
    }



    public List<Pg> getAll() {
        return pgRepository.findAll();
    }


    public void delete(String id) {
        pgRepository.deleteById(id);
    }


    private void uploadImages(Pg pg, MultipartFile[] images) {
        if (images == null || images.length == 0) return;

        List<String> uploadedImages = new ArrayList<>();

        for (MultipartFile file : images) {
            try {
                Map upload = cloudinary.uploader().upload(
                        file.getBytes(),
                        Map.of("folder", "pgs")
                );
                uploadedImages.add(upload.get("secure_url").toString());
            } catch (Exception e) {
                throw new RuntimeException("Image upload failed");
            }
        }

        pg.setImages(uploadedImages);
    }
}
