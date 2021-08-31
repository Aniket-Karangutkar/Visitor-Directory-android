package com.example.application1.Visitor;

public class VisitorDetails {
    private String visitorName, visitorDate, visitorImg;
    public VisitorDetails (String vistiorName, String visitorImg, String visitorDate){

        this.visitorName = vistiorName;
        this.visitorImg = visitorImg;
        this.visitorDate = visitorDate;

    }

    public String getVisitorName(){ return visitorName; }

    public void setVisitorName(String visitorName){ this.visitorName =  visitorName; }

    public String getVisitorImg(){ return visitorImg; }

    public void setVisitorImg(String visitorImg){ this.visitorImg =  visitorImg; }

    public String getVisitorDate(){ return visitorDate; }

    public void setVisitorDate(){ this.visitorDate =  visitorDate; }

}
