<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<article>
	<div id="memberJoinForm">
		<h3 id="joinFormTitle">회원 정보 수정</h3>

			<form action="${contextPath}/memImageUpdate.do" method="post">
					<input type="hidden" name="userId" value="${sessionScope.member.userId}" />
					<table>
						<tr>
							<td>${userId}</td>
						</tr>
						<tr>
							<td>사진 업로드</td>
							<span class="btn btn-default"> 이미지를 업로드 하세요 <input
								type="file" name="Image">;
								
							</span>
						</tr>
 
					</table>	<div class="memberInputText">
					<a href="${contextPath }/ImageForm.do">
					<span class="memberSpan">*사진 업로드</span> </a>
				</div>

				</form>

		<form action="memberUpdateResult" name="memberUpdateForm"
			id="memberUpdateForm" method="post">
			<input type="hidden" name="isIdCheck" value="true" />
			<div id="memberInputDefault">
				<div class="memberInputText">
					<span class="memberSpan">* 이&nbsp;&nbsp;름 : </span> <input
						type="text" name="name" size="15" id="name"
						value="${ sessionScope.member.name }" />
				</div>

	

			

				<div class="memberInputText">
					<span class="memberSpan">* 아이디 : </span> <input type="text"
						name="userId" size="15" id="userId" readonly
						value="${ sessionScope.member.userId }" />
				</div>


				<div class="memberInputText">
					<span class="memberSpan">* 기존 비밀번호 : </span> <input type="password"
						name="oldPass" id="oldPass" size="15" /> <input type="button"
						value="비밀번호 확인" id="btnPassCheck" />
				</div>
				


				<div class="memberInputText">
					<span class="memberSpan">* 새 비밀번호 : </span> <input type="password"
						name="pass1" id="pass1" size="15" />
				</div>


				<div class="memberInputText">
					<span class="memberSpan">* 새 비밀번호 확인 : </span> <input
						type="password" name="pass2" id="pass2" size="15" />
				</div>


				<div class="memberInputText">
					<span class="memberSpan">* 우편번호 : </span> <input type="text"
						name="zipcode" size="6" maxlength="5" readonly id="zipcode"
						value="${ sessionScope.member.zipcode }" />&nbsp; <input
						type="button" value="우편번호 검색" id="btnZipcode" />
				</div>


				<div class="memberInputText">
					<span class="memberSpan">* 자택주소 : </span> <input type="text"
						name="address1" size="50" readonly id="address1"
						value="${ sessionScope.member.address1 }" />
				</div>
				<div class="memberInputText">
					<span class="memberSpan">상세주소 : </span> <input type="text"
						name="address2" size="50" id="address2"
						value="${ sessionScope.member.address2 }" />
				</div>


				<div class="memberInputText">
					<span class="memberSpan">* 이 메 일 : </span> <input type="text"
						name="emailId" size="10" id="emailId"
						value="${ sessionScope.member.email.split('@')[0] }" /> @ <input
						type="text" name="emailDomain" size="10" id="emailDomain"
						value="${ sessionScope.member.email.split('@')[1] }" /> <select
						name="selectDomain" id="selectDomain">
						<option>직접입력</option>
						<option>네이버</option>
						<option>다음</option>
						<option>한메일</option>
						<option>구글</option>
					</select>
				</div>
			</div>


			<div>
				<div class="memberInputText">
					<span class="memberSpan"> 휴대폰 : </span> <select name="phone1">
						<option ${ member.phone.split('-')[0] == 010 ? "selected" : "" }>010</option>
						<option ${ member.phone.split('-')[0] == 02 ? "selected" : "" }>02</option>
					</select>- <input type="text" name="phone2" size="3" maxlength="4"
						value="${ member.phone.split('-')[1] }" />- <input type="text"
						name="phone3" size="3" maxlength="4"
						value="${ member.phone.split('-')[2] }" />
				</div>

			</div>
			<div class="formButton">
				<input type="reset" value="다시쓰기" /> <input type="submit"
					value="수정하기" />
			</div>
		</form>
	</div>
</article>



