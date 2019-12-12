package com.wego;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static com.wego.transformers.ApplicationTransformers.transformCarparkAvailabilityDomainToEntity;
import static com.wego.transformers.ApplicationTransformers.transformResultSetToNearestCarpark;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationTransformersTest {

    private final String TEST_ADDRESS = "TEST_ADDRESS";
    private final String TEST_LOT_TYPE = "TEST_LOT_TYPE";
    private final String TEST_CARPARK_NUMBER = "TEST_CARPARK_NUMBER";
    private final int TEST_LOTS_AVAILABLE = 15;
    private final int TEST_TOTAL_LOTS = 50;
    private final double TEST_LATITUDE = 50.65d;
    private final double TEST_LONGITUDE = 111.35d;

    @Test
    public void carparkAvailabilityDomainToEntity()
    {
        com.wego.domain.CarparkAvailability domain = new com.wego.domain.CarparkAvailability();
        domain.setLotType(TEST_LOT_TYPE);
        domain.setLotsAvailable(TEST_LOTS_AVAILABLE);
        domain.setTotalLots(TEST_TOTAL_LOTS);
        domain.setCarparkNumber(TEST_CARPARK_NUMBER);
        domain.setTimestamp(LocalDateTime.now());
        domain.setUpdateTime(LocalDateTime.now());

        com.wego.db.entity.CarparkAvailabilityEntity entity =
                transformCarparkAvailabilityDomainToEntity()
                .apply(domain);

        assertThat(entity.getCarparkNumber(), is(domain.getCarparkNumber()));
        assertThat(entity.getLotsAvailable(), is(domain.getLotsAvailable()));
        assertThat(entity.getLotType(), is(domain.getLotType()));
        assertThat(entity.getTotalLots(), is(domain.getTotalLots()));
        assertThat(entity.getTimestamp(), is(domain.getTimestamp()));
        assertThat(entity.getUpdateTime(), is(domain.getUpdateTime()));
    }

    @Test
    public void resultSetToNearestCarpark() throws SQLException
    {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString("address")).thenReturn(TEST_ADDRESS);
        when(resultSet.getDouble("latitude")).thenReturn(TEST_LATITUDE);
        when(resultSet.getDouble("longitude")).thenReturn(TEST_LONGITUDE);
        when(resultSet.getInt("total_lots")).thenReturn(TEST_TOTAL_LOTS);
        when(resultSet.getInt("lot_available")).thenReturn(TEST_LOTS_AVAILABLE);

        com.wego.db.entity.NearestCarpark nearestCarpark = transformResultSetToNearestCarpark().apply(resultSet);
        assertThat(nearestCarpark.getAddress(), is(resultSet.getString("address")));
        assertThat(nearestCarpark.getLatitude(), is(resultSet.getDouble("latitude")));
        assertThat(nearestCarpark.getLongitude(), is(resultSet.getDouble("longitude")));
        assertThat(nearestCarpark.getTotalLots(), is(resultSet.getInt("total_lots")));
        assertThat(nearestCarpark.getAvailableLots(), is(resultSet.getInt("lot_available")));
    }

    @Test
    public void nearestCarparkDbEntityToView()
    {
        com.wego.db.entity.NearestCarpark entity = new com.wego.db.entity.NearestCarpark();
        entity.setAddress(TEST_ADDRESS);
        entity.setLatitude(TEST_LATITUDE);
        entity.setLatitude(TEST_LONGITUDE);
        entity.setAvailableLots(TEST_LOTS_AVAILABLE);
        entity.setTotalLots(TEST_TOTAL_LOTS);

        com.wego.domain.NearestCarparkView view = new com.wego.domain.NearestCarparkView();
        assertThat(view.getAddress(), is(entity.getAddress()));
        assertThat(view.getLatitude(), is(entity.getLatitude()));
        assertThat(view.getLongitude(), is(entity.getLongitude()));
        assertThat(view.getAvailableLots(), is(entity.getAvailableLots()));
        assertThat(view.getTotalLots(), is(entity.getTotalLots()));
    }
}
