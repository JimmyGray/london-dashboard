package com.jamesbriangray.rest.requests;

import com.google.common.util.concurrent.Callables;
import com.jamesbriangray.models.tfl.TflStatus;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Callable;

@Component
public class TflRequests {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String BASE_URL = "https://api.tfl.gov.uk/line/mode/";
    private final String TFL_URL = BASE_URL + "tube/status";
    private final String NATIONAL_RAIL_URL = BASE_URL + "national-rail/status";

    public Callable<List<?>> retrieveTubeStatus() {
        return Callables.returning(restTemplate.exchange(TFL_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<TflStatus>>() {
        }).getBody());
    }

    public Callable<List<?>> retrieveNationRailWayStatus() {
        return Callables.returning(restTemplate.exchange(NATIONAL_RAIL_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<TflStatus>>() {
        }).getBody());
    }
}
