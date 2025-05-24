package com.example.votingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.votingsystem.dto.ResultDTO;
import com.example.votingsystem.model.Candidate;
import com.example.votingsystem.repository.CandidateRepository;
import com.example.votingsystem.service.VotingService;

@Controller
public class AdminController {

    @Autowired
    private CandidateRepository candidateRepository;
    
    @Autowired
    private VotingService votingService;
    
    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        List<Candidate> candidates = candidateRepository.findAll();
        List<ResultDTO> results = votingService.getResultsDTO();
        model.addAttribute("candidates", candidates);
        model.addAttribute("results", results);
        return "adminDashboard";  // adminDashboard.html
    }
}
