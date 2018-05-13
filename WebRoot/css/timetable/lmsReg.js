
//清空排课页面中的数据
function doCleanTimetable() {
    gStudentChoose = '0';
    $(".linkbutton").hide();
    $("input[name='setCustomClass']").attr('disabled',true);
    $("input[name='setStudentChoose']").attr('disabled',true);
    $("input[name='copyReg']").attr('disabled',true);
    $("input[name='setStudentChoose']:checkbox").each(function(){
        $(this).attr('checked',false);
    })
    $("input[name='noExperiment']:checkbox").each(function(){
        $(this).attr('checked',false);
    })
    $("input[name='regLab']:checkbox").each(function(){
        $(this).attr('checked',false);
    })
    $("input[name='setCustomReg']:checkbox").each(function(){
        $(this).attr('checked',false);
    })
    $("#groupName").val("");
    $('#timetable').val("");
    $("#timetable_number").html("");
    $("#timetable_teacher").html("");
    $("#timetable_course").html("");
    $("#experimentList").html("");

}
//选择一个选课组
function selectTimetable() {
    $(".linkbutton").show();
    $("input[name='noExperiment']:checkbox").each(function(){
        $(this).attr('checked',false);
    })
    $("input[name='setCustomClass']").attr('disabled',false);
    $("input[name='setStudentChoose']").attr('disabled',false);
    $("input[name='copyReg']").attr('disabled',false);
    var timetable_number = $("#timetable").val();
    if(timetable_number != '') {
        $.getJSON('ajaxReturnTimetableInfo',{
            timetable_number: timetable_number
        },function(data){
            gTimetableInfo = data;
            $("#timetable_number").html(data.timetable_number);
            $("#timetable_teacher").html(data.teacher_name);
            $("#timetable_course").html(data.course_name);
            returnExperiment(data.course_number,data.teacher_number,data.teacher_name);
        })
    }
}
//显示排课信息
function showReg(reg_stamp) {
    gSelectRegStamp = reg_stamp;
    $('#allRegList').empty();
    $("#showReg").window('open');
    $("#regList_reg_stamp").val(reg_stamp);
    $.getJSON('/lms/lmsReg/ajaxShowRegInfo',{
        reg_stamp: reg_stamp
    },function(data) {
        $.each(data,function(key,value){
            $("#allRegList").append("<option value="+value.experiment_id+">["+value.experiment_name+"] "+value.user_name+"</option>");
            if(!key)
                showRegDetail(gSelectRegStamp, $('#allRegList option').val());
        })
    })
    $(".detail").empty();
    $("#detail_info").html('基本信息');
    $("#detail_groups").html('分组信息');
}
function showRegDetail(stamp,experiment_id) {
    $("#detail_info").html('基本信息');
    $("#detail_groups").html('分组信息');
    if(stamp){
        var reg_stamp = stamp;
        var experiment_id = experiment_id;
    }else{
        var reg_stamp = $("#regList_reg_stamp").val();
        var experiment_id = $("#allRegList").val();
    }
    $.getJSON('/lms/lmsReg/ajaxShowRegInfo',{
        reg_stamp: reg_stamp,
        experiment_id: experiment_id
    },function(data){
        gExperimentInfo = data;
        $.each(data,function(key,value){
            $("#detail_timetable_number").text(value.timetable_number);
            $("#detail_course_number").text(value.course_number);
            $("#detail_course_name").text(value.course_name);
            $("#detail_experiment_number").text(value.experiment_number);
            $("#detail_experiment_name").text(value.experiment_name);
            $("#detail_lab").text(value.lab_name);
            $("#detail_class").text(value.class_name);
            $("#detail_weekday").text(value.weekday_name);
            $("#detail_week").text(value.week);
            $("#detail_user_name").text(value.user_number+"  "+value.user_name);
            $("#detail_group_name").text(value.group_name);
            $("#detail_group_limit").text(value.group_limit);
            $("#detail_group_start").text(value.group_start);
            $("#detail_group_end").text(value.group_end);
            if(value.user_number == gUserNumber || isSuperAdmin == '1') {
                $("#detail_info").html("基本信息<a href='javascript:void(0)' onclick='modifyExperiment()' style='float: right; margin-right: 15px'><img src='/images/icn_edit.png' border='0' title='修改' name='修改'></a>");
            }
            if(isSuperAdmin == '1') {
                $("#detail_groups").html("分组信息<a href='javascript:void(0)' onclick='modifyGroups()' style='float: right; margin-right: 15px'><img src='/images/icn_edit.png' border='0' title='修改' name='修改'></a>");
            }
        })
    })
}
//得到所有可用周次
function getAviableWeek(id) {
    var lab = $("#lab"+id).combobox('getValues');
    if(gCustomClass == '1') {
        var newClass = $("#customClass"+id).combobox('getValues');
        if(newClass != "") {
            gClassids = newClass.toString();
        } else {
            $.messager.alert('错误','请选择节次!');
            return false;
        }
    }
    
    if(gClassids != "" && lab != "") {
        $.messager.progress({
            title:'请稍后',
            msg:'系统在正筛选可用周次......'
        });
        $("#week"+id).combobox({
            url: 'ajaxGetAviableWeek?term='+gTerm+'&weekday='+gWeekday+'&lab='+lab.toString()+'&class='+gClassids,
            valueField: 'id',
            textField: 'name'
        })
        $.messager.progress('close');
        $.messager.alert('提示', '<span style="color: red">被占用的周次将不会在周次列表中显示!</span>');
        /*
        $.messager.show({
            title: '提示',
            msg: '<span style="color: red">被占用的周次将不会在周次列表中显示!</span>',
            showType: 'slide',
            timeout: 4000
        })
        */
    } else {
        $.messager.alert('错误','请选择实验室!');
    }
}

