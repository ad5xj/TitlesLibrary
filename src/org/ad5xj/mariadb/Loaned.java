package org.ad5xj.mariadb;

import jakarta.persistence.*;

import java.util.Objects;

@Entity @Table(name = "Loaned")
public class Loaned
{
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "loanid", nullable = false)
    @Id private int loanid;
    @Basic @Column(name = "loanDate")
    private String loanDate;
    @Basic @Column(name = "loanedTo")
    private String loanedTo;

    public int getLoanid( )
    {
        return loanid;
    }

    public void setLoanid( int loanid )
    {
        this.loanid = loanid;
    }

    public String getLoanDate( )
    {
        return loanDate;
    }

    public void setLoanDate( String loanDate )
    {
        this.loanDate = loanDate;
    }

    public String getLoanedTo( )
    {
        return loanedTo;
    }

    public void setLoanedTo( String loanedTo )
    {
        this.loanedTo = loanedTo;
    }

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
        Loaned that = (Loaned) o;
        return loanid == that.loanid && Objects.equals(loanDate, that.loanDate) && Objects.equals(
                loanedTo, that.loanedTo);
    }

    @Override
    public int hashCode( )
    {
        return Objects.hash(loanid, loanDate, loanedTo);
    }
}
