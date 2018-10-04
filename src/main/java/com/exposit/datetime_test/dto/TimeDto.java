package com.exposit.datetime_test.dto;

import java.sql.Timestamp;

public class TimeDto
{
    private Long id;
    private Timestamp withTz;
    private Timestamp withoutTz;
    private Timestamp withTzInUtc;
    private Timestamp withoutTzInUtc;

    public TimeDto(Long id, Timestamp withTz, Timestamp withoutTz)
    {
        this.id = id;
        this.withTz = withTz;
        this.withoutTz = withoutTz;
//        this.withoutTzInUtc = withoutTz.
    }
}
