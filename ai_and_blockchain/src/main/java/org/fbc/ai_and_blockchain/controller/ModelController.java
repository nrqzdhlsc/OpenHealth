package org.fbc.ai_and_blockchain.controller;

import org.fbc.ai_and_blockchain.service.ModelService;
import org.fbc.ai_and_blockchain.utils.SuperResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
public class ModelController {

    ModelService client = new ModelService();

    @RequestMapping(value = "/model/initialize", method = RequestMethod.GET)
    public SuperResult deployContract() {
        client.initialize();
        String result = client.deployModelContract();
        Map<String, String> map = new HashMap<>();
        map.put("address", result);
        return SuperResult.ok(map);
    }
}
