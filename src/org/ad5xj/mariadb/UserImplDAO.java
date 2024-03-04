package org.ad5xj.mariadb;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

public class UserImplDAO implements IUser
{
    private int userid;
    private int privlvl;
    private String login;
    private String userName;
    private String userPasswd;

    private DBUtil db;
    private ResultSet rs;
    private User   user;
    private UserImplDAO userobj;

    private List<User> users;

    public void User() {};

    public void User(int i_id, int i_p, String i_login, String i_name, String i_passwd)
    {
        this.userid     = i_id;
        this.privlvl    = i_p;
        this.login      = i_login;
        this.userName   = i_name;
        this.userPasswd = i_passwd;
    }

    @Override
    public boolean validate(String i_login, String i_passwd)
    {
        boolean result = false;
        System.out.println("testing i_login...");
        userobj = new UserImplDAO();
        user = userobj.retrieveByLogin(i_login);

        try
        {
            String login = user.getLogin();
            String passwd = user.getPasswd();
            if ( i_login.equals(login) && i_passwd.equals(passwd))
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
    public int getID()
    {
        try
        {
            return user.getID();
        }
        catch (NullPointerException e)
        {
            return -1;
        }
    }

    public int getPrivLvl()
    {
        try
        {
            return user.getPrivLvl();
        }
        catch (NullPointerException e)
        {
            return 0;
        }
    }

    public String  getLogin()
    {
        try
        {
            return user.getLogin();
        }
        catch (NullPointerException e)
        {
            return "unknown";
        }
    }

    public String  getName()
    {
        try
        {
            return user.getName();
        }
        catch (NullPointerException e)
        {
            return "unknown";
        }
    }
    public String  getPasswd()
    {
        try
        {
            return user.getPasswd();
        }
        catch (NullPointerException e)
        {
            return "";
        }
    }

    public void setID(int i_id) { user.setID(i_id); }
    public void setPrivLvl(int i_p) { user.setPrivLvl(i_p); }
    public void setLogin(String i_login) { user.setLogin(i_login); }
    public void setName(String i_name) { user.setName(i_name); }
    public void setPasswd(String i_passwd) { user.setPasswd(i_passwd); }
    //       END SETTERS AND GETTERS    //

    // LOCAL CRUD METHODS //
    /**
     * Create and persist a new User instance.
     * @param i_u local instance of User object
     * @return a string representing the view name to display after finishing the execution of this method.
     */
    public boolean add(User i_u)
    {
        boolean result = false;
        String DML = "";
        db = new DBUtil();

        DML = "INSERT INTO User (userid, privLvl, login, userName, userPasswd) VALUES (" +
              Integer.toString(i_u.getID()) + "," +
                Integer.toString(i_u.getPrivLvl()) + ",'" +
              i_u.getLogin()  + "', '" +
              i_u.getName()   + "', '" +
              i_u.getPasswd() + "')";
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
     * @brief Update a User instance
     * @param i_u  reference to the User table object
     */
    public boolean update(User i_u)
    {
        boolean result = false;
        String DML  = "";
        /* form DML from values in incoming object using MariaDB SQL syntax */
        DML = "UPDATE User (userid, userLogin, userName, userPasswd) VALUES (" +
                i_u.getID()      + ", " +
                i_u.getPrivLvl() + ",'" +
                i_u.getLogin()   + "', '" +
                i_u.getName()    + "', '" +
                i_u.getPasswd()  + "')";
        try
        {
            db.execQuery(DML);
            result = true;
        }
        catch (Exception e)
        {
            result =  false;
        }
        return result;
    }

    /**
     * @brief Delete a User instance
     * @param i_id the userid of the User to delete
     */
    public boolean destroy(int i_id)
    {
        boolean result = false;
        String DML  = "DELETE FROM Users WHERE userid = " + Integer.toString(i_id);
        try
        {
            db.execQuery(DML);
            db.close();
            result = true;
        }
        catch (Exception e)
        {
            result =  false;
        }
        db = null;
        return result;
    }
    // END CRUD METHODS //

    public String objectToString() {
        return "{ id: " + Integer.toString(this.userid) +
                ", priv:" + Integer.toString(this.privlvl) +
                ", login:'" + this.login +
                ", name: " + this.userName +
                ", passwd: " + this.userPasswd +
                "}";
    }

    @Override
    public int getLastID()
    {
        int lstid = -1;
        String DML = "SELECT max('userid') as max FROM Users ";
        db = new DBUtil();
        rs = null;

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
     * @param i_u  the reference to the User instance to be "loaded" from database.
     */
    public void refreshObject(User i_u)
    {
        int i_id = i_u.getID();
        User foundUser = retrieveByID(i_id );
        i_u.setID(foundUser.getID() );
        i_u.setPrivLvl(foundUser.getPrivLvl());
        i_u.setLogin(foundUser.getLogin() );
        i_u.setName(foundUser.getName() );
        i_u.setPasswd(foundUser.getPasswd() );
    }

    /**
     * @brief Retrieve all Title records from the Title table.
     *
     * @return the list of all Title records
     */
    @SuppressWarnings("null")
	public List<User> retrieveAll()
    {
        db = new DBUtil();
        rs = null;

        String DML = "SELECT userid, privLvl, login, userName, userPasswd FROM Users ORDER BY login ASC";
        rs = db.execQuery(DML);
        users.clear();
        try
        {
            while ( rs.next() )
            {
                int i_id = rs.getInt("userid");
                int i_p  = rs.getInt("privLvl");
                String i_login  = rs.getString("login");
                String i_name = rs.getString("userName");
                String i_passwd = rs.getString("userPasswd");
                User user = new User(i_id, i_p, i_login, i_name, i_passwd);
                users.add(user);
            }
            db.close();
        }
        catch ( SQLException | NullPointerException e)
        {
            // do nothing
        }
        db = null;
        return users;
    }

    /**
     * @brief Retrieve a User record from the Users table.
     *
     * @param i_id the User's userid
     * @return userobj the User object with the given id
     */
    public User retrieveByID(int i_id)
    {
        db = new DBUtil();
        rs = null;

        String DML = "SELECT * FROM Users WHERE userid = " + Integer.toString(i_id);
        user = new User();
        try
        {
            rs = db.execQuery(DML);
            user.setID(rs.getInt("userid"));
            user.setPrivLvl(rs.getInt("privLvl"));
            user.setLogin(rs.getString("login"));
            user.setName(rs.getString("userName"));
            user.setPasswd(rs.getString("userPasswd"));
            db.close();
            return user;
        }
        catch ( SQLException e)
        {
            return null;
        }
    }

    /**
     * @brief Retrieve a User record from the Users table.
     *
     * @param i_login the User's login
     * @return userobj the User object with the given login
     */
    public User retrieveByLogin(String i_login)
    {
        db = new DBUtil();
        rs = null;
        user = new User();

        System.out.println("getting user by login: " + i_login);
        String DML = "SELECT * FROM Users WHERE login = '" + i_login + "'";
        try
        {
            rs = db.execQuery(DML);
            int i_id = rs.getInt("userid");
            if ( !Integer.toString(i_id).equals(null) )
            {
                user.setID(rs.getInt("userid"));
                user.setPrivLvl(rs.getInt("privLvl"));
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("userName"));
                user.setPasswd(rs.getString("userPasswd"));
            }
            else
            {
               System.out.println("Query Failure:"+ db.getLastError());
            }
            db.close();
        }
        catch ( SQLException e)
        {
            System.out.println("retrieveByLogin() had an error..."+e.getMessage());
        }
        db = null;
        System.out.println("found user: " + userobj.getLogin() + "/" + userobj.getPasswd());
        return user;
    }

    /**
     * @brief Clear all entries from the User table
     */
    public void clearData()
    {
        String DML = "DELETE FROM User WHERE userid >= 0 ";
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
     * @brief Create test data (rows) in the Users table
     */
    public void createTestData()
    {
        db = new DBUtil();
        // clear existing books, so no primary key duplicate conflicts appear
        String DML = "INSERT INTO User (userid, privLvl, userName, userPasswd) VALUES (" +
                      Integer.toString(1) + "," + Integer.toString(9) + ",'admin','Database Admin,'NhbWun-333')";

        try
        {
            db.execUpdate(DML);
            db.close();
        }
        catch ( SQLException e )
        {
            System.out.println("createTestData() had an error: "+e.getMessage());
        }
        db = null;
        userobj = null;
    }
    /**
     * Read the list of all the users from the database.
     *
     * @return a list with all the User instance found in the database.
     */
    public List<User> getUsers()
    {
        return retrieveAll();
    };
}