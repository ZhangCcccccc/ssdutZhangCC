 <%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
 <jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
 
 <html >  
<head>
<meta name="decorator" content="iframe"/>
<!-- 富文本的css -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ueditor/themes/default/css/ueditor.css"/> 

<!-- 下拉框的样式 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/chosen/chosen.css" />
<!-- 下拉的样式结束 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/iconFont.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
	function checkUser(obj){
		if($(obj).val().trim()!=""){
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/teaching/coursesitetag/checkUser",
				data: {'username':$(obj).val().trim()},
				dataType:'json',
				success:function(data){
					if(!data){
						alert("该工号不存在，请查询后输入！");
						$(obj).val("");
					}
				}
			});
			
		}
	}
	
/*
*编辑题目
*/
function editTAssignmentItem(examItemId){
var sessionId = $("#sessionId").val();
var con = '<iframe scrolling="yes" id="message" frameborder="0"  src="${pageContext.request.contextPath}/teaching/exam/examItemInfo?examItemId='+ examItemId +'" style="width:100%;height:100%;"></iframe>'
$('#editTAssignmentItem').html(con);
//获取当前屏幕的绝对位置
var topPos = window.pageYOffset;
//使得弹出框在屏幕顶端可见
$('#editTAssignmentItem').window({left:"px", top:topPos+"px"});
$('#editTAssignmentItem').window('open');
}
</script>
</head>
<body>
	
	<div class="right-content">
		<div id="TabbedPanels1" class="TabbedPanels">
		  	<div class="TabbedPanelsContentGroup">
		  	    <!--题目抬头  -->
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">测验卷信息</div>
     					</div> 
						
						<div class="new-classroom">
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr> 
							    	<th><label style="margin-left: 16px">测验标题：</label></th>
							    	<td>${examInfo.title }</td>
							    	<th><label style="margin-left: 16px"></label></th>
							    	<td><%--${examInfo.TCourseSite.title }--%></td>
	                                </tr>
                                    <tr> 
							    	<th><label style="margin-left: 16px">开始时间：</label></th>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${examInfo.TAssignmentControl.startdate.time}" type="both"/></td>
                                    <th><label style="margin-left: 16px">截止时间：</label></th>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${examInfo.TAssignmentControl.duedate.time}" type="both"/></td>
                                    </tr>
                                    <tr>
                                        <th><label style="margin-left: 16px">总分：</label></th>
                                        <td>
                                          <c:set var="countScore" value="0"></c:set>
                                          <c:forEach items="${examInfo.TAssignmentSections}" var="current"  varStatus="i">
                                              <c:forEach items="${current.TAssignmentItems}" var="current1"  varStatus="k">
                                                       <c:set var="countScore" value="${countScore+current1.score }"></c:set>
                                              </c:forEach>
                                          </c:forEach>
                                          ${countScore}
                                        <th><label style="margin-left: 16px">教师：</label></th>
                                        <td>${examInfo.user.cname }
							 	     </td>
							 	     </tr>
							 	     <tr> 
							    	 <th><label style="margin-left: 16px">测试描述：</label></th>
							    	 <td>${examInfo.content }</td>
	                                 </tr>
							</thead>
							</table>
							</div>
					</div>
				</div>
				<!--题目内容  -->
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">开始答题</div>
						</div>
						<form:form action="${pageContext.request.contextPath}/teaching/exam/saveTAssignmentItemMapping?moduleType=${moduleType}&selectId=${selectId}" method="POST" >
						
						<div class="new-classroom">
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr> 
							    	<!-- <th>序号</th>
							    	<th>题目名称</th>
							    	<th>题目类型</th>
							    	<th>分值</th>
							    	<th>创建时间</th>
							    	<th>删除</th> -->
							    	<th width=10%>序号</th>
							    	<th>
							    	 <table  class="tb"  id="my_show"> 
							    	 <tr>
							    	  <td width=35%>题目名称</td>
							    	  <td width=5%>题目类型</td>
							    	  <td width=60%>回答</td>
							    	  </tr>
							    	  </table>
							    	<th>
							    	<table  class="tb"  id="my_show"> 
							    	</table>
							    	
							    	</th>
	                                </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${examInfo.TAssignmentSections}" var="current"  varStatus="i">
                                    <tr>
                                        <td>${i.count }:<c:out value="${current.description}"></c:out></td>
                                        <td>
							    	    <c:if test="${current.TAssignmentItems.size()==0}">
							    	        <%--<a href="${pageContext.request.contextPath}/teaching/exam/newExamItem?id=-1&sectionId=${current.id}">添加题目</a>
							    	    --%>
							    	    	无题目
							    	    </c:if>
                                        <table  class="tb"  id="my_show"> 
                                        <c:forEach items="${current.TAssignmentItems}" var="current1"  varStatus="j">
                                            <tr>
                                           		<td width=35%>${j.count }、题干：
                                           		<c:if test="${current1.type==1||current1.type==2||current1.type==4}">
                                           			<c:out value="${current1.description}"></c:out>
                                           		</c:if>
                                           		<c:if test="${current1.type==8}">
                                           			<c:out value="${current1.descriptionTemp}"></c:out>
                                           		</c:if>
                                           		<b>(分值：${current1.score})</b><br>
                                                                                              
                                               	</td>
                                               	<td width=5%>
                                  					<c:if test="${current1.type==1}">多选题</c:if>
		                                           	<c:if test="${current1.type==2}">对错题</c:if>
		                                           	<c:if test="${current1.type==4}">单选题</c:if>
		                                           	<c:if test="${current1.type==5}">简答题</c:if>
		                                           	<c:if test="${current1.type==8}">填空题</c:if>
		                                           	<c:if test="${current1.type==9}">匹配题</c:if>
                                               	</td>
                                               	<td width=60%>
                                                   	<c:forEach items="${current1.TAssignmentAnswers}" var="current2"  varStatus="k">
                                                        <c:if test="${current1.type==1 }">
                                                        <input type="checkbox" name="answers${current1.id}" id="answers${current1.id}" value="${current2.id}"/>${current2.label}: <c:out value="${current2.text}"></c:out><br>
                                                        </c:if>
                                                    	<c:if test="${current1.type==2 }">
                                                        <input type="radio" name="answers${current1.id}" id="answers${current1.id}" value="${current2.id}"/><c:out value="${current2.text}"></c:out><br>
                                                        </c:if>
                                                    	<c:if test="${current1.type==4 }">
                                                        <input type="radio" name="answers${current1.id}" id="answers${current1.id}" value="${current2.id}"/>${current2.label}: <c:out value="${current2.text}"></c:out><br>
                                                        </c:if>
                                                    	<c:if test="${current1.type==8 }">
                                                        <input type="hidden" name="answers${current1.id}" value="${current2.id}"/>
                                                        <input type="text" name="answertexts${current1.id}"  value=""/><br>
                                                        </c:if>
                                                   	</c:forEach>
                                               	</td>
                                            </tr>
                                            
                                        </c:forEach>
                                        </table>
                                        </td>
                                     </tr> 
                                    </c:forEach>
                                    </tbody>
							</table>
							</div>
							<!--隐含参数  -->
							<input type="hidden" name="assignmentId" value="${examInfo.id}">
							<input type="hidden" id="submitTime" name="submitTime" value="1">
							<input type="hidden" name="simulation" id="simulation" value="${simulation }">					
							
							<div class="moudle_footer">
						        <div class="submit_link">
						            <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)">
						            <input class="btn btn-return" id="save" type="submit" onclick="$('#submitTime').val(1);" value="提交">
						            
					        	</div>
						    </div>
					    </form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- 编辑题目 -->
<div id="editTAssignmentItem" class="easyui-window" title="编辑题目" closed="true"  modal="true" iconCls="icon-add" style="width:1000px;height:500px">
</div>
	
<!-- 下拉框的js -->
<script src="${pageContext.request.contextPath}/chosen/chosen.jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/chosen/docsupport/prism.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var config = {
      '.chzn-select': {search_contains : true},
      '.chzn-select-deselect'  : {allow_single_deselect:true},
      '.chzn-select-no-single' : {disable_search_threshold:10},
      '.chzn-select-no-results': {no_results_text:'选项, 没有发现!'},
      '.chzn-select-width'     : {width:"95%"}
    }
    for (var selector in config) {
      $(selector).chosen(config[selector]);
    }
    
</script>
<!-- 下拉框的js -->
<script type="text/javascript">
	//生成html编辑栏，设置宽度为888px
	var editor = new UE.ui.Editor({initialFrameWidth:700});
	editor.render("content");	 
</script>	
</body>


</html>