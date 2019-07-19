pragma solidity ^0.4.24;

contract DataBus {
    // event
    event RegisterEvent(int256 ret, string account, uint256 asset_value);

    struct User {
        string account;
        bytes[] publicKey;
        bytes[] verifyKey;
    }

    mapping(string => User) public users;
    mapping(string => bool) public isUserRegistered;
    uint256 private DEFAULT_AUTHORITY_LEVEL = 1; // 0 - Not allow, 1 - Read, 2 - Read and Write

    struct MetaInfo {
        string infoId;
        string infoTitle;
        string infoDescribe;
        string owner;
        uint256 authorityLevel;
        string[] attestations;
    }

    mapping(string => MetaInfo) public metaInfoById;
    mapping(string => bool) public isMetaInfoExists;
    mapping(string => string[]) public metaInfoByUser;

    string[] public allMetaInfo;

    function userRegister(string account, bytes[] public_key, bytes[] verify_key) public returns (uint256){
        uint256 ret_code = 0;
        // 判断用户尚未注册过
        if (isUserRegistered[account]) {
            ret_code = 1;
        } else {
            User memory user = User({
            account : account,
            publicKey : public_key,
            verifyKey : verify_key
            });
            users[account] = user;
            isUserRegistered[account] = true;
        }
        emit RegisterEvent(ret_code, account, balance);
        return ret_code;
    }

    function createMetaInfo(string account, string infoId, string infoTitle, string infoDescribe) public returns (uint256){
        uint256 ret_code = 0;
        // 判断用户是否存在且InfoId不存在
        if (isUserRegistered[account] == false || isMetaInfoExists[infoId]) {
            ret_code = 1;
        } else {
            MetaInfo memory metaInfo = MetaInfo({
                infoId : infoId,
                infoTitle : infoTitle,
                infoDescribe : infoDescribe,
                owner : account,
                authorityLevel : DEFAULT_AUTHORITY_LEVEL
                });
            metaInfoById[infoId] = metaInfo;
            metaInfoByUser[account] = metaInfo;
            allMetaInfo.push(metaInfo);
            isMetaInfoExists[infoId] = true;
        }
        return ret_code;
    }

    struct Data {
        string infoId;
        string dataId;
        string owner;
        string dataIndex;
        uin256 authorityLevel;
    }

    mapping(string => Data) private dataById;
    mapping(string => Data[]) private dataByUser;

    function createData(string metaInfoId, string dataId, string account, string dataIndex) public returns (uint256){
        uint256 ret_code = 0;
        // 确认MetaInfo存在 账户存在
        if (isMetaInfoExists[metaInfoId] == false || isUserRegistered[account] == false) {
            ret_code = 1;
        } else {
            Data memory data = Data({
                infoId : metaInfoId,
                dataId : dataId,
                owner : account,
                dataIndex : dataIndex,
                authorityLevel : DEFAULT_AUTHORITY_LEVEL
                });
            dataById[dataId] = data;
            dataByUser[account] = data;
        }
        return ret_code;
    }


}
