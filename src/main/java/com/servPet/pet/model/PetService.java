package com.servPet.pet.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;

	// 根據會員 ID 查詢寵物(改方法名
	public List<PetVO> getPetsByMebId(Integer mebId) {
		return petRepository.findPetsByMebId(mebId);
	}

	// 新增寵物
	public PetVO addPet(PetVO pet) {
		return petRepository.save(pet);
	}

	// 查詢所有寵物
	public List<PetVO> getAllPets() {
		return petRepository.findAll();
	}

	// 根據 ID 查詢寵物
	public PetVO getPetById(Integer petId) {
		return petRepository.findById(petId).orElse(null);
	}

	// 更新寵物資料
	public PetVO updatePet(PetVO pet) {
		return petRepository.save(pet);
	}

	// 刪除寵物
	public void deletePet(Integer petId) {
		petRepository.deleteById(petId);
	}
}
