pragma solidity ^0.4.24;

contract DSList {
    
    uint globalIndex;
    
    struct DataType {
        string description;
        string info;
        uint format;
        uint amount;
        uint totalSize;
    }
    
    struct DataInfo {
        uint id;
        string host;
        int port;
        DataType dataType;
    }
    
    DataInfo[]  DataInfoList;
    
    function DSList() {
        globalIndex = 0;
    }

    function getDataInfo_dataType_info(uint index)constant public returns(string) {
        string memory t;
        t = DataInfoList[index].dataType.info;
        return t;
    }
    function getDataInfo_dataType_format(uint index)constant public returns(uint) {
        uint t;
        t = DataInfoList[index].dataType.format;
        return t;
    }
    function getDataInfo_dataType_amount(uint index)constant public returns(uint) {
        uint t;
        t = DataInfoList[index].dataType.amount;
        return t;
    }
    function getDataInfo_dataType_totalSize(uint index)constant public returns(uint) {
        uint t;
        t = DataInfoList[index].dataType.totalSize;
        return t;
    }    
    
    function getDataInfo_port(uint index)constant public returns(int) {
        int t;
        t = DataInfoList[index].port;
        return t;
    }
    
    function getDataInfo_host(uint index)constant public returns(string) {
        string memory t;
        t = DataInfoList[index].host;
        return t;
    }

    function register(string host, int port, string info, string description, uint amount, uint totalSize, uint format) public  returns(uint){
        DataInfo memory data;
        data.id = globalIndex;
        data.host = host;
        data.port = port;
        
        data.dataType.info = info;
        data.dataType.amount = amount;
        data.dataType.totalSize = totalSize;
        data.dataType.format = format;
        data.dataType.description = description;

        DataInfoList.push(data);
        globalIndex += 1;
        return globalIndex;
    }
    
    function getGlobalIndex() constant returns (uint) {
        return globalIndex;
    }
}
