package com.example.votingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.votingsystem.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findByState(String state);
}
