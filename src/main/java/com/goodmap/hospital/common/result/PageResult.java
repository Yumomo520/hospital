package com.goodmap.hospital.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
	private String code;
	private String message;
	private long count;//总记录数
	private List<T> data;//当前页结果
}