package com.jamesbriangray.rest;

import com.jamesbriangray.rest.caching.ApiCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    ApiCacheManager apiCacheManager;

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/financeRequest", produces = "application/json")
    public ResponseEntity<List<?>> getFinanceResults() {
        return new ResponseEntity<>(apiCacheManager.getLoadingCache().getUnchecked("financeRequest"), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/newsRequest", produces = "application/json")
    public ResponseEntity<List<?>> getNewsResults() {
        return new ResponseEntity<>(apiCacheManager.getLoadingCache().getUnchecked("newsRequest"), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/tflTubeStatus", produces = "application/json")
    public ResponseEntity<List<?>> getTflTubeStatusResults() {
        return new ResponseEntity<>(apiCacheManager.getLoadingCache().getUnchecked("tflTubeStatusRequest"), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/tflTrainStatus", produces = "application/json")
    public ResponseEntity<List<?>> getTflTrainStatusResults() {
        return new ResponseEntity<>(apiCacheManager.getLoadingCache().getUnchecked("tflTrainStatusRequest"), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/twitterStatus", produces = "application/json")
    public ResponseEntity<List<?>> getTwitterStatusResults() {
        return new ResponseEntity<>(apiCacheManager.getLoadingCache().getUnchecked("twitterRequest"), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/weatherStatus", produces = "application/json")
    public ResponseEntity<List<?>> getWeatherStatusResults() {
        return new ResponseEntity<>(apiCacheManager.getLoadingCache().getUnchecked("weatherRequest"), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/updates", produces = "application/json")
    public ResponseEntity<List<?>> getAllUpdates() {
        return new ResponseEntity(apiCacheManager.getLoadingCache().asMap(), HttpStatus.OK);
    }
}

