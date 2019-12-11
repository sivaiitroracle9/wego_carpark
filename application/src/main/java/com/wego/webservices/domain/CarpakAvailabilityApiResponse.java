package com.wego.webservices.domain;

import java.util.List;

public class CarpakAvailabilityApiResponse {

    private List<CarparkAvailability> items;

    public List<CarparkAvailability> getItems() {
        return items;
    }

    public void setItems(List<CarparkAvailability> items) {
        this.items = items;
    }

    public CarparkAvailability getFirstItem()
    {
        return items != null && !items.isEmpty() ? items.get(0) : null;
    }

}
