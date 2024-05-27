package com.bluefox.apiFipe.service;

public interface IConvertAPI {
    <T> T getData(String json, Class<T> classe);
}
