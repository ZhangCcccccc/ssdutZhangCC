<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta name="decorator" content="iframe"/>
</head>
  
<body>
  <div class="navigation">
	<div id="navigation">
		<ul>
			<li><a href="javascript:void(0);">基础课程</a></li>
			<li class="end"><a href="javascript:void(0);">选课组信息</a></li>
		</ul>
	</div>
  </div>
  <div class="content-box">
    <div class="title">
	  <div id="title">选课组详细信息</div>
      <a class="btn btn-return" onclick="window.history.go(-1)">返回</a>
	</div>
	<div>
  <table>
    <tbody>
    <tr>
      <td>选课组编号</td>
      <td>${courseGroup.cgCodeCustom}</td>
    </tr>
    <tr>
      <td>课程编号</td>
      <td>${courseGroup.schoolCourse.CNumber}</td>
    </tr>
    <tr>
      <td>课程名称</td>
      <td>${courseGroup.schoolCourse.CName}</td>
    </tr>
    <tr>
      <td>班级编号</td>
      <td>${courseGroup.cgClassNo}</td>
    </tr>
    <tr>
      <td>班级名称</td>
      <td>${courseGroup.cgClassName}</td>
    </tr>
    <tr>
      <td>针对专业</td>
      <td>${courseGroup.cgMajor}</td>
    </tr>
    <tr>
      <td>实际选课人数</td>
      <td>${courseGroup.cgStudentCount}</td>
    </tr>
    <tr>
      <td>上课教师</td>
      <td>${courseGroup.user.cname}[${courseGroup.user.username}]</td>
    </tr>
    <tr>
      <td>学期</td>
      <td>${courseGroup.schoolTerm.termName}</td>
    </tr>
    </tbody>
  </table>
  </div>
  </div>
</body>
</html>
