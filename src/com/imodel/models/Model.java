package com.imodel.models;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "models")
public class Model {
	private Long id;
	private String modelName;
	private String algorithm;
	private String fileDir;
	private String fileName;
	private Timestamp createdTime;
	private Integer published;
	private String modelInput;
	private String modelTarget;
	private Long userId;
	private Integer state;
	private Integer size;
	private String appKey;
	
	
	
	
	
	@Basic
    @Column(name = "app_key")
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	@Basic
    @Column(name = "model_size")
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	@Basic
    @Column(name = "state")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Basic
    @Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Basic
    @Column(name = "model_name")
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	@Basic
    @Column(name = "algorithm")
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	@Basic
    @Column(name = "file_dir")
	public String getFileDir() {
		return fileDir;
	}
	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}
	
	@Basic
    @Column(name = "file_name")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Basic
    @Column(name = "created_time")
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	
	@Basic
    @Column(name = "published")
	public Integer getPublished() {
		return published;
	}
	public void setPublished(Integer published) {
		this.published = published;
	}
	
	@Basic
    @Column(name = "model_input")
	public String getModelInput() {
		return modelInput;
	}
	public void setModelInput(String modelInput) {
		this.modelInput = modelInput;
	}
	
	@Basic
    @Column(name = "model_target")
	public String getModelTarget() {
		return modelTarget;
	}
	public void setModelTarget(String modelTarget) {
		this.modelTarget = modelTarget;
	}
	
	

}
