<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- <% String code = (String)session.getAttribute("code"); %>
<% String id = (String)session.getAttribute("id"); %>
<% String name = (String)session.getAttribute("name"); %> --%>
<script>
$(document).ready(function(){
	
	var id = "";
	id = '${id}';
	if(id){
		sessionStorage.setItem('id', id);
	}
	
	$('#name').append(sessionStorage.getItem('name'));
	$('#username').val(sessionStorage.getItem('name'));
	
	$('#code').val(sessionStorage.getItem('code'));

	$('#grade').val(sessionStorage.getItem('grade'));
	$('#id').val(sessionStorage.getItem('id'));
	$('#reg_id').val(sessionStorage.getItem('id'));
});
</script>
