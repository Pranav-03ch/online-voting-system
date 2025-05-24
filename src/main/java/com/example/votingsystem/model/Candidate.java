package com.example.votingsystem.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String party;
    private String state;
    
    // Enhanced fields
    @Column(length = 1000)
    private String bio;
    private String photoUrl;        
    @Column(length = 1000)
    private String manifesto;
    private String socialMediaLink; 
    
    // Cascade deletion for votes (if you want to remove votes when candidate is deleted)
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();
    
    public Candidate() {}

    public Candidate(String name, String party, String state, String bio, String photoUrl, String manifesto, String socialMediaLink) {
        this.name = name;
        this.party = party;
        this.state = state;
        this.bio = bio;
        this.photoUrl = photoUrl;
        this.manifesto = manifesto;
        this.socialMediaLink = socialMediaLink;
    }
    
    // Getters and setters
    // ... (Include getters/setters for all fields, including votes)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getParty() { return party; }
    public void setParty(String party) { this.party = party; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    
    public String getManifesto() { return manifesto; }
    public void setManifesto(String manifesto) { this.manifesto = manifesto; }
    
    public String getSocialMediaLink() { return socialMediaLink; }
    public void setSocialMediaLink(String socialMediaLink) { this.socialMediaLink = socialMediaLink; }
    
    public List<Vote> getVotes() { return votes; }
    public void setVotes(List<Vote> votes) { this.votes = votes; }
}
