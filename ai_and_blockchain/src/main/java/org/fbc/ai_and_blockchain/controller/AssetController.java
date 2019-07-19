package org.fbc.ai_and_blockchain.controller;

import org.fbc.ai_and_blockchain.client.AssetClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class AssetController {
    private AssetClient client = new AssetClient();

    @RequestMapping(value = "/deploy")
    public void deployContract() {
        try{
            client.initialize();
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        client.deployAssetAndRecordAddr();
    }

    @RequestMapping(value = "/register")
    public String register(){
        try{
            client.initialize();
            client.registerAssetAccount("Alice",new BigInteger("100000"));
            client.registerAssetAccount("Bob",new BigInteger("100000"));
        }catch(Exception e){
            return "Initialize error";
        }
        return null;
    }

    @RequestMapping(value = "/query")
    public void query(){
        try {
            client.initialize();
            client.queryAssetAmount("Alice");
            client.queryAssetAmount("Bob");
        }catch (Exception e){
            System.out.println("Error");
        }
    }

    @RequestMapping(value = "/transfer")
    public void transfer(){
        try {
            client.initialize();
            client.transferAsset("Alice", "Bob", new BigInteger("50000"));
        }catch (Exception e){
            System.out.println("Error");
        }
    }
}
