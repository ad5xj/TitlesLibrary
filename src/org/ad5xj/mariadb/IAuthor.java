package org.ad5xj.mariadb;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.ad5xj.mariadb.Author;

import java.util.List;

public interface IAuthor
{
    @Id
    @GeneratedValue
    Integer authid = -1;
    String firstName = "";
    String lastName = "";
    String authNote = "";

    void Author();
    void Author(Integer i_id, String i_fname, String i_lname, String i_note);

    //       SETTERS AND GETTERS    //
    public Integer authID();
    public String  getFirstName();
    public String  getLsstName();
    public String  getPasswd();

    public void setID(Integer i_id);
    public void setFirstName(String i_fname);
    public void setLastName(String i_lname);
    public void setAuthNote(String i_note);
    //       END SETTERS AND GETTERS    //

    abstract boolean validate(String i_login);
    abstract boolean add( Author authobj);
    abstract boolean update( Author authobj);
    abstract boolean destroy(int i_id);

    abstract Author retrieveByID(int i_id);
    abstract Author retriveByLastName(String i_lname);
    abstract Author retriveByName(String i_lname, String i_fname);
    abstract List<Author> retrieveAll();
}
