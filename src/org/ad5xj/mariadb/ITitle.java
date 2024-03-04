package org.ad5xj.mariadb;

import java.util.List;

public interface ITitle extends Dao<Title>
{
    int titleid = -1;
    int authKey = -1;
    int mediaKey = -1;
    int loanKey = -1;
    String title = "unknown";
    String bookNote = "NONE";

    void Title();
    void Title(int i_id, int i_auth, int i_media, int i_loan, String i_title, String i_note);

    //       SETTERS AND GETTERS    //
    abstract int getID();
    abstract int  getAuthKey();
    abstract int  getMediaKey();
    abstract int  getLoanKey();
    abstract String  getTitle();
    abstract String  getBookNote();

    abstract void setID(int i_id);
    abstract void setAuthKey(int i_auth);
    abstract void setMediaKey(int i_media);
    abstract void setLoanKey(int i_loan);
    abstract void setTitle(String i_title);
    abstract void setBookNote(String i_note);
    //       END SETTERS AND GETTERS    //

    // CRUD METHODS //
    abstract boolean add(Title titleobj);
    abstract boolean update(Title titleobj);
    abstract boolean destroy(int i_id);
    // END CRUD METHODS //

    /**
     * Create a human-readable serialization.
     */
    abstract String objectToString();
    abstract int getLastID();
    abstract List<Title> retrieveByTitle(String i_title);
    abstract Title retrieveByID(int i_id );
    abstract void clearData();
    abstract void createTestData();
}