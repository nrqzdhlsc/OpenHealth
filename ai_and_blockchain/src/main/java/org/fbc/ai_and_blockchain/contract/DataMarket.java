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
public class DataMarket extends Contract {
    public static final String BINARY = "60806040527325a21ce10ae5fdfdcaacefc99f995e8aca019a9a6000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555073c900e395ee1a58e85c348dd3610beb0e5ef5d775600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555034801561017e57600080fd5b50600060058190555060006006819055506114168061019e6000396000f300608060405260043610610107576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630857841a1461010c5780631696fb7d146101375780631a6a2ae3146101785780632f8b115b1461021e578063367423b71461025f5780636d4ce63c146102a05780636eb207f4146102cb57806379b531b91461030c5780638051619e1461034d57806380d63681146103645780638b877e6e1461038f5780639998747e14610435578063bd07f03c14610460578063c7721e2d146104ab578063d4ccefbc14610551578063d902e54c14610592578063eabd9f29146105d3578063f6d7434a14610679578063ff7e32b4146106ba575b600080fd5b34801561011857600080fd5b506101216106fb565b6040518082815260200191505060405180910390f35b34801561014357600080fd5b50610162600480360381019080803590602001909291905050506107c3565b6040518082815260200191505060405180910390f35b34801561018457600080fd5b506101a360048036038101908080359060200190929190505050610898565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156101e35780820151818401526020810190506101c8565b50505050905090810190601f1680156102105780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561022a57600080fd5b50610249600480360381019080803590602001909291905050506109c3565b6040518082815260200191505060405180910390f35b34801561026b57600080fd5b5061028a600480360381019080803590602001909291905050506109f2565b6040518082815260200191505060405180910390f35b3480156102ac57600080fd5b506102b5610a21565b6040518082815260200191505060405180910390f35b3480156102d757600080fd5b506102f660048036038101908080359060200190929190505050610ae9565b6040518082815260200191505060405180910390f35b34801561031857600080fd5b5061033760048036038101908080359060200190929190505050610bbe565b6040518082815260200191505060405180910390f35b34801561035957600080fd5b50610362610c93565b005b34801561037057600080fd5b50610379610ca7565b6040518082815260200191505060405180910390f35b34801561039b57600080fd5b506103ba60048036038101908080359060200190929190505050610cb1565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156103fa5780820151818401526020810190506103df565b50505050905090810190601f1680156104275780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561044157600080fd5b5061044a610ddc565b6040518082815260200191505060405180910390f35b34801561046c57600080fd5b506104a960048036038101908080359060200190929190803590602001909291908035906020019092919080359060200190929190505050610ea4565b005b3480156104b757600080fd5b506104d660048036038101908080359060200190929190505050610f5c565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156105165780820151818401526020810190506104fb565b50505050905090810190601f1680156105435780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561055d57600080fd5b5061057c60048036038101908080359060200190929190505050611087565b6040518082815260200191505060405180910390f35b34801561059e57600080fd5b506105bd6004803603810190808035906020019092919050505061115c565b6040518082815260200191505060405180910390f35b3480156105df57600080fd5b506105fe60048036038101908080359060200190929190505050611231565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561063e578082015181840152602081019050610623565b50505050905090810190601f16801561066b5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561068557600080fd5b506106a46004803603810190808035906020019092919050505061135c565b6040518082815260200191505060405180910390f35b3480156106c657600080fd5b506106e56004803603810190808035906020019092919050505061138b565b6040518082815260200191505060405180910390f35b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166380d636816040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561078357600080fd5b505af1158015610797573d6000803e3d6000fd5b505050506040513d60208110156107ad57600080fd5b8101908080519060200190929190505050905090565b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a2d31d1f836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561085657600080fd5b505af115801561086a573d6000803e3d6000fd5b505050506040513d602081101561088057600080fd5b81019080805190602001909291905050509050919050565b6060600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166318f305b1836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b15801561092b57600080fd5b505af115801561093f573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250602081101561096957600080fd5b81019080805164010000000081111561098157600080fd5b8281019050602081018481111561099757600080fd5b81518560018202830111640100000000821117156109b457600080fd5b50509291905050509050919050565b6000806004838154811015156109d557fe5b906000526020600020906005020160040154905080915050919050565b600080600483815481101515610a0457fe5b906000526020600020906005020160010154905080915050919050565b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166380d636816040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610aa957600080fd5b505af1158015610abd573d6000803e3d6000fd5b505050506040513d6020811015610ad357600080fd5b8101908080519060200190929190505050905090565b6000600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663e245f1b0836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015610b7c57600080fd5b505af1158015610b90573d6000803e3d6000fd5b505050506040513d6020811015610ba657600080fd5b81019080805190602001909291905050509050919050565b6000600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639f176158836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b158015610c5157600080fd5b505af1158015610c65573d6000803e3d6000fd5b505050506040513d6020811015610c7b57600080fd5b81019080805190602001909291905050509050919050565b600660008154809291906001019190505550565b6000600554905090565b6060600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663b3ca64b8836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b158015610d4457600080fd5b505af1158015610d58573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f820116820180604052506020811015610d8257600080fd5b810190808051640100000000811115610d9a57600080fd5b82810190506020810184811115610db057600080fd5b8151856001820283011164010000000082111715610dcd57600080fd5b50509291905050509050919050565b6000600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166380d636816040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b158015610e6457600080fd5b505af1158015610e78573d6000803e3d6000fd5b505050506040513d6020811015610e8e57600080fd5b8101908080519060200190929190505050905090565b610eac6113ba565b848160000181815250508381602001818152505060055481604001818152505082816080018181525050818160600181815250506005600081548092919060010191905055506004819080600181540180825580915050906001820390600052602060002090600502016000909192909190915060008201518160000155602082015181600101556040820151816002015560608201518160030155608082015181600401555050505050505050565b6060600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a9cac9fc836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b158015610fef57600080fd5b505af1158015611003573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250602081101561102d57600080fd5b81019080805164010000000081111561104557600080fd5b8281019050602081018481111561105b57600080fd5b815185600182028301116401000000008211171561107857600080fd5b50509291905050509050919050565b6000600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16637fc96c52836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b15801561111a57600080fd5b505af115801561112e573d6000803e3d6000fd5b505050506040513d602081101561114457600080fd5b81019080805190602001909291905050509050919050565b6000600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166357e74faf836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050602060405180830381600087803b1580156111ef57600080fd5b505af1158015611203573d6000803e3d6000fd5b505050506040513d602081101561121957600080fd5b81019080805190602001909291905050509050919050565b6060600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16632e73d153836040518263ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180828152602001915050600060405180830381600087803b1580156112c457600080fd5b505af11580156112d8573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f82011682018060405250602081101561130257600080fd5b81019080805164010000000081111561131a57600080fd5b8281019050602081018481111561133057600080fd5b815185600182028301116401000000008211171561134d57600080fd5b50509291905050509050919050565b60008060048381548110151561136e57fe5b906000526020600020906005020160000154905080915050919050565b60008060048381548110151561139d57fe5b906000526020600020906005020160030154905080915050919050565b60a060405190810160405280600081526020016000815260200160008152602001600081526020016000815250905600a165627a7a72305820a09116b1b3de223f2394ecc4373e715339f22bfe67cdcf91fd04c358c28ae9a20029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[],\"name\":\"ML_getGlobalIndex\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"ML_getModelInfo_port\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"DS_getDataInfo_dataType_info\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"txIndex\",\"type\":\"uint256\"}],\"name\":\"getTxValue\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"txIndex\",\"type\":\"uint256\"}],\"name\":\"getTxDataIndex\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"DS_getDataInfo_dataType_format\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"DS_getDataInfo_dataType_amount\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[],\"name\":\"increaseGroupIndex\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"getGlobalIndex\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"DS_getDataInfo_host\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"DS_getGlobalIndex\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"modelIndex\",\"type\":\"int256\"},{\"name\":\"dataIndex\",\"type\":\"int256\"},{\"name\":\"txValue\",\"type\":\"uint256\"},{\"name\":\"groupIndex\",\"type\":\"int256\"}],\"name\":\"matchData\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"ML_getModelInfo_host\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"DS_getDataInfo_port\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"DS_getDataInfo_dataType_totalSize\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"index\",\"type\":\"uint256\"}],\"name\":\"ML_getModelInfo_API\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"txIndex\",\"type\":\"uint256\"}],\"name\":\"getTxModelIndex\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"txIndex\",\"type\":\"uint256\"}],\"name\":\"getTxGroupIndex\",\"outputs\":[{\"name\":\"\",\"type\":\"int256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";

