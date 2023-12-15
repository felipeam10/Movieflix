package com.felipe.movieflix.dto;

public class ReviewWithUserNameDTO {

    private Long id;
    private String text;
    private String userName;

	public ReviewWithUserNameDTO() {
	}

	public ReviewWithUserNameDTO(Long id, String text, String userName) {
		this.id = id;
		this.text = text;
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
