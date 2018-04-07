package com.zz.model;



/**
 * Created by dell on 2017/10/21.
 */
public class Gz {
    private String riqi;
    private String vlues;

    public Gz(){

    }

    Gz(String riqi,String vlues){
        super();
        this.riqi=riqi;
        this.vlues=vlues;
    }
    public String getRiqi(){
        return riqi;
    }
    public void setRiqi(String riqi){
        this.riqi=riqi;
    }

    public String getVlues(){
        return vlues;
    }
    public void setVlues(String vlues){
        this.vlues=vlues;
    }
}
