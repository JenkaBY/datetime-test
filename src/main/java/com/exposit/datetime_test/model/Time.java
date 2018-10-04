package com.exposit.datetime_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "times")
public class Time
{
    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "with_tz")
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ssZ")
    private Timestamp withTz;

    @Column(name = "without_tz")
    private Timestamp withoutTz;

    @Column
    private String description;

    @Column(name = "tz")
    private String timezone;

    public Time()
    {
    }

    public String getTimezone()
    {
        return timezone;
    }

    public void setTimezone(String timezone)
    {
        this.timezone = timezone;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Timestamp getWithTz()
    {
        return withTz;
    }

    public void setWithTz(Timestamp withTz)
    {
        this.withTz = withTz;
    }

    public Timestamp getWithoutTz()
    {
        return withoutTz;
    }

    public void setWithoutTz(Timestamp withoutTz)
    {
        this.withoutTz = withoutTz;
    }
}
