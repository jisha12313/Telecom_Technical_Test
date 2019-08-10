package com.exercise.telecom.dto;

public class ResponseStatusDto
  implements
  java.io.Serializable {

  private static final long serialVersionUID = 1112162443891472213L;
  private String code;
  private String reasonCode;
  private String message;

  public ResponseStatusDto() {

    super();
  }

  public ResponseStatusDto( final String code, final String reasonCode, final String message ) {
    super();
    this.code = code;
    this.reasonCode = reasonCode;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode( final String code ) {
    this.code = code;
  }

  public String getReasonCode() {
    return reasonCode;
  }

  public void setReasonCode( final String reasonCode ) {
    this.reasonCode = reasonCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage( final String message ) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "ResponseStatusDto [code=" + code + ", reasonCode=" + reasonCode + ", message=" + message + "]";
  }


}
