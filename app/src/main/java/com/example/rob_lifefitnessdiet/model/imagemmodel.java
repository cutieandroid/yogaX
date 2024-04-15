package com.example.rob_lifefitnessdiet.model;

public class imagemmodel {

    String imageurl;
    String Content;

    String step1;
    String step2;
    String step3;





    public imagemmodel(String imageurl, String content) {
        this.imageurl = imageurl;
        this.Content = content;

    }

    public imagemmodel(){

    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getStep1() {
        return step1;
    }

    public void setStep1(String step1) {
        this.step1 = step1;
    }

    public String getStep2() {
        return step2;
    }

    public void setStep2(String step2) {
        this.step2 = step2;
    }

    public String getStep3() {
        return step3;
    }

    public void setStep3(String step3) {
        this.step3 = step3;
    }
}
