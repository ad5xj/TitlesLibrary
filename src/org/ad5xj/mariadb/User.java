/*
 * (C)Copyright 2024 AD5XJ ken
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.ad5xj.mariadb;

import jakarta.persistence.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.Objects;

/**
 * @author ken
 * @brief Data definition of User data object
 * @ingroup MYSQL
 * @details This is the data bean of the User data object in the Users table.
 */
@RequestScoped @ManagedBean @Table(name = "Users")
@Entity
public class User
{
    @GeneratedValue
    @Column(name = "userid", nullable = false)
    @Id private int userid;
    @Basic @Column(name = "login")
    private String login;
    @Basic @Column(name = "userName")
    private String userName;
    @Basic @Column(name = "userPasswd")
    private String userPasswd;
    @Basic @Column(name = "privLvl")
    private Integer privLvl;


    public User( )
    {
    }  // default constructor no parameters

    public User( int i_id, int i_p, String i_login, String i_name, String i_passwd )
    { // with required parameters
        this.userid     = i_id;
        this.privLvl    = i_p;
        this.login      = i_login;
        this.userName   = i_name;
        this.userPasswd = i_passwd;
    }

    //       SETTERS AND GETTERS    //
    public int     getID()       { return userid; }
    public int     getPrivLvl()  { return privLvl; }
    public String  getLogin()    { return login; }
    public String  getName()     { return userName; }
    public String  getPasswd()   { return userPasswd; }

    public void setID(int i_id)            { userid = i_id; }
    public void setPrivLvl(int i_p)        { privLvl = i_p; }
    public void setLogin(String i_login)   { login = i_login; }
    public void setName(String i_name)     { userName = i_name; }
    public void setPasswd(String i_passwd) { userPasswd = i_passwd; }
    //       END SETTERS AND GETTERS    //

    public static boolean validate(String i_login, String i_passwd) { return false; }
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
        User that = (User) o;
        return userid == that.userid && Objects.equals(privLvl, that.privLvl) && Objects.equals(login, that.login) && Objects.equals(userName,
                that.userName) && Objects.equals(userPasswd, that.userPasswd) && Objects.equals(privLvl,
                that.privLvl);
    }

    public int hashCode( )
    {
        return Objects.hash(userid, privLvl, login, userName, userPasswd );
    }
}