package com.example.Digital.Art.Design.Rashu.Service;

import com.example.Digital.Art.Design.Rashu.EntityClasses.Rashu_Design;
import com.example.Digital.Art.Design.Rashu.Repository.Rashu_DesignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.apache.commons.codec.binary.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class DesignService {

    @Autowired
    private Rashu_DesignRepository designRepository;
    public List<Rashu_Design> findAllDesigns() {
        // Implementation to retrieve all designs
        return designRepository.findAll(); // Assuming you are using Spring Data JPA
    }
    public byte[] getImageByDesignerId(Long designer_id) {
        Optional<Rashu_Design> designOptional = designRepository.findByDesignerId(designer_id);

        if (designOptional.isPresent()) {
            Rashu_Design design = designOptional.get();
            return design.getContent(); // Assuming 'Content' is your image column
        }

        return null; // or throw an exception or handle as needed
    }

    public List<Rashu_Design> getDesignsByDesignerId(Long designerId) {
        return designRepository.findAllByDesignerId(designerId);
    }
}
