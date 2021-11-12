package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;
import com.ssafy.happyhouse.model.NoticeDto;

public interface NoticeMapper {
	
	int registerNotice(NoticeDto noticeDto);
	List<NoticeDto> listNotice();
	NoticeDto getNotice(int articleNo);
	int modifyNotice(NoticeDto noticeDto);
	int deleteNotice(int articleNo);
	
	//List<NoticeDto> listNotice(Map<String, Object> map);
	//int getTotalCount(Map<String, String> map);
	//void registerFile(NoticeDto noticeDto) throws Exception;
	//void deleteFile(int noticeNo) throws Exception;
}