package com.bluefox.apiFipe.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class ConvertAPI implements IConvertAPI{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonMappingException e) {
            throw new RuntimeException();
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public <T> List<T> getListData(String json, Class<T> classe) {
        CollectionType listType = mapper.getTypeFactory()
                                        .constructCollectionType(List.class, classe);

        try {
            return mapper.readValue(json, listType);
        } catch (JsonMappingException e) {
            throw new RuntimeException();
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
    
}
