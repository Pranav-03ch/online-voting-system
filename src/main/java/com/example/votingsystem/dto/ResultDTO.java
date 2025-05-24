package com.example.votingsystem.dto;

public class ResultDTO {
    private String candidateName;
    private String party;
    private long votes;

    public ResultDTO(String candidateName, String party, long votes) {
        this.candidateName = candidateName;
        this.party = party;
        this.votes = votes;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }
}
