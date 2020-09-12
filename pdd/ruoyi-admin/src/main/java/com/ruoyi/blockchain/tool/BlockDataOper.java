package com.ruoyi.blockchain.tool;

import com.google.protobuf.ByteString;
import javafx.util.converter.ByteStringConverter;

import java.util.List;

public class BlockDataOper {

    public static String bytesToHexString(byte[] src){

        StringBuilder stringBuilder = new StringBuilder("");

        if (src == null || src.length <= 0) {

            return null;

        }

        for (int i = 0; i < src.length; i++) {

            int v = src[i] & 0xFF;

            String hv = Integer.toHexString(v);

            if (hv.length() < 2) {

                stringBuilder.append(0);

            }

            stringBuilder.append(hv);

        }

        return stringBuilder.toString();

    }

    /**

     * Convert hex string to byte[]

     * @param hexString the hex string

     * @return byte[]

     */
    /*

    public static byte[] hexStringToBytes(String hexString) {

        if (hexString == null || hexString.equals("")) {

            return null;

        }

        hexString = hexString.toUpperCase();

        int length = hexString.length() / 2;

        char[] hexChars = hexString.toCharArray();

        byte[] d = new byte[length];

        for (int i = 0; i < length; i++) {

            int pos = i * 2;

            d[i] = (byte) ( charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));

        }

        return d;

    }
    */

    /**

     * Convert char to byte

     * @param c char

     * @return byte

     */

    private byte charToByte(char c) {

        return (byte) "0123456789ABCDEF".indexOf(c);

    }

    public static String int10toString16(int valueTen){

        //定义一个十进制值
       // int valueTen = 328;
        //将其转换为十六进制并输出
        String strHex = Integer.toHexString(valueTen);
        //LOGGER.info(valueTen + " [十进制]---->[十六进制] " + strHex);
        //将十六进制格式化输出
        String strHex2 = String.format("%08x",valueTen);
        //LOGGER.info(valueTen + " [十进制]---->[十六进制] " + strHex2);
        return strHex2;
    }



    public static String getDataListString(List<ByteString> DataList){
        String s="";
        for(int i=0;i<DataList.size();i++){
            s+=ByteString2String( DataList.get(i))+"\n";
                   //.toString()+"\n";



        }
        return s;

    }

    public static String ByteString2String(ByteString bytes) {
        String s="";
        for(Byte b:bytes){

            s+=b.toString();
        }

        return s;
    }


    public static String ByteString2String16Hex(ByteString bytes) {
        String s="";
        for(Byte b:bytes){

            s+=int10toString16(b.intValue());
            //s+=b.toString();
        }

        return s;
    }

    public static String bytes2String(byte[] bytes){
        ByteStringConverter converter=(new ByteStringConverter());
        String s="";
        for(int i=0;i<bytes.length;i++){
            s+=converter.toString(bytes[i]);
        }
        return s;

    }


}
