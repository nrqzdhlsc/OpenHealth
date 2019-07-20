package org.fbc.ai_and_blockchain.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.fbc.ai_and_blockchain.contract.DataBus;
import org.fbc.ai_and_blockchain.utils.*;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple4;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class ReencryptionService {
    private Web3j web3j;

    private Credentials credentials;

    private HashMap<String, String> data = new HashMap();

    private List<String> records = new ArrayList<>();

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

    public void recordAssetAddr(String address) throws FileNotFoundException, IOException {
        Properties prop = new Properties();
        prop.setProperty("address", address);
        final Resource contractResource = new ClassPathResource("contract.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
        prop.store(fileOutputStream, "contract address");
    }

    public String loadAssetAddr() throws Exception {
        // load Asset contact address from contract.properties
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());

        String contractAddress = prop.getProperty("address");
        if (contractAddress == null || contractAddress.trim().equals("")) {
            throw new Exception(" load Asset contract address failed, please deploy it first. ");
        }
        return contractAddress;
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

    public String deployDataBusAndRecordAddr() {
        String result = "";
        try {
            DataBus dataBus = DataBus.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
            System.out.println(" deploy Asset success, contract address is " + dataBus.getContractAddress());
            recordAssetAddr(dataBus.getContractAddress());
            result = dataBus.getContractAddress();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            System.out.println(" deploy Asset contract failed, error message is  " + e.getMessage());
            result = " deploy Asset contract failed";
        }
        return result;
    }

    public String genKey(String account) {
        JSONObject map = new JSONObject();
        map.put("account", account);
        String params = map.toString();
        String response = HttpUtil.post(CommonFields.PY_URL + "/gen_key", params);
        return response;
    }

    public int superiorRegister(String account, String publicKey, String verifyingKey) {
        int ret_code = 0;
        try {
            String contractAddress = loadAssetAddr();
            DataBus dataBus = DataBus.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = dataBus.superiorRegister(account, publicKey, verifyingKey).send();
            List<DataBus.SuperiorRegisterEventEventResponse> response = dataBus.getSuperiorRegisterEventEvents(receipt);
            if (!response.isEmpty()) {
                if (response.get(0).ret.compareTo(new BigInteger("0")) == 0) {
                    System.out.printf(" register superior user success => account: %s \n", account);
                } else {
                    System.out.printf(" register superior user failed, ret code is %s \n",
                            response.get(0).ret.toString());
                    ret_code = 1;
                }
            } else {
                System.out.println(" event log not found, maybe transaction not exec. ");
                ret_code = 1;
            }
        } catch (Exception e) {
            ret_code = 1;
        }
        return ret_code;
    }

    public int subordinateRegister(String account, String superior, String publicKey, String verifyingKey, int type) {
        int ret_code = 0;
        try {
            String contractAddress = loadAssetAddr();
            BigInteger userType = new BigInteger(type + "");
            DataBus dataBus = DataBus.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = dataBus.subordinateRegister(account, superior, publicKey, verifyingKey, userType).send();
            List<DataBus.SubordinateRegisterEventEventResponse> response = dataBus.getSubordinateRegisterEventEvents(receipt);
            if (!response.isEmpty()) {
                if (response.get(0).ret.compareTo(new BigInteger("0")) == 0) {
                    System.out.printf(" register subordinate user success => account: %s \n", account);
                } else {
                    System.out.printf(" register subordinate user failed, ret code is %s \n",
                            response.get(0).ret.toString());
                    ret_code = 1;
                }
            } else {
                System.out.println(" event log not found, maybe transaction not exec. ");
                ret_code = 1;
            }
        } catch (Exception e) {
            System.out.println("register subordinate error");
            ret_code = 1;
        }
        return ret_code;
    }

    public List<User> getAllPatients() {
        List<User> result = new ArrayList<>();
        try {
            String contractAddress = loadAssetAddr();
            DataBus dataBus = DataBus.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            List<BigInteger> allIndexs = dataBus.getAllPatient().send();
            for (BigInteger i : allIndexs) {
                Tuple4<String, String, String, BigInteger> userInfo = dataBus.getUserByIndex(i).send();
                User user = new User(userInfo.getValue1(), userInfo.getValue2(), userInfo.getValue3(), userInfo.getValue4());
                result.add(user);
            }
            return result;
        } catch (Exception e) {
            return result;
        }
    }

    public String getUserPubKey(String account) {
        try {
            String contractAddress = loadAssetAddr();

            DataBus dataBus = DataBus.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            String result = dataBus.getUserPubKey(account).send();
            return result;
        } catch (Exception e) {
            return "get public key error";
        }
    }

    public String getUserVerifyingKey(String account) {
        try {
            String contractAddress = loadAssetAddr();

            DataBus dataBus = DataBus.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            String result = dataBus.getUserVerifyKey(account).send();
            return result;
        } catch (Exception e) {
            return "get verifying key error";
        }
    }

    public SuperResult uploadData(String account, String owner, String cipheretext, String capsule) {
        Map<String, String> returnMap = new HashMap<>();
        try {
            String dataId = UUID.randomUUID().toString();
            System.out.println("data id is : " + dataId);
            data.put("cipheretext", cipheretext);
            data.put("capsule", capsule);
            String contractAddress = loadAssetAddr();
            DataBus dataBus = DataBus.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = dataBus.uploadData(account, owner, dataId, "RAM").send();
            List<DataBus.UploadDataEventEventResponse> response = dataBus.getUploadDataEventEvents(receipt);
            if (!response.isEmpty()) {
                if (response.get(0).ret.compareTo(new BigInteger("0")) == 0) {
                    System.out.printf(" uploadData Success => account: %s \n", account);
                    returnMap.put("dataId", dataId);
                    return SuperResult.ok(returnMap);
                } else {
                    System.out.printf(" uploadData fails, retCode is %s \n",
                            response.get(0).ret.toString());
                }
            } else {
                System.out.println(" event log not found, maybe transaction not exec. ");
            }
        } catch (Exception e) {
            System.out.println("upload data error");
        }
        return new SuperResult(500, null);
    }

    public String getCiphereText() {
        return data.get("cipheretext");
    }

    public String getCapsule() {
        return data.get("capsule");
    }

    public SuperResult requestForData(String account, String dataId) {
        Map<String, String> returnMap = new HashMap<>();
        try {
            String requestId = UUID.randomUUID().toString();
            String contractAddress = loadAssetAddr();
            DataBus dataBus = DataBus.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = dataBus.requestForData(account, requestId, dataId).send();
            List<DataBus.RequestForDataEventEventResponse> response = dataBus.getRequestForDataEventEvents(receipt);
            if (!response.isEmpty()) {
                if (response.get(0).ret.compareTo(new BigInteger("0")) == 0) {
                    System.out.printf(" request for data Success => account: %s, dataId: %s \n", account, dataId);
                    returnMap.put("requestId", requestId);
                    return SuperResult.ok(returnMap);
                } else {
                    System.out.printf(" request for data fails, retCode is %s \n",
                            response.get(0).ret.toString());
                }
            } else {
                System.out.println(" event log not found, maybe transaction not exec. ");
            }
            Tuple4<String, String, String, String> dataInfo = dataBus.getData(account, dataId).send();
            Data data = new Data(dataInfo.getValue1(), dataInfo.getValue2(), dataInfo.getValue3(), dataInfo.getValue4());
            String record = account + "申请查看" + data.getOwner() + "的病例数据";
            records.add(record);
        } catch (Exception e) {
            System.out.println("request data error");
        }
        return new SuperResult(500, null);
    }

    public SuperResult getYourRequest(String account) {
        Map<String, String> returnMap = new HashMap<>();
        try {
            List<Request> allRequests = new ArrayList<>();
            String contractAddress = loadAssetAddr();
            DataBus dataBus = DataBus.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            List<BigInteger> response = dataBus.getYourRequests(account).send();
            for (BigInteger i : response) {
                Tuple4<String, String, String, BigInteger> result = dataBus.getRequest(i).send();
                Request request = new Request(result.getValue1(), result.getValue2(), result.getValue3(), result.getValue4());
                allRequests.add(request);
            }
            String result = JSON.toJSONString(allRequests);
            returnMap.put("list", result);
            return SuperResult.ok(returnMap);
        } catch (Exception e) {
            System.out.println("Get requests error");
        }
        return new SuperResult(500, null);
    }

    public String getRequestById(String requestId) {
        try {
            String contractAddress = loadAssetAddr();
            DataBus dataBus = DataBus.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            Tuple4<String, String, String, BigInteger> result = dataBus.getRequestByRequestId(requestId).send();
            Request request = new Request(result.getValue1(), result.getValue2(), result.getValue3(), result.getValue4());
            String response = JSON.toJSONString(request);
            return response;
        } catch (Exception e) {
            return "get request by id error";
        }
    }

    public int agreeForRequest(String account, String requestId) {
        int ret_code = 0;
        try {
            String result = "";
            String contractAddress = loadAssetAddr();
            DataBus dataBus = DataBus.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            TransactionReceipt receipt = dataBus.agreeForRequest(account, requestId).send();
            List<DataBus.AgreeForRequestEventEventResponse> response = dataBus.getAgreeForRequestEventEvents(receipt);
            if (!response.isEmpty()) {
                if (response.get(0).ret.compareTo(new BigInteger("0")) == 0) {
                    System.out.printf(" agree for request Success => account: %s, requestId: %s \n", account, requestId);
                } else {
                    System.out.printf(" agree for request fails, retCode is %s \n",
                            response.get(0).ret.toString());
                    ret_code = 1;
                }
            } else {
                System.out.println(" event log not found, maybe transaction not exec. ");
                ret_code = 1;
            }
        } catch (Exception e) {
            System.out.println("agree for request error");
            ret_code = 1;
        }
        return ret_code;
    }

    public List<String> getAllRecords() {
        return records;
    }

    public String getData(String account, String dataId) {
        try {
            String contractAddress = loadAssetAddr();
            DataBus dataBus = DataBus.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
            Tuple4<String, String, String, String> result = dataBus.getData(account, dataId).send();
            Data chainData = new Data(result.getValue1(), result.getValue2(), result.getValue3(), result.getValue4());
            String accessPubKey = getUserPubKey(account);
            JSONObject map = new JSONObject();
            map.put("owner", chainData.getOwner());
            map.put("reader", account);
            map.put("ciphertext", getCiphereText());
            map.put("capsule", getCapsule());
            String response = HttpUtil.post(CommonFields.PY_URL + "/getData", map.toString());
            return response;
        } catch (Exception e) {
            return "get data error";
        }
    }


}
