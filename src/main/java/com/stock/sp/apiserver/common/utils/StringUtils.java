package kr.co.dsi.common.utils;

import org.springframework.http.server.PathContainer;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Map;
import java.util.UUID;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

	public static String encodeURIComponent(String s) {
		String result;
		try {
			result = URLEncoder.encode(s, "UTF-8")
					.replaceAll("\\+", "%20")
					.replaceAll("%21", "!")
					.replaceAll("%27", "'")
					.replaceAll("%28", "(")
					.replaceAll("%29", ")")
					.replaceAll("%7E", "~");
		} catch (UnsupportedEncodingException e) {
			result = s;
		}
		return result;
	}

	public static String getRuleFileKeyName(URI uri) {
		String keyName = "";
		String temp = uri.toString().substring(uri.toString().lastIndexOf("/"));
		String replace1 = uri.toString().replaceFirst(temp, "." + temp.substring(1));
		keyName = replace1.substring(replace1.lastIndexOf("/") + 1);
		return keyName;
	}

	public static String getGUID32() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String getRandomStr(int nSize) {
		StringBuffer temp = new StringBuffer();
		SecureRandom rnd = new SecureRandom();
		for (int i = 0; i < nSize; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
				case 0:
					// a-z
					temp.append((char) ((rnd.nextInt(26)) + 97));
					break;
				case 1:
					// A-Z
					temp.append((char) ((rnd.nextInt(26)) + 65));
					break;
				case 2:
					// 0-9
					temp.append((rnd.nextInt(10)));
					break;
			}
		}
		return temp.toString();
	}

	public static String ifNullToEmpty(String obj) {
		String result = "";
		if (obj != null) {
			return obj;
		}
		return result;
	}

	public static String ifNullToEmpty(Object obj) {
		String result = "";
		if (obj != null) {
			result = String.valueOf(obj);
			if ("null".equals(result)) {
				result = "";
			}
			return result;
		}
		return result;
	}

	public static String objectIfNullToEmpty(Object obj) {
		String result = "";
		if (obj != null) {
			result = String.valueOf(obj);
			if ("null".equals(result)) {
				result = "";
			}
			return result;
		}
		return result;
	}

	public static String formEncode(Map<String, Object> m) {
		String s = "";
		for (String key : m.keySet()) {
			if (s.length() > 0) {
				s += "&";
			}
			s += key + "=" + m.get(key);
		}
		return s;
	}

	public static boolean uriPatternMatches(String pattern, String input) {
		PathPattern pp = uriPathParse(pattern);
		return pp.matches(PathContainer.parsePath(input));
	}

	private static PathPattern uriPathParse(String path) {
		PathPatternParser pp = new PathPatternParser();
		pp.setMatchOptionalTrailingSeparator(true);
		return pp.parse(path);
	}
}
