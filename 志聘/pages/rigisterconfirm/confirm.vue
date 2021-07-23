<template>
	<view>
		<view class="rigisterconfirm">
			<text style="font-size: 20px;font-weight: 800;">请输入邮箱</text>
			<input type="text" style="font-size: 20px;background-color: #F0F0F0;height: 40px;margin-top: 20px;" v-model="email" />
			<button type="primary" size="mini" style="font-size: 18px;width: 140px;margin-top: 20px;" @click="sendEmailCode()">发送验证码</button>
		</view>
		<view class="rigisterconfirm">
			<text style="font-size: 20px;font-weight: 800;">请输入验证码</text>
			<input type="text"  style="font-size: 20px; background-color: #F0F0F0;height: 40px;margin-top: 20px;" v-model="confirmCode" />
			<button type="primary" size="mini" style="font-size: 20px;width: 100px;margin-top: 20px;" @click="sendMessage()">确定</button>
		</view>
		<view class="rigisterconfirm">
			<text style="font-size: 20px;text-align: center;font-weight: 800;">第一次使用？请注册</text>
			<button type="primary" size="mini" style="font-size: 20px;width: 100px;margin-top: 20px;" @click="signUp()">注册</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				email: undefined,
				confirmCode: undefined,
				send:true
			}
		},
		methods: {
			sendEmailCode() {
				uni.request({
				
					url: 'http://123.57.94.22:9091/logout.do',
				
					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {}
				});
				if (this.email == undefined) {
					uni.showToast({
						title:'请输入正确的邮箱！',
						icon:'none',
						duration: 2000,
					})
				} 
				else {
					  uni.showToast({
					  	title:'发送成功！',
					  	icon:'none',
					  	duration: 2000,
					  }),
					uni.request({
						url: 'http://123.57.94.22:9091/sendEmailCode.do',
						data: {
							to: this.email
						},
						header: {
							'content-type': 'application/x-www-form-urlencoded',
							'Accept': "application/json;charset=UTF-8"
						},
						method: 'POST',
						success: res => {
                             
						}

					})
					this.send=true;
				}


			},
			sendMessage() {
				if (this.confirmCode == undefined) {
					uni.showToast({
						title:'请输入验证码！',
						icon:'none',
						duration: 2000,
					})
				} else {
					uni.request({
						url: 'http://123.57.94.22:9091/checkVerificationCode.do',
						data: {
							verificationCode: this.confirmCode
						},
						header: {
							'content-type': 'application/x-www-form-urlencoded',
							'Accept': "application/json;charset=UTF-8"
						},
						method: 'POST',
						success: res => {
							if (res.data == "验证通过") {
								uni.navigateTo({
									url: '../companyrigister/companyrigister'
								})
							} else {
								uni.showToast({
									title:'验证码错误！',
									icon:'none',
									duration: 2000,
								})
							}

						}
					})
				}
			},
			signUp()
			{
				uni.navigateTo({
					url: '../signUp/signUp'
				})
			}
		},
	}
</script>
<style>
	.rigisterconfirm {
		margin: 40px;
		justify-content: center;
		display: flex;
		flex-direction: column;
	}
</style>
