package com.servlet.adminlte.service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Service {
    private static final Charset UTF_8= StandardCharsets.UTF_8;
    private static final String  OUTPUT_FORMAT="%-20s:%s";

    public   byte[] digest(byte[] input){
        MessageDigest md;
        try {
            md=MessageDigest.getInstance("MD5");

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result= md.digest(input);
        return result;
    }

    public  String byteToHex(byte[] bytes){
        StringBuilder sb=new StringBuilder();
        for (byte b: bytes){
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
