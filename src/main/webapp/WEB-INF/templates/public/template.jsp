<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/taglib.jsp" %>
	<!-- Header -->
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<!-- End Header -->
	<!-- Menu -->
	<tiles:insertAttribute name="menubar"></tiles:insertAttribute>
		<!--End Menu -->
		<!-- Body -->
		<tiles:insertAttribute name="body"></tiles:insertAttribute>
		<!--End Body -->
	<!-- Footer -->
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	<!--End Footer -->