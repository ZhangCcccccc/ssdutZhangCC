<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<head>
<meta name="decorator" content="iframe"/>
<script type="text/javascript">
var nowWeek=${week};
/**
 * EasyUI DataGrid根据字段动态合并单元格
 * @param tableID 要合并table的id
 * @param colList 要合并的列,用逗号分隔(例如："name,department,office");
 */
   function mergeCellsByField(tableID,colList){
       var ColArray = colList.split(",");
       var tTable = $('#'+tableID);
       var TableRowCnts=tTable.datagrid("getRows").length;
       var tmpA;
       var tmpB;
       var PerTxt = "";
       var CurTxt = "";
       var alertStr = "";
       for (j=ColArray.length-1;j>=0 ;j-- )
       {
           PerTxt="";
           tmpA=1;
           tmpB=0;
           
           for (i=0;i<=TableRowCnts ;i++ )
           {
               if (i==TableRowCnts)
               {
                   CurTxt="";
               }
               else
               {
                   CurTxt=tTable.datagrid("getRows")[i][ColArray[j]];
               }
               if (PerTxt==CurTxt)
               {
                   tmpA+=1;
               }
               else
               {
                   tmpB+=tmpA;
                   tTable.datagrid('mergeCells',{
                       index:i-tmpA,
                       field:ColArray[j],
                       rowspan:tmpA,
                       colspan:null
                   });
                   tmpA=1;
               }
               PerTxt=CurTxt;
           }
       }
   }
   $(function(){
      $('#spdata').datagrid({
      url:"${pageContext.request.contextPath}/getTimetableImformation?week="+nowWeek,
            onLoadSuccess: function (data) {
                if (data.rows.length > 0) {
                    //调用mergeCellsByField()合并单元格
                    mergeCellsByField("spdata", "weekday");
                }
            }
        });
   });
   function next(){
    /* var week=$('#spdata tr:eq(0) th:eq(2)').text();
    var strWeek= week.split("周");
    var nextWeek=strWeek[0]; */
     nowWeek=parseInt(nowWeek)+1;
     var text=nowWeek+"周";
$('#weekid').html('<img src="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/images/pagination_prev.gif" onclick="prev();">'+text+'<img src="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/images/pagination_next.gif" onclick="next();"/>');
     alert($('#weekid').html());
      $('#spdata').datagrid({url:"${pageContext.request.contextPath}/getTimetableImformation?week="+nowWeek});
      
    
   };
   function prev(){
 nowWeek=parseInt(nowWeek)-1;
    $('#spdata').datagrid({url:"${pageContext.request.contextPath}/getTimetableImformation?week="+nowWeek});
   }
 
</script>
</head>
<div>
<table id="spdata"  class="easyui-datagrid">
<thead><tr><th rowspan="2" field="weekday" width="40px">星期</th><th rowspan="2" field="labName" width="100px">实验室</th><th colspan="11" width="800px" id="weekid"><img src="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/images/pagination_prev.gif" onclick="prev('${week-1}');"/>${week}周<img src="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/images/pagination_next.gif" onclick="next('${week+1}');"/></th></tr>
<tr><c:forEach items="${classMap}" var="curr" varStatus="i"><th field="class_${i.index+1}" width="108px">${curr.value}</th></c:forEach></tr></thead>
</table>
</div>
