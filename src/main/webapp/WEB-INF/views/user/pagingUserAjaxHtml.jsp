<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:forEach items="${userList}" var="user" varStatus="loop">
	<tr class="user" data-userid="${user.userid }">
		<td>Ajax loopIndex ${loop.index} ${user.userid }</td>
		<td>${user.usernm }</td>
		<td>${user.pass }</td>
		<td><fmt:formatDate value="${user.reg_dt }" pattern="yyyy.MM.dd" /></td>
		<td>${user.alias }</td>
	</tr>
</c:forEach>

####################

<li class="prev"><a
	<%-- href="${cp }/user/pagingUserTiles?page=1&pageSize=${pageVo.pageSize }">«</a> --%>
	href="javascript:pagingUserAjax(1, ${pageVo.pageSize });">«</a>
</li>

<c:forEach begin="1" end="${pagination }" var="i">
	<c:choose>
		<c:when test="${pageVo.page == i }">


			<li class="active"><span>${i }</span></li>



		</c:when>
		<c:otherwise>
			<li><a
				href="javascript:pagingUserAjax(${i }, ${pageVo.pageSize });">${i }
			</a></li>
		</c:otherwise>
	</c:choose>
</c:forEach>

<li class="next"><a
	href="javascript:pagingUserAjax(${pagination }, ${pageVo.pageSize });">»</a>
</li>