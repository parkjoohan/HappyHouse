package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParameterDto;

public interface NoticeMapper {
	
	int registerNotice(NoticeDto noticeDto);
	List<NoticeDto> listNotice(NoticeParameterDto noticeParameterDto);
	NoticeDto getNotice(int articleNo);
	int modifyNotice(NoticeDto noticeDto);
	int deleteNotice(int articleNo);
}