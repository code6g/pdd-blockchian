package main

import (
	"bytes"
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	pb "github.com/hyperledger/fabric/protos/peer"
)

// operate the user info[hyxx],include add,del,modify,query

// define a struct HyxxCC
type HyxxCC struct {

}

// init the chain code
func (t *HyxxCC) Init(stub shim.ChaincodeStubInterface) pb.Response{

	fmt.Println("== HyxxCC Init")
	return shim.Success(nil)
}

// invoke function
func (t *HyxxCC) Invoke(stub shim.ChaincodeStubInterface) pb.Response{

	fmt.Println("== HyxxCC Invoke")

	//get the function name and arguments
	function, args := stub.GetFunctionAndParameters()

	// 对获取到的函数名称进行判断
	if function == "insertHyxx" {

		return t.insertHyxx(stub, args)
	}else if function == "deleteHyxx" {

		return t.deleteHyxx(stub, args)
	}else if function == "queryHyxx" {

		return t.queryHyxx(stub, args)

	}else if function == "queryRange" {

		return t.queryRange(stub, args)

	}




	// 传递的函数名出错，返回 shim.Error()
	return shim.Error(`== Invalid invoke function name. Expecting "insertHyxx\" "deleteHyxx\" "queryHyxx\" "queryRange" `)

}


/*

hyid	会员编码
hyzh	会员账号
hymm	会员密码
hymc	会员名称
hydz	会员地址
hydh	会员电话
hyzt	会员状态
zcrq	注册时间

peer chaincode invoke -o orderer.example.com:7050 -C mychannel -n hyxxcc --peerAddresses peer0.org1.example.com:7051  -c
'{"Args":["insertHyxx","hyid001","1325821991","123","code6g001","shanghai road zhongshan No.1","1325821991","1","2020-08-11"]}'


*/

func (t *HyxxCC) insertHyxx(stub shim.ChaincodeStubInterface, args []string) pb.Response {

	if len(args) != 8 {
		return shim.Error("==  Incorrect number of arguments[insertHyxx]. Expecting 8")
	}

	var hyid string
	var hyzh string
	var hymm string
	var hymc string
	var hydz string
	var hydh string
	var hyzt string
	var zcrq string

	hyid =args[0]
	hyzh =args[1]
	hymm =args[2]
	hymc =args[3]
	hydz =args[4]
	hydh =args[5]
	hyzt =args[6]
	zcrq =args[7]

	//parse json str
	//var hyxx Hyxx
	//json.Unmarshal([]byte(val), &hyxx)
	//fmt.Println(hyxx)
	//fmt.Println(hyxx.hyid) //get json object value
	var val string
	val=`{"hyid":"`+hyid+`","hyzh":"`+hyzh+`","hymm":"`+hymm+`","hymc":"`+hymc+`","hydz":"`+hydz+`","hydh":"`+hydh+`","hyzt":"`+hyzt+`","zcrq":"`+zcrq+`"}`

	// insert hyxx json str info into fabric
	err := stub.PutState(hyid,[]byte(val))
	if err != nil {
		return shim.Error("== Failed to insert hyxx json str")
	}

	err2 := stub.PutState("hyxx_zcrq_"+zcrq+"_"+hyid,[]byte(hyid))
	if err2 != nil {
		return shim.Error("== Failed to insert hyxx_zcrq and key ")
	}

	err3 := stub.PutState("hyxx_hydh_"+hydh+"_"+hyid,[]byte(hyid))
	if err3 != nil {
		return shim.Error("== Failed to insert hyxx_hydh and key ")
	}

	err4 := stub.PutState("hyxx_hyzh_"+hyzh+"_"+hyid,[]byte(hyid))
	if err4 != nil {
		return shim.Error("== Failed to  insert hyxx_hyzh and key ")
	}

	err5 := stub.PutState("hyxx_hymc_"+hymc+"_"+hyid,[]byte(hyid))
	if err5 != nil {
		return shim.Error("== Failed to  insert hyxx_hymc and key ")
	}

	return shim.Success(nil)
}


//  'Args:["deleteHyxx","20200812-121212123"]
func (t *HyxxCC) deleteHyxx(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	if len(args) != 1 {
		return shim.Error("==deleteHyxx :  Incorrect number of arguments. Expecting 1")
	}

	key := args[0]   //compose key

	// 从账本中删除该账户状态
	err := stub.DelState(key)
	if err != nil {
		return shim.Error("==deleteQyxx : Failed to delete state")
	}

	return shim.Success(nil)
}

// 查询
func (t *HyxxCC) queryHyxx(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	var key string
	var err error

	if len(args) != 1 {
		return shim.Error("==queryHyxx : Incorrect number of arguments. Expecting hyid of the hyxx to query")
	}

	key = args[0]
	//
	valBytes, err := stub.GetState(key)
	if err != nil {
		jsonResp := "{\"Error\":\" == queryHyxx : failed to get state for " + key + "\"}"
		return shim.Error(jsonResp)
	}

	if valBytes == nil {
		jsonResp := "{\"Error\":\"== queryHyxx : Nil value for " + key + "\"}"
		return shim.Error(jsonResp)
	}

	jsonResp := "{\"hyid\":\"" + key + "\",\"detail\":\"" + string(valBytes) + "\"}"
	fmt.Printf("********* ==  Query OK Response : %s\n", jsonResp)
	// 返回转账金额
	return shim.Success(valBytes)
}



func (t *HyxxCC) queryRange(stub shim.ChaincodeStubInterface, args []string) pb.Response {

	if len(args) != 2 {
		return shim.Error("==Error:queryRange()--Incorrect number of arguments. should is 2")
	}

	var rangeStart 	string
	var rangeEnd 	string
	rangeStart 	= args[0]
	rangeEnd 	= args[1]

	resultsIterator, err := stub.GetStateByRange(rangeStart, rangeEnd)
	if err != nil {
		jsonResp := "==Error:queryRange()--stub.GetStateByRange(rangeStart, rangeEnd)"
		return shim.Error(jsonResp)
	}

	//defer resultsIterator.Close() //释放迭代器
	var buffer bytes.Buffer
	var isfirst bool
	isfirst=true


	buffer.WriteString(`[`)

	for resultsIterator.HasNext() {
		queryResponse, err := resultsIterator.Next() //获取迭代器中的每一个值
		if err != nil {
			return shim.Error(`==Error:queryRange()--resultsIterator.Next()`)
		}
		var keyOne string
		keyOne = string(queryResponse.Value)

		valByteJson,err2 := stub.GetState(keyOne)
		if err2 != nil {
			jsonResp := `Error:queryRange()--stub.GetState(keyOne)`
			return shim.Error(jsonResp)
		}

		if string(valByteJson)==""{
			continue
		}

		if isfirst {
			buffer.WriteString(string(valByteJson))
			isfirst=false
		} else
		{
			buffer.WriteString(","+string(valByteJson))
		}




	}
	//buffer.WriteString(`]}`)
	buffer.WriteString(`]`)  //[{"spid":"sp001","spmc":"apple"},{"spid":"sp002","spmc":"banana"}]
	fmt.Print("queryRange()--result:", buffer.String())
	return shim.Success(buffer.Bytes())


}




func main() {
	err := shim.Start(new(HyxxCC))
	if err != nil {
		fmt.Printf("== Error: starting chaincode[HyxxCC] : %s", err)
	}
}
