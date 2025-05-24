package com.example.votingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.votingsystem.service.VotingService;

@Controller
public class VotingController {

    @Autowired
    private VotingService votingService;
    
    @PostMapping("/vote")
    public String castVote(@RequestParam("candidateId") Long candidateId,
                           @RequestParam(value = "state", required = false) String state,
                           Model model) {
        try {
            votingService.castVote(candidateId);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        if (state != null && !state.isEmpty()) {
            return "redirect:/candidatesByState?state=" + state;
        }
        return "redirect:/candidates";
    }
    
    // Display overall results.
    @GetMapping("/results")
    public String showResults(Model model) {
        model.addAttribute("results", votingService.getResultsDTO());
        return "results";  // corresponds to results.html
    }
    
    // Display filtered results by state.
    @GetMapping("/resultsByState")
    public String showResultsByState(@RequestParam("state") String state, Model model) {
        model.addAttribute("state", state);
        model.addAttribute("results", votingService.getResultsDTOByState(state));
        return "results";  // returns the same results.html template
    }
}
