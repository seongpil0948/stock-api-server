package kr.co.dsi.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

public class ApndFiles {
	private Integer apndFileId;

	private String apndFilePath;

	@JsonIgnore
	private String crteUsrId;

	private String contsType;

	private String fileExtn;

	private String apndFileNm;

	private String orgFileNm;

	private Integer fileSiz;

	private Integer fileIdx;

	public Integer getApndFileId() {
		return apndFileId;
	}

	public void setApndFileId(Integer apndFileId) {
		this.apndFileId = apndFileId;
	}

	public String getApndFilePath() {
		return apndFilePath;
	}

	public void setApndFilePath(String apndFilePath) {
		this.apndFilePath = apndFilePath;
	}

	public String getCrteUsrId() {
		return crteUsrId;
	}

	public void setCrteUsrId(String crteUsrId) {
		this.crteUsrId = crteUsrId;
	}

	public String getContsType() {
		return contsType;
	}

	public void setContsType(String contsType) {
		this.contsType = contsType;
	}

	public String getFileExtn() {
		return fileExtn;
	}

	public void setFileExtn(String fileExtn) {
		this.fileExtn = fileExtn;
	}

	public String getApndFileNm() {
		return apndFileNm;
	}

	public void setApndFileNm(String apndFileNm) {
		this.apndFileNm = apndFileNm;
	}

	public String getOrgFileNm() {
		return orgFileNm;
	}

	public void setOrgFileNm(String orgFileNm) {
		this.orgFileNm = orgFileNm;
	}

	public Integer getFileSiz() {
		return fileSiz;
	}

	public void setFileSiz(Integer fileSiz) {
		this.fileSiz = fileSiz;
	}

	public Integer getFileIdx() {
		return fileIdx;
	}

	public void setFileIdx(Integer fileIdx) {
		this.fileIdx = fileIdx;
	}

}
