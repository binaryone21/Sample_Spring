package seunghee.module;

import lombok.Data;
import seunghee.common.MapTool;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultVO {
    private boolean success = false;
    private String  message = "";
    private int     errorNo = 0;
    private Map<String, Object> data;

    public ResultVO() {
        this.success = true;
        this.message = "success";
    }

    public ResultVO(boolean success) {
        this.success = success;
        this.message = (success) ? "success" : "fail";
    }

    public ResultVO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResultVO(boolean success, String message, int errorNo) {
        this.success = success;
        this.message = message;
        this.errorNo = errorNo;
    }

    public void set(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public void set(boolean success, String message, int errorNo) {
        this.success = success;
        this.message = message;
        this.errorNo = errorNo;
    }

    public void put(String key, Object obj) {
        if(this.data == null) this.data = new HashMap<>();
        this.data.put(key, obj);
    }

    public void putAll(Map<String, Object> params) {
        if(this.data == null) this.data = new HashMap<>();
        for(Map.Entry<String, Object> entry : params.entrySet()) {
            this.data.put(entry.getKey(), entry.getValue());
        }
    }

    public void remove(String key) {
        if(this.data == null) this.data = new HashMap<>();
        if(this.data.containsKey(key)) this.data.remove(key);
    }

    public <T> T get(String key) {
        return MapTool.get(this.data, key);
    }

    public <T> T get(String key, T defaultValue) {
        return MapTool.get(this.data, key, defaultValue);
    }
}
