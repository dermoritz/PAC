package com.prodyna.pac.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import java.lang.Override;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

/**
 * A booking has disjoint start and end time an owner ( {@link User} ) and a
 * {@link Project}.
 * 
 * @author moritz
 *
 */
@Entity
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Version
    @Column(name = "version")
    private int version;

    @OneToOne
    @JoinColumn(name = "id")
    @NotNull
    private Project project;

    @ManyToOne
    @JoinColumn(name = "id")
    private User owner;

    @Column
    @NotNull
    private Timestamp start;

    @Column
    @NotNull
    private Timestamp end;

    public Long getId()
    {
        return this.id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public int getVersion()
    {
        return this.version;
    }

    public void setVersion(final int version)
    {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof Booking))
        {
            return false;
        }
        Booking other = (Booking) obj;
        if (id != null)
        {
            if (!id.equals(other.id))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public Timestamp getStart()
    {
        return start;
    }

    public void setStart(Timestamp start)
    {
        this.start = start;
    }

    public Timestamp getEnd()
    {
        return end;
    }

    public void setEnd(Timestamp end)
    {
        this.end = end;
    }
}