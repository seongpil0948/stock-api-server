package com.stock.sp.apiserver.common.utils;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ===========================================
 * 
 * @ProgramName : JwtUtils
 * @Author : "ChangGuenKim"
 * @Version : 1.0
 * @Dscription :
 *             ===========================================
 */
public class JwtUtils {
	/*
	public static void main(String[] args) throws ParseException {
		try {
			String userId = "testUser";
			String seckey = "dmkscretkey23456";
			String encryptJson = encrypt(userId, seckey);			// 암호화된 전문
			System.out.println(encryptJson);
			
			// 복호화 및 json parsing
			JSONObject jsonObj = decrypt(encryptJson, seckey);		// JSON
	
			// 최종 수신 정보
			String reqUserId = jsonObj.get("info1").toString();
			String expiredDateTime = jsonObj.get("expiredDateTime").toString();
			System.out.println(reqUserId);
			System.out.println(expiredDateTime);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	*/

	/**
	 * AES256 으로 암호화 한다.
	 * 키값의 길이가 16이하 Check
	 *
	 * @param userId
	 * @param key16
	 * @param addTimeField
	 * @param addAmount
	 * @return Json 암호화
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public static String encrypt(String userId, String key16, int addTimeField, int addAmount) throws NoSuchAlgorithmException,
			GeneralSecurityException, UnsupportedEncodingException {

		String iv = key16.substring(0, 16);
		byte[] keyBytes = new byte[16];
		byte[] b = key16.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}

		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

		String expiredDate = null;
		Date now = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(addTimeField, addAmount); // 로컬은 한국시간, 서버끼리는 미국시간이라 로컬,개발/운영 간 설정이 달라야 함
		expiredDate = sdformat.format(cal.getTime());

		Map<String, Object> data = new HashMap<String, Object>();

		data.put("info1", userId);
		data.put("expiredDateTime", expiredDate);
		JSONObject json = new JSONObject();
		json.putAll(data);

		String target = json.toJSONString();

		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
		byte[] encrypted = c.doFinal(target.getBytes("UTF-8"));
		String enStr = new String(Base64.encodeBase64(encrypted));
		return enStr;
	}

	/**
	 * AES256 으로 암호화 한다.
	 * 키값의 길이가 16이하 Check
	 *
	 * @param userId
	 * @param cucoId
	 * @param key16
	 * @param addTimeField
	 * @param addAmount
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public static String encrypt(String userId, String cucoId, String key16, int addTimeField, int addAmount) throws NoSuchAlgorithmException,
			GeneralSecurityException, UnsupportedEncodingException {

		String iv = key16.substring(0, 16);
		byte[] keyBytes = new byte[16];
		byte[] b = key16.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}

		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

		String expiredDate = null;
		Date now = new Date();
//		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(addTimeField, addAmount); // 로컬은 한국시간, 서버끼리는 미국시간이라 로컬,개발/운영 간 설정이 달라야 함
//		expiredDate = sdformat.format(cal.getTime());
		expiredDate = String.valueOf(cal.getTime().getTime());

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", userId);
		data.put("customer", cucoId);
		data.put("exp", expiredDate);
		JSONObject json = new JSONObject();
		json.putAll(data);

		String target = json.toJSONString();

		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
		byte[] encrypted = c.doFinal(target.getBytes("UTF-8"));
		String enStr = new String(Base64.encodeBase64(encrypted));
		return enStr;
	}

	/**
	 * AES256으로 암호화된 txt를 복호화한다.
	 * 
	 * @param str
	 *            복호화할 문자열
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 */
	public static JSONObject decrypt(String str, String key16) throws NoSuchAlgorithmException,
			GeneralSecurityException, UnsupportedEncodingException, ParseException {
		String iv = key16.substring(0, 16);
		byte[] keyBytes = new byte[16];
		byte[] b = key16.getBytes("UTF-8");
		int len = b.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}

		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
		byte[] byteStr = Base64.decodeBase64(str.getBytes());

		String decryptedStr = new String(c.doFinal(byteStr), "UTF-8");

		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParse.parse(decryptedStr);

		return jsonObj;
	}
}
