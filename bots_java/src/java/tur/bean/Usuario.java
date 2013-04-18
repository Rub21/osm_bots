/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tur.bean;

/**
 *
 * @author ruben
 */
public class Usuario {

    int num_post;
    int user_id;
    String osm_user;
    int num_edit;
    float num_obj_changes;

    public float getNum_obj_changes() {
        return num_obj_changes;
    }

    public void setNum_obj_changes(float num_obj_changes) {
        this.num_obj_changes = num_obj_changes;
    }
    

    public int getNum_post() {
        return num_post;
    }

    public void setNum_post(int num_post) {
        this.num_post = num_post;
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

    public int getNum_edit() {
        return num_edit;
    }

    public void setNum_edit(int num_edit) {
        this.num_edit = num_edit;
    }
}
