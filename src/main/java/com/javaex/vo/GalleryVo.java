package com.javaex.vo;

public class GalleryVo {

	private int no;
	private int user_No;
	private String Name;
	private String content;
	private String filePath;
	private String orgName;
	private String saveName;
	private int fileSize;
	
	
	
	public GalleryVo() {
		super();
	}



	public GalleryVo(int user_No, String content, String filePath, String orgName, String saveName, int fileSize) {
		super();
		this.user_No = user_No;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}



	public GalleryVo(int no, int user_No, String name, String content, String filePath, String orgName, String saveName,
			int fileSize) {
		this.no = no;
		this.user_No = user_No;
		Name = name;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}



	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}



	public int getUser_No() {
		return user_No;
	}



	public void setUser_No(int user_No) {
		this.user_No = user_No;
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getFilePath() {
		return filePath;
	}



	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}



	public String getOrgName() {
		return orgName;
	}



	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}



	public String getSaveName() {
		return saveName;
	}



	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}



	public int getFileSize() {
		return fileSize;
	}



	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}



	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", user_No=" + user_No + ", Name=" + Name + ", content=" + content
				+ ", filePath=" + filePath + ", orgName=" + orgName + ", saveName=" + saveName + ", fileSize="
				+ fileSize + "]";
	}
	
	
	
	
	
}
