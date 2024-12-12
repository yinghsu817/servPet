package com.servPet.pet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.servPet.pet.model.PetService;
import com.servPet.pet.model.PetVO;

@Controller
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    // 新增寵物
    @PostMapping
    public PetVO addPet(@RequestBody PetVO pet) {
        return petService.addPet(pet);
    }

    // 查詢所有寵物
    @GetMapping
    public List<PetVO> getAllPets() {
        return petService.getAllPets();
    }

    // 根據寵物 ID 查詢
    @GetMapping("/{id}")
    public PetVO getPetById(@PathVariable("id") Integer petId) {
        return petService.getPetById(petId);
    }

    // 根據會員 ID 查詢寵物
    @GetMapping("/member/{mebId}")
    public List<PetVO> getPetsByMemberId(@PathVariable("mebId") Integer mebId) {
        return petService.getPetsByMebId(mebId);
    }

    // 更新寵物資料
    @PutMapping("/{id}")
    public PetVO updatePet(@PathVariable("id") Integer petId, @RequestBody PetVO pet) {
        pet.setPetId(petId);
        return petService.updatePet(pet);
    }

    // 刪除寵物
    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable("id") Integer petId) {
        petService.deletePet(petId);
    }
}
