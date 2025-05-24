package com.example.votingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize; // For method-level security
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.votingsystem.model.Candidate;
import com.example.votingsystem.repository.CandidateRepository;

@Controller
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;
    
    // Display the state selection page.
    @GetMapping("/selectState")
    public String selectState(Model model) {
        List<String> states = List.of("Telangana", "Andhra Pradesh");
        model.addAttribute("states", states);
        return "selectState";  // maps to selectState.html
    }
    
    // ***** Only ADMIN users can add candidates *****
    // Display the Add Candidate form (GET) - restricted to ADMIN.
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/candidate/add")
    public String showAddCandidateForm(@RequestParam(value = "state", required = false) String state,
                                       Model model) {
        Candidate candidate = new Candidate();
        if (state != null && !state.isEmpty()) {
            candidate.setState(state);
        }
        model.addAttribute("candidate", candidate);
        return "addCandidate";  // maps to addCandidate.html
    }
    
    // Process the Add Candidate form (POST) - restricted to ADMIN.
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/candidate/add")
    public String addCandidate(@ModelAttribute Candidate candidate) {
        candidateRepository.save(candidate);
        return "redirect:/candidatesByState?state=" + candidate.getState();
    }
    
    // List candidates filtered by state.
    @GetMapping("/candidatesByState")
    public String listCandidatesByState(@RequestParam("state") String state, Model model) {
        List<Candidate> candidates = candidateRepository.findByState(state);
        model.addAttribute("state", state);
        model.addAttribute("candidates", candidates);
        return "candidatesByState";  // maps to candidatesByState.html
    }
    
    // List all candidates (with optional filtering by state).
    @GetMapping("/candidates")
    public String listAllCandidates(@RequestParam(value = "state", required = false) String state, Model model) {
        List<Candidate> candidates;
        if (state != null && !state.isEmpty()) {
            candidates = candidateRepository.findByState(state);
            model.addAttribute("state", state);
        } else {
            candidates = candidateRepository.findAll();
        }
        model.addAttribute("candidates", candidates);
        return "candidates";  // maps to candidates.html
    }
    
    // Delete candidate (restricted to ADMIN users).
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/candidate/delete")
    public String deleteCandidate(@RequestParam("candidateId") Long candidateId,
                                  @RequestParam(value = "state", required = false) String state) {
        candidateRepository.deleteById(candidateId);
        if (state != null && !state.isEmpty()) {
            return "redirect:/candidatesByState?state=" + state;
        }
        return "redirect:/candidates";
    }
}
