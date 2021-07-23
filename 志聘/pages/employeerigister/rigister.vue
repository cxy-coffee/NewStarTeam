<template>
	<view>
		<view>
			<text class='textStyle'>欢迎登录</text>
			<text style="font-size: 20px;margin: 20px;margin-top: 10px;font-weight: 800;">请输入员工账号：</text>
			<input class="uni-input" focus placeholder="请输入员工账号"
				style="font-size: 30px; margin: 20px;margin-top: 10px; " @input="setAccountNumber"
				v-model="accountNumber" :value="accountNumber" />
			<text style="font-size: 20px;margin: 20px;margin-top: 10px;font-weight: 800;">请输入密码：</text>
			<input class="uni-input" focus placeholder="请输入密码" style="font-size: 30px; margin: 20px;margin-top: 10px; "
				@input="setPassword" v-model="password" :value="password" />
		</view>
		<view>
			<button type=primary style="font-size: 25px;margin-left: 140px;margin-right: 140px;"
				@click="confirm()">登录</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				accountNumber: '',
				password: '',
			}
		},
		methods: {
			setAccountNumber(e) {
				this.accountNumber = e.target.value
			},
			setPassword(e) {
				this.password = e.target.value
			},
			confirm() {
				if (this.accountNumber == '' || this.password == '') {
					uni.showToast({
						title: '输入内容不能为空！',
						icon: 'none',
						duration: 2000
					})
				} else {
					uni.request({

						url: 'http://123.57.94.22:9091/logout.do',

						header: {
							'content-type': 'application/x-www-form-urlencoded',
							'Accept': "application/json;charset=UTF-8"
						},
						method: 'POST',
						success: res => {}
					});
					uni.request({
						url: 'http://123.57.94.22:9091/employeeLogin.do',
						complete: () => {},
						data: {
							accountNumber: this.accountNumber,
							password: this.password
						},
						header: {
							'content-type': 'application/x-www-form-urlencoded',
							'Accept': "application/json;charset=UTF-8"
						},
						method: 'POST',
						success: res => {
							if (res.data == '登录成功') {

								uni.showToast({
									title: '登录成功！',
									icon: 'none',
									duration: 2000,

								});

								uni.navigateTo({
									url: '../employeeOperate/employeeOperate'
								})
							} else {
								uni.showToast({
									title: '登录失败，请检查信息是否正确！',
									icon: 'none',
									duration: 2000,
								})
							}
						}
					});
				}
			},
		}
	}
</script>

<style>
	.textStyle {
		display: flex;
		font-size: 30px;
		//垂直居中
		align-items: center;
		//水平居中
		justify-content: center;
	}

	,
	.uni-input {
		background-color: #F8F8F8;

	}
</style>
