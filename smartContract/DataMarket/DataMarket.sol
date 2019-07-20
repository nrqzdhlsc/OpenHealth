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
    
    function ModelList() {}

    function getModelInfo_API(uint index)constant public returns(string) {}
    
    function getModelInfo_port(uint index)constant public returns(int) {}
    
    function getModelInfo_host(uint index)constant public returns(string) {}

    function register(string host, int port, string api) public returns(uint){}
    
    function getGlobalIndex() constant public returns (uint) {}
}


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
    
    function DSList() {}

    function getDataInfo_dataType_info(uint index)constant public returns(string) {}
    
    function getDataInfo_dataType_format(uint index)constant public returns(uint) {}
    
    function getDataInfo_dataType_amount(uint index)constant public returns(uint) {}
    
    function getDataInfo_dataType_totalSize(uint index)constant public returns(uint) {}    
    
    function getDataInfo_port(uint index)constant public returns(int) {}
    
    function getDataInfo_host(uint index)constant public returns(string) {}

    function register(string host, int port, string info, string description, uint amount, uint totalSize, uint format) public  returns(uint){}
    
    function getGlobalIndex() constant returns (uint) {}
}



contract DataMarket {

    address addrDataList = 0x25a21ce10ae5fdfdcaacefc99f995e8aca019a9a;
    //address addrDataList = 0xbbf289d846208c16edc8474705c748aff07732db;
    address addrModelList = 0xc900e395ee1a58e85c348dd3610beb0e5ef5d775;
    //address addrModelList = 0x692a70d2e424a56d2c6c27aa97d1a86395877b3a;

    ModelList ml = ModelList(addrModelList); 
    DSList dl = DSList(addrDataList);
    

    
    struct tx{
        int ModelIndex;
        int DataIndex;
        int txIndex;
        int groupIndex;
        uint256 txValue;
    }
    
    tx[] txs;
    
    int globalIndex;
    int groupIndex;
    
    function DataMarket(){
        globalIndex = 0;
        groupIndex = 0;
    }
    
    function get()constant returns(uint) {
        return ml.getGlobalIndex();
    }
    
    function getGlobalIndex() constant public returns(int){
        return globalIndex;
    }
    
    function increaseGroupIndex() public {
        groupIndex++;
    }
    
    function matchData(int modelIndex, int dataIndex,uint256 txValue,int groupIndex){
        tx memory t;
        t.ModelIndex = modelIndex;
        t.DataIndex = dataIndex;
        t.txIndex = globalIndex;
        t.txValue = txValue;
        t.groupIndex = groupIndex;
        
        globalIndex++;
        txs.push(t);
    } 
    
    function getTxModelIndex(uint txIndex) constant returns (int){
        int t;
        t = txs[txIndex].ModelIndex;
        return t;
    }
    
    function getTxDataIndex(uint txIndex) constant returns (int){
        int t;
        t = txs[txIndex].DataIndex;
        return t;
    }
    function getTxValue(uint txIndex) constant returns (uint256){
        uint256 t;
        t = txs[txIndex].txValue;
        return t;
    }
    function getTxGroupIndex(uint txIndex) constant returns (int){
        int t;
        t = txs[txIndex].groupIndex;
        return t;
    }
    
    
    function DS_getDataInfo_dataType_info(uint index)constant public returns(string) {return dl.getDataInfo_dataType_info(index);}
    
    function DS_getDataInfo_dataType_format(uint index)constant public returns(uint) {return dl.getDataInfo_dataType_format(index);}
    
    function DS_getDataInfo_dataType_amount(uint index)constant public returns(uint) {return dl.getDataInfo_dataType_amount(index);}
    
    function DS_getDataInfo_dataType_totalSize(uint index)constant public returns(uint) {return dl.getDataInfo_dataType_totalSize(index);}    
    
    function DS_getDataInfo_port(uint index)constant public returns(int) {return dl.getDataInfo_port(index);}
    
    function DS_getDataInfo_host(uint index)constant public returns(string) {return dl.getDataInfo_host(index);}
    
    function DS_getGlobalIndex() constant public returns(uint){return dl.getGlobalIndex();}
    
    function ML_getModelInfo_API(uint index)constant public returns(string) {return ml.getModelInfo_API(index);}
    
    function ML_getModelInfo_port(uint index)constant public returns(int) {return ml.getModelInfo_port(index);}
    
    function ML_getModelInfo_host(uint index)constant public returns(string) {return ml.getModelInfo_host(index);}
    
    function ML_getGlobalIndex() constant public returns(uint){return ml.getGlobalIndex();}
}
