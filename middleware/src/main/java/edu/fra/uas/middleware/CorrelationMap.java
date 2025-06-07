package edu.fra.uas.middleware;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class CorrelationMap extends ConcurrentHashMap<String, String> {

}
