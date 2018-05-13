<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>

<body>
<div class="table">
            <div class="title">
              <div id="title">网站列表</div>
              <a class="btn btn-new"  href="${pageContext.request.contextPath}/admin/newSite">新建</a>
            </div>
            <table class="news-list">
              <tr>
                  <th width="14%">网站中文名称</th>                                    
                  <th width="33%">网站底部文字</th>                                    
                  <th width="12%">网站唯一标识英文字符</th>                                    
                  <th width="11%">网站模板</th>                                    
                  <th width="8%">网站创建人</th>                                    
                  <th width="4%">状态</th>                                    
                  <th width="7%">更新时间</th>                                    
                  <th width="4%">删除</th>                                    
                  <th width="4%">设为当前</th>                                    
              </tr>
              <c:forEach  items="${sites }"  var="site">
              <tr>
                <td><a href="${pageContext.request.contextPath}/admin/editSite?siteUrl=${site.siteurl}">${site.name }</a></td>
                <td>${site.bottomContent }</td>
                <td>${site.siteurl }</td>
                <td>${site.cmsTemplate.name }	</td>
                <td>${site.user.cname }</td>
                <td>
                 <c:if test="${site.state==1 }">
                <img src="${pageContext.request.contextPath}/images/systemBack/normal.jpg"  style="width:30px;">
                </c:if>               
                 <c:if test="${site.state==0 }">
                <img src="${pageContext.request.contextPath}/images/systemBack/off.png"  style="width:30px;">
                </c:if>               
                </td>
                <td><fmt:formatDate type="date"  pattern="yy-MM-dd" value="${site.createTime.time }"/></td>
                <td><a  onclick="return confirm('确定删除此条记录？')"    href="${pageContext.request.contextPath}/admin/deleteSite?siteurlKey=${site.siteurl}"><img src="${pageContext.request.contextPath}/images/remove.png"></a></td>
                 <td>
                <c:if test="${site.current==1 }">
				<img src="${pageContext.request.contextPath}/images/systemBack/on.png"  style="width:30px;">
                </c:if>
                <c:if test="${site.current==0 }">
                <a href="${pageContext.request.contextPath}/admin/setCurrentSite?siteurl=${site.siteurl}"><img src="${pageContext.request.contextPath}/images/systemBack/icons/s.png"  style="width:30px;"></a>
                </c:if>
                </td>               
              </tr>
              </c:forEach>
            </table>          
                </div>
    
  
</body>
</html>