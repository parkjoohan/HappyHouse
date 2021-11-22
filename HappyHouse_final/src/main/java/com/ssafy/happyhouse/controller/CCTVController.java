package com.ssafy.happyhouse.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.CCTVDto;
import com.ssafy.happyhouse.model.service.CCTVService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/cctv")
public class CCTVController {
		
	@Autowired
	private CCTVService cctvService;

	@GetMapping("/cctvLocation")
	public ModelAndView houseSalesForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cctv/cctvLocation");
		return mv;
	}
		
	@GetMapping("/cctvVideo")
	public ModelAndView cctvVideoForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cctv/cctvVideo");
		return mv;
	}

//	@GetMapping("/location")
//	public Map<String, Object> getCCTVInfo(String dong,String pg) {
//		Map<String, String> map = new HashMap<String, String>();
//		String spp = map.get("spp");
//		map.put("spp", spp != null ? spp : "5");//sizePerPage
//
////		String pg = map.get("pg");
//		map.put("pg", pg != "" ? pg : "1");
//		map.put("dong", dong);
//		Map<String, Object> param = new HashMap<String, Object>();
//			
//		try {
//			List<CCTVDto> list = cctvService.getCCTVInfo(map);
////			PageNavigation pageNavigation = cctvService.makePageNavigation(map);
////			for (int i = 0; i < list.size(); i++) {
////				System.out.println(list.get(i).toString());
////			}
//			param.put("list", list);
////			param.put("navigation", pageNavigation);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//			
//		return param;
//	}

}

