package main

import (
	"bytes"
	"fmt"
	"github.com/hyperledger/fabric/core/chaincode/shim"
	pb "github.com/hyperledger/fabric/protos/peer"
)

// operate the product info[Ddxx],include add,del,modify,query

// define a struct DdxxCC
type DdxxCC struct {

}

// init the chain code
func (t *DdxxCC) Init(stub shim.ChaincodeStubInterface) pb.Response {

	fmt.Println("== DdxxCC Init")
	return shim.Success(nil)
}

// invoke function
func (t *DdxxCC) Invoke(stub shim.ChaincodeStubInterface) pb.Response {

	fmt.Println("== DdxxCC Invoke")

	//get the function name and arguments
	function, args := stub.GetFunctionAndParameters()

	// 对获取到的函数名称进行判断
	if function == "insertDdxx" {
		return t.insertDdxx(stub, args)
	} else if function == "deleteDdxx" {
		return t.deleteDdxx(stub, args)
	} else if function == "queryDdxx" {
		return t.queryDdxx(stub, args)
	}else if function == "queryRange" {
		return t.queryRange(stub, args)
	}

	// 传递的函数名出错，返回 shim.Error()
	return shim.Error("== Invalid invoke function name. Expecting insertDdxx deleteDdxx queryDdxx  queryRange")

}

/*
  == order info==

ddid	订单编码
ddbh	订单编号
hyid	会员编码
hymc	会员名称
spsl	商品数量
jgzj	价格总计
ddzt	订单状态
xdsj	下单时间
xdsm	下单说明
zfqd	支付渠道
zfzh	支付账号
zfry	支付人
zfsj	支付时间
zfsm	支付说明
shry	收货人
shdz	收货地址
shdh	收货人电话
psry	配送人
pssj	配送时间
pssm	配送说明
qsry	签收人
qssj	签收时间
qssm	签收说明
pjnr	评价内容
pjsj	评价时间



  == order detail==

mxid	明细编码
ddid	订单编码
ddbh	订单编号
hyid	会员编码
spid	商品编码
spmc	商品名称
spsl	商品数量
spjg	商品价格
jgxj	价格小计
mxsm	明细说明

  ==json order String==

peer chaincode invoke -o orderer.example.com:7050 -C mychannel -n ddxxcc --peerAddresses peer0.org1.example.com:7051 -c
'{"Args":["insertDdxx","dd001","dd202008181212120001","001","aidata100","3","16","2","2020-08-18 12:12:12","","zfb","code6g","niuge","2020-08-18 12:12:12","","niuge","zhongsan road No.128","18916933331","","","","","","","","",
"[{^mxid^:^mx001^,^ddid^:^dd001^,^ddbh^:^dd202008181212120001^,^hyid^:^001^,^spid^:^sp001^,^spmc^:^apple^,^spsl^:^2^,^spjg^:^5^,^jgxj^:^10^,^mxsm^:^^},
{^mxid^:^mx002^,^ddid^:^dd001^,^ddbh^:^dd202008181212120001^,^hyid^:^001^,^spid^:^sp002^,^spmc^:^banana^,^spsl^:^1^,^spjg^:^6^,^jgxj^:^6^,^mxsm^:^\"}]"]}'


//key: 订单编码

*/
//insert
func (t *DdxxCC) insertDdxx(stub shim.ChaincodeStubInterface, args []string) pb.Response {

	if len(args) != 26 {
		return shim.Error("==  Incorrect number of arguments[insertDdxx]. Expecting 26")
	}


	var ddid string
	var ddbh string
	var hyid string
	var hymc string
	var spsl string
	var jgzj string
	var ddzt string
	var xdsj string
	var xdsm string
	var zfqd string
	var zfzh string
	var zfry string
	var zfsj string
	var zfsm string
	var shry string
	var shdz string
	var shdh string
	var psry string
	var pssj string
	var pssm string
	var qsry string
	var qssj string
	var qssm string
	var pjnr string
	var pjsj string

	var ddmx string


	ddid=args[0]
	ddbh=args[1]
	hyid=args[2]
	hymc=args[3]
	spsl=args[4]
	jgzj=args[5]
	ddzt=args[6]
	xdsj=args[7]
	xdsm=args[8]
	zfqd=args[9]
	zfzh=args[10]
	zfry=args[11]
	zfsj=args[12]
	zfsm=args[13]
	shry=args[14]
	shdz=args[15]
	shdh=args[16]
	psry=args[17]
	pssj=args[18]
	pssm=args[19]
	qsry=args[20]
	qssj=args[21]
	qssm=args[22]
	pjnr=args[23]
	pjsj=args[24]


	ddmx=args[25]




	var val string
	val=`{"ddid":"`+ddid+`","ddbh":"`+ddbh+`","hyid":"`+hyid+`","hymc":"`+hymc+`","spsl":"`+spsl+`","jgzj":"`+jgzj+`","ddzt":"`+ddzt+`","xdsj":"`+xdsj+`","xdsm":"`+xdsm+`","zfqd":"`+zfqd+`","zfzh":"`+zfzh+`","zfry":"`+zfry+`","zfsj":"`+zfsj+`","zfsm":"`+zfsm+`","shry":"`+shry+`","shdz":"`+shdz+`","shdh":"`+shdh+`","psry":"`+psry+`","pssj":"`+pssj+`","pssm":"`+pssm+`","qsry":"`+qsry+`","qssj":"`+qssj+`","qssm":"`+qssm+`","pjnr":"`+pjnr+`","pjsj":"`+pjsj+`","ddmx":"`+ddmx+`"}`


	err := stub.PutState(ddid,[]byte(val))
	if err != nil {
		return shim.Error("== Failed to insert ddxx json str")
	}


	err2 := stub.PutState("ddxx_ddbh_"+ddbh+"_"+ddid,[]byte(ddid))
	if err2 != nil {
		return shim.Error("== Failed to insert ddxx_ddbh_{ddbh}_ddid and key ")
	}

	err3 := stub.PutState("ddxx_hyid_"+hyid+"_"+ddid,[]byte(ddid))
	if err3 != nil {
		return shim.Error("== Failed to insert ddxx_hyid_{hyid}_ddid and key ")
	}
	err4 := stub.PutState("ddxx_hymc_"+hymc+"_"+ddid,[]byte(ddid))
	if err4 != nil {
		return shim.Error("== Failed to insert ddxx_hymc_{hymc}_ddid and key ")
	}
	err5 := stub.PutState("ddxx_xdsj_"+xdsj+"_"+ddid,[]byte(ddid))
	if err5 != nil {
		return shim.Error("== Failed to insert ddxx_xdsj_{xdsj}_ddid and key ")
	}


	return shim.Success(nil)
}