    public static final String FUNC_ML_GETGLOBALINDEX = "ML_getGlobalIndex";

    public static final String FUNC_ML_GETMODELINFO_PORT = "ML_getModelInfo_port";

    public static final String FUNC_DS_GETDATAINFO_DATATYPE_INFO = "DS_getDataInfo_dataType_info";

    public static final String FUNC_GETTXVALUE = "getTxValue";

    public static final String FUNC_GETTXDATAINDEX = "getTxDataIndex";

    public static final String FUNC_GET = "get";

    public static final String FUNC_DS_GETDATAINFO_DATATYPE_FORMAT = "DS_getDataInfo_dataType_format";

    public static final String FUNC_DS_GETDATAINFO_DATATYPE_AMOUNT = "DS_getDataInfo_dataType_amount";

    public static final String FUNC_INCREASEGROUPINDEX = "increaseGroupIndex";

    public static final String FUNC_GETGLOBALINDEX = "getGlobalIndex";

    public static final String FUNC_DS_GETDATAINFO_HOST = "DS_getDataInfo_host";

    public static final String FUNC_DS_GETGLOBALINDEX = "DS_getGlobalIndex";

    public static final String FUNC_MATCHDATA = "matchData";

    public static final String FUNC_ML_GETMODELINFO_HOST = "ML_getModelInfo_host";

