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
	
	<div class="right-content" style="overflow: scroll !important;">
		<div id="TabbedPanels1" class="TabbedPanels">
		  	<div class="TabbedPanelsContentGroup">
		  	    <!--题目抬头  -->
				<div class="TabbedPanelsContent">
					<div class="content-box">
						<div class="title">
							<div id="title">考试信息</div>
							<!-- <a class="btn btn-new" href="javascript:void(0)" onclick="window.history.go(-1)">返回</a> -->
							<a class="btn btn-new" href="${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSiteId}&moduleType=${moduleType}&selectId=${selectId}">返回</a>
     					</div> 
						
						<div class="new-classroom">
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr> 
							    	<th><label style="margin-left: 16px">考试标题：</label></th>
							    	<td>${parentTest.title }</td>
							    	<th><label style="margin-left: 16px">答题详情：</label></th>
							    	<td>${tAssignmentGrading.finalScore }分</td>
	                                </tr>
                                    <tr> 
							    	<th><label style="margin-left: 16px">开始时间：</label></th>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parentTest.TAssignmentControl.startdate.time}" type="both"/></td>
                                    <th><label style="margin-left: 16px">截止时间：</label></th>
									<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parentTest.TAssignmentControl.duedate.time}" type="both"/></td>
                                    </tr>
                                    <tr>
                                        <th><label style="margin-left: 16px">总分：</label></th>
                                        <td>
                                          ${parentTest.TAssignmentAnswerAssign.score }
                                        </td>
                                        <th><label style="margin-left: 16px">教师：</label></th>
                                        <td>
                                        ${parentTest.user.cname }
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
							<div id="title">答题详情</div>
						</div>
						
						<div class="new-classroom">
                            <table  class="tb"  id="my_show"> 
				                <thead>
				                    <tr> 
							    		<th width=10%>序号</th>
							    		<th width=90%>
										<table  class="tb"  id="my_show"> 
											<tr>
												<td width=35%>题目名称</td>
												<td width=5%>题目类型</td>
												<td width="5%">得分</td>
												<td width=55%>回答</td>
											</tr>
										</table>
										</th>
								    	<!-- <th>
									    	<table  class="tb"  id="my_show"> 
									    	</table>
								    	
								    	</th> -->
	                                </tr>
                                </thead>
                                <tbody>
                                	
                                	<c:set var="totalScore" value="0"></c:set>
                                	
                                    <c:forEach items="${test.TAssignmentSections}" var="current"  varStatus="i">
                                   	<c:set var="countScore" value="0"></c:set>
                                    <tr>
                                        <td>${i.count }:<c:out value="${current.description}"></c:out></td>
                                        <td>
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
                                               	<td>
                                                    <c:if test="${scoreMap[current1.id] == '0.00'||scoreMap[current1.id] == null }">
                                                    	<b><font color="red">0</font></b>
                                                    </c:if>
                                                    <c:if test="${scoreMap[current1.id] != '0.00' }">
                                                    	<b><font color="green"><c:out value="${scoreMap[current1.id] }"></c:out></font></b>
                                                    	<c:set var="countScore" value="${countScore+scoreMap[current1.id] }"></c:set>
                                                    	<c:set var="totalScore" value="${totalScore+scoreMap[current1.id] }"></c:set>
                                                    </c:if>
                                               	</td>
                                               	<td width=55%>
                                                   <c:forEach items="${current1.TAssignmentAnswers}" var="current2"  varStatus="k">
	                                                    <c:if test="${current1.type==1 }">
	                                                           <input type="checkbox" <c:if test="${recordMap[current2.id]!=null }">checked="true"</c:if> name="answers${current1.id}" id="answers${current1.id}" value="${current2.id}"/>
	                                                           ${current2.label}.<c:out value="${current2.text}"></c:out>
	                                                           <c:if test="${current2.iscorrect == 1&&recordMap[current2.id]!=null }"><img src="${pageContext.request.contextPath}/images/songProject/tick_green_big.gif"></c:if>
	                                                           <c:if test="${current2.iscorrect == 1&&recordMap[current2.id]==null }"><img src="${pageContext.request.contextPath}/images/songProject/tick_green_small.gif"></c:if>
	                                                           <c:if test="${current2.iscorrect == 0&&recordMap[current2.id]!=null }"><img src="${pageContext.request.contextPath}/images/songProject/cross_red_big.gif"></c:if>
	                                                           <c:if test="${current2.iscorrect == 0&&recordMap[current2.id]==null }"><img src="${pageContext.request.contextPath}/images/songProject/cross_red_small.gif"></c:if>
	                                                           <br>
	                                                    </c:if>
	                                                    <c:if test="${current1.type==2 }">
	                                                           <input type="radio" <c:if test="${recordMap[current2.id]!=null }">checked="true"</c:if> name="answers${current1.id}" id="answers${current1.id}" value="${current2.id}"/> ${current2.text}
	                                                           <c:if test="${current2.iscorrect == 1&&recordMap[current2.id]!=null }"><img src="${pageContext.request.contextPath}/images/songProject/tick_green_big.gif"></c:if>
	                                                           <c:if test="${current2.iscorrect == 1&&recordMap[current2.id]==null }"><img src="${pageContext.request.contextPath}/images/songProject/tick_green_small.gif"></c:if>
	                                                           <c:if test="${current2.iscorrect == 0&&recordMap[current2.id]!=null }"><img src="${pageContext.request.contextPath}/images/songProject/cross_red_big.gif"></c:if>
	                                                           <c:if test="${current2.iscorrect == 0&&recordMap[current2.id]==null }"><img src="${pageContext.request.contextPath}/images/songProject/cross_red_small.gif"></c:if>
	                                                           <br>
	                                                    </c:if>
	                                                    <c:if test="${current1.type==4 }">
	                                                           <input type="radio" <c:if test="${recordMap[current2.id]!=null }">checked="true"</c:if> name="answers${current1.id}" id="answers${current1.id}" value="${current2.id}"/>
	                                                           ${current2.label}.<c:out value="${current2.text}"></c:out>
	                                                           <c:if test="${current2.iscorrect == 1&&recordMap[current2.id]!=null }"><img src="${pageContext.request.contextPath}/images/songProject/tick_green_big.gif"></c:if>
	                                                           <c:if test="${current2.iscorrect == 1&&recordMap[current2.id]==null }"><img src="${pageContext.request.contextPath}/images/songProject/tick_green_small.gif"></c:if>
	                                                           <c:if test="${current2.iscorrect == 0&&recordMap[current2.id]!=null }"><img src="${pageContext.request.contextPath}/images/songProject/cross_red_big.gif"></c:if>
	                                                           <c:if test="${current2.iscorrect == 0&&recordMap[current2.id]==null }"><img src="${pageContext.request.contextPath}/images/songProject/cross_red_small.gif"></c:if>
	                                                           <br>
	                                                    </c:if>
	                                                    <c:if test="${current1.type==8 }">
                                                        	${k.count }.
                                                        	<input type="hidden" name="answers${current1.id}" value="${current2.id}"/>
                                                        	<input type="text" name="answertexts${current1.id}"  value="${recordMap[current2.id].answerText}"/>
                                                        	<c:if test="${recordMap[current2.id]!=null && recordMap[current2.id].answerText eq current2.text}"><img src="${pageContext.request.contextPath}/images/songProject/tick_green_big.gif"></c:if>
                                                           	<c:if test="${recordMap[current2.id]!=null && recordMap[current2.id].answerText ne current2.text}"><img src="${pageContext.request.contextPath}/images/songProject/cross_red_big.gif"></c:if>
                                                        	参考答案：${current2.text }
                                                        	<br>
                                                        </c:if>
                                                   </c:forEach>
                                               	</td>
                                            </tr>
                                            
                                        </c:forEach>
                                        	<tr>
                                        		<td>${current.description }</td>
                                        		<td>得分</td>
                                        		<td><b><font color="green">${countScore }</font></b></td>
                                        		<td></td>
                                        	</tr>
                                        </table>
                                        </td>
                                     </tr> 
                                    </c:forEach>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>
                                        	<table>
                                        		<tr>
	                                        		<td width="35%">总计</td>
	                                        		<td width="5%">得分</td>
	                                        		<td width="5%"><b><font color="green">${totalScore }</font></b></td>
	                                        		<td width="55%"></td>
	                                        	</tr>
                                        	</table>
                                        </td>
                                    </tr>
                                 </tbody>
							</table>
							</div>
							
							<div class="moudle_footer">
						        <div class="submit_link">
						            <!-- <input class="btn btn-return" type="button" value="返回" onclick="window.history.go(-1)"> -->
						            <a class="btn btn-new" href="${pageContext.request.contextPath}/tcoursesite/chaptersList?tCourseSiteId=${tCourseSiteId}&moduleType=${moduleType}&selectId=${selectId}">返回</a>
						        </div>
						    </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>