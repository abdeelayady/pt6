package model.persist;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import models.Paciente;

public class PacienteDAO {

    private static DBConnect dataSource;
    private final Properties queries;
    private static String PROPS_FILE;

    public PacienteDAO(String ruta) throws IOException {
        queries = new Properties();
        PROPS_FILE = ruta + "/paciente_queries.properties";
        queries.load(new FileInputStream(PROPS_FILE));

        dataSource = DBConnect.getInstance();
    }

    public String getQuery(String queryName) {
        return queries.getProperty(queryName);
    }
    /**
     * 
     * @param n
     * @return 
     */
    public ArrayList<Paciente> findAll(int n) {
        ArrayList<Paciente> list = new ArrayList<>();
        try ( Connection conn = dataSource.getConnection();
              Statement st = conn.createStatement(); )
        {    
            ResultSet res = st.executeQuery(getQuery("FIND_ALL"));

            while (res.next()) {
                if (n == 1){
                    Paciente p = new Paciente();
                    p.setIdRegister(res.getInt("id"));
                    p.setEdat(res.getInt("edat"));
                    p.setGrupEdat(res.getString("grupEdat"));
                    p.setPes(res.getInt("pes"));
                    p.setTalla(res.getInt("talla"));
                    p.setImc(res.getDouble("imc"));
                    p.setClassificacio("classificacio");
                    p.setMenarquia(res.getInt("menarquia"));
                    p.setMenopausia(res.getString("menopausia"));
                    p.setTipusMenopausia(res.getString("tipusMenopausia"));
                    
                    list.add(p);
                }
                if (n == 2){
                    Paciente p = new Paciente();
                    p.setGrupEdat(res.getString("grupEdat"));
                    p.setPes(res.getInt("pes"));
                    p.setImc(res.getDouble("imc"));
                    p.setClassificacio("classificacio");
                    list.add(p);
                    

                }
            }

        } catch (SQLException e) {
            list = new ArrayList<>();
        }
        
        return list;
    }
    
     public int insert(Paciente p) {
        int rowsAffected=0;

        try ( Connection conn = dataSource.getConnection();
              PreparedStatement pst = conn.prepareStatement(getQuery("INSERT")); )
        {
            pst.setInt(1, p.getEdat());
            pst.setString(2, p.getGrupEdat());
            pst.setInt(3, p.getPes());
            pst.setInt(4, p.getTalla());
            pst.setDouble(5, p.getImc());
            pst.setString(6, p.getClassificacio());
            pst.setInt(7, p.getMenarquia());
            pst.setString(8, p.getMenopausia());
            pst.setString(9, p.getTipusMenopausia());
            
            rowsAffected = pst.executeUpdate();
        } catch (SQLException e) {
            rowsAffected = 0;
        }

        return rowsAffected;
    }
     
     public ArrayList<Paciente> filterAll(String menopausia, String clasificacion, String tipoMeno) {

        ArrayList<Paciente> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pst = conn.prepareStatement(getQuery("FIND_FULL"));) {
            pst.setString(1, clasificacion);
            pst.setString(2, menopausia);
            pst.setString(3, tipoMeno);

            ResultSet res = pst.executeQuery();
            while (res.next()) {

                Paciente p = new Paciente();
                p.setEdat(res.getInt("edat"));
                p.setGrupEdat(res.getString("grupEdat"));
                p.setPes(res.getInt("pes"));
                p.setTalla(res.getInt("talla"));
                p.setImc(res.getDouble("imc"));
                p.setClassificacio(res.getString("classificacio"));
                p.setMenarquia(res.getInt("menarquia"));
                p.setMenopausia(res.getString("menopausia"));
                p.setTipusMenopausia(res.getString("tipusMenopausia"));
                list.add(p);
            }

        } catch (SQLException e) {
            list = new ArrayList<>();
        }

        return list;
    }
     
     public List<Paciente> filterMenoClasi(String menopausia, String clasificacion) {

        ArrayList<Paciente> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pst = conn.prepareStatement(getQuery("FIND_MC"));) {
            pst.setString(1, clasificacion);
            pst.setString(2, menopausia);

            ResultSet res = pst.executeQuery();
            while (res.next()) {

                Paciente p = new Paciente();
                p.setEdat(res.getInt("edat"));
                p.setGrupEdat(res.getString("grupEdat"));
                p.setPes(res.getInt("pes"));
                p.setTalla(res.getInt("talla"));
                p.setImc(res.getDouble("imc"));
                p.setClassificacio(res.getString("classificacio"));
                p.setMenarquia(res.getInt("menarquia"));
                p.setMenopausia(res.getString("menopausia"));
                p.setTipusMenopausia(res.getString("tipusMenopausia"));
                list.add(p);
            }

        } catch (SQLException e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<Paciente> filterMenoYtipo(String menopausia, String tipoMeno) {

        ArrayList<Paciente> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pst = conn.prepareStatement(getQuery("FIND_MT"));) {
            pst.setString(1, menopausia);
            pst.setString(2, tipoMeno);

            ResultSet res = pst.executeQuery();
            while (res.next()) {

                Paciente p = new Paciente();
                p.setEdat(res.getInt("edat"));
                p.setGrupEdat(res.getString("grupEdat"));
                p.setPes(res.getInt("pes"));
                p.setTalla(res.getInt("talla"));
                p.setImc(res.getDouble("imc"));
                p.setClassificacio(res.getString("classificacio"));
                p.setMenarquia(res.getInt("menarquia"));
                p.setMenopausia(res.getString("menopausia"));
                p.setTipusMenopausia(res.getString("tipusMenopausia"));
                list.add(p);
            }

        } catch (SQLException e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<Paciente> filterClaYtipo(String clasificacion, String tipoMeno) {

        ArrayList<Paciente> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pst = conn.prepareStatement(getQuery("FIND_CT"));) {
            pst.setString(1, clasificacion);
            pst.setString(2, tipoMeno);

            ResultSet res = pst.executeQuery();
            while (res.next()) {

                Paciente p = new Paciente();
                p.setEdat(res.getInt("edat"));
                p.setGrupEdat(res.getString("grupEdat"));
                p.setPes(res.getInt("pes"));
                p.setTalla(res.getInt("talla"));
                p.setImc(res.getDouble("imc"));
                p.setClassificacio(res.getString("classificacio"));
                p.setMenarquia(res.getInt("menarquia"));
                p.setMenopausia(res.getString("menopausia"));
                p.setTipusMenopausia(res.getString("tipusMenopausia"));
                list.add(p);
            }

        } catch (SQLException e) {
            list = new ArrayList<>();
        }

        return list;
    }

    public List<Paciente> filterOne(String campo1,String campo2,String campo3) {

        ArrayList<Paciente> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
                PreparedStatement pst = conn.prepareStatement(getQuery("FIND_ONE"));) {
           
            pst.setString(1, campo1);
            pst.setString(2, campo2);
            pst.setString(3, campo3);

            ResultSet res = pst.executeQuery();
            
            while (res.next()) {

                Paciente p = new Paciente();
                p.setEdat(res.getInt("edat"));
                p.setGrupEdat(res.getString("grupEdat"));
                p.setPes(res.getInt("pes"));
                p.setTalla(res.getInt("talla"));
                p.setImc(res.getDouble("imc"));
                p.setClassificacio(res.getString("classificacio"));
                p.setMenarquia(res.getInt("menarquia"));
                p.setMenopausia(res.getString("menopausia"));
                p.setTipusMenopausia(res.getString("tipusMenopausia"));
                list.add(p);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            list = new ArrayList<>();
        }

        return list;
    }

   
}