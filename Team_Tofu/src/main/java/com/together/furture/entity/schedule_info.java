package com.together.furture.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.SimpleDateFormat;
import java.util.Date;

public class schedule_info {
	private int sche_idx;
	private String sche_title;
	private String sche_content;
	private String st_dt; // Timestamp 대신 String으로 변경
	private String ed_dt; // Timestamp 대신 String으로 변경
	private String user_id;
	private String sche_status;
	private String sche_color;
	private int cw_idx;

	public schedule_info() {
	}

	public schedule_info(int sche_idx, String sche_title, String sche_content, String st_dt, String ed_dt,
			String user_id, String sche_status, String sche_color, int cw_idx) {
		this.sche_idx = sche_idx;
		this.sche_title = sche_title;
		this.sche_content = sche_content;
		this.st_dt = st_dt;
		this.ed_dt = ed_dt;
		this.user_id = user_id;
		this.sche_status = sche_status;
		this.sche_color = sche_color;
		this.cw_idx = cw_idx;
	}

	// Getters
	@JsonProperty("sche_idx")
	public int get_sche_idx() {
		return sche_idx;
	}

	@JsonProperty("sche_title")
	public String get_sche_title() {
		return sche_title;
	}

	@JsonProperty("sche_content")
	public String get_sche_content() {
		return sche_content;
	}

	@JsonProperty("st_dt")
	public String get_st_dt() {
		return st_dt;
	}

	@JsonProperty("ed_dt")
	public String get_ed_dt() {
		return ed_dt;
	}

	@JsonProperty("user_id")
	public String get_user_id() {
		return user_id;
	}

	@JsonProperty("sche_status")
	public String get_sche_status() {
		return sche_status;
	}

	@JsonProperty("sche_color")
	public String get_sche_color() {
		return sche_color;
	}

	@JsonProperty("cw_idx")
	public int get_cw_idx() {
		return cw_idx;
	}

	// Setters
	@JsonProperty("sche_idx")
	public void set_sche_idx(int sche_idx) {
		this.sche_idx = sche_idx;
	}

	@JsonProperty("sche_title")
	public void set_sche_title(String sche_title) {
		this.sche_title = sche_title;
	}

	@JsonProperty("sche_content")
	public void set_sche_content(String sche_content) {
		this.sche_content = sche_content;
	}

	@JsonProperty("st_dt")
	public void set_st_dt(String st_dt) {
		this.st_dt = st_dt;
	}

	@JsonProperty("ed_dt")
	public void set_ed_dt(String ed_dt) {
		this.ed_dt = ed_dt;
	}

	@JsonProperty("user_id")
	public void set_user_id(String user_id) {
		this.user_id = user_id;
	}

	@JsonProperty("sche_status")
	public void set_sche_status(String sche_status) {
		this.sche_status = sche_status;
	}

	@JsonProperty("sche_color")
	public void set_sche_color(String sche_color) {
		this.sche_color = sche_color;
	}

	@JsonProperty("cw_idx")
	public void set_cw_idx(int cw_idx) {
		this.cw_idx = cw_idx;
	}

	// Timestamp로 변환하는 메서드
	public java.sql.Timestamp getStDtAsTimestamp() throws Exception {
		if (st_dt == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(st_dt);
		return new java.sql.Timestamp(date.getTime());
	}

	public java.sql.Timestamp getEdDtAsTimestamp() throws Exception {
		if (ed_dt == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(ed_dt);
		return new java.sql.Timestamp(date.getTime());
	}
}