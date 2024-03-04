package org.ad5xj.mariadb;

import jakarta.persistence.*;
import jakarta.transaction.UserTransaction;

import java.util.List;
import java.util.Objects;

/**
 * @brief Data definition of Author data object
 * @ingroup MYSQL
 * @author ken
 *
 * @details This is the data bean of the Author data object in the Author table.
 */
@Entity @Table(name = "Author")
public class Author
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authid")
    @Id private Integer authid;
    @Basic @Column(name = "firstName", nullable = false)
    private String firstName;
    @Basic @Column(name = "lastName")
    private String lastName;
    @Basic @Column(name = "authNote")
    private String authNote;

    public Author() {

    }  // default constructor no parameters

    public Author( Integer i_id, String i_fname, String i_lname, String i_note )
    { // with required parameters
        this.authid     = i_id;
        this.firstName  = i_fname;
        this.lastName   = i_lname;
        this.authNote   = i_note;
    }


    //       SETTERS AND GETTERS    //
    public Integer getAuthID()       { return authid; }
    public String  getFirstName() { return firstName; }
    public String  getLastName()  { return lastName; }
    public String  getAuthNote()      { return authNote; }

    public void setID(Integer i_id)          { authid = i_id; }
    public void setFirstName(String i_fname) { firstName = i_fname; }
    public void setLastName(String i_lname)     { lastName = i_lname; }
    public void setAuthNote(String i_note) { authNote = i_note; }
    //       END SETTERS AND GETTERS    //

    /**
     * Create a human-readable serialization.
     */
    public String objectToString() {
        return "{ id: '" + Integer.toString(this.authid) +
                "', name:'" + this.lastName + ", " + this.firstName +
                ", note: " + this.authNote + "}";
    }

    /**
     * @brief Clear all entries from the User table
     *
     * @param em  reference to the entity manager
     * @param ut  reference to the user transaction
     * @throws Exception
     */
    public static void clearData( EntityManager em, UserTransaction ut) throws Exception {
        ut.begin();
        Query deleteQuery = em.createQuery( "DELETE FROM Author ");
        deleteQuery.executeUpdate();
        ut.commit();
    }

    /**
     * @brief Retrieve all Author records from the Author table.
     *
     * @return the list of all Author records
     */
    public static List<Author> retrieveAll() {
        return null;
    }
    public static Author retrieveByID(int i_id)  { return null; }
    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( o == null || getClass() != o.getClass() )
        {
            return false;
        }
        Author that = (Author) o;
        return authid == that.authid && Objects.equals(firstName, that.firstName) && Objects.equals(
                lastName, that.lastName) && Objects.equals(authNote, that.authNote);
    }

    @Override
    public int hashCode( )
    {
        return Objects.hash(authid, firstName, lastName, authNote);
    }
}; /* end class */