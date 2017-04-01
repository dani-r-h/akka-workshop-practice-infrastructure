package com.autentia.workshop.akka.practice.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Event implements Serializable {

	private final String id;
	private final Request request;

	public Event(String id, Request request) {
		super();
		this.id = id;
		this.request = request;
	}

	public String getId() {
		return id;
	}

	public Request getRequest() {
		return request;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(request).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Event)) {
			return false;
		}

		final Event anotherObj = (Event) obj;
		return new EqualsBuilder().append(id, anotherObj.id).append(request, anotherObj.request).isEquals();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	

}
