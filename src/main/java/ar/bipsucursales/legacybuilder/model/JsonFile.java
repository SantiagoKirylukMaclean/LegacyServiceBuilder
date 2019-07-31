package ar.bipsucursales.legacybuilder.model;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonFile {
	
	private String name;
	private HashMap<String,Object>  fields;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, Object> getFields() {
		return fields;
	}
	public void setFields(HashMap<String, Object> fields) {
		this.fields = fields;
	}
	public JsonFile(@JsonProperty("name") String name, @JsonProperty("fields") HashMap<String,Object> fields) {
		super();
		this.name = name;
		this.fields = fields;
	}
}
