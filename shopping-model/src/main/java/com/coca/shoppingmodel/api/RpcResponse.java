package com.coca.shoppingmodel.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcResponse<T> implements Serializable {

    private boolean success;

    private String message;

    private T entity;

    /**
     * 结果
     *
     * @param message
     * @param entity
     * @param resultFlag
     * @param <T>
     * @return
     */
    public static <T> RpcResponse result(String message, T entity, Boolean resultFlag) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setSuccess(resultFlag);
        rpcResponse.setEntity(entity);
        rpcResponse.setMessage(message);
        return rpcResponse;
    }

    /**
     * 成功
     *
     * @param message
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> RpcResponse success(String message, T entity) {
        return RpcResponse.result(message, entity, Boolean.TRUE);
    }

    /**
     * 成功
     *
     * @return
     */
    public static RpcResponse success(String message) {
        return RpcResponse.success(message, null);
    }

    /**
     * 成功
     *
     * @return
     */
    public static RpcResponse success() {
        return RpcResponse.success(null, null);
    }

    /**
     * 成功
     *
     * @return
     */
    public static <T> RpcResponse success(T entity) {
        return RpcResponse.success("execute the process is success!", entity);
    }

    /**
     * 失败
     *
     * @param message
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> RpcResponse failure(String message, T entity) {
        return RpcResponse.result(message, entity, Boolean.FALSE);
    }

    /**
     * 失败
     *
     * @return
     */
    public static RpcResponse failure(String message) {
        return RpcResponse.failure(message, null);
    }

    /**
     * 失败
     *
     * @return
     */
    public static <T> RpcResponse failure(T entity) {
        return RpcResponse.failure("execute the process is failure!", entity);
    }
}
