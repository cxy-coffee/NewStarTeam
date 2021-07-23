<template>
	<view>
		<view>
		    <text style="font-size: 20px;margin: 20px;font-weight: 800;">企业名称：</text>
		    <input class="uni-input" focus placeholder="请输入企业名字" style="font-size: 25px; margin: 20px;margin-top: 10px; "  v-model="companyName":value="companyName"/>
		</view>
		<view>
		    <text style="font-size: 20px;margin: 20px;font-weight: 800;">法人名：</text>
		    <input class="uni-input" focus placeholder="请输入法人名" style="font-size: 25px; margin: 20px;margin-top: 10px; "  v-model="legalRepresentativeName":value="legalRepresentativeName"/>
		</view>
		<view>
		    <text style="font-size: 20px;margin: 20px;font-weight: 800;">企业邮箱：</text>
		    <input class="uni-input" focus placeholder="请输入企业邮箱" style="font-size: 25px; margin: 20px;margin-top: 10px; "  v-model="companyEmail":value="companyEmail"/>
		</view>
		<view>
		    <text style="font-size: 20px;margin: 20px;font-weight: 800;">企业地址：</text>
		   <input class="uni-input" focus placeholder="请输入企业地址" style="font-size: 25px; margin: 20px;margin-top: 10px; " v-model="companyAddress":value="companyAddress"/>
		</view>
		<view>
		    <text style="font-size: 20px;margin: 20px;font-weight: 800;">联系电话：</text>
		    <input class="uni-input" focus placeholder="请输入联系电话" style="font-size: 25px; margin: 20px;margin-top: 10px; "  v-model="companyPhone":value="companyPhone"/>
		</view>
		<button type=primary style="font-size: 25px;margin-left: 120px;margin-right: 120px;" @click="signUp()">确认注册</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				companyName:'',
				companyEmail:'',
				companyAddress:'',
				companyPhone:'',
				legalRepresentativeName:''
			}
		},
		methods: {
			// setCompanyName(e)
			// {
			// 	this.companyName=e.target.value
			// },
			// setLegalRepresentativeName(e)
			// {
			// 	this.legalRepresentativeName=e.target.value
			// },
			// setCompanyEmail(e)
			// {
			// 	this.companyEmail=e.target.value
			// },
			// setCompanyAddress(e)
			// {
			// 	this.companyAddress=e.target.value
			// },
			// setCompanyPhone(e)
			// {
			// 	this.companyPhone=e.target.value
			// },
			signUp()
			{
				if(this.companyName==''||this.legalRepresentativeName==''||this.companyEmail==''||this.companyAddress==''||this.companyPhone=='')
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
						url:'http://123.57.94.22:9091/registerCompany.do',
						complete: ()=> {},
						data:{
							name:this.companyName,
							legalRepresentativeName:this.legalRepresentativeName,
							email:this.companyEmail,
							address:this.companyAddress,
							phone:this.companyPhone
						},
						header:{
							'content-type':'application/x-www-form-urlencoded',
							'Accept':"application/json;charset=UTF-8"
						},
						method:'POST',
						success:res=>{
								console.log(res);
								uni.showToast({
									title:'创建成功，请重新登录！',
									icon:'none',
									duration: 2000,
								}),
								uni.navigateTo({
									url:'../rigister/rigister'
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
			background-color: #F8F8F8;
	
		}
</style>
