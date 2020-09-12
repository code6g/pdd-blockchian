package main

import (
	"bytes"
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	pb "github.com/hyperledger/fabric/protos/peer"
)

// operate the product info[Spxx],include add,del,modify,query

// define a struct SpxxCC
type SpxxCC struct {

}

// init the chain code
func (t *SpxxCC) Init(stub shim.ChaincodeStubInterface) pb.Response{

	fmt.Println("== SpxxCC Init")
	return shim.Success(nil)
}

// invoke function
func (t *SpxxCC) Invoke(stub shim.ChaincodeStubInterface) pb.Response{

	fmt.Println("== SpxxCC Invoke")

	//get the function name and arguments
	function, args := stub.GetFunctionAndParameters()

	// 对获取到的函数名称进行判断
	if function == "insertSpxx" {

		return t.insertSpxx(stub, args)
	} else if function == "deleteSpxx" {

		return t.deleteSpxx(stub, args)
	} else if function == "querySpxx" {

		return t.querySpxx(stub, args)
	}else if function == "queryRange" {

		return t.queryRange(stub, args)

	}


	// 传递的函数名出错，返回 shim.Error()
	return shim.Error("== Invalid invoke function name. Expecting  insertSpxx deleteSpxx querySpxx queryRange")

}

/*

spid	商品编码
flid	分类编码
flmc	分类名称
spmc	商品名称
spjg	商品价格
spms	商品描述
sptp	商品图片
spzt	商品状态

'{"Args":["insertSpxx","sp001","fl001", "food", "apple","5","shanxi apple","no","1"}"]}'

*/
//insert
func (t *SpxxCC) insertSpxx(stub shim.ChaincodeStubInterface, args []string) pb.Response {

	if len(args) != 8 {
		return shim.Error("==  Incorrect number of arguments[insertSpxx]. Expecting 8")
	}


	var spid string
	var flid string
	var flmc string
	var spmc string
	var spjg string
	var spms string
	var sptp string
	var spzt string

	spid =args[0]
	flid =args[1]
	flmc =args[2]
	spmc =args[3]
	spjg =args[4]
	spms =args[5]
	sptp =args[6]
	spzt =args[7]


	var val string
	val=`{"spid":"`+spid+`","flid":"`+flid+`","flmc":"`+flmc+`","spmc":"`+spmc+`","spjg":"`+spjg+`","spms":"`+spms+`","sptp":"`+sptp+`","spzt":"`+spzt+`"}`

	// insert spxx json str info into fabric
	err := stub.PutState(spid,[]byte(val))
	if err != nil {
		return shim.Error("== Failed to insert spxx json str")
	}


	err2 := stub.PutState("spxx_flmc_"+flmc+"_"+spid,[]byte(spid))
	if err2 != nil {
		return shim.Error("== Failed to insert spxx_flmc and key ")
	}

	err3 := stub.PutState("spxx_spmc_"+spmc+"_"+spid,[]byte(spid))
	if err3 != nil {
		return shim.Error("== Failed to insert spxx_spmc and key ")
	}

	err4 := stub.PutState("spxx_spjg_"+spjg+"_"+spid,[]byte(spid))
	if err4 != nil {
		return shim.Error("== Failed to insert spxx_spjg and key ")
	}


	err5 := stub.PutState("spxx_flid_"+flid+"_"+spid,[]byte(spid))
	if err5 != nil {
		return shim.Error("== Failed to insert spxx_flid and key ")
	}

	return shim.Success(nil)
}


//delete  '{"Args":["deleteSpxx","sp001"]}'
func (t *SpxxCC) deleteSpxx(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	if len(args) != 1 {
		return shim.Error("==deleteSpxx :  Incorrect number of arguments. Expecting 1")
	}

	key := args[0]   //compose key

	// 从账本中删除该账户状态
	err := stub.DelState(key)
	if err != nil {
		return shim.Error("==deleteQyxx : Failed to delete state")
	}

	return shim.Success(nil)
}

//query  '{"Args":["querySpxx","sp001"]}'
func (t *SpxxCC) querySpxx(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	var key string
	var err error

	if len(args) != 1 {
		return shim.Error("==querySpxx : Incorrect number of arguments. Expecting hyid of the Spxx to query")
	}

	key = args[0]
	//
	valBytes, err := stub.GetState(key)
	if err != nil {
		jsonResp := "{\"Error\":\" == querySpxx : failed to get state for " + key + "\"}"
		return shim.Error(jsonResp)
	}

	if valBytes == nil {
		jsonResp := "{\"Error\":\"== querySpxx : Nil amount for " + key + "\"}"
		return shim.Error(jsonResp)
	}

	jsonResp := "{\"spid\":\"" + key + "\",\"detail\":\"" + string(valBytes) + "\"}"
	fmt.Printf("==  querySpxx OK Response : %s\n", jsonResp)
	// 返回转账金额
	return shim.Success(valBytes)
}




func (t *SpxxCC) queryRange(stub shim.ChaincodeStubInterface, args []string) pb.Response {

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
	err := shim.Start(new(SpxxCC))
	if err != nil {
		fmt.Printf("== Error: starting chaincode[SpxxCC] : %s", err)
	}
}