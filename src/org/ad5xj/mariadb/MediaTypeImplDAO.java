package org.ad5xj.mariadb;

import org.ad5xj.mariadb.DBUtil;
import org.ad5xj.mariadb.IMediaType;
import org.ad5xj.mariadb.MediaType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("unused")
public class MediaTypeImplDAO implements IMediaType
{
    protected DBUtil db;

    private int mediaid;
    private String mediaName;

    private MediaType mediaobj;

    @Override
    public void MediaType() {}

    @Override
    public void MediaType(int i_id, String i_name)
    {
        this.mediaid     = i_id;
        this.mediaName   = i_name;
    }
    //       SETTERS AND GETTERS    //
    @Override
    public int getID()
    {
        try
        {
            return mediaobj.getID();
        }
        catch (NullPointerException e)
        {
            return -1;
        }
    }

    @Override
    public String  getName()
    {
        try
        {
            return mediaobj.getName();
        }
        catch (NullPointerException e)
        {
            return "unknown";
        }
    }

    @Override
    public void setID(int i_id) { mediaobj.setID(i_id); }
    @Override
    public void setName(String i_name) { mediaobj.setName(i_name); }
    //       END SETTERS AND GETTERS    //


    // CRUD METHODS //
    /**
     * Create and persist a new MediaType instance.
     * @param mediaobj local instance of MediaType object
     * @return a string representing the view name to display after finishing the execution of this method.
     */
    @Override
    public boolean add(MediaType mediaobj)
    {
        boolean result = false;

        db = new DBUtil();

        // create DML statement from incoming data
        String DML = "INSERT INTO MediaType (mediaid, mediaName) VALUES (" +
                mediaobj.getID() + ",'" +
                mediaobj.getName() + "')";
        try
        {
            result = db.execUpdate(DML);
            db.close();
        }
        catch (SQLException e)
        {
            System.out.println("add() had an error: " + e.getMessage());
            result = false;
        }
        db = null;
        return result;
    }

