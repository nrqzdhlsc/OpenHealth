package org.fbc.ai_and_blockchain.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.fbc.ai_and_blockchain.service.ReencryptionService;
import org.fbc.ai_and_blockchain.utils.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReencryptionController {

    ReencryptionService client = new ReencryptionService();

    @RequestMapping(value = "/initialize", method = RequestMethod.GET)
    public SuperResult deployContract() {
        client.initialize();
        String address = client.deployDataBusAndRecordAddr();
        client.initData();
        Map<String, String> map = new HashMap<>();
        map.put("address", address);
        return SuperResult.ok(map);
    }

    @RequestMapping(value = "/registerSuperior", method = RequestMethod.POST)
    public SuperResult registerSuperior(String account) {
        System.out.println("Call RegisterSuperior Success");
        JSONObject map = new JSONObject();
        map.put("account", account);
        String params = map.toString();
        String response = HttpUtil.post(CommonFields.PY_URL + "/gen_key", params);
        JSONObject result = JSON.parseObject(response);
        String public_key = result.getString("public_key");
        String verifying_key = result.getString("verifying_key");
        client.initialize();
        int ret = client.superiorRegister(account, public_key, verifying_key);
        if (ret == 0) {
            return SuperResult.ok();
        }
        return new SuperResult(500, null);
    }

    @RequestMapping(value = "/registerSubordinate", method = RequestMethod.POST)
    public SuperResult registerSubordinate(String account, String superior, int type) {
        System.out.println("Call RegisterSubordinate Success");
        String response = client.genKey(account);
        JSONObject result = JSON.parseObject(response);
        String public_key = result.getString("public_key");
        String verifying_key = result.getString("verifying_key");
        client.initialize();
        int ret = client.subordinateRegister(account, superior, public_key, verifying_key, type);
        if (ret == 0) {
            return SuperResult.ok();
        }
        return new SuperResult(500, null);
    }

    @RequestMapping(value = "/getAllPatients", method = RequestMethod.GET)
    public SuperResult getAllPatients() {
        client.initialize();
        List<String> list = client.getAllPatients();
        Map<String, List<String>> map = new HashMap<>();
        if (list != null) {
            map.put("patients", list);
            return SuperResult.ok(map);
        }
        return new SuperResult(500, null);
    }

    @RequestMapping(value = "/getUserPubKey", method = RequestMethod.GET)
    public SuperResult getUserPubKey(String account) {
        client.initialize();
        Map<String, String> map = new HashMap<>();
        String pubKey = client.getUserPubKey(account);
        map.put("publicKey", pubKey);
        return SuperResult.ok(map);
    }

    @RequestMapping(value = "/getUserVerifyingKey", method = RequestMethod.GET)
    public SuperResult getUserVerifyingKey(String account) {
        client.initialize();
        Map<String, String> map = new HashMap<>();
        String verKey = client.getUserVerifyingKey(account);
        map.put("verifyingKey", verKey);
        return SuperResult.ok(map);
    }

    @RequestMapping(value = "/uploadData", method = RequestMethod.POST)
    public SuperResult uploadData(String account, String owner) {
        client.initialize();
        JSONObject map = new JSONObject();
        map.put("account", owner);
        String params = map.toString();
        String response = HttpUtil.post(CommonFields.PY_URL + "/uploadData", params);
        JSONObject result = JSON.parseObject(response);
        String ciphertext = result.getString("ciphertext");
        String capsule = result.getString("capsule");
        return client.uploadData(account, owner, ciphertext, capsule);
    }

    @RequestMapping(value = "/requestForData", method = RequestMethod.POST)
    public SuperResult requestForData(String account, String dataId) {
        client.initialize();
        return client.requestForData(account, dataId);
    }

    @RequestMapping(value = "/getAllYourRequests", method = RequestMethod.GET)
    public SuperResult getAllYourRequests(String account) {
        client.initialize();
        return client.getYourRequest(account);
    }

    @RequestMapping(value = "/agreeForRequest", method = RequestMethod.POST)
    public SuperResult agreeForRequest(String account, String requestId) {
        client.initialize();
        int ret = client.agreeForRequest(account, requestId);
        if (ret == 0) {
            return SuperResult.ok();
        }
        return new SuperResult(500, null);
    }

    @RequestMapping(value = "/getAllRecords", method = RequestMethod.GET)
    public SuperResult getAllRecords() {
        String result = JSON.toJSONString(client.getAllRecords());
        HashMap<String, String> map = new HashMap<>();
        map.put("list", result);
        return SuperResult.ok(map);
    }

    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public SuperResult getData(String account, String dataId) {
        client.initialize();
        String data = client.getData(account, dataId);
        HashMap<String, String> map = new HashMap<>();
        map.put("dataURL", data);
        return SuperResult.ok(map);
    }

    @RequestMapping(value = "/getHospitalRecords", method = RequestMethod.GET)
    public SuperResult getHospitalRecords() {
        DataSharingResponse response = client.genHospitalRecords();
        return SuperResult.ok(response);
    }

    @RequestMapping(value = "/getPatientRequests", method = RequestMethod.GET)
    public SuperResult getPatientRequests() {
        RequestResponse response = client.genPatientRequests();
        return SuperResult.ok(response);
    }

    @RequestMapping(value = "/getPatientInfo", method = RequestMethod.GET)
    public SuperResult getPatientInfo() {
        PatientInfoResponse response = client.genPatientInfo();
        return SuperResult.ok(response);
    }

    @RequestMapping(value = "/getPlatformRecords", method = RequestMethod.GET)
    public SuperResult getPlatformRecords() {
        DataSharingResponse response = client.genPlatformRecords();
        return SuperResult.ok(response);
    }

}
