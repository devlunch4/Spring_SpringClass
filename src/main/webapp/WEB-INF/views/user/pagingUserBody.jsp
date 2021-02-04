<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">
	//문서 로딩이 완료되고 나서 실행되는 영역
	$(function() {
		$(".user").on("click", function() {
			//this : 클릭 이벤트가 발생한 element
			//data-속성명 >> data-userid >> $(this).data("userid"),
			//속성명은 대소문자를 무시하고 소문자로 인식
			//console.log($(this).data("userid"))
			var userid = $(this).data("userid");
			$("#userid").val(userid);
			$("#frm").submit();
		});
	});
</script>

<form id="frm" action="/user/userFormTiles">
	<input type="hidden" id="userid" name="userid" value="">
</form>
<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">전체사용자페이징리스트 BODY</h2>
		<div class="table-responsive">
			<table class="table table-striped">
				<tr>
					<th>사용자 아이디</th>
					<th>사용자 이름</th>
					<th>사용자 비밀번호</th>
					<th>등록일시</th>
					<th>사용자 별명</th>
				</tr>

				<c:forEach items="${userList}" var="user" varStatus="loop">
					<tr class="user" data-userid="${user.userid }">
						<td>loopIndex ${loop.index} ${user.userid }</td>
						<td>${user.usernm }</td>
						<td>${user.pass }</td>
						<td><fmt:formatDate value="${user.reg_dt }"
								pattern="yyyy.MM.dd" /></td>
						<td>${user.alias }</td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<a class="btn btn-default pull-right" href="${cp }/user/userRegistTiles">사용자
			등록</a> <a class="btn btn-default pull-right"
			href="${cp }/user/excelDownload">전체 사용자 엑셀 다운로드</a>
		<div class="text-center">


			<ul class="pagination">
				<%--pagination 값이 4이므로 1부터 4까지 4번 반복된다
							전체 사용자수 16명
							페이지사이즈수 5
							전체 페이지수 4 --%>
				<li class="prev"><a
					href="${cp }/user/pagingUserTiles?page=1&pageSize=${pageVo.pageSize }">«</a>
				</li>

				<c:forEach begin="1" end="${pagination }" var="i">
					<c:choose>
						<c:when test="${pageVo.page == i }">


							<li class="active"><span>${i }</span></li>



						</c:when>
						<c:otherwise>
							<li><a
								href="${cp }/user/pagingUserTiles?page=${i }&pageSize=${pageVo.pageSize }">${i }
							</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<li class="next"><a
					href="${cp }/user/pagingUserTiles?page=${pagination }&pageSize=${pageVo.pageSize }">»</a>
				</li>
			</ul>
		</div>
	</div>
</div>

