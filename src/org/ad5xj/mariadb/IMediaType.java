package org.ad5xj.mariadb;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

public interface IMediaType extends Dao<MediaType>
{
    @Id
    @GeneratedValue
    Integer mediaid  = - 1;
    String mediaName = "";

    MediaType mediaobj = null;

    void MediaType();
    void MediaType(int i_id, String i_name);

    //       SETTERS AND GETTERS    //
    abstract int getID();
    abstract String  getName();

    abstract void setID(int i_id);
    abstract void setName(String i_name);
    //       END SETTERS AND GETTERS    //

    abstract boolean add(MediaType mediaobj);
    abstract boolean update(MediaType mediaobj);
    abstract boolean destroy(int i_id);

    abstract int getLastID();
    abstract void clearData();
    abstract void createTestData();
    abstract List<MediaType> getByType(String i_type);
}