//delete  '{"Args":["deleteDdxx","dd001"]}'
func (t *DdxxCC) deleteDdxx(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	if len(args) != 1 {
		return shim.Error("==deleteDdxx :  Incorrect number of arguments. Expecting 1")
	}

	key := args[0] //compose key

	// 从账本中删除
	err := stub.DelState(key)
	if err != nil {
		return shim.Error("==deleteDdxx : Failed to delete state")
	}

	return shim.Success(nil)
}

//query  '{"Args":["queryDdxx","dd001"]}'
func (t *DdxxCC) queryDdxx(stub shim.ChaincodeStubInterface, args []string) pb.Response {
	var key string
	var err error

	if len(args) != 1 {
		return shim.Error("==queryDdxx : Incorrect number of arguments. Expecting ddid of the Ddxx to query")
	}

	key = args[0]
	//
	valBytes, err := stub.GetState(key)
	if err != nil {
		jsonResp := "{\"Error\":\" == queryDdxx : failed to get state for " + key + "\"}"
		return shim.Error(jsonResp)
	}

	if valBytes == nil {
		jsonResp := "{\"Error\":\"== queryDdxx : Nil amount for " + key + "\"}"
		return shim.Error(jsonResp)
	}

	jsonResp := "{\"ddid\":\"" + key + "\",\"detail\":\"" + string(valBytes) + "\"}"
	fmt.Printf("********* ==  Query OK Response : %s\n", jsonResp)
	// 返回
	return shim.Success(valBytes)
}


func (t *DdxxCC) queryRange(stub shim.ChaincodeStubInterface, args []string) pb.Response {

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
	err := shim.Start(new(DdxxCC))
	if err != nil {
		fmt.Printf("== Error: starting chaincode[DdxxCC] : %s", err)
	}
}
