package client.controller;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;

@Getter
public enum Fruit {

	APPLE("사과"),BANANA("바나나"), FRUNE("자두");
	
	private String val;

	private Fruit(String val) {
		this.val = val;
	} 
	
	public static Stream<Fruit> stream() {
        return Stream.of(Fruit.values()); 
    }
	
	public static Map<String, String> toMap() {
        return Stream.of(values()).collect(Collectors.toMap(k->k.name(), e->e.val));
        		
    }
}
