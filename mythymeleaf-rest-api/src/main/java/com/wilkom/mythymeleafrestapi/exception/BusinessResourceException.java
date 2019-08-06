package com.wilkom.mythymeleafrestapi.exception;

import org.springframework.http.HttpStatus;

/**
 * Gestion des erreurs au niveau des services CRUD
 * @author Koffi
 *
 */
public class BusinessResourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Long resourceId;
	private String errorCode;
	private HttpStatus status;

    public BusinessResourceException(String message) {
        super(message);
    }
    
    public BusinessResourceException(Long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }
    public BusinessResourceException(Long resourceId, String errorCode, String message) {
    	super(message);
        this.resourceId = resourceId;
        this.errorCode = errorCode;
    }
    
    public BusinessResourceException(String errorCode, String message) {
    	super(message);
        this.errorCode = errorCode;
    }
    
    public BusinessResourceException(String errorCode, String message, HttpStatus status) {
    	super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}    
	
    public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}