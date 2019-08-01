package com.content.filter.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiResponse implements Serializable {

	private static final long serialVersionUID = -3374518765639360115L;

	private List<String> data;

	private HttpStatus status;

	public ApiResponse() {
		super();
	}

	public ApiResponse(List<String> data) {
		super();
		this.data = data;
	}

	public ApiResponse(List<String> data, HttpStatus status) {
		super();
		this.data = data;
		this.status = status;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ApiResponse [data=" + data + ", status=" + status + "]";
	}

}
