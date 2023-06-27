package com.oreilly.restclient.services;

import com.oreilly.restclient.json.AstroResponse;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/astros.json")
public interface AstroInterface {
    @GetExchange
    AstroResponse getAstroResponse();
}
