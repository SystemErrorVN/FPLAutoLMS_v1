/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import request.support.HttpRequestHeader;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Administrator
 */
public class Utilities {
    
    public static HttpRequestHeader buildHttpRequestHeader(String cookie){
        HttpRequestHeader httpRequestHeader = new HttpRequestHeader();
        httpRequestHeader.add("Cookie", cookie);
        return httpRequestHeader;
    }
    
    public static String Base64Encode(String inp) {
        BASE64Encoder b64 = new BASE64Encoder();
        return b64.encodeBuffer(inp.getBytes());
    }
    
}