    /**
     * @brief Update a MediaType instance
     * @param mediaobj  reference to the MediaType table object
     * @return result boolean indicating success
     */
    @Override
    public boolean update(MediaType mediaobj)
    {
        boolean result = false;
        String DML  = "";
        try
        {
            /* form DML from values in incoming object using MariaDB SQL syntax */
            DML = "UPDATE MediaType (mediaid, mediaName) VALUES (" +
                    mediaobj.getID() + ", '" +
                    mediaobj.getName() + "')";

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
     * @brief Delete a User instance
     * @param i_id the userid of the User to delete
     * @return result boolean indicating success.
     */
    @Override
    public boolean destroy(int i_id)
    {
        boolean result = false;
        String DML = "DELETE FROM MediaType WHERE mediaid = " + i_id;

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

    @SuppressWarnings("null")
	@Override
    public List<MediaType>  getByType(String i_type)
    {
        String DML = "SELECT * FROM MediaType where mediaName like '" + i_type + "'";
        List<MediaType> mediaTypes = null;
        ResultSet rs = null;

        mediaTypes.clear();
        try
        {
            rs = db.execQuery(DML);
            while ( rs.next() )
            {
                int i_id = rs.getInt("mediaid");
                String i_name = rs.getString("mediaName");
                MediaType media = new MediaType(i_id, i_name);
                mediaTypes.add(media);
            }
            db.close();
        }
        catch ( SQLException | NullPointerException e)
        {
            // do nothing
        }
        db = null;
        return mediaTypes;
    }

    @Override
    public int getLastID()
    {
        int lstid = -1;
        String DML = "SELECT max('mediaid') as max FROM MediaType ";
        db = new DBUtil();
        ResultSet rs = null;

        try
        {
            rs = db.execQuery(DML);
            lstid = rs.getInt("max");
        }
        catch ( SQLException | NullPointerException e)
        {
            return - 1;
        }
        return lstid;
    }

    /**
     * @brief refresh the data in the MediaType object
     * @details
     * Refresh the object by setting its property values to match the one
     * existing in the database for the specific instance, identified by the
     * primary key value.
     *
     * @param mediatype  the reference to the User instance to be "loaded" from database.
     */
    public void refreshObject(MediaType mediatype)
    {
        int  i_id = mediatype.getID();
        try
        {
            MediaType foundType = retrieveByID(i_id);
            mediatype.setID(foundType.getID());
            mediatype.setName(foundType.getName());
        }
        catch ( NullPointerException e )
        {
            System.out.println("refreshObject() had a null value..."+e.getMessage());
        }
    }

    /**
     * @brief Retrieve all Title records from the Title table.
     *
     * @return the list of all Title records
     */
    @SuppressWarnings("null")
	public List<MediaType> retrieveAll()
    {
        db = new DBUtil();
        ResultSet rs = null;
        List<MediaType> mediaTypes = null;

        String DML = "SELECT mediaid, mediaName FROM MediaType ORDER BY mediaName ASC";
        mediaTypes.clear();
        rs = db.execQuery(DML);
        try
        {
            while ( rs.next() )
            {
                int i_id = rs.getInt("mediaid");
                String i_name = rs.getString("mediaName");
                MediaType media = new MediaType(i_id, i_name);
                mediaTypes.add(media);
            }
            db.close();
        }
        catch ( SQLException | NullPointerException e)
        {
            // do nothing
        }
        db = null;
        return mediaTypes;
    }

    /**
     * @brief Retrieve a MediaType record from the MediaType table.
     *
     * @param i_id the MediaType's mediaid
     * @return MediaType the MediaType object with the given id
     */
    public MediaType retrieveByID(int i_id)
    {
        db = new DBUtil();
        mediaobj = new MediaType();
        ResultSet rs = null;

        String DML = "SELECT * FROM MediaType WHERE mediaid = " + i_id;
        try
        {
            rs = db.execQuery(DML);
            mediaobj.setID(rs.getInt("userid"));
            mediaobj.setName(rs.getString("mediaName"));
            db.close();
        }
        catch ( SQLException e)
        {
            System.out.println("retrieveByID() had an error: "+e.getMessage());
        }
        return mediaobj;
    }

    /**
     * Read the list of all the MediaTypes from the database.
     *
     * @return a list with all the MediaType instances found in the database.
     */
    public List<MediaType> getMediaTypes()
    {
        return retrieveAll();
    }


    /**
     * @brief Clear all entries from the User table
     */
    @Override
    public void clearData()
    {
        String DML = "DELETE FROM MediaType WHERE mediaid >= 0 ";
        db = new DBUtil();

        try
        {
            db.execUpdate(DML);
            db.close();
        }
        catch ( SQLException e )
        {
            System.out.println("clearData() had an error: "+e.getMessage());
        }
    }

    /**
     * @brief Create test data (rows) in the MediaType table
     */
    @Override
    public void createTestData()
    {
        // creating reference data used with the Titles record
        db = new DBUtil();
        String DML = "INSERT INTO MediaType (mediaid,mediaName) VALUES (1,'Softcover')";
        try
        {
            db.execSQL(DML);
        }
        catch ( SQLException e )
        {
            System.out.println("createTestData() had an error:" + e.getMessage());
        }

        DML = "INSERT INTO MediaType (mediaid,mediaName) VALUES (2,'Hardcover')";
        try
        {
            db.execSQL(DML);
        }
        catch ( SQLException e )
        {
            System.out.println("createTestData() had an error: "+e.getMessage());
        }

        DML = "INSERT INTO MediaType (mediaid,mediaName) VALUES (3,'Paperback')";
        try
        {
            db.execSQL(DML);
        }
        catch ( SQLException e )
        {
            System.out.println("createTestData() had an error: "+e.getMessage());
        }

        DML = "INSERT INTO MediaType (mediaid,mediaName) VALUES (4,'eBook')";
        try
        {
            db.execSQL(DML);
        }
        catch ( SQLException e )
        {
            System.out.println("createTestData() had an error: "+e.getMessage());
        }

        DML = "INSERT INTO MediaType (mediaid,MediaName) VALUES (5,'Audio Book')";
        try
        {
            db.execSQL(DML);
        }
        catch ( SQLException e )
        {
            System.out.println("createTestData() had an error: "+e.getMessage());
        }

        DML = "INSERT INTO MediaType (mediaid,mediaName) VALUES (9,'Video - digital download')";
        try
        {
            db.execSQL(DML);
        }
        catch ( SQLException e )
        {
            System.out.println("createTestData() had an error: "+e.getMessage());
        }

        DML = "INSERT INTO MediaType (mediaid,mediaName) VALUES (10,'Video - CD')";
        try
        {
            db.execSQL(DML);
        }
        catch ( SQLException e )
        {
            System.out.println("createTestData() had an error: "+e.getMessage());
        }

        DML = "INSERT INTO MediaType (mediaid,mediaName) VALUES (11,'Video - DVD'";
        try
        {
            db.execSQL(DML);
        }
        catch ( SQLException e )
        {
            System.out.println("createTestData() had an error: "+e.getMessage());
        }

        DML = "INSERT INTO MediaType (mediaid,MediaName) VALUES (12,'Video - VHS Tape')";
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