    public static final String FUNC_DS_GETDATAINFO_PORT = "DS_getDataInfo_port";

    public static final String FUNC_DS_GETDATAINFO_DATATYPE_TOTALSIZE = "DS_getDataInfo_dataType_totalSize";

    public static final String FUNC_ML_GETMODELINFO_API = "ML_getModelInfo_API";

    public static final String FUNC_GETTXMODELINDEX = "getTxModelIndex";

    public static final String FUNC_GETTXGROUPINDEX = "getTxGroupIndex";

    @Deprecated
    protected DataMarket(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected DataMarket(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected DataMarket(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected DataMarket(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> ML_getGlobalIndex() {
        final Function function = new Function(FUNC_ML_GETGLOBALINDEX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> ML_getModelInfo_port(BigInteger index) {
        final Function function = new Function(FUNC_ML_GETMODELINFO_PORT, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> DS_getDataInfo_dataType_info(BigInteger index) {
        final Function function = new Function(FUNC_DS_GETDATAINFO_DATATYPE_INFO, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getTxValue(BigInteger txIndex) {
        final Function function = new Function(FUNC_GETTXVALUE, 
                Arrays.<Type>asList(new Uint256(txIndex)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getTxDataIndex(BigInteger txIndex) {
        final Function function = new Function(FUNC_GETTXDATAINDEX, 
                Arrays.<Type>asList(new Uint256(txIndex)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> get() {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> DS_getDataInfo_dataType_format(BigInteger index) {
        final Function function = new Function(FUNC_DS_GETDATAINFO_DATATYPE_FORMAT, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> DS_getDataInfo_dataType_amount(BigInteger index) {
        final Function function = new Function(FUNC_DS_GETDATAINFO_DATATYPE_AMOUNT, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> increaseGroupIndex() {
        final Function function = new Function(
                FUNC_INCREASEGROUPINDEX, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void increaseGroupIndex(TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_INCREASEGROUPINDEX, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String increaseGroupIndexSeq() {
        final Function function = new Function(
                FUNC_INCREASEGROUPINDEX, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<BigInteger> getGlobalIndex() {
        final Function function = new Function(FUNC_GETGLOBALINDEX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> DS_getDataInfo_host(BigInteger index) {
        final Function function = new Function(FUNC_DS_GETDATAINFO_HOST, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> DS_getGlobalIndex() {
        final Function function = new Function(FUNC_DS_GETGLOBALINDEX, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> matchData(BigInteger modelIndex, BigInteger dataIndex, BigInteger txValue, BigInteger groupIndex) {
        final Function function = new Function(
                FUNC_MATCHDATA, 
                Arrays.<Type>asList(new Int256(modelIndex),
                new Int256(dataIndex),
                new Uint256(txValue),
                new Int256(groupIndex)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void matchData(BigInteger modelIndex, BigInteger dataIndex, BigInteger txValue, BigInteger groupIndex, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_MATCHDATA, 
                Arrays.<Type>asList(new Int256(modelIndex),
                new Int256(dataIndex),
                new Uint256(txValue),
                new Int256(groupIndex)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String matchDataSeq(BigInteger modelIndex, BigInteger dataIndex, BigInteger txValue, BigInteger groupIndex) {
        final Function function = new Function(
                FUNC_MATCHDATA, 
                Arrays.<Type>asList(new Int256(modelIndex),
                new Int256(dataIndex),
                new Uint256(txValue),
                new Int256(groupIndex)),
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public RemoteCall<String> ML_getModelInfo_host(BigInteger index) {
        final Function function = new Function(FUNC_ML_GETMODELINFO_HOST, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> DS_getDataInfo_port(BigInteger index) {
        final Function function = new Function(FUNC_DS_GETDATAINFO_PORT, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> DS_getDataInfo_dataType_totalSize(BigInteger index) {
        final Function function = new Function(FUNC_DS_GETDATAINFO_DATATYPE_TOTALSIZE, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> ML_getModelInfo_API(BigInteger index) {
        final Function function = new Function(FUNC_ML_GETMODELINFO_API, 
                Arrays.<Type>asList(new Uint256(index)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<BigInteger> getTxModelIndex(BigInteger txIndex) {
        final Function function = new Function(FUNC_GETTXMODELINDEX, 
                Arrays.<Type>asList(new Uint256(txIndex)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getTxGroupIndex(BigInteger txIndex) {
        final Function function = new Function(FUNC_GETTXGROUPINDEX, 
                Arrays.<Type>asList(new Uint256(txIndex)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Int256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static DataMarket load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new DataMarket(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static DataMarket load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new DataMarket(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static DataMarket load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new DataMarket(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static DataMarket load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new DataMarket(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<DataMarket> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DataMarket.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<DataMarket> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(DataMarket.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DataMarket> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DataMarket.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<DataMarket> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(DataMarket.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
