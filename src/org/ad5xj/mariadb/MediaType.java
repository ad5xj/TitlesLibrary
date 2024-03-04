package org.ad5xj.mariadb;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * @brief Data definition of MediaType data object
 * @ingroup MARIADB
 * @author ken
 *
 * @details This is the data bean of the MediaType data object in the MediaType table.
 */
@Entity @Table(name = "MediaType")
public class MediaType
{
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "mediaid", nullable = false)
    @Id private int mediaid;
    @Basic @Column(name = "mediaName")
    private String mediaName;

    public MediaType() { }  // object with no parameters

    public MediaType(int i_id, String i_name)
    {  // with required parameters
        this.mediaid   = i_id;
        this.mediaName = i_name;
    }

    public static List<MediaType>  getByType(String i_type) { return null; }

    public int getMediaid( )
    {
        return mediaid;
    }

    public void setMediaid( int mediaid )
    {
        this.mediaid = mediaid;
    }

    public String getMediaName( )
    {
        return mediaName;
    }

    public void setMediaName( String mediaName )
    {
        this.mediaName = mediaName;
    }

    /*       SETTERS AND GETTERS    */
    public int    getID()   { return mediaid; }

    public void setID(int i_id)        { mediaid = i_id; }

    public String getName() { return mediaName; }
    /*       END SETTERS AND GETTERS    */

    public void setName(String i_name) { mediaName = i_name; }

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
        MediaType that = (MediaType) o;
        return mediaid == that.mediaid && Objects.equals(mediaName, that.mediaName);
    }

    @Override
    public int hashCode( )
{
    return Objects.hash(mediaid, mediaName);
}
}