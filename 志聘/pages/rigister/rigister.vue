<template>
	<view class="">
		<view class="rigisterview">
			<text class="rigistertext">请选择登录方式</text>
		</view>
		<view class="rigisterway">
			<view class="companyrigisterwayview">
				<image src="../../static/company.png" class="img" @click="toCompany()"></image><br />
				<text>企业</text>
			</view>
			<br />
			<view class="staffrigisterwayview">
				<image src="../../static/staff.png" mode="aspectFit" class="img" @click="toemployee()"></image><br />
				<text>员工</text>
			</view>
			<br />
			<view class="administractorrigisterwayview">
				<image src="../../static/administractor.png" mode="aspectFit" class="img" @click="toadministractor()">
				</image><br />
				<text>管理员</text>
			</view>
			<br />
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				type: ["companyManager", "employee", "manager"],


			}
		},
		methods: {
			toCompany() {

				uni.request({

					url: 'http://123.57.94.22:9091/checkLoginState.do',
					data: {
						userType: this.type[0]
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {
						if (res.data) {
							uni.navigateTo({
								url: "../companyOperate/companyOperate"
							})
						} else {
							uni.navigateTo({
								url: "../rigisterconfirm/confirm"
							})
						}

					}


				})

			},
			toemployee() {
				uni.request({
					url: 'http://123.57.94.22:9091/checkLoginState.do',
					data: {
						userType: this.type[1]
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {
						if (res.data) {
							uni.navigateTo({
								url: "../employeeOperate/employeeOperate"
							})
						} else {
							uni.navigateTo({
								url: "../employeerigister/rigister"
							})
						}

					}


				})
			},
			toadministractor() {
				uni.request({

					url: 'http://123.57.94.22:9091/checkLoginState.do',
					data: {
						userType: this.type[2]
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {
						if (res.data) {
							uni.navigateTo({
								url: "../managerOperate/managerOperate"
							})
						} else {
							uni.navigateTo({
								url: "../managerRigister/managerRigister"
							})
						}

					}


				})
			}
		}
	}
</script>

<style>
	.rigisterview {
		display: flex;
		flex-direction: column;
		margin-left: 20px;
		margin-right: 20px;

	}



	.rigistertext {
		background-color: #5BC0DE;
		background: linear-gradient(180deg, #5BC0DE 0%, #aaffff 100%);
		font-size: 30px;
		border: #080808 dashed;
		text-align: center;
	}

	//"请选择登录方式的文本"

	.rigisterway {
		display: flex;
		flex-direction: column;
		flex-flow: column nowrap;
		justify-content: space-between;
		margin-top: 100px;

	}

	.companyrigisterwayview {
		text-align: center;
		font-size: 20px;
		font-weight: 800;
	}

	.staffrigisterwayview {
		text-align: center;
		font-size: 20px;
		font-weight: 800;
	}

	.administractorrigisterwayview {
		text-align: center;
		font-size: 20px;
		font-weight: 800;

	}


	.img {
		margin: 30px;
		margin-bottom: 0px;
		width: 75px;
		height: 75px;
	}
</style>
