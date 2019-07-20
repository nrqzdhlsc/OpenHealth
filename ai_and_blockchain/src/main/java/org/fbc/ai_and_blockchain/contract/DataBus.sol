pragma solidity ^0.4.24;

contract DataBus {
    // event
    event SuperiorRegisterEvent(uint256 ret, string account);
    event SubordinateRegisterEvent(uint256 ret, string account, string superior, uint256 userType);
    event UploadDataEvent(uint256 ret, string dataId, string dataIndex);
    event RequestForDataEvent(uint256 ret, string account, string dataId, string requestId);
    event AgreeForRequestEvent(uint256 ret, string account, string dataId);

    struct User {
        string account;
        string pubKey;
        string verifyKey;
        uint256 userType; // 0-医院 1-病人 2-医生
    }

    mapping(string => bool) hasSubordinate;
    mapping(string => uint256[]) subordinates;
    mapping(string => uint256) getUserIndex;
    User[] private users;
    uint256[] private patients;
    uint256 userNonce = 0;

    mapping(string => string) getPubKeyById;
    mapping(string => string) getVerifyKeyById;
    mapping(string => bool) isUserRegistered;

    function superiorRegister(string account, string public_key, string verify_key) public returns (uint256){
        uint256 ret_code = 0;
        // 判断用户尚未注册过
        if (isUserRegistered[account]) {
            ret_code = 1;
        } else {
            User memory user = User({
                account : account,
                pubKey : public_key,
                verifyKey : verify_key,
                userType : 0
                });
            users.push(user);
            getUserIndex[account] = userNonce;
            isUserRegistered[account] = true;
            userNonce = userNonce + 1;
        }
        emit SuperiorRegisterEvent(ret_code, account);
        return ret_code;
    }

    function subordinateRegister(string account, string superior, string public_key, string verify_key, uint256 userType) public returns (uint256){
        uint256 ret_code = 0;
        if (isUserRegistered[account] || isUserRegistered[superior] == false) {
            ret_code = 1;
        } else {
            User memory user = User({
                account : account,
                pubKey : public_key,
                verifyKey : verify_key,
                userType : userType
                });
            if (userType == 1) {
                patients.push(userNonce);
            }
            users.push(user);
            getUserIndex[account] = userNonce;
            isUserRegistered[account] = true;
            subordinates[superior].push(userNonce);
            hasSubordinate[superior] = true;
            userNonce = userNonce + 1;
        }
        emit SubordinateRegisterEvent(ret_code, account, superior, userType);
        return ret_code;
    }

    function getUserPubKey(string account) public constant returns (string){
        User memory user = users[getUserIndex[account]];
        return user.pubKey;
    }

    function getUserVerifyKey(string account) public constant returns (string){
        User memory user = users[getUserIndex[account]];
        return user.verifyKey;
    }

    function getUserByIndex(uint256 index) public constant returns (string, string, string, uint256){
        User memory user = users[index];
        return (user.account, user.pubKey, user.verifyKey, user.userType);
    }

    function getAllPatient() public constant returns (uint256[]){
        return patients;
    }

    mapping(string => mapping(string => bool)) canUserGetData;
    mapping(string => Data) getDataById;
    mapping(string => bool) isDataExisted;
    mapping(string => string[]) getYourData;

    Data[] private allData;

    struct Data {
        string dataId;
        string owner;
        string relevant;
        string dataIndex;
    }

    function uploadData(string account, string owner, string dataId, string dataIndex) public returns (uint256){
        uint256 ret_code = 0;
        if (isUserRegistered[account] == false || isUserRegistered[owner] == false || isDataExisted[dataId] == true) {
            ret_code = 1;
        } else {
            Data memory data = Data({
                dataId : dataId,
                owner : owner,
                relevant : account,
                dataIndex : dataIndex
                });
            getDataById[dataId] = data;
            canUserGetData[account][dataId] = true;
            canUserGetData[owner][dataId] = true;
            isDataExisted[dataId] = true;
            getYourData[owner].push(dataId);
            allData.push(data);
            emit UploadDataEvent(ret_code, dataId, dataIndex);
        }
        return ret_code;
    }

    struct Request {
        string requestId;
        string owner;
        string dataId;
        uint256 status;
    }

    mapping(string => uint256[]) getAllYourRequests;
    uint256 requestNonce = 0;
    mapping(string => Request) getRequestById;
    Request[] allRequests;

    function getRequest(uint256 requestIndex) public constant returns (string, string, string, uint256){
        Request memory request = allRequests[requestIndex];
        return (request.requestId, request.owner, request.dataId, request.status);
    }

    function getRequestByRequestId(string requestId) public constant returns (string, string, string, uint256){
        Request memory request = getRequestById[requestId];
        return (request.requestId, request.owner, request.dataId, request.status);
    }

    function getYourRequests(string account) public constant returns (uint256[]){
        return getAllYourRequests[account];
    }

    function requestForData(string account, string requestId, string dataId) public returns (uint256){
        uint256 ret_code = 0;
        if (isUserRegistered[account] == false || isDataExisted[dataId] == false || canUserGetData[account][dataId] == true) {
            ret_code = 1;
        } else {
            Request memory request = Request({
                requestId : requestId,
                owner : account,
                dataId : dataId,
                status : 0
                });
            getRequestById[requestId] = request;
            Data memory data = getDataById[dataId];
            getAllYourRequests[account].push(requestNonce);
            getAllYourRequests[data.owner].push(requestNonce);
            getAllYourRequests[data.relevant].push(requestNonce);
            allRequests.push(request);
            requestNonce = requestNonce + 1;
            emit RequestForDataEvent(ret_code, account, dataId, requestId);
        }
        return ret_code;
    }

    function agreeForRequest(string account, string requestId) public returns (uint256){
        uint256 ret_code = 0;
        if (isUserRegistered[account] == false) {
            ret_code = 1;
        } else {
            Request storage request = getRequestById[requestId];
            Data memory data = getDataById[request.dataId];
            if (canUserGetData[account][data.dataId] == false) {
                ret_code = 1;
            } else {
                request.status = 1;
                canUserGetData[request.owner][data.dataId] = true;
                getRequestById[requestId] = request;
                emit AgreeForRequestEvent(ret_code, account, data.dataId);
            }
        }
        return ret_code;
    }

    function getData(string account, string dataId) public constant returns (string, string, string, string){
        Data memory data = getDataById[dataId];
        return (data.dataId, data.owner, data.relevant, data.dataIndex);
    }

}
