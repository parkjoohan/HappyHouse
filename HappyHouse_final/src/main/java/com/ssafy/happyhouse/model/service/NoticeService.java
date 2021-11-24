package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParameterDto;

public interface NoticeService {

	boolean registerNotice(NoticeDto noticeDto);
	List<NoticeDto> listNotice(NoticeParameterDto noticeParameterDto);
	NoticeDto getNotice(int noticeNo);
	boolean modifyNotice(NoticeDto noticeDto);
	boolean deleteNotice(int noticeNo);
}