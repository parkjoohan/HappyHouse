package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BoardParameterDto : 게시판 파라미터 정보", description = "게시판의 글을 얻기위한 부가적인 파라미터정보.")
public class NoticeParameterDto {

	@ApiModelProperty(value = "검색 조건")
	private String key;
	@ApiModelProperty(value = "검색어")
	private String word;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return "NoticeParameterDto [key=" + key + ", word=" + word + "]";
	}
}