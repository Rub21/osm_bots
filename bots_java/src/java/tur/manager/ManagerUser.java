/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tur.manager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import tur.bean.Edicions;

import tur.bean.User;
import tur.dao.DAOUser;
import tur.datasource.BDConnecion;

/**
 *
 * @author ruben
 */
public class ManagerUser {

    DAOUser dAOUser;
    User user;
    Connection cn = null;

    public ManagerUser(BDConnecion connecion) {

        this.cn = connecion.getConnection();
    }

    public List list_idUsers() {
        dAOUser = new DAOUser(cn);
        return dAOUser.list_idUsers();
    }

    public ArrayList<Edicions> list_edition_day(int id) {
        dAOUser = new DAOUser(cn);
        return dAOUser.list_edition_day(id);
    }

    public String find_osm_user(int id) {
        dAOUser = new DAOUser(cn);
        return dAOUser.find_osm_user(id);
    }
}
