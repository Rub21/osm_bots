/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tur.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import tur.bean.Edicions;
import tur.bean.Geometry;
import tur.bean.Properties;
import tur.bean.User;
import tur.bean.Usuario;

/**
 *
 * @author ruben
 */
public class DAOUser {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    //Edicion
    Connection conni = null;
    PreparedStatement pstmti = null;
    ResultSet rsi = null;
    //String osm_user = null;

    public DAOUser(Connection conn) {
        this.conn = conn;
        this.conni = conn;
    }

    public List list_idUsers() {
        List list = new LinkedList();
        try {
            String sql = "SELECT user_id , count(*) AS nun_edits FROM osm_changeset GROUP BY user_id ORDER BY nun_edits DESC limit 100;";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int num = 0;
            while (rs.next()) {
                int id = rs.getInt("user_id");
                list.add(id);
            }
            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en en id" + ex);
        }
        return list;
    }

    public ArrayList<Edicions> list_edition_day(int id) {
        ArrayList<Edicions> list = new ArrayList<Edicions>();

        try {
            String sql = "select get_date_by_day(closed_at) as date,\n"
                    + "count(num_changes) as num_edition, sum(num_changes) as num_changes \n"
                    + "from  osm_changeset  where user_id=" + id + "\n"
                    + "GROUP BY get_date_by_day(closed_at)\n"
                    + "ORDER BY get_date_by_day(closed_at) DESC;";
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int num = 0;
            while (rs.next()) {
                Edicions edicion = new Edicions();
                edicion.setDate(rs.getString("date"));
                edicion.setNum_changeset(rs.getInt("num_edition"));
                edicion.setNum_obj_changes(rs.getInt("num_changes"));
                list.add(edicion);
            }

            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en en Edicion" + ex);
        }
        return list;

    }

    public String find_osm_user(int id) {
        String osm_user = "";
        try {
            String sql = "SELECT osm_user from osm_changeset where  user_id=" + id + " limit 1;";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                osm_user = rs.getString("osm_user");
            }

            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en en Edicion" + ex);
        }
        return osm_user;

    }

    public Usuario find_Usuario(int id) {
  
        Usuario usuario = new Usuario();

        try {
            String sql = "SELECT osm_user , count(num_changes) as num_changeset, sum(num_changes) AS obj_changes FROM osm_changeset where user_id=" + id + " GROUP BY osm_user;";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                usuario.setOsm_user(rs.getString("osm_user"));
                usuario.setNum_edit(rs.getInt("num_changeset"));
                usuario.setNum_obj_changes(rs.getInt("obj_changes"));

            }

            pstmt.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error en en Edicion" + ex);
        }
        return usuario;

    }
}
