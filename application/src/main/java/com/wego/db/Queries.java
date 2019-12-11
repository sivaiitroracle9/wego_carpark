package com.wego.db;

public class Queries {
    public static final String NEAREST_CARPARK_PROC_QUERY = "CALL GetNearestCarparks(:input_latitude, :input_longitude, :input_pagenumber, :input_pagesize)";
    public static final String NEAREST_CARPARK_PROC_QUERY1 = "{CALL GetNearestCarparks(?, ?, ?, ?)}";
}
