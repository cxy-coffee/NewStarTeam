<template>
	<view>
	    <view style="margin-top: 20px;margin-left: 20px;margin-right: 20px;">
		    <text style="font-size: 20px;font-weight: 800;">新员工姓名：</text>
		    <input class="uni-input" focus placeholder="请输入员工姓名" style="font-size: 25px;margin-top: 10px;" @input="setName" v-model="name":value="name"/>
	    </view>
		<br/>
	    <view style="margin-left: 20px;margin-right: 20px;">
	     	<text style="font-size: 20px;font-weight: 800;">新员工邮箱：</text>
			<input class="uni-input" focus placeholder="请输入验证邮箱" style="font-size: 25px; margin-top: 10px; " @input="setEmail" v-model="email":value="email"/>
    	</view>
		<view>
			<button type=primary style="font-size: 20px;width: 160px;margin-top: 40px;" @click="confirm()">查询员工业绩</button>
<!-- 			<button type=primary style="font-size: 30px;" @click="sendAddMessage()">录用新人</button> -->
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				name:'',
				email:'',
				confirmCode:''
			}
		},
		methods: {
			setName(e)
			{
				this.name=e.target.value
			},
			setEmail(e)
			{
				this.email=e.target.value
			},
			confirm()
			{
				if(this.name==''||this.email=='')
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
						url:'http://123.57.94.22:9091/sendEmailCode.do',
						complete: ()=> {},
						data:{
							to:this.email,
						},
						header:{
							'content-type':'application/x-www-form-urlencoded',
							'Accept':"application/json;charset=UTF-8"
						},
						method:'POST',
						success:res=>{
							uni.navigateTo({
								url:'../confirm/confirm?email='+this.email+'&name='+this.name
							})
						}
					});
				}
			}
		}
	}
</script>

<style>
.uni-input {
		background-color: #F0F0F0;

	}
</style>
