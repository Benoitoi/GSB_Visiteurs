package modele.dao;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.FileReader;

/**
 * Classe fournit un objet de connexion JDBC
 *
 * @author nbourgeois
 * @version 2 22 novembre 2013
 */
public class Jdbc {

    // Instance de l'objet Jdbc
    private static Jdbc jdbc = null;
    // Paramètre de la connexion
    private String piloteJdbc = "";
    private String protocoleJdbc = "";
    private String serveurBd = "";
    private String nomBd = "";
    private String loginSgbd = "";
    private String mdpSgbd = "";
    // Connexion
    private Connection connexion = null; // java.sql.Connection

    private Jdbc() {
    }

    /**
     * @param pilote : classe du pilote Jdbc
     * @param protocole : préfixe l'URL du serveur ; dépend du type de SGBD
     * @param serveur : adresse du serveur + port (fini par un /, sauf pour
     * Oracle ; BD pour embarquée : chemin accès répertoire )
     * @param base : nom de la BD ou du DSN pour ODBC
     * @param login : utilisateur autorisé du SGBD (ou schéma Oracle)
     * @param mdp : son mot de passe
     */
    private Jdbc(String pilote, String protocole, String serveur, String base, String login, String mdp) {
        this.piloteJdbc = pilote;
        this.protocoleJdbc = protocole;
        this.serveurBd = serveur;
        this.nomBd = base;
        this.loginSgbd = login;
        this.mdpSgbd = mdp;
    }

    /**
     *
     * @return
     */
    public static Jdbc creer() {
        Properties propertiesJdbc = FileReader.getBddProperties();
        jdbc = new Jdbc(propertiesJdbc.getProperty("pilote"), propertiesJdbc.getProperty("protocole"), propertiesJdbc.getProperty("url"), propertiesJdbc.getProperty("base"), propertiesJdbc.getProperty("utilisateur"), propertiesJdbc.getProperty("mdp"));
        try {
            jdbc.connecter();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jdbc;
    }

    /**
     *
     * @return
     */
    public static Jdbc getInstance() {
        if(jdbc == null){
            creer();
        }
        return jdbc;
    }

    /**
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void connecter() throws ClassNotFoundException, SQLException {
        DriverManager.setLoginTimeout(3);//temps d'attente de 3 seconde au lieu de 10 par défaut avant d'afficher echec de la connexion
        Class.forName(this.getPiloteJdbc());
        setConnexion(DriverManager.getConnection(this.getProtocoleJdbc() + this.getServeurBd() + this.getNomBd(), this.getLoginSgbd(), this.getMdpSgbd()));
        getConnexion().setAutoCommit(true);
    }

    /**
     *
     * @throws SQLException
     */
    public void deconnecter() throws SQLException {
        getConnexion().close();
    }

    /**
     *
     * @param uneDate
     * @return
     */
    public static java.sql.Date utilDateToSqlDate(java.util.Date uneDate) {
        return (new java.sql.Date(uneDate.getTime()));
    }

    /**
     * ************************************* *
     * ACCESSEURS * **************************************
     * @return 
     */
    public String getPiloteJdbc() {
        return piloteJdbc;
    }

    /**
     *
     * @param piloteJdbc
     */
    public void setPiloteJdbc(String piloteJdbc) {
        this.piloteJdbc = piloteJdbc;
    }

    /**
     * @return the protocoleJdbc
     */
    public String getProtocoleJdbc() {
        return protocoleJdbc;
    }

    /**
     * @param protocoleJdbc the protocoleJdbc to set
     */
    public void setProtocoleJdbc(String protocoleJdbc) {
        this.protocoleJdbc = protocoleJdbc;
    }

    /**
     *
     * @return
     */
    public String getServeurBd() {
        return serveurBd;
    }

    /**
     *
     * @param serveurBd
     */
    public void setServeurBd(String serveurBd) {
        this.serveurBd = serveurBd;
    }

    /**
     *
     * @return
     */
    public String getNomBd() {
        return nomBd;
    }

    /**
     *
     * @param nomBd
     */
    public void setNomBd(String nomBd) {
        this.nomBd = nomBd;
    }

    /**
     *
     * @return
     */
    public String getLoginSgbd() {
        return loginSgbd;
    }

    /**
     *
     * @param loginSgbd
     */
    public void setLoginSgbd(String loginSgbd) {
        this.loginSgbd = loginSgbd;
    }

    /**
     *
     * @return
     */
    public String getMdpSgbd() {
        return mdpSgbd;
    }

    /**
     *
     * @param mdpSgbd
     */
    public void setMdpSgbd(String mdpSgbd) {
        this.mdpSgbd = mdpSgbd;
    }

    /**
     *
     * @return
     */
    public Connection getConnexion() {
        return connexion;
    }

    /**
     *
     * @param connexion
     */
    public void setConnexion(Connection connexion) {
        this.connexion = connexion;
    }
}
