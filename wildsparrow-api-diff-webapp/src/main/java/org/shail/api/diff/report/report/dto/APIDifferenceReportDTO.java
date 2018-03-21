package org.shail.api.diff.report.report.dto;

import java.io.Serializable;
import java.util.List;

public class APIDifferenceReportDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3149053045546920550L;
	private List<APIDifferenceReportDetailsDTO> addedMethodReportDTOs;
	private List<APIDifferenceReportDetailsDTO> removedMethodReportDTOs;
	private List<APIDifferenceReportDetailsDTO> modifiedSignatureChange;
	private List<APIDifferenceReportDetailsDTO> modifiedInputDTOFieldsAdded;
	private List<APIDifferenceReportDetailsDTO> modifiedInputDTOFieldRemoved;
	private List<APIDifferenceReportDetailsDTO> ambigiousMethodReportDTOs;

	public List<APIDifferenceReportDetailsDTO> getAddedMethodReportDTOs() {
		return addedMethodReportDTOs;
	}

	public void setAddedMethodReportDTOs(List<APIDifferenceReportDetailsDTO> addedMethodReportDTOs) {
		this.addedMethodReportDTOs = addedMethodReportDTOs;
	}

	public List<APIDifferenceReportDetailsDTO> getRemovedMethodReportDTOs() {
		return removedMethodReportDTOs;
	}

	public void setRemovedMethodReportDTOs(List<APIDifferenceReportDetailsDTO> removedMethodReportDTOs) {
		this.removedMethodReportDTOs = removedMethodReportDTOs;
	}

	public List<APIDifferenceReportDetailsDTO> getModifiedSignatureChange() {
		return modifiedSignatureChange;
	}

	public void setModifiedSignatureChange(List<APIDifferenceReportDetailsDTO> modifiedSignatureChange) {
		this.modifiedSignatureChange = modifiedSignatureChange;
	}

	public List<APIDifferenceReportDetailsDTO> getModifiedInputDTOFieldsAdded() {
		return modifiedInputDTOFieldsAdded;
	}

	public void setModifiedInputDTOFieldsAdded(List<APIDifferenceReportDetailsDTO> modifiedInputDTOFieldsAdded) {
		this.modifiedInputDTOFieldsAdded = modifiedInputDTOFieldsAdded;
	}

	public List<APIDifferenceReportDetailsDTO> getModifiedInputDTOFieldRemoved() {
		return modifiedInputDTOFieldRemoved;
	}

	public void setModifiedInputDTOFieldRemoved(List<APIDifferenceReportDetailsDTO> modifiedInputDTOFieldRemoved) {
		this.modifiedInputDTOFieldRemoved = modifiedInputDTOFieldRemoved;
	}

	public List<APIDifferenceReportDetailsDTO> getAmbigiousMethodReportDTOs() {
		return ambigiousMethodReportDTOs;
	}

	public void setAmbigiousMethodReportDTOs(List<APIDifferenceReportDetailsDTO> ambigiousMethodReportDTOs) {
		this.ambigiousMethodReportDTOs = ambigiousMethodReportDTOs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("APIDifferenceReportDTO [");
		if (addedMethodReportDTOs != null) {
			builder.append("addedMethodReportDTOs=");
			builder.append(addedMethodReportDTOs);
			builder.append(", ");
		}
		if (removedMethodReportDTOs != null) {
			builder.append("removedMethodReportDTOs=");
			builder.append(removedMethodReportDTOs);
			builder.append(", ");
		}
		if (modifiedSignatureChange != null) {
			builder.append("modifiedSignatureChange=");
			builder.append(modifiedSignatureChange);
			builder.append(", ");
		}
		if (modifiedInputDTOFieldsAdded != null) {
			builder.append("modifiedInputDTOFieldsAdded=");
			builder.append(modifiedInputDTOFieldsAdded);
			builder.append(", ");
		}
		if (modifiedInputDTOFieldRemoved != null) {
			builder.append("modifiedInputDTOFieldRemoved=");
			builder.append(modifiedInputDTOFieldRemoved);
			builder.append(", ");
		}
		if (ambigiousMethodReportDTOs != null) {
			builder.append("ambigiousMethodReportDTOs=");
			builder.append(ambigiousMethodReportDTOs);
		}
		builder.append("]");
		return builder.toString();
	}

}
