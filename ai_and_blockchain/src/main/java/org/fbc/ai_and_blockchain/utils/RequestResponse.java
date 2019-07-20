package org.fbc.ai_and_blockchain.utils;

import java.io.Serializable;
import java.util.List;

public class RequestResponse implements Serializable {
    private String title;
    private List<PatientRequest> requests;

    public RequestResponse(String title, List<PatientRequest> requests) {
        this.title = title;
        this.requests = requests;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PatientRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<PatientRequest> requests) {
        this.requests = requests;
    }
}
