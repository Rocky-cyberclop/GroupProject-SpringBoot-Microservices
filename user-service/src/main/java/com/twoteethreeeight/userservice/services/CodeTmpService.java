package com.twoteethreeeight.userservice.services;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class CodeTmpService {

	private static final Map<String, CodeTmp> CodeTmpMap = new HashMap<>();
	 private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*";
	// khoi tao code
	public String initCode() {
		return "123456";
	}
	public String initHasCode() {
		// sử dụng thư viện  là một lớp trong gói java.security
		 SecureRandom random = new SecureRandom();
	        StringBuilder code = new StringBuilder();

	        for (int i = 0; i < 8; i++) {
	            int index = random.nextInt(CHARACTERS.length());
	            code.append(CHARACTERS.charAt(index));
	        }
	        return code.toString();
	}
	
	
	// tao moi code voi thoi gian hop le 
	public String genarateCodeTmp(String email) {
		String codeTmp = initCode();
		LocalDateTime  expirationTime = LocalDateTime.now().plusMinutes(5);
		
		CodeTmpMap.put(email, new CodeTmp(codeTmp, expirationTime));
		return codeTmp;
	}
	// kiem tra code 
	public boolean validateCode(String email, String responseCode) {
		CodeTmp codeTmp = CodeTmpMap.get(email);
		// kiem tra codetmp nhap vao 
		if (codeTmp != null && codeTmp.getCode().equals(responseCode) && !codeTmp.isExpirated()) {
			// neu dung xoa code da luu va tra ve true
			CodeTmpMap.remove(email);
			return true;
		}
		
		return false;
	}
	// xoa code thu cong  
	public void invalidCode(String emmail) {
		CodeTmpMap.remove(emmail);
	}
	
	@Scheduled(fixedRate = 60000)
	public void autoRemoveExpiratedCode() {
		LocalDateTime now = LocalDateTime.now();
		List< String > expiratedCodes = new ArrayList<>();
		for(Map.Entry<String, CodeTmp> entry : CodeTmpMap.entrySet()) {
			CodeTmp c = entry.getValue();
				if (c.isExpired(now)) {
					expiratedCodes.add(entry.getKey());
				}
		}
		for(String email : expiratedCodes) {
			CodeTmpMap.remove(email);
		}
	}

}
