<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 주소 입력 부분 다음 API 활용 -->
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	$(function() {

		//주소 검색 버튼이 클릭 되었을 때 다음주소API 팝업을 연다			
		$("#addrBtn").on("click", function() {
			new daum.Postcode({
				oncomplete : function(data) {
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
					// 예제를 참고하여 다양한 활용법을 확인해 보세요.
					$("#addr1").val(data.roadAddress); // 도로명 주소
					$("#zipcode").val(data.zonecode); // 우편주소

					//사용자 편의성을 위해 상세주소 입력 input 태그로 focus 설정
					$("#addr2").focus();
				}
			}).open();
		});
	});
</script>

<script type="text/javascript">
	function initData() {
		$("#userId").val("1234");
		$("#userNm").val("테스터");
		$("#userAlias").val("tester");
		$("#pass").val("1234");
		$("#reg_dt").val("2021.01.01");
		$("#addr1").val("테스트주소1");
		$("#addr2").val("테스트주소2");
		$("#zipcode").val("123456");
	}
</script>

<form class="form-horizontal" role="form"
	action="${cp }/user/userRegist" method="POST"
	enctype="multipart/form-data">
	<input type="hidden" name="userId" value="" />
	<h1>사용자 등록 BODY</h1>
	spring message 설정<br>
	<spring:message code="GREETING" arguments="${S_USER.userid}" />

	<hr>

	<div class="form-group">
		<label for="userId" class="col-sm-2 control-label"><spring:message
				code="USERID" /> </label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="userid" name="userid"
				placeholder="사용자 아이디" value="${param.userid}">

			<!-- 사용자 이름 글자수 제한 관련 msg-->
			<span style="color: red;"> <form:errors path="userVo.userid" />
			</span> <input type="file" class="form-control" name="profile">
		</div>

	</div>

	<div class="form-group">
		<label for="userNm" class="col-sm-2 control-label"><spring:message
				code="USERNM" /></label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="usernm" name="usernm"
				placeholder="사용자 이름" value="${param.usernm}">
		</div>
	</div>

	<div class="form-group">
		<label for="pass" class="col-sm-2 control-label"><spring:message
				code="PASS" /></label>
		<div class="col-sm-10">
			<input type="password" class="form-control" id="pass" name="pass"
				placeholder="Password" value="${param.pass}">
		</div>
	</div>

	<div class="form-group">
		<label for="reg_dt" class="col-sm-2 control-label"><spring:message
				code="REG_DT" /></label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="reg_dt2" name="reg_dt2"
				placeholder="사용자 등록일" value="${param.reg_dt}" />
		</div>
	</div>

	<div class="form-group">
		<label for="userNm" class="col-sm-2 control-label"><spring:message
				code="ALIAS" /></label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="alias" name="alias"
				placeholder="별명" value="${param.alias}" />
		</div>
	</div>

	<div class="form-group">
		<label for="addr1" class="col-sm-2 control-label"><spring:message
				code="ADDR1" /></label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="addr1" name="addr1"
				placeholder="주소 검색 버튼 클릭" value="${param.addr1}" readonly />
		</div>
		<div class="col-sm-2">
			<button type="button" id="addrBtn" class="btn btn-default">주소
				검색</button>
		</div>
	</div>

	<div class="form-group">
		<label for="addr2" class="col-sm-2 control-label"><spring:message
				code="ADDR2" /></label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="addr2" name="addr2"
				placeholder="상세주소" value="${param.addr2}" />
		</div>
	</div>

	<div class="form-group">
		<label for="zipcode" class="col-sm-2 control-label"><spring:message
				code="ZIPCODE" /></label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="zipcode" name="zipcode"
				placeholder="우편번호" value="${param.zipcode}" readonly />
		</div>
	</div>


	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">등록 완료</button>
		</div>
	</div>
</form>



<select name="lang">
	<option value="">언어설정</option>
	<option value="ko">한국어</option>
	<option value="en">English</option>
</select>

<script>
	$(function() {
		$("select[name=lang]").on("change", function() {
			console.log("select[lang] on change")
			document.location = "/user/userRegistTiles?lang=" + $(this).val();
		});
	});
</script>

