package com.prohua.dovedemo.bean;

import java.io.Serializable;

/**
 * 全局基类
 *
 * @author Deep
 * @date 2017/10/12 0012
 */

public class NetBase<T> implements Serializable {
    /**
     * 状态: true / false
     */
    public boolean state;
    /**
     * 信息: String
     */
    public String message;
    /**
     * 返回的结构数据
     */
    public  T data;
}
