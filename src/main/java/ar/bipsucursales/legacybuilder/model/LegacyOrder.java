package ar.bipsucursales.legacybuilder.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LegacyOrder {

	private String transaction;
	private String operation;
	private int stackSize;
	private List<Request> requests;

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

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

	public LegacyOrder(@JsonProperty("transaction") String transaction, @JsonProperty("operation") String operation,
			@JsonProperty("stackSize") int stackSize, @JsonProperty("requests") List<Request> requests) {
		super();
		this.transaction = transaction;
		this.operation = operation;
		this.stackSize = stackSize;
		this.requests = requests;
	}

}
