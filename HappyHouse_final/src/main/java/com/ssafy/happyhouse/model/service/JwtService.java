package com.ssafy.happyhouse.model.service;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	private static final String salt = "MYSALT";
	private static final int expireMin = 5; //5분
	
	//로그인 성공 시 사용자 정보를 기반으로 토큰을 생성해서 반환
	public String create(final MemberDto member) {
		
		//JWT Token = Header + Payload + Signature
		JwtBuilder builder = Jwts.builder();

		//Header 설정
		builder.setHeaderParam("typ", "JWT");
		
		//Payload 설정 - claim 정보 포함
		builder.setSubject("Login Token") //토큰 제목 설정
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))
			.claim("Member", member); //담고 싶은 정보
		
		//signature - secret Key를 이용한 암호화
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		
		//직렬화 처리
		final String jwt = builder.compact();
		return jwt;
	}
	
	//전달받은 토큰이 유효한 토큰인지 확인
	public void checkValid(final String jwt) {
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	}
	
	//jwt 토큰을 분석해서 필요한 정보를 반환
	public Map<String, Object> get(final String jwt){
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return claims.getBody();
	}
}
