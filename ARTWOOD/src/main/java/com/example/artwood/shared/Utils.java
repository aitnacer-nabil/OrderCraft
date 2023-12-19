package com.example.artwood.shared;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class Utils {
    private static   final Logger logger = LogManager.getLogger();
    public static String GenerateId(){
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString().substring(0,6);
        return uuidAsString;
    }
    public static void printInfoMessage(String message){
        logger.info(message);
    }
    public static void printWarningMessage(String message){
        logger.warn(message);
    }
}
