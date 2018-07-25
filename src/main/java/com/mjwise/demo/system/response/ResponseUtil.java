package com.mjwise.demo.system.response;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mjwise.demo.system.utils.JSONBeanUtils;
import java.util.List;

/**
 * Created by liyub on 2018/5/12.
 */
public class ResponseUtil {

    public static String DATA_KEY = "data";

    public static String ERR_CODE_KEY = "code";

    public static String ERR_MSG_KEY = "msg";

    public static JSONObject getBaseResponse(){
        JSONObject jo = new JSONObject();
        jo.put(DATA_KEY, "");
        jo.put(ERR_CODE_KEY, 0);
        jo.put(ERR_MSG_KEY, "");
        return jo;
    }

    public static JSONObject getSuccessResponse(Object data){
        JSONObject jo = new JSONObject();
        try{
            if((data instanceof JSONObject) || (data instanceof JSONArray)){
                jo.put(DATA_KEY, data);
            }else if(data instanceof List){
                jo.put(DATA_KEY, JSONBeanUtils.listToJson((List)data));
            }else if(data instanceof String){
                JSONObject sdata = new JSONObject();
                sdata.put("msg", data);
                jo.put(DATA_KEY, sdata);
            }else{
                jo.put(DATA_KEY, JSONBeanUtils.beanToJson(data));
            }
            jo.put(ERR_CODE_KEY, 0);
            jo.put(ERR_MSG_KEY, "");
        }catch (Exception e){
            jo.put(DATA_KEY, "");
            jo.put(ERR_CODE_KEY, 500);
            jo.put(ERR_MSG_KEY, "请求结果转换失败");
        }
        return jo;
    }

    public static JSONObject getFailResponse(int code,String msg){
        JSONObject jo = new JSONObject();
        jo.put(DATA_KEY, "");
        jo.put(ERR_CODE_KEY, code);
        jo.put(ERR_MSG_KEY, msg);
        return jo;
    }
}
