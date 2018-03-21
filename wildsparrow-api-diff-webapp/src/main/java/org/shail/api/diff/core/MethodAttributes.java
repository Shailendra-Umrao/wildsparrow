package org.shail.api.diff.core;

import java.util.SortedSet;

public class MethodAttributes {
	
	private String fullyQualifiedName;
	private String name;
	private String returnType;
	private int parameterCount;
	private SortedSet<String> parameters ;
	

	public MethodAttributes(String fullyQualifiedName, String name, String returnType, int parameterCount,
			SortedSet<String> parameters) {
		super();
		this.fullyQualifiedName = fullyQualifiedName;
		this.name = name;
		this.returnType = returnType;
		this.parameterCount = parameterCount;
		this.parameters = parameters;
	}
	public String getFullyQualifiedName() {
		return fullyQualifiedName;
	}
	public void setFullyQualifiedName(String fullyQualifiedName) {
		this.fullyQualifiedName = fullyQualifiedName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public int getParameterCount() {
		return parameterCount;
	}
	public void setParameterCount(int parameterCount) {
		this.parameterCount = parameterCount;
	}
	public SortedSet<String> getParameters() {
		return parameters;
	}
	public void setParameters(SortedSet<String> parameters) {
		this.parameters = parameters;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullyQualifiedName == null) ? 0 : fullyQualifiedName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + parameterCount;
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result + ((returnType == null) ? 0 : returnType.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MethodAttributes)) {
			return false;
		}
		MethodAttributes other = (MethodAttributes) obj;
		if (fullyQualifiedName == null) {
			if (other.fullyQualifiedName != null) {
				return false;
			}
		} else if (!fullyQualifiedName.equals(other.fullyQualifiedName)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (parameterCount != other.parameterCount) {
			return false;
		}
		if (parameters == null) {
			if (other.parameters != null) {
				return false;
			}
		} else if (!parameters.equals(other.parameters)) {
			return false;
		}
		if (returnType == null) {
			if (other.returnType != null) {
				return false;
			}
		} else if (!returnType.equals(other.returnType)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MethodAttributes [");
		if (fullyQualifiedName != null) {
			builder.append("fullyQualifiedName=");
			builder.append(fullyQualifiedName);
			builder.append(", ");
		}
		if (name != null) {
			builder.append("name=");
			builder.append(name);
			builder.append(", ");
		}
		if (returnType != null) {
			builder.append("returnType=");
			builder.append(returnType);
			builder.append(", ");
		}
		builder.append("parameterCount=");
		builder.append(parameterCount);
		builder.append(", ");
		if (parameters != null) {
			builder.append("parameters=");
			builder.append(parameters);
		}
		builder.append("]");
		return builder.toString();
	}
	
	

}
