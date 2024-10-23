const userObject = {
	init:function(){
		$("#btn-save").on("click", (e)=>{
			e.preventDefault();
			this.save();
		}),
		
		$("#btn-update").on('click', (e)=>{
			e.preventDefault();
			this.update();
		})
		
	},
	
	save:function(){
		
		const user ={
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val(),
			gender : $("input[name='gender']:checked").val()
		}
		// 입력 검증
		if(!user.username||!user.password||!user.email||!user.gender){
			alert("모든 항목을 입력해 주세요");
			return;
		}
		
		$.ajax({
			type:"POST",
			url : "/signup",
			data : JSON.stringify(user),
			contentType : "application/json;charset=UTF-8"
		}).done(function(response){
			alert(response.data);
			if(response.status == 200)
				location.href = "/";
		}).fail(function(error){
			console.log(error);
		})
	},
	
	update : function(){
		const user={
			username : $('#username').val(),
			email : $('#email').val(),
			password : $('#password').val()
		}
		
		if(!user.username||!user.password){
			alert('정보를 모두 입력해주세요')
			return;
		}else if(!confirm('수정하시겠습니까?')){
			return;
		}
		$.ajax({
			type:"PUT",
			url:"/userinfo",
			data:JSON.stringify(user),
			contentType:"application/json;charset=UFT-8"
		}).done(function(response){
			alert(response.data)
			if(response.status == 200){
				location.href = '/';
			}
		}).fail(function(error){
			console.log(error)
		})
	}
	
	
}

userObject.init();