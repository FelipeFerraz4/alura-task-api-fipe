package com.bluefox.apiFipe.service;

import java.util.List;

public interface IConvertAPI {
    <T> T getData(String json, Class<T> classe);

    <T> List<T> getListData(String json, Class<T> classe);
}
