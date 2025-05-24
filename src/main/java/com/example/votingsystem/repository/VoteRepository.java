package com.example.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.votingsystem.model.Candidate;
import com.example.votingsystem.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    long countByCandidate(Candidate candidate);
}
