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
<!-- 打印插件的引用 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>
<script type="text/javascript">
	function checkForm(){
		var countNumber;
		var resultVar="";
		var flag = -1;
		$(".answer").each(function(i,value){
			countNumber = 0;
			countNumber +=$(this).find("input:checked").size();
			$(this).find("input[type='text']").each(function(i,obj){
				if ($(this).val().trim()!="") {
					countNumber++;
				}
			})
				
			if (countNumber==0) {
				if (flag == -1) {
					flag = i;
				}
				resultVar += $(this).next().find("input:eq(0)").val()+"第"+$(this).next().find("input:eq(1)").val()+"题，";
			}
			
		})
		
		if (resultVar=="") {
			if(confirm('仅可提交一次，确认提交吗？')){
				$("#save").attr("disabled","true");
				$("#button").attr("disabled","true");
				return true;
			}
		}else {
			resultVar = resultVar.substring(0, resultVar.length-1)+" 尚未作答，是否确认提交？";
			if(confirm(resultVar)){
				$("#save").attr("disabled","true");
				$("#button").attr("disabled","true");
				return true;
			}else{
				$(".answer:eq("+flag+")").find("input:eq(0)")[0].focus();
				return false;
			}
		}
		return false;
		
	}
	$(function(){//打印
	     $("#print").click(function(){
		$("#my_show").jqprint();
		});
	  });

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
							<div id="title">考试卷信息 <input type="button" value="打印" id="print"></div>
     					</div> 
						
						<div class="new-classroom">
                            <table  class="tb"> 
				                <thead>
				                    <tr> 
							    	<th><label style="margin-left: 16px">所属站点：</label></th>
							    	<td>${parentTest.TCourseSite.title }</td>
							    	<th><label style="margin-left: 16px">考试标题：</label></th>
							    	<td>${parentTest.title }</td>
	                                </tr>
                                    <tr> 
							    	<th><label style="margin-left: 16px">开始时间：</label></th>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${parentTest.TAssignmentControl.startdate.time}" type="both"/></td>
                                    <th><label style="margin-left: 16px">截止时间：</label></th>
									<td><fmt:formatDate pattern="yyyy-MM-dd" value="${parentTest.TAssignmentControl.duedate.time}" type="both"/></td>
                                    </tr>
                                    <tr>
                                        <th><label style="margin-left: 16px">总分：</label></th>
                                        <td>
                                          <%-- <c:set var="countScore" value="0"></c:set>
                                          <c:forEach items="${test.TAssignmentSections}" var="current"  varStatus="i">
                                              <c:forEach items="${current.TAssignmentItems}" var="current1"  varStatus="k">
                                                       <c:set var="countScore" value="${countScore+current1.score }"></c:set>
                                              </c:forEach>
                                          </c:forEach> --%>
                                          ${parentTest.TAssignmentAnswerAssign.score}分
                                        <th><label style="margin-left: 16px">教师：</label></th>
                                        <td>${parentTest.user.cname }
							 	     </td>
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
							<div id="title">试卷答案</div>
						</div>
						<form:form name="myForm" action="${pageContext.request.contextPath}/teaching/test/saveTAssignmentItemMapping" method="POST" onsubmit="return checkForm()">
						
						<div class="new-classroom">
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr> 
							    	<th width=10%>序号</th>
							    	<th width=90%>
							    	 <table  class="tb"  id="my_show"> 
							    	 <tr>
							    	  <td width=50%>题目</td>
							    	  <td width=50%>答案</td>
							    	  </tr>
							    	  </table>
							    	  </th>
							    	<th>
							    	<!-- <table  class="tb"  id="my_show"> 
							    	</table> -->
							    	
							    	</th>
	                                </tr>
                                 </thead>
                                 <tbody>
                                    <c:forEach items="${test.TAssignmentSections}" var="current"  varStatus="i">
                                    <tr>
                                        <td>${i.count }:<c:out value="${current.description}"></c:out></td>
                                        <td>
                                        <table  class="tb"  id="my_show"> 
                                        <c:forEach items="${current.TAssignmentItems}" var="current1"  varStatus="j">
                                            <tr>
                                           		<td width=50%>${j.count}
                                           		<%--<c:if test="${current1.type==1||current1.type==2||current1.type==4}">
                                           			<c:out value="${current1.description}"></c:out>
                                           		</c:if>
                                           		--%><%--<c:if test="${current1.type==8}">
                                           			<c:out value="${current1.descriptionTemp}"></c:out>
                                           		</c:if>
                                           		--%><%--<b>(分值：${current1.score})</b><br>
                                           		--%>	                 
                                               	</td>
                                               	<%--<td width=5%>
                                  					<c:if test="${current1.type==1}">多选题</c:if>
		                                           	<c:if test="${current1.type==2}">对错题</c:if>
		                                           	<c:if test="${current1.type==4}">单选题</c:if>
		                                           	<c:if test="${current1.type==5}">简答题</c:if>
		                                           	<c:if test="${current1.type==8}">填空题</c:if>
		                                           	<c:if test="${current1.type==9}">匹配题</c:if>
                                               	</td>
                                               	--%><td width=60% class="answer">
                                                   	<%--<c:forEach items="${current1.TAssignmentAnswers}" var="current2"  varStatus="k">
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
                                                        <input type="text" name="answertexts${current1.id}"  value=""/><br>
                                                        <input type="hidden" name="answers${current1.id}" value="${current2.id}"/>
                                                        </c:if>
                                                   	</c:forEach>--%>
                                                   	<!-- 输出选择题答案 -->
                                                   	<c:if test="${current1.type==1||current1.type==4}">
                                                   		<c:forEach items="${current1.TAssignmentAnswers}" var="current2"  varStatus="j">
                                           				<c:if test="${current2.iscorrect==1}">${current2.label} </c:if>
                                                    	</c:forEach>
                                                   	</c:if>
                                                   	<!-- 输出填空题答案 -->
                                                   	<c:if test="${current1.type==8}">
                                                   		<c:forEach items="${current1.TAssignmentAnswers}" var="current2"  varStatus="j">
                                           				<c:if test="${current2.iscorrect==1}">${current2.text} </c:if>
                                                    	</c:forEach>
                                                   	</c:if>
                                                   	<!-- 输出判断题答案 -->
                                                   	<c:if test="${current1.type==2}">
                                                   		<c:forEach items="${current1.TAssignmentAnswers}" var="current2"  varStatus="j">
                                           				<c:if test="${current2.iscorrect==1}">${current2.text} </c:if>
                                                    	</c:forEach>
                                                   	</c:if>
                                                   	
                                                   	</td>
                                               	<td width="0%">
                                               		<input type="hidden" value="${current.description }"/>
                                               		<input type="hidden" value="${j.count }"/>
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
							<input type="hidden" name="assignmentId" value="${test.id}">
							<input type="hidden" id="submitTime" name="submitTime" value="1">
							<input type="hidden" name="simulation" id="simulation" value="${simulation }">					
							
							<div class="moudle_footer">
						        <div class="submit_link">
						            <!-- <input class="btn btn-return" id="button" type="button" value="返回" onclick="window.history.go(-1)"> -->
						            <input class="btn btn-return" id="save" type="submit" onclick="$('#submitTime').val(1);" value="提交">
						            <!-- <input class="btn btn-return" id="save" type="submit" onclick="$('#submitTime').val(0);" value="保存"> -->
						            
					        	</div>
						    </div>
					    </form:form>
					</div>
				</div>
			</div>
		</div>
	</div>


<input type="hidden" id="year" value="<fmt:formatDate value='${parentTest.TAssignmentControl.duedate.time}' pattern='yyyy'></fmt:formatDate>" />
<input type="hidden" id="month" value="<fmt:formatDate value='${parentTest.TAssignmentControl.duedate.time}' pattern='MM'></fmt:formatDate>" />
<input type="hidden" id="day" value="<fmt:formatDate value='${parentTest.TAssignmentControl.duedate.time}' pattern='dd'></fmt:formatDate>" />
<input type="hidden" id="hour" value="<fmt:formatDate value='${parentTest.TAssignmentControl.duedate.time}' pattern='HH'></fmt:formatDate>" />
<input type="hidden" id="minute" value="<fmt:formatDate value='${parentTest.TAssignmentControl.duedate.time}' pattern='mm'></fmt:formatDate>" />
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
</body>


</html>