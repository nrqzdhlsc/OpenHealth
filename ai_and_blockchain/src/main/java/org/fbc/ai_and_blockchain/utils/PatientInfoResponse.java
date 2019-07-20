package org.fbc.ai_and_blockchain.utils;

import java.io.Serializable;
import java.util.List;

public class PatientInfoResponse implements Serializable {
    private String title;
    private List<PatientInfo> patientInfos;

    public PatientInfoResponse(String title, List<PatientInfo> patientInfos) {
        this.title = title;
        this.patientInfos = patientInfos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PatientInfo> getPatientInfos() {
        return patientInfos;
    }

    public void setPatientInfos(List<PatientInfo> patientInfos) {
        this.patientInfos = patientInfos;
    }
}
