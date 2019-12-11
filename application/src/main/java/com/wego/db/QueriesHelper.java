package com.wego.db;

import java.util.HashMap;
import java.util.Map;

public class QueriesHelper {

    public static Map<String, Object> getNearestCarparkProcParameters(double latitude, double longitude, int page, int per_page)
    {
        Map<String, Object> parametersMap = new HashMap<String, Object>();
        parametersMap.put("input_latitude", latitude);
        parametersMap.put("input_longitude", longitude);
        parametersMap.put("input_pagenumber", page);
        parametersMap.put("input_pagesize", per_page);

        return parametersMap;
    }

}
