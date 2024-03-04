package org.ad5xj.mariadb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TitleImplDAO implements ITitle
{
    private DBUtil db;

    private Title titleobj;

    private int titleid;
    private int authKey;
    private int mediaKey;
    private int loanKey;
	private String title;
    private String bookNote;

    public void Title() { }  // no parameter object for overload

    public void Title(int i_id, int i_auth, int i_media, int i_loan, String i_title, String i_note)
    { // full parameter overload object
        this.titleid    = i_id;
        this.authKey    = i_auth;
        this.mediaKey   = i_media;
        this.loanKey    = i_loan;
        this.title      = i_title;
        this.bookNote   = i_note;
    }

    //       SETTERS AND GETTERS    //
    public int getID()
    {
        try
        {
            return titleobj.getID();
        }
        catch (NullPointerException e)
        {
            return -1;
        }
    }
    public int getAuthKey()
    {
        try
        {
            return titleobj.getAuthKey();
        }
        catch (NullPointerException e)
        {
            return -1;
        }
    }

    public int getMediaKey()
    {
        try
        {
            return titleobj.getMediaKey();
        }
        catch (NullPointerException e)
        {
            return -1;
        }
    }

    public int getLoanKey()
    {
        try
        {
            return titleobj.getLoanKey();
        }
        catch (NullPointerException e)
        {
            return -1;
        }
    }

    public String  getTitle()
    {
        try
        {
            return titleobj.getTitle();
        }
        catch (NullPointerException e)
        {
            return "unknown";
        }
    }

    public String  getBookNote()
    {
        try
        {
            return titleobj.getBookNote();
        }
        catch (NullPointerException e)
        {
            return "";
        }
    }

    public void setID(int i_id)            { titleobj.setID(i_id); }
    public void setAuthKey(int i_auth)     { titleobj.setAuthKey(i_auth); }
    public void setMediaKey(int i_media)   { titleobj.setMediaKey(i_media); }
    public void setLoanKey(int i_loan)     { titleobj.setLoanKey(i_loan); }
    public void setTitle(String i_title)   { titleobj.setTitle(i_title); }
    public void setBookNote(String i_note) { titleobj.setBookNote(i_note); }
    //       END SETTERS AND GETTERS    //

    // LOCAL CRUD METHODS //
    /**
     * Create and persist a new Title instance.
     * @param titleobj local instance of Title object
     * @return a string representing the view name to display after finishing the execution of this method.
     */
    @Override
    public boolean add(Title titleobj)
    {
        boolean result = false;
        String DML = "";
        db = new DBUtil();

        DML = "INSERT INTO Title (titleid, authKey, mediaKey, loanKey, title, bookNote) VALUES (" +
                titleobj.getID() + "," +
                titleobj.getAuthKey() + "," +
                titleobj.getMediaKey() + "," +
                titleobj.getLoanKey() + ",'" +
                titleobj.getTitle() + "','" +
                titleobj.getBookNote() + "')";
        try
        {
            result = db.execUpdate(DML);
            db.close();
        }
        catch (Exception e)
        {
            result = false;
        }
        db = null;
        return result;
    }

    /**
     * @brief Update a Title instance
     * @param titleobj  reference to the Title table object
     */
    @Override
    public boolean update(Title titleobj)
    {
        boolean result = false;
        String DML  = "";
        db = new DBUtil();

        /* form DML from values in incoming object using MariaDB SQL syntax */
        DML = "UPDATE Title SET authKey = " +
                titleobj.getAuthKey() +
              " mediaKey = " +
                titleobj.getMediaKey() + " " +
              " loanKey = " +
                titleobj.getLoanKey() + " " +
              "title = '" + titleobj.getTitle() + "' " +
              "bookNote = '" + titleobj.getBookNote() + "'  " +
              "WHERE titleid = " + titleobj.getID();


        try
        {
            result = db.execUpdate(DML);
            db.close();
        }
        catch (Exception e)
        {
            result =  false;
        }
        db = null;
        return result;
    }

    /**
     * @brief Delete a Title instance from the database
     * @param i_id the titleid of the Tilte record to delete
     */
    @Override
    public boolean destroy(int i_id)
    {
        boolean result = false;
        Title titleobj = new Title();
        db = new DBUtil();

        String DML = "DELETE FROM Title WHERE titleid = " + i_id;

        try
        {
            result = db.execUpdate(DML);
            db.close();
        }
        catch (Exception e)
        {
            result = false;
        }
        db = null;
        return result;
    }
    // END CRUD METHODS //

    @Override
    public String objectToString()
    {
        String strTitle = "ID: " + Integer.toString(titleobj.getID()) + "Title: " + titleobj.getTitle() + "Note: " + titleobj.getBookNote();
        return strTitle;
    }

    /**
     * Read the list of all the titles from the database.
     *
     * @return a list with all the Title instance found in the database.
     */
    public List<Title> getTitles()
    {
        return retrieveAll();
    }

    @Override
    public int getLastID()
    {
        int lstid = -1;
        String DML = "SELECT max('titleid') as max FROM Title ";
        db = new DBUtil();
        ResultSet rs = null;

        try
        {
            rs = db.execQuery(DML);
            lstid = rs.getInt("max");
        }
        catch (SQLException | NullPointerException e)
        {
            return - 1;
        }
        return lstid;
    }


    /**
     * Update the reference object by setting its property values to match the one
     * existing in the database for the specific instance, identified by the
     * primary key value.
     *
     * @param titleobj  the reference to the User instance to be "loaded" from database.
     */
    public void refreshObject(Title titleobj)
    {
        int i_id = titleobj.getID();
        Title foundTitle = retrieveByID(i_id );
        try
        {
            titleobj.setID(foundTitle.getID());
            titleobj.setAuthKey(foundTitle.getAuthKey());
            titleobj.setTitle(foundTitle.getTitle());
            titleobj.setBookNote(foundTitle.getBookNote());
        }
        catch ( NullPointerException e )
        {
            System.out.println("refreshObj() had a null pointer error..."+e.getMessage());
        }
    }

    /**
     * @brief Retrieve all Title records from the Title table.
     *
     * @return the list of all Title records
     */
    public List<Title> retrieveAll()
    {
        db = new DBUtil();
        ResultSet rs = null;
        List<Title> titles = null;

        String DML = "SELECT titleid, authKey, mediaKey, loanKey, title, bookNote FROM Title ORDER BY Title.title ASC";
        rs = db.execQuery(DML);
        try
        {
            while ( (rs != null) && rs.next() )
            {
                int    i_id    = rs.getInt("titleid");
                int    i_auth  = rs.getInt("authKey");
                int    i_media = rs.getInt("mediaKey");
                int    i_loan  = rs.getInt("loanKey");
                String i_title = rs.getString("title");
                String i_note  = rs.getString("bookNote");
                Title  tobj    = new Title(i_id, i_auth, i_media, i_loan, i_title, i_note);
                try
                {
                    titles.add(tobj);
                }
                catch ( NullPointerException e )
                {
                    System.out.println("retrieveAll() had a null pointer..." + e.getMessage());
                }
            }
            db.close();
        }
        catch ( SQLException | NullPointerException e)
        {
            // do nothing
        }
        db = null;
        return titles;
    }

    /**
     * @brief Retrieve a single Title record from the Title table.
     *
     * @param i_id the Title's titleid
     * @return titleobj the Title object with the given id
     */
    @Override
    public Title retrieveByID(int i_id)
    {
        db = new DBUtil();
        ResultSet rs = null;

        String DML = "SELECT * FROM Title WHERE titleid = " + i_id;
        titleobj = new Title();

        try
        {
            rs = db.execQuery(DML);
            titleobj.setID(rs.getInt("titleid"));
            titleobj.setAuthKey(rs.getInt("authKey"));
            titleobj.setMediaKey(rs.getInt("mediaKey"));
            titleobj.setLoanKey(rs.getInt("loanKey"));
            titleobj.setTitle(rs.getString("title"));
            titleobj.setBookNote(rs.getString("bookNote"));
            db.close();
            return titleobj;
        }
        catch ( SQLException e)
        {
            return null;
        }
    }

    /**
     * @brief Retrieve a Title record from the Title table given the partial title value.
     *
     * @param strtitle the Title object's title value to search for
     * @return titleobj the Title object with the given id
     */
    @Override
    public List<Title> retrieveByTitle(String strtitle)
    {
        db = new DBUtil();
        ResultSet rs       = null;
        List<Title> titles = null;

        String DML = "SELECT titleid, authKey, mediaKey, loanKey, title, bookNote FROM Title WHERE title LIKE '"+
                     strtitle + "' ORDER BY titlle ASC";
        rs = db.execQuery(DML);
        try
        {
            while ( (rs != null) && rs.next() )
            {
                int    i_id    = rs.getInt("titleid");
                int    i_auth  = rs.getInt("authKey");
                int    i_media = rs.getInt("mediaKey");
                int    i_loan  = rs.getInt("loanKey");
                String i_title = rs.getString("title");
                String i_note  = rs.getString("bookNote");
                Title  tobj    = new Title(i_id, i_auth, i_media, i_loan, i_title, i_note);
                try
                {
                    titles.add(tobj);
                }
                catch ( NullPointerException e )
                {
                    System.out.println("retrieveAll() had a null pointer..." + e.getMessage());
                }
            }
            db.close();
        }
        catch ( SQLException | NullPointerException e)
        {
            // do nothing
        }
        db = null;
        return titles;
    }

    /**
     * @brief Clear all entries from the User table
     */
    @Override
    public void clearData()
    {
        db = new DBUtil();
        String DML = "DELETE FROM User WHERE userid >= 0 ";

        try
        {
            db.execUpdate(DML);
            db.close();
        }
        catch ( SQLException e )
        {
            System.out.println("clearData() had an error: "+e.getMessage());
        }
        db = null;
    }

    /**
     * @brief Create test data (rows) in the Users table
     */
    @Override
    public void createTestData()
    {
        db = new DBUtil();

        String DML = "INSERT INTO Title (authKey, mediaKey, loanKey, title, bookNote) VALUES (" +
                     "1,1,1,'Holy Bible', 'example entry')";
        try
        {
            db.execSQL(DML);
            db.close();
        }
        catch ( SQLException e )
        {
            System.out.println("createTestData() had an error: "+e.getMessage());
        }
        db = null;
    }
}