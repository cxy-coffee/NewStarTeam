<template>
	<view class="box2">
		<view class="view1">-----员工基本信息-----</view>
		<view class="employeeinfoview">
			<text>姓名：</text><br />
			<input type="text" class="input1" v-model="name" />
		</view>
	
	<view class="employeeinfoview">
		<text>性别：</text><br />
		<input type="text" class="input1" v-model="gender"  />
	</view>
	<view class="employeeinfoview">
		<text>工号:(不可修改)</text><br />
		<text class="input1">{{accountNumber}}</text>
	</view>
	<view class="employeeinfoview">
		<text>身份证:(仅修改一次)</text><br />
		<text class="input1" v-show="!ifShow">{{identifyNumber}}</text>
		<input type="text" class="input1" v-model="identifyNumber" v-show="ifShow" />
	</view>
	<view class="employeeinfoview">
		<text>生日：</text>
		<input type="text" class="input1" v-model="birthday" />
	</view>
	<view class="employeeinfoview">
		<text>邮箱：</text>
		<input type="text" class="input1" v-model="email" />
	</view>
	
	<button type="primary" class="changebutton" style="margin: 50px;margin-left: 100px;margin-right: 100px;"
		@click="ensureChangeInfo()">确认修改</button>
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				ifShow: true,
				name: undefined,
				gender: undefined,
				birthday: undefined,
				email: undefined,
				identifyNumber: undefined,
				accountNumber: undefined,
				password: undefined,

			}
		},
		methods: {
			ensureChangeInfo() {
				uni.showModal({
					title: '提示',
					content: '您确定要修改您的信息吗',
					success: (res) => {
						if (res.confirm) {

							uni.request({
								url: 'http://123.57.94.22:9091/updateEmployee.do',
								data: {
									accountNumber: this.accountNumber,
									name: this.name,
									gender: this.gender,
									identifyNumber: this.identifyNumber,
									password: this.password,
									email: this.email,
									birthday: this.birthday,
								},
								header: {
									'content-type': 'application/x-www-form-urlencoded',
									'Accept': "application/json;charset=UTF-8"
								},
								method: 'POST',
								success: res => {
									if (res.data) {
										uni.showToast({
												title: '修改成功！',
												icon: 'none',
												duration: 2000,
											}),
											uni.navigateTo({
												url: '../employeeOperate/employeeOperate'
											})
									} else {
										uni.showToast({
											title: '修改失败，请检查输入！',
											icon: 'none',
											duration: 2000,
										})
									}

								}
							})

						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				})

			},

		},
		onLoad: function(options) {

			this.name = options.name;
			this.gender = options.gender;
			this.birthday = options.birthday.toString().substr(0, 10);
			this.email = options.email;
			this.identifyNumber = options.identifyNumber;
			this.accountNumber = options.accountNumber;
			console.log(this.identifyNumber);
			if (this.identifyNumber == '未设定') {
				this.ifShow = true;
			}else{
				this.ifShow = false;
				
			}
		}
	}
</script>

<style>
	.employeeinfoview {

		margin-left: 30px;
		margin-right: 30px;
		font-size: 20px;
		font-weight: 800;

	}

	,

	.box2 {
		margin-left: 20px;
		margin-right: 20px;
		margin-top: 70px;
		line-height: 50px;
		border-radius: 25px;
		background: linear-gradient(180deg, #aaffff 0%, #fff 45%, #fff 55%, #aaffff 100%);
	}

	,

	.view1 {
		text-align: center;
		font-size: 28px;
	}

	,



	.input1 {
		min-height: 15px;
		height: 15px;
		font-size: 15px;
		border: 15px;
	}

	,


	.changebutton {
		align-items: center;

		border-radius: 10px;
		margin-left: 20px;
		margin-right: 20px;
		margin-bottom: 10px;
		margin-top: 20px;


	}
</style>
