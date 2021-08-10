<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css"
	rel="stylesheet" type="text/css">


<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<!-- 해더 네비 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //해더 네비 -->


		<div id="container" class="clearfix">
			<!-- 게시판 aside -->
			<c:import url="/WEB-INF/views/includes/asideGallery.jsp"></c:import>
			<!-- //게시판 aside -->

			<div id="content">

				<div id="content-head">
					<h3>갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="gallery">
					<div id="list">

						<c:if test="${authUser != null }">
							<button id="btnImgUpload">이미지올리기</button>
							<div class="clear"></div>
						</c:if>

						<ul id="viewArea">

							<!-- 이미지반복영역 -->
							<c:forEach items="${gList }" var="gList">
								<li>
									<div class="view" id="deleteImg-${gList.no }">
										<img data-no="${gList.no }" class="imgItem"
											src="${pageContext.request.contextPath }/upload/${gList.saveName}">
										<div class="imgWriter">
											작성자: <strong>${gList.name }</strong>
										</div>
									</div>

								</li>
							</c:forEach>
						</ul>
					</div>
					<!-- //list -->
				</div>
				<!-- //gallery -->


			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->


		<!-- 푸터 -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //푸터 -->
	</div>
	<!-- //wrap -->









	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				<!-- enc타입은 이미지 서버로 전송할때 multipart/form-data을 씀  -->
				<form method="post"
					action="${pageContext.request.contextPath }/gallery/upload"
					enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label> <input id="addModalContent"
								type="text" name="content" value="">
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label> <input id="file"
								type="file" name="file" value="">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>


			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">

					<div class="formgroup">
						<img id="viewModelImg" src="">
						<!-- ajax로 처리 : 이미지출력 위치-->
					</div>

					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>

				</div>
				<form method="" action="">
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
						<input type="text" id="delNo" value="">
					</div>


				</form>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>
<script type="text/javascript">




//이미지 등록
$("#btnImgUpload").on("click",function(){
	console.log("모달창 클릭")
	
	
	$("#addModal").modal();
	$("#addModalContent").val("");
	$("#file").val("");
});

//이미지 보기
$(".imgItem").on("click",function(){
	console.log("이미지 클릭")
	//var tag = $(this);
	//var no = tag.date("no");
	
	var no =$(this).data("no");
	console.log(no);
	
	$("#delNo").val(no);
	
	$("#viewModal").modal();
	


	//no전송해서 이미지 보기 ajax로
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/gallery/oneView",
		type : "post",
		//contentType : "application/json",
		data : "no=" + no,

		dataType : "json",
		success : function(galleryVo){
			/*성공시 처리해야될 코드 작성*/
			console.log("galleryVo")
			console.log(galleryVo)
			
			$("#viewModelImg").attr("src","${pageContext.request.contextPath }/upload/"+galleryVo.saveName);
			$("#viewModelContent").text(galleryVo.content);
			
			if("${sessionScope.authUser.no}" == galleryVo.user_No){
				$("#btnDel").show();
			}else {
				$("#btnDel").hide();
			}
			
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
		
		
	});
});
	//삭제
	
	
	$("#btnDel").on("click",function(){
	console.log("이미지 삭제클릭")
	//var tag = $(this);
	//var no = tag.date("no");
	
	var no =$("#delNo").val();
	console.log(no);
	
	//$("#viewModal").modal();
	
$.ajax({
		
		url : "${pageContext.request.contextPath }/api/gallery/deleteImg",
		type : "post",
		//contentType : "application/json",
		data : {no: no},

		dataType : "json",
		success : function(count){
			/*성공시 처리해야될 코드 작성*/
		if(count == 1 ){
			$("#deleteImg-"+no).remove();
			$("#viewModal").modal("hide");
		}else{
			$("#viewModal").modal("hide");
		}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
		
		
	});

	
});








</script>
</html>

