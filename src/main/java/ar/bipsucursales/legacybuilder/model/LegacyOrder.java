package ar.bipsucursales.legacybuilder.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LegacyOrder {

	private String transaction;
	private String operation;
	private int stackSize;
	private List<JsonFile> requests;
	private List<JsonFile> responses;

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public int getStackSize() {
		return stackSize;
	}

	public void setStackSize(int stackSize) {
		this.stackSize = stackSize;
	}

	public List<JsonFile> getRequests() {
		return requests;
	}

	public void setRequests(List<JsonFile> requests) {
		this.requests = requests;
	}

	public List<JsonFile> getResponses() {
		return responses;
	}

	public void setResponses(List<JsonFile> responses) {
		this.responses = responses;
	}

	public LegacyOrder(@JsonProperty("transaction") String transaction, @JsonProperty("operation") String operation,
			@JsonProperty("stackSize") int stackSize, @JsonProperty("requests") List<JsonFile> requests,
			@JsonProperty("responses") List<JsonFile> responses) {
		super();
		this.transaction = transaction;
		this.operation = operation;
		this.stackSize = stackSize;
		this.requests = requests;
		this.responses = responses;
	}

}
