pragma solidity ^0.4.24;

contract ModelList {
    
    uint globalIndex;
    
    struct API {
        string info;
    }
    
    struct ModelInfo {
        uint id;
        string host;
        int port;
        API api;
    }
    
    ModelInfo[]  ModelInfoList;
    
    function ModelList() {
        globalIndex = 0;
    }

    function getModelInfo_API(uint index)constant public returns(string) {
        API t;
        t = ModelInfoList[index].api;
        return t.info;
    }
    
    function getModelInfo_port(uint index)constant public returns(int) {
        int t;
        t = ModelInfoList[index].port;
        return t;
    }
    
    function getModelInfo_host(uint index)constant public returns(string) {
        string memory t;
        t = ModelInfoList[index].host;
        return t;
    }

    function register(string host, int port, string api) public returns(uint){
        ModelInfo memory model;
        model.id = globalIndex;
        model.host = host;
        model.port = port;
        model.api.info = api;
        ModelInfoList.push(model);
        globalIndex += 1;
        return globalIndex;
    }
    
    function getGlobalIndex() constant public returns (uint) {
        return globalIndex;
    }
}
