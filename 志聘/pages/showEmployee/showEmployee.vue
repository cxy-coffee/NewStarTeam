<template>
	<view>
		<view>
			<text class='textStyle' style="font-size: 20px;">姓名</text>
			<view class='textStyle' style="font-size: 25px;font-weight: 400;">{{name}}</view>
			<text class='textStyle'style="font-size: 20px;">生日</text>
			<view class='textStyle'style="font-size: 25px;font-weight: 400;">{{date}}</view>
			<text class='textStyle'style="font-size: 20px;">性别</text>
			<view class='textStyle'style="font-size: 25px;font-weight: 400;">{{gender}}</view>
			<text class='textStyle'style="font-size: 20px;">邮箱</text>
			<view class='textStyle'style="font-size: 25px;font-weight: 400;">{{email}}</view>
			<text class='textStyle'style="font-size: 20px;margin-top: 20px;">过往评价</text>
			<view class="comment" v-for="item in experiences"
				style="margin: 0px;font-weight: 400;font-size: 25px;margin-left: 25px;">{{item.assessment.performance}}
			</view>
			<view v-show="!ifShow">
				<button type=primary style="font-size: 20px;margin-top: 30px;margin-left: 130px;margin-right: 130px;"
					@click="addEmployee()">下一步</button>
			</view>
			<view v-show="ifShow">
				<button type=primary style="font-size: 20px;margin-top: 30px;margin-left: 150px;margin-right: 150px;"
					@click="inviteEmployee()">邀请面试</button>

			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				ifShow: true,
				name: '',
				date: '',
				gender: '',
				email: '',
				experiences: '',
				identifyNumber: '',
				password: '',
				accountNumber: '',
				assessments: '',
				performance: ''
			}
		},
		onLoad: function(e) {
			this.ifShow = e.ifShow,
				this.email = e.email,
				uni.request({
					url: 'http://123.57.94.22:9091/getEmployeeByEmail.do',
					data: {
						email: this.email
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {
						this.name = res.data.name;
						this.birthday = res.data.birthday.toString().substr(0, 10);
						this.gender = res.data.gender;
						this.email = res.data.email;
						this.identifyNumber = res.data.identifyNumber;
						this.accountNumber = res.data.accountNumber;
						this.experiences = res.data.experiences
					}
				})
		},
		methods: {
			addEmployee() {
				uni.navigateTo({
					url: '../hire/hire?accountNumber=' + this.accountNumber
				})
			},
			inviteEmployee() {
				uni.request({
					url: 'http://123.57.94.22:9091/sendInvitation.do',
					data: {
						accountNumber: this.accountNumber
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded',
						'Accept': "application/json;charset=UTF-8"
					},
					method: 'POST',
					success: res => {
						uni.showToast({
							title: '已发送面试邀请！',
							icon: 'none',
							duration: 2000,
						})
					}
				})
			}
		
	},
	
	}
</script>

<style>
	.textStyle {
		display: flex;
		font-size: 25px;
		//垂直居中
		align-items: center;
		//水平居中
		margin-left: 25px;
		font-weight: 800;
	}
</style>
