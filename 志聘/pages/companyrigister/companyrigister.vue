<template>
	<view class="companyrigister">
		<text
			style="font-size: 25px; text-align: center;text-emphasis-color: #007AFF;font-weight: 800;">欢迎使用本系统，请登录</text>
		<view id="view1">
			<view class="rigister">
				<text class="companyrigistertext" style="font-size: 20px;">公司ID：</text>
				<input type="text" class="rigisterinput" v-model="companyId" />
			</view>
			<view class="rigister">
				<text class="companyrigistertext" style="font-size: 20px;">密码：</text>
				<input type="text" class="rigisterinput" v-model="password" />
			</view>
			<view class="rigister">
				<text class="companyrigistertext" style="font-size: 20px;">工号：</text>
				<input type="text" class="rigisterinput" v-model="jobnumber" />
			</view>

		</view>
		<button type="primary" @click="isture()">登录</button>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				companyId: undefined,
				password: undefined,
				jobnumber: undefined,
			}
		},
		onLoad() {
			
			
			
		},
		
		methods: {
			isture() {
				uni.setStorageSync(
					 'companyId',
					 this.companyId
				)

				if (this.companyId !== undefined && this.password !== undefined && this.jobnumber !== undefined) {

					uni.request({
						url: 'http://123.57.94.22:9091/companyLogin.do',
						data: {
							companyId: this.companyId,
							password: this.password,
							jobNumber: this.jobnumber,

						},
						header: {
							'content-type': 'application/x-www-form-urlencoded',
							'Accept': "application/json;charset=UTF-8"
						},
						method: 'POST',
						success: res => {
							if (res.data == '登录成功') {
								uni.navigateTo({
									url: "../companyOperate/companyOperate?companyId=" + this.companyId

								})

							} else {
								uni.showToast({
									title: '公司名或密码或工号错误！',
									icon: 'none',
									duration: 2000,
								})
							}
						}

					})
					



				} else {
					uni.showToast({
						title: '请输入完整的用户名和密码！',
						icon: 'none',
						duration: 2000,
					})

				}


			}


		}
	}
</script>

<style>
	.rigister {
		margin-top: 20px;

	}

	page {
		background-color: #AAFFFF;
	}

	.rigisterinput {

		font-family: 'Courier New', Courier, monospace;
		font-size: 40px;
		margin-left: 5px;
		margin-right: 5px;
		margin-bottom: 10px;

	}

	.companyrigister {
		display: flex;
		flex-direction: column;
		margin: 20px;
		background-color: #AAFFFF;
		margin-top: 150px;
		border-radius: 20px;

	}

	.companyrigistertext {
		font-size: 10px;
		margin-left: 5px;
		margin-right: 20px;
		font-weight: 800;

	}

	#view1 {
		border: #09BB07 double;
		margin-left: 20px;
		margin-right: 20px;
		border-radius: 25px;

	}
</style>
