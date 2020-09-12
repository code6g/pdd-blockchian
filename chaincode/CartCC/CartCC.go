package main

import (
	"bytes"
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	pb "github.com/hyperledger/fabric/protos/peer"
)

// operate the product info[Cart],include add,del,modify,query

// define a struct CartCC
type CartCC struct {

}

// init the chain code
func (t *CartCC) Init(stub shim.ChaincodeStubInterface) pb.Response{

	fmt.Println("== CartCC Init")
	return shim.Success(nil)
}

// invoke function
func (t *CartCC) Invoke(stub shim.ChaincodeStubInterface) pb.Response{

	fmt.Println("== CartCC Invoke")

	//get the function name and arguments
	function, args := stub.GetFunctionAndParameters()

	// 对获取到的函数名称进行判断
	if function == "insertCart" {

		return t.insertCart(stub, args)
	} else if function == "deleteCart" {

		return t.deleteCart(stub, args)
	} else if function == "queryCart" {

		return t.queryCart(stub, args)
	}else if function == "queryRange" {

		return t.queryRange(stub, args)

	}

	// 传递的函数名出错，返回 shim.Error()
	return shim.Error("== Invalid invoke function name. Expecting insertCart deleteCart queryCart  queryRange")

}

/*

gwid	购物车编码
hyid	会员编码
spid	商品编码
spmc	商品名称
spsl	商品数量
spjg	商品价格
jgxj	价格小计
mxsm	明细说明

'{"Args":["insertCart","gw001","001","sp001","apple","2","5","10","ok fine"]}'


*/
//insert
func (t *CartCC) insertCart(stub shim.ChaincodeStubInterface, args []string) pb.Response {

	if len(args) != 8 {
		return shim.Error("==  Incorrect number of arguments[insertCart]. Expecting 8")
	}

	var gwid string
	var hyid string
	var spid string
	var spmc string
	var spsl string
	var spjg string
	var jgxj string
	var mxsm string

	gwid =args[0]
	hyid =args[1]
	spid =args[2]
	spmc =args[3]
	spsl =args[4]
	spjg =args[5]
	jgxj =args[6]
	mxsm =args[7]


	var val string
	val=`{"gwid":"`+gwid+`","hyid":"`+hyid+`","spid":"`+spid+`","spmc":"`+spmc+`","spsl":"`+spsl+`","spjg":"`+spjg+`","jgxj":"`+jgxj+`","mxsm":"`+mxsm+`"}`

	// insert spxx json str info into fabric
	err := stub.PutState(gwid,[]byte(val))
	if err != nil {
		return shim.Error("== Failed to insert spxx json str")
	}

	err2 := stub.PutState("cart_hyid_"+hyid+"_"+gwid,[]byte(gwid))
	if err2 != nil {
		return shim.Error("== Failed to insert cart_hyid and key ")
	}

	err3 := stub.PutState("cart_spmc_"+spmc+"_"+gwid,[]byte(gwid))
	if err3 != nil {
		return shim.Error("== Failed to insert cart_spmc and key ")
	}

	return shim.Success(nil)
}


//delete  '{"Args":["deleteCart","gw001"]}'
func (t *CartCC) deleteCart(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	if len(args) != 1 {
		return shim.Error("==deleteCart :  Incorrect number of arguments. Expecting 1")
	}

	key := args[0]   //compose key

	// 从账本中删除该账户状态
	err := stub.DelState(key)
	if err != nil {
		return shim.Error("==deleteCart : Failed to delete state")
	}

	return shim.Success(nil)
}

//query  '{"Args":["queryCart","gw001"]}'
func (t *CartCC) queryCart(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	var key string
	var err error

	if len(args) != 1 {
		return shim.Error("==queryCart : Incorrect number of arguments. Expecting hyid of the Cart to query")
	}

	key = args[0]
	//
	valBytes, err := stub.GetState(key)
	if err != nil {
		jsonResp := "{\"Error\":\" == queryCart : failed to get state for " + key + "\"}"
		return shim.Error(jsonResp)
	}

	if valBytes == nil {
		jsonResp := "{\"Error\":\"== queryCart : Nil amount for " + key + "\"}"
		return shim.Error(jsonResp)
	}

	jsonResp := "{\"gwid\":\"" + key + "\",\"detail\":\"" + string(valBytes) + "\"}"
	fmt.Printf("== queryCart OK, Response : %s ", jsonResp)
	// 返回转账金额
	return shim.Success(valBytes)
}




func (t *CartCC) queryRange(stub shim.ChaincodeStubInterface, args []string) pb.Response {

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
	err := shim.Start(new(CartCC))
	if err != nil {
		fmt.Printf("== Error: starting chaincode[CartCC] : %s", err)
	}
}