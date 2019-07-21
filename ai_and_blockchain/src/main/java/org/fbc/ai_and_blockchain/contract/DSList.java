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
public class DSList extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b5060008081905550610922806100276000396000f30060806040526004361061008e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806318f305b11461009357806357e74faf146101395780637fc96c521461017a57806380d63681146101bb5780639f176158146101e6578063b02fc1f114610227578063b3ca64b814610358578063e245f1b0146103fe575b600080fd5b34801561009f57600080fd5b506100be6004803603810190808035906020019092919050505061043f565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100fe5780820151818401526020810190506100e3565b50505050905090810190601f16801561012b5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561014557600080fd5b5061016460048036038101908080359060200190929190505050610509565b6040518082815260200191505060405180910390f35b34801561018657600080fd5b506101a56004803603810190808035906020019092919050505061053b565b6040518082815260200191505060405180910390f35b3480156101c757600080fd5b506101d061056a565b6040518082815260200191505060405180910390f35b3480156101f257600080fd5b5061021160048036038101908080359060200190929190505050610573565b6040518082815260200191505060405180910390f35b34801561023357600080fd5b50610342600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192908035906020019092919080359060200190929190803590602001909291905050506105a5565b6040518082815260200191505060405180910390f35b34801561036457600080fd5b50610383600480360381019080803590602001909291905050506106f8565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156103c35780820151818401526020810190506103a8565b50505050905090810190601f1680156103f05780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561040a57600080fd5b50610429600480360381019080803590602001909291905050506107bf565b6040518082815260200191505060405180910390f35b60608060018381548110151561045157fe5b90600052602060002090600802016003016001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104f95780601f106104ce576101008083540402835291602001916104f9565b820191906000526020600020905b8154815290600101906020018083116104dc57829003601f168201915b5050505050905080915050919050565b60008060018381548110151561051b57fe5b906000526020600020906008020160030160040154905080915050919050565b60008060018381548110151561054d57fe5b906000526020600020906008020160020154905080915050919050565b60008054905090565b60008060018381548110151561058557fe5b906000526020600020906008020160030160030154905080915050919050565b60006105af6107f1565b60005481600001818152505088816020018190525087816040018181525050868160600151602001819052508481606001516060018181525050838160600151608001818152505082816060015160400181815250508581606001516000018190525060018190806001815401808255809150509060018203906000526020600020906008020160009091929091909150600082015181600001556020820151816001019080519060200190610666929190610821565b50604082015181600201556060820151816003016000820151816000019080519060200190610696929190610821565b5060208201518160010190805190602001906106b3929190610821565b50604082015181600201556060820151816003015560808201518160040155505050505060016000808282540192505081905550600054915050979650505050505050565b60608060018381548110151561070a57fe5b90600052602060002090600802016001018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107af5780601f10610784576101008083540402835291602001916107af565b820191906000526020600020905b81548152906001019060200180831161079257829003601f168201915b5050505050905080915050919050565b6000806001838154811015156107d157fe5b906000526020600020906008020160030160020154905080915050919050565b6101006040519081016040528060008152602001606081526020016000815260200161081b6108a1565b81525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061086257805160ff1916838001178555610890565b82800160010185558215610890579182015b8281111561088f578251825591602001919060010190610874565b5b50905061089d91906108d1565b5090565b60a06040519081016040528060608152602001606081526020016000815260200160008152602001600081525090565b6108f391905b808211156108ef5760008160009055506001016108d7565b5090565b905600a165627a7a723058206131f0a86237ed41d16ea41556d73ab16e24241a7a6c6a0fa86c56fdf15a688d0029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getDataInfo_dataType_info\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getDataInfo_dataType_totalSize\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getDataInfo_port\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getGlobalIndex\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getDataInfo_dataType_amount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"host\",\"type\":\"string\"},{\"name\":\"port\",\"type\":\"int256\"},{\"name\":\"info\",\"type\":\"string\"},{\"name\":\"description\",\"type\":\"string\"},{\"name\":\"amount\",\"type\":\"uint256\"},{\"name\":\"totalSize\",\"type\":\"uint256\"},{\"name\":\"format\",\"type\":\"uint256\"}],\"name\":\"register\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getDataInfo_host\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"getDataInfo_dataType_format\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";

    public static final String FUNC_GETDATAINFO_DATATYPE_INFO = "getDataInfo_dataType_info";

    public static final String FUNC_GETDATAINFO_DATATYPE_TOTALSIZE = "getDataInfo_dataType_totalSize";

    public static final String FUNC_GETDATAINFO_PORT = "getDataInfo_port";

    public static final String FUNC_GETGLOBALINDEX = "getGlobalIndex";

    public static final String FUNC_GETDATAINFO_DATATYPE_AMOUNT = "getDataInfo_dataType_amount";

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_GETDATAINFO_HOST = "getDataInfo_host";

    public static final String FUNC_GETDATAINFO_DATATYPE_FORMAT = "getDataInfo_dataType_format";

    @Deprecated
    protected DSList(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DSList(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DSList(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DSList(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<String> getDataInfo_dataType_info(BigInteger index) {
        final Function function = new Function(FUNC_GETDATAINFO_DATATYPE_INFO, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getDataInfo_dataType_totalSize(BigInteger index) {
        final Function function = new Function(FUNC_GETDATAINFO_DATATYPE_TOTALSIZE, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getDataInfo_port(BigInteger index) {
        final Function function = new Function(FUNC_GETDATAINFO_PORT, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getGlobalIndex() {
        final Function function = new Function(FUNC_GETGLOBALINDEX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getDataInfo_dataType_amount(BigInteger index) {
        final Function function = new Function(FUNC_GETDATAINFO_DATATYPE_AMOUNT, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> register(String host, BigInteger port, String info, String description, BigInteger amount, BigInteger totalSize, BigInteger format) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new Utf8String(host),
                new Int256(port),
                new Utf8String(info),
                new Utf8String(description),
                new Uint256(amount),
                new Uint256(totalSize),
                new Uint256(format)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void register(String host, BigInteger port, String info, String description, BigInteger amount, BigInteger totalSize, BigInteger format, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new Utf8String(host),
                new Int256(port),
                new Utf8String(info),
                new Utf8String(description),
                new Uint256(amount),
                new Uint256(totalSize),
                new Uint256(format)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String registerSeq(String host, BigInteger port, String info, String description, BigInteger amount, BigInteger totalSize, BigInteger format) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(new Utf8String(host),
                new Int256(port),
                new Utf8String(info),
                new Utf8String(description),
                new Uint256(amount),
                new Uint256(totalSize),
                new Uint256(format)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<String> getDataInfo_host(BigInteger index) {
        final Function function = new Function(FUNC_GETDATAINFO_HOST, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getDataInfo_dataType_format(BigInteger index) {
        final Function function = new Function(FUNC_GETDATAINFO_DATATYPE_FORMAT, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static DSList load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DSList(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DSList load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DSList(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DSList load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DSList(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DSList load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DSList(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DSList> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DSList.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<DSList> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DSList.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DSList> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DSList.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DSList> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DSList.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
