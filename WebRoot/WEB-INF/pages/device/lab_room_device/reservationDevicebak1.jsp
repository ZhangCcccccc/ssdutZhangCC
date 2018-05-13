<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<head>
	<meta name="decorator" content="none"/>
	<title>Combo box in the lightbox</title>

	<script src="${pageContext.request.contextPath}/js/dhtmlx/dhtmlxscheduler.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/js/dhtmlx/dhtmlxscheduler.css" type="text/css" media="screen" title="no title"
	      charset="utf-8">
	<script src="${pageContext.request.contextPath}/js/dhtmlx/ext/dhtmlxscheduler_editors.js" type="text/javascript" charset="utf-8"></script>

	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/dhtmlx/dhtmlxCombo/dhtmlxcombo.css">
	<script src="${pageContext.request.contextPath}/js/dhtmlx/dhtmlxCombo/dhtmlxcombo.js"></script>

	<style type="text/css" media="screen">
		html, body {
			margin: 0px;
			padding: 0px;
			height: 100%;
			overflow: hidden;
		}
	</style>

	<script type="text/javascript" charset="utf-8">
		if (!window.dhtmlXCombo)
			alert("You need to have dhtmlxCombo files, to see full functionality of this sample.");

		function init() {
			scheduler.config.multi_day = true;

			scheduler.config.event_duration = 30;
			scheduler.config.auto_end_date = true;
			scheduler.config.details_on_create = true;
			scheduler.config.details_on_dblclick = true;

			scheduler.locale.labels.section_snack = "Choose your snack:";

			var snacks = [
				{ key: 5, label: 'Pineapple' },
				{ key: 6, label: 'Chocolate' },
				{ key: 7, label: 'Chips' },
				{ key: 8, label: 'Apple pie' }
			];

			scheduler.config.lightbox.sections = [
				{ name: "description", height: 50, map_to: "text", type: "textarea", focus: true },
				{ name: "snack", options: snacks, map_to: "combo_select", type: "combo", image_path: "../common/dhtmlxCombo/imgs/", height:30, filtering: true },
				{ name: "time", height: 72, type: "time", map_to: "auto"}
			];

			scheduler.config.xml_date = "%Y-%m-%d %H:%i";
			scheduler.init('scheduler_here', new Date(2015,1,10), "week");
			scheduler.load("data/events.xml", function(){
				//show lightbox
				scheduler.showLightbox("1261150549")
			});

		}
	</script>

</head>

<body onload="init();">
<div id="scheduler_here" class="dhx_cal_container" style='width:100%; height:100%;'>
	<div class="dhx_cal_navline">
		<div class="dhx_cal_prev_button">&nbsp;</div>
		<div class="dhx_cal_next_button">&nbsp;</div>
		<div class="dhx_cal_today_button"></div>
		<div class="dhx_cal_date"></div>
		<div class="dhx_cal_tab" name="day_tab" style="right:204px;"></div>
		<div class="dhx_cal_tab" name="week_tab" style="right:140px;"></div>
		<div class="dhx_cal_tab" name="month_tab" style="right:76px;"></div>
	</div>
	<div class="dhx_cal_header">
	</div>
	<div class="dhx_cal_data">
	</div>
</div>
</body>