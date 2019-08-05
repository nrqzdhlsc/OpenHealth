package org.fbc.ai_and_blockchain.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.abi.datatypes.generated.Int256;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class ModelList extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600080819055506106b2806100276000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680632e73d1531461007257806380d636811461011857806396063d1714610143578063a2d31d1f14610210578063a9cac9fc14610251575b600080fd5b34801561007e57600080fd5b5061009d600480360381019080803590602001909291905050506102f7565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100dd5780820151818401526020810190506100c2565b50505050905090810190601f16801561010a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561012457600080fd5b5061012d6103c2565b6040518082815260200191505060405180910390f35b34801561014f57600080fd5b506101fa600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506103cb565b6040518082815260200191505060405180910390f35b34801561021c57600080fd5b5061023b600480360381019080803590602001909291905050506104a8565b6040518082815260200191505060405180910390f35b34801561025d57600080fd5b5061027c600480360381019080803590602001909291905050506104d7565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156102bc5780820151818401526020810190506102a1565b50505050905090810190601f1680156102e95780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6060600060018381548110151561030a57fe5b90600052602060002090600402016003019050806000018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103b55780601f1061038a576101008083540402835291602001916103b5565b820191906000526020600020905b81548152906001019060200180831161039857829003601f168201915b5050505050915050919050565b60008054905090565b60006103d561059e565b6000548160000181815250508481602001819052508381604001818152505082816060015160000181905250600181908060018154018082558091505090600182039060005260206000209060040201600090919290919091506000820151816000015560208201518160010190805190602001906104559291906105cd565b506040820151816002015560608201518160030160008201518160000190805190602001906104859291906105cd565b505050505050600160008082825401925050819055506000549150509392505050565b6000806001838154811015156104ba57fe5b906000526020600020906004020160020154905080915050919050565b6060806001838154811015156104e957fe5b90600052602060002090600402016001018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561058e5780601f106105635761010080835404028352916020019161058e565b820191906000526020600020905b81548152906001019060200180831161057157829003601f168201915b5050505050905080915050919050565b6080604051908101604052806000815260200160608152602001600081526020016105c761064d565b81525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061060e57805160ff191683800117855561063c565b8280016001018555821561063c579182015b8281111561063b578251825591602001919060010190610620565b5b5090506106499190610661565b5090565b602060405190810160405280606081525090565b61068391905b8082111561067f576000816000905550600101610667565b5090565b905600a165627a7a72305820ccbd5c6c914090ac76a5b196a775f49131172d749e0fe957246b3fdd752b1d640029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getModelInfo_API\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getGlobalIndex\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"host\",\"type\":\"string\"},{\"name\":\"port\",\"type\":\"int256\"},{\"name\":\"api\",\"type\":\"string\"}],\"name\":\"register\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getModelInfo_port\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getModelInfo_host\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";

    public static final String FUNC_GETMODELINFO_API = "getModelInfo_API";

    public static final String FUNC_GETGLOBALINDEX = "getGlobalIndex";

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_GETMODELINFO_PORT = "getModelInfo_port";

    public static final String FUNC_GETMODELINFO_HOST = "getModelInfo_host";

    @Deprecated
    protected ModelList(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ModelList(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ModelList(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ModelList(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> getModelInfo_API(BigInteger index) {
        final Function function = new Function(FUNC_GETMODELINFO_API, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getGlobalIndex() {
        final Function function = new Function(FUNC_GETGLOBALINDEX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> register(String host, BigInteger port, String api) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new Utf8String(host),
                new Int256(port),
                new Utf8String(api)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void register(String host, BigInteger port, String api, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new Utf8String(host),
                new Int256(port),
                new Utf8String(api)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String registerSeq(String host, BigInteger port, String api) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new Utf8String(host),
                new Int256(port),
                new Utf8String(api)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> getModelInfo_port(BigInteger index) {
        final Function function = new Function(FUNC_GETMODELINFO_PORT, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> getModelInfo_host(BigInteger index) {
        final Function function = new Function(FUNC_GETMODELINFO_HOST, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static ModelList load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ModelList(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ModelList load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ModelList(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ModelList load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ModelList(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ModelList load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ModelList(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ModelList> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ModelList.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<ModelList> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ModelList.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ModelList> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ModelList.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ModelList> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ModelList.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
