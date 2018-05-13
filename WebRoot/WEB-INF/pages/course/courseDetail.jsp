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
			<li class="end"><a href="javascript:void(0);">课程详细信息</a></li>
		</ul>
	</div>
  </div>
  <div class="content-box">
  <div class="title">
	  <div id="title">课程详细信息</div>
      <a class="btn btn-return" onclick="window.history.go(-1)">返回</a>
	</div>
	<div>
  <table>
    <tbody>
    <tr>
      <td>课程编号</td>
      <td>${schoolCourse.CNumber}</td>
    </tr>
    <tr>
      <td>课程名称</td>
      <td>${schoolCourse.CName}</td>
    </tr>
    <tr>
      <td>所属学院</td>
      <td>${schoolCourse.schoolAcademy.academyName}</td>
    </tr>
    <tr>
      <td>课程类型</td>
      <td>${schoolCourse.CCategoryMain}</td>
    </tr>
    <tr>
      <td>理论学时</td>
      <td>${schoolCourse.CTheoreticalHours}</td>
    </tr>
    <tr>
      <td>总学时</td>
      <td>${schoolCourse.CTotalHours}</td>
    </tr>
    <tr>
      <td>课程备注</td>
      <td>${schoolCourse.CComment}</td>
    </tr>
    </tbody>
  </table>
  </div>
  </div>

</body>
</html>
