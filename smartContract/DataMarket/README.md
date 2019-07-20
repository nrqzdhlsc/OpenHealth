# ModelList.sol

维护全局结构数组ModelInfoList来记录模型方的模型信息（科学家，有模型无数据的一方）

## Struct

全局结构体数组的结构类型说明：

    struct API {
        string info;
    }      

    struct ModelInfo {
        uint id;
        string host;
        int port;
        API api;
    }

- id代表模型的primaryKey，也叫globalIndex
- host代表模型Client的服务器ip
- port代表模型Client的服务器端口
- api代表模型Client的模型信息（例如调用方式，需要调配的参数，这里用一个string代替）

## API
函数API说明：

    function register(string host, int port, string api) public returns(uint){}

    function getGlobalIndex() constant public returns (uint) {}

    function getModelInfo_host(uint index)constant public returns(string) {}

    function getModelInfo_port(uint index)constant public returns(int) {}

    function getModelInfo_API(uint index)constant public returns(string) {}
        
- register:模型注册，输入上述参数，进行模型的信息注册，globalIndex会自增。
- getGlobalIndex:获取当前全局index，获取循环模型信息的循环上限。
- getModelInfo_host:输入全局index，获取对应的服务器地址。
- getModelInfo_port:输入全局index，获取对应的服务器端口。
- getModelInfo_API:输入全局的index，获取对应的模型API



# DSList.sol

维护全局结构数组DataInfoList来记录数据方的数据信息（医院或者机构，有数据无模型的一方）

## Struct
全局结构体数组的结构类型说明：

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

- id代表数据的primaryKey，也叫globalIndex
- host代表数据方Worker的服务器ip
- port代表数据方Worker的服务器端口
- description代表数据Worker的数据描述（例如数据是由某段时间的什么病人的什么数据采集得到的）
- info代表数据Worker的数据调用方式（例如调用方式，需要调配的参数，这里用一个string代替）
- format代表数据的格式
- amount代表数据的数量
- totalsize代表数据的大小

## API

函数API说明：

    function register(string host, int port, string info, string description, uint amount, uint totalSize, uint format) public  returns(uint){}
    
    function getGlobalIndex() constant returns (uint) {}
    
    function getDataInfo_port(uint index)constant public returns(int) {}
    
    function getDataInfo_host(uint index)constant public returns(string) {}

    function getDataInfo_dataType_info(uint index)constant public returns(string) {}
    
    function getDataInfo_dataType_format(uint index)constant public returns(uint) {}
    
    function getDataInfo_dataType_amount(uint index)constant public returns(uint) {}
    
    function getDataInfo_dataType_totalSize(uint index)constant public returns(uint) {}    

- register:数据注册，输入上述参数，进行数据的信息注册，globalIndex会自增。
- getGlobalIndex:获取当前全局index，获取循环数据信息的循环上限。
- getDataInfo_host:输入全局index，获取对应的服务器地址。
- getDataInfo_port:输入全局index，获取对应的服务器端口。

- getDataInfo_dataType_description:输入全局index，获取数据描述

- getDataInfo_dataType_info:输入全局index，获取数据调用信息

- getDataInfo_dataType_format:输入全局index，获取数据类型

- getDataInfo_dataType_amount:输入全局index，获取数据数量

- getDataInfo_dataType_totalSize:输入全局index，获取数据大小

# DataMarket.sol

## Struct

全局结构体数组的结构类型说明：

    struct tx{
    int ModelIndex;
    int DataIndex;
    int txIndex;
    int groupIndex;
    uint256 txValue;
}

- txIndex代表数据的primaryKey，值为globalIndex
- groupIndex代表数据属于联邦学习的哪一组（比如科学家选了三组数据，那么需要调用match三次，这三次的group值应为一个值）
- DataIndex代表数据的匹配的index号，对应DSList合约中的 data primaryKey
- ModelIndex代表模型的匹配的index号，对应ModelList合约中的 model primaryKey
- txValue代表匹配中模型方向数据方传输的价值，通常不同的数据类型与数据大小对应的价值也不同


## API

本体函数API说明：

    function getGlobalIndex() constant public returns(int){}
    
    function increaseGroupIndex() public {}
    
    function matchData(int modelIndex, int dataIndex,uint256 txValue,int groupIndex){} 
    
    function getTxModelIndex(uint txIndex) constant returns (int){}
    
    function getTxDataIndex(uint txIndex) constant returns (int){}

    function getTxValue(uint txIndex) constant returns (uint256){}

    function getTxGroupIndex(uint txIndex) constant returns (int){}

- getGlobalIndex:获取当前合约下的全局index
- increaseGroupIndex:增加increateGroupIndex
- matchData:核心函数，匹配Model源和Data源，并注册在txs中
- getTxModelIndex:输入全局index，获取模型index
- getTxDataIndex:输入全局index，获取数据index
- getTxValue:输入全局index，获取交易价值
- getTxGroupIndex:输入全局index，获取groupIndex




其他合约函数API的封装说明:  
    
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

- get* 调用在上述其他合约中get数据的接口
