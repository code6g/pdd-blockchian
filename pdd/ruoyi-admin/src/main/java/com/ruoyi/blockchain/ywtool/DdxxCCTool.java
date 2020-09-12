package com.ruoyi.blockchain.ywtool;


//Ddxx user info tool of operating blockchain
public class DdxxCCTool {
    static String CHAINCODENAME ="ddxxcc";
    static String FN_insertDdxx ="insertDdxx";
    static String FN_deleteDdxx ="deleteDdxx";
    static String FN_queryDdxx  ="queryDdxx";
    static String FN_queryRange  ="queryRange";


    //value[0] is key
    public static boolean insertDdxx(String[] value) {
        return BaseTool.insertKeyArrayValue(CHAINCODENAME,FN_insertDdxx,value);
    }

    public static boolean updateDdxx(String[] value) {
        return BaseTool.insertKeyArrayValue(CHAINCODENAME,FN_insertDdxx,value);
    }

    public static boolean deleteDdxx(String  key) {
        return BaseTool.deleteByKey(CHAINCODENAME,FN_deleteDdxx,key);
    }
    public static String queryDdxxByKey(String key) {
        return BaseTool.queryValueByKey(CHAINCODENAME,FN_queryDdxx,key);
    }


    //DdxxCCTool.queryBatch("ddxx_spfl_001","ddxx_spfl_002");
    public static String queryRange(String rangeStart,String rangeEnd) {
        return BaseTool.queryRange(CHAINCODENAME,FN_queryRange,rangeStart,rangeEnd);
    }


    public static void main(String args){
        DdxxCCTool.queryRange("ddxx_zcrq_2020-08-11","ddxx_zcrq_2020-08-13");
    }


}
