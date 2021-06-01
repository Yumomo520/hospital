package com.goodmap.hospital.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityResult<T> implements Serializable {
	private String code;
	private String message;
	private T data;

	public EntityResult(String code, String message) {
		this(code, message, null);
	}
}
