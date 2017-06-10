package com.service.library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.google.gson.Gson;

/**
 *
 * @author Reza
 */
public class RestUtil {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";

    public static <T> ResponseEntity<T> getJsonResponse(T src) {
        HttpHeaders headers = new HttpHeaders();
	headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	return new ResponseEntity<>(src, headers, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> getJsonResponse(T src, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        Map<String,Object> map=new HashMap<>();
        map.put("data", src);
	return new ResponseEntity(map, headers, status);
    }

    public static <T> ResponseEntity<T> getJsonResponse(T src,
        HttpStatus status, Map<String, String> mapHeaderMessage) {
        HttpHeaders headers = new HttpHeaders();
            if (null != mapHeaderMessage) {
		for (String key : mapHeaderMessage.keySet()) {
                    headers.add(key, mapHeaderMessage.get(key));
		}
            }
        Map<String,Object> map=new HashMap<>();
        map.put("messages", mapHeaderMessage);
        map.put("data", src);
        headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity(map, headers, status);
    }

    public static <T> ResponseEntity<T> getJsonHttptatus(HttpStatus status) {
        return new ResponseEntity<>(status);
    }
	
    public static <T> ResponseEntity<T> getJsonHttptatus(HttpStatus status, Map<String, String> mapHeaderMessage) {
        HttpHeaders headers = new HttpHeaders();
        if (null != mapHeaderMessage) {
            for (String key : mapHeaderMessage.keySet()) {
                headers.add(key, mapHeaderMessage.get(key));
            }
        }
        headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	return new ResponseEntity<>(headers, status);
    }
    
    public static <T> ResponseEntity<Set<T>> defaultJsonResponse(Set<T> src) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	return new ResponseEntity<>(src, headers, HttpStatus.OK);
    }

    public static <T> ResponseEntity<Set<T>> defaultJsonResponse(Set<T> src, Map<String, String> mapHeaderMessage) {
        HttpHeaders headers = new HttpHeaders();
	if (null != mapHeaderMessage) {
            for (String key : mapHeaderMessage.keySet()) {
                headers.add(key, mapHeaderMessage.get(key));
            }
	}
	headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	return new ResponseEntity<>(src, headers, HttpStatus.OK);
    }

    public static <T> ResponseEntity<List<T>> defaultJsonResponse(List<T> src, Map<String, String> mapHeaderMessage) {
        HttpHeaders headers = new HttpHeaders();
	if (null != mapHeaderMessage) {
            for (String key : mapHeaderMessage.keySet()) {
		headers.add(key, mapHeaderMessage.get(key));
            }
	}
        headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(src, headers, HttpStatus.OK);
    }

    public static <T> ResponseEntity<List<T>> defaultJsonResponse(List<T> src) {
        HttpHeaders headers = new HttpHeaders();
	headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	return new ResponseEntity<>(src, headers, HttpStatus.OK);
    }

    public static ResponseEntity<String> defaultJsonResponse(Object src) {
        Gson gson = new Gson();
        HttpHeaders headers = new HttpHeaders();
	headers.set(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	return new ResponseEntity<>(gson.toJson(src), headers, HttpStatus.OK);
    }
    
    public static <T> T jsonToObject(String strJson, Class<T> type) {
        Gson gson = new Gson();
	return gson.fromJson(strJson, type);
    }

    public static String toJson(Object object) {
	Gson gson = new Gson();
	return gson.toJson(object);
    }
    
}