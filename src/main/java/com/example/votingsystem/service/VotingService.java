package com.example.votingsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.votingsystem.dto.ResultDTO;
import com.example.votingsystem.model.Candidate;
import com.example.votingsystem.model.Vote;
import com.example.votingsystem.repository.CandidateRepository;
import com.example.votingsystem.repository.VoteRepository;

@Service
public class VotingService {

    @Autowired
    private CandidateRepository candidateRepository;
    
    @Autowired
    private VoteRepository voteRepository;
    
    /**
     * Casts a vote for the candidate with the given ID.
     * The vote is saved to the database without broadcasting any updates.
     *
     * @param candidateId the ID of the candidate receiving the vote
     * @return the saved Vote object
     * @throws Exception if the candidate is not found
     */
    public Vote castVote(Long candidateId) throws Exception {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new Exception("Candidate not found"));
        Vote vote = new Vote(candidate);
        Vote savedVote = voteRepository.save(vote);
        return savedVote;
    }
    
    /**
     * Retrieves overall voting results as a list of ResultDTOs.
     *
     * @return a list of ResultDTO objects representing the vote counts for all candidates
     */
    public List<ResultDTO> getResultsDTO() {
        List<Candidate> candidates = candidateRepository.findAll();
        List<ResultDTO> results = new ArrayList<>();
        for (Candidate candidate : candidates) {
            long count = voteRepository.countByCandidate(candidate);
            results.add(new ResultDTO(candidate.getName(), candidate.getParty(), count));
        }
        return results;
    }
    
    /**
     * Retrieves voting results filtered by state as a list of ResultDTOs.
     *
     * @param state the state to filter candidates by
     * @return a list of ResultDTO objects for candidates in the given state
     */
    public List<ResultDTO> getResultsDTOByState(String state) {
        List<Candidate> candidates = candidateRepository.findByState(state);
        List<ResultDTO> results = new ArrayList<>();
        for (Candidate candidate : candidates) {
            long count = voteRepository.countByCandidate(candidate);
            results.add(new ResultDTO(candidate.getName(), candidate.getParty(), count));
        }
        return results;
    }
}
