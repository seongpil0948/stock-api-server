package com.stock.sp.apiserver.common.utils;

import com.stock.sp.apiserver.common.exception.RestException;

import java.io.File;
import java.io.IOException;

/**
 * @author : 이성진
 * @systemName : aes
 * @programName : FileUtil
 * @createDate : 2022. 7. 15.
 * @description :
 */
public class FileUtil {
	public static void createFile(File file) throws IOException {
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			if (!file.createNewFile()) {
				throw new RestException("Temp File Creation Error");
			}
		}
	}
}
