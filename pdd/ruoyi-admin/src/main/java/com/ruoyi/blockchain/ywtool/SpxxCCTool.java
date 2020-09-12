package com.ruoyi.blockchain.ywtool;


//Spxx user info tool of operating blockchain
public class SpxxCCTool {
    static String CHAINCODENAME ="spxxcc";
    static String FN_insertSpxx ="insertSpxx";
    static String FN_deleteSpxx ="deleteSpxx";
    static String FN_querySpxx  ="querySpxx";
    static String FN_queryRange  ="queryRange";


    //value[0] is key
    public static boolean insertSpxx(String[] value) {
        return BaseTool.insertKeyArrayValue(CHAINCODENAME,FN_insertSpxx,value);
    }

    public static boolean updateSpxx(String[] value) {
        return BaseTool.insertKeyArrayValue(CHAINCODENAME,FN_insertSpxx,value);
    }

    public static boolean deleteSpxx(String  key) {
        return BaseTool.deleteByKey(CHAINCODENAME,FN_deleteSpxx,key);
    }
    public static String querySpxxByKey(String key) {
        return BaseTool.queryValueByKey(CHAINCODENAME,FN_querySpxx,key);
    }


    //SpxxCCTool.queryBatch("spxx_spfl_001","spxx_spfl_002");
    public static String queryRange(String rangeStart,String rangeEnd) {
        return BaseTool.queryRange(CHAINCODENAME,FN_queryRange,rangeStart,rangeEnd);
    }


    public static void main(String args){
        SpxxCCTool.queryRange("spxx_zcrq_2020-08-11","spxx_zcrq_2020-08-13");
    }


}
