package com.ruoyi.blockchain.ywtool;


//Hyxx user info tool of operating blockchain
public class HyxxCCTool {
    static String CHAINCODENAME ="hyxxcc";
    static String FN_insertHyxx ="insertHyxx";
    static String FN_deleteHyxx ="deleteHyxx";
    static String FN_queryHyxx  ="queryHyxx";
    static String FN_queryRange  ="queryRange";


    //value[0] is key
    public static boolean insertHyxx(String[] value) {
        return BaseTool.insertKeyArrayValue(CHAINCODENAME,FN_insertHyxx,value);
    }

    public static boolean updateHyxx(String[] value) {
        return BaseTool.insertKeyArrayValue(CHAINCODENAME,FN_insertHyxx,value);
    }

    public static boolean deleteHyxx(String  key) {
        return BaseTool.deleteByKey(CHAINCODENAME,FN_deleteHyxx,key);
    }
    public static String queryHyxxByKey(String key) {
        return BaseTool.queryValueByKey(CHAINCODENAME,FN_queryHyxx,key);
    }


    //HyxxCCTool.queryBatch("hyxx_zcrq_2020-08-11","hyxx_zcrq_2020-08-13");
    public static String queryRange(String rangeStart,String rangeEnd) {
        return BaseTool.queryRange(CHAINCODENAME,FN_queryRange,rangeStart,rangeEnd);
    }



    public static void main(String args){
        HyxxCCTool.queryRange("hyxx_zcrq_2020-08-11","hyxx_zcrq_2020-08-13");
    }


}
