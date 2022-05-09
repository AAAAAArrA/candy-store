package com.example.chocolateshop.services;

import com.example.chocolateshop.models.Chocolate;
import com.example.chocolateshop.repositories.ChocolateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;


@Service
@Slf4j
public class ChocolateService {
    private final ChocolateRepository chocolateRepository;
    public ChocolateService(ChocolateRepository chocolateRepository) {

        this.chocolateRepository = chocolateRepository;
    }

    public Chocolate saveProductToBD(Chocolate chocolate, MultipartFile file) throws IOException {
        chocolate.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        return chocolateRepository.save(chocolate);
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
