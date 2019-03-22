package model.persist;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import models.Paciente;

import models.User;

public class UsersDAO {

    private static DBConnect dataSource;
    private final Properties queries;
    private static String PROPS_FILE;

    public UsersDAO(String ruta) throws IOException {
        queries = new Properties();
        PROPS_FILE = ruta + "/users_queries.properties";
        queries.load(new FileInputStream(PROPS_FILE));

        dataSource = DBConnect.getInstance();
    }

    public String getQuery(String queryName) {
        return queries.getProperty(queryName);
    }

    //retorna un boleà en funció si l'usuari hi és o no a l'aplicació   
    public String findOne(User u) {
        String role = "";
        try ( Connection conn = dataSource.getConnection();
              PreparedStatement st = conn.prepareStatement(getQuery("FIND_ONE")); )
        {
            st.setString(1, u.getUsername());
            st.setString(2, u.getPassword());
            ResultSet res = st.executeQuery();
            //comprpvar si rol esta vacio
            while (res.next()) {
                u.setRole(res.getString("role"));
            }
            role = u.getRole();
        } catch (SQLException e) {
            role = "";//si llega aqui el role esta vacio y el metodo devuelve
        }
        
        return role;
    }

    public boolean findName(String username) {
        boolean flag = false;
        
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pst = conn.prepareStatement(getQuery("FIND_NAME"));) {
            pst.setString(1, username);

            ResultSet res = pst.executeQuery();
            while (res.next()) {
                flag = true;
            }

        } catch (SQLException e) {
           flag = false;
        }
        return flag;
    }

    public int addUser(String username, String password) {
        int rowsAffected = 0;

        try (Connection conn = dataSource.getConnection();
                PreparedStatement pst = conn.prepareStatement(getQuery("INSERT"));) {

            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, "basic");


            rowsAffected = pst.executeUpdate();

        } catch (SQLException e) {
            rowsAffected = 0;
        }
        return rowsAffected;
    }
   
}