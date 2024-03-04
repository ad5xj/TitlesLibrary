package org.ad5xj.mariadb;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.transaction.UserTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("unused")
public class AuthorImplDAO
{
    protected EntityManager em;
    protected DBUtil        db;

    private Author authobj;
    @Resource()
    UserTransaction ut;

	private Integer authid;
    private String firstName;
    private String lastName;
    private String authNote;

    public void Author() {};

    public void Author(Integer i_id, String i_fname, String i_lname, String i_note)
    {
        this.authid     = i_id;
        this.firstName  = i_fname;
        this.lastName   = i_lname;
        this.authNote   = i_note;
    }

    /**
     * @brief Read the list of all the Authors from the database.
     *
     * @return a list with all the Author instances found in the database.
     */
    @SuppressWarnings("null")
	public List<Author> retrieveAll()
    {
        db = new DBUtil();
        ResultSet rs = null;
        List<Author> authors = null;

        String DML = "SELECT authid, firstName, lastName, authNote FROM Author ORDER BY lastName ASC, firstName ASC";
        try
        {
            rs = db.execQuery(DML);
            authors.clear();
            while ( rs.next() )
            {
            	Author author = new Author();
                author.setID(rs.getInt("authid"));
                author.setFirstName(rs.getString("firstName"));
                author.setLastName(rs.getString("lastName"));
                author.setAuthNote(rs.getString("authNote"));
                authors.add(author);
            }
            db.close();
        }
        catch ( SQLException | NullPointerException e)
        {
            System.out.println("List<Author> haa an error:" + e.getMessage());
        }
        db = null;
        return authors;
    }


    @SuppressWarnings("null")
	public boolean validate(int i_id)
    {
        boolean result = false;
        List<Author> authors = null;
		ResultSet rs  = null;

        Author authobj = null;

        authobj = Author.retrieveByID(i_id);

        authors.clear();
        try
        {
            String firstName = authobj.getFirstName();
            String lastName  = authobj.getLastName();
            if ( !lastName.equals("") && !firstName.equals("") )
            {
                result = true;
            }
            else
            {
                result = false;
            }
        }
        catch (Exception e)
        {
            result = false;
        }
        return result;
    }

    //       SETTERS AND GETTERS    //
    public Integer getID()
    {
        try
        {
            return authobj.getAuthID();
        }
        catch (NullPointerException e)
        {
            return -1;
        }
    }
    public String  getFirstName()
    {
        try
        {
            return authobj.getFirstName();
        }
        catch (NullPointerException e)
        {
            return "unknown";
        }
    }

    public String  getLastName()
    {
        try
        {
            return authobj.getLastName();
        }
        catch (NullPointerException e)
        {
            return "unknown";
        }
    }

    public String  getAuthNote()
    {
        try
        {
            return authobj.getAuthNote();
        }
        catch (NullPointerException e)
        {
            return "";
        }
    }

    public void setID(Integer i_id) { authobj.setID(i_id); }
    public void setFirstName(String i_name) { authobj.setFirstName(i_name); }
    public void setLastName(String i_name) { authobj.setLastName(i_name); }
    public void setAuthNote(String i_note) { authobj.setAuthNote(i_note); }
    //       END SETTERS AND GETTERS    //

    public int getLastID()
    {
        int lstid = -1;
        String DML = "SELECT max('authid') as max FROM Author ";
        db = new DBUtil();
        ResultSet rs = null;

        try
        {
            rs = db.execQuery(DML);
            lstid = rs.getInt("max");
            db.close();
        }
        catch ( SQLException | NullPointerException e)
        {
            lstid = -1;
        }
        db = null;
        return lstid;
    }

    /**
     * Update the reference object by setting its property values to match the one
     * existing in the database for the specific instance, identified by the
     * primary key value.
     *
     * @param author  the reference to the User instance to be "loaded" from database.
     */
    public void refreshObject(Author author)
    {
        Author foundAuth = author;
        author.setID(foundAuth.getAuthID());
        author.setFirstName(foundAuth.getFirstName() );
        author.setLastName(foundAuth.getLastName() );
        author.setAuthNote(foundAuth.getAuthNote() );
    }

    /**
     * @brief Retrieve a Author record from the Author table.
     *
     * @param i_id the Author's authid
     * @return authobj the Author object with the given id
     */
    public Author retrieveByID(int i_id)
    {
        db = new DBUtil();
        ResultSet rs = null;

        String DML = "SELECT * FROM Author WHERE authid = " + Integer.toString(i_id);
        Author authobj = new Author();

        try
        {
            rs = db.execQuery(DML);
            authobj.setID(rs.getInt("authid"));
            authobj.setFirstName(rs.getString("firstName"));
            authobj.setLastName(rs.getString("lastName"));
            authobj.setAuthNote(rs.getString("authNote"));
            db.close();
        }
        catch ( SQLException e)
        {
            // will return a null object
        }
        db = null;
        return authobj;
    }

    /**
     * @brief Create and persist a new User instance.
     */
    public boolean add(Author auth)
    {
        boolean result = false;
        String DML  = "";
        Author authobj = auth;
        db  = new DBUtil();

        DML = "INSERT INTO Author (authid, firstName, lastName, authNote) VALUES (" +
                authobj.getAuthID() + "', '" +
                authobj.getFirstName() + "', '" +
                authobj.getLastName() + "', '" +
                authobj.getAuthNote() + "')";

        try
        {
            result = db.execUpdate(DML);
            db.close();
        }
        catch (SQLException e)
        {
            result = false;
            System.out.println("add() had an error: " + e.getMessage());
        }
        db = null;
        return result;
    }

    /**
     * @brief Update an Author instance
     */
    public boolean update(Author auth)
    {
        boolean result = false;
        String DML  = "";
        Author authobj = auth;
        db = new DBUtil();

        DML = "UPDATE Author (authid, firstName, lastName, authNote) VALUES (" +
                authobj.getAuthID() + "', '" +
                authobj.getFirstName() + "', '" +
                authobj.getLastName() + "', '" +
                authobj.getAuthNote() + "')";

        try
        {
            db.execUpdate(DML);
            result = true;
            db.close();
        }
        catch (SQLException e)
        {
            result =  false;
        }
        db = null;
        return result;
    }

    /**
     * @brief Delete an Author instance
     * @param i_id the authid of the Author to delete
     */
    public boolean destroy(int i_id)
    {
        boolean result = false;
        String DML = "";
        db = new DBUtil();

        try
        {
            DML  = "DELETE FROM Author WHERE authID = " + Integer.toString(i_id);
            db.execUpdate(DML);
            db.close();
            result = true;
        }
        catch (Exception e)
        {
            result = false;
        }
        db  = null;
        return result;
    }

    /**
     * @brief Clear all entries from the Author table
     */
    public void clearData()
    {
        String DML = "DELETE FROM Author WHERE authid >= 0";
        DBUtil db = new DBUtil();

        try
        {
            db.execUpdate(DML);
            db.close();
        }
        catch (SQLException e )
        {
            System.out.println("clearData() had an error: " + e.getMessage());
        }
        db = null;
    }

    /**
     * @brief Create test data (rows) in the Author table
     */
    public void createTestData()
    {
        Author authobj = new Author();
        // clear existing books, so no primary key duplicate conflicts appear
        String DML = "INSERT INTO Author(authid, firstName, lastName, authNote) VALUES (0,'TEST','AUTHOR','NONE')";
        try
        {
            db.execSQL(DML);
            db.close();
        }
        catch (SQLException e )
        {
            System.out.println("createTestData() had an error adding data:"+e.getMessage());
        }
        db = null;
    }
} /* end class */