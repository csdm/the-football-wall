package com.claudiodimauro.thefootballwall.api.beans.responses.generic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.claudiodimauro.thefootballwall.utils.enums.ElaborationStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenericResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private T result;
	private Date timestamp;
	private String path; //es "http://<endpoint>/<api>"
	private String method; //es GET
	private HttpStatus httpStatus;
	private String resultDescription;
	private ElaborationStatus elaborationStatus;
	
	public GenericResponse(T result, String resultDescription) {
		super();
		this.result = result;
		this.resultDescription = resultDescription;
	}
	
	public GenericResponse(T result, String resultDescription, ElaborationStatus elaborationStatus) {
		super();
		this.result = result;
		this.resultDescription = resultDescription;
		this.elaborationStatus = elaborationStatus;
	}
}