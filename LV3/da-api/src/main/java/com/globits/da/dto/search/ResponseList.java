package com.globits.da.dto.search;

import com.globits.da.Validate.ResponseStatus;

import java.util.LinkedList;
import java.util.List;

public class ResponseList<T> {
    private T data;
    private List<Integer> codes;
    private List<String> messages;


    public ResponseList() {
        codes = new LinkedList<>();
        messages = new LinkedList<>();
    }

    public ResponseList(T data) {
        this.data = data;
        codes = new LinkedList<>();
        messages = new LinkedList<>();
    }

    public ResponseList(T data, ResponseStatus status) {
        this.data = data;
        List<Integer> newCodes = new LinkedList<>();
        newCodes.add(status.getCode());
        this.codes = newCodes;
        List<String> newMessages = new LinkedList<>();
        newMessages.add(status.getMessage());
        this.messages = newMessages;
    }

    public ResponseList(ResponseStatus status) {
        List<Integer> newCodes = new LinkedList<>();
        newCodes.add(status.getCode());
        this.codes = newCodes;
        List<String> newMessages = new LinkedList<>();
        newMessages.add(status.getMessage());
        this.messages = newMessages;
    }

    public void addCode(Integer code) {
        codes.add(code);
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Integer> getCodes() {
        return codes;
    }

    public void setCodes(List<Integer> codes) {
        this.codes = codes;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
