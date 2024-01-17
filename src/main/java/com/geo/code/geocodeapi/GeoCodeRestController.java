package com.geo.code.geocodeapi;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/geocodeapi")
@Slf4j
@RequiredArgsConstructor
public class GeoCodeRestController {
    private final GeoCodeService geoCodeService;

    @GetMapping
    void consumingApi() throws IOException, InterruptedException {
        GeoCode code = geoCodeService.getGeoCode();
        //log.info(code.getCity());
    }
}
