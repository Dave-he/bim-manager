package com.mjwise.demo.entity.file;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "file_image")
public class ImageFile {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "id", columnDefinition = "varchar(64) binary")
	private String id;

	/**
	 * 原始文件名
	 */
	@Type(type = "text")
	@Column(name = "original_name")
	private String originalName;

	/**
	 * 新文件名
	 */
	@Type(type = "text")
	@Column(name = "presentName")
	private String presentName;

	@Column(name = "suffix")
	private String suffix;

	/**
	 * 文件绝对路径
	 */
	@Column(name = "root_path")
	private String rootPath;

	/**
	 * 上传时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "upload_date")
	private Date uploadDate;

	/**
	 * 文件是否有效
	 */
	@Column(name = "valid_state",columnDefinition = "bit(1) not null default 0")
	private boolean validState;

	public boolean isValidState() {
		return validState;
	}

	public void setValidState(boolean validState) {
		this.validState = validState;
	}

	/**
	 * 文件用途
	 */
	@Column(name = "used_Type")
	private String usedType;

	public ImageFile() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getPresentName() {
		return presentName;
	}

	public void setPresentName(String presentName) {
		this.presentName = presentName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUsedType() {
		return usedType;
	}

	public void setUsedType(String usedType) {
		this.usedType = usedType;
	}
}