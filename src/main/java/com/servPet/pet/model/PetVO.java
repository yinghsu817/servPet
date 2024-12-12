package com.servPet.pet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;



@Entity
@Table(name = "PET")
public class PetVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PET_ID")
    private Integer petId;

    @Column(name = "MEB_ID", nullable = false)
    private Integer mebId;

    @Column(name = "PET_NAME", nullable = false, length = 10)
    private String petName;

    @Column(name = "PET_TYPE", nullable = false, length = 10)
    private String petType;

    @Lob
    @Column(name = "PET_IMG")
    private byte[] petImage;

    // Getters and Setters
    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public Integer getMebId() {
        return mebId;
    }

    public void setMebId(Integer mebId) {
        this.mebId = mebId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public byte[] getPetImage() {
        return petImage;
    }

    public void setPetImage(byte[] petImage) {
        this.petImage = petImage;
    }
}
