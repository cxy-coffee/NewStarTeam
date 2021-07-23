<!-- author:文一丁
	 createDate:07/08
	 updateDate:07/11-->
<template>
	<view>
		<input class="uni-input" focus placeholder="请输入验证码" style="font-size: 30px; margin: 20px;" @input="setCode"
			v-model="confirmCode" :value="confirmCode" />
		<button type=primary style="font-size: 20px;margin-left: 140px;margin-right: 140px;"
			@click="sendFindMessage">确认</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				name: '',
				date: '',
				gender: '',
				experiences: '',
				identifyNumber: '',
				password: '',
				email: '',
				confirmCode: '',
				accountNumber: '',
				send: true
			}
		},
		onLoad: function(e) {
			this.email = e.email;
			this.name = e.name
		},
		methods: {
			setCode(e) {
				this.confirmCode = e.target.value
			},
			sendFindMessage() {
				var that=this;
				 name=this.name;
				if (this.confirmCode == '') {
					uni.showToast({
						title: '输入内容不能为空！',
						icon: 'none',
						duration: 2000,
					})
				} else {
					this.send = false;
					//将输入的验证码发给后端
					uni.request({
						url: 'http://123.57.94.22:9091/checkVerificationCode.do',
						complete: () => {},
						data: {
							verificationCode: this.confirmCode,
						},
						header: {
							'content-type': 'application/x-www-form-urlencoded',
							'Accept': "application/json;charset=UTF-8"
						},
						method: 'POST',
						success: res => {
							if (res.data == '验证通过') {
								//如果返回值为true，跳转界面并请求发送该员工信息
								uni.request({
									url: 'http://123.57.94.22:9091/getEmployeeByEmail.do',
									complete: () => {},
									data: {
										email: this.email
									},
									header: {
										'content-type': 'application/x-www-form-urlencoded',
										'Accept': "application/json;charset=UTF-8"
									},
									method: 'POST',
									success: res => {
										console.log(res)
										if (res.data== '') {
											console.log(res)
											uni.navigateTo({
												url: '../showEmployee/showEmployee?email=' +this.email+
												'&ifShow' + true
													
											})
										} else {
											var that = this;
											uni.showModal({
												title: '未查询到该员工信息',
												content: '是否创建新的员工信息并录用？',
												success: function(res) {
													if (res.confirm) {
														uni.request({
															url: 'http://123.57.94.22:9091/registerEmployee.do',
															data: {
																name: name,
																email: that.email,
																birthday: '2000-01-01',
																gender: '未设定',
																identifyNumber: '未设定',
																password: '123456'
															},
															header: {
																'content-type': 'application/x-www-form-urlencoded',
																'Accept': "application/json;charset=UTF-8"
															},
															method: 'POST',
															success: res => {
																//跳转页面至输入员工录用信息
																uni.navigateTo({
																	url: '../hire/hire?accountNumber=' +
																		res
																		.data
																		.accountNumber
																})
															}
														})
													} else if (res.cancel) {
														uni.navigateTo({
															url: '../companyOperate/companyOperate'
														})
													}
												}
											})
										}
									}
								});

							} else {
								//如果返回值为false，显示验证码错误
								uni.showToast({
									title: '验证码错误！',
									icon: 'none',
									duration: 2000,
								})
							}
						}
					})
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