//得到所有可用周次
function getAviableWeek2(id) {
    var lab = $("#lab"+id).combobox('getValues');
    var Class = $("#class"+id).combobox('getValues');
    var weekday = $("#weekday"+id).val();
    var term = $("#term"+id).val();
    if(Class != "" && lab != "") {
        $.messager.progress({
            title:'请稍后',
            msg:'系统在正筛选可用周次......'
        });
        $("#week"+id).combobox({
            url: 'ajaxGetAviableWeek?term='+term+'&weekday='+weekday+'&lab='+lab.toString()+'&class='+Class.toString(),
            valueField: 'id',
            textField: 'name'
        })
        $.messager.progress('close');
        $.messager.alert('提示', '<span style="color: red">被占用的周次将不会在周次列表中显示!</span>');
        /*
        $.messager.show({
            title: '提示',
            msg: '<span style="color: red">被占用的周次将不会在周次列表中显示!</span>',
            showType: 'slide',
            timeout: 4000
        })
         */
    } else {
        $.messager.alert('错误','请选择实验室!');
    }
}

//排课提交

function submit() {
    var save = '1';
    var jsonArray = new Array();
    $.messager.progress({
        title:'请稍后',
        msg:'正在将你的排课信息进行冲突判断,并写入数据库.......'
    });
    $.post('ajaxReturnNextRegStamp',function(data){
        gRegStamp = data;
        $.each(gExperimentList.rows,function(key,value){
            var lab = $("#lab"+value.id).combobox('getValues');
            var week = $("#week"+value.id).combobox('getValues');
            var teacher = $("#teacher"+value.id).val();
            if(gCustomClass == '1') {
                var newClass = $("#customClass"+value.id).combobox('getValues');
                if(newClass != "") {
                    gClassids = newClass.toString();
                }
            }
            if(lab != "" && week != "") {
                lab = lab.toString();
                week = week.toString();
                lab = (lab.split(',')).join('_');
                week = (week.split(',')).join('_');
                gClassids = (gClassids.split(',')).join('_');
                var str = gTerm + "-" + gWeekday + "-" + gClassids.toString() + "-" + week.toString() + "-" + lab.toString() + "-" + gTimetableInfo.timetable_number + "-" +gTimetableInfo.course_number + "-" + value.id + "-" + teacher + "-" + gRegStamp + "-" + gStudentChoose + "-" + save + "-" + gLabCenter + "-" + gAcademyNumber;
                jsonArray.push(str);
            } else {
                $.messager.progress('close');
                $.messager.alert('<font color=red>错误</font>', '<font color=red size=3px>请填写完整!</font>');
                /*
                $.messager.show({
                    title: '<font color=red>错误</font>',
                    msg: '<font color=red size=3px>请填写完整!</font>',
                    showType: 'fade',
                    timeout:  3000
                })
                */
            }
        })
        
        $.getJSON('regAdd',{
            json: jsonArray.join(',')
        },function(data){
            if(data.length < 1) {
                if(gStudentChoose == "1") {
                    //如果需要分组，则写入分组
                    $.post('regGroups',{
                        timetable_number: gTimetableInfo.timetable_number,
                        reg_stamp: gRegStamp,
                        groupname: gStudentGroupName,
                        accesstype: gGroupAccessType,
                        grouplimit: gStudentGroupLimit,
                        autoGroup: gStudentAutoGroup
                    },function(data){ 
                        //判断写入分组是否成功
                        if(data == 'success') {
                            $.messager.progress('close');
                            $.messager.confirm('消息','排课成功！<br>由于您所排课课程需要学生选课,<br>请所有排课完成之后在左边菜单\"发布选课\"中确认您的排课信息并发布该课程!',function(r){
                                if(r) {
                                    window.location='reg?term='+gTerm;
                                }
                            })
                        } else {
                            $.messager.progress('close');
                            //回滚之前的排课记录
                            rollBack(gRegStamp); 
                            $.messager.alert('错误','写入分组数据失败！请重新提交!<br>错误原因:'+data);
                        }
                    })
                } else {
                    $.messager.progress('close');
                    
                    $.messager.confirm('消息','排课成功！',function(r){
                        if(r) {
                            $("#showTimetable").window('close');
                            window.location='reg?term='+gTerm;
                            $.messager.show({
                                title: '提示',
                                msg: "<span style='color: red; font-size: 15px'>请稍后,页面正在刷新!</span>",
                                showType: 'slide',
                                timeout: 5000
                            })
                        }
                    })
                }
            } else {
                //回滚之前的排课记录
                rollBack(gRegStamp);
                $.messager.progress('close');
                $("#conflictDetail").empty();
                var str = "<tr style='height: 30px'><th>您排的实验</th><th>与你冲突的排课的详情</th></tr>";
                $.each(data,function(key,value) {
                    str += "<tr><td style='height: 20px'>"+$("#experimentName"+value.id).text()+"</td><td><ul>";
                    $.each(value.reg_stamp,function(k,v) {
                        $.getJSON('/lms/lmsReg/ajaxShowRegInfo', {
                            reg_stamp: v
                        }, function(info) {
                            if(info[0].reg_type < '5') {
                                str += "<li>课程:"+info[0].course_name+" 实验室:"+info[0].lab_name+" 星期:"+info[0].weekday_name+" 周次:"+info[0].week+" 节次:"+info[0].class_name+"</li>";
                            } else if(info[0].reg_type == '5') {
                                str += "<li>申请内容:"+info[0].title+" 实验室:"+info[0].lab_name+" 开始时间:"+info[0].date+" "+info[0].starttime+" 结束时间:"+info[0].date+" "+info[0].endtime+"</li>";
                            } else {
                                str += "<li>申请内容:"+info[0].title+" 实验室:"+info[0].lab_name+" 星期:"+info[0].weekday_name+" 周次:"+info[0].week+" 节次:"+info[0].class_name+"</li>";
                            }
                        }) 
                    })
                    str += "</ul></td><tr>";
                })
                $("#conflictWindow #conflictDetail").html(str);
                $("#conflictWindow").window('open');
            }
        })
    })
}
//排课回滚
function rollBack(reg_stamp) {
    $.post('ajaxRollBack',{
        reg_stamp: reg_stamp
    },function(data){
        if(data == 'yes') {
            $.messager.show({
                title:'回滚操作',
                msg:'回滚成功！',
                timeout:3000,
                showType:'slide'
            });
        } else {
            $.messager.show({
                title:'回滚操作',
                msg:'回滚失败！',
                timeout:3000,
                showType:'slide'
            });
        }
    })
}
//搜索选课组
function searchTimetableAction() {
    $('#searchTimetable').window('open');
}
function searchTimetableSubmit() {
    var num = $('#searchTimetableNum').val();
    var course = $('#searchTimetableCourse').val();
    var teacher = $('#searchTimetableTeacher').val();
    if(num == '' && course == '' && teacher == '') {
        $.messager.alert('错误','请至少输入一个查询条件!');
        return false;
    }
    $('#timetableTT').datagrid({
        url: encodeURI('/lms/lmsReg/ajaxSearchTimetable?num='+num+'&course='+course+'&teacher='+teacher),
        title: '搜索选课组',
        width: 670,
        height: 'auto',
        fitColumns: true,
        rownumbers:true,
        singleSelect: true,
        columns:[[
        {
            field:'num',
            title:'编号',
            width:100
        },

        {
            field:'course',
            title:'课程',
            width:200
        },

        {
            field:'teacher',
            title:'教师',
            width:100
        },

        {
            field:'classno',
            title:'班次',
            width:50
        },

        {
            field:'do',
            title:'操作',
            width:30,
            align:'left'
        }
        ]]
    });
}
function searchTimetableSelected(id) {
    $('#timetable').val(id);
    $('#searchTimetable').window('close');
    selectTimetable();
}
function searchTeacherAction() {
    $("#searchTeacher").window('open');
}
function searchTeacherSubmit() {
    var usernumber = $('#sys_user_user_number').val();
    var firstname = $('#sys_user_first_name').val();
    if(usernumber == '' && firstname == '') {
        $.messager.alert('错误','请至少输入一个查询条件!');
        return false;
    }
    $('#tt-teacher').datagrid({
        url: encodeURI('/lms/home/searchTeacher?usernumber='+usernumber+'&firstname='+firstname),
        title: '搜索教师',
        width: 670,
        height: 'auto',
        fitColumns: true,
        rownumbers:true,
        singleSelect: true,
        columns:[[
        {
            field:'usernumber',
            title:'工号',
            width:150
        },

        {
            field:'firstname',
            title:'姓名',
            width:150
        },

        {
            field:'academy',
            title:'学院',
            width:200
        },

        {
            field:'do',
            title:'操作',
            width:30,
            align:'left'
        }
        ]]
    });
}
function searchTeacherSelected(id,usernumber,name){
    $(".addTeacher").append("<option value="+usernumber+">"+name+"</option>");
    $('#searchTeacher').window('close');
}
//设置自定节次
function setCustomClass() {
    $("input[name='setCustomClass']:checkbox").each(function(){
        if($(this).attr('checked')) {
            gCustomClass = '1';
        } else {
            gCustomClass = '0';
        }
        selectTimetable();
    })
}
//显示排课分组信息界面
function showStudentChoose() {
    $("input[name='setStudentChoose']:checkbox").each(function(){
        if($(this).attr('checked')) {
            gStudentChoose = '1';
            $("#showStudentChoose").window('open');
        } else {
            gStudentChoose = '0';
        }
    })
}
//设置排课分组信息
function setStudentChoose() {
    gStudentChoose = '1';
    gStudentGroupName = $("#groupName").val();
    gStudentGroupLimit = $("#groupLimit").val();
    gGroupAccessType = $("#accessType").val();
    $("input[name='autoGroup']:checkbox").each(function(){
        if($(this).attr('checked')) {
            gStudentAutoGroup = '1';
        } else {
            gStudentAutoGroup = '0';
        }
    })
    if(gStudentGroupName == "" || gStudentGroupLimit == "") {
        $.messager.alert('错误','请填写完整！');
        return false;
    }
    $('#showStudentChoose').window('close');
}
//取消排课分组
function cancelStudentChoose() {
    gStudentChoose = '0';
    $("input[name='setStudentChoose']:checkbox").each(function(){
        $(this).attr('checked',false);
    })
    $("#showStudentChoose").window('close');
    $("#showUpdateStudentChoose").window('close');
}
//修改实验排课
function modifyExperiment() {
    $("#modifyExperiment").window('open');
    $.each(gExperimentInfo,function(key,value){
        $("#m-experimentNumber").val(value.experiment_number);
        $("#m-experimentName").val(value.experiment_name);
        $("#m-user_number").val(value.user_number);
        $("#m-teacher").val(value.user_name);
        $("#termm").val(value.term_id);
        $("#m-regstamp").val(value.reg_stamp);
        $("#m-experiment_id").val(value.experiment_id);
        $("#weekdaym").val(value.weekday);
        $("#classm").combobox('setValues',value.class_id.split(','));
        $("#labm").combobox('setValues',value.lab_id.split(','));
        $("#weekm").combobox('setValues',value.week.split(','));
    })
}
//更新排课内容
function updateExperiment(){
    var termid = $("#termm").val();
    var regstamp = gSelectRegStamp;
    var experiment = $("#m-experiment_id").val();
    var weekday = $("#weekdaym").val();
    var Class = $("#classm").combobox('getValues');
    var lab = $("#labm").combobox('getValues');
    var week = $("#weekm").combobox('getValues');
    if(week == "" || lab == "" || Class == "" || weekday == "") {
        $.messager.alert('错误','请填写完整!','error');
        return false;
    }
    $.post('regUpdate',{
        termid:             termid,
        regstamp:           regstamp,
        experiment_id:      experiment,
        classids:           Class.toString(),
        lab:                lab.toString(),
        week:               week.toString(),
        weekday:            weekday
    },function(data){
        if(data == 'success') {
            showRegDetail();
            $("#modifyExperiment").window('close');
            $.messager.show({
                title: '消息',
                msg: '修改实验排课信息成功!',
                showType: 'fade',
                timeout: 3000
            });
        } else {
            $.messager.alert('错误',data);
        }
    })
}
//修改分组情况
function modifyGroups() {
    $("#showUpdateStudentChoose").window('open');
    $.each(gExperimentInfo,function(key,value){
        $("#updateTimetableNumber").val(value.timetable_number);
        $("#updategroupid").val(value.group_id);
        $("#updategroupName").val(value.group_name);
        $("#updategroupLimit").val(value.group_limit);
        $("#updatestarttime").datetimebox('setValue',value.group_start);
        $("#updateendtime").datetimebox('setValue',value.group_start);
    })
}
//更新分组情况
function updateGroups() {
    $.post("regGroups",{
        id: $("#updategroupid").val(),
        timetable_number: $("#updateTimetableNumber").val(),
        reg_stamp: gSelectRegStamp,
        groupname: $("#updategroupName").val(),
        grouplimit: $("#updategroupLimit").val()
    },function(data){
        if(data == 'success') {
            $("#showUpdateStudentChoose").window('close');
            $.messager.alert('消息','修改分组信息成功！');
        }
    })
}

