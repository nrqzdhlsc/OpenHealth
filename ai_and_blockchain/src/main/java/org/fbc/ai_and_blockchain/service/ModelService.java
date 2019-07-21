package org.fbc.ai_and_blockchain.service;

import com.alibaba.fastjson.JSON;
import org.fbc.ai_and_blockchain.contract.DSList;
import org.fbc.ai_and_blockchain.contract.DataMarket;
import org.fbc.ai_and_blockchain.contract.ModelList;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ModelService {
    private Web3j web3j;

    private Credentials credentials;

    public Web3j getWeb3j() {
        return web3j;
    }

    public void setWeb3j(Web3j web3j) {
        this.web3j = web3j;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void recordModelAddr(String dsListAddress, String modelListAddress, String dataMarketAddress) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        prop.setProperty("dsListAddress", dsListAddress);
        prop.setProperty("modelListAddress", modelListAddress);
        prop.setProperty("dataMarketAddress", dataMarketAddress);
        final Resource contractResource = new ClassPathResource("modelContract.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
        prop.store(fileOutputStream, "contract address");
    }

    public List<String> loadModelAddr() throws Exception {
        List<String> result = new ArrayList<>();
        // load Asset contact address from contract.properties
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("modelContract.properties");
        prop.load(contractResource.getInputStream());
        String dsListAddress = prop.getProperty("dsListAddress");
        String modelListAddress = prop.getProperty("modelListAddress");
        String dataMarketAddress = prop.getProperty("dataMarketAddress");
        if (dsListAddress == null || dsListAddress.trim().equals("") || modelListAddress == null || modelListAddress.trim().equals("") || dataMarketAddress == null || dataMarketAddress.trim().equals("")) {
            throw new Exception(" load model contract address failed, please deploy it first. ");
        }
        return result;
    }

    public void initialize() {
        try {
            // init the Service
            @SuppressWarnings("resource")
            ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
            Service service = context.getBean(Service.class);
            service.run();

            ChannelEthereumService channelEthereumService = new ChannelEthereumService();
            channelEthereumService.setChannelService(service);
            Web3j web3j = Web3j.build(channelEthereumService, 1);

            // init Credentials
            Credentials credentials = Credentials.create(Keys.createEcKeyPair());

            setCredentials(credentials);
            setWeb3j(web3j);

        } catch (Exception e) {
            System.out.println("Initialize error");
        }
    }

    private static BigInteger gasPrice = new BigInteger("30000000");
    private static BigInteger gasLimit = new BigInteger("30000000");

    public String deployModelContract() {
        List<String> result = new ArrayList<>();
        String response = "";
        try {
            ModelList modelList = ModelList.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
            DSList dsList = DSList.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
            DataMarket dataMarket = DataMarket.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
            System.out.println(" deploy modelList success, contract address is " + modelList.getContractAddress());
            System.out.println(" deploy dsList success, contract address is " + dsList.getContractAddress());
            System.out.println(" deploy dataMarket success, contract address is " + dataMarket.getContractAddress());
            recordModelAddr(dsList.getContractAddress(), modelList.getContractAddress(), dataMarket.getContractAddress());
            result.add(dsList.getContractAddress());
            result.add(modelList.getContractAddress());
            result.add(dataMarket.getContractAddress());
            response = JSON.toJSONString(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            System.out.println(" deploy Asset contract failed, error message is  " + e.getMessage());
            response = " deploy Asset contract failed";
        }
        return response;
    }

    public String getDSListAddress() {
        return "0x2d5d0fb4e018a16449123009772583edb98832d7";
    }

    public String getModelListAddress() {
        return "0x5f2913739e8d2cf95fd3ebf2a936760b3c6f82a5";
    }

    public String getDataMarketAddress() {
        return "0xd34267d867bb25c8e4b7d58ca08358af031b0dac";
    }

    public void modelRegister(String host, int port, String api) {
        try {
            String contractAddress = getModelListAddress();
            ModelList modelList = ModelList.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = modelList.register(host, new BigInteger(port + ""), api).send();
        } catch (Exception e) {
        }
    }

    public int modelGetGlobalIndex() {
        try {
            String contractAddress = getModelListAddress();
            ModelList modelList = ModelList.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            BigInteger result = modelList.getGlobalIndex().send();
            return result.intValue();
        } catch (Exception e) {

        }
        return -1;
    }

    public String modelGetModelInfoHost(int index) {
        String result = "";
        try {
            String contractAddress = getModelListAddress();
            ModelList modelList = ModelList.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            result = modelList.getModelInfo_host(new BigInteger(index + "")).send();
        } catch (Exception e) {
            result = "modelGetModelInfoHost error";
        }
        return result;
    }

    public int modelGetModelInfoPort(int index) {
        try {
            String contractAddress = getModelListAddress();
            ModelList modelList = ModelList.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            BigInteger result = modelList.getModelInfo_port(new BigInteger(index + "")).send();
            return result.intValue();
        } catch (Exception e) {

        }
        return -1;
    }

    public String modelGetModelInfoAPI(int index) {
        String result = "";
        try {
            String contractAddress = getModelListAddress();
            ModelList modelList = ModelList.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            result = modelList.getModelInfo_API(new BigInteger(index + "")).send();
        } catch (Exception e) {
            result = "modelGetModelInfoHost error";
        }
        return result;
    }

    public void dsRegister(String host, int port, String info, String description, int amount, int totalSize, int format) {
        try {
            String contractAddress = getDSListAddress();
            DSList dsList = DSList.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = dsList.register(host, new BigInteger("" + port), info,
                    description, new BigInteger("" + amount), new BigInteger("" + totalSize),
                    new BigInteger("" + format)).send();
        } catch (Exception e) {

        }
    }

    public int dsGetGlobalIndex() {
        try {
            String contractAddress = getDSListAddress();
            DSList dsList = DSList.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            BigInteger result = dsList.getGlobalIndex().send();
            return result.intValue();
        } catch (Exception e) {

        }
        return -1;
    }

    public int dsGetDataInfoPort(int index) {
        try {
            String contractAddress = getDSListAddress();
            DSList dsList = DSList.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            BigInteger result = dsList.getDataInfo_port(new BigInteger("" + index)).send();
            return result.intValue();
        } catch (Exception e) {

        }
        return -1;
    }

    public String dsGetDataInfoHost(int index) {
        String result = "";
        try {
            String contractAddress = getDSListAddress();
            DSList dsList = DSList.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            result = dsList.getDataInfo_host(new BigInteger("" + index)).send();
        } catch (Exception e) {
            result = "dsGetDataInfoHost error";
        }
        return result;
    }

    public String dsGetDataInfo_dataType_info(int index) {
        String result = "";
        try {
            String contractAddress = getDSListAddress();
            DSList dsList = DSList.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            result = dsList.getDataInfo_dataType_info(new BigInteger("" + index)).send();
        } catch (Exception e) {
            result = "getDataInfo_dataType_info error";
        }
        return result;
    }

    public int dsGetDataInfo_dataType_format(int index) {
        try {
            String contractAddress = getDSListAddress();
            DSList dsList = DSList.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            BigInteger result = dsList.getDataInfo_dataType_format(new BigInteger("" + index)).send();
            return result.intValue();
        } catch (Exception e) {

        }
        return -1;
    }

    public int dsGetDataInfo_dataType_amount(int index) {
        try {
            String contractAddress = getDSListAddress();
            DSList dsList = DSList.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            BigInteger result = dsList.getDataInfo_dataType_amount(new BigInteger("" + index)).send();
            return result.intValue();
        } catch (Exception e) {

        }
        return -1;
    }

    public int dsGetDataInfo_dataType_totalSize(int index) {
        try {
            String contractAddress = getDSListAddress();
            DSList dsList = DSList.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            BigInteger result = dsList.getDataInfo_dataType_totalSize(new BigInteger("" + index)).send();
            return result.intValue();
        } catch (Exception e) {

        }
        return -1;
    }



}
