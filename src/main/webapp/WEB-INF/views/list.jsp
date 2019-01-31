<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>TodoApp</title>
<style>
	.outer {
		display: table;
		width: 100%;
		height: 100%;
	}
	.inner {
		display: table-cell;
		vertical-align: middle;
		text-align: center;
	}
	.centered {
		position: relative;
		display: inline-block;

		width: 70%;
		padding: 1em;
		color: white;
	}
</style>

<script>
	$(document).ready(function(){
		$('[data-toggle="tooltip"]').tooltip();
	});

	var COMPLETED = 'C';
	var ING = 'I';

	$(function(){

		$('#addBtn').click(function(){
			addTodo();
		});

		$("#addForm #whatTodo").keypress(function (e) {
			if (e.which == 13){
				addTodo();
			}
		});

		$('#modBtn').click(function(){
			modTodo('');
		});

		$("#modForm #whatTodo").keypress(function (e) {
			if (e.which == 13){
				return;
			}
		});

		// Set Default List
		goPage(1);
	});

	// ## 리스트 조회
	// Todo Base API URL
	var todoBaseUrl = '/api/todos';

	function goPage(pageNo){
		var frm = $('#pageForm');
		var reqUrl = todoBaseUrl + "/" + pageNo;

		$('#pageForm #pageNo').val(pageNo);

		//alert("todoListUrl :"+todoListUrl);
		$.ajax({
			url      : reqUrl,
			type     : 'get',
			data	 :	frm.serializeArray(),
			dataType : 'json',
			cache    : false,
			success  : function( json ) {

				// 1. 테이블 생성
				renderTableBody(json);

				// 2. 페이징 생성
				rederPagination();
			},
			error    : function(request){
				alertMsgByJson(request);
			}
		});
	}

	function pageReload(){
		var pageNo = $('#pageForm #pageNo').val();
		goPage(pageNo);
	}

	// 테이블 그리기
	function renderTableBody(json){
		// Parsing to Json
		// alert(json);
		var _json = JSON.stringify(json);
		var jsonData = JSON.parse(_json);

		// List 의 길이
		var listLength = jsonData.lists.length;

		// 리스트 전체 Row 수
		var totalCount = jsonData.totalCount;

		// 페이지 폼에 저장
		$('#pageForm #totalCount').val(totalCount);

		// Table Body에 'Html'이 들어갈 변수
		var bodyHtml = listLength > 0 ? "" : "<tr class=\"list\"><td colspan=\"5\">검색 결과가 없습니다.</td></tr>";
		for (var i = 0; i < listLength; i++) {
			var todoItem = jsonData.lists[i];

			var todoId 		= todoItem.todoId;
			var whatTodo 	= todoItem.whatTodo;
			var insertDate 	= todoItem.insertDate;
			var updateDate 	= ( todoItem.updateDate == null ? todoItem.insertDate : todoItem.updateDate );
			var status 	= todoItem.status;

			var todoReferenceList = todoItem.todoReferenceList;

			bodyHtml += "<tr>";
			bodyHtml += "<td data-id='"+todoId+"'>"+todoId+"</td>";

			if(status == COMPLETED)
				bodyHtml += "<td class='tdRow' todoId='"+todoId+"' whatTodo='"+whatTodo+"' status='"+status+"'><strike>"+whatTodo+"</strike>";
			else
				bodyHtml += "<td class='tdRow' todoId='"+todoId+"' whatTodo='"+whatTodo+"' status='"+status+"'>"+whatTodo;
			// 참조하고 있는 Todo Render
			for (var j = 0; j < todoReferenceList.length; j++) {
				if(j == 0) bodyHtml += "<p/>";
				var todoReferenceItem = todoReferenceList[j];

				var c_parentTodoId = todoReferenceItem.parentTodoId;
				var c_whatTodo = todoReferenceItem.whatTodo;
				var c_status = todoReferenceItem.status;

				//var c_refSeq = todoReferenceItem.refSeq;
				//var c_todoId = todoReferenceItem.todoId;

				if(c_status == COMPLETED)
					bodyHtml += "<a href='#' data-toggle='tooltip' data-placement='top' title='"+c_whatTodo+"("+(c_status == ING ? "진행중": "완료됨")+")'><strike>@"+c_parentTodoId+"</strike></a>";
				else
					bodyHtml += "<a href='#' data-toggle='tooltip' data-placement='top' title='"+c_whatTodo+"("+(c_status == ING ? "진행중": "완료됨")+")'>@"+c_parentTodoId+"</a>";

				if(j < todoReferenceList.length-1) bodyHtml += ",&nbsp;";
			}
			if(todoReferenceList.length == 0) bodyHtml += "<p/>";
			bodyHtml += "</td>";
			bodyHtml += "<td>"+insertDate+"</td>";
			bodyHtml += "<td>"+updateDate+"</td>";
			//bodyHtml += "<td>"+(status == 'I' ? "진행중": "완료됨")+"</td>";

			bodyHtml += "<td>";
			bodyHtml += "<select name='status' id='status' onChange=\"modTodoStatus(this, "+ todoId +",'"+whatTodo+"')\" >";
			bodyHtml += "<option value='I'"+(status == ING ? " selected": "")+">진행중</option>";
			bodyHtml += "<option value='C'"+(status == COMPLETED ? " selected": "")+">완  료</option>";
			bodyHtml += "</select>";
			bodyHtml += "</td>";

			bodyHtml += "<td><button type='button' todoId='"+ todoId +"' class='delBtn btn btn-danger btn-sm' id='delBtn' >삭제</button></td>";

			bodyHtml += "</tr>";
		}

		// Set Table Body render
		$('#tBody').html( bodyHtml );

		// Table Row click event - (할일) 클릭시 이벤트 설정
		$('.tdRow').click(function(){
			var todoId 	 = $(this).attr('todoId');
			var whatTodo = $(this).attr('whatTodo');
			var status 	 = $(this).attr('status');

			// 수정 Form에 parameter Value 세팅
			$('#modForm #todoId').val(todoId);
			$('#modForm #whatTodo').val(whatTodo);
			$('#modForm #status').val(status);

			$('#modForm .status').html((status == ING ? "진행중": "완료됨"));

			if(status == COMPLETED){
				// 수정 버튼 없애기  - 완료된 Todo는 수정불가 -> 완료된 todo는 완료된todo만 참조가 가능하므로 의미가 없다.
				$('#modBtn').hide();
			} else {
				$('#modBtn').show();
			}

			// 수정 팝업창의 체크 박스 세팅
			setCheckBoxWithModal(todoId);

			$("#myModal").modal();
		});

		// 삭제 버튼 이벤트 listener
		$('.delBtn').click(function(){
			var _todoId 	 = $(this).attr('todoId');
			removeTodo(_todoId);
		});

		$('[data-toggle="tooltip"]').tooltip();
	}

	function modTodoStatus(obj, todoId, whatTodo){
		// set Form data
		$('#modForm #todoId').val(todoId);
		$('#modForm #whatTodo').val(whatTodo);
		$('#modForm #status').val(obj.value);

		// 수정
		modTodo('/status');
	}

	// Remove TodoItem
	function removeTodo(todoId){
		var reqUrl = todoBaseUrl + "/" + todoId;
		var frm = $('#pageForm');

		if (confirm('삭제 하시겠습니까?')) {
			$.ajax({
				url: reqUrl,
				type: 'delete',
				data: frm.serializeArray(),
				dataType: 'json',
				cache: false,
				success  : function( json ) {
					var _json = JSON.stringify(json);
					var jsonData = JSON.parse(_json);
					var message = jsonData.message;

					// msg alert
					alert(message);

					// move to first Page.
					goPage(1);
				},
				error: function (request) {
					alertMsgByJson(request);
				}
			});
		}
	}

	//## SelectRefrenceTodoList for Checkbox
	// 참조하고 있는 Todo 목록 조회 (체크박스 만들기 용)
	//  parameter
	//      : todoId
	//  Response Example
	//  todoId   refYN
	//    1        y
	//    2        n
	//    3        n
	//    4        y
	//
	var refTodoList = '/api/todos/{todoId}/ref';
	function setCheckBoxWithModal(todoId){
		var reqUrl = refTodoList.replace("{todoId}",todoId);

		$.ajax({
			url      : reqUrl,
			type     : 'get',
			//data	 :	frm.serializeArray(),
			dataType : 'json',
			cache    : false,
			success  : function( json ) {

				var _json = JSON.stringify(json);
				var jsonData = JSON.parse(_json);

				// List 의 길이
				var listLength = jsonData.lists.length;

				// Table Body에 'Html'이 들어갈 변수
				var chkHtml = listLength > 0 ? "" : "참조 가능한 Todo가 없어요.";
				for (var i = 0; i < listLength; i++) {
					var todoItem = jsonData.lists[i];

					var todoId 		= todoItem.todoId;
					var whatTodo 	= todoItem.whatTodo;
					var insertDate 	= todoItem.insertDate;
					var updateDate 	= ( todoItem.updateDate == null ? todoItem.insertDate : todoItem.updateDate );
					var status 		= todoItem.status;
					var refYn 		= todoItem.refYn;

					var checked = ( refYn == 'Y' ? 'checked' : '' );

					chkHtml += " <input type='checkbox' id='parentTodoId' name='parentTodoId' value='"+todoId+"' "+checked+" >&nbsp;"+whatTodo+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
					if( i > 0 && ( i % 3 == 0 ) ) chkHtml += "</p>";
				}

				$('#modForm .parentTodoIds').html(chkHtml);
			},
			error    : function(request){
				alertMsgByJson(request);
				pageReload();
			}
		});
	}

	//## Add TodoItem
	function addTodo(){
		var frm = $('#addForm');
		var reqUrl = todoBaseUrl;

		var whatTodo = $('#addForm #whatTodo').val();
		if(whatTodo == '' || whatTodo == null){
			alert(whatTodo+"할일을 입력하세요.");
			$('#addForm #whatTodo').focus();
			return;
		}
		//alert("todoListUrl :"+todoListUrl);
		$.ajax({
		 url      : reqUrl,
		 type     : 'post',
		 data	 :	frm.serializeArray(),
		 dataType : 'json',
		 cache    : false,
		 success  : function( json ) {
			var _json = JSON.stringify(json);
			var jsonData = JSON.parse(_json);
			var message = jsonData.message;

			// msg alert
			alert(message);

			// reset input
			$('#addForm #whatTodo').val('');

			// move to first Page.
			goPage(1);

		 },
		 error : function(request){
					// error message Parse
					alertMsgByJson(request);
					pageReload();
		 }
	 });
	}

	// ## Modify TodoItem
	function modTodo(path){
		var frm = $('#modForm');
		var reqUrl = todoBaseUrl+path;

		var whatTodo = $('#modForm #whatTodo').val();
		// var status = $('#modForm #status' ).val()

		if(whatTodo == '' || whatTodo == null){
			alert( "필수 항목 누락" );
			return;
		}

		if (confirm('수정 하시겠습니까?')) {
			$.ajax({
				url      : reqUrl,
				type     : 'put',
				data	 :	frm.serializeArray(),
				dataType : 'json',
				cache    : false,
				success  : function( json ) {
					var _json = JSON.stringify(json);
					var jsonData = JSON.parse(_json);
					var message = jsonData.message;

					// Close modal popup
					$('#myModal').modal('hide');

					// msg alert
					alert(message);

					// move to first Page.
					pageReload();
				},
				error    : function(request){
					alertMsgByJson(request);
					$('#myModal').modal('hide');
					pageReload();
				}
			});

		}
	}

	function alertMsgByJson(request){
		var json = JSON.parse(JSON.stringify(request));
		var rjson = json.responseText;

		alert(rjson);
	}

	function rederPagination() {
		var pageNo 			  = Number($('#pageForm #pageNo').val());
		var perPage 		  = Number($('#pageForm #perPage').val());
		var totalCount 		  = Number($('#pageForm #totalCount').val());
		var pageHorizonalSize = Number($('#pageForm #pageHorizonalSize').val());

		var tag = new StringBuffer();

		//  처음 이전  1 2 3  다음 마지막

		var totolPage = Math.floor( totalCount % perPage ) == 0 ? Math.floor( totalCount / perPage ) : ( Math.floor( totalCount / perPage ) + 1 );
		var currentGroupNo = Math.floor( pageNo % pageHorizonalSize) == 0 ? ( Math.floor( pageNo / pageHorizonalSize ) - 1 ) :  Math.floor( pageNo / pageHorizonalSize );
		var currentGroupStartPageNo = ( currentGroupNo * pageHorizonalSize ) + 1;
		var currentGroupEndPageNo = ( currentGroupStartPageNo + pageHorizonalSize );

		var lastGroupNo =   totolPage < pageHorizonalSize ? 0 :( ( totolPage % pageHorizonalSize ) == 0 ?  ( Math.floor( totolPage / pageHorizonalSize ) - 1 ) : Math.floor(totolPage / pageHorizonalSize) );

		// first * previous
		if( currentGroupNo > 0 && currentGroupNo <= lastGroupNo ) tag.append('<li><a href=\"javascript:goPage(1);\">처음&nbsp;</a></li>');
		if( currentGroupNo > 0 ) tag.append('<li><a href=\"javascript:goPage('+ (currentGroupStartPageNo - 1) +');\">이전&nbsp;</a></li>');

		// Paging
		for(var i = currentGroupStartPageNo; i < currentGroupEndPageNo ; i++) {
			var pno = i;

			tag.append('<li><a href=\"javascript:goPage('+pno+');\">'+pno+'</a></li>');

			if(pno == totolPage) break;
		}

		// next & End
		if( currentGroupNo < lastGroupNo ) {
			tag.append('<li><a href=\"javascript:goPage(' + currentGroupEndPageNo + ');\">다음&nbsp;</a></li>');
			tag.append('<li><a href=\"javascript:goPage(' + totolPage + ');\">마지막&nbsp;</a></li>');
		}
		$('.pagination').html(tag.toString());
	}

	var StringBuffer = function() {
		this.buffer = new Array();
	};
	StringBuffer.prototype.append = function(str) {
		this.buffer[this.buffer.length] = str;
	};
	StringBuffer.prototype.toString = function() {
		return this.buffer.join("");
	};
