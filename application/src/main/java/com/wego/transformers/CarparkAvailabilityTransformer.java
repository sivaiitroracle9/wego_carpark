package com.wego.transformers;

import com.wego.domain.CarparkAvailability;
import com.wego.webservices.domain.CarparkInfo;
import com.wego.webservices.domain.CarparkData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CarparkAvailabilityTransformer {

    SimpleDateFormat sdf = null;

    private LocalDateTime getLocalDateTime(String text)
    {
        //return ZonedDateTime.parse(text, DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime();
        return LocalDateTime.parse(text, DateTimeFormatter.ISO_DATE_TIME);
    }

    public SimpleDateFormat getSdf() {
        if (sdf == null)
        {
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            TimeZone tz = TimeZone.getTimeZone("Asia/Singapore");
            sdf.setTimeZone(tz);
        }
        return sdf;
    }

    private Date getDateTime(String text)
    {
        try {
            return sdf.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Collection<CarparkAvailability> transform(com.wego.webservices.domain.CarparkAvailability carparkAvailability)
    {
        final HashMap<String, CarparkAvailability> carparkAvailabilityMap = new HashMap<>();
        if (carparkAvailability == null)
        {
            return carparkAvailabilityMap.values();
        }

        String timestamp = carparkAvailability.getTimestamp();
        for(CarparkData data : carparkAvailability.getCarparkData())
        {
            CarparkAvailability carparkAvl = new CarparkAvailability();
            carparkAvl.setCarparkNumber(data.getCarparkNumber());
            carparkAvl.setUpdateTime(getLocalDateTime(data.getUpdateTime()));
            carparkAvl.setTimestamp(getLocalDateTime(timestamp));

            CarparkInfo carparkInfo = data.getCarparkInfo().get(0);
            carparkAvl.setLotType(carparkInfo.getLotType());
            carparkAvl.setLotsAvailable(carparkInfo.getLotsAvailable());
            carparkAvl.setTotalLots(carparkInfo.getTotalLots());

            if (carparkAvailabilityMap.get(carparkAvl.getCarparkNumber()) == null ||
                    carparkAvl.getUpdateTime().isAfter(carparkAvailabilityMap.get(carparkAvl.getCarparkNumber()).getUpdateTime()))
            {
                carparkAvailabilityMap.put(carparkAvl.getCarparkNumber(), carparkAvl);
            }
        }
        return carparkAvailabilityMap.values();
    }

}
