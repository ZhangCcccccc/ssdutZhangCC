<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<fmt:setBundle basename="bundles.user-resources"/>
<jsp:useBean id="now" class="java.util.Date" />
<html>

<head>
    <title>XDU实验实训教学平台</title>
    <!--[if lt IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <![endif]-->
    <!--[if gte IE 8]>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>  
    <![endif]-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="Generator" content="gvsun">
    <meta name="Author" content="lyyyyyy">
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <link href="${pageContext.request.contextPath}/css/tCourseSite/lib.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/global.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/tCourseSite/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tCourseSite/checkWork_radio.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/tCourseSite/message/notice.css" rel="stylesheet" type="text/css">
	
	<!--下拉框样式-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/chosen/docsupport/prism.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/chosen/chosen.css" />
    <!--下拉框的样式结束-->
	
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.autosize.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/jquery.searchableSelect.js"></script>
<script type="text/javascript">
	
</script>

</head>

<body>
	<input type="hidden"  id="contextPath" value="${pageContext.request.contextPath}"/>
	<input type="hidden"  id="attendenceId" value="${curAttendenceId}"/>
    <div class="course_con ma back_gray">
        <div class="course_cont r back_gray">
        	<div class="notice_cont ">
            <div class="w100p cf">
                
                <ul class="tool_box tab cf rel zx2 pt5 ">
                
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/student/courseStudentsList?tCourseSiteId=${tCourseSite.id}&currpage=1" class="g3">
                        <i class="fa fa-edit mr5"></i>班级成员
                        </a>
                    </div>
                </li>
                
                <li class="rel l pb5">
                    <div class="wire_btn1 bgb l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/attendence?tCourseSiteId=${tCourseSite.id}&attendenceId=-1" class="g3">
                        <i class="fa fa-edit mr5"></i>考勤
                        </a>
                    </div>
                </li>
                
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="#" class="g9">
                        <i class="fa fa-file-text-o mr5"></i>作业
                        </a>
                    </div>
                </li>
                
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="${pageContext.request.contextPath}/tcoursesite/gradeBook?tCourseSiteId=${tCourseSite.id}&type=assignment" class="g3">
                        <i class="fa fa-comments-o mr5"></i>成绩
                        </a>
                    </div>
                </li>
                
                <li class="rel l pb5">
                    <div class="wire_btn1 l ml10 mt10 poi">
                        <a href="#" class="g9">
                        <i class="fa fa-comments-o mr5"></i>学习行为
                        </a>
                    </div>
                </li>
                </ul>
            </div>
            </div>            
            <div class="course_mod f14 mb2">
                <%--<div class=" lh40 bgg  pl30 f18 ">
                    <span class="bc">考勤</span>
                    <select onchange="findAttendence(this.value)" class="w20p lh30 br3 b1 mt5 select_chosen">		                     
                       <c:forEach items="${tAssignments}" var="tAssignment" varStatus="i">                                             		
                       			<option value="${tAssignment.id}"  <c:if test="${tAssignment.id == curAttendenceId}">selected="selected"</c:if>>${tAssignment.title}</option>                                          		
                       </c:forEach>
                   </select>
                </div>
                --%><div class=" lh40 bgg  pl30 f18 ">
                    <span class="bc">考勤</span>
                </div>
                <div class="cf">
                    <div class="l mt10 ml30" style="width:150px;">
                    
                    <select onchange="findAttendence(this.value)"  class="chzn-select" style="width:150px;">		                     
                       <c:forEach items="${tAssignments}" var="tAssignment" varStatus="i">                                             		
                       			<option value="${tAssignment.id}"  <c:if test="${tAssignment.id == curAttendenceId}">selected="selected"</c:if>>${tAssignment.title}</option>                                          		
                       </c:forEach>
                   </select>
                    </div>                    
                </div>
                <div class="module_con  mtb20">
                    <div class="plr30 lh30 f14">
                        <div class="w100p f14">
                        	<form:form id="form" action="" method="POST" enctype="multipart/form-data" name="frm">
                            <table class="w100p" id="studentsList">                         	
                                <tr>
                                	<th class="w10p tl">姓名</th>
                                    <th class="w10p tl ">学号</th>
                                    <th class="tl w10p">出勤 </th>                                                                       
                                    <th class="tl w10p">迟到                           
                                    <input id="lateScore" name="lateScore" type="number" class=" w35p b1 br3 h30 lh30 mt5 plr5" value="${tAssignmentAnswerAssign.lateScore}" min="0" max="${tAssignmentAnswerAssign.score}" required/>                                   
                                    </th>
                                    <th class="tl w10p">早退
                                    <input id="earlyScore" name="earlyScore" type="number" class=" w35p b1 br3 h30 lh30 mt5 plr5" value="${tAssignmentAnswerAssign.earlyScore}" min="0" max="${tAssignmentAnswerAssign.score}" required/>
                                    </th>
                                    <th class="tl w10p">旷课
                                    <input id="truantScore" name="truantScore" type="number" class=" w35p b1 br3 h30 lh30 mt5 plr5" value="${tAssignmentAnswerAssign.truantScore}" min="0" max="${tAssignmentAnswerAssign.score}" required/>
                                    </th>
                                    <th class="tl w10p">请假
                                    <input id="leaveScore" name="leaveScore" type="number" class=" w35p b1 br3 h30 lh30 mt5 plr5" value="${tAssignmentAnswerAssign.leaveScore}" min="0" max="${tAssignmentAnswerAssign.score}" required/>
                                    </th> 
                                </tr>
                                <tbody id="body">
                                <c:forEach items="${attendenceList}" var="attendence" varStatus="i">
                                <tr>                          	
		                    		<td>${attendence[7]}</td>
                                    <td>${attendence[6]}</td>
                                    <td> 
                                    <c:if test="${attendence[8] == null ||attendence[8] == 0}">          		                               		
                    					<input type="radio" name="${attendence[6]}"  value="0" id="0${attendence[6]}" checked="checked" /><label onclick="count(${attendence[6]},'0${attendence[6]}')" for="0${attendence[6]}"></label>               						
                                	</c:if>
                                	<c:if test="${attendence[8] != 0 && attendence[8] != null }">          		                              		
                    					<input type="radio" name="${attendence[6]}"  value="0" id="0${attendence[6]}" /><label onclick="count(${attendence[6]},'0${attendence[6]}')" for="0${attendence[6]}"></label>               						
                                	</c:if>	 	                    					                    			
		                    		</td>
                                   <td>
                                   	<c:if test="${attendence[8] == 1}">                                         
                                       <input type="radio" name="${attendence[6]}" value="1"  id="1${attendence[6]}" checked="checked"/><label onclick="count(${attendence[6]},'1${attendence[6]}')" for="1${attendence[6]}"></label>
                					</c:if>
                					<c:if test="${attendence[8] != 1}">                                         
                                       <input type="radio" name="${attendence[6]}" value="1"  id="1${attendence[6]}"/><label onclick="count(${attendence[6]},'1${attendence[6]}')" for="1${attendence[6]}"></label>
                					</c:if>
                					</td>
                                	<td>
                                    <c:if test="${attendence[8] == 2}">                                         
                                       <input type="radio" name="${attendence[6]}" value="2"  id="2${attendence[6]}" checked="checked"/><label onclick="count(${attendence[6]},'2${attendence[6]}')" for="2${attendence[6]}" ></label>
                					</c:if>
                					<c:if test="${attendence[8] != 2}">                                         
                                       <input type="radio" name="${attendence[6]}" value="2"  id="2${attendence[6]}"/><label onclick="count(${attendence[6]},'2${attendence[6]}')" for="2${attendence[6]}"></label>
                					</c:if>
                                	</td>
                                	<td>
                                      	<c:if test="${attendence[8] == 3}">                                         
                                       <input type="radio" name="${attendence[6]}" value="3"  id="3${attendence[6]}" checked="checked"/><label onclick="count(${attendence[6]},'3${attendence[6]}')" for="3${attendence[6]}"></label>
                					</c:if>
                					<c:if test="${attendence[8] != 3}">                                         
                                       <input type="radio" name="${attendence[6]}" value="3"  id="3${attendence[6]}"/><label onclick="count(${attendence[6]},'3${attendence[6]}')" for="3${attendence[6]}"></label>
                					</c:if>
                                	</td>
                                	<td>
                                    	<c:if test="${attendence[8] == 4}">                                         
                                       <input type="radio" name="${attendence[6]}" value="4"  id="4${attendence[6]}" checked="checked"/><label onclick="count(${attendence[6]},'4${attendence[6]}')"for="4${attendence[6]}" ></label>
                					</c:if>
                					<c:if test="${attendence[8] != 4}">                                         
                                       <input type="radio" name="${attendence[6]}" value="4"  id="4${attendence[6]}"/><label onclick="count(${attendence[6]},'4${attendence[6]}')" for="4${attendence[6]}"></label>
                					</c:if>
                                	</td>
                                </tr>
                                </c:forEach>
								</tbody>
								<tr id="totalRow">
                                    
                                </tr>
                            </table>
                            <c:if test="${flag==1}">                       
                            <div class="tc mb15 mt20">               
                      			<input class="bbtn bgb f14 tc wh nb br3 w80 poi" type="submit" value="保存" onclick="return mySubmit()"/>                  
             				</div>
             				</c:if>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
   
        <script type="text/javascript">
       //统计人数
        $(document).ready(function() {
        	var t0=0;
        	var t1=0;
        	var t2=0;
        	var t3=0;
        	var t4=0;
           
        	$('input[checked="checked"]').each(function () {		
	        	if($(this).val()==0){t0 +=1}
	        	if($(this).val()==1){t1 +=1}
	        	if($(this).val()==2){t2 +=1}
	        	if($(this).val()==3){t3 +=1}
	        	if($(this).val()==4){t4 +=1}
			});
        
        $('#totalRow').html('<td>合计</td><td></td><td>'+t0+'</td><td>'+t1+'</td><td>'+t2+
    			'</td><td>'+t3+'</td><td>'+t4+'</td>');
        });
       //点击选择后改变统计数量
        function count(name,cid) 
        {
        	var a = name;
        	var b = cid;
        	$("#0"+name).removeAttr("checked");
        	$("#1"+name).removeAttr("checked");
        	$("#2"+name).removeAttr("checked");
        	$("#3"+name).removeAttr("checked");
        	$("#4"+name).removeAttr("checked");
        	$("#"+b).attr("checked",'checked'); 
        	var t0=0;
        	var t1=0;
        	var t2=0;
        	var t3=0;
        	var t4=0;
        	$('input[checked="checked"]').each(function () {		
	        	if($(this).val()==0){t0 +=1}
	        	if($(this).val()==1){t1 +=1}
	        	if($(this).val()==2){t2 +=1}
	        	if($(this).val()==3){t3 +=1}
	        	if($(this).val()==4){t4 +=1}
			});
        $('#totalRow').html('<td>合计</td><td></td><td>'+t0+'</td><td>'+t1+'</td><td>'+t2+
    			'</td><td>'+t3+'</td><td>'+t4+'</td>');
        }
       //切换考勤
        function findAttendence(id){     	
        	if(id != $("#attendenceId").val()){
        		//获取学生考勤信息
        		$.ajax({
        			async:false, 
            		type: "POST",
            		url: $("#contextPath").val()+"/tcoursesite/findAttendenceById",
            		data: {'id':id},
            		dataType:'json',
            		success:function(data){ 
            			var ht = getTbody(data);
            			$("#body").html(ht);
            			
            		},          		
            	});
        		$("#attendenceId").val(id);
        		//获取分数设置
        		$.ajax({
        			async:false, 
            		type: "POST",
            		url: $("#contextPath").val()+"/tcoursesite/findtAssignmentAnswerAssign",
            		data: {'id':id},
            		dataType:'json',
            		success:function(data){           			
            			$.each(data,function(key,values){
            				$("#"+key).val(""+values);
            			});
            		},          		
            	});
        		//统计数量
        		var t0=0;
            	var t1=0;
            	var t2=0;
            	var t3=0;
            	var t4=0;
               
            	$('input[checked="checked"]').each(function () {		
    	        	if($(this).val()==0){t0 +=1}
    	        	if($(this).val()==1){t1 +=1}
    	        	if($(this).val()==2){t2 +=1}
    	        	if($(this).val()==3){t3 +=1}
    	        	if($(this).val()==4){t4 +=1}
    			});
            
            $('#totalRow').html('<td>合计</td><td></td><td>'+t0+'</td><td>'+t1+'</td><td>'+t2+
        			'</td><td>'+t3+'</td><td>'+t4+'</td>');
        	}     	
        }
    
      //拼接文件tr
        function getTbody(data){
        	var ht = "";//设置上传成功后页面动态生成的文件tr
        	for(var i=0; i< data.length;i++){
        		ht +="<tr> <td>"+data[i][7]+"</td> <td>"+data[i][6]+"</td>";
        		if(data[i][8]== null || data[i][8] == 0){
        		ht +="<td><input type='radio' name='"+data[i][6]+"' value='0' id='0"+data[i][6]+"' checked='checked' /><label onclick=\"count("+data[i][6]+",'0"+data[i][6]+"')\" for='0"+data[i][6]+"'></label></td>";
        		}
        		if(data[i][8]!= null && data[i][8] != 0){             						
        		ht +="<td><input type='radio' name='"+data[i][6]+"'  value='0' id='0"+data[i][6]+"'/><label onclick=\"count("+data[i][6]+",'0"+data[i][6]+"')\" for='0"+data[i][6]+"'></td>";
        		} 
        		if(data[i][8] == 1){
        		ht +="<td><input type='radio' name='"+data[i][6]+"'  value='1' id='1"+data[i][6]+"' checked='checked' /><label onclick=\"count("+data[i][6]+",'1"+data[i][6]+"')\" for='1"+data[i][6]+"'></label></td>";     
        		}
        		else{
        		ht +="<td><input type='radio' name='"+data[i][6]+"'  value='1' id='1"+data[i][6]+"'/><label onclick=\"count("+data[i][6]+",'1"+data[i][6]+"')\" for='1"+data[i][6]+"'></label></td>"; 
        		}
        		if(data[i][8] == 2){
           		ht +="<td><input type='radio' name='"+data[i][6]+"'  value='2' id='2"+data[i][6]+"' checked='checked' /><label onclick=\"count("+data[i][6]+",'2"+data[i][6]+"')\" for='2"+data[i][6]+"'></label></td>";     
           		}
           		else{
           		ht +="<td><input type='radio' name='"+data[i][6]+"'  value='2' id='2"+data[i][6]+"'/><label onclick=\"count("+data[i][6]+",'2"+data[i][6]+"')\" for='2"+data[i][6]+"'></label></td>"; 
           		}
        		if(data[i][8] == 3){
           		ht +="<td><input type='radio' name='"+data[i][6]+"'  value='3' id='3"+data[i][6]+"' checked='checked' /><label onclick=\"count("+data[i][6]+",'3"+data[i][6]+"')\" for='3"+data[i][6]+"'></label></td>";     
           		}
           		else{
           		ht +="<td><input type='radio' name='"+data[i][6]+"'  value='3' id='3"+data[i][6]+"'/><label onclick=\"count("+data[i][6]+",'3"+data[i][6]+"')\" for='3"+data[i][6]+"'></label></td>"; 
           		}
        		if(data[i][8] == 4){
           		ht +="<td><input type='radio' name='"+data[i][6]+"'  value='4' id='4"+data[i][6]+"' checked='checked' /><label onclick=\"count("+data[i][6]+",'4"+data[i][6]+"')\" for='4"+data[i][6]+"'></label></td>";     
           		}
           		else{
           		ht +="<td><input type='radio' name='"+data[i][6]+"'  value='4' id='4"+data[i][6]+"'/><label onclick=\"count("+data[i][6]+",'4"+data[i][6]+"')\" for='4"+data[i][6]+"'></label></td>"; 
           		}
        		ht += "</tr>";
      		}

        	return ht;
        }
     //提交   
        function mySubmit(){
    	    var s = $("#attendenceId").val();
             frm.action = "${pageContext.request.contextPath}/tcoursesite/saveAttendenceGrading?tCourseSiteId="+${tCourseSite.id}+"&attendenceId="+s;             
             }
</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/tCourseSite/global.js"></script>

</body>

</html>