function labRegShow() {
    $("input[name='regLab']:checkbox").each(function(){
        if($(this).attr('checked')) {
            $("#timetableDiv *").hide();
            $("#timetableReg").hide();
            $("#labReg").show();
            $("#noExperiment").hide();
            $("input[name='setCustomReg']").attr('disabled',true);
            $("input[name='setCustomClass']").attr('disabled',true);
            $("input[name='setStudentChoose']").attr('disabled',true);
            $("input[name='copyReg']").attr('disabled',true);
            $(".linkbutton").hide();
        } else {
            $("#timetableDiv *").show();
            $("#timetableReg").show();
            $("#labReg").hide();
            $("input[name='setCustomReg']").attr('disabled',false);
            selectTimetable();
        }
    })
}
function labRegAviableWeek() {
    var lab = $("#labRegLab").combobox('getValues');
    $.messager.progress({
        title:'请稍后',
        msg:'正在处理冲突周次信息......'
    });
    $("#labRegWeek").combobox({
        url: 'ajaxGetAviableWeek?term='+gTerm+'&weekday='+gWeekday+'&lab='+lab.toString()+'&class='+gClassids,
        valueField: 'id',
        textField: 'name'
    })
    $.messager.progress('close');
}
function labRegSubmit() {
    var lab = $("#labRegLab").combobox('getValues');
    var week = $("#labRegWeek").combobox('getValues');
    var apply = $("#apply").val();
    if(lab == "" || week == "" || apply == "") {
        $.messager.show({
            title: '错误',
            msg: "<span style='font-size:16px; color:red'>请填写完整!</span>",
            timeout: 3000,
            showType: 'fade'
        })
        return false;
    }
    $.post('ajaxReturnNextRegStamp',function(regstamp){
        $.post('labRegAdd',{
            term: gTerm,
            weekday: gWeekday,
            classstring: gClassids,
            week: week.toString(),
            lab: lab.toString(),
            regstamp: regstamp,
            apply: apply
        },function(data){
            if(data == 'success') {
                $.messager.confirm('成功', '实验室预约保存成功！时间确认后请在\"排课管理\"中选择您的预约并提交审核!', function(r){
                    if (r){
                        window.location.reload();
                    }
                });
               
            } else {
                $.messager.alert('错误','实验室预约失败！错误原因:[<font color=red>'+data+"</font>]");
            }
        });
    });
}
function customTimetableShow() {
    $("input[name='setCustomReg']:checkbox").each(function(){
        if($(this).attr('checked')) {
            $("#customTimetable").window('open');
        }
    });
}
function customTimetableCancel() {
    $("#customTimetable").window('close');
    $("input[name='setCustomReg']:checkbox").each(function(){
        $(this).attr('checked',false);
    })
}
function customTimetableSubmit() {
    var sl = $("#sl").val();
    if(sl == "") {
        var ctimetable = "L"+$("#customTimetable #customTimetableNumber").val();
        var ccourse = $("#customTimetable #customTimetableCourse").val();
        var cterm = $("#customTimetable #customTimetableTerm").val();
        if(ctimetable == "" || ccourse == "" || cterm == "") {
            $.messager.show({
                title: '错误',
                msg: '请填写完整在进行提交!',
                showType: 'slide',
                timeout: 3000
            })
            return false;
        }
        $.getJSON('/lms/lmsReg/ajaxAddCustomTimetable',{
            timetable: ctimetable,
            course: ccourse,
            term: cterm
        },function(data) {
            if(data['result'] == 'success') {
                $("#timetable").append("<option value="+data['data'].timetable_number+">"+data['data'].text+"</option>");
                $("#timetable").val(data['data'].timetable_number);
                $("#customTimetable").window('close');
                $.messager.show({
                    title: '提示',
                    msg: '添加自定义选课组成功!',
                    showType: 'slide',
                    timeout: 3000
                })
                selectTimetable();
            } else {
                $.messager.alert('错误','添加失败!<br>错误原因:'+data['msg']);
            }
        })
    } else {
        $.ajax({
            url: 'checkCustomTimetableStudent',
            type: 'post',
            dataType: 'json',
            data: {
                sl: sl
            },
            success: function(data){
                $.messager.progress('close');
                $.messager.confirm('提示',"系统有效的学号有:"+data.success+"个.<br>不存在的学号为: "+data.error+" !<br>继续导入请点击'确定',修改请'取消'!<br><font color=red>注意: 继续导入将不会导入系统不存在的学号!</font>",function(r){
                    var ctimetable = "L"+$("#customTimetable #customTimetableNumber").val();
                    var ccourse = $("#customTimetable #customTimetableCourse").val();
                    var cterm = $("#customTimetable #customTimetableTerm").val();
                    if(ctimetable == "" || ccourse == "" || cterm == "") {
                        $.messager.show({
                            title: '错误',
                            msg: '请填写完整在进行提交!',
                            showType: 'slide',
                            timeout: 3000
                        })
                        return false;
                    }
                    $.getJSON('/lms/lmsReg/ajaxAddCustomTimetable',{
                        timetable: ctimetable,
                        course: ccourse,
                        term: cterm,
                        sl: sl
                    },function(data) { 
                        if(data['result'] == 'success') {
                            $("#timetable").append("<option value="+data['data'].timetable_number+">"+data['data'].text+"</option>");
                            $("#timetable").val(data['data'].timetable_number);
                            $("#customTimetable").window('close');
                            $.messager.show({
                                title: '提示',
                                msg: '添加自定义选课组成功!',
                                showType: 'slide',
                                timeout: 3000
                            })
                            selectTimetable();
                        } else {
                            $.messager.alert('错误','添加失败!<br>错误原因:'+data['msg']);
                        }
                    })
                })
            },
            beforeSend: function(){
                $.messager.progress({
                    title: '处理中',
                    msg: '系统正在为你提交数据,并导入学生数据,请稍后.......'
                })
            },
            error:function(data) {
                $.messager.progress('close');
                $.messager.alert('错误','出错啦! <br>错误原因:'+data);
            }    
        })
    }
}

function copyTimetableShow() {
    $("#copyTimetableWindows").window('open');
    $.getJSON('/lms/lmsReg/ajaxTimetableGroups',{
        timetable_number: $("#timetable").val()
    },function(data){

        })
}
function copyTimetableCancel() {
    $("#copyTimetableWindows").window('close');
    $("input[name='copyReg']:checkbox").each(function(){
        $(this).attr('checked',false);
    })
}
function copyTimetableSet() {

}
function setNoExperiment() {
    $.post('/lms/lmsReg/noExperiment',{
        course_name: gTimetableInfo.course_name,
        course_number: gTimetableInfo.course_number
    },function(data){
        if(data == 'success') {
            $.messager.show({
                title:  '提示',
                msg:   '<span style=\"font-size: 14px\">已经为您添加系统默认实验!您可以继续排课!</span>',
                showType: 'slide',
                timeout: 3000
            })
            returnExperiment(gTimetableInfo.course_number,gTimetableInfo.teacher_number,gTimetableInfo.teacher_name);
        } else {
            $.messager.alert('错误','为您添加系统默认实验失败!<br>错误原因:'+data,'error');
        }
    })
}