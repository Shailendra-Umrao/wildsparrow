package org.shail.api.diff.file.upload.form;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm implements Serializable{
	private static final long serialVersionUID = 5184286196032296516L;
	private String namingPatternStart;
	private String namingPatternEnd;
	private String inputExclusionList;
	private String classExclusionList;

	private List<MultipartFile> oldVersionJars;
	private List<MultipartFile> oldVersionThirdPartyJars;

	private List<MultipartFile> newVersionJars;
	private List<MultipartFile> newVersionThirdPartyJars;
	
	private String packageTobeScanned;
	
	public String getNamingPatternStart() {
		return namingPatternStart;
	}
	public void setNamingPatternStart(String namingPatternStart) {
		this.namingPatternStart = namingPatternStart;
	}
	public String getNamingPatternEnd() {
		return namingPatternEnd;
	}
	public void setNamingPatternEnd(String namingPatternEnd) {
		this.namingPatternEnd = namingPatternEnd;
	}
	public String getInputExclusionList() {
		return inputExclusionList;
	}
	public void setInputExclusionList(String inputExclusionList) {
		this.inputExclusionList = inputExclusionList;
	}
	public String getClassExclusionList() {
		return classExclusionList;
	}
	public void setClassExclusionList(String classExclusionList) {
		this.classExclusionList = classExclusionList;
	}
	public List<MultipartFile> getOldVersionJars() {
		return oldVersionJars;
	}
	public void setOldVersionJars(List<MultipartFile> oldVersionJars) {
		this.oldVersionJars = oldVersionJars;
	}
	public List<MultipartFile> getOldVersionThirdPartyJars() {
		return oldVersionThirdPartyJars;
	}
	public void setOldVersionThirdPartyJars(List<MultipartFile> oldVersionThirdPartyJars) {
		this.oldVersionThirdPartyJars = oldVersionThirdPartyJars;
	}
	public List<MultipartFile> getNewVersionJars() {
		return newVersionJars;
	}
	public void setNewVersionJars(List<MultipartFile> newVersionJars) {
		this.newVersionJars = newVersionJars;
	}
	public List<MultipartFile> getNewVersionThirdPartyJars() {
		return newVersionThirdPartyJars;
	}
	public void setNewVersionThirdPartyJars(List<MultipartFile> newVersionThirdPartyJars) {
		this.newVersionThirdPartyJars = newVersionThirdPartyJars;
	}
	public String getPackageTobeScanned() {
		return packageTobeScanned;
	}
	public void setPackageTobeScanned(String packageTobeScanned) {
		this.packageTobeScanned = packageTobeScanned;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileUploadForm [");
		if (namingPatternStart != null) {
			builder.append("namingPatternStart=");
			builder.append(namingPatternStart);
			builder.append(", ");
		}
		if (namingPatternEnd != null) {
			builder.append("namingPatternEnd=");
			builder.append(namingPatternEnd);
			builder.append(", ");
		}
		if (inputExclusionList != null) {
			builder.append("inputExclusionList=");
			builder.append(inputExclusionList);
			builder.append(", ");
		}
		if (classExclusionList != null) {
			builder.append("classExclusionList=");
			builder.append(classExclusionList);
			builder.append(", ");
		}
		if (oldVersionJars != null) {
			builder.append("oldVersionJars=");
			builder.append(oldVersionJars);
			builder.append(", ");
		}
		if (oldVersionThirdPartyJars != null) {
			builder.append("oldVersionThirdPartyJars=");
			builder.append(oldVersionThirdPartyJars);
			builder.append(", ");
		}
		if (newVersionJars != null) {
			builder.append("newVersionJars=");
			builder.append(newVersionJars);
			builder.append(", ");
		}
		if (newVersionThirdPartyJars != null) {
			builder.append("newVersionThirdPartyJars=");
			builder.append(newVersionThirdPartyJars);
			builder.append(", ");
		}
		if (packageTobeScanned != null) {
			builder.append("packageTobeScanned=");
			builder.append(packageTobeScanned);
		}
		builder.append("]");
		return builder.toString();
	}
	

}