package com.example.votingsystem.dto;

public class VoteUpdate {
    private Long candidateId;
    private String candidateName;
    private long voteCount;

    public VoteUpdate() {}

    public VoteUpdate(Long candidateId, String candidateName, long voteCount) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.voteCount = voteCount;
    }

    // Getters and setters

    public Long getCandidateId() {
        return candidateId;
    }
    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
    public String getCandidateName() {
        return candidateName;
    }
    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }
    public long getVoteCount() {
        return voteCount;
    }
    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }
}
