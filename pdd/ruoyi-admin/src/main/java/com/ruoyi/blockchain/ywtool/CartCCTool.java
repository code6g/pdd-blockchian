package com.ruoyi.blockchain.ywtool;


//Cart user info tool of operating blockchain
public class CartCCTool {
    static String CHAINCODENAME ="cartcc";
    static String FN_insertCart ="insertCart";
    static String FN_deleteCart ="deleteCart";
    static String FN_queryCart  ="queryCart";
    static String FN_queryRange  ="queryRange";


    //value[0] is key
    public static boolean insertCart(String[] value) {
        return BaseTool.insertKeyArrayValue(CHAINCODENAME,FN_insertCart,value);
    }

    public static boolean updateCart(String[] value) {
        return BaseTool.insertKeyArrayValue(CHAINCODENAME,FN_insertCart,value);
    }

    public static boolean deleteCart(String  key) {
        return BaseTool.deleteByKey(CHAINCODENAME,FN_deleteCart,key);
    }
    public static String queryCartByKey(String key) {
        return BaseTool.queryValueByKey(CHAINCODENAME,FN_queryCart,key);
    }


    //CartCCTool.queryBatch("cart_spfl_001","cart_spfl_002");
    public static String queryRange(String rangeStart,String rangeEnd) {
        return BaseTool.queryRange(CHAINCODENAME,FN_queryRange,rangeStart,rangeEnd);
    }


    public static void main(String args){
        CartCCTool.queryRange("cart_zcrq_2020-08-11","cart_zcrq_2020-08-13");
    }


}
