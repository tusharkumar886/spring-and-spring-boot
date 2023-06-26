package com.oreilly.restclient.services;

import com.oreilly.restclient.json.AstroResponse;
import org.springframework.web.service.annotation.GetExchange;

public interface AstroInterface {
    @GetExchange("/astros.json")
    AstroResponse getAstroResponse();
}