</script>

</head>
<body>
	<div class="container-fluid">
		
		<!-- Paging Info -->
		<form class="form-inline" id ='pageForm'>
			<input type="hidden" id="pageNo" value="" />
			<input type="hidden" id="totalCount" value="" />
			<input type="hidden" id="perPage" value="5" />
			<input type="hidden" id="pageHorizonalSize" value="3" />
		</form>

		<!-- TOP -->
		<div class="jumbotron">
			<h1>TODO</h1>      
			<p>오늘 할일은 내일로 미루자.</p>
		</div>
	
		<!-- Modification Modal Popup -->
		<form class="form-inline" action="" id ='modForm'>
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">
			    <!-- Modal content-->
				    <div class="modal-content">
				    	
						<div class="modal-header">
				        	<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Modify TodoItem</h4>
						</div>
						<div class="modal-body">
							<input type="hidden" id="todoId" value="" name="todoId" />
							<input type="hidden" id="status" value="" name="status" />
				
					        <label for="whatTodo">[할일]</label>
		      				<input type="text" class="form-control" id="whatTodo" value="" placeholder="할일을 입력하세요." name="whatTodo">
		      				<p>&nbsp;</p>
		      				<label for="parentTodoIds">[참조ToDo]</label>
		      				<p class="parentTodoIds ">&nbsp;</p>
		      				<p>&nbsp;</p>
		      				<label for="parentTodoId">[진행상태]</label>
		      				<p class="status">&nbsp;</p>
		      				<!-- <select name="status" id="status">
							  <option value="I">진행중</option>
							  <option value="C">완료됨</option>
							</select> -->
		    			</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="modBtn">수정하기</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	
		<!-- Form -->
		<form class="form-inline" id ='addForm'>
		    <div class="form-group">
		      <label for="whatTodo">할일 :</label>
		      <input type="text" class="form-control" id="whatTodo" placeholder="할일을 입력하세요." name="whatTodo">
		    </div>
		    
		    <button type="button" class="btn btn-default" id="addBtn">등록하기</button>
		</form>
	
		<!-- Table -->
		<table class="table table-striped">
			<caption></caption>
			<thead>
				<tr>
					<th>Id</th>
					<th>할일</th>
					<th>작성일</th>
					<th>최종수정일</th>
					<th>상태</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody id='tBody'></tbody>
		</table>
		<!-- Pagination -->
		<div class="outer">
			<div class="inner">
				<div class="centered">
					<ul class="pagination"><li>...</li></ul>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<div class="alert alert-success">
	  <strong>Todo App</strong> for kakaoix
	</div>
</body>
</html>
