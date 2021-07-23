<template>
	<view>
		<view>
		    <text style="font-size: 20px;margin: 20px;font-weight: 800;">账号：</text>
		   <input class="uni-input" focus placeholder="请输入管理员账号" style="font-size: 25px; margin: 20px;margin-top: 10px; "  v-model="accountNumber":value="accountNumber"/>
		</view>
		<view>
		    <text style="font-size: 20px;margin: 20px;font-weight: 800;">密码：</text>
		   <input class="uni-input" focus placeholder="请输入密码" style="font-size: 25px; margin: 20px;margin-top: 10px; "  v-model="password":value="password"/>
		</view>
		<button type=primary style="font-size: 25px;margin-left: 140px;margin-right: 140px;" @click="managerRigister()">登录</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				accountNumber:'',
				password:''
			}
		},
		methods: {
			// setAccountNumber(e)
			// {
			// 	this.accountNumber=e.target.value
			// },
			// setPassword(e)
			// {
			// 	this.password=e.target.value
			// },
			managerRigister()
			{
				uni.request({
				
					url: 'http://123.57.94.22:9091/logout.do',
				
					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {}
				});
		
				if(this.accountNumber==''||this.password=='')
				{
					uni.showToast({
						title:'输入内容不能为空！',
						icon:'none',
						duration: 2000
					})
				}
				else
				{
					uni.request({
						url:'http://123.57.94.22:9091/managerLogin.do',
						complete: ()=> {},
						data:{
							accountNumber:this.accountNumber,
							password:this.password
						},
						header:{
							'content-type':'application/x-www-form-urlencoded',
							'Accept':"application/json;charset=UTF-8"
						},
						method:'POST',
						success:res=>{
							console.log(res.data);
								if(res.data=="登录成功")
								{
									uni.navigateTo({
										url:'../managerOperate/managerOperate'
									})
								}
								else
								{
									uni.showToast({
										title:'登录失败，请检查信息是否正确！',
										icon:'none',
										duration: 2000
									})
								}
						}
					});
				}
			}
		}
	}
</script>

<style>
.uni-input {
			background-color: #F8F8F8;
	
		}
</style>
