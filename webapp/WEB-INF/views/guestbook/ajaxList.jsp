<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js" ></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js" ></script>

</head>

<body>
	<div id="wrap">

		<!-- 헤더 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- 헤더 네비 -->

		
	
		<div id="container" class="clearfix">
		<c:import url="/WEB-INF/views/includes/asideGuestbook.jsp"></c:import>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></td>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></td>
									<td><input id="input-pass"type="password" name="pass"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnSubmit" type="submit">등록</button></td>
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">
						
					</form>	
				
					
					<div id="listArea">
					<!-- jquery로 리스트 그리기 -->
					</div>	
					<!-- //guestRead -->
					
				</div>
				<!-- //guestbook -->
			
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

	<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->
	
	<!-- -------------------------------------------------------------------- -->
	<!-- 삭제 모달창 -->
<div id="delModal" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">방명록 삭제</h4>
      </div>
      <div class="modal-body">
        
        <label for="modalPassword">비밀번호</label>
        <input id="modalPassword" type="password" name="password">
        
        <input type="text" name ="no" value="">
      </div>
      <div class="modal-footer">
        <button id="modalBtnDel" type="button" class="btn btn-primary">삭제</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- -------------------------------------------------------------------- -->






</body>

<script type="text/javascript">
//화면 로딩되기 직전
$(document).ready(function(){
   console.log("화면 로딩 직전"); 
   
   //ajax요청하는법
   $.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/list",		
		type : "post",
		//contentType : "application/json",
		//data : {name: ”홍길동"},

		//dataType : "json",
		success : function(guestList){
			/*성공시 처리해야될 코드 작성*/
			console.log(guestList);
			
			//화면에 그리기
			for(var i=0; i<guestList.length; i++){
				render(guestList[i], "down");// 방명록 글 1개씩 추가
			}
			
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

   
   
});

//로딩이 끝난 후 
$("#btnSubmit").on("click",function(){
	event.preventDefault();
	console.log("버튼클릭");
	
	/*
	//name값 읽어오기
	var userName = $("#input-uname").val();
	console.log(userName);
	//password값 읽어오기
	var password = $("#input-pass").val();
	console.log(password);
	//content 값 읽어오기
	var content = $("[name='content']").val();
	console.log(content);
	*/
	
	//이렇게해도된다
	var guestbookVo={
		name: $("#input-uname").val(),
		password: $("#input-pass").val(),
		content: $("[name='content']").val()
			
	};
	
	
	//데이터 ajax방식으로 서버에서 전송
$.ajax({
		
		//url : "${pageContext.request.contextPath }/api/guestbook/write?name="+userName+"&password="+password+ "&
		url : "${pageContext.request.contextPath }/api/guestbook/write",
		type : "get",
		//contentType : "application/json",
		data : guestbookVo,

		//dataType : "json",
		success : function(guestbookVo){
			/*성공시 처리해야될 코드 작성*/
			console.log(guestbookVo);
			render(guestbookVo,"up");
			
            //입력폼 초기화
            $("#input-uname").val("");
            $("#input-pass").val("");
            $("[name='content']").val("");
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
	
	
});

//리스트에서 삭제 버튼을 클릭할때
$("#listArea").on("click",".btnDel",function(){
	console.log("버튼클릭");
	
	//hidden no 입력하기
	var no = $(this).data("no");
	$("[name=no]").val(no);
	
	
	//비밀번호 창 초기화
	$("#modalPassword").val("");
	//hidden no 입력하기
	
	
	
	//모달창 보이기 
	$("#delModal").modal();
});


	//삭제모달창의 삭제버튼 클릭할때
	$("#modalBtnDel").on("click",function(){
		console.log("모달창 삭제 버튼 클릭");
		
		var no = $("[name='no']").val();
		
		var guestbookVo = {
			no: $("[name=no]").val(),
			password: $("[name=password]").val()
		};
		
		console.log(guestbookVo)
		
		
		//서버에 삭제 요청 (no,password 전달)
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/guestbook/remove",		
			type : "post",
			//contentType : "application/json",
			data : guestbookVo,

			dataType : "json",
			success : function(count){
				/*성공시 처리해야될 코드 작성*/
				
				
			if(count === 1){
				//모달창 닫기
				$("#delModal").modal("hide");
				
				
				//리스트에 삭제 버튼이 있던 테이블 화면에서 지운다.
				$("#t-" + no).remove();
			}else{
				
				//모달창 닫기
				$("#delModal").modal("hide");
			}	
				
				
				
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

		
	});


//방명록 1개씩 랜더링 
function render(guestbookVo, type){
	var Str = "";
	
	Str +=  '<table id="t-'+guestbookVo.no+'"class="guestRead">';
	Str += '<colgroup>';
	Str += '<col style="width: 10%;">';
	Str += '<col style="width: 40%;">';
	Str += '<col style="width: 40%;">';
	Str += '	<col style="width: 10%;">';
	Str += '</colgroup>';
	Str += '<tr>';
	Str += '<td>'+guestbookVo.no+'</td>';
	Str += '<td>'+guestbookVo.name+'</td>';
	Str += '<td>'+guestbookVo.reg_date +'</td>';
	Str += '<td><button class="btnDel" data-no="'+  guestbookVo.no   +'">삭제</button></td>';
	Str += '</tr>';
	Str += '<tr>';
	Str += '<td colspan=4 class="text-left">'+guestbookVo.content+'</td>';
	Str += '</tr>';
	Str += '</table>';
	
	if(type === 'down'){
		$("#listArea").append(Str);
	}else if((type === 'up')){
		$("#listArea").prepend(Str);
	}else{
		console.log("방향을 지정해 주세요");
	}
	

}
</script>


</html>