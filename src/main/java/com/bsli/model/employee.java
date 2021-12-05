package com.bsli.model;

import java.sql.Date;

public class Employee {
	
	String Series_reference;
	Double Period;
	Integer Data_value;
	String Suppressed;
	String STATUS;
	String UNITS;
	String Magnitude;
	String Subject;
	String Group;
	String Series_title_1;
	String Series_title_2;
	String Series_title_3;
	public Employee() {
		super();
	}
	
	public Employee(String series_reference, Double period, Integer data_value, String suppressed, String sTATUS,
			String uNITS, String magnitude, String subject, String group, String series_title_1, String series_title_2,
			String series_title_3) {
		super();
		Series_reference = series_reference;
		Period = period;
		Data_value = data_value;
		Suppressed = suppressed;
		STATUS = sTATUS;
		UNITS = uNITS;
		Magnitude = magnitude;
		Subject = subject;
		Group = group;
		Series_title_1 = series_title_1;
		Series_title_2 = series_title_2;
		Series_title_3 = series_title_3;
	}
	
	public String getSeries_reference() {
		return Series_reference;
	}
	public void setSeries_reference(String series_reference) {
		Series_reference = series_reference;
	}
	public Double getPeriod() {
		return Period;
	}
	public void setPeriod(Double period) {
		Period = period;
	}
	public Integer getData_value() {
		return Data_value;
	}
	public void setData_value(Integer data_value) {
		Data_value = data_value;
	}
	public String getSuppressed() {
		return Suppressed;
	}
	public void setSuppressed(String suppressed) {
		Suppressed = suppressed;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getUNITS() {
		return UNITS;
	}
	public void setUNITS(String uNITS) {
		UNITS = uNITS;
	}
	public String getMagnitude() {
		return Magnitude;
	}
	public void setMagnitude(String magnitude) {
		Magnitude = magnitude;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getGroup() {
		return Group;
	}
	public void setGroup(String group) {
		Group = group;
	}
	public String getSeries_title_1() {
		return Series_title_1;
	}
	public void setSeries_title_1(String series_title_1) {
		Series_title_1 = series_title_1;
	}
	public String getSeries_title_2() {
		return Series_title_2;
	}
	public void setSeries_title_2(String series_title_2) {
		Series_title_2 = series_title_2;
	}
	public String getSeries_title_3() {
		return Series_title_3;
	}
	public void setSeries_title_3(String series_title_3) {
		Series_title_3 = series_title_3;
	}
	@Override
	public String toString() {
		return "Employee [Series_reference=" + Series_reference + ", Period=" + Period + ", Data_value=" + Data_value
				+ ", Suppressed=" + Suppressed + ", STATUS=" + STATUS + ", UNITS=" + UNITS + ", Magnitude=" + Magnitude
				+ ", Subject=" + Subject + ", Group=" + Group + ", Series_title_1=" + Series_title_1
				+ ", Series_title_2=" + Series_title_2 + ", Series_title_3=" + Series_title_3 + "]";
	}
	


}


