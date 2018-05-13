 jQuery.fn.rowspan = function(colIdx) {
 return this.each(function(){
  var that;
  $('tr', this).each(function(row) {
  $('td:eq('+colIdx+')', this).each(function(col) {
      if ($(this).html() == $(that).html()) {
        rowspan = $(that).attr("rowSpan");
        if (rowspan == undefined) {
  
          $(that).attr("rowSpan",1);
          rowspan = $(that).attr("rowSpan");
        }
        rowspan = Number(rowspan)+1;
        $(that).attr("rowSpan",rowspan); // do your action for the colspan cell here
        $(this).hide(); // .remove(); // do your action for the old cell here
      } else {
        that = this;
      }
      that = (that == null) ? this : that; // set the that if not already set
  });
 });

 });
}

jQuery.fn.colspan = function(rowIdx) {
 return this.each(function(){

  var that;
  $('tr', this).filter(":eq("+rowIdx+")").each(function(row) {
  $(this).find('td').each(function(col) {
 /*  alert($(this).text()!=""); */
      if ($(this).html() == $(that).html()) {
        colspan = $(that).attr("colSpan");
        if (colspan == undefined) {
          $(that).attr("colSpan",1);
          colspan = $(that).attr("colSpan");
        }
        colspan = Number(colspan)+1;
        $(that).attr("colSpan",colspan);
        $(this).hide(); // .remove();
      } else {
        that = this;
      }
      that = (that == null) ? this : that; // set the that if not already set
  });
 });

 });
 }