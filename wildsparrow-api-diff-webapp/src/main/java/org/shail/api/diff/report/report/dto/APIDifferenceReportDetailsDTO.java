
package org.shail.api.diff.report.report.dto;

import java.io.Serializable;

public class APIDifferenceReportDetailsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6855201839744759774L;

	public APIDifferenceReportDetailsDTO() {

		super();
	}

	public APIDifferenceReportDetailsDTO(String className,
	                              String methodName,
	                              String change,
	                              String oldSignature,
	                              String newSignature,
	                              String dTOs_changed,
	                              String module) {

		super();
		this.class_Name = className;
		this.method_Name = methodName;
		this.change = change;
		this.old_Signature = oldSignature;
		this.new_Signature = newSignature;
		this.dTOs_changed = dTOs_changed;
		this.module = module;
	}

	private String class_Name;
	private String module;
	private String method_Name;
	private String change;
	private String old_Signature;
	private String new_Signature;
	private String dTOs_changed;

	/**
	 * @return the class_Name
	 */
	public String getClass_Name() {

		return class_Name;
	}

	/**
	 * @param class_Name the class_Name to set
	 */
	public void setClass_Name(String class_Name) {

		this.class_Name = class_Name;
	}

	/**
	 * @return the method_Name
	 */
	public String getMethod_Name() {

		return method_Name;
	}

	/**
	 * @param method_Name the method_Name to set
	 */
	public void setMethod_Name(String method_Name) {

		this.method_Name = method_Name;
	}

	/**
	 * @return the change
	 */
	public String getChange() {

		return change;
	}

	/**
	 * @param change the change to set
	 */
	public void setChange(String change) {

		this.change = change;
	}

	/**
	 * @return the old_Signature
	 */
	public String getOld_Signature() {

		return old_Signature;
	}

	/**
	 * @param old_Signature the old_Signature to set
	 */
	public void setOld_Signature(String old_Signature) {

		this.old_Signature = old_Signature;
	}

	/**
	 * @return the new_Signature
	 */
	public String getNew_Signature() {

		return new_Signature;
	}

	/**
	 * @param new_Signature the new_Signature to set
	 */
	public void setNew_Signature(String new_Signature) {

		this.new_Signature = new_Signature;
	}

	/**
	 * @return the dTOs_changed
	 */
	public String getDTOs_changed() {

		return dTOs_changed;
	}

	/**
	 * @param dTOs_changed the dTOs_changed to set
	 */
	public void setDTOs_changed(String dTOs_changed) {

		this.dTOs_changed = dTOs_changed;
	}

	/**
	 * @return the module
	 */
	public String getModule() {

		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {

		this.module = module;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("APIDifferenceReportDetailsDTO [");
		if (class_Name != null) {
			builder.append("class_Name=");
			builder.append(class_Name);
			builder.append(", ");
		}
		if (module != null) {
			builder.append("module=");
			builder.append(module);
			builder.append(", ");
		}
		if (method_Name != null) {
			builder.append("method_Name=");
			builder.append(method_Name);
			builder.append(", ");
		}
		if (change != null) {
			builder.append("change=");
			builder.append(change);
			builder.append(", ");
		}
		if (old_Signature != null) {
			builder.append("old_Signature=");
			builder.append(old_Signature);
			builder.append(", ");
		}
		if (new_Signature != null) {
			builder.append("new_Signature=");
			builder.append(new_Signature);
			builder.append(", ");
		}
		if (dTOs_changed != null) {
			builder.append("dTOs_changed=");
			builder.append(dTOs_changed);
		}
		builder.append("]");
		return builder.toString();
	}
}
