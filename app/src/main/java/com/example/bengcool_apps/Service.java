package com.example.bengcool_apps;

public class Service {

    public String emailCust;
    public String companyName;
    public String serviceType;


    public Service(String email, String name, String type){
        this.emailCust = email;
        this.companyName = name;
        this.serviceType = type;
    }

    public String getEmailCust() {
        return emailCust;
    }

    public void setEmailCust(String emailCust) {
        this.emailCust = emailCust;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
