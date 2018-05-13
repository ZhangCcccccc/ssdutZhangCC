<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>
<html>
<head>
    <title>pdf在线预览</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/flexPaper/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/flexPaper/flexpaper.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/flexPaper/flexpaper_handlers.js"></script>
</head>
<body>

<div id="documentViewer" class="flexpaper_viewer" style="width:770px;height:600px;margin-left:auto;margin-right:auto;"></div>

<script type="text/javascript">

    $('#documentViewer').FlexPaperViewer(
            { config : {

                SWFFile : '/gvsuntms${swfUrl}',

                Scale : 0.6,
                ZoomTransition : 'easeOut',
                ZoomTime : 0.5,
                ZoomInterval : 0.2,
                FitPageOnLoad : true,
                FitWidthOnLoad : false,
                FullScreenAsMaxWindow : false,
                ProgressiveLoading : false,
                MinZoomSize : 0.2,
                MaxZoomSize : 5,
                SearchMatchAll : false,
                InitViewMode : 'Portrait',
                RenderingOrder : 'flash',
                StartAtPage : '',

                ViewModeToolsVisible : true,
                ZoomToolsVisible : true,
                NavToolsVisible : true,
                CursorToolsVisible : true,
                SearchToolsVisible : true,
                WMode : 'window',
                localeChain: 'en_US'
            }}
    );
</script>
</body>
</html>