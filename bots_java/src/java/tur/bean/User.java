package tur.bean;


import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ruben
 */
public class User {

   String type= "FeatureCollection";
   
    int num_pos;
    int user_id;
    String osm_user;
    ArrayList<Edicions>  editions ;
  
    
    
    public int getNum_pos() {
        return num_pos;
    }

    public void setNum_pos(int num_pos) {
        this.num_pos = num_pos;
    }
   
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getOsm_user() {
        return osm_user;
    }

    public void setOsm_user(String osm_user) {
        this.osm_user = osm_user;
    }

    public ArrayList<Edicions> getEditions() {
        return editions;
    }

    public void setEditions(ArrayList<Edicions> editions) {
        this.editions = editions;
    }
    
    
}
