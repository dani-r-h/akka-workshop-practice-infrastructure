package com.autentia.workshop.akka.practice.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Request implements Serializable {

	private final String customerId;
	private final String requestId;
	private final TortillaType tortillaType;

	public Request(String customerId, String requestId, TortillaType tortillaType) {
		super();
		this.customerId = customerId;
		this.requestId = requestId;
		this.tortillaType = tortillaType;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getRequestId() {
		return requestId;
	}

	public TortillaType getTortillaType() {
		return tortillaType;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(customerId).append(requestId).append(tortillaType).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Request)){
			return false;
		}
		
		final Request anotherObj = (Request) obj;
		return new EqualsBuilder().append(customerId, anotherObj.customerId).append(requestId, anotherObj.requestId).append(tortillaType, anotherObj.tortillaType).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
