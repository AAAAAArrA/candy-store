package com.example.chocolateshop.services;

import com.example.chocolateshop.models.Chocolate;
import com.example.chocolateshop.models.Image;
import com.example.chocolateshop.repositories.ChocolateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@Slf4j
public class ChocolateService {
    private final ChocolateRepository chocolateRepository;
    public ChocolateService(ChocolateRepository chocolateRepository) {
        this.chocolateRepository = chocolateRepository;
    }

    public void saveChocolate(Chocolate chocolate, MultipartFile file1) throws IOException {
        Image image1;
        Image image2;
        Image image3;
        if(file1.getSize() != 0){
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            chocolate.addImageToChocolate(image1);
        }
//        if(file2.getSize() != 0){
//            image2 = toImageEntity(file2);
//            chocolate.addImageToChocolate(image2);
//        }
//        if(file3.getSize() != 0){
//            image3 = toImageEntity(file3);
//            chocolate.addImageToChocolate(image3);
//        }
        log.info("Saving new product Name {}, Price{}", chocolate.getName(), chocolate.getPrice());
        Chocolate chocolateFromDB = chocolateRepository.save(chocolate);
        chocolateFromDB.setPreviewImageId(chocolateFromDB.getImages().get(0).getId());
        chocolateRepository.save(chocolate);
    }

    private Image toImageEntity(MultipartFile file)throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    // ВЫВОДИТ ВСЕ ШОКОЛАДЫ
    public List<Chocolate>  chocolateList(){
        return chocolateRepository.findAll();
    }


    //УДАЛЯЕТ ШОКОЛАД ПО ID
    public void deleteChocolate(int id){
        chocolateRepository.deleteById(id);
    }

    //ДОСТАЕТ ОДИН ШОКОЛАД ПО ID
    public Chocolate findChocolate(int id){
        return chocolateRepository.findById(id).get();
    }





}
