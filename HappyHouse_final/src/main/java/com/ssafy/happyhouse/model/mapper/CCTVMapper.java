package com.ssafy.happyhouse.model.mapper;

import java.util.*;
import com.ssafy.happyhouse.model.CCTVDto;

public interface CCTVMapper {
	
	List<CCTVDto> getCCTVInfo(String dongCode)throws Exception;
}
