# ModelList.sol

通过维护全局结构数组ModelInfoList来记录模型方的模型信息（科学家，有模型无数据的一方）

## API

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


函数API说明：

    function register(string host, int port, string api) public returns(uint){}

    function getGlobalIndex() constant public returns (uint) {}

    function getModelInfo_host(uint index)constant public returns(string) {}

    function getModelInfo_port(uint index)constant public returns(int) {}

    function getModelInfo_API(uint index)constant public returns(string) {}
        
- register:模型注册，输入上述参数，进行模型的信息注册，globalIndex会自增，所以。
- getGlobalIndex:获取当前全局index，获取循环模型信息的循环上限。
- getModelInfo_host:输入全局index，获取对应的服务器地址。
- getModelInfo_port:输入全局index，获取对应的服务器端口。
- getModelInfo_API:输入全局的index，获取对应的模型API



# DSList.sol

# DataMarket.sol