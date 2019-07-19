package org.fbc.ai_and_blockchain.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.fbc.ai_and_blockchain.utils.HttpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReencryptionController {

    @RequestMapping(value = "/gen_key")
    public String generateKeys(String account){
        JSONObject map = new JSONObject();
        map.put("account",account);
        String params = map.toString();
        String result = HttpUtil.post("http://localhost:5000", params);
        return result;
    }

}
